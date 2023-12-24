package com.example.smartcubeapp.ui.statsUI

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cube_cube.cube.SolveStatus
import com.example.cube_database.solvedatabase.services.SolveAnalysisDBService
import com.example.cube_database.solvedatabase.stats.StatsDBConstants
import com.example.cube_database.solvedatabase.stats.StatsService
import com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase
import com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase
import com.example.cube_detection.phasedetection.SolvePhase
import com.example.cube_global.millisToSeconds
import com.example.cube_global.roundDouble
import com.example.smartcubeapp.R

class StatsActivity : ComponentActivity() {

    private var context: Context = this
    private lateinit var statsService: StatsService
    private val phaseStatsSummaryNumberOfSolves = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Layout()
        }
    }

    @Composable
    fun Layout(context: Context = this@StatsActivity) {
        this.context = context
        this.statsService = StatsService(context)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TotalStats()
            GlobalAverageTimeAndMovesRow()
            TableLabel(labelText = context.getString(R.string.time_averages_table_label))
            AveragesTable(getTimeAverages()).GenerateTableLayout(context = context)
            TableLabel(labelText = context.getString(R.string.move_averages_table_label))
            AveragesTable(getMoveAverages()).GenerateTableLayout(context = context)
            PhaseStatsSummary()
            PLLCases()
            OLLCases()
        }
    }

    private fun getTimeAverages(): List<Pair<String, String>> {
        val averagesList = mutableListOf<Pair<String, String>>()
        val totalSolves = statsService.totalNumberOfSolves()
        StatsDBConstants.numberOfSolvesValues.forEach {
            if (it > totalSolves) {
                averagesList.add(Pair("-", "-"))
            } else {
                val average = millisToSeconds(statsService.averageOf(it))
                val bestAverage =
                    millisToSeconds(statsService.bestAverageOf(it))
                val averageRounded = roundDouble(average, 100).toString()
                val bestAverageRounded = roundDouble(bestAverage, 100).toString()
                averagesList.add(Pair(averageRounded, bestAverageRounded))
            }
        }
        return averagesList
    }

    private fun getMoveAverages(): List<Pair<String, String>> {
        val averagesList = mutableListOf<Pair<String, String>>()
        val totalSolves = statsService.totalNumberOfSolves()
        StatsDBConstants.numberOfSolvesValues.forEach {
            if (it > totalSolves) {
                averagesList.add(Pair("-", "-"))
            } else {
                val average = statsService.averageNumberOfMovesPerSolveInLastXSolves(it)
                val bestAverage = statsService.bestAverageNumberOfMovesPerSolveInXSolves(it)
                val averageRounded = roundDouble(average, 10).toString()
                val bestAverageRounded = roundDouble(bestAverage, 10).toString()
                averagesList.add(Pair(averageRounded, bestAverageRounded))
            }
        }
        return averagesList
    }

    @Composable
    fun TotalStats() {
        val time = statsService.totalSolvingTime()
        val timeHours = roundDouble((time / 1000.0 / 60.0 / 60.0), 10)
        val bestTime = statsService.bestTime()

        val bestSolveTime = if (bestTime != null) roundDouble(
            bestTime.solveDuration / 1000.0,
            100
        ).toString() else "-"
        StatLabelAndValue(
            label = context.getString(R.string.total_solves_stats_label),
            value = StatsService(context).totalNumberOfSolves().toString()
        )
        StatLabelAndValue(
            label = context.getString(R.string.total_time_spent_solving_stats_label),
            value = "${timeHours}h"
        )
        StatLabelAndValue(
            label = context.getString(R.string.total_moves_stats_label),
            value = StatsService(context).totalNumberOfMoves().toString()
        )
        StatLabelAndValue(
            label = context.getString(R.string.best_solve_stats_label),
            value = bestSolveTime
        )
    }

    @Composable
    fun GlobalAverageTimeAndMovesRow() {
        val timeSeconds = StatsService(context).meanTime() / 1000.0
        val moves = roundDouble(StatsService(context).meanMoveCount(), 10)
        Row(modifier = Modifier.padding(10.dp), horizontalArrangement = Arrangement.SpaceAround) {
            StatLabelAndValue(
                label = context.getString(R.string.solve_time_average_stats_label),
                value = if (timeSeconds > 0.0) roundDouble(timeSeconds, 100).toString() else "-"
            )
            StatLabelAndValue(
                label = context.getString(R.string.solve_moves_average_stats_label),
                value = if (moves > 0.0) moves.toString() else "-"
            )
        }
    }

    @Composable
    fun StatLabelAndValue(label: String, value: String) {
        Column(
            modifier = Modifier
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = label, fontSize = 15.sp, textAlign = TextAlign.Center)
            Text(text = value, fontSize = 25.sp, textAlign = TextAlign.Center)
        }
    }

    @Composable
    fun TableLabel(labelText: String) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = labelText, fontSize = 25.sp)
        }
    }

    @Composable
    fun PhaseStatsSummary() {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            val modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 5.dp)
            PhaseCard(phase = SolvePhase.Cross, modifier)
            PhaseCard(phase = SolvePhase.F2L, modifier)
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            val modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 5.dp)
            PhaseCard(phase = SolvePhase.OLL, modifier)
            PhaseCard(phase = SolvePhase.PLL, modifier)
        }
    }

    @Composable
    fun PhaseCard(phase: SolvePhase, modifier: Modifier) {
        val phaseSheetVisible = remember { mutableStateOf(false) }
        if (phaseSheetVisible.value) {
            PhaseStatsSheet(phase, context).GenerateSheet {
                phaseSheetVisible.value = false
            }
        }
        Card(modifier = modifier) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
                    .clickable {
                        phaseSheetVisible.value = true
                    }
            ) {
                Text(
                    text = phase.toString(),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
                val time = com.example.cube_global.millisToSeconds(
                    statsService.averageTimeForPhaseInLastXSolves(
                        phaseStatsSummaryNumberOfSolves,
                        phase
                    )
                )
                CardDataLabelAndValue(
                    label = context.getString(R.string.phase_card_average_time),
                    value = roundDouble(time, 100).toString()
                )
                val moves = statsService.averageNumberOfMovesForPhaseInLastXSolves(
                    phaseStatsSummaryNumberOfSolves,
                    phase
                )
                CardDataLabelAndValue(
                    label = context.getString(R.string.phase_card_average_moves),
                    value = com.example.cube_global.roundDouble(moves, 10).toString()
                )
            }
        }
    }

    @Composable
    fun CardDataLabelAndValue(label: String, value: String) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.padding(top = 2.dp)
        ) {
            Text(
                text = label,
                fontSize = 15.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(end = 2.dp)
            )
            Text(text = value, fontSize = 17.sp, textAlign = TextAlign.Center)
        }
    }

    @Composable
    fun PLLCases() {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            val casesWithoutSkip =
                PredefinedPLLCase.values()
                    .copyOfRange(
                        0,
                        PredefinedPLLCase.values().size - 1
                    )
            //Table label
            TableLabel(labelText = context.getString(R.string.pll_cases_table_label))
            //Table header
            LLCaseRow(
                case = context.getString(R.string.ll_cases_table_header_case),
                avgTime = context.getString(R.string.ll_cases_table_header_average_time),
                avgMoves = context.getString(R.string.ll_cases_table_header_average_moves)
            )
            casesWithoutSkip.forEach { case ->
                val avgTime = statsService.averageTimeForPLLCaseInLastXSolves(100, case) / 1000.0
                val avgMoves = statsService.averageNumberOfMovesForPLLCaseInLastXSolves(100, case)
                LLCaseRow(
                    case = case.name,
                    avgTime = if (avgTime > 0.0) roundDouble(avgTime, 100)
                        .toString() else "-",
                    avgMoves = if (avgMoves > 0.0) roundDouble(avgMoves, 10)
                        .toString() else "-"
                )
            }
        }
    }

    @Composable
    fun OLLCases() {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            val casesWithoutSkip =
                PredefinedOLLCase.values()
                    .copyOfRange(
                        0,
                        PredefinedOLLCase.values().size - 1
                    )
            //Table label
            TableLabel(labelText = context.getString(R.string.oll_cases_table_label))
            //Table header
            LLCaseRow(
                case = context.getString(R.string.ll_cases_table_header_case),
                avgTime = context.getString(R.string.ll_cases_table_header_average_time),
                avgMoves = context.getString(R.string.ll_cases_table_header_average_moves)
            )
            casesWithoutSkip.forEach { case ->
                val avgTime = statsService.averageTimeForOLLCaseInLastXSolves(100, case) / 1000.0
                val avgMoves = statsService.averageNumberOfMovesForOLLCaseInLastXSolves(100, case)
                LLCaseRow(
                    case = case.name,
                    avgTime = if (avgTime > 0.0) com.example.cube_global.roundDouble(avgTime, 100)
                        .toString() else "-",
                    avgMoves = if (avgMoves > 0.0) com.example.cube_global.roundDouble(avgMoves, 10)
                        .toString() else "-"
                )
            }
        }
    }

    @Composable
    fun LLCaseRow(case: String, avgTime: String, avgMoves: String) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TableCell(text = case, weight = 1f)
            TableCell(text = avgTime, weight = 1f)
            TableCell(text = avgMoves, weight = 1f)
        }
    }

    @Composable
    fun RowScope.TableCell(
        text: String,
        weight: Float,
        textAlign: TextAlign = TextAlign.Center
    ) {
        Text(
            text = text,
            modifier = Modifier.weight(weight),
            maxLines = 1,
            fontSize = 20.sp,
            textAlign = textAlign
        )
    }
}

@Preview
@Composable
fun StatsActivityLayoutPreview() {
    val context = LocalContext.current
    //yes this is way too many solves to be saved every time layout preview is initialized,
    //but I don't think that creating a new pre-populated database just for stats UI tests is justified
    val solveAnalysisDBService = SolveAnalysisDBService(context)
    if (StatsService(context).totalNumberOfSolves() < 100) {
        for (i in 0..100) {
            val solve = com.example.cube_global.simpleTestSolve()
            solve.scrambleSequence = "R U R' U'"
            solve.solveStatus = SolveStatus.Solved
            solve.solveStartTime = 1
            solve.solveStateSequence = com.example.cube_global.simpleSolveStateSequence()
            solveAnalysisDBService.saveSolveWithAnalysis(solve)
        }
    }
    StatsActivity().Layout(context)
}