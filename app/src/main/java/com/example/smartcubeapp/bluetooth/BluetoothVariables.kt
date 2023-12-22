package com.example.smartcubeapp.bluetooth

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.smartcubeapp.cube.CubeState
import com.example.smartcubeapp.cube.Move
import com.example.smartcubeapp.ui.timerUI.TimerState

const val MY_CUBE_ADDRESS = "CC:34:E2:64:FA:07"
const val SERVICE_UUID = "0000aadb-0000-1000-8000-00805f9b34fb"
const val CHARACTERISTIC_UUID = "0000aadc-0000-1000-8000-00805f9b34fb"
const val GIIKER_DESCRIPTOR_UUID = "00002902-0000-1000-8000-00805f9b34fb"

val MyCubeDevice = CubeDevice("MyCube", MY_CUBE_ADDRESS)

lateinit var cubeState: MutableState<CubeState>
lateinit var timerState: MutableState<TimerState>
lateinit var lastMove: MutableState<Move>
var bluetoothState: MutableState<BluetoothState> = mutableStateOf(BluetoothState.Disconnected)