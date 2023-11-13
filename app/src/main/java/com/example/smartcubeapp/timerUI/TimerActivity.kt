package com.example.smartcubeapp.timerUI

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.smartcubeapp.bluetooth.BluetoothState
import com.example.smartcubeapp.bluetooth.bluetoothState
import com.example.smartcubeapp.bluetooth.cubeState
import com.example.smartcubeapp.bluetooth.timerState
import com.example.smartcubeapp.cube.Solve

class TimerActivity: ComponentActivity() {

    private lateinit var solve: MutableState<Solve>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("TimerActivity")
        setContent {
            solve = remember { mutableStateOf(Solve()) }
            timerState = remember { mutableStateOf(TimerState.Scrambling)}
            GenerateLayout()
        }

    }

    @Composable
    fun GenerateLayout(){
        if(bluetoothState.value != BluetoothState.Connected){
            val intent = Intent(this, ConnectActivity::class.java)
            startActivity(intent)
        }

        when (timerState.value) {
            TimerState.Scrambling -> {
                StateScramblingLayout(timerState, cubeState, solve).GenerateLayout(this)
            }

            TimerState.Solving -> {
                StateSolvingLayout(timerState, cubeState, solve).GenerateLayout()
            }

            TimerState.SolveFinished -> {
                StateSolveFinishedLayout(timerState, cubeState, solve, this).GenerateLayout()
            }
        }
    }
}