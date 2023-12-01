package com.example.smartcubeapp.solvedatabase.dataclasses

import com.example.smartcubeapp.cube.Solve

data class SolveData(
    var id: Long,
    val solveDuration: Long,
    val timestamp: Long,
    val scrambledStateID: Long,
    val scramble: String,
    val moveCount: Int = 0,
    val penalty: Int = 0
){
    constructor(solve: Solve): this(
        solve.id,
        solve.time,
        solve.date.timeInMillis,
        solve.scrambledState.id,
        solve.scrambleSequence,
        solve.solveStateSequence.size - 1,
        solve.solvePenalty.ordinal
    )
}