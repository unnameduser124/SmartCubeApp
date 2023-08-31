package com.example.smartcubeapp.timerUI

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.smartcubeapp.bluetooth.BluetoothService
import com.example.smartcubeapp.cube.CubeState
import com.example.smartcubeapp.cube.Solve

class StateScramblingLayout(
    private val state: MutableState<TimerState>,
    val cubeState: MutableState<CubeState>,
    val solve: MutableState<Solve>
) {

    @Composable
    fun GenerateLayout() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    if(!cubeState.value.isSolved()){
                        state.value = TimerState.Solving
                        solve.value.scrambledState = cubeState.value
                    }
                },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Ready?", fontSize = 25.sp)
        }
    }
}