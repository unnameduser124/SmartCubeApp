package com.example.cube_cube.solveDBDataClasses

import com.example.cube_cube.cube.CubeState

data class CubeStateData(
    var id: Long,
    val timestamp: Long,
    var solveID: Long,
    val moveIndex: Int,
    val lastMove: String,
    var cornerPositions: String,
    var edgePositions: String,
    var cornerOrientations: String,
    var edgeOrientations: String
){
    constructor(cubeState: CubeState, moveIndex: Int): this(
        id = cubeState.id,
        timestamp = cubeState.timestamp,
        solveID = cubeState.solveID,
        moveIndex = moveIndex,
        lastMove = cubeState.lastMove.notation,
        cornerPositions = cubeState.cornerPositions.joinToString(","),
        edgePositions = cubeState.edgePositions.joinToString(","),
        cornerOrientations = cubeState.cornerOrientations.joinToString(","),
        edgeOrientations = ""
    ){
        val orientationsBoolToByte = cubeState.edgeOrientations.map { if(it) 1 else 0 }
        edgeOrientations = orientationsBoolToByte.joinToString(",")
    }
}