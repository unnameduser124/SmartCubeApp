package com.example.smartcubeapp.cube

import com.example.smartcubeapp.SOLVED_CUBE_STATE
import java.util.Calendar

class Solve(
    var time: Long = 0,
    var date: Calendar = Calendar.getInstance(),
    var solveMoveSequence: MutableList<CubeState> = mutableListOf(),//TODO("Change to list of moves")
    var scrambledState: CubeState = SOLVED_CUBE_STATE,
    var solveInProgress: Boolean = false,
    var solveStartTime: Long = 0
) {
    fun getTurnsPerSecond(): Double{
        return solveMoveSequence.size.toDouble() / (time / 1000.0)
    }
}