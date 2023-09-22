package com.example.smartcubeapp.casedetection

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.example.smartcubeapp.cube.BlueSide
import com.example.smartcubeapp.cube.CubeState
import com.example.smartcubeapp.cube.GreenSide
import com.example.smartcubeapp.cube.OrangeSide
import com.example.smartcubeapp.cube.RedSide
import com.example.smartcubeapp.cube.WhiteSide
import com.example.smartcubeapp.cube.YellowSide
import com.example.smartcubeapp.cube.piece.Orientation
import com.example.smartcubeapp.cube.piece.PieceType
import com.example.smartcubeapp.casedetection.olldetection.OLLPositionRepresentationElement
import org.junit.Before
import org.junit.Test

class PositionRepresentationRotationTests {

    private lateinit var context: Context
    private lateinit var positionTransformer: PositionRepresentationTransformer<OLLPositionRepresentationElement>

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        positionTransformer = PositionRepresentationTransformer(
            CubeState.SOLVED_CUBE_STATE,
            WhiteSide
        )
    }

    @Test
    fun positionRepresentationRotationTestWhite() {
        positionTransformer.changeCubeSide(WhiteSide)
        positionTransformer.changeCubeState(CubeState.SOLVED_CUBE_STATE)
        val positionBeforeRotation: Array<Array<OLLPositionRepresentationElement>> =
            positionTransformer.transformStateToPositionRepresentation(context)

        val positionAfterRotation =
            PositionRepresentationTransformer<OLLPositionRepresentationElement>(
                CubeState.SOLVED_CUBE_STATE,
                positionTransformer.cubeSide
            ).rotatePositionClockwise(positionBeforeRotation)

        val expectedPosition = arrayOf(
            arrayOf(
                OLLPositionRepresentationElement(PieceType.CORNER, Orientation.Correct, Pair(0, 0)),
                OLLPositionRepresentationElement(PieceType.EDGE, Orientation.Correct, Pair(0, 1)),
                OLLPositionRepresentationElement(PieceType.CORNER, Orientation.Correct, Pair(0, 2))
            ),
            arrayOf(
                OLLPositionRepresentationElement(PieceType.EDGE, Orientation.Correct, Pair(1, 0)),
                OLLPositionRepresentationElement(PieceType.EDGE, Orientation.Correct, Pair(1, 2))
            ),
            arrayOf(
                OLLPositionRepresentationElement(PieceType.CORNER, Orientation.Correct, Pair(2, 0)),
                OLLPositionRepresentationElement(PieceType.EDGE, Orientation.Correct, Pair(2, 1)),
                OLLPositionRepresentationElement(PieceType.CORNER, Orientation.Correct, Pair(2, 2))
            )
        )

        assert(positionAfterRotation.contentDeepEquals(expectedPosition))
    }

    @Test
    fun positionRepresentationRotationTestYellow() {
        positionTransformer.changeCubeSide(YellowSide)
        positionTransformer.changeCubeState(CubeState.SOLVED_CUBE_STATE)
        val positionBeforeRotation: Array<Array<OLLPositionRepresentationElement>> =
            positionTransformer.transformStateToPositionRepresentation(context)

        val positionAfterRotation =
            PositionRepresentationTransformer<OLLPositionRepresentationElement>(
                CubeState.SOLVED_CUBE_STATE,
                positionTransformer.cubeSide
            ).rotatePositionClockwise(positionBeforeRotation)

        val expectedPosition = arrayOf(
            arrayOf(
                OLLPositionRepresentationElement(PieceType.CORNER, Orientation.Correct, Pair(0, 0)),
                OLLPositionRepresentationElement(PieceType.EDGE, Orientation.Correct, Pair(0, 1)),
                OLLPositionRepresentationElement(PieceType.CORNER, Orientation.Correct, Pair(0, 2))
            ),
            arrayOf(
                OLLPositionRepresentationElement(PieceType.EDGE, Orientation.Correct, Pair(1, 0)),
                OLLPositionRepresentationElement(PieceType.EDGE, Orientation.Correct, Pair(1, 2))
            ),
            arrayOf(
                OLLPositionRepresentationElement(PieceType.CORNER, Orientation.Correct, Pair(2, 0)),
                OLLPositionRepresentationElement(PieceType.EDGE, Orientation.Correct, Pair(2, 1)),
                OLLPositionRepresentationElement(PieceType.CORNER, Orientation.Correct, Pair(2, 2))
            )
        )

        assert(positionAfterRotation.contentDeepEquals(expectedPosition))
    }

    @Test
    fun positionRepresentationRotationTestBlue() {
        positionTransformer.changeCubeSide(BlueSide)
        positionTransformer.changeCubeState(CubeState.SOLVED_CUBE_STATE)
        val positionBeforeRotation: Array<Array<OLLPositionRepresentationElement>> =
            positionTransformer.transformStateToPositionRepresentation(context)

        val positionAfterRotation =
            PositionRepresentationTransformer<OLLPositionRepresentationElement>(
                CubeState.SOLVED_CUBE_STATE,
                positionTransformer.cubeSide
            ).rotatePositionClockwise(positionBeforeRotation)

        val expectedPosition = arrayOf(
            arrayOf(
                OLLPositionRepresentationElement(PieceType.CORNER, Orientation.Correct, Pair(0, 0)),
                OLLPositionRepresentationElement(PieceType.EDGE, Orientation.Correct, Pair(0, 1)),
                OLLPositionRepresentationElement(PieceType.CORNER, Orientation.Correct, Pair(0, 2))
            ),
            arrayOf(
                OLLPositionRepresentationElement(PieceType.EDGE, Orientation.Correct, Pair(1, 0)),
                OLLPositionRepresentationElement(PieceType.EDGE, Orientation.Correct, Pair(1, 2))
            ),
            arrayOf(
                OLLPositionRepresentationElement(PieceType.CORNER, Orientation.Correct, Pair(2, 0)),
                OLLPositionRepresentationElement(PieceType.EDGE, Orientation.Correct, Pair(2, 1)),
                OLLPositionRepresentationElement(PieceType.CORNER, Orientation.Correct, Pair(2, 2))
            )
        )

        assert(positionAfterRotation.contentDeepEquals(expectedPosition))
    }

    @Test
    fun positionRepresentationRotationTestGreen() {
        positionTransformer.changeCubeSide(GreenSide)
        positionTransformer.changeCubeState(CubeState.SOLVED_CUBE_STATE)
        val positionBeforeRotation: Array<Array<OLLPositionRepresentationElement>> =
            positionTransformer.transformStateToPositionRepresentation(context)

        val positionAfterRotation =
            PositionRepresentationTransformer<OLLPositionRepresentationElement>(
                CubeState.SOLVED_CUBE_STATE,
                positionTransformer.cubeSide
            ).rotatePositionClockwise(positionBeforeRotation)

        val expectedPosition = arrayOf(
            arrayOf(
                OLLPositionRepresentationElement(PieceType.CORNER, Orientation.Correct, Pair(0, 0)),
                OLLPositionRepresentationElement(PieceType.EDGE, Orientation.Correct, Pair(0, 1)),
                OLLPositionRepresentationElement(PieceType.CORNER, Orientation.Correct, Pair(0, 2))
            ),
            arrayOf(
                OLLPositionRepresentationElement(PieceType.EDGE, Orientation.Correct, Pair(1, 0)),
                OLLPositionRepresentationElement(PieceType.EDGE, Orientation.Correct, Pair(1, 2))
            ),
            arrayOf(
                OLLPositionRepresentationElement(PieceType.CORNER, Orientation.Correct, Pair(2, 0)),
                OLLPositionRepresentationElement(PieceType.EDGE, Orientation.Correct, Pair(2, 1)),
                OLLPositionRepresentationElement(PieceType.CORNER, Orientation.Correct, Pair(2, 2))
            )

        )

        assert(positionAfterRotation.contentDeepEquals(expectedPosition))
    }

    @Test
    fun positionRepresentationRotationTestRed() {
        positionTransformer.changeCubeSide(RedSide)
        positionTransformer.changeCubeState(CubeState.SOLVED_CUBE_STATE)
        val positionBeforeRotation: Array<Array<OLLPositionRepresentationElement>> =
            positionTransformer.transformStateToPositionRepresentation(context)

        val positionAfterRotation =
            PositionRepresentationTransformer<OLLPositionRepresentationElement>(
                CubeState.SOLVED_CUBE_STATE,
                positionTransformer.cubeSide
            ).rotatePositionClockwise(positionBeforeRotation)

        val expectedPosition = arrayOf(
            arrayOf(
                OLLPositionRepresentationElement(PieceType.CORNER, Orientation.Correct, Pair(0, 0)),
                OLLPositionRepresentationElement(PieceType.EDGE, Orientation.Correct, Pair(0, 1)),
                OLLPositionRepresentationElement(PieceType.CORNER, Orientation.Correct, Pair(0, 2))
            ),
            arrayOf(
                OLLPositionRepresentationElement(PieceType.EDGE, Orientation.Correct, Pair(1, 0)),
                OLLPositionRepresentationElement(PieceType.EDGE, Orientation.Correct, Pair(1, 2))
            ),
            arrayOf(
                OLLPositionRepresentationElement(PieceType.CORNER, Orientation.Correct, Pair(2, 0)),
                OLLPositionRepresentationElement(PieceType.EDGE, Orientation.Correct, Pair(2, 1)),
                OLLPositionRepresentationElement(PieceType.CORNER, Orientation.Correct, Pair(2, 2))
            )
        )


        assert(positionAfterRotation.contentDeepEquals(expectedPosition))
    }

    @Test
    fun positionRepresentationRotationTestOrange() {
        positionTransformer.changeCubeSide(OrangeSide)
        positionTransformer.changeCubeState(CubeState.SOLVED_CUBE_STATE)
        val positionBeforeRotation: Array<Array<OLLPositionRepresentationElement>> =
            positionTransformer.transformStateToPositionRepresentation(context)

        val positionAfterRotation =
            PositionRepresentationTransformer<OLLPositionRepresentationElement>(
                CubeState.SOLVED_CUBE_STATE,
                positionTransformer.cubeSide
            ).rotatePositionClockwise(positionBeforeRotation)

        val expectedPosition = arrayOf(
            arrayOf(
                OLLPositionRepresentationElement(PieceType.CORNER, Orientation.Correct, Pair(0, 0)),
                OLLPositionRepresentationElement(PieceType.EDGE, Orientation.Correct, Pair(0, 1)),
                OLLPositionRepresentationElement(PieceType.CORNER, Orientation.Correct, Pair(0, 2))
            ),
            arrayOf(
                OLLPositionRepresentationElement(PieceType.EDGE, Orientation.Correct, Pair(1, 0)),
                OLLPositionRepresentationElement(PieceType.EDGE, Orientation.Correct, Pair(1, 2))
            ),
            arrayOf(
                OLLPositionRepresentationElement(PieceType.CORNER, Orientation.Correct, Pair(2, 0)),
                OLLPositionRepresentationElement(PieceType.EDGE, Orientation.Correct, Pair(2, 1)),
                OLLPositionRepresentationElement(PieceType.CORNER, Orientation.Correct, Pair(2, 2))
            )
        )

        assert(positionAfterRotation.contentDeepEquals(expectedPosition))
    }
}