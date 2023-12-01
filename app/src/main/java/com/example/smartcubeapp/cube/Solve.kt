package com.example.smartcubeapp.cube

import com.example.smartcubeapp.MILLIS_IN_SECOND
import com.example.smartcubeapp.solvedatabase.dataclasses.SolveData
import java.util.Calendar

class Solve(
    var time: Long = 0,
    var date: Calendar = Calendar.getInstance(),
    var solveStateSequence: MutableList<CubeState> = mutableListOf(),
    var scrambledState: CubeState = CubeState.SOLVED_CUBE_STATE,
    var solveStatus: SolveStatus = SolveStatus.Scramble,
    var solveStartTime: Long = 0,
    var id: Long = -1,
    var scrambleSequence: String = "",
    var solvePenalty: SolvePenalty = SolvePenalty.None
) {

    constructor(solveData: SolveData): this(
        time = solveData.solveDuration,
        date = Calendar.getInstance().apply { timeInMillis = solveData.timestamp },
        id = solveData.id,
        scrambleSequence = solveData.scramble,
        solvePenalty = SolvePenalty.values()[solveData.penalty]
    )

    fun getTurnsPerSecond(): Double{
        return solveStateSequence.size.toDouble() / (time / MILLIS_IN_SECOND)
    }

    fun calculateTimeFromStateSequence(){
        if(solveStateSequence.isEmpty()) throw IllegalArgumentException("Solve state sequence is empty")

        val time = solveStateSequence.last().timestamp - solveStateSequence.first().timestamp
        this.time = time
    }

    fun prepareForNewSolve(){
        id = -1
        solveStateSequence.clear()
        solveStatus = SolveStatus.Scramble
        solveStartTime = 0
        scrambledState = CubeState.SOLVED_CUBE_STATE
        solvePenalty = SolvePenalty.None
    }
}