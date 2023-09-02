package com.example.smartcubeapp.cube

import java.util.Calendar

class Solve(
    var time: Long = 0,
    var date: Calendar = Calendar.getInstance(),
    var solveStateSequence: MutableList<CubeState> = mutableListOf(),
    var scrambledState: CubeState = CubeState.SOLVED_CUBE_STATE,
    var solveInProgress: Boolean = false,
    var solveStartTime: Long = 0
) {
    fun getTurnsPerSecond(): Double{
        return solveStateSequence.size.toDouble() / (time / 1000.0)
    }

    fun calculateTimeFromStateSequence(){
        val time = solveStateSequence.last().timestamp - solveStateSequence.first().timestamp
        this.time = time
    }
}