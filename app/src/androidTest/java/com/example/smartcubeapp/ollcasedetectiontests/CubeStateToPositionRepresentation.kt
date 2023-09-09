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

class CubeStateToPositionRepresentationTests {

    private lateinit var ollCaseDetection: OLLCaseDetection
    private lateinit var context: Context

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        ollCaseDetection = OLLCaseDetection(CubeState.SOLVED_CUBE_STATE, WhiteSide)
    }

    @Test
    fun cubeStateToPositionRepresentationTestWhite() {
        ollCaseDetection.changeCubeSide(WhiteSide)
        val positionRepresentation =
            ollCaseDetection.transformStateToPositionRepresentation()

        val expectedRepresentation = arrayOf(
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, 2, Orientation.Correct, Pair(0, 0)),
                PositionRepresentationElement(PieceType.EDGE, 5, Orientation.Correct, Pair(0, 1)),
                PositionRepresentationElement(PieceType.CORNER, 6, Orientation.Correct, Pair(0, 2))
            ),
            arrayOf(
                PositionRepresentationElement(PieceType.EDGE, 2, Orientation.Correct, Pair(1, 0)),
                PositionRepresentationElement(PieceType.EDGE, 10, Orientation.Correct, Pair(1, 2))
            ),
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, 1, Orientation.Correct, Pair(2, 0)),
                PositionRepresentationElement(PieceType.EDGE, 6, Orientation.Correct, Pair(2, 1)),
                PositionRepresentationElement(PieceType.CORNER, 5, Orientation.Correct, Pair(2, 2))
            )
        )

        assert(positionRepresentation.contentDeepEquals(expectedRepresentation))
    }

    @Test
    fun cubeStateToPositionRepresentationTestYellow(){
        ollCaseDetection.changeCubeSide(YellowSide)
        val positionRepresentation =
            ollCaseDetection.transformStateToPositionRepresentation()

        val expectedRepresentation = arrayOf(
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, 4, Orientation.Correct, Pair(0, 0)),
                PositionRepresentationElement(PieceType.EDGE, 8, Orientation.Correct, Pair(0, 1)),
                PositionRepresentationElement(PieceType.CORNER, 7, Orientation.Correct, Pair(0, 2))
            ),
            arrayOf(
                PositionRepresentationElement(PieceType.EDGE, 4, Orientation.Correct, Pair(1, 0)),
                PositionRepresentationElement(PieceType.EDGE, 7, Orientation.Correct, Pair(1, 2))
            ),
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, 0, Orientation.Correct, Pair(2, 0)),
                PositionRepresentationElement(PieceType.EDGE, 0, Orientation.Correct, Pair(2, 1)),
                PositionRepresentationElement(PieceType.CORNER, 3, Orientation.Correct, Pair(2, 2))
            )
        )

        assert(positionRepresentation.contentDeepEquals(expectedRepresentation))
    }

    @Test
    fun cubeStateToPositionRepresentationTestBlue(){
        ollCaseDetection.changeCubeSide(BlueSide)
        val positionRepresentation =
            ollCaseDetection.transformStateToPositionRepresentation()

        val expectedRepresentation = arrayOf(
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, 5, Orientation.Correct, Pair(0, 0)),
                PositionRepresentationElement(PieceType.EDGE, 10, Orientation.Correct, Pair(0, 1)),
                PositionRepresentationElement(PieceType.CORNER, 6, Orientation.Correct, Pair(0, 2))
            ),
            arrayOf(
                PositionRepresentationElement(PieceType.EDGE, 9, Orientation.Correct, Pair(1, 0)),
                PositionRepresentationElement(PieceType.EDGE, 11, Orientation.Correct, Pair(1, 2))
            ),
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, 4, Orientation.Correct, Pair(2, 0)),
                PositionRepresentationElement(PieceType.EDGE, 8, Orientation.Correct, Pair(2, 1)),
                PositionRepresentationElement(PieceType.CORNER, 7, Orientation.Correct, Pair(2, 2))
            )
        )

        assert(positionRepresentation.contentDeepEquals(expectedRepresentation))
    }

    @Test
    fun cubeStateToPositionRepresentationTestGreen(){
        ollCaseDetection.changeCubeSide(GreenSide)
        val positionRepresentation =
            ollCaseDetection.transformStateToPositionRepresentation()

        val expectedRepresentation = arrayOf(
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, 3, Orientation.Correct, Pair(0, 0)),
                PositionRepresentationElement(PieceType.EDGE, 3, Orientation.Correct, Pair(0, 1)),
                PositionRepresentationElement(PieceType.CORNER, 2, Orientation.Correct, Pair(0, 2))
            ),
            arrayOf(
                PositionRepresentationElement(PieceType.EDGE, 0, Orientation.Correct, Pair(1, 0)),
                PositionRepresentationElement(PieceType.EDGE, 2, Orientation.Correct, Pair(1, 2))
            ),
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, 0, Orientation.Correct, Pair(2, 0)),
                PositionRepresentationElement(PieceType.EDGE, 1, Orientation.Correct, Pair(2, 1)),
                PositionRepresentationElement(PieceType.CORNER, 1, Orientation.Correct, Pair(2, 2))
            )
        )

        assert(positionRepresentation.contentDeepEquals(expectedRepresentation))
    }

    @Test
    fun cubeStateToPositionRepresentationTestRed(){
        ollCaseDetection.changeCubeSide(RedSide)
        val positionRepresentation =
            ollCaseDetection.transformStateToPositionRepresentation()

        val expectedRepresentation = arrayOf(
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, 1, Orientation.Correct, Pair(0, 0)),
                PositionRepresentationElement(PieceType.EDGE, 5, Orientation.Correct, Pair(0, 1)),
                PositionRepresentationElement(PieceType.CORNER, 5, Orientation.Correct, Pair(0, 2))
            ),
            arrayOf(
                PositionRepresentationElement(PieceType.EDGE, 1, Orientation.Correct, Pair(1, 0)),
                PositionRepresentationElement(PieceType.EDGE, 9, Orientation.Correct, Pair(1, 2))
            ),
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, 0, Orientation.Correct, Pair(2, 0)),
                PositionRepresentationElement(PieceType.EDGE, 4, Orientation.Correct, Pair(2, 1)),
                PositionRepresentationElement(PieceType.CORNER, 4, Orientation.Correct, Pair(2, 2))
            )
        )

        assert(positionRepresentation.contentDeepEquals(expectedRepresentation))
    }

    @Test
    fun cubeStateToPositionRepresentationTestOrange(){
        ollCaseDetection.changeCubeSide(OrangeSide)
        val positionRepresentation =
            ollCaseDetection.transformStateToPositionRepresentation()

        val expectedRepresentation = arrayOf(
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, 3, Orientation.Correct, Pair(0, 0)),
                PositionRepresentationElement(PieceType.EDGE, 7, Orientation.Correct, Pair(0, 1)),
                PositionRepresentationElement(PieceType.CORNER, 7, Orientation.Correct, Pair(0, 2))
            ),
            arrayOf(
                PositionRepresentationElement(PieceType.EDGE, 3, Orientation.Correct, Pair(1, 0)),
                PositionRepresentationElement(PieceType.EDGE, 11, Orientation.Correct, Pair(1, 2))
            ),
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, 2, Orientation.Correct, Pair(2, 0)),
                PositionRepresentationElement(PieceType.EDGE, 6, Orientation.Correct, Pair(2, 1)),
                PositionRepresentationElement(PieceType.CORNER, 6, Orientation.Correct, Pair(2, 2))
            )
        )

        assert(positionRepresentation.contentDeepEquals(expectedRepresentation))
    }

}