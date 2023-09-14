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
            ollCaseDetection.transformStateToPositionRepresentation(context)

        val expectedRepresentation = arrayOf(
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

        assert(positionRepresentation.contentDeepEquals(expectedRepresentation))
    }

    @Test
    fun cubeStateToPositionRepresentationTestYellow(){
        ollCaseDetection.changeCubeSide(YellowSide)
        val positionRepresentation =
            ollCaseDetection.transformStateToPositionRepresentation(context)

        val expectedRepresentation = arrayOf(
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

        assert(positionRepresentation.contentDeepEquals(expectedRepresentation))
    }

    @Test
    fun cubeStateToPositionRepresentationTestBlue(){
        ollCaseDetection.changeCubeSide(BlueSide)
        val positionRepresentation =
            ollCaseDetection.transformStateToPositionRepresentation(context)

        val expectedRepresentation = arrayOf(
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

        assert(positionRepresentation.contentDeepEquals(expectedRepresentation))
    }

    @Test
    fun cubeStateToPositionRepresentationTestGreen(){
        ollCaseDetection.changeCubeSide(GreenSide)
        val positionRepresentation =
            ollCaseDetection.transformStateToPositionRepresentation(context)

        val expectedRepresentation = arrayOf(
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

        assert(positionRepresentation.contentDeepEquals(expectedRepresentation))
    }

    @Test
    fun cubeStateToPositionRepresentationTestRed(){
        ollCaseDetection.changeCubeSide(RedSide)
        val positionRepresentation =
            ollCaseDetection.transformStateToPositionRepresentation(context)

        val expectedRepresentation = arrayOf(
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

        assert(positionRepresentation.contentDeepEquals(expectedRepresentation))
    }

    @Test
    fun cubeStateToPositionRepresentationTestOrange(){
        ollCaseDetection.changeCubeSide(OrangeSide)
        val positionRepresentation =
            ollCaseDetection.transformStateToPositionRepresentation(context)

        val expectedRepresentation = arrayOf(
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

        assert(positionRepresentation.contentDeepEquals(expectedRepresentation))
    }

}