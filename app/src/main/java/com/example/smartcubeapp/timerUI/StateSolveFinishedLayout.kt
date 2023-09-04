package com.example.smartcubeapp.timerUI

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartcubeapp.MILLIS_IN_SECOND
import com.example.smartcubeapp.cube.CubeState
import com.example.smartcubeapp.cube.Solve
import com.example.smartcubeapp.phasedetection.CubeStatePhaseDetection
import com.example.smartcubeapp.phasedetection.SolutionPhaseDetection
import com.example.smartcubeapp.phasedetection.SolvePhase
import com.example.smartcubeapp.roundDouble
import com.example.smartcubeapp.simpleTestSolve
import kotlin.concurrent.thread
import kotlin.coroutines.coroutineContext

class StateSolveFinishedLayout(
    private val state: MutableState<TimerState>,
    val cubeState: MutableState<CubeState>,
    private val solve: MutableState<Solve>,
    private val context: Context
) {

    @Composable
    fun GenerateLayout() {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SolveResults()
            Text(
                text = "Ready?",
                fontSize = 40.sp,
                modifier = Modifier.clickable {
                    if (!cubeState.value.isSolved()) {
                        solve.value = Solve()
                        solve.value.scrambledState = cubeState.value
                        state.value = TimerState.Solving
                    }
                })
        }

    }

    @Composable
    fun SolveResults() {
        Row(horizontalArrangement = Arrangement.Center) {
            solve.value.calculateTimeFromStateSequence()
            val time = solve.value.time / MILLIS_IN_SECOND.toDouble()
            val timeRounded = roundDouble(time, 100)
            Text(text = timeRounded.toString(), fontSize = 50.sp, modifier = Modifier.padding(top = 20.dp))
        }
        Row(horizontalArrangement = Arrangement.Center) {
            val moveCount = solve.value.solveStateSequence.size
            Text(
                text = "$moveCount moves",
                fontSize = 25.sp,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            val tpsRounded = roundDouble(solve.value.getTurnsPerSecond(), 100)
            Text(
                text = "${tpsRounded}tps",
                fontSize = 25.sp,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
        }
        Row(horizontalArrangement = Arrangement.Center) {
            PhaseStatisticsLazyColumn()
        }
    }

    @Composable
    fun PhaseStatisticsLazyColumn(){
        val phases = SolvePhase.values().toMutableList()
        phases.remove(SolvePhase.Scrambled)

        LazyColumn {
            items(phases.size) { index ->

                val solutionPhaseDetection = SolutionPhaseDetection(solve.value, CubeStatePhaseDetection(CubeState.SOLVED_CUBE_STATE))
                val phase = phases[index]

                val phaseTime = remember { mutableStateOf(0.0) }
                val phaseTps = remember { mutableStateOf(0.0) }
                val phaseMoves = remember { mutableStateOf(0) }

                phaseTime.value = roundDouble(solutionPhaseDetection.getPhaseDurationInSeconds(phase, context), 100)
                phaseTps.value = roundDouble(solutionPhaseDetection.getPhaseTPS(phase, context), 10)
                phaseMoves.value = solutionPhaseDetection.getPhaseMoveCount(phase, context)

                Row(horizontalArrangement = Arrangement.Center) {
                    Text(
                        text = phase.toString(),
                        fontSize = 25.sp,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )
                    Text(
                        text = phaseTime.value.toString(),
                        fontSize = 25.sp,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )
                    Text(
                        text = phaseMoves.value.toString(),
                        fontSize = 25.sp,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )
                    Text(
                        text = phaseTps.value.toString(),
                        fontSize = 25.sp,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )
                }
            }
        }
    }

}

@Preview
@Composable
fun PreviewStateSolveFinishedLayout() {
    val state = remember { mutableStateOf(TimerState.SolveFinished) }
    val cubeState = remember { mutableStateOf(CubeState.SOLVED_CUBE_STATE) }
    val solve = remember { mutableStateOf(simpleTestSolve()) }
    val context = androidx.compose.ui.platform.LocalContext.current
    StateSolveFinishedLayout(state, cubeState, solve, context).GenerateLayout()
}
