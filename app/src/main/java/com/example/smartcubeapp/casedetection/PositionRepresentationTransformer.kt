package com.example.smartcubeapp.casedetection

import android.content.Context
import com.example.smartcubeapp.casedetection.olldetection.OLLPositionRepresentationElement
import com.example.smartcubeapp.casedetection.olldetection.ollcase.CustomOLLCase
import com.example.smartcubeapp.casedetection.plldetection.PLLPositionRepresentationElement
import com.example.smartcubeapp.casedetection.plldetection.pllcase.CustomPLLCase
import com.example.smartcubeapp.cube.BlueSide
import com.example.smartcubeapp.cube.CubeSide
import com.example.smartcubeapp.cube.CubeState
import com.example.smartcubeapp.cube.GreenSide
import com.example.smartcubeapp.cube.RedSide
import com.example.smartcubeapp.cube.WhiteSide
import com.example.smartcubeapp.cube.YellowSide
import com.example.smartcubeapp.cube.piece.ElementOrientation
import com.example.smartcubeapp.cube.piece.Orientation
import com.example.smartcubeapp.cube.piece.PieceType
import com.example.smartcubeapp.elementdatabase.ElementDatabaseConstants
import com.example.smartcubeapp.elementdatabase.casedetectiondatabase.CaseElementOrientationDBService

class PositionRepresentationTransformer<T>(var cubeState: CubeState, var cubeSide: CubeSide) {

    inline fun <reified T> transformStateToPositionRepresentation(context: Context): Array<Array<T>> {
        when (cubeSide) {
            YellowSide -> {
                return stateToPositionRepresentationYellow(context)
            }

            WhiteSide -> {
                return stateToPositionRepresentationWhite(context)
            }

            GreenSide -> {
                return stateToPositionRepresentationGreen(context)
            }

            BlueSide -> {
                return stateToPositionRepresentationBlue(context)
            }

            RedSide -> {
                return stateToPositionRepresentationRed(context)
            }

            else -> return stateToPositionRepresentationOrange(context)
        }
    }

    inline fun <reified T> createRotatedPositionsArray(): Array<Array<T>> {
        val positionsClass = T::class.java
        return arrayOf(
            arrayOf(
                positionsClass.newInstance(),
                positionsClass.newInstance(),
                positionsClass.newInstance()
            ),
            arrayOf(
                positionsClass.newInstance(),
                positionsClass.newInstance()
            ),
            arrayOf(
                positionsClass.newInstance(),
                positionsClass.newInstance(),
                positionsClass.newInstance()
            )
        )
    }

    inline fun <reified T> sideRelativePosition(element: T, position: Pair<Int, Int>) {
        if (element is OLLPositionRepresentationElement) {
            element.sideRelativePosition = position
        } else if (element is PLLPositionRepresentationElement) {
            element.sideRelativePosition = position
        }
    }


    inline fun <reified T> rotatePositionClockwise(
        position: Array<Array<T>>
    ): Array<Array<T>> {

        val rotatedPosition: Array<Array<T>> = createRotatedPositionsArray()

        //corners
        rotatedPosition[0][0] = position[2][0]
        sideRelativePosition(rotatedPosition[0][0], Pair(0, 0))
        rotatedPosition[0][2] = position[0][0]
        sideRelativePosition(rotatedPosition[0][2], Pair(0, 2))
        rotatedPosition[2][0] = position[2][2]
        sideRelativePosition(rotatedPosition[2][0], Pair(2, 0))
        rotatedPosition[2][2] = position[0][2]
        sideRelativePosition(rotatedPosition[2][2], Pair(2, 2))

        //edges
        rotatedPosition[0][1] = position[1][0]
        sideRelativePosition(rotatedPosition[0][1], Pair(0, 1))
        rotatedPosition[1][1] = position[0][1]
        sideRelativePosition(rotatedPosition[1][1], Pair(1, 2))
        rotatedPosition[2][1] = position[1][1]
        sideRelativePosition(rotatedPosition[2][1], Pair(2, 1))
        rotatedPosition[1][0] = position[2][1]
        sideRelativePosition(rotatedPosition[1][0], Pair(1, 0))

        return rotatedPosition
    }


