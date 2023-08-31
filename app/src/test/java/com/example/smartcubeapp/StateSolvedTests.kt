package com.example.smartcubeapp

import com.example.smartcubeapp.cube.CubeState
import org.junit.Test

class StateSolvedTests {

    @Test
    fun cubeStateSolvedTest(){
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7),
            mutableListOf(3, 3, 3, 3, 3, 3, 3, 3),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 ,11),
            mutableListOf(false, false, false, false, false, false, false, false, false, false, false, false)
        )
        assert(cubeState.isSolved())
    }

    @Test

    fun cubeStateNotSolvedTest(){
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 4, 3, 5, 6, 7),
            mutableListOf(3, 3, 3, 3, 3, 3, 3, 3),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 ,11),
            mutableListOf(false, false, false, false, false, false, false, false, false, false, false, false)
        )
        assert(!cubeState.isSolved())
    }
}