package com.example.cube_global

import com.example.cube_cube.cube.Solve
import com.example.cube_cube.cube.SolveStatus

const val MILLIS_IN_SECOND = 1000
const val MILLIS_IN_HOUR = 3600000
var dbAccesses = 0
var solve = Solve().apply{ solveStatus = SolveStatus.Scramble }