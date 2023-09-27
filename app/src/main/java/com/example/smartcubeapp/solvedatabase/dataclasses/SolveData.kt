package com.example.smartcubeapp.solvedatabase.dataclasses

import com.example.smartcubeapp.cube.Solve

data class SolveData(
    val id: Long,
    val solveDuration: Long,
    val timestamp: Long,
    val scrambledStateID: Long,
    val scramble: String
){
    constructor(solve: Solve): this(
        solve.id,
        solve.time,
        solve.date.timeInMillis,
        solve.scrambledState.id,
        solve.scrambleSequence
    )
}