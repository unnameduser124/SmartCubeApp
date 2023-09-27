package com.example.smartcubeapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import com.example.smartcubeapp.bluetooth.BluetoothService
import com.example.smartcubeapp.bluetooth.BluetoothState
import com.example.smartcubeapp.bluetooth.BluetoothUtilities
import com.example.smartcubeapp.bluetooth.bluetoothState
import com.example.smartcubeapp.bluetooth.cubeState
import com.example.smartcubeapp.bluetooth.lastMove
import com.example.smartcubeapp.cube.CubeState
import com.example.smartcubeapp.cube.Move
import com.example.smartcubeapp.cube.Solve
import com.example.smartcubeapp.timerUI.ConnectActivity
import com.example.smartcubeapp.timerUI.TimerActivity
import java.util.*

class MainActivity : ComponentActivity() {
    private lateinit var bluetoothService: BluetoothService
    private lateinit var bluetoothUtilities: BluetoothUtilities

    private lateinit var solve: MutableState<Solve>

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            cubeState = remember { mutableStateOf(CubeState.SOLVED_CUBE_STATE) }
            lastMove = remember { mutableStateOf(Move("?", 0, "?")) }
            bluetoothState = remember { mutableStateOf(BluetoothState.Disconnected)}

            solve = remember { mutableStateOf(Solve()) }

            bluetoothUtilities = BluetoothUtilities(this, this)
            if (!bluetoothUtilities.checkForBluetoothConnectPermission()
                || !bluetoothUtilities.checkForBluetoothScanPermission()
                || !bluetoothUtilities.checkFineLocationPermission()
                || bluetoothUtilities.checkCoarseLocationPermission()
            ) {
                bluetoothUtilities.requestAllPermissions()
            }
            MainLayout()
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    @Composable
    fun MainLayout() {
        when (bluetoothState.value) {
            BluetoothState.Connected -> {
                val intent = Intent(this, TimerActivity::class.java)
                startActivity(intent)
            }
            else -> {
                val intent = Intent(this, ConnectActivity::class.java)
                startActivity(intent)
            }
        }
    }
}

