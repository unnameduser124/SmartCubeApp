package com.example.smartcubeapp

import com.example.smartcubeapp.cube.CubeState

const val MY_CUBE_ADDRESS = "CC:34:E2:64:FA:07"
const val SERVICE_UUID = "0000aadb-0000-1000-8000-00805f9b34fb"
const val CHARACTERISTIC_UUID = "0000aadc-0000-1000-8000-00805f9b34fb"
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
