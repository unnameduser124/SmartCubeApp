package com.example.smartcubeapp.ui.statsUI

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartcubeapp.R
import com.example.smartcubeapp.millisToSeconds
import com.example.smartcubeapp.phasedetection.SolvePhase
import com.example.smartcubeapp.stats.StatsDBConstants
import com.example.smartcubeapp.stats.StatsService

class PhaseStatsSheet(val phase: SolvePhase, val context: Context) {

    private val statsService = StatsService(context)
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun GenerateSheet(onDismiss: () -> Unit) {
        val sheetState = rememberModalBottomSheetState()
        ModalBottomSheet(
            onDismissRequest = { onDismiss() },
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PhaseNameHeader()
                TableLabel(labelText = context.getString(R.string.time_averages_table_label))
                AveragesTable(getTimeAverages()).GenerateTableLayout(context)
                TableLabel(labelText = context.getString(R.string.move_averages_table_label))
                AveragesTable(getMoveAverages()).GenerateTableLayout(context)
            }
        }
    }

    @Composable
    fun PhaseNameHeader(){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = phase.name, fontSize = 25.sp)
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

    private fun getTimeAverages(): List<Pair<Double, Double>> {
        val averagesList = mutableListOf<Pair<Double, Double>>()
        StatsDBConstants.numberOfSolvesValues.forEach {
            val average = millisToSeconds(statsService.averageTimeForPhaseInLastXSolves(it, phase))
            val bestAverage = millisToSeconds(statsService.bestAverageTimeForPhaseInXSolves(it, phase))
            averagesList.add(Pair(average, bestAverage))
        }
        return averagesList
    }

    private fun getMoveAverages(): List<Pair<Double, Double>> {
        val averagesList = mutableListOf<Pair<Double, Double>>()
        StatsDBConstants.numberOfSolvesValues.forEach {
            val average = statsService.averageNumberOfMovesForPhaseInLastXSolves(it, phase)
            val bestAverage = statsService.bestAverageNumberOfMovesForPhaseInXSolves(it, phase)
            averagesList.add(Pair(average, bestAverage))
        }
        return averagesList
    }

    //@OptIn(ExperimentalMaterial3Api::class)
    //@Composable
    //fun HideSheetButton(sheetState: SheetState, onDismiss: () -> Unit) {
    //    val scope = rememberCoroutineScope()
    //    Button(onClick = {
    //        scope.launch { sheetState.hide() }.invokeOnCompletion { onDismiss() }
    //    }) {
    //        Text(text = "Hide Sheet")
    //    }
    //}
}

@Preview
@Composable
fun PhaseStatsSheetPreview() {
    val sheetOpen = remember { mutableStateOf(true) }
    val context = LocalContext.current
    PhaseStatsSheet(SolvePhase.Cross, context).GenerateSheet { sheetOpen.value = false }
}