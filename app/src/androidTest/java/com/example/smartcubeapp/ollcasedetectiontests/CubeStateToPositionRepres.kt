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
        ollCaseDetection.setCubeSide(WhiteSide)
        val positionRepresentation =
            ollCaseDetection.transformStateToPositionRepresentation()

        val expectedRepresentation = arrayOf(
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, 2, Orientation.Correct),
                PositionRepresentationElement(PieceType.EDGE, 5, Orientation.Correct),
                PositionRepresentationElement(PieceType.CORNER, 6, Orientation.Correct)
            ),
            arrayOf(
                PositionRepresentationElement(PieceType.EDGE, 2, Orientation.Correct),
                PositionRepresentationElement(PieceType.EDGE, 10, Orientation.Correct)
            ),
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, 1, Orientation.Correct),
                PositionRepresentationElement(PieceType.EDGE, 6, Orientation.Correct),
                PositionRepresentationElement(PieceType.CORNER, 5, Orientation.Correct)
            )
        )

        assert(positionRepresentation.contentDeepEquals(expectedRepresentation))
    }

    @Test
    fun cubeStateToPositionRepresentationTestYellow(){
        ollCaseDetection.setCubeSide(YellowSide)
        val positionRepresentation =
            ollCaseDetection.transformStateToPositionRepresentation()

        val expectedRepresentation = arrayOf(
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, 4, Orientation.Correct),
                PositionRepresentationElement(PieceType.EDGE, 8, Orientation.Correct),
                PositionRepresentationElement(PieceType.CORNER, 7, Orientation.Correct)
            ),
            arrayOf(
                PositionRepresentationElement(PieceType.EDGE, 4, Orientation.Correct),
                PositionRepresentationElement(PieceType.EDGE, 7, Orientation.Correct)
            ),
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, 0, Orientation.Correct),
                PositionRepresentationElement(PieceType.EDGE, 0, Orientation.Correct),
                PositionRepresentationElement(PieceType.CORNER, 3, Orientation.Correct)
            )
        )

        assert(positionRepresentation.contentDeepEquals(expectedRepresentation))
    }

    @Test
    fun cubeStateToPositionRepresentationTestBlue(){
        ollCaseDetection.setCubeSide(BlueSide)
        val positionRepresentation =
            ollCaseDetection.transformStateToPositionRepresentation()

        val expectedRepresentation = arrayOf(
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, 5, Orientation.Correct),
                PositionRepresentationElement(PieceType.EDGE, 10, Orientation.Correct),
                PositionRepresentationElement(PieceType.CORNER, 6, Orientation.Correct)
            ),
            arrayOf(
                PositionRepresentationElement(PieceType.EDGE, 9, Orientation.Correct),
                PositionRepresentationElement(PieceType.EDGE, 11, Orientation.Correct)
            ),
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, 4, Orientation.Correct),
                PositionRepresentationElement(PieceType.EDGE, 8, Orientation.Correct),
                PositionRepresentationElement(PieceType.CORNER, 7, Orientation.Correct)
            )
        )

        assert(positionRepresentation.contentDeepEquals(expectedRepresentation))
    }

    @Test
    fun cubeStateToPositionRepresentationTestGreen(){
        ollCaseDetection.setCubeSide(GreenSide)
        val positionRepresentation =
            ollCaseDetection.transformStateToPositionRepresentation()

        val expectedRepresentation = arrayOf(
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, 3, Orientation.Correct),
                PositionRepresentationElement(PieceType.EDGE, 3, Orientation.Correct),
                PositionRepresentationElement(PieceType.CORNER, 2, Orientation.Correct)
            ),
            arrayOf(
                PositionRepresentationElement(PieceType.EDGE, 0, Orientation.Correct),
                PositionRepresentationElement(PieceType.EDGE, 2, Orientation.Correct)
            ),
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, 0, Orientation.Correct),
                PositionRepresentationElement(PieceType.EDGE, 1, Orientation.Correct),
                PositionRepresentationElement(PieceType.CORNER, 1, Orientation.Correct)
            )
        )

        assert(positionRepresentation.contentDeepEquals(expectedRepresentation))
    }

    @Test
    fun cubeStateToPositionRepresentationTestRed(){
        ollCaseDetection.setCubeSide(RedSide)
        val positionRepresentation =
            ollCaseDetection.transformStateToPositionRepresentation()

        val expectedRepresentation = arrayOf(
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, 1, Orientation.Correct),
                PositionRepresentationElement(PieceType.EDGE, 5, Orientation.Correct),
                PositionRepresentationElement(PieceType.CORNER, 5, Orientation.Correct)
            ),
            arrayOf(
                PositionRepresentationElement(PieceType.EDGE, 1, Orientation.Correct),
                PositionRepresentationElement(PieceType.EDGE, 9, Orientation.Correct)
            ),
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, 0, Orientation.Correct),
                PositionRepresentationElement(PieceType.EDGE, 4, Orientation.Correct),
                PositionRepresentationElement(PieceType.CORNER, 4, Orientation.Correct)
            )
        )

        assert(positionRepresentation.contentDeepEquals(expectedRepresentation))
    }

    @Test
    fun cubeStateToPositionRepresentationTestOrange(){
        ollCaseDetection.setCubeSide(OrangeSide)
        val positionRepresentation =
            ollCaseDetection.transformStateToPositionRepresentation()

        val expectedRepresentation = arrayOf(
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, 3, Orientation.Correct),
                PositionRepresentationElement(PieceType.EDGE, 7, Orientation.Correct),
                PositionRepresentationElement(PieceType.CORNER, 7, Orientation.Correct)
            ),
            arrayOf(
                PositionRepresentationElement(PieceType.EDGE, 3, Orientation.Correct),
                PositionRepresentationElement(PieceType.EDGE, 11, Orientation.Correct)
            ),
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, 2, Orientation.Correct),
                PositionRepresentationElement(PieceType.EDGE, 6, Orientation.Correct),
                PositionRepresentationElement(PieceType.CORNER, 6, Orientation.Correct)
            )
        )

        assert(positionRepresentation.contentDeepEquals(expectedRepresentation))
    }

}