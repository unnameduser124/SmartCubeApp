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
import com.example.smartcubeapp.cube.piece.PositionRepresentationElement
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
        val positionBeforeRotation = ollCaseDetection.transformStateToPositionRepresentation()

        ollCaseDetection.rotatePositionClockwise(positionBeforeRotation)

        val positionAfterRotation = ollCaseDetection.transformStateToPositionRepresentation()

        val expectedPosition = arrayOf(
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, 1, Orientation.Correct, Pair(0, 0)),
                PositionRepresentationElement(PieceType.EDGE, 2, Orientation.Correct, Pair(0, 1)),
                PositionRepresentationElement(PieceType.CORNER, 2, Orientation.Correct, Pair(0, 2))
            ),
            arrayOf(
                PositionRepresentationElement(PieceType.EDGE, 5, Orientation.Correct, Pair(1, 0)),
                PositionRepresentationElement(PieceType.EDGE, 6, Orientation.Correct, Pair(1, 2))
            ),
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, 5, Orientation.Correct, Pair(2, 0)),
                PositionRepresentationElement(PieceType.EDGE, 10, Orientation.Correct, Pair(2, 1)),
                PositionRepresentationElement(PieceType.CORNER, 6, Orientation.Correct, Pair(2, 2))
            )
        )

        assert(positionAfterRotation.contentDeepEquals(expectedPosition))
    }

    @Test
    fun positionRepresentationRotationTestYellow(){
        ollCaseDetection.changeCubeSide(YellowSide)
        ollCaseDetection.changeCubeState(CubeState.SOLVED_CUBE_STATE)
        val positionBeforeRotation = ollCaseDetection.transformStateToPositionRepresentation()

        ollCaseDetection.rotatePositionClockwise(positionBeforeRotation)

        val positionAfterRotation = ollCaseDetection.transformStateToPositionRepresentation()

        val expectedPosition = arrayOf(
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, 0, Orientation.Correct, Pair(0, 0)),
                PositionRepresentationElement(PieceType.EDGE, 4, Orientation.Correct, Pair(0, 1)),
                PositionRepresentationElement(PieceType.CORNER, 4, Orientation.Correct, Pair(0, 2))
            ),
            arrayOf(
                PositionRepresentationElement(PieceType.EDGE, 0, Orientation.Correct, Pair(1, 0)),
                PositionRepresentationElement(PieceType.EDGE, 8, Orientation.Correct, Pair(1, 2))
            ),
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, 3, Orientation.Correct, Pair(2, 0)),
                PositionRepresentationElement(PieceType.EDGE, 7, Orientation.Correct, Pair(2, 1)),
                PositionRepresentationElement(PieceType.CORNER, 7, Orientation.Correct, Pair(2, 2))
            )
        )

        assert(positionAfterRotation.contentDeepEquals(expectedPosition))
    }

    @Test
    fun positionRepresentationRotationTestBlue(){
        ollCaseDetection.changeCubeSide(BlueSide)
        ollCaseDetection.changeCubeState(CubeState.SOLVED_CUBE_STATE)
        val positionBeforeRotation = ollCaseDetection.transformStateToPositionRepresentation()

        ollCaseDetection.rotatePositionClockwise(positionBeforeRotation)

        val positionAfterRotation = ollCaseDetection.transformStateToPositionRepresentation()

        val expectedPosition = arrayOf(
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, 4, Orientation.Correct, Pair(0, 0)),
                PositionRepresentationElement(PieceType.EDGE, 9, Orientation.Correct, Pair(0, 1)),
                PositionRepresentationElement(PieceType.CORNER, 5, Orientation.Correct, Pair(0, 2))
            ),
            arrayOf(
                PositionRepresentationElement(PieceType.EDGE, 8, Orientation.Correct, Pair(1, 0)),
                PositionRepresentationElement(PieceType.EDGE, 10, Orientation.Correct, Pair(1, 2))
            ),
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, 7, Orientation.Correct, Pair(2, 0)),
                PositionRepresentationElement(PieceType.EDGE, 11, Orientation.Correct, Pair(2, 1)),
                PositionRepresentationElement(PieceType.CORNER, 6, Orientation.Correct, Pair(2, 2))
            )
        )

        assert(positionAfterRotation.contentDeepEquals(expectedPosition))
    }

    @Test
    fun positionRepresentationRotationTestGreen(){
        ollCaseDetection.changeCubeSide(GreenSide)
        ollCaseDetection.changeCubeState(CubeState.SOLVED_CUBE_STATE)
        val positionBeforeRotation = ollCaseDetection.transformStateToPositionRepresentation()

        ollCaseDetection.rotatePositionClockwise(positionBeforeRotation)

        val positionAfterRotation = ollCaseDetection.transformStateToPositionRepresentation()

        val expectedPosition = arrayOf(
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, 0, Orientation.Correct, Pair(0, 0)),
                PositionRepresentationElement(PieceType.EDGE, 0, Orientation.Correct, Pair(0, 1)),
                PositionRepresentationElement(PieceType.CORNER, 3, Orientation.Correct, Pair(0, 2))
            ),
            arrayOf(
                PositionRepresentationElement(PieceType.EDGE, 1, Orientation.Correct, Pair(1, 0)),
                PositionRepresentationElement(PieceType.EDGE, 3, Orientation.Correct, Pair(1, 2))
            ),
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, 1, Orientation.Correct, Pair(2, 0)),
                PositionRepresentationElement(PieceType.EDGE, 2, Orientation.Correct, Pair(2, 1)),
                PositionRepresentationElement(PieceType.CORNER, 2, Orientation.Correct, Pair(2, 2))
            )

        )

        assert(positionAfterRotation.contentDeepEquals(expectedPosition))
    }

    @Test
    fun positionRepresentationRotationTestRed(){
        ollCaseDetection.changeCubeSide(RedSide)
        ollCaseDetection.changeCubeState(CubeState.SOLVED_CUBE_STATE)
        val positionBeforeRotation = ollCaseDetection.transformStateToPositionRepresentation()

        ollCaseDetection.rotatePositionClockwise(positionBeforeRotation)

        val positionAfterRotation = ollCaseDetection.transformStateToPositionRepresentation()

        val expectedPosition = arrayOf(
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, 0, Orientation.Correct, Pair(0, 0)),
                PositionRepresentationElement(PieceType.EDGE, 1, Orientation.Correct, Pair(0, 1)),
                PositionRepresentationElement(PieceType.CORNER, 1, Orientation.Correct, Pair(0, 2))
            ),
            arrayOf(
                PositionRepresentationElement(PieceType.EDGE, 4, Orientation.Correct, Pair(1, 0)),
                PositionRepresentationElement(PieceType.EDGE, 5, Orientation.Correct, Pair(1, 2))
            ),
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, 4, Orientation.Correct, Pair(2, 0)),
                PositionRepresentationElement(PieceType.EDGE, 9, Orientation.Correct, Pair(2, 1)),
                PositionRepresentationElement(PieceType.CORNER, 5, Orientation.Correct, Pair(2, 2))
            )
        )

        assert(positionAfterRotation.contentDeepEquals(expectedPosition))
    }

    @Test
    fun positionRepresentationRotationTestOrange(){
        ollCaseDetection.changeCubeSide(OrangeSide)
        ollCaseDetection.changeCubeState(CubeState.SOLVED_CUBE_STATE)
        val positionBeforeRotation = ollCaseDetection.transformStateToPositionRepresentation()

        ollCaseDetection.rotatePositionClockwise(positionBeforeRotation)

        val positionAfterRotation = ollCaseDetection.transformStateToPositionRepresentation()

        val expectedPosition = arrayOf(
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, 2, Orientation.Correct, Pair(0, 0)),
                PositionRepresentationElement(PieceType.EDGE, 3, Orientation.Correct, Pair(0, 1)),
                PositionRepresentationElement(PieceType.CORNER, 3, Orientation.Correct, Pair(0, 2))
            ),
            arrayOf(
                PositionRepresentationElement(PieceType.EDGE, 6, Orientation.Correct, Pair(1, 0)),
                PositionRepresentationElement(PieceType.EDGE, 7, Orientation.Correct, Pair(1, 2))
            ),
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, 6, Orientation.Correct, Pair(2, 0)),
                PositionRepresentationElement(PieceType.EDGE, 11, Orientation.Correct, Pair(2, 1)),
                PositionRepresentationElement(PieceType.CORNER, 7, Orientation.Correct, Pair(2, 2))
            )
        )

        assert(positionAfterRotation.contentDeepEquals(expectedPosition))
    }
}