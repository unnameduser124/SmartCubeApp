package com.example.smartcubeapp.solvedatabase.dataclasses

import com.example.smartcubeapp.cube.CubeState

data class CubeStateData(
    val id: Long,
    val timestamp: Long,
    val solveID: Long,
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
        edgeOrientations = cubeState.edgeOrientations.joinToString(",")
    )
}