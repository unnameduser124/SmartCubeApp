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

class CubeStateToPositionRepresentationTests {

    private lateinit var positionTransformer: PositionRepresentationTransformer<OLLPositionRepresentationElement>
    private lateinit var context: Context

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        positionTransformer = PositionRepresentationTransformer(
            CubeState.SOLVED_CUBE_STATE,
            WhiteSide
        )
    }

    @Test
    fun cubeStateToPositionRepresentationTestWhite() {
        positionTransformer.changeCubeSide(WhiteSide)
        val positionRepresentation: Array<Array<OLLPositionRepresentationElement>> =
            positionTransformer.transformStateToPositionRepresentation(context)

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
        positionTransformer.changeCubeSide(YellowSide)
        val positionRepresentation: Array<Array<OLLPositionRepresentationElement>> =
            positionTransformer.transformStateToPositionRepresentation(context)

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
        positionTransformer.changeCubeSide(BlueSide)
        val positionRepresentation: Array<Array<OLLPositionRepresentationElement>> =
            positionTransformer.transformStateToPositionRepresentation(context)

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
        positionTransformer.changeCubeSide(GreenSide)
        val positionRepresentation: Array<Array<OLLPositionRepresentationElement>> =
            positionTransformer.transformStateToPositionRepresentation(context)

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
        positionTransformer.changeCubeSide(RedSide)
        val positionRepresentation: Array<Array<OLLPositionRepresentationElement>> =
            positionTransformer.transformStateToPositionRepresentation(context)

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
        positionTransformer.changeCubeSide(OrangeSide)
        val positionRepresentation: Array<Array<OLLPositionRepresentationElement>> =
            positionTransformer.transformStateToPositionRepresentation(context)

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