package com.example.smartcubeapp.ui.timerUI

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.cube_bluetooth.bluetooth.BluetoothState
import com.example.cube_bluetooth.bluetooth.bluetoothState
import com.example.cube_bluetooth.bluetooth.cubeState
import com.example.cube_bluetooth.bluetooth.lastMove
import com.example.cube_bluetooth.bluetooth.timerState
import com.example.cube_cube.cube.CubeState
import com.example.cube_cube.cube.Move
import com.example.cube_global.TimerState
import com.example.smartcubeapp.ui.connectUI.ConnectActivity

class TimerActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkForConnection()
        setContent {
            bluetoothState = remember { mutableStateOf(BluetoothState.Connected) }
            cubeState = remember { mutableStateOf(CubeState.SOLVED_CUBE_STATE) }
            lastMove = remember { mutableStateOf(Move("?", 0, "?")) }
            GenerateLayout()
        }

    }

    @Composable
    fun GenerateLayout(){
        when (timerState) {
            TimerState.Solving -> {
                val intent = Intent(this, SolvingActivity::class.java)
                startActivity(intent)
            }
            else -> {
                val intent = Intent(this, SolvePreparationActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun checkForConnection(){
        if(bluetoothState.value != BluetoothState.Connected){
            val intent = Intent(this, ConnectActivity::class.java)
            startActivity(intent)
        }
    }
}