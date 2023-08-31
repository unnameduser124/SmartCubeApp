package com.example.smartcubeapp

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import com.example.smartcubeapp.bluetooth.BluetoothService
import com.example.smartcubeapp.bluetooth.BluetoothUtilities
import com.example.smartcubeapp.cube.CubeState
import com.example.smartcubeapp.cube.Move
import com.example.smartcubeapp.cube.Solve
import com.example.smartcubeapp.timerUI.StateConnectingLayout
import com.example.smartcubeapp.timerUI.StateScramblingLayout
import com.example.smartcubeapp.timerUI.StateSolveFinishedLayout
import com.example.smartcubeapp.timerUI.StateSolvingLayout
import com.example.smartcubeapp.timerUI.TimerState
import java.util.*

class MainActivity : ComponentActivity() {
    private lateinit var bluetoothService: BluetoothService
    private lateinit var bluetoothUtilities: BluetoothUtilities

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val state = remember { mutableStateOf(TimerState.Connecting) }
            val cubeState = remember { mutableStateOf(CubeState.SOLVED_CUBE_STATE) }
            val lastMove = remember { mutableStateOf(Move("?", 0, "?")) }

            val solve = remember { mutableStateOf(Solve()) }

            bluetoothService = BluetoothService(this, this, state, cubeState, lastMove)
            bluetoothUtilities = BluetoothUtilities(this, this)
            if (!bluetoothUtilities.checkForBluetoothConnectPermission()
                || !bluetoothUtilities.checkForBluetoothScanPermission()
                || !bluetoothUtilities.checkFineLocationPermission()
                || bluetoothUtilities.checkCoarseLocationPermission()
            ) {
                requestAllPermissions()
            }
            MainLayout(bluetoothService, state, solve, cubeState)
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    fun requestAllPermissions() {
        val permissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.BLUETOOTH_CONNECT,
            Manifest.permission.BLUETOOTH_SCAN
        )
        requestPermissions(permissions, 5)
    }
}


@Composable
fun MainLayout(
    bluetoothService: BluetoothService,
    state: MutableState<TimerState>,
    solve: MutableState<Solve>,
    cubeState: MutableState<CubeState>
) {
    when (state.value) {
        TimerState.Connecting -> {
            StateConnectingLayout(state, bluetoothService).GenerateLayout()
        }

        TimerState.Scrambling -> {
            StateScramblingLayout(state, cubeState, solve).GenerateLayout()
        }

        TimerState.Solving -> {
            StateSolvingLayout(state, cubeState, solve).GenerateLayout()
        }

        TimerState.SolveFinished -> {
            StateSolveFinishedLayout(state, cubeState, solve).GenerateLayout()
        }
    }
}