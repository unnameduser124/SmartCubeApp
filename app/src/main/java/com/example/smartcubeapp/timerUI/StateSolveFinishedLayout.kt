package com.example.smartcubeapp.timerUI

import android.content.Context
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
import com.example.smartcubeapp.MILLIS_IN_SECOND
import com.example.smartcubeapp.R
import com.example.smartcubeapp.bluetooth.lastMove
import com.example.smartcubeapp.bluetooth.timerState
import com.example.smartcubeapp.cube.CubeState
import com.example.smartcubeapp.cube.Solve
import com.example.smartcubeapp.millisToSeconds
import com.example.smartcubeapp.phasedetection.CubeStatePhaseDetection
import com.example.smartcubeapp.phasedetection.SolutionPhaseDetection
import com.example.smartcubeapp.phasedetection.SolvePhase
import com.example.smartcubeapp.roundDouble
import com.example.smartcubeapp.scramble.Scramble
import com.example.smartcubeapp.scramble.ScrambleGenerator
import com.example.smartcubeapp.scramble.ScramblingMode
import com.example.smartcubeapp.simpleTestSolve
import com.example.smartcubeapp.solvedatabase.services.SolveAnalysisDBService
import com.example.smartcubeapp.stats.StatsService

class StateSolveFinishedLayout(
    private val state: MutableState<TimerState>,
    val cubeState: MutableState<CubeState>,
    private val solve: MutableState<Solve>,
    private val context: Context
) {

    private lateinit var solveSaved: MutableState<Boolean>
    private lateinit var saveTime: MutableState<Long>
    private lateinit var scrambleSequence: MutableState<String>
    private lateinit var solveTime: MutableState<String>
    private lateinit var ao5: MutableState<Double>
    private lateinit var ao12: MutableState<Double>
    private lateinit var ao50: MutableState<Double>
    private lateinit var ao100: MutableState<Double>
    private lateinit var noSolves: MutableState<Int>
    private val generatedScramble = ScrambleGenerator.generateScramble()
    private val scramble: Scramble = Scramble(generatedScramble)
    private var lastState = CubeState(
        mutableListOf(),
        mutableListOf(),
        mutableListOf(),
        mutableListOf(),
    )

    init{
        SolveAnalysisDBService(context).saveSolveWithAnalysis(solve.value)
    }

    @Composable
    fun GenerateLayout() {
        InitializeVariables()

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

        if (cubeState.value != lastState) {
            if(lastState.cornerPositions.isNotEmpty()){
                lastState = cubeState.value
                scramble.processMove(lastMove.value.notation)
                scrambleSequence.value = scramble.getRemainingMoves()
            }
            else{
                lastState = cubeState.value
            }
            if(scramble.scramblingMode == ScramblingMode.Scrambled){
                solve.value.prepareForNewSolve()
                solve.value.scrambledState = cubeState.value
                solve.value.scrambleSequence = scramble.getScramble()
                Toast.makeText(context, "Scrambled", Toast.LENGTH_SHORT).show()
                timerState.value = TimerState.Solving
            }
        }
    }

    @Composable
    fun InitializeVariables(){
        scrambleSequence = remember { mutableStateOf(scramble.getRemainingMoves()) }
        solveTime = remember { mutableStateOf("0.00") }
        ao5 = remember { mutableStateOf(0.00) }
        ao12 = remember { mutableStateOf(0.00) }
        ao50 = remember { mutableStateOf(0.00) }
        ao100 = remember { mutableStateOf(0.00) }
        noSolves = remember { mutableStateOf(0) }
        solveSaved = remember { mutableStateOf(false) }

        val statsService = StatsService(context)
        if(noSolves.value == 0){
            noSolves.value = statsService.totalNumberOfSolves()
        }
        if(noSolves.value >= 5 && ao5.value == 0.00){
            ao5.value = millisToSeconds(statsService.averageOf(5))
        }
        if(noSolves.value >= 12 && ao12.value == 0.00){
            ao12.value = millisToSeconds(statsService.averageOf(12))
        }
        if(noSolves.value >= 50 && ao50.value == 0.00){
            ao50.value = millisToSeconds(statsService.averageOf(50))
        }
        if(noSolves.value >= 100 && ao100.value == 0.00){
            ao100.value = millisToSeconds(statsService.averageOf(100))
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
            Row(horizontalArrangement = Arrangement.Center) {
                val moveCount = solve.value.solveStateSequence.size - 1
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
            PhaseStatsDataTable()
            OLLCaseRow()
            PLLCaseRow()
        }
    }

    @Composable
    fun OLLCaseRow() {
        val ollCase = SolutionPhaseDetection(
            solve.value,
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
            solve.value,
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
            solve.value,
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

    @Composable
    fun ScrambleSequenceRow() {
        val interactionSource = remember { MutableInteractionSource() }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(interactionSource = interactionSource, indication = null) {
                    scramble.generateNewScramble()
                    scrambleSequence.value = scramble.getRemainingMoves()
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
            IconButton(onClick = { /*TODO("Open solve history activity")*/ }) {
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
            IconButton(onClick = { /*TODO("Open statistics activity")*/ }) {
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
                Text(text = "ao5: ${if(noSolves.value >= 5) ao5.value else "-"}", fontSize = 20.sp)
                Text(text = "ao12: ${if(noSolves.value >= 12) ao12.value else "-"}", fontSize = 20.sp)
            }
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "ao50: ${if(noSolves.value >= 50) ao50.value else "-"}", fontSize = 20.sp)
                Text(text = "ao100: ${if(noSolves.value >=100) ao100.value else "-"}", fontSize = 20.sp)
            }

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .padding(bottom = 30.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Solves: ${noSolves.value}", fontSize = 20.sp)
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
        solveTime.value = calculateTime()
        val solveSeconds = solveTime.value.split(".")[0]
        val solveMilliseconds = solveTime.value.split(".")[1]
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
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
            val phases = SolvePhase.values().toMutableList()
            phases.remove(SolvePhase.Scrambled)

            items(phases.size) { index ->

                val solutionPhaseDetection = SolutionPhaseDetection(
                    solve.value,
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
                    roundDouble(solutionPhaseDetection.getPhaseTPS(phase, context), 10)
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
        solve.value.calculateTimeFromStateSequence()
        val time = solve.value.time / MILLIS_IN_SECOND.toDouble()
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

@Preview
@Composable
fun PreviewStateSolveFinishedLayout() {
    val state = remember { mutableStateOf(TimerState.SolveFinished) }
    val cubeState = remember { mutableStateOf(CubeState.SOLVED_CUBE_STATE) }
    val solve = remember { mutableStateOf(simpleTestSolve()) }
    val context = androidx.compose.ui.platform.LocalContext.current
    StateSolveFinishedLayout(state, cubeState, solve, context).GenerateLayout()
}
