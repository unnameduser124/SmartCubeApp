package com.example.tests.correctnesstests.detectiontests.casedetection.pllcasedetectiontests

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.example.cube_detection.casedetection.PositionRepresentationTransformer
import com.example.cube_detection.casedetection.plldetection.PLLElementPosition
import com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase
import com.example.cube_cube.cube.CubeState
import com.example.cube_cube.cube.WhiteSide
import com.example.cube_cube.cube.piece.PieceType
import org.junit.Before
import org.junit.Test

class PredefinedCaseTests {

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
    fun PLLAaTest() {
        val positionRepresentation =
            positionTransformer.transformStateToPositionRepresentation<com.example.cube_detection.casedetection.plldetection.PLLElementPosition>(context)

        val applyCase = com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase.Aa.performPermutation(positionRepresentation)

        val expectedPositionRepresentation = arrayOf(
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    1,
                    Pair(0, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    6,
                    Pair(0, 1)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    2,
                    Pair(0, 2)
                ),
            ),
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    2,
                    Pair(1, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    10,
                    Pair(1, 2)
                ),
            ),
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    6,
                    Pair(2, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    5,
                    Pair(2, 1)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    5,
                    Pair(2, 2)
                ),
            ),
        )
        assert(applyCase.contentDeepEquals(expectedPositionRepresentation))
    }

    @Test
    fun PLLAbTest() {
        val positionRepresentation =
            positionTransformer.transformStateToPositionRepresentation<com.example.cube_detection.casedetection.plldetection.PLLElementPosition>(context)

        val applyCase = com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase.Ab.performPermutation(positionRepresentation)

        val expectedPositionRepresentation = arrayOf(
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    5,
                    Pair(0, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    6,
                    Pair(0, 1)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    6,
                    Pair(0, 2)
                ),
            ),
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    2,
                    Pair(1, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    10,
                    Pair(1, 2)
                ),
            ),
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    2,
                    Pair(2, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    5,
                    Pair(2, 1)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    1,
                    Pair(2, 2)
                ),
            ),
        )


        assert(applyCase.contentDeepEquals(expectedPositionRepresentation))
    }

    @Test
    fun PLLFTest() {
        val positionRepresentation =
            positionTransformer.transformStateToPositionRepresentation<com.example.cube_detection.casedetection.plldetection.PLLElementPosition>(context)

        val applyCase = com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase.F.performPermutation(positionRepresentation)

        val expectedPositionRepresentation = arrayOf(
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    2,
                    Pair(0, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    5,
                    Pair(0, 1)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    5,
                    Pair(0, 2)
                ),
            ),
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    2,
                    Pair(1, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    10,
                    Pair(1, 2)
                ),
            ),
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    1,
                    Pair(2, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    6,
                    Pair(2, 1)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    6,
                    Pair(2, 2)
                ),
            ),
        )

        assert(applyCase.contentDeepEquals(expectedPositionRepresentation))
    }

    @Test
    fun PLLGaTest() {
        val positionRepresentation =
            positionTransformer.transformStateToPositionRepresentation<com.example.cube_detection.casedetection.plldetection.PLLElementPosition>(context)

        val applyCase = com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase.Ga.performPermutation(positionRepresentation)

        val expectedPositionRepresentation = arrayOf(
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    1,
                    Pair(0, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    10,
                    Pair(0, 1)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    2,
                    Pair(0, 2)
                ),
            ),
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    6,
                    Pair(1, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    2,
                    Pair(1, 2)
                ),
            ),
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    6,
                    Pair(2, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    5,
                    Pair(2, 1)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    5,
                    Pair(2, 2)
                ),
            ),
        )

        assert(applyCase.contentDeepEquals(expectedPositionRepresentation))
    }

    @Test
    fun PLLGbTest() {
        val positionRepresentation =
            positionTransformer.transformStateToPositionRepresentation<com.example.cube_detection.casedetection.plldetection.PLLElementPosition>(context)

        val applyCase = com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase.Gb.performPermutation(positionRepresentation)

        val expectedPositionRepresentation = arrayOf(
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    6,
                    Pair(0, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    2,
                    Pair(0, 1)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    1,
                    Pair(0, 2)
                ),
            ),
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    10,
                    Pair(1, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    6,
                    Pair(1, 2)
                ),
            ),
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    2,
                    Pair(2, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    5,
                    Pair(2, 1)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    5,
                    Pair(2, 2)
                ),
            ),
        )

        assert(applyCase.contentDeepEquals(expectedPositionRepresentation))
    }

    @Test
    fun PLLGcTest() {
        val positionRepresentation =
            positionTransformer.transformStateToPositionRepresentation<com.example.cube_detection.casedetection.plldetection.PLLElementPosition>(context)

        val applyCase = com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase.Gc.performPermutation(positionRepresentation)

        val expectedPositionRepresentation = arrayOf(
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    5,
                    Pair(0, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    6,
                    Pair(0, 1)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    6,
                    Pair(0, 2)
                ),
            ),
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    5,
                    Pair(1, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    2,
                    Pair(1, 2)
                ),
            ),
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    2,
                    Pair(2, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    10,
                    Pair(2, 1)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    1,
                    Pair(2, 2)
                ),
            ),
        )

        assert(applyCase.contentDeepEquals(expectedPositionRepresentation))
    }

    @Test
    fun PLLGdTest() {
        val positionRepresentation =
            positionTransformer.transformStateToPositionRepresentation<com.example.cube_detection.casedetection.plldetection.PLLElementPosition>(context)

        val applyCase = com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase.Gd.performPermutation(positionRepresentation)

        val expectedPositionRepresentation = arrayOf(
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    1,
                    Pair(0, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    5,
                    Pair(0, 1)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    2,
                    Pair(0, 2)
                ),
            ),
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    6,
                    Pair(1, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    10,
                    Pair(1, 2)
                ),
            ),
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    6,
                    Pair(2, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    2,
                    Pair(2, 1)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    5,
                    Pair(2, 2)
                ),
            ),
        )

        assert(applyCase.contentDeepEquals(expectedPositionRepresentation))
    }

    @Test
    fun PLLJaTest() {
        val positionRepresentation =
            positionTransformer.transformStateToPositionRepresentation<com.example.cube_detection.casedetection.plldetection.PLLElementPosition>(context)

        val applyCase = com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase.Ja.performPermutation(positionRepresentation)

        val expectedPositionRepresentation = arrayOf(
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    2,
                    Pair(0, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    10,
                    Pair(0, 1)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    5,
                    Pair(0, 2)
                ),
            ),
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    2,
                    Pair(1, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    6,
                    Pair(1, 2)
                ),
            ),
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    1,
                    Pair(2, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    5,
                    Pair(2, 1)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    6,
                    Pair(2, 2)
                ),
            ),
        )

        assert(applyCase.contentDeepEquals(expectedPositionRepresentation))
    }

    @Test
    fun PLLJbTest() {
        val positionRepresentation =
            positionTransformer.transformStateToPositionRepresentation<com.example.cube_detection.casedetection.plldetection.PLLElementPosition>(context)

        val applyCase = com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase.Jb.performPermutation(positionRepresentation)

        val expectedPositionRepresentation = arrayOf(
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    2,
                    Pair(0, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    6,
                    Pair(0, 1)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    5,
                    Pair(0, 2)
                ),
            ),
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    2,
                    Pair(1, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    5,
                    Pair(1, 2)
                ),
            ),
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    1,
                    Pair(2, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    10,
                    Pair(2, 1)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    6,
                    Pair(2, 2)
                ),
            ),
        )

        assert(applyCase.contentDeepEquals(expectedPositionRepresentation))
    }

    @Test
    fun PLLRaTest() {
        val positionRepresentation =
            positionTransformer.transformStateToPositionRepresentation<com.example.cube_detection.casedetection.plldetection.PLLElementPosition>(context)

        val applyCase = com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase.Ra.performPermutation(positionRepresentation)

        val expectedPositionRepresentation = arrayOf(
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    2,
                    Pair(0, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    2,
                    Pair(0, 1)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    5,
                    Pair(0, 2)
                ),
            ),
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    6,
                    Pair(1, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    10,
                    Pair(1, 2)
                ),
            ),
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    1,
                    Pair(2, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    5,
                    Pair(2, 1)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    6,
                    Pair(2, 2)
                ),
            ),
        )

        assert(applyCase.contentDeepEquals(expectedPositionRepresentation))
    }

    @Test
    fun PLLRbTest() {
        val positionRepresentation =
            positionTransformer.transformStateToPositionRepresentation<com.example.cube_detection.casedetection.plldetection.PLLElementPosition>(context)

        val applyCase = com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase.Rb.performPermutation(positionRepresentation)

        val expectedPositionRepresentation = arrayOf(
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    2,
                    Pair(0, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    6,
                    Pair(0, 1)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    5,
                    Pair(0, 2)
                ),
            ),
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    5,
                    Pair(1, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    10,
                    Pair(1, 2)
                ),
            ),
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    1,
                    Pair(2, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    2,
                    Pair(2, 1)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    6,
                    Pair(2, 2)
                ),
            ),
        )

        assert(applyCase.contentDeepEquals(expectedPositionRepresentation))
    }

    @Test
    fun PLLTTest() {
        val positionRepresentation =
            positionTransformer.transformStateToPositionRepresentation<com.example.cube_detection.casedetection.plldetection.PLLElementPosition>(context)

        val applyCase = com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase.T.performPermutation(positionRepresentation)

        val expectedPositionRepresentation = arrayOf(
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    2,
                    Pair(0, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    6,
                    Pair(0, 1)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    5,
                    Pair(0, 2)
                ),
            ),
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    10,
                    Pair(1, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    2,
                    Pair(1, 2)
                ),
            ),
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    1,
                    Pair(2, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    5,
                    Pair(2, 1)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    6,
                    Pair(2, 2)
                ),
            ),
        )

        assert(applyCase.contentDeepEquals(expectedPositionRepresentation))
    }

    @Test
    fun PLLETest() {
        val positionRepresentation =
            positionTransformer.transformStateToPositionRepresentation<com.example.cube_detection.casedetection.plldetection.PLLElementPosition>(context)

        val applyCase = com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase.E.performPermutation(positionRepresentation)

        val expectedPositionRepresentation = arrayOf(
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    1,
                    Pair(0, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    6,
                    Pair(0, 1)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    5,
                    Pair(0, 2)
                ),
            ),
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    2,
                    Pair(1, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    10,
                    Pair(1, 2)
                ),
            ),
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    2,
                    Pair(2, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    5,
                    Pair(2, 1)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    6,
                    Pair(2, 2)
                ),
            ),
        )

        assert(applyCase.contentDeepEquals(expectedPositionRepresentation))
    }

    @Test
    fun PLLNaTest() {
        val positionRepresentation =
            positionTransformer.transformStateToPositionRepresentation<com.example.cube_detection.casedetection.plldetection.PLLElementPosition>(context)

        val applyCase = com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase.Na.performPermutation(positionRepresentation)

        val expectedPositionRepresentation = arrayOf(
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    2,
                    Pair(0, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    6,
                    Pair(0, 1)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    1,
                    Pair(0, 2)
                ),
            ),
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    10,
                    Pair(1, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    2,
                    Pair(1, 2)
                ),
            ),
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    6,
                    Pair(2, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    5,
                    Pair(2, 1)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    5,
                    Pair(2, 2)
                ),
            ),
        )

        assert(applyCase.contentDeepEquals(expectedPositionRepresentation))
    }

    @Test
    fun PLLNbTest() {
        val positionRepresentation =
            positionTransformer.transformStateToPositionRepresentation<com.example.cube_detection.casedetection.plldetection.PLLElementPosition>(context)

        val applyCase = com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase.Nb.performPermutation(positionRepresentation)

        val expectedPositionRepresentation = arrayOf(
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    5,
                    Pair(0, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    6,
                    Pair(0, 1)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    6,
                    Pair(0, 2)
                ),
            ),
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    10,
                    Pair(1, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    2,
                    Pair(1, 2)
                ),
            ),
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    1,
                    Pair(2, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    5,
                    Pair(2, 1)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    2,
                    Pair(2, 2)
                ),
            ),
        )

        assert(applyCase.contentDeepEquals(expectedPositionRepresentation))
    }

    @Test
    fun PLLVTest() {
        val positionRepresentation =
            positionTransformer.transformStateToPositionRepresentation<com.example.cube_detection.casedetection.plldetection.PLLElementPosition>(context)

        val applyCase = com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase.V.performPermutation(positionRepresentation)

        val expectedPositionRepresentation = arrayOf(
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    5,
                    Pair(0, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    10,
                    Pair(0, 1)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    6,
                    Pair(0, 2)
                ),
            ),
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    2,
                    Pair(1, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    6,
                    Pair(1, 2)
                ),
            ),
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    1,
                    Pair(2, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    5,
                    Pair(2, 1)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    2,
                    Pair(2, 2)
                ),
            ),
        )

        assert(applyCase.contentDeepEquals(expectedPositionRepresentation))
    }

    @Test
    fun PLLYTest() {
        val positionRepresentation =
            positionTransformer.transformStateToPositionRepresentation<com.example.cube_detection.casedetection.plldetection.PLLElementPosition>(context)

        val applyCase = com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase.Y.performPermutation(positionRepresentation)

        val expectedPositionRepresentation = arrayOf(
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    5,
                    Pair(0, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    2,
                    Pair(0, 1)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    6,
                    Pair(0, 2)
                ),
            ),
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    6,
                    Pair(1, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    10,
                    Pair(1, 2)
                ),
            ),
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    1,
                    Pair(2, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    5,
                    Pair(2, 1)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    2,
                    Pair(2, 2)
                ),
            ),
        )

        assert(applyCase.contentDeepEquals(expectedPositionRepresentation))
    }

    @Test
    fun PLLHTest() {
        val positionRepresentation =
            positionTransformer.transformStateToPositionRepresentation<com.example.cube_detection.casedetection.plldetection.PLLElementPosition>(context)

        val applyCase = com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase.H.performPermutation(positionRepresentation)

        val expectedPositionRepresentation = arrayOf(
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    2,
                    Pair(0, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    5,
                    Pair(0, 1)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    6,
                    Pair(0, 2)
                ),
            ),
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    10,
                    Pair(1, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    2,
                    Pair(1, 2)
                ),
            ),
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    1,
                    Pair(2, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    6,
                    Pair(2, 1)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    5,
                    Pair(2, 2)
                ),
            ),
        )

        assert(applyCase.contentDeepEquals(expectedPositionRepresentation))
    }

    @Test
    fun PLLUaTest() {
        val positionRepresentation =
            positionTransformer.transformStateToPositionRepresentation<com.example.cube_detection.casedetection.plldetection.PLLElementPosition>(context)

        val applyCase = com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase.Ua.performPermutation(positionRepresentation)

        val expectedPositionRepresentation = arrayOf(
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    2,
                    Pair(0, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    6,
                    Pair(0, 1)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    6,
                    Pair(0, 2)
                ),
            ),
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    10,
                    Pair(1, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    5,
                    Pair(1, 2)
                ),
            ),
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    1,
                    Pair(2, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    2,
                    Pair(2, 1)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    5,
                    Pair(2, 2)
                ),
            ),
        )

        assert(applyCase.contentDeepEquals(expectedPositionRepresentation))
    }

    @Test
    fun PLLUbTest() {
        val positionRepresentation =
            positionTransformer.transformStateToPositionRepresentation<com.example.cube_detection.casedetection.plldetection.PLLElementPosition>(context)

        val applyCase = com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase.Ub.performPermutation(positionRepresentation)

        val expectedPositionRepresentation = arrayOf(
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    2,
                    Pair(0, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    6,
                    Pair(0, 1)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    6,
                    Pair(0, 2)
                ),
            ),
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    5,
                    Pair(1, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    2,
                    Pair(1, 2)
                ),
            ),
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    1,
                    Pair(2, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    10,
                    Pair(2, 1)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    5,
                    Pair(2, 2)
                ),
            ),
        )

        assert(applyCase.contentDeepEquals(expectedPositionRepresentation))
    }

    @Test
    fun PLLZTest() {
        val positionRepresentation =
            positionTransformer.transformStateToPositionRepresentation<com.example.cube_detection.casedetection.plldetection.PLLElementPosition>(context)

        val applyCase = com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase.Z.performPermutation(positionRepresentation)

        val expectedPositionRepresentation = arrayOf(
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    2,
                    Pair(0, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    10,
                    Pair(0, 1)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    6,
                    Pair(0, 2)
                ),
            ),
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    5,
                    Pair(1, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    6,
                    Pair(1, 2)
                ),
            ),
            arrayOf(
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    1,
                    Pair(2, 0)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.EDGE,
                    2,
                    Pair(2, 1)
                ),
                com.example.cube_detection.casedetection.plldetection.PLLElementPosition(
                    PieceType.CORNER,
                    5,
                    Pair(2, 2)
                ),
            ),
        )

        assert(applyCase.contentDeepEquals(expectedPositionRepresentation))
    }
}