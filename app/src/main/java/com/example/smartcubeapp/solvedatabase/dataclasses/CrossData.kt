package com.example.smartcubeapp.solvedatabase.dataclasses

class CrossData(
    val solveID: Long,
    val duration: Long,
    val moveCount: Int,
    val startStateID: Long,
    val endStateID: Long,
    val id: Long = -1
){
    constructor(): this(
        solveID = -1,
        duration = -1,
        moveCount = -1,
        startStateID = -1,
        endStateID = -1
    )
}