package com.example.smartcubeapp.solvedatabase.dataclasses

data class SolveData(
    val id: Long,
    val timestamp: Long,
    val scrambledStateID: Long,
    val scramble: String
)