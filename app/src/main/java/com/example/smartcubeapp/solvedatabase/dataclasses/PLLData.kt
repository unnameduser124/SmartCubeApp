package com.example.smartcubeapp.solvedatabase.dataclasses

data class PLLData(
    val id: Long,
    val solveID: Long,
    val duration: Long,
    val moveCount: Long,
    val startStateID: Long,
    val endStateID: Long,
    val case: Int
)