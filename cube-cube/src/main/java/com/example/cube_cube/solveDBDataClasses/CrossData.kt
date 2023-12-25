package com.example.cube_cube.solveDBDataClasses

class CrossData(
    val solveID: Long,
    val duration: Long,
    val moveCount: Int,
    val startStateID: Long,
    val endStateID: Long,
    var id: Long = -1
){
    constructor(): this(
        solveID = -1,
        duration = -1,
        moveCount = -1,
        startStateID = -1,
        endStateID = -1
    )
}