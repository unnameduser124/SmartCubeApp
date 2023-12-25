package com.example.cube_cube.solveDBDataClasses

data class OLLData(
    val solveID: Long,
    val duration: Long,
    val moveCount: Int,
    val startStateID: Long,
    val endStateID: Long,
    val case: Int,
    var id: Long = -1
){
    constructor(): this(
        solveID = -1,
        duration = -1,
        moveCount = -1,
        startStateID = -1,
        endStateID = -1,
        case = -1
    )
}