package com.example.smartcubeapp.ui.statsUI

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
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
import com.example.smartcubeapp.R.string
import com.example.smartcubeapp.ui.theme.SmartCubeAppTheme
import com.example.smartcubeapp.ui.theme.backgroundDark
import com.example.smartcubeapp.ui.theme.onPrimaryDark
import com.example.smartcubeapp.ui.theme.primaryDark
import com.example.smartcubeapp.ui.theme.surfaceContainerHighestDark
import com.example.smartcubeapp.ui.theme.surfaceContainerLowDark
import kotlinx.coroutines.launch

class StatsActivity : ComponentActivity() {

    private lateinit var statsService: StatsService
    private val phaseStatsSummaryNumberOfSolves = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmartCubeAppTheme {
                Surface(color = backgroundDark) {
                    Layout()
                }
            }
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
            TableLabel(labelText = this@StatsActivity.getString(string.time_averages_table_label))
            AveragesTable(timeAverages).GenerateTableLayout(context = this@StatsActivity)
            TableLabel(labelText = this@StatsActivity.getString(string.move_averages_table_label))
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
            label = this.getString(string.total_solves_stats_label),
            value = totalSolves.value.toString()
        )
        StatLabelAndValue(
            label = this.getString(string.total_time_spent_solving_stats_label),
            value = "${timeHours.value}h"
        )
        StatLabelAndValue(
            label = this.getString(string.total_moves_stats_label),
            value = totalMoves.value.toString()
        )
        StatLabelAndValue(
            label = this.getString(string.best_solve_stats_label),
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
                label = this@StatsActivity.getString(string.solve_time_average_stats_label),
                value = if (timeSeconds.value > 0.0) roundDouble(
                    timeSeconds.value,
                    100
                ).toString() else "-"
            )
            StatLabelAndValue(
                label = this@StatsActivity.getString(string.solve_moves_average_stats_label),
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
        Card(
            modifier = modifier,
            colors = CardDefaults.cardColors(
                containerColor = primaryDark,
                contentColor = onPrimaryDark
            )
        ) {
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
                    label = this@StatsActivity.getString(string.phase_card_average_time),
                    value = roundDouble(time.value, 100).toString()
                )
                CardDataLabelAndValue(
                    label = this@StatsActivity.getString(string.phase_card_average_moves),
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
        //Table label
        Row(modifier = Modifier.padding(5.dp)) {
            TableLabel(
                labelText = this@StatsActivity.getString(
                    string.pll_cases_table_label
                )
            )
        }
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .background(color = surfaceContainerHighestDark, shape = RoundedCornerShape(10.dp))
        ) {
            val casesWithoutSkip =
                PredefinedPLLCase.values()
                    .copyOfRange(
                        0,
                        PredefinedPLLCase.values().size - 1
                    )
            val lastIndex = casesWithoutSkip.size - 1
            //Table header
            LLCaseRow(
                case = this@StatsActivity.getString(string.ll_cases_table_header_case),
                avgTime = this@StatsActivity.getString(string.ll_cases_table_header_average_time),
                avgMoves = this@StatsActivity.getString(string.ll_cases_table_header_average_moves),
                index = -1,
                lastIndex = lastIndex
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
                        .toString() else "-",
                    index = index,
                    lastIndex = lastIndex
                )
            }
        }
    }

    @Composable
    fun OLLCases() {
        //Table label
        val casePopupVisible = remember { mutableStateOf(false) }
        val popupCase = remember { mutableStateOf(PredefinedOLLCase.OLL_01) }
        Row(modifier = Modifier.padding(5.dp)) {
            TableLabel(
                labelText = this@StatsActivity.getString(
                    string.oll_cases_table_label
                )
            )
        }
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .background(color = surfaceContainerHighestDark, shape = RoundedCornerShape(10.dp))
        ) {
            val casesWithoutSkip =
                PredefinedOLLCase.values()
                    .copyOfRange(
                        0,
                        PredefinedOLLCase.values().size - 1
                    )
            val lastIndex = casesWithoutSkip.size - 1
            //Table header
            LLCaseRow(
                case = this@StatsActivity.getString(string.ll_cases_table_header_case),
                avgTime = this@StatsActivity.getString(string.ll_cases_table_header_average_time),
                avgMoves = this@StatsActivity.getString(string.ll_cases_table_header_average_moves),
                index = -1,
                lastIndex = lastIndex,
            )
            val casesData = remember { mutableStateListOf<Pair<Double, Double>>() }
            LaunchedEffect(casesData) {
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
            }
            casesWithoutSkip.forEachIndexed { index, case ->
                var avgTime = 0.0
                var avgMoves = 0.0
                if (casesData.isNotEmpty()) {
                    avgTime = casesData[index].first
                    avgMoves = casesData[index].second
                }
                OLLCaseRow(
                    case = case,
                    avgTime = if (avgTime > 0.0) roundDouble(avgTime, 100)
                        .toString() else "-",
                    avgMoves = if (avgMoves > 0.0) roundDouble(avgMoves, 10)
                        .toString() else "-",
                    index = index,
                    lastIndex = lastIndex,
                    casePopupVisible = casePopupVisible,
                    popupCase = popupCase
                )
            }
        }
        if (casePopupVisible.value) {
            OLLCaseImagePopup(
                this,
                getOLLDrawableID(popupCase.value),
                casePopupVisible
            ).GeneratePopup()
        }
    }

    @Composable
    fun OLLCaseRow(
        case: PredefinedOLLCase,
        avgTime: String,
        avgMoves: String,
        index: Int,
        lastIndex: Int,
        popupCase: MutableState<PredefinedOLLCase>,
        casePopupVisible: MutableState<Boolean>
    ) {
        val modifier = when (index) {
            lastIndex -> {
                Modifier
                    .padding(start = 1.dp, end = 1.dp, bottom = 1.dp)
                    .background(
                        color = surfaceContainerLowDark,
                        shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp)
                    )
                    .padding(2.dp)
                    .clickable {
                        casePopupVisible.value = true
                        popupCase.value = case
                    }
            }

            else -> {
                Modifier
                    .padding(start = 1.dp, end = 1.dp, bottom = 1.dp)
                    .background(
                        color = surfaceContainerLowDark,
                    )
                    .padding(2.dp)
                    .clickable {
                        casePopupVisible.value = true
                        popupCase.value = case
                    }
            }
        }
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TableCell(text = case.name, weight = 1f)
            TableCell(text = avgTime, weight = 1f)
            TableCell(text = avgMoves, weight = 1f)
        }
    }

    @Composable
    fun LLCaseRow(
        case: String,
        avgTime: String,
        avgMoves: String,
        index: Int,
        lastIndex: Int,
    ) {
        val modifier = when (index) {
            -1 -> {
                Modifier
                    .padding(1.dp)
                    .background(
                        color = surfaceContainerLowDark,
                        shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
                    )
                    .padding(2.dp)
            }

            lastIndex -> {
                Modifier
                    .padding(start = 1.dp, end = 1.dp, bottom = 1.dp)
                    .background(
                        color = surfaceContainerLowDark,
                        shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp)
                    )
                    .padding(2.dp)
            }

            else -> {
                Modifier
                    .padding(start = 1.dp, end = 1.dp, bottom = 1.dp)
                    .background(
                        color = surfaceContainerLowDark,
                    )
                    .padding(2.dp)
            }
        }
        Row(
            modifier = modifier,
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