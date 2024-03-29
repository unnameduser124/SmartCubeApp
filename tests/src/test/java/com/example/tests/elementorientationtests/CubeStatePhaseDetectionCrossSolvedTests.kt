package com.example.tests.elementorientationtests

import com.example.cube_cube.cube.CubeState
import com.example.cube_detection.phasedetection.CubeStatePhaseDetection
import junit.framework.TestCase.assertTrue
import org.junit.Test

class CubeStatePhaseDetectionCrossSolvedTests {

    @Test
    fun whiteCrossSolvedTest() {
        val cubeState = CubeState(
            mutableListOf(5, 1, 2, 3, 0, 4, 6, 7),
            mutableListOf(2, 3, 3, 3, 3, 1, 3, 3),
            mutableListOf(7, 1, 2, 3, 9, 5, 6, 4, 0, 8, 10, 11),
            mutableListOf(
                false, false, false, false, true, false, false, false, true, false, false, false
            )
        )

        val cubeStatePhaseDetection =
            com.example.cube_detection.phasedetection.CubeStatePhaseDetection(cubeState)
        val whiteCrossSolved = cubeStatePhaseDetection.crossSolved()
        assertTrue(whiteCrossSolved)
    }

    @Test
    fun yellowCrossSolvedTest() {
        val cubeState = CubeState(
            mutableListOf(5, 2, 6, 3, 0, 4, 1, 7),
            mutableListOf(2, 3, 1, 3, 2, 2, 3, 3),
            mutableListOf(0, 1, 2, 3, 4, 10, 5, 7, 8, 9, 6, 11),
            mutableListOf(
                false, false, true, false, false, false, true, false, false, true, true, false
            )
        )

        val cubeStatePhaseDetection =
            com.example.cube_detection.phasedetection.CubeStatePhaseDetection(cubeState)
        val yellowCrossSolved = cubeStatePhaseDetection.crossSolved()
        assertTrue(yellowCrossSolved)
    }

    @Test
    fun blueCrossSolvedTest() {
        val cubeState = CubeState(
            mutableListOf(1, 0, 5, 3, 2, 4, 6, 7),
            mutableListOf(2, 2, 3, 3, 3, 1, 1, 3),
            mutableListOf(0, 4, 1, 3, 6, 5, 2, 7, 8, 9, 10, 11),
            mutableListOf(
                false, false, true, false, false, true, false, false, false, false, false, false
            )
        )

        val cubeStatePhaseDetection =
            com.example.cube_detection.phasedetection.CubeStatePhaseDetection(cubeState)
        val blueCrossSolved = cubeStatePhaseDetection.crossSolved()
        assertTrue(blueCrossSolved)
    }

    @Test
    fun greenCrossSolvedTest() {
        val cubeState = CubeState(
            mutableListOf(5, 4, 2, 3, 1, 6, 0, 7),
            mutableListOf(2, 1, 1, 3, 2, 1, 1, 3),
            mutableListOf(0, 1, 2, 3, 4, 10, 9, 7, 11, 5, 8, 6),
            mutableListOf(
                false, false, false, false, false, true, false, false, false, true, true, true
            )
        )

        val cubeStatePhaseDetection =
            com.example.cube_detection.phasedetection.CubeStatePhaseDetection(cubeState)
        val greenCrossSolved = cubeStatePhaseDetection.crossSolved()
        assertTrue(greenCrossSolved)
    }

    @Test
    fun redCrossSolvedTest() {
        val cubeState = CubeState(
            mutableListOf(5, 1, 4, 7, 2, 0, 6, 3),
            mutableListOf(2, 1, 1, 1, 3, 3, 3, 2),
            mutableListOf(0, 1, 10, 7, 4, 5, 2, 6,  8, 9, 11, 3),
            mutableListOf(
                false, false, false, false, false, false, true, true, true, false, false, true
            )
        )

        val cubeStatePhaseDetection =
            com.example.cube_detection.phasedetection.CubeStatePhaseDetection(cubeState)
        val redCrossSolved = cubeStatePhaseDetection.crossSolved()
        assertTrue(redCrossSolved)
    }

    @Test
    fun orangeCrossSolvedTest(){
        val cubeState = CubeState(
            mutableListOf(2, 4, 6, 7, 3, 5, 0, 1),
            mutableListOf(2, 1, 2, 1, 1, 1, 3, 1),
            mutableListOf(4, 1, 2, 3, 8, 10, 6, 7, 5, 0, 9, 11),
            mutableListOf(
                true, false, false, false, false, true, false, false, true, true, false, false
            )
        )

        val cubeStatePhaseDetection =
            com.example.cube_detection.phasedetection.CubeStatePhaseDetection(cubeState)
        val orangeCrossSolved = cubeStatePhaseDetection.crossSolved()
        assertTrue(orangeCrossSolved)
    }

    @Test
    fun crossNotSolvedTest(){
        val cubeState = CubeState(
            mutableListOf(2, 4, 6, 7, 3, 0, 1, 5),
            mutableListOf(2, 1, 2, 1, 1, 1, 3, 1),
            mutableListOf(4, 2, 1, 8, 3, 10, 6, 7, 5, 0, 11, 9),
            mutableListOf(
                true, false, false, false, false, true, false, false, true, true, false, false
            )
        )

        val cubeStatePhaseDetection =
            com.example.cube_detection.phasedetection.CubeStatePhaseDetection(cubeState)
        val crossSolved = cubeStatePhaseDetection.crossSolved()
        assertTrue(!crossSolved)
    }
}