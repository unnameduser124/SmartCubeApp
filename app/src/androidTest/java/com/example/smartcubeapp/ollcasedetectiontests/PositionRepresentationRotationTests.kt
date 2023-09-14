package com.example.smartcubeapp.ollcasedetectiontests

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
import com.example.smartcubeapp.cube.piece.OLLPositionRepresentationElement
import com.example.smartcubeapp.olldetection.OLLCaseDetection
import org.junit.Before
import org.junit.Test

class PositionRepresentationRotationTests {

    private lateinit var context: Context
    private lateinit var ollCaseDetection: OLLCaseDetection

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        ollCaseDetection = OLLCaseDetection(CubeState.SOLVED_CUBE_STATE, WhiteSide)
    }

    @Test
    fun positionRepresentationRotationTestWhite(){
        ollCaseDetection.changeCubeSide(WhiteSide)
        ollCaseDetection.changeCubeState(CubeState.SOLVED_CUBE_STATE)
        val positionBeforeRotation = ollCaseDetection.transformStateToPositionRepresentation(context)

        val positionAfterRotation = ollCaseDetection.rotatePositionClockwise(positionBeforeRotation)

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
    fun positionRepresentationRotationTestYellow(){
        ollCaseDetection.changeCubeSide(YellowSide)
        ollCaseDetection.changeCubeState(CubeState.SOLVED_CUBE_STATE)
        val positionBeforeRotation = ollCaseDetection.transformStateToPositionRepresentation(context)

        val positionAfterRotation = ollCaseDetection.rotatePositionClockwise(positionBeforeRotation)

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
    fun positionRepresentationRotationTestBlue(){
        ollCaseDetection.changeCubeSide(BlueSide)
        ollCaseDetection.changeCubeState(CubeState.SOLVED_CUBE_STATE)
        val positionBeforeRotation = ollCaseDetection.transformStateToPositionRepresentation(context)

        val positionAfterRotation = ollCaseDetection.rotatePositionClockwise(positionBeforeRotation)

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
    fun positionRepresentationRotationTestGreen(){
        ollCaseDetection.changeCubeSide(GreenSide)
        ollCaseDetection.changeCubeState(CubeState.SOLVED_CUBE_STATE)
        val positionBeforeRotation = ollCaseDetection.transformStateToPositionRepresentation(context)

        val positionAfterRotation = ollCaseDetection.rotatePositionClockwise(positionBeforeRotation)

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
    fun positionRepresentationRotationTestRed(){
        ollCaseDetection.changeCubeSide(RedSide)
        ollCaseDetection.changeCubeState(CubeState.SOLVED_CUBE_STATE)
        val positionBeforeRotation = ollCaseDetection.transformStateToPositionRepresentation(context)

        val positionAfterRotation = ollCaseDetection.rotatePositionClockwise(positionBeforeRotation)

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
    fun positionRepresentationRotationTestOrange(){
        ollCaseDetection.changeCubeSide(OrangeSide)
        ollCaseDetection.changeCubeState(CubeState.SOLVED_CUBE_STATE)
        val positionBeforeRotation = ollCaseDetection.transformStateToPositionRepresentation(context)

        val positionAfterRotation = ollCaseDetection.rotatePositionClockwise(positionBeforeRotation)

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