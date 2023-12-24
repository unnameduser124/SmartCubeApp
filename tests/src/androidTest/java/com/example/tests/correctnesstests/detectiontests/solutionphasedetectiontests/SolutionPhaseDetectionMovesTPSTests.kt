package com.example.tests.correctnesstests.detectiontests.solutionphasedetectiontests

import android.content.Context
import com.example.cube_cube.cube.CubeState
import com.example.cube_detection.phasedetection.CubeStatePhaseDetection
import com.example.cube_detection.phasedetection.SolutionPhaseDetection
import com.example.cube_detection.phasedetection.SolvePhase
import com.example.cube_global.simpleTestSolve
import org.junit.Before
import org.junit.Test

class SolutionPhaseDetectionMovesTPSTests {

    private lateinit var context: Context
    private lateinit var solutionPhaseDetection: com.example.cube_detection.phasedetection.SolutionPhaseDetection
    private lateinit var cubeStatePhaseDetection: com.example.cube_detection.phasedetection.CubeStatePhaseDetection

    @Before
    fun setup() {
        context = androidx.test.core.app.ApplicationProvider.getApplicationContext()
        cubeStatePhaseDetection =
            com.example.cube_detection.phasedetection.CubeStatePhaseDetection(CubeState.SOLVED_CUBE_STATE)
        solutionPhaseDetection = com.example.cube_detection.phasedetection.SolutionPhaseDetection(
            simpleTestSolve(),
            cubeStatePhaseDetection
        )
    }

    @Test
    fun crossMoveCountTest() {
        val expected = 1
        val actual = solutionPhaseDetection.getPhaseMoveCount(com.example.cube_detection.phasedetection.SolvePhase.Cross, context)
        assert(expected == actual)
    }

    @Test
    fun crossTPSTest() {
        val expected = 10.0
        val actual = solutionPhaseDetection.getPhaseTPS(com.example.cube_detection.phasedetection.SolvePhase.Cross, context)
        assert(expected == actual)
    }

    @Suppress("TestFunctionName")
    @Test
    fun F2LMoveCountTest() {
        val expected = 3
        val actual = solutionPhaseDetection.getPhaseMoveCount(com.example.cube_detection.phasedetection.SolvePhase.F2L, context)
        assert(expected == actual)
    }

    @Suppress("TestFunctionName")
    @Test
    fun F2LTPSTest() {
        val expected = 10.0
        val actual = solutionPhaseDetection.getPhaseTPS(com.example.cube_detection.phasedetection.SolvePhase.F2L, context)
        assert(expected == actual)
    }

    @Suppress("TestFunctionName")
    @Test
    fun OLLMoveCountTest() {
        val expected = 6
        val actual = solutionPhaseDetection.getPhaseMoveCount(com.example.cube_detection.phasedetection.SolvePhase.OLL, context)
        assert(expected == actual)
    }

    @Suppress("TestFunctionName")
    @Test
    fun OLLTPSTest() {
        val expected = 10.0
        val actual = solutionPhaseDetection.getPhaseTPS(com.example.cube_detection.phasedetection.SolvePhase.OLL, context)
        assert(expected == actual)
    }

    @Suppress("TestFunctionName")
    @Test
    fun PLLMoveCountTest(){
        val expected = 0
        val actual = solutionPhaseDetection.getPhaseMoveCount(com.example.cube_detection.phasedetection.SolvePhase.PLL, context)
        assert(expected == actual)
    }

    @Suppress("TestFunctionName")
    @Test
    fun PLLTPSTest(){
        val expected = 0.0
        val actual = solutionPhaseDetection.getPhaseTPS(com.example.cube_detection.phasedetection.SolvePhase.PLL, context)
        assert(expected == actual)
    }

}