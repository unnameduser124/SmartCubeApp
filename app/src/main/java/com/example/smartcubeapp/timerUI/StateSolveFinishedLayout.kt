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
import com.example.smartcubeapp.cube.CubeState
import com.example.smartcubeapp.cube.Solve
import com.example.smartcubeapp.roundDouble

class StateSolveFinishedLayout(
    private val state: MutableState<TimerState>,
    val cubeState: MutableState<CubeState>,
    val solve: MutableState<Solve>
) {

    @Composable
    fun GenerateLayout() {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SolveResults()
            Text(
                text = "Ready?",
                fontSize = 40.sp,
                modifier = Modifier.clickable {
                    if(!cubeState.value.isSolved()){
                        solve.value = Solve()
                        solve.value.scrambledState = cubeState.value
                        state.value = TimerState.Solving
                    }
                })
        }

    }

    @Composable
    fun SolveResults() {
        val time = solve.value.time / 1000.0
        Text(text = time.toString(), fontSize = 50.sp)
        val tpsRounded = roundDouble(solve.value.getTurnsPerSecond(), 100)
        Text(text = "${tpsRounded}tps", fontSize = 25.sp)
    }
}