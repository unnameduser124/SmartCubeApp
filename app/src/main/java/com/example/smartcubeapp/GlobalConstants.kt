package com.example.smartcubeapp

import com.example.smartcubeapp.cube.CubeState

val SOLVED_CUBE_STATE = CubeState(
    cornerPositions = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8),
    cornerOrientations = mutableListOf(3, 3, 3, 3, 3, 3, 3, 3),
    edgePositions = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12),
    edgeOrientations = mutableListOf(
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false
    ),
    false
)
