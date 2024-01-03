package com.example.smartcubeapp.ui.timerUI

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cube_bluetooth.bluetooth.cubeState
import com.example.cube_cube.cube.CubeState
import com.example.cube_cube.cube.SolvePenalty
import com.example.cube_cube.cube.SolveStatus
import com.example.cube_detection.phasedetection.CubeStatePhaseDetection
import com.example.cube_detection.phasedetection.SolutionPhaseDetection
import com.example.cube_detection.phasedetection.SolvePhase
import com.example.cube_global.AppSettings
import com.example.cube_global.MILLIS_IN_SECOND
import com.example.cube_global.roundDouble
import com.example.cube_global.solve

class SolveResultsUI(val context: Context) {

    @Composable
    fun SolveResults() {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            SolveTimeRow()
            if (solve.solvePenalty != SolvePenalty.DNF && solve.solveStatus == SolveStatus.Solved) {
                Row(horizontalArrangement = Arrangement.Center) {
                    val moveCount = solve.solveStateSequence.size - 1
                    Text(
                        text = "$moveCount moves",
                        fontSize = 25.sp,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )
                    val tpsRounded =
                        roundDouble(solve.getTurnsPerSecond(), 100)
                    Text(
                        text = "${tpsRounded}tps",
                        fontSize = 25.sp,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )
                }
                PhaseStatsDataTable()
                OLLCaseRow()
                PLLCaseRow()
            }
        }
    }

    @Composable
    fun OLLCaseRow() {
        val ollCase = SolutionPhaseDetection(
            solve,
            CubeStatePhaseDetection(CubeState.SOLVED_CUBE_STATE)
        ).getOLL(context)

        Row(horizontalArrangement = Arrangement.Center) {
            Text(
                text = "OLL Case",
                fontSize = 25.sp,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            Text(
                text = ollCase.toString(),
                fontSize = 25.sp,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
        }
    }

    @Composable
    fun PLLCaseRow() {
        val pllCase = SolutionPhaseDetection(
            solve,
            CubeStatePhaseDetection(CubeState.SOLVED_CUBE_STATE)
        ).getPLL(context)

        Row(horizontalArrangement = Arrangement.Center) {
            Text(
                text = "PLL Case",
                fontSize = 25.sp,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            Text(
                text = pllCase.toString(),
                fontSize = 25.sp,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
        }
    }

    @Composable
    fun SolveTimeRow() {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.clickable {
                if(!AppSettings.isScrambleGenerationEnabled && !cubeState.value.isSolved()){
                    solve.prepareForNewSolve()
                    solve.scrambledState = cubeState.value
                    solve.scrambleSequence = "Scramble generation disabled"
                    val intent = Intent(context, SolvingActivity::class.java)
                    context.startActivity(intent)
                }
            }
        ) {
            when (solve.solvePenalty) {
                SolvePenalty.DNF -> {
                    Text(
                        text = "DNF",
                        fontSize = 70.sp,
                    )
                }
                SolvePenalty.PlusTwo -> {
                    val solveTime = calculateTime()
                    val solveSeconds = solveTime.split(".")[0]
                    val solveMilliseconds = solveTime.split(".")[1]
                    Text(
                        text = "$solveSeconds.",
                        fontSize = 70.sp,
                    )
                    Text(
                        text = solveMilliseconds,
                        fontSize = 50.sp,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                }
                else -> {
                    val solveTime = calculateTime()
                    val solveSeconds = solveTime.split(".")[0]
                    val solveMilliseconds = solveTime.split(".")[1]
                    Text(
                        text = "$solveSeconds.",
                        fontSize = 70.sp,
                    )
                    Text(
                        text = solveMilliseconds,
                        fontSize = 50.sp,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                }
            }
        }
    }

    @Composable
    fun PhaseStatsDataTable() {
        val columnWeight = 1f

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.widthIn(100.dp, 300.dp)
        ) {
            item {
                Row(horizontalArrangement = Arrangement.Center) {
                    TableCell("Phase", columnWeight)
                    TableCell("Time", columnWeight)
                    TableCell("Moves", columnWeight)
                    TableCell("TPS", columnWeight)
                }
            }
            val phases =
                SolvePhase.values().toMutableList()
            phases.remove(SolvePhase.Scrambled)

            items(phases.size) { index ->

                val solutionPhaseDetection =
                    SolutionPhaseDetection(
                        solve,
                        CubeStatePhaseDetection(CubeState.SOLVED_CUBE_STATE)
                    )
                val phase = phases[index]

                val phaseTime = remember { mutableStateOf(0.0) }
                val phaseTps = remember { mutableStateOf(0.0) }
                val phaseMoves = remember { mutableStateOf(0) }

                phaseTime.value = roundDouble(
                    solutionPhaseDetection.getPhaseDurationInSeconds(phase, context),
                    100
                )
                phaseTps.value =
                    roundDouble(
                        solutionPhaseDetection.getPhaseTPS(
                            phase,
                            context
                        ), 10
                    )
                phaseMoves.value = solutionPhaseDetection.getPhaseMoveCount(phase, context)

                Row(horizontalArrangement = Arrangement.Center) {
                    TableCell(phase.toString(), columnWeight)
                    TableCell(phaseTime.value.toString(), columnWeight)
                    TableCell(phaseMoves.value.toString(), columnWeight)
                    TableCell(phaseTps.value.toString(), columnWeight)
                }
            }
        }
    }

    private fun calculateTime(): String {
        try {
            solve.calculateTimeFromStateSequence()
        } catch (exception: IllegalArgumentException) {
            print(exception.message)
            return "0.0"
        }
        val time =
            when (solve.solvePenalty) {
                SolvePenalty.None -> solve.time / MILLIS_IN_SECOND.toDouble()
                SolvePenalty.PlusTwo -> solve.time / MILLIS_IN_SECOND.toDouble() + 2.0
                else -> 0.0
            }
        val timeRounded = roundDouble(time, 100)
        return timeRounded.toString()
    }

    @Composable
    fun RowScope.TableCell(
        text: String,
        weight: Float
    ) {
        Text(
            text = text,
            modifier = Modifier.weight(weight),
            maxLines = 1,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}