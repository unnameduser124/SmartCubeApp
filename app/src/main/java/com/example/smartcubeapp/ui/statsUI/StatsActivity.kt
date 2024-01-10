package com.example.smartcubeapp.ui.statsUI

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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.cube_database.solvedatabase.statsDB.StatsDBConstants
import com.example.cube_database.solvedatabase.statsDB.StatsService
import com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase
import com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase
import com.example.cube_detection.phasedetection.SolvePhase
import com.example.cube_global.millisToHours
import com.example.cube_global.millisToSeconds
import com.example.cube_global.roundDouble
import com.example.smartcubeapp.R
import kotlinx.coroutines.launch

class StatsActivity : ComponentActivity() {

    private lateinit var statsService: StatsService
    private val phaseStatsSummaryNumberOfSolves = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Layout()
        }
    }

    @Composable
    fun Layout() {
        this.statsService = StatsService(this)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val timeAverages = remember { mutableStateListOf<Pair<String, String>>() }
            val moveAverages = remember { mutableStateListOf<Pair<String, String>>() }
            launchOnStarted {
                timeAverages.addAll(getTimeAverages())
                moveAverages.addAll(getMoveAverages())
            }
            TotalStats()
            GlobalAverageTimeAndMovesRow()
            TableLabel(labelText = this@StatsActivity.getString(R.string.time_averages_table_label))
            AveragesTable(timeAverages).GenerateTableLayout(context = this@StatsActivity)
            TableLabel(labelText = this@StatsActivity.getString(R.string.move_averages_table_label))
            AveragesTable(moveAverages).GenerateTableLayout(context = this@StatsActivity)
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
        val timeHours = remember { mutableStateOf(0.0) }
        val bestSolveTime = remember { mutableStateOf("") }
        val totalMoves = remember { mutableStateOf(0) }
        val totalSolves = remember { mutableStateOf(0) }

        launchOnStarted {
            val time = statsService.totalSolvingTime()
            timeHours.value = millisToHours(time)
            val bestTime = statsService.bestTime()
            bestSolveTime.value = if (bestTime != null) roundDouble(
                bestTime.solveDuration / 1000.0,
                100
            ).toString() else "-"
            totalMoves.value = statsService.totalNumberOfMoves()
            totalSolves.value = statsService.totalNumberOfSolves()
        }

        StatLabelAndValue(
            label = this.getString(R.string.total_solves_stats_label),
            value = totalSolves.value.toString()
        )
        StatLabelAndValue(
            label = this.getString(R.string.total_time_spent_solving_stats_label),
            value = "${timeHours.value}h"
        )
        StatLabelAndValue(
            label = this.getString(R.string.total_moves_stats_label),
            value = totalMoves.value.toString()
        )
        StatLabelAndValue(
            label = this.getString(R.string.best_solve_stats_label),
            value = bestSolveTime.value
        )
    }

    @Composable
    fun GlobalAverageTimeAndMovesRow() {
        val timeSeconds = remember { mutableStateOf(0.0) }
        val moves = remember { mutableStateOf(0.0) }

        launchOnStarted {
            timeSeconds.value = statsService.meanTime() / 1000.0
            moves.value = roundDouble(statsService.meanMoveCount(), 10)
        }

        Row(modifier = Modifier.padding(10.dp), horizontalArrangement = Arrangement.SpaceAround) {
            StatLabelAndValue(
                label = this@StatsActivity.getString(R.string.solve_time_average_stats_label),
                value = if (timeSeconds.value > 0.0) roundDouble(
                    timeSeconds.value,
                    100
                ).toString() else "-"
            )
            StatLabelAndValue(
                label = this@StatsActivity.getString(R.string.solve_moves_average_stats_label),
                value = if (moves.value > 0.0) moves.value.toString() else "-"
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
            PhaseStatsSheet(phase, this, lifecycleScope, lifecycle).GenerateSheet {
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
                val time = remember { mutableStateOf(0.0) }
                val moves = remember { mutableStateOf(0.0) }
                launchOnStarted {
                    time.value = millisToSeconds(
                        statsService.averageTimeForPhaseInLastXSolves(
                            phaseStatsSummaryNumberOfSolves,
                            phase
                        )
                    )
                    moves.value = statsService.averageNumberOfMovesForPhaseInLastXSolves(
                        phaseStatsSummaryNumberOfSolves,
                        phase
                    )
                }
                CardDataLabelAndValue(
                    label = this@StatsActivity.getString(R.string.phase_card_average_time),
                    value = roundDouble(time.value, 100).toString()
                )
                CardDataLabelAndValue(
                    label = this@StatsActivity.getString(R.string.phase_card_average_moves),
                    value = roundDouble(moves.value, 10).toString()
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
            TableLabel(labelText = this@StatsActivity.getString(R.string.pll_cases_table_label))
            //Table header
            LLCaseRow(
                case = this@StatsActivity.getString(R.string.ll_cases_table_header_case),
                avgTime = this@StatsActivity.getString(R.string.ll_cases_table_header_average_time),
                avgMoves = this@StatsActivity.getString(R.string.ll_cases_table_header_average_moves)
            )
            val casesData = remember { mutableStateListOf<Pair<Double, Double>>() }
            launchOnStarted {
                casesWithoutSkip.forEach { case ->
                    val avgTime =
                        statsService.averageTimeForPLLCaseInLastXSolves(100, case) / 1000.0
                    val avgMoves =
                        statsService.averageNumberOfMovesForPLLCaseInLastXSolves(100, case)
                    casesData.add(Pair(avgTime, avgMoves))
                }
            }
            casesWithoutSkip.forEachIndexed { index, case ->
                var avgTime = 0.0
                var avgMoves = 0.0
                if (casesData.isNotEmpty()) {
                    avgTime = casesData[index].first
                    avgMoves = casesData[index].second
                }
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
            TableLabel(labelText = this@StatsActivity.getString(R.string.oll_cases_table_label))
            //Table header
            LLCaseRow(
                case = this@StatsActivity.getString(R.string.ll_cases_table_header_case),
                avgTime = this@StatsActivity.getString(R.string.ll_cases_table_header_average_time),
                avgMoves = this@StatsActivity.getString(R.string.ll_cases_table_header_average_moves)
            )
            val casesData = remember { mutableStateListOf<Pair<Double, Double>>() }
            lifecycleScope.launch {
                lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    casesWithoutSkip.forEach { case ->
                        val avgTime =
                            statsService.averageTimeForOLLCaseInLastXSolves(100, case) / 1000.0
                        val avgMoves =
                            statsService.averageNumberOfMovesForOLLCaseInLastXSolves(100, case)
                        casesData.add(Pair(avgTime, avgMoves))
                    }
                }
            }
            casesWithoutSkip.forEachIndexed { index, case ->
                var avgTime = 0.0
                var avgMoves = 0.0
                if (casesData.isNotEmpty()) {
                    avgTime = casesData[index].first
                    avgMoves = casesData[index].second
                }
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

    private fun launchOnStarted(block: () -> Unit) {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                block()
            }
        }
    }
}

@Preview
@Composable
fun StatsActivityLayoutPreview() {
    //val context = LocalContext.current
    ////yes this is way too many solves to be saved every time layout preview is initialized,
    ////but I don't think that creating a new pre-populated database just for stats UI tests is justified
    //val solveAnalysisDBService = SolveAnalysisDBService(context)
    //if (StatsService(context).totalNumberOfSolves() < 100) {
    //    for (i in 0..100) {
    //        val solve = com.example.cube_global.simpleTestSolve()
    //        solve.scrambleSequence = "R U R' U'"
    //        solve.solveStatus = SolveStatus.Solved
    //        solve.solveStartTime = 1
    //        solve.solveStateSequence = com.example.cube_global.simpleSolveStateSequence()
    //        solveAnalysisDBService.saveSolveWithAnalysis(solve)
    //    }
    //}
    //StatsActivity().Layout()
}