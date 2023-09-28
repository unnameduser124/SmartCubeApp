package com.example.smartcubeapp.solvedatabase.dataclasses

data class F2LData(
    val solveID: Long,
    val duration: Long,
    val moveCount: Long,
    val startStateID: Long,
    val endStateID: Long,
    val id: Long = -1
)