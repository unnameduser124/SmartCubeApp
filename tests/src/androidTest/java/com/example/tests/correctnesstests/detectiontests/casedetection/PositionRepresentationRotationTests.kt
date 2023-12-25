package com.example.tests.correctnesstests.detectiontests.casedetection

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.example.cube_detection.casedetection.PositionRepresentationTransformer
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

class PositionRepresentationRotationTests {

    private lateinit var context: Context
    private lateinit var positionTransformer: com.example.cube_detection.casedetection.PositionRepresentationTransformer

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
    fun positionRepresentationRotationTestWhite() {
        positionTransformer.changeCubeSide(WhiteSide)
        positionTransformer.changeCubeState(CubeState.SOLVED_CUBE_STATE)
        val positionBeforeRotation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)

        val positionAfterRotation =
            positionTransformer.rotatePositionClockwise(positionBeforeRotation)

        val expectedPosition = arrayOf(
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

        assert(positionAfterRotation.contentDeepEquals(expectedPosition))
    }

    @Test
    fun positionRepresentationRotationTestYellow() {
        positionTransformer.changeCubeSide(YellowSide)
        positionTransformer.changeCubeState(CubeState.SOLVED_CUBE_STATE)
        val positionBeforeRotation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)

        val positionAfterRotation =
            positionTransformer.rotatePositionClockwise(positionBeforeRotation)

        val expectedPosition = arrayOf(
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

        assert(positionAfterRotation.contentDeepEquals(expectedPosition))
    }

    @Test
    fun positionRepresentationRotationTestBlue() {
        positionTransformer.changeCubeSide(BlueSide)
        positionTransformer.changeCubeState(CubeState.SOLVED_CUBE_STATE)
        val positionBeforeRotation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)

        val positionAfterRotation =
            positionTransformer.rotatePositionClockwise(positionBeforeRotation)

        val expectedPosition = arrayOf(
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

        assert(positionAfterRotation.contentDeepEquals(expectedPosition))
    }

    @Test
    fun positionRepresentationRotationTestGreen() {
        positionTransformer.changeCubeSide(GreenSide)
        positionTransformer.changeCubeState(CubeState.SOLVED_CUBE_STATE)
        val positionBeforeRotation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)

        val positionAfterRotation =
            positionTransformer.rotatePositionClockwise(positionBeforeRotation)

        val expectedPosition = arrayOf(
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

        assert(positionAfterRotation.contentDeepEquals(expectedPosition))
    }

    @Test
    fun positionRepresentationRotationTestRed() {
        positionTransformer.changeCubeSide(RedSide)
        positionTransformer.changeCubeState(CubeState.SOLVED_CUBE_STATE)
        val positionBeforeRotation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)

        val positionAfterRotation =
            positionTransformer.rotatePositionClockwise(positionBeforeRotation)

        val expectedPosition = arrayOf(
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


        assert(positionAfterRotation.contentDeepEquals(expectedPosition))
    }

    @Test
    fun positionRepresentationRotationTestOrange() {
        positionTransformer.changeCubeSide(OrangeSide)
        positionTransformer.changeCubeState(CubeState.SOLVED_CUBE_STATE)
        val positionBeforeRotation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)

        val positionAfterRotation =
            positionTransformer.rotatePositionClockwise(positionBeforeRotation)

        val expectedPosition = arrayOf(
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

        assert(positionAfterRotation.contentDeepEquals(expectedPosition))
    }
}