package com.example.smartcubeapp.cube

data class CubeState(
    val cornerPositions: MutableList<Int>,
    val cornerOrientations: MutableList<Int>,
    val edgePositions: MutableList<Int>,
    val edgeOrientations: MutableList<Boolean>,
    var solved: Boolean
)
