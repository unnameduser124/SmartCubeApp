package com.example.smartcubeapp.timerUI

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.smartcubeapp.bluetooth.BluetoothState
import com.example.smartcubeapp.bluetooth.bluetoothState
import com.example.smartcubeapp.bluetooth.timerState
import com.example.smartcubeapp.cube.Solve

class TimerActivity: ComponentActivity() {

    private var solve: Solve = Solve()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkForConnection()
        setContent {
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