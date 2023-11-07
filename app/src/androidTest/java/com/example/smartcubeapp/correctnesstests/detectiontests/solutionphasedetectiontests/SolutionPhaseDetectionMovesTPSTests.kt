package com.example.smartcubeapp.correctnesstests.detectiontests.solutionphasedetectiontests

import android.content.Context
import com.example.smartcubeapp.cube.CubeState
import com.example.smartcubeapp.phasedetection.CubeStatePhaseDetection
import com.example.smartcubeapp.phasedetection.SolutionPhaseDetection
import com.example.smartcubeapp.phasedetection.SolvePhase
import com.example.smartcubeapp.simpleTestSolve
import org.junit.Before
import org.junit.Test

class SolutionPhaseDetectionMovesTPSTests {

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
    fun crossMoveCountTest() {
        val expected = 1
        val actual = solutionPhaseDetection.getPhaseMoveCount(SolvePhase.Cross, context)
        assert(expected == actual)
    }

    @Test
    fun crossTPSTest() {
        val expected = 10.0
        val actual = solutionPhaseDetection.getPhaseTPS(SolvePhase.Cross, context)
        assert(expected == actual)
    }

    @Suppress("TestFunctionName")
    @Test
    fun F2LMoveCountTest() {
        val expected = 3
        val actual = solutionPhaseDetection.getPhaseMoveCount(SolvePhase.F2L, context)
        assert(expected == actual)
    }

    @Suppress("TestFunctionName")
    @Test
    fun F2LTPSTest() {
        val expected = 10.0
        val actual = solutionPhaseDetection.getPhaseTPS(SolvePhase.F2L, context)
        assert(expected == actual)
    }

    @Suppress("TestFunctionName")
    @Test
    fun OLLMoveCountTest() {
        val expected = 6
        val actual = solutionPhaseDetection.getPhaseMoveCount(SolvePhase.OLL, context)
        assert(expected == actual)
    }

    @Suppress("TestFunctionName")
    @Test
    fun OLLTPSTest() {
        val expected = 10.0
        val actual = solutionPhaseDetection.getPhaseTPS(SolvePhase.OLL, context)
        assert(expected == actual)
    }

    @Suppress("TestFunctionName")
    @Test
    fun PLLMoveCountTest(){
        val expected = 0
        val actual = solutionPhaseDetection.getPhaseMoveCount(SolvePhase.PLL, context)
        assert(expected == actual)
    }

    @Suppress("TestFunctionName")
    @Test
    fun PLLTPSTest(){
        val expected = 0.0
        val actual = solutionPhaseDetection.getPhaseTPS(SolvePhase.PLL, context)
        assert(expected == actual)
    }

}