    inline fun <reified CaseType, reified T> positionRepresentationToCase(position: Array<Array<T>>): CaseType {

        if (CaseType::class.java == CustomOLLCase::class.java
            && T::class.java == PLLPositionRepresentationElement::class.java) {
            throw Exception("Cannot use PLLPositionRepresentationElement in OLLCase")
        }
        else if(CaseType::class.java == CustomPLLCase::class.java
            && T::class.java == OLLPositionRepresentationElement::class.java) {
            throw Exception("Cannot use OLLPositionRepresentationElement in PLLCase")
        }

        val elements = mutableListOf<T>()
        for (row in position) {
            for (element in row) {
                if (element is OLLPositionRepresentationElement) {
                    if (element.sideRelativeOrientation != Orientation.Correct) {
                        elements.add(element as T)
                    }
                }
                else {
                    elements.add(element)
                }
            }
        }
        val case = CaseType::class.java.newInstance()
        when (case) {
            is CustomOLLCase -> {
                case.incorrectlyOrientedPieces =
                    elements as MutableList<OLLPositionRepresentationElement>
            }

            is CustomPLLCase -> {
                case.lastLayerPieces = elements as MutableList<PLLPositionRepresentationElement>
            }

            else -> {
                throw Exception("Unknown case type")
            }
        }
        return case
    }

    fun changeCubeSide(cubeSide: CubeSide) {
        this.cubeSide = cubeSide
    }

    fun changeCubeState(cubeState: CubeState) {
        this.cubeState = cubeState
    }


    fun getSideRelativePositionRepresentation(
        pieceType: PieceType,
        pieceNumber: Int,
        piecePosition: Int,
        pieceOrientation: Int,
        context: Context
    ): Orientation {
        val db =
            CaseElementOrientationDBService(context, ElementDatabaseConstants.CASE_DATABASE_NAME)

        val element = ElementOrientation(
            sideName = cubeSide.sideName,
            pieceNumber = pieceNumber,
            pieceType = pieceType,
            piecePosition = piecePosition,
            pieceOrientation = pieceOrientation
        )
        return db.getOrientationForElement(element) ?: Orientation.Incorrect
    }

    inline fun <reified T> createPositionRepresentationElement(
        pieceType: PieceType,
        piecePosition: Int,
        context: Context,
        sideRelativePosition: Pair<Int, Int>,
    ): T {
        val elementClass = T::class.java
        if (elementClass::class.java == PLLPositionRepresentationElement::class.java) {
            val element = elementClass.newInstance()
            (element as PLLPositionRepresentationElement).pieceType = pieceType
            (element as PLLPositionRepresentationElement).pieceNumber =
                if (pieceType == PieceType.CORNER) cubeState.cornerPositions[piecePosition]
                else cubeState.edgePositions[piecePosition]
            (element as PLLPositionRepresentationElement).sideRelativePosition = sideRelativePosition
            return element
        } else {
            val element = elementClass.newInstance()
            (element as OLLPositionRepresentationElement).pieceType = pieceType
            (element as OLLPositionRepresentationElement).sideRelativePosition =
                sideRelativePosition

            (element as OLLPositionRepresentationElement).sideRelativeOrientation =
                getSideRelativePositionRepresentation(
                    pieceType,
                    if (pieceType == PieceType.CORNER) cubeState.cornerPositions[piecePosition]
                    else cubeState.edgePositions[piecePosition],
                    piecePosition,
                    if (pieceType == PieceType.CORNER) cubeState.cornerOrientations[piecePosition]
                    else if (cubeState.edgeOrientations[piecePosition]) 1 else 0,
                    context
                )
            return element
        }
    }

