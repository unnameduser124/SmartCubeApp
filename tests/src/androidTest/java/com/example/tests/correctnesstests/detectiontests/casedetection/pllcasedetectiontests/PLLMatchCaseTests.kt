package com.example.tests.correctnesstests.detectiontests.casedetection.pllcasedetectiontests

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.example.cube_detection.casedetection.PositionRepresentationTransformer
import com.example.cube_detection.casedetection.plldetection.PLLCaseDetection
import com.example.cube_detection.casedetection.plldetection.PLLElementPosition
import com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase
import com.example.cube_cube.cube.BlueSide
import com.example.cube_cube.cube.CubeState
import com.example.cube_cube.cube.GreenSide
import com.example.cube_cube.cube.OrangeSide
import com.example.cube_cube.cube.RedSide
import com.example.cube_cube.cube.WhiteSide
import com.example.cube_cube.cube.YellowSide
import junit.framework.TestCase.fail
import org.junit.Before
import org.junit.Test


class PLLMatchCaseTests {

    private lateinit var context: Context
    private lateinit var pllCaseDetection: com.example.cube_detection.casedetection.plldetection.PLLCaseDetection

    @Before
    fun setup() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        pllCaseDetection = com.example.cube_detection.casedetection.plldetection.PLLCaseDetection(
            CubeState.SOLVED_CUBE_STATE,
            WhiteSide
        )
    }

    @Test
    fun matchCaseAaTest() {
        val cubeState = CubeState(
            mutableListOf(0, 6, 2, 3, 4, 1, 5, 7),
            mutableListOf(3, 3, 3, 3, 3, 2, 2, 3),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11),
            mutableListOf(
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false
            )
        )

        pllCaseDetection.changeCubeState(cubeState)
        pllCaseDetection.changeCubeSide(WhiteSide)
        val position = com.example.cube_detection.casedetection.PositionRepresentationTransformer(
            cubeState,
            WhiteSide
        )
            .transformStateToPositionRepresentation<com.example.cube_detection.casedetection.plldetection.PLLElementPosition>(context)

        val case = pllCaseDetection.matchCase(position)
        if (case == null) {
            fail()
        } else {
            assert(case == com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase.Aa)
        }
    }

    @Test
    fun matchCaseAbTest() {
        val cubeState = CubeState(
            mutableListOf(0, 5, 2, 3, 4, 6, 1, 7),
            mutableListOf(3, 2, 3, 3, 3, 2, 3, 3),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11),
            mutableListOf(
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false
            )
        )

        pllCaseDetection.changeCubeState(cubeState)
        pllCaseDetection.changeCubeSide(WhiteSide)
        val position = com.example.cube_detection.casedetection.PositionRepresentationTransformer(
            cubeState,
            WhiteSide
        )
            .transformStateToPositionRepresentation<com.example.cube_detection.casedetection.plldetection.PLLElementPosition>(context)

        val case = pllCaseDetection.matchCase(position)
        if (case == null) {
            fail()
        } else {
            assert(case == com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase.Ab)
        }
    }

    @Test
    fun matchCaseETest() {
        val cubeState = CubeState(
            mutableListOf(4, 1, 2, 7, 0, 5, 6, 3),
            mutableListOf(2, 3, 3, 2, 2, 3, 3, 2),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11),
            mutableListOf(
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false
            )
        )

        pllCaseDetection.changeCubeState(cubeState)
        pllCaseDetection.changeCubeSide(YellowSide)
        val position = com.example.cube_detection.casedetection.PositionRepresentationTransformer(
            cubeState,
            YellowSide
        )
            .transformStateToPositionRepresentation<com.example.cube_detection.casedetection.plldetection.PLLElementPosition>(context)

        val case = pllCaseDetection.matchCase(position)
        if (case == null) {
            fail()
        } else {
            assert(case == com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase.E)
        }
    }

    @Test
    fun matchCaseFTest() {
        val cubeState = CubeState(
            mutableListOf(3, 1, 2, 7, 4, 5, 6, 0),
            mutableListOf(2, 3, 3, 2, 3, 3, 3, 3),
            mutableListOf(7, 1, 2, 3, 8, 5, 6, 0, 4, 9, 10, 11),
            mutableListOf(
                true,
                false,
                false,
                false,
                true,
                false,
                false,
                true,
                true,
                false,
                false,
                false
            )
        )

        pllCaseDetection.changeCubeState(cubeState)
        pllCaseDetection.changeCubeSide(YellowSide)
        val position = com.example.cube_detection.casedetection.PositionRepresentationTransformer(
            cubeState,
            YellowSide
        )
            .transformStateToPositionRepresentation<com.example.cube_detection.casedetection.plldetection.PLLElementPosition>(context)

        val case = pllCaseDetection.matchCase(position)
        if (case == null) {
            fail()
        } else {
            assert(case == com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase.F)
        }
    }

    @Test
    fun matchCaseGaTest() {
        val cubeState = CubeState(
            mutableListOf(0, 5, 2, 3, 4, 1, 6, 7),
            mutableListOf(3, 1, 3, 3, 3, 1, 3, 3),
            mutableListOf(0, 9, 2, 3, 1, 4, 6, 7, 8, 5, 10, 11),
            mutableListOf(
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false
            )
        )

        pllCaseDetection.changeCubeState(cubeState)
        pllCaseDetection.changeCubeSide(RedSide)
        val position = com.example.cube_detection.casedetection.PositionRepresentationTransformer(
            cubeState,
            RedSide
        )
            .transformStateToPositionRepresentation<com.example.cube_detection.casedetection.plldetection.PLLElementPosition>(context)

        val case = pllCaseDetection.matchCase(position)
        if (case == null) {
            fail()
        } else {
            assert(case == com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase.Ga)
        }
    }

    @Test
    fun matchCaseGbTest() {
        val cubeState = CubeState(
            mutableListOf(4, 0, 2, 3, 1, 5, 6, 7),
            mutableListOf(1, 1, 3, 3, 3, 3, 3, 3),
            mutableListOf(0, 5, 3, 2, 1, 4, 6, 7, 8, 9, 10, 11),
            mutableListOf(
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false
            )
        )

        pllCaseDetection.changeCubeState(cubeState)
        pllCaseDetection.changeCubeSide(RedSide)
        val position = com.example.cube_detection.casedetection.PositionRepresentationTransformer(
            cubeState,
            RedSide
        )
            .transformStateToPositionRepresentation<com.example.cube_detection.casedetection.plldetection.PLLElementPosition>(context)

        val case = pllCaseDetection.matchCase(position)
        if (case == null) {
            fail()
        } else {
            assert(case == com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase.Gb)
        }
    }

    @Test
    fun matchCaseGcTest() {
        val cubeState = CubeState(
            mutableListOf(0, 1, 6, 2, 4, 5, 3, 7),
            mutableListOf(3, 3, 1, 1, 3, 3, 3, 3),
            mutableListOf(0, 1, 2, 11, 4, 5, 3, 7, 8, 9, 10, 6),
            mutableListOf(
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false
            )
        )

        pllCaseDetection.changeCubeState(cubeState)
        pllCaseDetection.changeCubeSide(OrangeSide)
        val position = com.example.cube_detection.casedetection.PositionRepresentationTransformer(
            cubeState,
            OrangeSide
        )
            .transformStateToPositionRepresentation<com.example.cube_detection.casedetection.plldetection.PLLElementPosition>(context)

        val case = pllCaseDetection.matchCase(position)
        if (case == null) {
            fail()
        } else {
            assert(case == com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase.Gc)
        }
    }

    @Test
    fun matchCaseGdTest() {
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 7, 4, 5, 3, 6),
            mutableListOf(3, 3, 3, 1, 3, 3, 3, 1),
            mutableListOf(0, 1, 2, 11, 4, 5, 6, 3, 8, 9, 10, 7),
            mutableListOf(
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false
            )
        )

        pllCaseDetection.changeCubeState(cubeState)
        pllCaseDetection.changeCubeSide(OrangeSide)
        val position = com.example.cube_detection.casedetection.PositionRepresentationTransformer(
            cubeState,
            OrangeSide
        )
            .transformStateToPositionRepresentation<com.example.cube_detection.casedetection.plldetection.PLLElementPosition>(context)

        val case = pllCaseDetection.matchCase(position)
        if (case == null) {
            fail()
        } else {
            assert(case == com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase.Gd)
        }
    }

    @Test
    fun matchCaseHTest() {
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 7, 4, 5, 6),
            mutableListOf(3, 3, 3, 3, 3, 3, 3, 3),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 9, 10, 11, 8),
            mutableListOf(
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false
            )
        )

        pllCaseDetection.changeCubeState(cubeState)
        pllCaseDetection.changeCubeSide(BlueSide)
        val position = com.example.cube_detection.casedetection.PositionRepresentationTransformer(
            cubeState,
            BlueSide
        )
            .transformStateToPositionRepresentation<com.example.cube_detection.casedetection.plldetection.PLLElementPosition>(context)

        val case = pllCaseDetection.matchCase(position)
        if (case == null) {
            fail()
        } else {
            assert(case == com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase.H)
        }
    }

    @Test
    fun matchCaseJaTest() {
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 4, 5, 7, 6),
            mutableListOf(3, 3, 3, 3, 3, 3, 3, 3),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 10),
            mutableListOf(
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false
            )
        )

        pllCaseDetection.changeCubeState(cubeState)
        pllCaseDetection.changeCubeSide(BlueSide)
        val position = com.example.cube_detection.casedetection.PositionRepresentationTransformer(
            cubeState,
            BlueSide
        )
            .transformStateToPositionRepresentation<com.example.cube_detection.casedetection.plldetection.PLLElementPosition>(context)

        val case = pllCaseDetection.matchCase(position)
        if (case == null) {
            fail()
        } else {
            assert(case == com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase.Ja)
        }
    }

    @Test
    fun matchCaseJbTest() {
        val cubeState = CubeState(
            mutableListOf(3, 1, 0, 2, 4, 5, 6, 7),
            mutableListOf(3, 3, 3, 3, 3, 3, 3, 3),
            mutableListOf(3, 1, 0, 2, 4, 5, 6, 7, 8, 9, 10, 11),
            mutableListOf(
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false
            )
        )

        pllCaseDetection.changeCubeState(cubeState)
        pllCaseDetection.changeCubeSide(GreenSide)
        val position = com.example.cube_detection.casedetection.PositionRepresentationTransformer(
            cubeState,
            GreenSide
        )
            .transformStateToPositionRepresentation<com.example.cube_detection.casedetection.plldetection.PLLElementPosition>(context)

        val case = pllCaseDetection.matchCase(position)
        if (case == null) {
            fail()
        } else {
            assert(case == com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase.Jb)
        }
    }

    @Test
    fun matchCaseNaTest() {
        val cubeState = CubeState(
            mutableListOf(0, 3, 2, 1, 4, 5, 6, 7),
            mutableListOf(3, 3, 3, 3, 3, 3, 3, 3),
            mutableListOf(0, 3, 2, 1, 4, 5, 6, 7, 8, 9, 10, 11),
            mutableListOf(
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false
            )
        )

        pllCaseDetection.changeCubeState(cubeState)
        pllCaseDetection.changeCubeSide(GreenSide)
        val position = com.example.cube_detection.casedetection.PositionRepresentationTransformer(
            cubeState,
            GreenSide
        )
            .transformStateToPositionRepresentation<com.example.cube_detection.casedetection.plldetection.PLLElementPosition>(context)

        val case = pllCaseDetection.matchCase(position)
        if (case == null) {
            fail()
        } else {
            assert(case == com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase.Na)
        }
    }

    @Test
    fun matchCaseNbTest() {
        val cubeState = CubeState(
            mutableListOf(0, 2, 1, 3, 4, 6, 5, 7),
            mutableListOf(3, 2, 2, 3, 3, 2, 2, 3),
            mutableListOf(0, 1, 5, 3, 4, 2, 10, 7, 8, 9, 6, 11),
            mutableListOf(
                false,
                false,
                true,
                false,
                false,
                true,
                true,
                false,
                false,
                false,
                true,
                false
            )
        )

        pllCaseDetection.changeCubeState(cubeState)
        pllCaseDetection.changeCubeSide(WhiteSide)
        val position = com.example.cube_detection.casedetection.PositionRepresentationTransformer(
            cubeState,
            WhiteSide
        )
            .transformStateToPositionRepresentation<com.example.cube_detection.casedetection.plldetection.PLLElementPosition>(context)

        val case = pllCaseDetection.matchCase(position)
        if (case == null) {
            fail()
        } else {
            assert(case == com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase.Nb)
        }
    }

    @Test
    fun matchCaseRaTest() {
        val cubeState = CubeState(
            mutableListOf(4, 1, 2, 3, 7, 5, 6, 0),
            mutableListOf(2, 3, 3, 3, 2, 3, 3, 3),
            mutableListOf(4, 1, 2, 3, 7, 5, 6, 0, 8, 9, 10, 11),
            mutableListOf(
                true,
                false,
                false,
                false,
                false,
                false,
                false,
                true,
                false,
                false,
                false,
                false
            )
        )

        pllCaseDetection.changeCubeState(cubeState)
        pllCaseDetection.changeCubeSide(YellowSide)
        val position = com.example.cube_detection.casedetection.PositionRepresentationTransformer(
            cubeState,
            YellowSide
        )
            .transformStateToPositionRepresentation<com.example.cube_detection.casedetection.plldetection.PLLElementPosition>(context)

        val case = pllCaseDetection.matchCase(position)
        if (case == null) {
            fail()
        } else {
            assert(case == com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase.Ra)
        }
    }

    @Test
    fun matchCaseRbTest() {
        val cubeState = CubeState(
            mutableListOf(0, 4, 2, 3, 5, 1, 6, 7),
            mutableListOf(3, 3, 3, 3, 1, 1, 3, 3),
            mutableListOf(0, 4, 2, 3, 9, 5, 6, 7, 8, 1, 10, 11),
            mutableListOf(
                true,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false
            )
        )

        pllCaseDetection.changeCubeState(cubeState)
        pllCaseDetection.changeCubeSide(RedSide)
        val position = com.example.cube_detection.casedetection.PositionRepresentationTransformer(
            cubeState,
            RedSide
        )
            .transformStateToPositionRepresentation<com.example.cube_detection.casedetection.plldetection.PLLElementPosition>(context)

        val case = pllCaseDetection.matchCase(position)
        if (case == null) {
            fail()
        } else {
            assert(case == com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase.Rb)
        }
    }

    @Test
    fun matchCaseTTest() {
        val cubeState = CubeState(
            mutableListOf(0, 1, 3, 2, 4, 5, 6, 7),
            mutableListOf(3, 3, 1, 1, 3, 3, 3, 3),
            mutableListOf(0, 1, 2, 11, 4, 5, 6, 7, 8, 9, 10, 3),
            mutableListOf(
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false
            )
        )

        pllCaseDetection.changeCubeState(cubeState)
        pllCaseDetection.changeCubeSide(OrangeSide)
        val position = com.example.cube_detection.casedetection.PositionRepresentationTransformer(
            cubeState,
            OrangeSide
        )
            .transformStateToPositionRepresentation<com.example.cube_detection.casedetection.plldetection.PLLElementPosition>(context)

        val case = pllCaseDetection.matchCase(position)
        if (case == null) {
            fail()
        } else {
            assert(case == com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase.T)
        }
    }

    @Test
    fun matchCaseUaTest() {
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 5, 6, 7, 4),
            mutableListOf(3, 3, 3, 3, 3, 3, 3, 3),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 10, 9, 11),
            mutableListOf(
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false
            )
        )

        pllCaseDetection.changeCubeState(cubeState)
        pllCaseDetection.changeCubeSide(BlueSide)
        val position = com.example.cube_detection.casedetection.PositionRepresentationTransformer(
            cubeState,
            BlueSide
        )
            .transformStateToPositionRepresentation<com.example.cube_detection.casedetection.plldetection.PLLElementPosition>(context)

        val case = pllCaseDetection.matchCase(position)
        if (case == null) {
            fail()
        } else {
            assert(case == com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase.Ua)
        }
    }

    @Test
    fun matchCaseUbTest() {
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7),
            mutableListOf(3, 3, 3, 3, 3, 3, 3, 3),
            mutableListOf(0, 3, 1, 2, 4, 5, 6, 7, 8, 9, 10, 11),
            mutableListOf(
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false
            )
        )

        pllCaseDetection.changeCubeState(cubeState)
        pllCaseDetection.changeCubeSide(GreenSide)
        val position = com.example.cube_detection.casedetection.PositionRepresentationTransformer(
            cubeState,
            GreenSide
        )
            .transformStateToPositionRepresentation<com.example.cube_detection.casedetection.plldetection.PLLElementPosition>(context)

        val case = pllCaseDetection.matchCase(position)
        if (case == null) {
            fail()
        } else {
            assert(case == com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase.Ub)
        }
    }

    @Test
    fun matchCaseVTest() {
        val cubeState = CubeState(
            mutableListOf(0, 2, 1, 3, 4, 6, 5, 7),
            mutableListOf(3, 2, 2, 3, 3, 2, 2, 3),
            mutableListOf(0, 1, 2, 3, 4, 6, 10, 7, 8, 9, 5, 11),
            mutableListOf(
                false,
                false,
                false,
                false,
                false,
                false,
                true,
                false,
                false,
                false,
                true,
                false
            )
        )

        pllCaseDetection.changeCubeState(cubeState)
        pllCaseDetection.changeCubeSide(WhiteSide)
        val position = com.example.cube_detection.casedetection.PositionRepresentationTransformer(
            cubeState,
            WhiteSide
        )
            .transformStateToPositionRepresentation<com.example.cube_detection.casedetection.plldetection.PLLElementPosition>(context)

        val case = pllCaseDetection.matchCase(position)
        if (case == null) {
            fail()
        } else {
            assert(case == com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase.V)
        }
    }

    @Test
    fun matchCaseYTest() {
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 4, 3, 5, 6, 7),
            mutableListOf(3, 3, 3, 3, 3, 3, 3, 3),
            mutableListOf(0, 1, 2, 3, 8, 5, 6, 7, 4, 9, 10, 11),
            mutableListOf(
                false,
                false,
                false,
                false,
                true,
                false,
                false,
                false,
                true,
                false,
                false,
                false
            )
        )

        pllCaseDetection.changeCubeState(cubeState)
        pllCaseDetection.changeCubeSide(YellowSide)
        val position = com.example.cube_detection.casedetection.PositionRepresentationTransformer(
            cubeState,
            YellowSide
        )
            .transformStateToPositionRepresentation<com.example.cube_detection.casedetection.plldetection.PLLElementPosition>(context)

        val case = pllCaseDetection.matchCase(position)
        if (case == null) {
            fail()
        } else {
            assert(case == com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase.Y)
        }
    }

    @Test
    fun matchCaseZTest() {
        val cubeState = CubeState(
            mutableListOf(1, 5, 2, 3, 0, 4, 6, 7),
            mutableListOf(1, 1, 3, 3, 1, 1, 3, 3),
            mutableListOf(0, 1, 2, 3, 5, 4, 6, 7, 8, 9, 10, 11),
            mutableListOf(
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false
            )
        )

        pllCaseDetection.changeCubeState(cubeState)
        pllCaseDetection.changeCubeSide(RedSide)
        val position = com.example.cube_detection.casedetection.PositionRepresentationTransformer(
            cubeState,
            RedSide
        )
            .transformStateToPositionRepresentation<com.example.cube_detection.casedetection.plldetection.PLLElementPosition>(context)

        val case = pllCaseDetection.matchCase(position)
        if (case == null) {
            fail()
        } else {
            assert(case == com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase.Z)
        }
    }
}