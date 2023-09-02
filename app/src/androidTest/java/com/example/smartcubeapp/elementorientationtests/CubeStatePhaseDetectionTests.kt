package com.example.smartcubeapp.elementorientationtests

import android.content.Context
import com.example.smartcubeapp.cube.CubeState
import com.example.smartcubeapp.phasedetection.CubeStatePhaseDetection
import com.example.smartcubeapp.phasedetection.SolvePhase
import org.junit.Before
import org.junit.Test

class CubeStatePhaseDetectionTests {

    private lateinit var context: Context
    private lateinit var cubeStatePhaseDetection: CubeStatePhaseDetection

    @Before
    fun setup() {
        context = androidx.test.core.app.ApplicationProvider.getApplicationContext()
        cubeStatePhaseDetection = CubeStatePhaseDetection(CubeState.SOLVED_CUBE_STATE)
    }

    @Test
    fun stateCrossSolvedWhiteSideTest() {
        val cubeState = CubeState(
            mutableListOf(1, 2, 5, 7, 6, 3, 4, 0),
            mutableListOf(2, 3, 3, 1, 2, 1, 3, 3),
            mutableListOf(8, 9, 2, 4, 3, 5, 6, 7, 11, 1, 10, 0),
            mutableListOf(
                true, false, false, true, true, false, false, false, true, true, false, true
            )
        )

        cubeStatePhaseDetection.changeState(cubeState)
        val phase = cubeStatePhaseDetection.getFinishedPhaseForState(context)
        assert(phase == SolvePhase.Cross)
    }

    @Test
    fun stateCrossSolvedBlueSideTest() {
        val cubeState = CubeState(
            mutableListOf(2, 1, 4, 3, 5, 0, 6, 7),
            mutableListOf(2, 2, 1, 2, 3, 2, 1, 3),
            mutableListOf(1, 3, 4, 2, 6, 5, 0, 7, 8, 9, 10, 11),
            mutableListOf(
                true, true, true, true, true, false, true, false, false, false, false, false
            )
        )
        cubeStatePhaseDetection.changeState(cubeState)
        val phase = cubeStatePhaseDetection.getFinishedPhaseForState(context)
        assert(phase == SolvePhase.Cross)
    }

    @Test
    fun stateF2LSolvedYellowSideTest() {
        val cubeState = CubeState(
            mutableListOf(0, 5, 6, 3, 4, 1, 2, 7),
            mutableListOf(3, 1, 2, 3, 3, 3, 1, 3),
            mutableListOf(0, 1, 10, 3, 4, 6, 5, 7, 8, 9, 2, 11),
            mutableListOf(
                false, false, true, false, false, false, true, false, false, false, false, false
            )
        )
        cubeStatePhaseDetection.changeState(cubeState)
        val phase = cubeStatePhaseDetection.getFinishedPhaseForState(context)
        assert(phase == SolvePhase.F2L)
    }

    @Test
    fun stateF2LSolvedRedSideTest() {
        val cubeState = CubeState(
            mutableListOf(0, 1, 7, 6, 4, 5, 2, 3),
            mutableListOf(3, 3, 3, 3, 3, 3, 1, 1),
            mutableListOf(0, 1, 2, 6, 4, 5, 7, 11, 8, 9, 10, 3),
            mutableListOf(
                false, false, false, false, false, false, false, true, false, false, false, true
            )
        )
        cubeStatePhaseDetection.changeState(cubeState)
        val phase = cubeStatePhaseDetection.getFinishedPhaseForState(context)
        assert(phase == SolvePhase.F2L)
    }


    @Test
    fun stateOLLSolvedOrangeSideTest() {
        val cubeState = CubeState(
            mutableListOf(5, 0, 2, 3, 4, 1, 6, 7),
            mutableListOf(3, 1, 3, 3, 3, 1, 3, 3),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11),
            mutableListOf(
                false, false, false, false, false, false, false, false, false, false, false, false
            )
        )
        cubeStatePhaseDetection.changeState(cubeState)
        val phase = cubeStatePhaseDetection.getFinishedPhaseForState(context)
        assert(phase == SolvePhase.OLL)
    }

    @Test
    fun stateOLLSolvedGreenSideTest() {
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 4, 6, 5, 7),
            mutableListOf(3, 3, 3, 3, 3, 3, 3, 3),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 10, 9, 8, 11),
            mutableListOf(
                false, false, false, false, false, false, false, false, false, false, false, false
            )
        )
        cubeStatePhaseDetection.changeState(cubeState)
        val phase = cubeStatePhaseDetection.getFinishedPhaseForState(context)
        assert(phase == SolvePhase.OLL)
    }

    @Test
    fun stateCubeSolvedTest() {
        cubeStatePhaseDetection.changeState(CubeState.SOLVED_CUBE_STATE)
        val phase = cubeStatePhaseDetection.getFinishedPhaseForState(context)
        assert(phase == SolvePhase.Solved)
    }

}