    inline fun <reified T> stateToPositionRepresentationYellow(
        context: Context
    ): Array<Array<T>> {

        return arrayOf(
            arrayOf(
                createPositionRepresentationElement(
                    PieceType.CORNER,
                    4,
                    context,
                    Pair(0, 0),
                ),
                createPositionRepresentationElement(
                    PieceType.EDGE,
                    8,
                    context,
                    Pair(0, 1),
                ),
                createPositionRepresentationElement(
                    PieceType.CORNER,
                    7,
                    context,
                    Pair(0, 2),
                )
            ),
            arrayOf(
                createPositionRepresentationElement(
                    PieceType.EDGE,
                    4,
                    context,
                    Pair(1, 0),
                ),
                createPositionRepresentationElement(
                    PieceType.EDGE,
                    7,
                    context,
                    Pair(1, 2),
                )
            ),
            arrayOf(
                createPositionRepresentationElement(
                    PieceType.CORNER,
                    0,
                    context,
                    Pair(2, 0),
                ),
                createPositionRepresentationElement(
                    PieceType.EDGE,
                    0,
                    context,
                    Pair(2, 1),
                ),
                createPositionRepresentationElement(
                    PieceType.CORNER,
                    3,
                    context,
                    Pair(2, 2),
                )
            )
        )

    }

    inline fun <reified T> stateToPositionRepresentationWhite(
        context: Context,
    ): Array<Array<T>> {

        return arrayOf(
            arrayOf(
                createPositionRepresentationElement(
                    PieceType.CORNER,
                    2,
                    context,
                    Pair(0, 0),
                ),
                createPositionRepresentationElement(
                    PieceType.EDGE,
                    6,
                    context,
                    Pair(0, 1),
                ),
                createPositionRepresentationElement(
                    PieceType.CORNER,
                    6,
                    context,
                    Pair(0, 2),
                )
            ),
            arrayOf(
                createPositionRepresentationElement(
                    PieceType.EDGE,
                    2,
                    context,
                    Pair(1, 0),
                ),
                createPositionRepresentationElement(
                    PieceType.EDGE,
                    10,
                    context,
                    Pair(1, 2),
                )
            ),
            arrayOf(
                createPositionRepresentationElement(
                    PieceType.CORNER,
                    1,
                    context,
                    Pair(2, 0),
                ),
                createPositionRepresentationElement(
                    PieceType.EDGE,
                    5,
                    context,
                    Pair(2, 1),
                ),
                createPositionRepresentationElement(
                    PieceType.CORNER,
                    5,
                    context,
                    Pair(2, 2),
                )
            )
        )
    }

    inline fun <reified T> stateToPositionRepresentationGreen(
        context: Context,
    ): Array<Array<T>> {

        return arrayOf(
            arrayOf(
                createPositionRepresentationElement(
                    PieceType.CORNER,
                    3,
                    context,
                    Pair(0, 0),
                ),
                createPositionRepresentationElement(
                    PieceType.EDGE,
                    3,
                    context,
                    Pair(0, 1),
                ),
                createPositionRepresentationElement(
                    PieceType.CORNER,
                    2,
                    context,
                    Pair(0, 2),
                )
            ),
            arrayOf(
                createPositionRepresentationElement(
                    PieceType.EDGE,
                    0,
                    context,
                    Pair(1, 0),
                ),
                createPositionRepresentationElement(
                    PieceType.EDGE,
                    2,
                    context,
                    Pair(1, 2),
                )
            ),
            arrayOf(
                createPositionRepresentationElement(
                    PieceType.CORNER,
                    0,
                    context,
                    Pair(2, 0),
                ),
                createPositionRepresentationElement(
                    PieceType.EDGE,
                    1,
                    context,
                    Pair(2, 1),
                ),
                createPositionRepresentationElement(
                    PieceType.CORNER,
                    1,
                    context,
                    Pair(2, 2),
                )
            )
        )
    }

