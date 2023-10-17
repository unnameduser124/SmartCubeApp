package com.example.smartcubeapp.correctnesstests.casedetection.pllcasedetectiontests

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.example.smartcubeapp.casedetection.PositionRepresentationTransformer
import com.example.smartcubeapp.casedetection.plldetection.PLLElementPosition
import com.example.smartcubeapp.casedetection.plldetection.pllcase.PredefinedPLLCase
import com.example.smartcubeapp.cube.CubeState
import com.example.smartcubeapp.cube.WhiteSide
import com.example.smartcubeapp.cube.piece.PieceType
import org.junit.Before
import org.junit.Test

class PredefinedCaseTests {

    private lateinit var context: Context
    private lateinit var positionTransformer: PositionRepresentationTransformer

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        positionTransformer = PositionRepresentationTransformer(
            CubeState.SOLVED_CUBE_STATE,
            WhiteSide
        )
    }

    @Test
    fun PLLAaTest() {
        val positionRepresentation =
            positionTransformer.transformStateToPositionRepresentation<PLLElementPosition>(context)

        val applyCase = PredefinedPLLCase.Aa.performPermutation(positionRepresentation)

        val expectedPositionRepresentation = arrayOf(
            arrayOf(
                PLLElementPosition(PieceType.CORNER, 1, Pair(0, 0)),
                PLLElementPosition(PieceType.EDGE, 6, Pair(0, 1)),
                PLLElementPosition(PieceType.CORNER, 2, Pair(0, 2)),
            ),
            arrayOf(
                PLLElementPosition(PieceType.EDGE, 2, Pair(1, 0)),
                PLLElementPosition(PieceType.EDGE, 10, Pair(1, 2)),
            ),
            arrayOf(
                PLLElementPosition(PieceType.CORNER, 6, Pair(2, 0)),
                PLLElementPosition(PieceType.EDGE, 5, Pair(2, 1)),
                PLLElementPosition(PieceType.CORNER, 5, Pair(2, 2)),
            ),
        )
        assert(applyCase.contentDeepEquals(expectedPositionRepresentation))
    }

    @Test
    fun PLLAbTest() {
        val positionRepresentation =
            positionTransformer.transformStateToPositionRepresentation<PLLElementPosition>(context)

        val applyCase = PredefinedPLLCase.Ab.performPermutation(positionRepresentation)

        val expectedPositionRepresentation = arrayOf(
            arrayOf(
                PLLElementPosition(PieceType.CORNER, 5, Pair(0, 0)),
                PLLElementPosition(PieceType.EDGE, 6, Pair(0, 1)),
                PLLElementPosition(PieceType.CORNER, 6, Pair(0, 2)),
            ),
            arrayOf(
                PLLElementPosition(PieceType.EDGE, 2, Pair(1, 0)),
                PLLElementPosition(PieceType.EDGE, 10, Pair(1, 2)),
            ),
            arrayOf(
                PLLElementPosition(PieceType.CORNER, 2, Pair(2, 0)),
                PLLElementPosition(PieceType.EDGE, 5, Pair(2, 1)),
                PLLElementPosition(PieceType.CORNER, 1, Pair(2, 2)),
            ),
        )


        assert(applyCase.contentDeepEquals(expectedPositionRepresentation))
    }

    @Test
    fun PLLFTest() {
        val positionRepresentation =
            positionTransformer.transformStateToPositionRepresentation<PLLElementPosition>(context)

        val applyCase = PredefinedPLLCase.F.performPermutation(positionRepresentation)

        val expectedPositionRepresentation = arrayOf(
            arrayOf(
                PLLElementPosition(PieceType.CORNER, 2, Pair(0, 0)),
                PLLElementPosition(PieceType.EDGE, 5, Pair(0, 1)),
                PLLElementPosition(PieceType.CORNER, 5, Pair(0, 2)),
            ),
            arrayOf(
                PLLElementPosition(PieceType.EDGE, 2, Pair(1, 0)),
                PLLElementPosition(PieceType.EDGE, 10, Pair(1, 2)),
            ),
            arrayOf(
                PLLElementPosition(PieceType.CORNER, 1, Pair(2, 0)),
                PLLElementPosition(PieceType.EDGE, 6, Pair(2, 1)),
                PLLElementPosition(PieceType.CORNER, 6, Pair(2, 2)),
            ),
        )

        assert(applyCase.contentDeepEquals(expectedPositionRepresentation))
    }

    @Test
    fun PLLGaTest() {
        val positionRepresentation =
            positionTransformer.transformStateToPositionRepresentation<PLLElementPosition>(context)

        val applyCase = PredefinedPLLCase.Ga.performPermutation(positionRepresentation)

        val expectedPositionRepresentation = arrayOf(
            arrayOf(
                PLLElementPosition(PieceType.CORNER, 1, Pair(0, 0)),
                PLLElementPosition(PieceType.EDGE, 10, Pair(0, 1)),
                PLLElementPosition(PieceType.CORNER, 2, Pair(0, 2)),
            ),
            arrayOf(
                PLLElementPosition(PieceType.EDGE, 6, Pair(1, 0)),
                PLLElementPosition(PieceType.EDGE, 2, Pair(1, 2)),
            ),
            arrayOf(
                PLLElementPosition(PieceType.CORNER, 6, Pair(2, 0)),
                PLLElementPosition(PieceType.EDGE, 5, Pair(2, 1)),
                PLLElementPosition(PieceType.CORNER, 5, Pair(2, 2)),
            ),
        )

        assert(applyCase.contentDeepEquals(expectedPositionRepresentation))
    }

    @Test
    fun PLLGbTest() {
        val positionRepresentation =
            positionTransformer.transformStateToPositionRepresentation<PLLElementPosition>(context)

        val applyCase = PredefinedPLLCase.Gb.performPermutation(positionRepresentation)

        val expectedPositionRepresentation = arrayOf(
            arrayOf(
                PLLElementPosition(PieceType.CORNER, 6, Pair(0, 0)),
                PLLElementPosition(PieceType.EDGE, 2, Pair(0, 1)),
                PLLElementPosition(PieceType.CORNER, 1, Pair(0, 2)),
            ),
            arrayOf(
                PLLElementPosition(PieceType.EDGE, 10, Pair(1, 0)),
                PLLElementPosition(PieceType.EDGE, 6, Pair(1, 2)),
            ),
            arrayOf(
                PLLElementPosition(PieceType.CORNER, 2, Pair(2, 0)),
                PLLElementPosition(PieceType.EDGE, 5, Pair(2, 1)),
                PLLElementPosition(PieceType.CORNER, 5, Pair(2, 2)),
            ),
        )

        assert(applyCase.contentDeepEquals(expectedPositionRepresentation))
    }

    @Test
    fun PLLGcTest() {
        val positionRepresentation =
            positionTransformer.transformStateToPositionRepresentation<PLLElementPosition>(context)

        val applyCase = PredefinedPLLCase.Gc.performPermutation(positionRepresentation)

        val expectedPositionRepresentation = arrayOf(
            arrayOf(
                PLLElementPosition(PieceType.CORNER, 5, Pair(0, 0)),
                PLLElementPosition(PieceType.EDGE, 6, Pair(0, 1)),
                PLLElementPosition(PieceType.CORNER, 6, Pair(0, 2)),
            ),
            arrayOf(
                PLLElementPosition(PieceType.EDGE, 5, Pair(1, 0)),
                PLLElementPosition(PieceType.EDGE, 2, Pair(1, 2)),
            ),
            arrayOf(
                PLLElementPosition(PieceType.CORNER, 2, Pair(2, 0)),
                PLLElementPosition(PieceType.EDGE, 10, Pair(2, 1)),
                PLLElementPosition(PieceType.CORNER, 1, Pair(2, 2)),
            ),
        )

        assert(applyCase.contentDeepEquals(expectedPositionRepresentation))
    }

    @Test
    fun PLLGdTest() {
        val positionRepresentation =
            positionTransformer.transformStateToPositionRepresentation<PLLElementPosition>(context)

        val applyCase = PredefinedPLLCase.Gd.performPermutation(positionRepresentation)

        val expectedPositionRepresentation = arrayOf(
            arrayOf(
                PLLElementPosition(PieceType.CORNER, 1, Pair(0, 0)),
                PLLElementPosition(PieceType.EDGE, 5, Pair(0, 1)),
                PLLElementPosition(PieceType.CORNER, 2, Pair(0, 2)),
            ),
            arrayOf(
                PLLElementPosition(PieceType.EDGE, 6, Pair(1, 0)),
                PLLElementPosition(PieceType.EDGE, 10, Pair(1, 2)),
            ),
            arrayOf(
                PLLElementPosition(PieceType.CORNER, 6, Pair(2, 0)),
                PLLElementPosition(PieceType.EDGE, 2, Pair(2, 1)),
                PLLElementPosition(PieceType.CORNER, 5, Pair(2, 2)),
            ),
        )

        assert(applyCase.contentDeepEquals(expectedPositionRepresentation))
    }

    @Test
    fun PLLJaTest() {
        val positionRepresentation =
            positionTransformer.transformStateToPositionRepresentation<PLLElementPosition>(context)

        val applyCase = PredefinedPLLCase.Ja.performPermutation(positionRepresentation)

        val expectedPositionRepresentation = arrayOf(
            arrayOf(
                PLLElementPosition(PieceType.CORNER, 2, Pair(0, 0)),
                PLLElementPosition(PieceType.EDGE, 10, Pair(0, 1)),
                PLLElementPosition(PieceType.CORNER, 5, Pair(0, 2)),
            ),
            arrayOf(
                PLLElementPosition(PieceType.EDGE, 2, Pair(1, 0)),
                PLLElementPosition(PieceType.EDGE, 6, Pair(1, 2)),
            ),
            arrayOf(
                PLLElementPosition(PieceType.CORNER, 1, Pair(2, 0)),
                PLLElementPosition(PieceType.EDGE, 5, Pair(2, 1)),
                PLLElementPosition(PieceType.CORNER, 6, Pair(2, 2)),
            ),
        )

        assert(applyCase.contentDeepEquals(expectedPositionRepresentation))
    }

    @Test
    fun PLLJbTest() {
        val positionRepresentation =
            positionTransformer.transformStateToPositionRepresentation<PLLElementPosition>(context)

        val applyCase = PredefinedPLLCase.Jb.performPermutation(positionRepresentation)

        val expectedPositionRepresentation = arrayOf(
            arrayOf(
                PLLElementPosition(PieceType.CORNER, 2, Pair(0, 0)),
                PLLElementPosition(PieceType.EDGE, 6, Pair(0, 1)),
                PLLElementPosition(PieceType.CORNER, 5, Pair(0, 2)),
            ),
            arrayOf(
                PLLElementPosition(PieceType.EDGE, 2, Pair(1, 0)),
                PLLElementPosition(PieceType.EDGE, 5, Pair(1, 2)),
            ),
            arrayOf(
                PLLElementPosition(PieceType.CORNER, 1, Pair(2, 0)),
                PLLElementPosition(PieceType.EDGE, 10, Pair(2, 1)),
                PLLElementPosition(PieceType.CORNER, 6, Pair(2, 2)),
            ),
        )

        assert(applyCase.contentDeepEquals(expectedPositionRepresentation))
    }

    @Test
    fun PLLRaTest() {
        val positionRepresentation =
            positionTransformer.transformStateToPositionRepresentation<PLLElementPosition>(context)

        val applyCase = PredefinedPLLCase.Ra.performPermutation(positionRepresentation)

        val expectedPositionRepresentation = arrayOf(
            arrayOf(
                PLLElementPosition(PieceType.CORNER, 2, Pair(0, 0)),
                PLLElementPosition(PieceType.EDGE, 2, Pair(0, 1)),
                PLLElementPosition(PieceType.CORNER, 5, Pair(0, 2)),
            ),
            arrayOf(
                PLLElementPosition(PieceType.EDGE, 6, Pair(1, 0)),
                PLLElementPosition(PieceType.EDGE, 10, Pair(1, 2)),
            ),
            arrayOf(
                PLLElementPosition(PieceType.CORNER, 1, Pair(2, 0)),
                PLLElementPosition(PieceType.EDGE, 5, Pair(2, 1)),
                PLLElementPosition(PieceType.CORNER, 6, Pair(2, 2)),
            ),
        )

        assert(applyCase.contentDeepEquals(expectedPositionRepresentation))
    }

    @Test
    fun PLLRbTest() {
        val positionRepresentation =
            positionTransformer.transformStateToPositionRepresentation<PLLElementPosition>(context)

        val applyCase = PredefinedPLLCase.Rb.performPermutation(positionRepresentation)

        val expectedPositionRepresentation = arrayOf(
            arrayOf(
                PLLElementPosition(PieceType.CORNER, 2, Pair(0, 0)),
                PLLElementPosition(PieceType.EDGE, 6, Pair(0, 1)),
                PLLElementPosition(PieceType.CORNER, 5, Pair(0, 2)),
            ),
            arrayOf(
                PLLElementPosition(PieceType.EDGE, 5, Pair(1, 0)),
                PLLElementPosition(PieceType.EDGE, 10, Pair(1, 2)),
            ),
            arrayOf(
                PLLElementPosition(PieceType.CORNER, 1, Pair(2, 0)),
                PLLElementPosition(PieceType.EDGE, 2, Pair(2, 1)),
                PLLElementPosition(PieceType.CORNER, 6, Pair(2, 2)),
            ),
        )

        assert(applyCase.contentDeepEquals(expectedPositionRepresentation))
    }

    @Test
    fun PLLTTest() {
        val positionRepresentation =
            positionTransformer.transformStateToPositionRepresentation<PLLElementPosition>(context)

        val applyCase = PredefinedPLLCase.T.performPermutation(positionRepresentation)

        val expectedPositionRepresentation = arrayOf(
            arrayOf(
                PLLElementPosition(PieceType.CORNER, 2, Pair(0, 0)),
                PLLElementPosition(PieceType.EDGE, 6, Pair(0, 1)),
                PLLElementPosition(PieceType.CORNER, 5, Pair(0, 2)),
            ),
            arrayOf(
                PLLElementPosition(PieceType.EDGE, 10, Pair(1, 0)),
                PLLElementPosition(PieceType.EDGE, 2, Pair(1, 2)),
            ),
            arrayOf(
                PLLElementPosition(PieceType.CORNER, 1, Pair(2, 0)),
                PLLElementPosition(PieceType.EDGE, 5, Pair(2, 1)),
                PLLElementPosition(PieceType.CORNER, 6, Pair(2, 2)),
            ),
        )

        assert(applyCase.contentDeepEquals(expectedPositionRepresentation))
    }

    @Test
    fun PLLETest() {
        val positionRepresentation =
            positionTransformer.transformStateToPositionRepresentation<PLLElementPosition>(context)

        val applyCase = PredefinedPLLCase.E.performPermutation(positionRepresentation)

        val expectedPositionRepresentation = arrayOf(
            arrayOf(
                PLLElementPosition(PieceType.CORNER, 1, Pair(0, 0)),
                PLLElementPosition(PieceType.EDGE, 6, Pair(0, 1)),
                PLLElementPosition(PieceType.CORNER, 5, Pair(0, 2)),
            ),
            arrayOf(
                PLLElementPosition(PieceType.EDGE, 2, Pair(1, 0)),
                PLLElementPosition(PieceType.EDGE, 10, Pair(1, 2)),
            ),
            arrayOf(
                PLLElementPosition(PieceType.CORNER, 2, Pair(2, 0)),
                PLLElementPosition(PieceType.EDGE, 5, Pair(2, 1)),
                PLLElementPosition(PieceType.CORNER, 6, Pair(2, 2)),
            ),
        )

        assert(applyCase.contentDeepEquals(expectedPositionRepresentation))
    }

    @Test
    fun PLLNaTest() {
        val positionRepresentation =
            positionTransformer.transformStateToPositionRepresentation<PLLElementPosition>(context)

        val applyCase = PredefinedPLLCase.Na.performPermutation(positionRepresentation)

        val expectedPositionRepresentation = arrayOf(
            arrayOf(
                PLLElementPosition(PieceType.CORNER, 2, Pair(0, 0)),
                PLLElementPosition(PieceType.EDGE, 6, Pair(0, 1)),
                PLLElementPosition(PieceType.CORNER, 1, Pair(0, 2)),
            ),
            arrayOf(
                PLLElementPosition(PieceType.EDGE, 10, Pair(1, 0)),
                PLLElementPosition(PieceType.EDGE, 2, Pair(1, 2)),
            ),
            arrayOf(
                PLLElementPosition(PieceType.CORNER, 6, Pair(2, 0)),
                PLLElementPosition(PieceType.EDGE, 5, Pair(2, 1)),
                PLLElementPosition(PieceType.CORNER, 5, Pair(2, 2)),
            ),
        )

        assert(applyCase.contentDeepEquals(expectedPositionRepresentation))
    }

    @Test
    fun PLLNbTest() {
        val positionRepresentation =
            positionTransformer.transformStateToPositionRepresentation<PLLElementPosition>(context)

        val applyCase = PredefinedPLLCase.Nb.performPermutation(positionRepresentation)

        val expectedPositionRepresentation = arrayOf(
            arrayOf(
                PLLElementPosition(PieceType.CORNER, 5, Pair(0, 0)),
                PLLElementPosition(PieceType.EDGE, 6, Pair(0, 1)),
                PLLElementPosition(PieceType.CORNER, 6, Pair(0, 2)),
            ),
            arrayOf(
                PLLElementPosition(PieceType.EDGE, 10, Pair(1, 0)),
                PLLElementPosition(PieceType.EDGE, 2, Pair(1, 2)),
            ),
            arrayOf(
                PLLElementPosition(PieceType.CORNER, 1, Pair(2, 0)),
                PLLElementPosition(PieceType.EDGE, 5, Pair(2, 1)),
                PLLElementPosition(PieceType.CORNER, 2, Pair(2, 2)),
            ),
        )

        assert(applyCase.contentDeepEquals(expectedPositionRepresentation))
    }

    @Test
    fun PLLVTest() {
        val positionRepresentation =
            positionTransformer.transformStateToPositionRepresentation<PLLElementPosition>(context)

        val applyCase = PredefinedPLLCase.V.performPermutation(positionRepresentation)

        val expectedPositionRepresentation = arrayOf(
            arrayOf(
                PLLElementPosition(PieceType.CORNER, 5, Pair(0, 0)),
                PLLElementPosition(PieceType.EDGE, 10, Pair(0, 1)),
                PLLElementPosition(PieceType.CORNER, 6, Pair(0, 2)),
            ),
            arrayOf(
                PLLElementPosition(PieceType.EDGE, 2, Pair(1, 0)),
                PLLElementPosition(PieceType.EDGE, 6, Pair(1, 2)),
            ),
            arrayOf(
                PLLElementPosition(PieceType.CORNER, 1, Pair(2, 0)),
                PLLElementPosition(PieceType.EDGE, 5, Pair(2, 1)),
                PLLElementPosition(PieceType.CORNER, 2, Pair(2, 2)),
            ),
        )

        assert(applyCase.contentDeepEquals(expectedPositionRepresentation))
    }

    @Test
    fun PLLYTest() {
        val positionRepresentation =
            positionTransformer.transformStateToPositionRepresentation<PLLElementPosition>(context)

        val applyCase = PredefinedPLLCase.Y.performPermutation(positionRepresentation)

        val expectedPositionRepresentation = arrayOf(
            arrayOf(
                PLLElementPosition(PieceType.CORNER, 5, Pair(0, 0)),
                PLLElementPosition(PieceType.EDGE, 2, Pair(0, 1)),
                PLLElementPosition(PieceType.CORNER, 6, Pair(0, 2)),
            ),
            arrayOf(
                PLLElementPosition(PieceType.EDGE, 6, Pair(1, 0)),
                PLLElementPosition(PieceType.EDGE, 10, Pair(1, 2)),
            ),
            arrayOf(
                PLLElementPosition(PieceType.CORNER, 1, Pair(2, 0)),
                PLLElementPosition(PieceType.EDGE, 5, Pair(2, 1)),
                PLLElementPosition(PieceType.CORNER, 2, Pair(2, 2)),
            ),
        )

        assert(applyCase.contentDeepEquals(expectedPositionRepresentation))
    }

    @Test
    fun PLLHTest() {
        val positionRepresentation =
            positionTransformer.transformStateToPositionRepresentation<PLLElementPosition>(context)

        val applyCase = PredefinedPLLCase.H.performPermutation(positionRepresentation)

        val expectedPositionRepresentation = arrayOf(
            arrayOf(
                PLLElementPosition(PieceType.CORNER, 2, Pair(0, 0)),
                PLLElementPosition(PieceType.EDGE, 5, Pair(0, 1)),
                PLLElementPosition(PieceType.CORNER, 6, Pair(0, 2)),
            ),
            arrayOf(
                PLLElementPosition(PieceType.EDGE, 10, Pair(1, 0)),
                PLLElementPosition(PieceType.EDGE, 2, Pair(1, 2)),
            ),
            arrayOf(
                PLLElementPosition(PieceType.CORNER, 1, Pair(2, 0)),
                PLLElementPosition(PieceType.EDGE, 6, Pair(2, 1)),
                PLLElementPosition(PieceType.CORNER, 5, Pair(2, 2)),
            ),
        )

        assert(applyCase.contentDeepEquals(expectedPositionRepresentation))
    }

    @Test
    fun PLLUaTest() {
        val positionRepresentation =
            positionTransformer.transformStateToPositionRepresentation<PLLElementPosition>(context)

        val applyCase = PredefinedPLLCase.Ua.performPermutation(positionRepresentation)

        val expectedPositionRepresentation = arrayOf(
            arrayOf(
                PLLElementPosition(PieceType.CORNER, 2, Pair(0, 0)),
                PLLElementPosition(PieceType.EDGE, 6, Pair(0, 1)),
                PLLElementPosition(PieceType.CORNER, 6, Pair(0, 2)),
            ),
            arrayOf(
                PLLElementPosition(PieceType.EDGE, 10, Pair(1, 0)),
                PLLElementPosition(PieceType.EDGE, 5, Pair(1, 2)),
            ),
            arrayOf(
                PLLElementPosition(PieceType.CORNER, 1, Pair(2, 0)),
                PLLElementPosition(PieceType.EDGE, 2, Pair(2, 1)),
                PLLElementPosition(PieceType.CORNER, 5, Pair(2, 2)),
            ),
        )

        assert(applyCase.contentDeepEquals(expectedPositionRepresentation))
    }

    @Test
    fun PLLUbTest() {
        val positionRepresentation =
            positionTransformer.transformStateToPositionRepresentation<PLLElementPosition>(context)

        val applyCase = PredefinedPLLCase.Ub.performPermutation(positionRepresentation)

        val expectedPositionRepresentation = arrayOf(
            arrayOf(
                PLLElementPosition(PieceType.CORNER, 2, Pair(0, 0)),
                PLLElementPosition(PieceType.EDGE, 6, Pair(0, 1)),
                PLLElementPosition(PieceType.CORNER, 6, Pair(0, 2)),
            ),
            arrayOf(
                PLLElementPosition(PieceType.EDGE, 5, Pair(1, 0)),
                PLLElementPosition(PieceType.EDGE, 2, Pair(1, 2)),
            ),
            arrayOf(
                PLLElementPosition(PieceType.CORNER, 1, Pair(2, 0)),
                PLLElementPosition(PieceType.EDGE, 10, Pair(2, 1)),
                PLLElementPosition(PieceType.CORNER, 5, Pair(2, 2)),
            ),
        )

        assert(applyCase.contentDeepEquals(expectedPositionRepresentation))
    }

    @Test
    fun PLLZTest() {
        val positionRepresentation =
            positionTransformer.transformStateToPositionRepresentation<PLLElementPosition>(context)

        val applyCase = PredefinedPLLCase.Z.performPermutation(positionRepresentation)

        val expectedPositionRepresentation = arrayOf(
            arrayOf(
                PLLElementPosition(PieceType.CORNER, 2, Pair(0, 0)),
                PLLElementPosition(PieceType.EDGE, 10, Pair(0, 1)),
                PLLElementPosition(PieceType.CORNER, 6, Pair(0, 2)),
            ),
            arrayOf(
                PLLElementPosition(PieceType.EDGE, 5, Pair(1, 0)),
                PLLElementPosition(PieceType.EDGE, 6, Pair(1, 2)),
            ),
            arrayOf(
                PLLElementPosition(PieceType.CORNER, 1, Pair(2, 0)),
                PLLElementPosition(PieceType.EDGE, 2, Pair(2, 1)),
                PLLElementPosition(PieceType.CORNER, 5, Pair(2, 2)),
            ),
        )

        assert(applyCase.contentDeepEquals(expectedPositionRepresentation))
    }
}