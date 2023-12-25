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
import com.example.cube_cube.cube.Solve
import com.example.cube_global.TimerState
import com.example.smartcubeapp.ui.connectUI.ConnectActivity

class TimerActivity: ComponentActivity() {

    private var solve: Solve = Solve()

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
        timerState = remember { mutableStateOf(TimerState.Scrambling) }

        when (timerState.value) {
            TimerState.Scrambling -> {
                StateScramblingLayout(solve).GenerateLayout(this)
            }

            TimerState.Solving -> {
                StateSolvingLayout(solve).GenerateLayout()
            }

            TimerState.SolveFinished -> {
                StateSolveFinishedLayout(solve).GenerateLayout(this)
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