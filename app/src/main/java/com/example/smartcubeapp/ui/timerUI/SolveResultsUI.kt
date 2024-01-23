package com.example.smartcubeapp.ui.timerUI

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cube_bluetooth.bluetooth.cubeState
import com.example.cube_cube.cube.CubeState
import com.example.cube_cube.cube.SolvePenalty
import com.example.cube_cube.cube.SolveStatus
import com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase
import com.example.cube_detection.phasedetection.CubeStatePhaseDetection
import com.example.cube_detection.phasedetection.SolutionPhaseDetection
import com.example.cube_detection.phasedetection.SolvePhase
import com.example.cube_global.AppSettings
import com.example.cube_global.MILLIS_IN_SECOND
import com.example.cube_global.roundDouble
import com.example.cube_global.simpleTestSolve
import com.example.cube_global.solve
import com.example.smartcubeapp.R
import com.example.smartcubeapp.ui.theme.backgroundDark
import com.example.smartcubeapp.ui.theme.errorDark
import com.example.smartcubeapp.ui.theme.onBackgroundDark
import com.example.smartcubeapp.ui.theme.surfaceContainerHighestDark
import com.example.smartcubeapp.ui.theme.surfaceContainerLowDark

class SolveResultsUI(val context: Context) {

    private val fontColor = onBackgroundDark

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
                        modifier = Modifier.padding(horizontal = 10.dp),
                        color = fontColor
                    )
                    val tpsRounded =
                        roundDouble(solve.getTurnsPerSecond(), 100)
                    Text(
                        text = "${tpsRounded}tps",
                        fontSize = 25.sp,
                        modifier = Modifier.padding(horizontal = 10.dp),
                        color = fontColor
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

        val ollPainterResource = painterResource(getOLLDrawableID(ollCase!!))
        Column(
            modifier = Modifier
                .padding(top = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "OLL Case",
                fontSize = 25.sp,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                color = fontColor
            )
            Row(
                modifier = Modifier.background(
                    color = surfaceContainerHighestDark,
                    shape = RoundedCornerShape(10.dp)
                )
            ) {
                Image(
                    ollPainterResource,
                    contentDescription = "OLL Case",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(10.dp)
                )
            }
        }
    }

    private fun getOLLDrawableID(ollCase: PredefinedOLLCase): Int {
        return when (ollCase) {
            PredefinedOLLCase.OLL_01 -> R.drawable.oll_01
            PredefinedOLLCase.OLL_02 -> R.drawable.oll_02
            PredefinedOLLCase.OLL_03 -> R.drawable.oll_03
            PredefinedOLLCase.OLL_04 -> R.drawable.oll_04
            PredefinedOLLCase.OLL_05 -> R.drawable.oll_05
            PredefinedOLLCase.OLL_06 -> R.drawable.oll_06
            PredefinedOLLCase.OLL_07 -> R.drawable.oll_07
            PredefinedOLLCase.OLL_08 -> R.drawable.oll_08
            PredefinedOLLCase.OLL_09 -> R.drawable.oll_09
            PredefinedOLLCase.OLL_10 -> R.drawable.oll_10
            PredefinedOLLCase.OLL_11 -> R.drawable.oll_11
            PredefinedOLLCase.OLL_12 -> R.drawable.oll_12
            PredefinedOLLCase.OLL_13 -> R.drawable.oll_13
            PredefinedOLLCase.OLL_14 -> R.drawable.oll_14
            PredefinedOLLCase.OLL_15 -> R.drawable.oll_15
            PredefinedOLLCase.OLL_16 -> R.drawable.oll_16
            PredefinedOLLCase.OLL_17 -> R.drawable.oll_17
            PredefinedOLLCase.OLL_18 -> R.drawable.oll_18
            PredefinedOLLCase.OLL_19 -> R.drawable.oll_19
            PredefinedOLLCase.OLL_20 -> R.drawable.oll_20
            PredefinedOLLCase.OLL_21 -> R.drawable.oll_21
            PredefinedOLLCase.OLL_22 -> R.drawable.oll_22
            PredefinedOLLCase.OLL_23 -> R.drawable.oll_23
            PredefinedOLLCase.OLL_24 -> R.drawable.oll_24
            PredefinedOLLCase.OLL_25 -> R.drawable.oll_25
            PredefinedOLLCase.OLL_26 -> R.drawable.oll_26
            PredefinedOLLCase.OLL_27 -> R.drawable.oll_27
            PredefinedOLLCase.OLL_28 -> R.drawable.oll_28
            PredefinedOLLCase.OLL_29 -> R.drawable.oll_29
            PredefinedOLLCase.OLL_30 -> R.drawable.oll_30
            PredefinedOLLCase.OLL_31 -> R.drawable.oll_31
            PredefinedOLLCase.OLL_32 -> R.drawable.oll_32
            PredefinedOLLCase.OLL_33 -> R.drawable.oll_33
            PredefinedOLLCase.OLL_34 -> R.drawable.oll_34
            PredefinedOLLCase.OLL_35 -> R.drawable.oll_35
            PredefinedOLLCase.OLL_36 -> R.drawable.oll_36
            PredefinedOLLCase.OLL_37 -> R.drawable.oll_37
            PredefinedOLLCase.OLL_38 -> R.drawable.oll_38
            PredefinedOLLCase.OLL_39 -> R.drawable.oll_39
            PredefinedOLLCase.OLL_40 -> R.drawable.oll_40
            PredefinedOLLCase.OLL_41 -> R.drawable.oll_41
            PredefinedOLLCase.OLL_42 -> R.drawable.oll_42
            PredefinedOLLCase.OLL_43 -> R.drawable.oll_43
            PredefinedOLLCase.OLL_44 -> R.drawable.oll_44
            PredefinedOLLCase.OLL_45 -> R.drawable.oll_45
            PredefinedOLLCase.OLL_46 -> R.drawable.oll_46
            PredefinedOLLCase.OLL_47 -> R.drawable.oll_47
            PredefinedOLLCase.OLL_48 -> R.drawable.oll_48
            PredefinedOLLCase.OLL_49 -> R.drawable.oll_49
            PredefinedOLLCase.OLL_50 -> R.drawable.oll_50
            PredefinedOLLCase.OLL_51 -> R.drawable.oll_51
            PredefinedOLLCase.OLL_52 -> R.drawable.oll_52
            PredefinedOLLCase.OLL_53 -> R.drawable.oll_53
            PredefinedOLLCase.OLL_54 -> R.drawable.oll_54
            PredefinedOLLCase.OLL_55 -> R.drawable.oll_55
            PredefinedOLLCase.OLL_56 -> R.drawable.oll_56
            PredefinedOLLCase.OLL_57 -> R.drawable.oll_57
            else -> R.drawable.ollskip
        }
    }

    @Composable
    fun PLLCaseRow() {
        val pllCase = SolutionPhaseDetection(
            solve,
            CubeStatePhaseDetection(CubeState.SOLVED_CUBE_STATE)
        ).getPLL(context)

        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(top = 10.dp)) {
            Text(
                text = "PLL Case",
                fontSize = 25.sp,
                modifier = Modifier.padding(horizontal = 10.dp),
                color = fontColor
            )
            Text(
                text = pllCase.toString(),
                fontSize = 25.sp,
                modifier = Modifier.padding(horizontal = 10.dp),
                color = fontColor
            )
        }
    }

    @Composable
    fun SolveTimeRow() {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.clickable {
                if (!AppSettings.isScrambleGenerationEnabled && !cubeState.value.isSolved()) {
                    solve.prepareForNewSolve()
                    solve.scrambledState = cubeState.value
                    solve.scrambleSequence = "Scramble generation disabled"
                    val intent = Intent(context, SolvingActivity::class.java)
                    context.startActivity(intent)
                }
            }
        ) {
            SolveTimeText(solvePenalty = solve.solvePenalty)
        }
    }

    private fun solveTimeString(solvePenalty: SolvePenalty): Pair<String, String> {
        return when (solvePenalty) {
            SolvePenalty.DNF -> {
                Pair("DNF", "DNF")
            }

            else -> {
                val solveTime = calculateTime()
                val solveSeconds = solveTime.split(".")[0]
                val solveMilliseconds = solveTime.split(".")[1]
                Pair(solveSeconds, solveMilliseconds)
            }
        }
    }

    @Composable
    fun SolveTimeText(solvePenalty: SolvePenalty) {
        val textColor = if (solvePenalty == SolvePenalty.DNF) {
            errorDark
        } else {
            onBackgroundDark
        }
        when (solvePenalty) {
            SolvePenalty.DNF -> {
                Text(
                    text = "DNF",
                    fontSize = 70.sp,
                    color = textColor
                )
            }

            else -> {
                val solveTimePair = solveTimeString(solvePenalty)
                val solveSeconds = solveTimePair.first
                val solveMilliseconds = solveTimePair.second
                Text(
                    text = "$solveSeconds.",
                    fontSize = 70.sp,
                    color = textColor
                )
                Text(
                    text = solveMilliseconds,
                    fontSize = 50.sp,
                    modifier = Modifier.padding(bottom = 6.dp),
                    color = textColor
                )
            }
        }
    }

    @Composable
    fun PhaseStatsDataTable() {
        val columnWeight = 1f

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(vertical = 10.dp)
                .widthIn(100.dp, 300.dp)
                .background(color = surfaceContainerHighestDark, shape = RoundedCornerShape(10.dp))
        ) {
            item {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(start = 1.dp, end = 1.dp, bottom = 1.dp, top = 1.dp)
                        .background(
                            color = surfaceContainerLowDark,
                            shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
                        )
                ) {
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

                val phaseTime = remember { mutableDoubleStateOf(0.0) }
                val phaseTps = remember { mutableDoubleStateOf(0.0) }
                val phaseMoves = remember { mutableIntStateOf(0) }

                phaseTime.doubleValue = roundDouble(
                    solutionPhaseDetection.getPhaseDurationInSeconds(phase, context),
                    100
                )
                phaseTps.doubleValue =
                    roundDouble(
                        solutionPhaseDetection.getPhaseTPS(
                            phase,
                            context
                        ), 10
                    )
                phaseMoves.intValue = solutionPhaseDetection.getPhaseMoveCount(phase, context)
                val modifier = if (index == phases.size - 1) {
                    Modifier
                        .padding(start = 1.dp, end = 1.dp, bottom = 1.dp)
                        .background(
                            color = surfaceContainerLowDark,
                            shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp)
                        )
                } else {
                    Modifier
                        .padding(start = 1.dp, end = 1.dp, bottom = 1.dp)
                        .background(
                            color = surfaceContainerLowDark,
                        )
                }

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = modifier
                ) {
                    TableCell(phase.toString(), columnWeight)
                    TableCell(phaseTime.doubleValue.toString(), columnWeight)
                    TableCell(phaseMoves.intValue.toString(), columnWeight)
                    TableCell(phaseTps.doubleValue.toString(), columnWeight)
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
        weight: Float,
    ) {
        Text(
            text = text,
            modifier = Modifier
                .weight(weight)
                .padding(5.dp),
            maxLines = 1,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            color = fontColor
        )
    }
}

@Composable
@Preview
fun SolveResultsPreview() {
    solve = simpleTestSolve()
    solve.id = 1
    solve.solveStatus = SolveStatus.Solved
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundDark)
    ) {
        SolveResultsUI(LocalContext.current).SolveResults()
    }
}