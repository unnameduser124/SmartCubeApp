package com.example.smartcubeapp.timerUI

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.smartcubeapp.bluetooth.cubeState
import com.example.smartcubeapp.bluetooth.timerState
import com.example.smartcubeapp.cube.Solve
import com.example.smartcubeapp.cube.SolveStatus
import com.example.smartcubeapp.roundDouble
import kotlinx.coroutines.delay
import java.util.Calendar

class StateSolvingLayout(
    val solve: Solve
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

            LaunchedEffect(solve.solveStatus) {
                while (solve.solveStatus == SolveStatus.Solving) {
                    delay(100)
                    solveTime.value =
                        Calendar.getInstance().timeInMillis - solve.solveStartTime
                }
            }

            if (cubeState.value != solve.scrambledState && solve.solveStatus != SolveStatus.Solving) {
                solve.solveStartTime = Calendar.getInstance().timeInMillis
                solve.solveStatus = SolveStatus.Solving
                solve.scrambledState.timestamp = solve.solveStartTime
                solve.solveStateSequence.add(solve.scrambledState)
                LaunchedEffect(solve.solveStatus) {
                    while (solve.solveStatus == SolveStatus.Solving) {
                        delay(100)
                        solveTime.value =
                            Calendar.getInstance().timeInMillis - solve.solveStartTime
                    }
                }
            }
            if (cubeState.value != solve.scrambledState
                && cubeState.value != solve.solveStateSequence.lastOrNull()
                && solve.solveStatus == SolveStatus.Solving) {
                solve.solveStateSequence.add(cubeState.value)
            }
            if (cubeState.value.isSolved()) {
                timerState.value = TimerState.SolveFinished
                solve.calculateTimeFromStateSequence()
                solve.solveStatus = SolveStatus.Solved
            }

            Text(
                text = "${roundDouble(solveTime.value / 1000.0, 100)}s",
                fontSize = 50.sp
            )
        }
    }
}

@Preview
@Composable
fun StateSolvingLayoutPreview() {
    val solve = Solve()

    StateSolvingLayout(solve).GenerateLayout()
}