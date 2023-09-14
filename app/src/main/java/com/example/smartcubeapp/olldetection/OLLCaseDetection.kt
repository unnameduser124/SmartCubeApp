package com.example.smartcubeapp.olldetection

import android.content.Context
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
import com.example.smartcubeapp.cube.piece.PositionRepresentationElement
import com.example.smartcubeapp.elementdatabase.ElementDatabaseConstants
import com.example.smartcubeapp.elementdatabase.casedetectiondatabase.CaseElementOrientationDBService

class OLLCaseDetection(var cubeState: CubeState, private var cubeSide: CubeSide) {

    fun detectOLLCase(): PredefinedOLLCase? {
        TODO("Not implemented yet")
    }

    fun transformStateToPositionRepresentation(context: Context): Array<Array<PositionRepresentationElement>> {
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

    fun rotatePositionClockwise(position: Array<Array<PositionRepresentationElement>>): Array<Array<PositionRepresentationElement>> {
        val rotatedPosition: Array<Array<PositionRepresentationElement>> = arrayOf(
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, 0),
                PositionRepresentationElement(PieceType.CORNER, 0),
                PositionRepresentationElement(PieceType.CORNER, 0)
            ),
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, 0),
                PositionRepresentationElement(PieceType.CORNER, 0)
            ),
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, 0),
                PositionRepresentationElement(PieceType.CORNER, 0),
                PositionRepresentationElement(PieceType.CORNER, 0)
            )
        )

        //corners
        rotatedPosition[0][0] = position[2][0]
        rotatedPosition[0][0].sideRelativePosition = Pair(0, 0)
        rotatedPosition[0][2] = position[0][0]
        rotatedPosition[0][2].sideRelativePosition = Pair(0, 2)
        rotatedPosition[2][0] = position[2][2]
        rotatedPosition[2][0].sideRelativePosition = Pair(2, 0)
        rotatedPosition[2][2] = position[0][2]
        rotatedPosition[2][2].sideRelativePosition = Pair(2, 2)

        //edges
        rotatedPosition[0][1] = position[1][0]
        rotatedPosition[0][1].sideRelativePosition = Pair(0, 1)
        rotatedPosition[1][1] = position[0][1]
        rotatedPosition[1][1].sideRelativePosition = Pair(1, 2)
        rotatedPosition[2][1] = position[1][1]
        rotatedPosition[2][1].sideRelativePosition = Pair(2, 1)
        rotatedPosition[1][0] = position[2][1]
        rotatedPosition[1][0].sideRelativePosition = Pair(1, 0)

        return rotatedPosition
    }

    fun matchCase(case: OLLCase): PredefinedOLLCase? {
        TODO("Not implemented yet")
    }

    fun positionRepresentationToOLLCase(position: Array<Array<PositionRepresentationElement>>): CustomOLLCase {
        val elements = mutableListOf<PositionRepresentationElement>()
        for (row in position) {
            for (element in row) {
                if (element.sideRelativeOrientation != Orientation.Correct) {
                    elements.add(element)
                }
            }
        }
        return CustomOLLCase(elements)
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
        val fromDatabase = db.getElementOrientation(element)
        return if (fromDatabase != null) {
            fromDatabase.sideRelativeOrientation!!
        } else {
            Orientation.Incorrect
        }
    }

    fun createPositionRepresentationElement(
        pieceType: PieceType,
        piecePosition: Int,
        context: Context,
        sideRelativePosition: Pair<Int, Int>
    ): PositionRepresentationElement {
        return if (pieceType == PieceType.CORNER)
            PositionRepresentationElement(
                pieceType = pieceType,
                pieceNumber = cubeState.cornerPositions[piecePosition],
                sideRelativePosition = sideRelativePosition,
                sideRelativeOrientation = getSideRelativePositionRepresentation(
                    pieceType,
                    cubeState.cornerPositions[piecePosition],
                    piecePosition,
                    cubeState.cornerOrientations[piecePosition],
                    context
                )
            )
        else
            PositionRepresentationElement(
                pieceType = pieceType,
                pieceNumber = cubeState.edgePositions[piecePosition],
                sideRelativePosition = sideRelativePosition,
                sideRelativeOrientation = getSideRelativePositionRepresentation(
                    pieceType,
                    cubeState.edgePositions[piecePosition],
                    piecePosition,
                    if (cubeState.edgeOrientations[piecePosition]) 1 else 0,
                    context
                )
            )

    }

    private fun stateToPositionRepresentationYellow(context: Context): Array<Array<PositionRepresentationElement>> {

        return arrayOf(
            arrayOf(
                createPositionRepresentationElement(
                    PieceType.CORNER,
                    4,
                    context,
                    Pair(0, 0)
                ),
                createPositionRepresentationElement(
                    PieceType.EDGE,
                    8,
                    context,
                    Pair(0, 1)
                ),
                createPositionRepresentationElement(
                    PieceType.CORNER,
                    7,
                    context,
                    Pair(0, 2)
                )
            ),
            arrayOf(
                createPositionRepresentationElement(
                    PieceType.EDGE,
                    4,
                    context,
                    Pair(1, 0)
                ),
                createPositionRepresentationElement(
                    PieceType.EDGE,
                    7,
                    context,
                    Pair(1, 2)
                )
            ),
            arrayOf(
                createPositionRepresentationElement(
                    PieceType.CORNER,
                    0,
                    context,
                    Pair(2, 0)
                ),
                createPositionRepresentationElement(
                    PieceType.EDGE,
                    0,
                    context,
                    Pair(2, 1)
                ),
                createPositionRepresentationElement(
                    PieceType.CORNER,
                    3,
                    context,
                    Pair(2, 2)
                )
            )
        )

    }

    private fun stateToPositionRepresentationWhite(context: Context): Array<Array<PositionRepresentationElement>> {
        return arrayOf(
            arrayOf(
                createPositionRepresentationElement(
                    PieceType.CORNER,
                    2,
                    context,
                    Pair(0, 0)
                ),
                createPositionRepresentationElement(
                    PieceType.EDGE,
                    6,
                    context,
                    Pair(0, 1)
                ),
                createPositionRepresentationElement(
                    PieceType.CORNER,
                    6,
                    context,
                    Pair(0, 2)
                )
            ),
            arrayOf(
                createPositionRepresentationElement(
                    PieceType.EDGE,
                    2,
                    context,
                    Pair(1, 0)
                ),
                createPositionRepresentationElement(
                    PieceType.EDGE,
                    10,
                    context,
                    Pair(1, 2)
                )
            ),
            arrayOf(
                createPositionRepresentationElement(
                    PieceType.CORNER,
                    1,
                    context,
                    Pair(2, 0)
                ),
                createPositionRepresentationElement(
                    PieceType.EDGE,
                    5,
                    context,
                    Pair(2, 1)
                ),
                createPositionRepresentationElement(
                    PieceType.CORNER,
                    5,
                    context,
                    Pair(2, 2)
                )
            )
        )
    }

    private fun stateToPositionRepresentationGreen(context: Context): Array<Array<PositionRepresentationElement>> {
        return arrayOf(
            arrayOf(
                createPositionRepresentationElement(
                    PieceType.CORNER,
                    3,
                    context,
                    Pair(0, 0)
                ),
                createPositionRepresentationElement(
                    PieceType.EDGE,
                    3,
                    context,
                    Pair(0, 1)
                ),
                createPositionRepresentationElement(
                    PieceType.CORNER,
                    2,
                    context,
                    Pair(0, 2)
                )
            ),
            arrayOf(
                createPositionRepresentationElement(
                    PieceType.EDGE,
                    0,
                    context,
                    Pair(1, 0)
                ),
                createPositionRepresentationElement(
                    PieceType.EDGE,
                    2,
                    context,
                    Pair(1, 2)
                )
            ),
            arrayOf(
                createPositionRepresentationElement(
                    PieceType.CORNER,
                    0,
                    context,
                    Pair(2, 0)
                ),
                createPositionRepresentationElement(
                    PieceType.EDGE,
                    1,
                    context,
                    Pair(2, 1)
                ),
                createPositionRepresentationElement(
                    PieceType.CORNER,
                    1,
                    context,
                    Pair(2, 2)
                )
            )
        )
    }

    private fun stateToPositionRepresentationBlue(context: Context): Array<Array<PositionRepresentationElement>> {
        return arrayOf(
            arrayOf(
                createPositionRepresentationElement(
                    PieceType.CORNER,
                    5,
                    context,
                    Pair(0, 0)
                ),
                createPositionRepresentationElement(
                    PieceType.EDGE,
                    10,
                    context,
                    Pair(0, 1)
                ),
                createPositionRepresentationElement(
                    PieceType.CORNER,
                    6,
                    context,
                    Pair(0, 2)
                )
            ),
            arrayOf(
                createPositionRepresentationElement(
                    PieceType.EDGE,
                    9,
                    context,
                    Pair(1, 0)
                ),
                createPositionRepresentationElement(
                    PieceType.EDGE,
                    11,
                    context,
                    Pair(1, 2)
                )
            ),
            arrayOf(
                createPositionRepresentationElement(
                    PieceType.CORNER,
                    4,
                    context,
                    Pair(2, 0)
                ),
                createPositionRepresentationElement(
                    PieceType.EDGE,
                    8,
                    context,
                    Pair(2, 1)
                ),
                createPositionRepresentationElement(
                    PieceType.CORNER,
                    7,
                    context,
                    Pair(2, 2)
                )
            )
        )
    }

    private fun stateToPositionRepresentationRed(context: Context): Array<Array<PositionRepresentationElement>> {
        return arrayOf(
            arrayOf(
                createPositionRepresentationElement(
                    PieceType.CORNER,
                    1,
                    context,
                    Pair(0, 0)
                ),
                createPositionRepresentationElement(
                    PieceType.EDGE,
                    5,
                    context,
                    Pair(0, 1)
                ),
                createPositionRepresentationElement(
                    PieceType.CORNER,
                    5,
                    context,
                    Pair(0, 2)
                )
            ),
            arrayOf(
                createPositionRepresentationElement(
                    PieceType.EDGE,
                    1,
                    context,
                    Pair(1, 0)
                ),
                createPositionRepresentationElement(
                    PieceType.EDGE,
                    9,
                    context,
                    Pair(1, 2)
                )
            ),
            arrayOf(
                createPositionRepresentationElement(
                    PieceType.CORNER,
                    0,
                    context,
                    Pair(2, 0)
                ),
                createPositionRepresentationElement(
                    PieceType.EDGE,
                    4,
                    context,
                    Pair(2, 1)
                ),
                createPositionRepresentationElement(
                    PieceType.CORNER,
                    4,
                    context,
                    Pair(2, 2)
                )
            )
        )
    }

    private fun stateToPositionRepresentationOrange(context: Context): Array<Array<PositionRepresentationElement>> {
        return arrayOf(
            arrayOf(
                createPositionRepresentationElement(
                    PieceType.CORNER,
                    3,
                    context,
                    Pair(0, 0)
                ),
                createPositionRepresentationElement(
                    PieceType.EDGE,
                    7,
                    context,
                    Pair(0, 1)
                ),
                createPositionRepresentationElement(
                    PieceType.CORNER,
                    7,
                    context,
                    Pair(0, 2)
                )
            ),
            arrayOf(
                createPositionRepresentationElement(
                    PieceType.EDGE,
                    3,
                    context,
                    Pair(1, 0)
                ),
                createPositionRepresentationElement(
                    PieceType.EDGE,
                    11,
                    context,
                    Pair(1, 2)
                )
            ),
            arrayOf(
                createPositionRepresentationElement(
                    PieceType.CORNER,
                    2,
                    context,
                    Pair(2, 0)
                ),
                createPositionRepresentationElement(
                    PieceType.EDGE,
                    6,
                    context,
                    Pair(2, 1)
                ),
                createPositionRepresentationElement(
                    PieceType.CORNER,
                    6,
                    context,
                    Pair(2, 2)
                )
            )
        )
    }

}