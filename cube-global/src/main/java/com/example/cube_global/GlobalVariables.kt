package com.example.cube_global

import com.example.cube_cube.cube.Solve
import com.example.cube_cube.cube.SolveStatus

const val MILLIS_IN_SECOND = 1000
var dbAccesses = 0
var solve = Solve().apply{ solveStatus = SolveStatus.Scramble }