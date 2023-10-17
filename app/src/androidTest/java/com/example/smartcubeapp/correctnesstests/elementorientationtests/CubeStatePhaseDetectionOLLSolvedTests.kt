package com.example.smartcubeapp.correctnesstests.elementorientationtests

import android.content.Context
import com.example.smartcubeapp.cube.CubeState
import com.example.smartcubeapp.phasedetection.CubeStatePhaseDetection
import org.junit.Before
import org.junit.Test

class CubeStatePhaseDetectionOLLSolvedTests {

    private lateinit var context: Context

    @Before
    fun setup(){
        context = androidx.test.core.app.ApplicationProvider.getApplicationContext()
    }

    @Test
    fun OLLSolvedWhiteSide(){
        val cubeState = CubeState(
            cornerPositions = mutableListOf(0, 5, 2, 3, 4, 6, 1, 7),
            cornerOrientations = mutableListOf(3, 2, 3, 3, 3, 2, 3, 3),
            edgePositions = mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11),
            edgeOrientations = mutableListOf(false, false, false, false, false, false, false, false, false, false, false, false)
        )

        val cubeStatePhaseDetection = CubeStatePhaseDetection(cubeState)
        val OLLSolved = cubeStatePhaseDetection.OLLSolved(context)
        assert(OLLSolved)
    }

    @Test
    fun OLLNotSolvedYellowSideTest(){
        val cubeState = CubeState(
            cornerPositions = mutableListOf(7, 1, 2, 3, 0, 5, 6, 4),
            cornerOrientations = mutableListOf(2, 3, 3, 3, 2, 3, 3),
            edgePositions = mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11),
            edgeOrientations = mutableListOf(false, false, false, false, false, false, false, false, false, false, false, false)
        )

        val cubeStatePhaseDetection = CubeStatePhaseDetection(cubeState)
        val OLLSolved = cubeStatePhaseDetection.OLLSolved(context)
        assert(!OLLSolved)
    }

    @Test
    fun OLLSolvedBlueSideTest(){
        val cubeState = CubeState(
            cornerPositions = mutableListOf(0, 1, 2, 3, 7, 5, 6, 4),
            cornerOrientations = mutableListOf(3, 3, 3, 3, 3, 3, 3, 3),
            edgePositions = mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 9, 10, 11, 8),
            edgeOrientations = mutableListOf(false, false, false, false, false, false, false, false, false, false, false, false)
        )

        val cubeStatePhaseDetection = CubeStatePhaseDetection(cubeState)
        val OLLSolved = cubeStatePhaseDetection.OLLSolved(context)
        assert(OLLSolved)
    }

    @Test
    fun OLLNotSolvedGreenSideTest(){
        val cubeState = CubeState(
            cornerPositions = mutableListOf(3, 0, 1, 2, 4, 5, 6, 7),
            cornerOrientations = mutableListOf(3, 3, 3, 3, 3, 3, 3, 3),
            edgePositions = mutableListOf(1, 2, 3, 0, 4, 5, 6, 7, 9, 8, 10, 11),
            edgeOrientations = mutableListOf(true, true, true, true, false, false, false, false, false, false, false, false)
        )

        val cubeStatePhaseDetection = CubeStatePhaseDetection(cubeState)
        val OLLSolved = cubeStatePhaseDetection.OLLSolved(context)
        assert(!OLLSolved)
    }

    @Test
    fun OLLSolvedRedSideTest(){
        val cubeState = CubeState(
            cornerPositions = mutableListOf(0, 1, 2, 3, 4, 5, 6, 7),
            cornerOrientations = mutableListOf(3, 3, 3, 3, 3, 3, 3, 3),
            edgePositions = mutableListOf(0, 9, 2, 3, 5, 4, 6, 7, 8, 1, 10, 11),
            edgeOrientations = mutableListOf(false, false, false, false, false, false, false, false, false, false, false, false)
        )

        val cubeStatePhaseDetection = CubeStatePhaseDetection(cubeState)
        val OLLSolved = cubeStatePhaseDetection.OLLSolved(context)
        assert(OLLSolved)
    }

    @Test
    fun OLLNotSolvedOrangeSideTest(){
        val cubeState = CubeState(
            cornerPositions = mutableListOf(0, 1, 2, 3, 4, 5, 6, 7),
            cornerOrientations = mutableListOf(3, 3, 1, 1, 3, 3, 1, 1),
            edgePositions = mutableListOf(0, 1, 2, 11, 4, 5, 3, 7, 8, 9, 10, 7),
            edgeOrientations = mutableListOf(false, false, false, false, false, false, false, false, false, false, false, false)
        )

        val cubeStatePhaseDetection = CubeStatePhaseDetection(cubeState)
        val OLLSolved = cubeStatePhaseDetection.OLLSolved(context)
        assert(!OLLSolved)
    }
}