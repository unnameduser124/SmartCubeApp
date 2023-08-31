package com.example.smartcubeapp.elementorientationtests

import com.example.smartcubeapp.cube.CubeSideIndexes
import com.example.smartcubeapp.cube.CubeState
import com.example.smartcubeapp.cube.WhiteSide
import com.example.smartcubeapp.cube.YellowSide
import com.example.smartcubeapp.phasedetection.SolutionPhaseDetection
import junit.framework.TestCase.assertTrue
import org.junit.Test

class SolutionPhaseDetectionSideSolvedTests {

    @Test
    fun whiteSideSolvedTest(){
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7),
            mutableListOf(2, 3, 3, 1, 2, 3, 3, 1),
            mutableListOf(4, 0, 2, 10, 11, 5, 6, 1, 9, 3, 10, 7),
            mutableListOf(true, false, false, true, false, false, false, true, false, false, false, true)
        )

        val solutionPhaseDetection = SolutionPhaseDetection(cubeState)
        val whiteSideSolved =  solutionPhaseDetection.checkIfSideIsSolved(
            WhiteSide.cornerIndexes,
            WhiteSide.edgeIndexes
        )
        assertTrue(whiteSideSolved)
    }

    @Test
    fun yellowSideNotSolvedTest(){
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7),
            mutableListOf(2, 3, 3, 1, 2, 3, 3, 1),
            mutableListOf(4, 0, 2, 10, 11, 5, 6, 1, 9, 3, 10, 7),
            mutableListOf(true, false, false, true, false, false, false, true, false, false, false, true)
        )

        val solutionPhaseDetection = SolutionPhaseDetection(cubeState)
        val yellowSideSolved =  solutionPhaseDetection.checkIfSideIsSolved(
            YellowSide.cornerIndexes,
            YellowSide.edgeIndexes
        )
        assertTrue(!yellowSideSolved)
    }

    @Test
    fun blueSideSolvedTest(){
        val cubeState = CubeState(
            mutableListOf(2, 0, 1, 3, 4, 5, 6, 7),
            mutableListOf(3, 2, 1, 2, 3, 3, 3, 3),
            mutableListOf(1, 0, 2, 4, 6, 5, 7, 3, 8, 9, 10, 11),
            mutableListOf(true, false, true, true, true, false, true, true, false, false, false, false)
        )

        val solutionPhaseDetection = SolutionPhaseDetection(cubeState)
        val blueSideSolved =  solutionPhaseDetection.checkIfSideIsSolved(
            CubeSideIndexes.BLUE_SIDE_CORNER_INDEXES,
            CubeSideIndexes.BLUE_SIDE_EDGE_INDEXES
        )
        assertTrue(blueSideSolved)
    }

    @Test
    fun greenSideNotSolvedTest(){
        val cubeState = CubeState(
            mutableListOf(2, 0, 1, 3, 4, 5, 6, 7),
            mutableListOf(3, 2, 1, 2, 3, 3, 3, 3),
            mutableListOf(1, 0, 2, 4, 6, 5, 7, 3, 8, 9, 10, 11),
            mutableListOf(true, false, true, true, true, false, true, true, false, false, false, false)
        )

        val solutionPhaseDetection = SolutionPhaseDetection(cubeState)
        val greenSideSolved =  solutionPhaseDetection.checkIfSideIsSolved(
            CubeSideIndexes.GREEN_SIDE_CORNER_INDEXES,
            CubeSideIndexes.GREEN_SIDE_EDGE_INDEXES
        )
        assertTrue(!greenSideSolved)
    }

    @Test
    fun redSideSolvedTest(){
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 6, 4, 5, 7, 3),
            mutableListOf(3, 3, 3, 2, 3, 3, 1, 3),
            mutableListOf(2, 1, 0, 3, 4, 5, 6, 11, 8, 9, 11, 7),
            mutableListOf(false, false, true, false, false, false, false, true, false, false, false, false)
        )

        val solutionPhaseDetection = SolutionPhaseDetection(cubeState)
        val redSideSolved =  solutionPhaseDetection.checkIfSideIsSolved(
            CubeSideIndexes.RED_SIDE_CORNER_INDEXES,
            CubeSideIndexes.RED_SIDE_EDGE_INDEXES
        )
        assertTrue(redSideSolved)
    }

    @Test
    fun orangeSideNotSolvedTest(){
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 6, 4, 5, 7, 3),
            mutableListOf(3, 3, 3, 2, 3, 3, 1, 3),
            mutableListOf(2, 1, 0, 3, 4, 5, 6, 11, 8, 9, 11, 7),
            mutableListOf(false, false, true, false, false, false, false, true, false, false, false, false)
        )

        val solutionPhaseDetection = SolutionPhaseDetection(cubeState)
        val orangeSideSolved =  solutionPhaseDetection.checkIfSideIsSolved(
            CubeSideIndexes.ORANGE_SIDE_CORNER_INDEXES,
            CubeSideIndexes.ORANGE_SIDE_EDGE_INDEXES
        )
        assertTrue(!orangeSideSolved)
    }
}