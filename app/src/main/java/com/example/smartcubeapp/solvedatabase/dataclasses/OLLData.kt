package com.example.smartcubeapp.solvedatabase.dataclasses

data class OLLData(
    val solveID: Long,
    val duration: Long,
    val moveCount: Long,
    val startStateID: Long,
    val endStateID: Long,
    val case: Int,
    val id: Long = -1
)