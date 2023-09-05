package com.example.smartcubeapp.cube

import com.example.smartcubeapp.MILLIS_IN_SECOND
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
        return solveStateSequence.size.toDouble() / (time / MILLIS_IN_SECOND)
    }

    fun calculateTimeFromStateSequence(){
        val time = solveStateSequence.last().timestamp - solveStateSequence.first().timestamp
        this.time = time
    }
}