package com.example.smartcubeapp.elementorientationtests

import com.example.smartcubeapp.cube.CubeState
import com.example.smartcubeapp.phasedetection.SolutionPhaseDetection
import org.junit.Test

class SolutionPhaseDetectionF2LSolvedTests {

    @Test
    fun whiteCrossBaseF2LSolvedTest() {
        val cubeState = CubeState(
            mutableListOf(4, 1, 2, 3, 0, 5, 6, 7),
            mutableListOf(2, 3, 3, 3, 2, 3, 3, 3),
            mutableListOf(8, 1, 2, 3, 4, 5, 6, 7, 0, 9, 10, 11),
            mutableListOf(
                false, false, false, false, false, false, false, false, false, false, false, false
            )
        )
        val solutionPhaseDetection = SolutionPhaseDetection(cubeState)
        val f2lSolved = solutionPhaseDetection.F2LSolved()
        assert(f2lSolved)
    }

    @Test
    fun yellowCrossBaseF2LNotSolvedTest() {
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7),
            mutableListOf(3, 3, 3, 3, 3, 3, 3, 3),
            mutableListOf(0, 1, 2, 11, 4, 6, 3, 7, 8, 9, 5, 10),
            mutableListOf(
                false, false, false, false, false, false, true, false, false, false, true, false
            )
        )
        val solutionPhaseDetection = SolutionPhaseDetection(cubeState)
        val f2lSolved = solutionPhaseDetection.F2LSolved()
        assert(!f2lSolved)
    }

    @Test
    fun blueCrossBaseF2LSolvedTest() {
        val cubeState = CubeState(
            mutableListOf(0, 2, 3, 1, 4, 5, 6, 7),
            mutableListOf(3, 3, 3, 3, 3, 3, 3, 3),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11),
            mutableListOf(
                false, false, false, false, false, false, false, false, false, false, false, false
            )
        )
        val solutionPhaseDetection = SolutionPhaseDetection(cubeState)
        val f2lSolved = solutionPhaseDetection.F2LSolved()
        assert(f2lSolved)
    }

    @Test
    fun greenCrossBaseF2LNotSolved(){
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7),
            mutableListOf(3, 3, 3, 3, 3, 3, 3, 3),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 10, 11, 8, 9),
            mutableListOf(
                false, false, false, false, false, false, false, true, false, false, true, false
            )
        )
        val solutionPhaseDetection = SolutionPhaseDetection(cubeState)
        val f2lSolved = solutionPhaseDetection.F2LSolved()
        assert(!f2lSolved)
    }

    @Test
    fun redCrossBaseF2LSolved(){
        val cubeState = CubeState(
            mutableListOf(0, 1, 6, 3, 4, 5, 7, 2),
            mutableListOf(3, 3, 1, 3, 3, 3, 1, 3),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11),
            mutableListOf(
                false, false, false, false, false, false, false, false, false, false, false, false
            )
        )
        val solutionPhaseDetection = SolutionPhaseDetection(cubeState)
        val f2lSolved = solutionPhaseDetection.F2LSolved()
        assert(f2lSolved)
    }

    @Test
    fun orangeCrossBaseF2LNotSolved(){
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7),
            mutableListOf(3, 3, 3, 3, 3, 3, 3, 3),
            mutableListOf(0, 9, 2, 3, 4, 1, 6, 7, 5, 10, 8, 11),
            mutableListOf(
                false, false, false, false, false, false, false, false, true, true, false, false
            )
        )
        val solutionPhaseDetection = SolutionPhaseDetection(cubeState)
        val f2lSolved = solutionPhaseDetection.F2LSolved()
        assert(!f2lSolved)
    }
}