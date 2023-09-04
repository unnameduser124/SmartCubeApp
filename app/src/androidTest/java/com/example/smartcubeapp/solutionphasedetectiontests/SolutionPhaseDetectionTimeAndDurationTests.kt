package com.example.smartcubeapp.solutionphasedetectiontests

import android.content.Context
import com.example.smartcubeapp.cube.CubeState
import com.example.smartcubeapp.cube.WhiteSide
import com.example.smartcubeapp.cube.YellowSide
import com.example.smartcubeapp.phasedetection.CubeStatePhaseDetection
import com.example.smartcubeapp.phasedetection.SolutionPhaseDetection
import com.example.smartcubeapp.phasedetection.SolvePhase
import com.example.smartcubeapp.simpleTestSolve
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class SolutionPhaseDetectionIndexTimeAndDurationTests {

    private lateinit var context: Context
    private lateinit var solutionPhaseDetection: SolutionPhaseDetection
    private lateinit var cubeStatePhaseDetection: CubeStatePhaseDetection

    @Before
    fun setup() {
        context = androidx.test.core.app.ApplicationProvider.getApplicationContext()
        cubeStatePhaseDetection = CubeStatePhaseDetection(CubeState.SOLVED_CUBE_STATE)
        solutionPhaseDetection = SolutionPhaseDetection(simpleTestSolve(), cubeStatePhaseDetection)
    }

    @Test
    fun getCrossSideTest(){
        val expected = WhiteSide
        solutionPhaseDetection.setCrossSide()
        val actual = solutionPhaseDetection.getCrossSide()
        assertEquals(expected, actual)
    }

    @Test
    fun getCrossOppositeSideTest(){
        val expected = YellowSide
        solutionPhaseDetection.setCrossSide()
        val actual = solutionPhaseDetection.getCrossOppositeSide()
        assertEquals(expected, actual)
    }

    @Test
    fun getStartIndexForCrossPhaseTest(){
        val expected = 0
        val actual = solutionPhaseDetection.getStartIndexForPhase(SolvePhase.Cross, context)
        assertEquals(expected, actual)
    }

    @Test
    fun getStartIndexForF2LPhaseTest(){
        val expected = 1
        val actual = solutionPhaseDetection.getStartIndexForPhase(SolvePhase.F2L, context)
        assertEquals(expected, actual)
    }

    @Test
    fun getStartIndexForOLLPhaseTest(){
        val expected = 4
        val actual = solutionPhaseDetection.getStartIndexForPhase(SolvePhase.OLL, context)
        assertEquals(expected, actual)
    }

    @Test
    fun getStartIndexForPLLPhaseTest(){
        val expected = 10
        val actual = solutionPhaseDetection.getStartIndexForPhase(SolvePhase.PLL, context)
        assertEquals(expected, actual)
    }

    @Test
    fun getEndIndexForCrossPhaseTest(){
        val expected = 1
        val actual = solutionPhaseDetection.getEndIndexForPhase(SolvePhase.Cross, context)
        assertEquals(expected, actual)
    }

    @Test
    fun getEndIndexForF2LPhaseTest(){
        val expected = 4
        val actual = solutionPhaseDetection.getEndIndexForPhase(SolvePhase.F2L, context)
        assertEquals(expected, actual)
    }

    @Test
    fun getEndIndexForOLLPhaseTest(){
        val expected = 10
        val actual = solutionPhaseDetection.getEndIndexForPhase(SolvePhase.OLL, context)
        assertEquals(expected, actual)
    }

    @Test
    fun getEndIndexForPLLPhaseTest(){
        val expected = 10
        val actual = solutionPhaseDetection.getEndIndexForPhase(SolvePhase.PLL, context)
        assertEquals(expected, actual)
    }


    @Test
    fun crossStartTimeTest(){
        val expected = 0L
        val actual = solutionPhaseDetection.getStartTimeForPhase(SolvePhase.Cross, context)
        assertEquals(expected, actual)
    }

    @Test
    fun crossEndTimeTest(){
        val expected = 100L
        val actual = solutionPhaseDetection.getEndTimeForPhase(SolvePhase.Cross, context)
        assertEquals(expected, actual)
    }

    @Test
    fun crossDurationTest(){
        val expected = 0.1
        val actual = solutionPhaseDetection.getPhaseDurationInSeconds(SolvePhase.Cross, context)
        assertEquals(expected, actual)
    }

    @Test
    fun F2LStartTimeTest(){
        val expected = 100L
        val actual = solutionPhaseDetection.getStartTimeForPhase(SolvePhase.F2L, context)
        assertEquals(expected, actual)
    }

    @Test
    fun F2LEndTimeTest(){
        val expected = 400L
        val actual = solutionPhaseDetection.getEndTimeForPhase(SolvePhase.F2L, context)
        assertEquals(expected, actual)
    }

    @Test
    fun F2LDurationTest(){
        val expected = 0.3
        val actual = solutionPhaseDetection.getPhaseDurationInSeconds(SolvePhase.F2L, context)
        assertEquals(expected, actual)
    }

    @Test
    fun OLLStartTimeTest(){
        val expected = 400L
        val actual = solutionPhaseDetection.getStartTimeForPhase(SolvePhase.OLL, context)
        assertEquals(expected, actual)
    }

    @Test
    fun OLLEndTimeTest(){
        val expected = 1000L
        val actual = solutionPhaseDetection.getEndTimeForPhase(SolvePhase.OLL, context)
        assertEquals(expected, actual)
    }

    @Test
    fun OLLDurationTest(){
        val expected = 0.6
        val actual = solutionPhaseDetection.getPhaseDurationInSeconds(SolvePhase.OLL, context)
        assertEquals(expected, actual)
    }

    @Test
    fun cubeSolvedTime(){
        val expected = 1000L
        val actual = solutionPhaseDetection.getStartTimeForPhase(SolvePhase.PLL, context)
        assertEquals(expected, actual)
    }
}