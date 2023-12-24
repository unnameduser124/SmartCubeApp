package com.example.tests.correctnesstests.detectiontests.casedetection

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.example.cube_cube.cube.BlueSide
import com.example.cube_cube.cube.CubeState
import com.example.cube_cube.cube.GreenSide
import com.example.cube_cube.cube.OrangeSide
import com.example.cube_cube.cube.RedSide
import com.example.cube_cube.cube.WhiteSide
import com.example.cube_cube.cube.YellowSide
import com.example.cube_cube.cube.piece.Orientation
import com.example.cube_cube.cube.piece.PieceType
import com.example.cube_detection.casedetection.olldetection.OLLElementOrientation
import org.junit.Before
import org.junit.Test

class CubeStateToPositionRepresentationTests {

    private lateinit var positionTransformer: com.example.cube_detection.casedetection.PositionRepresentationTransformer
    private lateinit var context: Context

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        positionTransformer =
            com.example.cube_detection.casedetection.PositionRepresentationTransformer(
                CubeState.SOLVED_CUBE_STATE,
                WhiteSide
            )
    }

    @Test
    fun cubeStateToPositionRepresentationTestWhite() {
        positionTransformer.changeCubeSide(WhiteSide)
        val positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)

        val expectedRepresentation = arrayOf(
            arrayOf(
                com.example.cube_detection.casedetection.olldetection.OLLElementOrientation(
                    PieceType.CORNER,
                    Orientation.Correct,
                    Pair(0, 0)
                ),
                com.example.cube_detection.casedetection.olldetection.OLLElementOrientation(
                    PieceType.EDGE,
                    Orientation.Correct,
                    Pair(0, 1)
                ),
                com.example.cube_detection.casedetection.olldetection.OLLElementOrientation(
                    PieceType.CORNER,
                    Orientation.Correct,
                    Pair(0, 2)
                )
            ),
            arrayOf(
                com.example.cube_detection.casedetection.olldetection.OLLElementOrientation(
                    PieceType.EDGE,
                    Orientation.Correct,
                    Pair(1, 0)
                ),
                com.example.cube_detection.casedetection.olldetection.OLLElementOrientation(
                    PieceType.EDGE,
                    Orientation.Correct,
                    Pair(1, 2)
                )
            ),
            arrayOf(
                com.example.cube_detection.casedetection.olldetection.OLLElementOrientation(
                    PieceType.CORNER,
                    Orientation.Correct,
                    Pair(2, 0)
                ),
                com.example.cube_detection.casedetection.olldetection.OLLElementOrientation(
                    PieceType.EDGE,
                    Orientation.Correct,
                    Pair(2, 1)
                ),
                com.example.cube_detection.casedetection.olldetection.OLLElementOrientation(
                    PieceType.CORNER,
                    Orientation.Correct,
                    Pair(2, 2)
                )
            )
        )

        assert(positionRepresentation.contentDeepEquals(expectedRepresentation))
    }

    @Test
    fun cubeStateToPositionRepresentationTestYellow(){
        positionTransformer.changeCubeSide(YellowSide)
        val positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)

        val expectedRepresentation = arrayOf(
            arrayOf(
                com.example.cube_detection.casedetection.olldetection.OLLElementOrientation(
                    PieceType.CORNER,
                    Orientation.Correct,
                    Pair(0, 0)
                ),
                com.example.cube_detection.casedetection.olldetection.OLLElementOrientation(
                    PieceType.EDGE,
                    Orientation.Correct,
                    Pair(0, 1)
                ),
                com.example.cube_detection.casedetection.olldetection.OLLElementOrientation(
                    PieceType.CORNER,
                    Orientation.Correct,
                    Pair(0, 2)
                )
            ),
            arrayOf(
                com.example.cube_detection.casedetection.olldetection.OLLElementOrientation(
                    PieceType.EDGE,
                    Orientation.Correct,
                    Pair(1, 0)
                ),
                com.example.cube_detection.casedetection.olldetection.OLLElementOrientation(
                    PieceType.EDGE,
                    Orientation.Correct,
                    Pair(1, 2)
                )
            ),
            arrayOf(
                com.example.cube_detection.casedetection.olldetection.OLLElementOrientation(
                    PieceType.CORNER,
                    Orientation.Correct,
                    Pair(2, 0)
                ),
                com.example.cube_detection.casedetection.olldetection.OLLElementOrientation(
                    PieceType.EDGE,
                    Orientation.Correct,
                    Pair(2, 1)
                ),
                com.example.cube_detection.casedetection.olldetection.OLLElementOrientation(
                    PieceType.CORNER,
                    Orientation.Correct,
                    Pair(2, 2)
                )
            )
        )

        assert(positionRepresentation.contentDeepEquals(expectedRepresentation))
    }

    @Test
    fun cubeStateToPositionRepresentationTestBlue(){
        positionTransformer.changeCubeSide(BlueSide)
        val positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)

        val expectedRepresentation = arrayOf(
            arrayOf(
                com.example.cube_detection.casedetection.olldetection.OLLElementOrientation(
                    PieceType.CORNER,
                    Orientation.Correct,
                    Pair(0, 0)
                ),
                com.example.cube_detection.casedetection.olldetection.OLLElementOrientation(
                    PieceType.EDGE,
                    Orientation.Correct,
                    Pair(0, 1)
                ),
                com.example.cube_detection.casedetection.olldetection.OLLElementOrientation(
                    PieceType.CORNER,
                    Orientation.Correct,
                    Pair(0, 2)
                )
            ),
            arrayOf(
                com.example.cube_detection.casedetection.olldetection.OLLElementOrientation(
                    PieceType.EDGE,
                    Orientation.Correct,
                    Pair(1, 0)
                ),
                com.example.cube_detection.casedetection.olldetection.OLLElementOrientation(
                    PieceType.EDGE,
                    Orientation.Correct,
                    Pair(1, 2)
                )
            ),
            arrayOf(
                com.example.cube_detection.casedetection.olldetection.OLLElementOrientation(
                    PieceType.CORNER,
                    Orientation.Correct,
                    Pair(2, 0)
                ),
                com.example.cube_detection.casedetection.olldetection.OLLElementOrientation(
                    PieceType.EDGE,
                    Orientation.Correct,
                    Pair(2, 1)
                ),
                com.example.cube_detection.casedetection.olldetection.OLLElementOrientation(
                    PieceType.CORNER,
                    Orientation.Correct,
                    Pair(2, 2)
                )
            )
        )

        assert(positionRepresentation.contentDeepEquals(expectedRepresentation))
    }

    @Test
    fun cubeStateToPositionRepresentationTestGreen(){
        positionTransformer.changeCubeSide(GreenSide)
        val positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)

        val expectedRepresentation = arrayOf(
            arrayOf(
                com.example.cube_detection.casedetection.olldetection.OLLElementOrientation(
                    PieceType.CORNER,
                    Orientation.Correct,
                    Pair(0, 0)
                ),
                com.example.cube_detection.casedetection.olldetection.OLLElementOrientation(
                    PieceType.EDGE,
                    Orientation.Correct,
                    Pair(0, 1)
                ),
                com.example.cube_detection.casedetection.olldetection.OLLElementOrientation(
                    PieceType.CORNER,
                    Orientation.Correct,
                    Pair(0, 2)
                )
            ),
            arrayOf(
                com.example.cube_detection.casedetection.olldetection.OLLElementOrientation(
                    PieceType.EDGE,
                    Orientation.Correct,
                    Pair(1, 0)
                ),
                com.example.cube_detection.casedetection.olldetection.OLLElementOrientation(
                    PieceType.EDGE,
                    Orientation.Correct,
                    Pair(1, 2)
                )
            ),
            arrayOf(
                com.example.cube_detection.casedetection.olldetection.OLLElementOrientation(
                    PieceType.CORNER,
                    Orientation.Correct,
                    Pair(2, 0)
                ),
                com.example.cube_detection.casedetection.olldetection.OLLElementOrientation(
                    PieceType.EDGE,
                    Orientation.Correct,
                    Pair(2, 1)
                ),
                com.example.cube_detection.casedetection.olldetection.OLLElementOrientation(
                    PieceType.CORNER,
                    Orientation.Correct,
                    Pair(2, 2)
                )
            )
        )

        assert(positionRepresentation.contentDeepEquals(expectedRepresentation))
    }

    @Test
    fun cubeStateToPositionRepresentationTestRed(){
        positionTransformer.changeCubeSide(RedSide)
        val positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)

        val expectedRepresentation = arrayOf(
            arrayOf(
                com.example.cube_detection.casedetection.olldetection.OLLElementOrientation(
                    PieceType.CORNER,
                    Orientation.Correct,
                    Pair(0, 0)
                ),
                com.example.cube_detection.casedetection.olldetection.OLLElementOrientation(
                    PieceType.EDGE,
                    Orientation.Correct,
                    Pair(0, 1)
                ),
                com.example.cube_detection.casedetection.olldetection.OLLElementOrientation(
                    PieceType.CORNER,
                    Orientation.Correct,
                    Pair(0, 2)
                )
            ),
            arrayOf(
                com.example.cube_detection.casedetection.olldetection.OLLElementOrientation(
                    PieceType.EDGE,
                    Orientation.Correct,
                    Pair(1, 0)
                ),
                com.example.cube_detection.casedetection.olldetection.OLLElementOrientation(
                    PieceType.EDGE,
                    Orientation.Correct,
                    Pair(1, 2)
                )
            ),
            arrayOf(
                com.example.cube_detection.casedetection.olldetection.OLLElementOrientation(
                    PieceType.CORNER,
                    Orientation.Correct,
                    Pair(2, 0)
                ),
                com.example.cube_detection.casedetection.olldetection.OLLElementOrientation(
                    PieceType.EDGE,
                    Orientation.Correct,
                    Pair(2, 1)
                ),
                com.example.cube_detection.casedetection.olldetection.OLLElementOrientation(
                    PieceType.CORNER,
                    Orientation.Correct,
                    Pair(2, 2)
                )
            )
        )

        assert(positionRepresentation.contentDeepEquals(expectedRepresentation))
    }

    @Test
    fun cubeStateToPositionRepresentationTestOrange(){
        positionTransformer.changeCubeSide(OrangeSide)
        val positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)

        val expectedRepresentation = arrayOf(
            arrayOf(
                com.example.cube_detection.casedetection.olldetection.OLLElementOrientation(
                    PieceType.CORNER,
                    Orientation.Correct,
                    Pair(0, 0)
                ),
                com.example.cube_detection.casedetection.olldetection.OLLElementOrientation(
                    PieceType.EDGE,
                    Orientation.Correct,
                    Pair(0, 1)
                ),
                com.example.cube_detection.casedetection.olldetection.OLLElementOrientation(
                    PieceType.CORNER,
                    Orientation.Correct,
                    Pair(0, 2)
                )
            ),
            arrayOf(
                com.example.cube_detection.casedetection.olldetection.OLLElementOrientation(
                    PieceType.EDGE,
                    Orientation.Correct,
                    Pair(1, 0)
                ),
                com.example.cube_detection.casedetection.olldetection.OLLElementOrientation(
                    PieceType.EDGE,
                    Orientation.Correct,
                    Pair(1, 2)
                )
            ),
            arrayOf(
                com.example.cube_detection.casedetection.olldetection.OLLElementOrientation(
                    PieceType.CORNER,
                    Orientation.Correct,
                    Pair(2, 0)
                ),
                com.example.cube_detection.casedetection.olldetection.OLLElementOrientation(
                    PieceType.EDGE,
                    Orientation.Correct,
                    Pair(2, 1)
                ),
                com.example.cube_detection.casedetection.olldetection.OLLElementOrientation(
                    PieceType.CORNER,
                    Orientation.Correct,
                    Pair(2, 2)
                )
            )
        )

        assert(positionRepresentation.contentDeepEquals(expectedRepresentation))
    }

}