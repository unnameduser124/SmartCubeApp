package com.example.smartcubeapp.solvedatabase.dataclasses

data class CubeStateData(
    val id: Long,
    val timestamp: Long,
    val solveID: Long,
    val moveIndex: Int,
    val lastMove: String,
    val cornerPositions: String,
    val edgePositions: String,
    val cornerOrientations: String,
    val edgeOrientations: String
)