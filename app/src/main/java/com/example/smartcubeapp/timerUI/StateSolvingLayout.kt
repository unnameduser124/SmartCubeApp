package com.example.smartcubeapp.timerUI

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.smartcubeapp.SOLVED_CUBE_STATE
import com.example.smartcubeapp.cube.CubeState
import com.example.smartcubeapp.cube.Move
import com.example.smartcubeapp.cube.Solve
import kotlinx.coroutines.delay
import java.util.Calendar

class StateSolvingLayout(
    private val state: MutableState<TimerState>,
    val cubeState: MutableState<CubeState>,
    val solve: MutableState<Solve>
) {

    @Composable
    fun GenerateLayout() {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val solveTime = remember { mutableStateOf(0L) }

            LaunchedEffect(solve.value.solveInProgress) {
                while (solve.value.solveInProgress) {
                    delay(100)
                    solveTime.value =
                        Calendar.getInstance().timeInMillis - solve.value.solveStartTime
                }
            }

            if (cubeState.value != solve.value.scrambledState && !solve.value.solveInProgress) {
                solve.value.solveStartTime = Calendar.getInstance().timeInMillis
                solve.value.solveInProgress = true
                LaunchedEffect(solve.value.solveInProgress) {
                    while (solve.value.solveInProgress) {
                        delay(100)
                        solveTime.value =
                            Calendar.getInstance().timeInMillis - solve.value.solveStartTime
                    }
                }
            }
            if(cubeState.value != solve.value.scrambledState){
                solve.value.solveMoveSequence.add(cubeState.value)
            }
            if (cubeState.value.solved) {
                state.value = TimerState.SolveFinished
                solve.value.time = Calendar.getInstance().timeInMillis - solve.value.solveStartTime
                solve.value.solveInProgress = false
            }

            Text(
                text = "${solveTime.value / 1000.0}s",
                fontSize = 50.sp
            )
        }
    }
}