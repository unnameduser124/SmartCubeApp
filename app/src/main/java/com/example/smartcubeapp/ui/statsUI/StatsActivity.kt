package com.example.smartcubeapp.ui.statsUI

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.example.smartcubeapp.cube.SolveStatus
import com.example.smartcubeapp.roundDouble
import com.example.smartcubeapp.simpleSolveStateSequence
import com.example.smartcubeapp.simpleTestSolve
import com.example.smartcubeapp.solvedatabase.services.SolveAnalysisDBService
import com.example.smartcubeapp.stats.StatsService

class StatsActivity : ComponentActivity() {

    private var context: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Layout()
        }
    }

    @Composable
    fun Layout(context: Context = this@StatsActivity) {
        this.context = context
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TotalStats()
            GlobalAverageTimeAndMovesRow()
        }
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
        val timeMinutes = StatsService(context).meanTime()/1000.0/60.0
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

}

@Preview
@Composable
fun StatsActivityLayoutPreview() {
    val context = LocalContext.current
    //yes this is way too many solves to be saved every time layout preview is initialized,
    //but I don't think that creating a new pre-populated database just for stats UI tests is justified
    if (StatsService(context).totalNumberOfSolves() < 100) {
        for (i in 0..100) {
            val solve = simpleTestSolve()
            solve.scrambleSequence = "R U R' U'"
            solve.solveStatus = SolveStatus.Solved
            solve.solveStartTime = 1
            solve.solveStateSequence = simpleSolveStateSequence()
            SolveAnalysisDBService(context).saveSolveWithAnalysis(solve)
        }
    }
    StatsActivity().Layout(context)
}