    inline fun <reified T> stateToPositionRepresentationBlue(
        context: Context,
    ): Array<Array<T>> {
        return arrayOf(
            arrayOf(
                createPositionRepresentationElement(
                    PieceType.CORNER,
                    5,
                    context,
                    Pair(0, 0),
                ),
                createPositionRepresentationElement(
                    PieceType.EDGE,
                    10,
                    context,
                    Pair(0, 1),
                ),
                createPositionRepresentationElement(
                    PieceType.CORNER,
                    6,
                    context,
                    Pair(0, 2),
                )
            ),
            arrayOf(
                createPositionRepresentationElement(
                    PieceType.EDGE,
                    9,
                    context,
                    Pair(1, 0),
                ),
                createPositionRepresentationElement(
                    PieceType.EDGE,
                    11,
                    context,
                    Pair(1, 2),
                )
            ),
            arrayOf(
                createPositionRepresentationElement(
                    PieceType.CORNER,
                    4,
                    context,
                    Pair(2, 0),
                ),
                createPositionRepresentationElement(
                    PieceType.EDGE,
                    8,
                    context,
                    Pair(2, 1),
                ),
                createPositionRepresentationElement(
                    PieceType.CORNER,
                    7,
                    context,
                    Pair(2, 2),
                )
            )
        )
    }

    inline fun <reified T> stateToPositionRepresentationRed(
        context: Context,
    ): Array<Array<T>> {
        return arrayOf(
            arrayOf(
                createPositionRepresentationElement(
                    PieceType.CORNER,
                    1,
                    context,
                    Pair(0, 0),
                ),
                createPositionRepresentationElement(
                    PieceType.EDGE,
                    5,
                    context,
                    Pair(0, 1),
                ),
                createPositionRepresentationElement(
                    PieceType.CORNER,
                    5,
                    context,
                    Pair(0, 2),
                )
            ),
            arrayOf(
                createPositionRepresentationElement(
                    PieceType.EDGE,
                    1,
                    context,
                    Pair(1, 0),
                ),
                createPositionRepresentationElement(
                    PieceType.EDGE,
                    9,
                    context,
                    Pair(1, 2),
                )
            ),
            arrayOf(
                createPositionRepresentationElement(
                    PieceType.CORNER,
                    0,
                    context,
                    Pair(2, 0),
                ),
                createPositionRepresentationElement(
                    PieceType.EDGE,
                    4,
                    context,
                    Pair(2, 1),
                ),
                createPositionRepresentationElement(
                    PieceType.CORNER,
                    4,
                    context,
                    Pair(2, 2),
                )
            )
        )
    }

    inline fun <reified T> stateToPositionRepresentationOrange(
        context: Context
    ): Array<Array<T>> {
        return arrayOf(
            arrayOf(
                createPositionRepresentationElement(
                    PieceType.CORNER,
                    3,
                    context,
                    Pair(0, 0),
                ),
                createPositionRepresentationElement(
                    PieceType.EDGE,
                    7,
                    context,
                    Pair(0, 1),
                ),
                createPositionRepresentationElement(
                    PieceType.CORNER,
                    7,
                    context,
                    Pair(0, 2),
                )
            ),
            arrayOf(
                createPositionRepresentationElement(
                    PieceType.EDGE,
                    3,
                    context,
                    Pair(1, 0),
                ),
                createPositionRepresentationElement(
                    PieceType.EDGE,
                    11,
                    context,
                    Pair(1, 2),
                )
            ),
            arrayOf(
                createPositionRepresentationElement(
                    PieceType.CORNER,
                    2,
                    context,
                    Pair(2, 0),
                ),
                createPositionRepresentationElement(
                    PieceType.EDGE,
                    6,
                    context,
                    Pair(2, 1),
                ),
                createPositionRepresentationElement(
                    PieceType.CORNER,
                    6,
                    context,
                    Pair(2, 2),
                )
            )
        )
    }
}