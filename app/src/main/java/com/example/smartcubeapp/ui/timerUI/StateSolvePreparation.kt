package com.example.smartcubeapp.ui.timerUI

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cube_bluetooth.bluetooth.cubeState
import com.example.cube_bluetooth.bluetooth.lastMove
import com.example.cube_bluetooth.bluetooth.timerState
import com.example.cube_cube.cube.CubeState
import com.example.cube_cube.cube.Solve
import com.example.cube_cube.cube.SolvePenalty
import com.example.cube_cube.cube.SolveStatus
import com.example.cube_cube.scramble.Scramble
import com.example.cube_cube.scramble.ScrambleGenerator
import com.example.cube_cube.scramble.ScramblingMode
import com.example.cube_database.solvedatabase.solvesDB.services.SolveAnalysisDBService
import com.example.cube_database.solvedatabase.statsDB.StatsService
import com.example.cube_detection.phasedetection.CubeStatePhaseDetection
import com.example.cube_detection.phasedetection.SolutionPhaseDetection
import com.example.cube_global.TimerState
import com.example.smartcubeapp.R
import com.example.smartcubeapp.ui.historyUI.HistoryActivity
import com.example.smartcubeapp.ui.statsUI.StatsActivity
import java.util.Calendar

class StateSolvePreparationLayout(
    private val solve: Solve
) {

    private lateinit var scrambleSequence: MutableState<String>
    private val generatedScramble = ScrambleGenerator.generateScramble()
    private val scramble: Scramble = Scramble(generatedScramble)
    private lateinit var context: Context
    private var lastState = CubeState(
        mutableListOf(),
        mutableListOf(),
        mutableListOf(),
        mutableListOf(),
    )

    @Composable
    fun GenerateLayout(context: Context) {
        this.context = context
        if (solve.id == -1L && solve.solvePenalty != SolvePenalty.DNF && solve.solveStatus == SolveStatus.Solved) {
            solve.date = Calendar.getInstance()
            val id = SolveAnalysisDBService(context).saveSolveWithAnalysis(solve).solveID
            solve.id = id
        }

        SolveResults()
        CurrentStatsColumn()
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
        ) {
            ScrambleSequenceRow()
            SolveHistoryButtonRow()
            StatisticsButtonRow()
            SettingsButtonRow()
        }
    }

    private fun handleScrambling() {
        if (scramble.scramblingMode == ScramblingMode.PreparingToScramble) {
            scrambleSequence.value = "Solve the cube before scrambling"
            if (scramble.scramblingMode == ScramblingMode.PreparingToScramble && cubeState.value.isSolved()) {
                scramble.scramblingMode = ScramblingMode.Scrambling
                scramble.wrongMoves.clear()
                scrambleSequence.value = scramble.getRemainingMoves()
                lastState = cubeState.value
            }
        } else if (cubeState.value != lastState) {
            if (lastState.cornerPositions.isNotEmpty()) {
                lastState = cubeState.value
                scramble.processMove(lastMove.value.notation)
                scrambleSequence.value = scramble.getRemainingMoves()
            } else {
                lastState = cubeState.value
            }
            if (scramble.scramblingMode == ScramblingMode.Scrambled) {
                solve.prepareForNewSolve()
                solve.scrambledState = cubeState.value
                solve.scrambleSequence = scramble.getScramble()
                Toast.makeText(context, "Scrambled", Toast.LENGTH_SHORT).show()
                timerState.value = TimerState.Solving
            }
        }
    }

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
                        com.example.cube_global.roundDouble(solve.getTurnsPerSecond(), 100)
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
    fun CrossSideRow() {
        val crossSide = SolutionPhaseDetection(
            solve,
            CubeStatePhaseDetection(CubeState.SOLVED_CUBE_STATE)
        ).getCrossSide() ?: return

        Row(horizontalArrangement = Arrangement.Center) {
            Text(
                text = "Cross Side",
                fontSize = 25.sp,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            Text(
                text = crossSide.sideName,
                fontSize = 25.sp,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
        }
    }

    private fun handleCubeNotSolved() {
        if (
            !com.example.cube_bluetooth.bluetooth.cubeState.value.isSolved()
            && scramble.getRemainingMoves() == scramble.getScramble()
            && !lastState.isSolved()
        ) {
            scramble.scramblingMode = ScramblingMode.PreparingToScramble
        }
    }

    @Composable
    fun ScrambleSequenceRow() {
        scrambleSequence = remember { mutableStateOf(scramble.getRemainingMoves()) }
        handleCubeNotSolved()
        handleScrambling()
        val interactionSource = remember { MutableInteractionSource() }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(interactionSource = interactionSource, indication = null) {
                    scramble.generateNewScramble()
                    scrambleSequence.value = scramble.getRemainingMoves()
                    if (!com.example.cube_bluetooth.bluetooth.cubeState.value.isSolved()) {
                        scramble.scramblingMode = ScramblingMode.PreparingToScramble
                        scrambleSequence.value = "Solve the cube before scrambling"
                    } else {
                        scrambleSequence.value = scramble.getRemainingMoves()
                    }
                }
                .padding(16.dp)
                .background(color = Color.LightGray, shape = RoundedCornerShape(20.dp))
        ) {
            Text(
                scrambleSequence.value,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp, start = 15.dp, end = 5.dp),
                fontSize = 25.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                textAlign = TextAlign.Center
            )
        }
    }

    @Composable
    fun SolveHistoryButtonRow() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp, top = 5.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                val intent = Intent(context, HistoryActivity::class.java)
                context.startActivity(intent)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_history_24),
                    contentDescription = "Solve history button",
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }

    @Composable
    fun StatisticsButtonRow() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp, top = 5.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                val intent = Intent(context, StatsActivity::class.java)
                context.startActivity(intent)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_stats_24),
                    contentDescription = "Statistics button",
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }

    @Composable
    fun CurrentStatsColumn() {
        val statsService = StatsService(context)
        val noSolves = statsService.totalNumberOfSolves()
        var ao5 = "-"
        var ao12 = "-"
        var ao50 = "-"
        var ao100 = "-"

        if (noSolves >= 5) {
            ao5 = com.example.cube_global.millisToSeconds(statsService.averageOf(5)).toString()
        }
        if (noSolves >= 12) {
            ao12 = com.example.cube_global.millisToSeconds(statsService.averageOf(12)).toString()
        }
        if (noSolves >= 50) {
            ao50 = com.example.cube_global.millisToSeconds(statsService.averageOf(50)).toString()
        }
        if (noSolves >= 100) {
            ao100 = com.example.cube_global.millisToSeconds(statsService.averageOf(100)).toString()
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "ao5: $ao5", fontSize = 20.sp)
                Text(text = "ao12: $ao12", fontSize = 20.sp)
            }
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "ao50: $ao50", fontSize = 20.sp)
                Text(text = "ao100: $ao100", fontSize = 20.sp)
            }

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .padding(bottom = 30.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Solves: ${noSolves}", fontSize = 20.sp)
            }
        }
    }

    @Composable
    fun SettingsButtonRow() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp, top = 5.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /*TODO("Open settings activity")*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_settings_24),
                    contentDescription = "Settings button",
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }

    @Composable
    fun SolveTimeRow() {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            if (solve.solvePenalty == SolvePenalty.DNF) {
                Text(
                    text = "DNF",
                    fontSize = 70.sp,
                )
            } else if (solve.solvePenalty == SolvePenalty.PlusTwo) {
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
            } else {
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
                com.example.cube_detection.phasedetection.SolvePhase.values().toMutableList()
            phases.remove(com.example.cube_detection.phasedetection.SolvePhase.Scrambled)

            items(phases.size) { index ->

                val solutionPhaseDetection =
                    com.example.cube_detection.phasedetection.SolutionPhaseDetection(
                        solve,
                        com.example.cube_detection.phasedetection.CubeStatePhaseDetection(CubeState.SOLVED_CUBE_STATE)
                    )
                val phase = phases[index]

                val phaseTime = remember { mutableStateOf(0.0) }
                val phaseTps = remember { mutableStateOf(0.0) }
                val phaseMoves = remember { mutableStateOf(0) }

                phaseTime.value = com.example.cube_global.roundDouble(
                    solutionPhaseDetection.getPhaseDurationInSeconds(phase, context),
                    100
                )
                phaseTps.value =
                    com.example.cube_global.roundDouble(
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
        try{
            solve.calculateTimeFromStateSequence()
        }
        catch(exception: IllegalArgumentException){
            print(exception.message)
            return "0.0"
        }
        val time =
            when (solve.solvePenalty) {
                SolvePenalty.None -> solve.time / com.example.cube_global.MILLIS_IN_SECOND.toDouble()
                SolvePenalty.PlusTwo -> solve.time / com.example.cube_global.MILLIS_IN_SECOND.toDouble() + 2.0
                else -> 0.0
            }
        val timeRounded = com.example.cube_global.roundDouble(time, 100)
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

@Preview
@Composable
fun PreviewStateSolveFinishedLayout() {
    val solve = Solve()
    val context = androidx.compose.ui.platform.LocalContext.current
    StateSolvePreparationLayout(solve).GenerateLayout(context)
}
