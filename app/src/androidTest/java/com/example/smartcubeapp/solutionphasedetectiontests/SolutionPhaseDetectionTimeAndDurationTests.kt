package com.example.smartcubeapp.solutionphasedetectiontests

import android.content.Context
import com.example.smartcubeapp.cube.CubeState
import com.example.smartcubeapp.cube.Move
import com.example.smartcubeapp.cube.Solve
import com.example.smartcubeapp.phasedetection.CubeStatePhaseDetection
import com.example.smartcubeapp.phasedetection.SolutionPhaseDetection
import com.example.smartcubeapp.phasedetection.SolvePhase
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
        solutionPhaseDetection = SolutionPhaseDetection(simpleSolution(), cubeStatePhaseDetection)
    }

    @Test
    fun crossSolvedIndexTest() {
        val expected = 1
        val actual = solutionPhaseDetection.crossSolvedIndex()
        assertEquals(expected, actual)
    }

    @Test
    fun F2LSolvedIndexTest() {
        val expected = 4
        val actual = solutionPhaseDetection.F2LSolvedIndex()
        assertEquals(expected, actual)
    }

    @Test
    fun OLLSolvedIndexTest(){
        val expected = 10
        val actual = solutionPhaseDetection.OLLSolvedIndex()
        assertEquals(expected, actual)
    }

    @Test
    fun cubeSolvedIndexTest(){
        val expected = 10
        val actual = solutionPhaseDetection.cubeSolvedIndex()
        assertEquals(expected, actual)
    }

    @Test
    fun crossStartTimeTest(){
        val expected = 0L
        val actual = solutionPhaseDetection.getStartTimeForPhase(SolvePhase.Cross)
        assertEquals(expected, actual)
    }

    @Test
    fun crossEndTimeTest(){
        val expected = 100L
        val actual = solutionPhaseDetection.getEndTimeForPhase(SolvePhase.Cross)
        assertEquals(expected, actual)
    }

    @Test
    fun crossDurationTest(){
        val expected = 100.0
        val actual = solutionPhaseDetection.getPhaseDurationInSeconds(SolvePhase.Cross)
        assertEquals(expected, actual)
    }

    @Test
    fun F2LStartTimeTest(){
        val expected = 100L
        val actual = solutionPhaseDetection.getStartTimeForPhase(SolvePhase.F2L)
        assertEquals(expected, actual)
    }

    @Test
    fun F2LEndTimeTest(){
        val expected = 400L
        val actual = solutionPhaseDetection.getEndTimeForPhase(SolvePhase.F2L)
        assertEquals(expected, actual)
    }

    @Test
    fun F2LDurationTest(){
        val expected = 300.0
        val actual = solutionPhaseDetection.getPhaseDurationInSeconds(SolvePhase.F2L)
        assertEquals(expected, actual)
    }

    @Test
    fun OLLStartTimeTest(){
        val expected = 400L
        val actual = solutionPhaseDetection.getStartTimeForPhase(SolvePhase.OLL)
        assertEquals(expected, actual)
    }

    @Test
    fun OLLEndTimeTest(){
        val expected = 1000L
        val actual = solutionPhaseDetection.getEndTimeForPhase(SolvePhase.OLL)
        assertEquals(expected, actual)
    }

    @Test
    fun OLLDurationTest(){
        val expected = 600.0
        val actual = solutionPhaseDetection.getPhaseDurationInSeconds(SolvePhase.OLL)
        assertEquals(expected, actual)
    }

    @Test
    fun cubeSolvedTime(){
        val expected = 1000L
        val actual = solutionPhaseDetection.getStartTimeForPhase(SolvePhase.PLL)
        assertEquals(expected, actual)
    }


    private fun simpleSolution(): Solve {
        return Solve(
            solveStateSequence = mutableListOf(
                //scrambled
                CubeState(
                    mutableListOf(2, 1, 7, 4, 3, 5, 0, 6),
                    mutableListOf(1, 3, 3, 1, 3, 3, 1, 1),
                    mutableListOf(3, 1, 2, 0, 8, 5, 7, 11, 4, 9, 10, 6),
                    mutableListOf(
                        true, false, false, false, false, false, false, false, true, false, false, false
                    ),
                    Move(),
                    0
                ),
                //cross solved
                CubeState(
                    mutableListOf(2, 1, 0, 7, 3, 5, 6, 4),
                    mutableListOf(1, 3, 3, 1, 3, 3, 3, 3),
                    mutableListOf(3, 1, 2, 7, 8, 5, 6, 0, 4, 9, 10, 11),
                    mutableListOf(
                        true, false, false, false, false, false, false, false, true, false, false, false
                    ),
                    Move(),
                    100
                ),
                //start of F2L
                CubeState(
                    mutableListOf(2, 1, 6, 0, 3, 5, 4, 7),
                    mutableListOf(1, 3, 1, 1, 3, 3, 1, 3),
                    mutableListOf(3, 1, 2, 6, 8, 5, 11, 7, 4, 9, 10, 0),
                    mutableListOf(
                        true, false, false, false, false, false, false, false, true, false, false, false
                    ),
                    Move(),
                    200
                ),
                CubeState(
                    mutableListOf(3, 1, 6, 2, 7, 5, 4, 0),
                    mutableListOf(2, 3, 1, 1, 2, 3, 1, 1),
                    mutableListOf(8, 1, 2, 6, 4, 5, 11, 3, 7, 9, 10, 0),
                    mutableListOf(
                        true, false, false, false, false, false, false, false, true, false, false, false
                    ),
                    Move(),
                    300
                ),
                //F2L solved
                CubeState(
                    mutableListOf(3, 1, 2, 0, 7, 5, 6, 4),
                    mutableListOf(2, 3, 3, 3, 2, 3, 3, 3),
                    mutableListOf(8, 1, 2, 3, 4, 5, 6, 0, 7, 9, 10, 11),
                    mutableListOf(
                        true, false, false, false, false, false, false, false, true, false, false, false
                    ),
                    Move(),
                    400
                ),
                //start of OLL
                CubeState(
                    mutableListOf(1, 2, 0, 3, 7, 5, 6, 4),
                    mutableListOf(3, 3, 3, 1, 2, 3, 3, 3),
                    mutableListOf(1, 2, 3, 8, 4, 5, 6, 0, 7, 9, 10, 11),
                    mutableListOf(
                        false, false, false, true, false, false, false, false, true, false, false, false
                    ),
                    Move(),
                    500
                ),
                CubeState(
                    mutableListOf(3, 2, 0, 4, 1, 5, 6, 7),
                    mutableListOf(1, 3, 3, 2, 2, 3, 3, 3),
                    mutableListOf(0, 2, 3, 8, 1, 5, 6, 7, 4, 9, 10, 11),
                    mutableListOf(
                        true, false, false, true, true, false, false, false, true, false, false, false
                    ),
                    Move(),
                    600
                ),
                CubeState(
                    mutableListOf(3, 2, 6, 0, 1, 5, 7, 4),
                    mutableListOf(1, 3, 1, 1, 2, 3, 1, 2),
                    mutableListOf(0, 2, 3, 6, 1,  5, 11, 8, 4, 9, 10, 7),
                    mutableListOf(
                        true, false, false, false, true, false, false, true, true, false, false, false
                    ),
                    Move(),
                    700
                ),
                CubeState(
                    mutableListOf(1, 2, 6, 3, 4, 5, 7, 0),
                    mutableListOf(3, 3, 1, 1, 3, 3, 1, 1),
                    mutableListOf(1, 2, 3, 6, 4, 5, 11, 0, 8, 9, 10, 7),
                    mutableListOf(
                        false, false, false, false, false, false, false, false, false, false, false, false
                    ),
                    Move(),
                    800
                ),
                CubeState(
                    mutableListOf(1, 2, 3, 0, 4, 5, 6, 7),
                    mutableListOf(3, 3, 3, 3, 3, 3, 3, 3),
                    mutableListOf(1, 2, 3, 0, 4, 5, 6, 7, 8, 9, 10, 11),
                    mutableListOf(
                        false, false, false, false, false, false, false, false, false, false, false, false
                    ),
                    Move(),
                    900
                ),
                //OLL solved, cube solved
                CubeState(
                    CubeState.SOLVED_CUBE_STATE.cornerPositions,
                    CubeState.SOLVED_CUBE_STATE.cornerOrientations,
                    CubeState.SOLVED_CUBE_STATE.edgePositions,
                    CubeState.SOLVED_CUBE_STATE.edgeOrientations,
                    Move(),
                    1000
                ),
            )
        )
    }
}