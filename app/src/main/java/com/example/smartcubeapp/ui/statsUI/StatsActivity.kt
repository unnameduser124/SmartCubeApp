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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartcubeapp.R
import com.example.smartcubeapp.casedetection.olldetection.ollcase.PredefinedOLLCase
import com.example.smartcubeapp.casedetection.plldetection.pllcase.PredefinedPLLCase
import com.example.smartcubeapp.cube.SolveStatus
import com.example.smartcubeapp.millisToSeconds
import com.example.smartcubeapp.phasedetection.SolvePhase
import com.example.smartcubeapp.roundDouble
import com.example.smartcubeapp.simpleSolveStateSequence
import com.example.smartcubeapp.simpleTestSolve
import com.example.smartcubeapp.solvedatabase.services.SolveAnalysisDBService
import com.example.smartcubeapp.stats.StatsDBConstants
import com.example.smartcubeapp.stats.StatsService

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

    private fun getTimeAverages(): List<Pair<Double, Double>> {
        val averagesList = mutableListOf<Pair<Double, Double>>()
        StatsDBConstants.numberOfSolvesValues.forEach {
            val average = millisToSeconds(statsService.averageOf(it))
            val bestAverage = millisToSeconds(statsService.bestAverageOf(it))
            averagesList.add(Pair(average, bestAverage))
        }
        return averagesList
    }

    private fun getMoveAverages(): List<Pair<Double, Double>> {
        val averagesList = mutableListOf<Pair<Double, Double>>()
        StatsDBConstants.numberOfSolvesValues.forEach {
            val average = statsService.averageNumberOfMovesPerSolveInLastXSolves(it)
            val bestAverage = statsService.bestAverageNumberOfMovesPerSolveInXSolves(it)
            averagesList.add(Pair(average, bestAverage))
        }
        return averagesList
    }

    @Composable
    fun TotalStats() {
        val time = StatsService(context).totalSolvingTime()
        val timeHours = roundDouble((time / 1000.0 / 60.0 / 60.0), 10)
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
    }

    @Composable
    fun GlobalAverageTimeAndMovesRow() {
        val timeMinutes = StatsService(context).meanTime() / 1000.0
        Row(modifier = Modifier.padding(10.dp), horizontalArrangement = Arrangement.SpaceAround) {
            StatLabelAndValue(
                label = context.getString(R.string.solve_time_average_stats_label),
                value = roundDouble(timeMinutes, 100).toString()
            )
            StatLabelAndValue(
                label = context.getString(R.string.solve_moves_average_stats_label),
                value = roundDouble(StatsService(context).meanMoveCount(), 1).toString()
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
            Text(text = labelText, fontSize = 20.sp)
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
        Card(modifier = modifier) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
                    .clickable {
                        TODO("Open phase details popup")
                    }
            ) {
                Text(
                    text = phase.toString(),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
                val time = millisToSeconds(
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
                    value = roundDouble(moves, 10).toString()
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
                PredefinedPLLCase.values().copyOfRange(0, PredefinedPLLCase.values().size - 1)
            //Table label
            Text(
                text = context.getString(R.string.pll_cases_table_label),
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            )
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
                    avgTime = roundDouble(avgTime, 100).toString(),
                    avgMoves = roundDouble(avgMoves, 10).toString()
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
                PredefinedOLLCase.values().copyOfRange(0, PredefinedOLLCase.values().size - 1)
            //Table label
            Text(
                text = context.getString(R.string.oll_cases_table_label),
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
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
                    avgTime = roundDouble(avgTime, 100).toString(),
                    avgMoves = roundDouble(avgMoves, 10).toString()
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
            val solve = simpleTestSolve()
            solve.scrambleSequence = "R U R' U'"
            solve.solveStatus = SolveStatus.Solved
            solve.solveStartTime = 1
            solve.solveStateSequence = simpleSolveStateSequence()
            solveAnalysisDBService.saveSolveWithAnalysis(solve)
        }
    }
    StatsActivity().Layout(context)
}