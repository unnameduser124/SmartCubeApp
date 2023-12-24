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
import com.example.cube_database.solvedatabase.stats.StatsDBConstants
import com.example.cube_database.solvedatabase.stats.StatsService
import com.example.smartcubeapp.R

class PhaseStatsSheet(val phase: com.example.cube_detection.phasedetection.SolvePhase, val context: Context) {

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

    private fun getTimeAverages(): List<Pair<String, String>> {
        val averagesList = mutableListOf<Pair<String, String>>()
        val totalSolves = statsService.totalNumberOfSolves()
        StatsDBConstants.numberOfSolvesValues.forEach {
            if(it > totalSolves) {
                averagesList.add(Pair("-", "-"))
            }
            else{
                val average = com.example.cube_global.millisToSeconds(
                    statsService.averageTimeForPhaseInLastXSolves(
                        it,
                        phase
                    )
                )
                val bestAverage = com.example.cube_global.millisToSeconds(
                    statsService.bestAverageTimeForPhaseInXSolves(
                        it,
                        phase
                    )
                )
                val averageRounded = com.example.cube_global.roundDouble(average, 100).toString()
                val bestAverageRounded = com.example.cube_global.roundDouble(bestAverage, 100).toString()
                averagesList.add(Pair(averageRounded, bestAverageRounded))
            }
        }
        return averagesList
    }

    private fun getMoveAverages(): List<Pair<String, String>> {
        val averagesList = mutableListOf<Pair<String, String>>()
        val totalSolves = statsService.totalNumberOfSolves()
        StatsDBConstants.numberOfSolvesValues.forEach {
            if(it > totalSolves) {
                averagesList.add(Pair("-", "-"))
            }
            else{
                val average = statsService.averageNumberOfMovesForPhaseInLastXSolves(it, phase)
                val bestAverage = statsService.bestAverageNumberOfMovesForPhaseInXSolves(it, phase)
                val averageRounded = com.example.cube_global.roundDouble(average, 10).toString()
                val bestAverageRounded = com.example.cube_global.roundDouble(bestAverage, 10).toString()
                averagesList.add(Pair(averageRounded, bestAverageRounded))
            }
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
    PhaseStatsSheet(com.example.cube_detection.phasedetection.SolvePhase.Cross, context).GenerateSheet { sheetOpen.value = false }
}