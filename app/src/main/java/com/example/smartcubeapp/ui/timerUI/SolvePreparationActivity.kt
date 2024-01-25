package com.example.smartcubeapp.ui.timerUI

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cube_cube.cube.CubeState
import com.example.cube_cube.cube.SolvePenalty
import com.example.cube_cube.cube.SolveStatus
import com.example.cube_cube.scramble.Scramble
import com.example.cube_cube.scramble.ScrambleGenerator
import com.example.cube_cube.scramble.ScramblingMode
import com.example.cube_database.solvedatabase.solvesDB.services.SolveAnalysisDBService
import com.example.cube_database.solvedatabase.statsDB.StatsService
import com.example.cube_global.AppSettings
import com.example.cube_global.millisToSeconds
import com.example.cube_global.solve
import com.example.smartcubeapp.R
import com.example.smartcubeapp.ui.historyUI.HistoryActivity
import com.example.smartcubeapp.ui.settingsUI.SettingsActivity
import com.example.smartcubeapp.ui.statsUI.StatsActivity
import com.example.smartcubeapp.ui.theme.SmartCubeAppTheme
import com.example.smartcubeapp.ui.theme.backgroundDark
import com.example.smartcubeapp.ui.theme.errorDark
import com.example.smartcubeapp.ui.theme.onBackgroundDark
import com.example.smartcubeapp.ui.theme.onErrorDark
import com.example.smartcubeapp.ui.theme.onPrimaryDark
import com.example.smartcubeapp.ui.theme.primaryDark
import java.util.Calendar
import kotlin.concurrent.thread

class SolvePreparationActivity : ComponentActivity() {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SmartCubeAppTheme {
                GenerateLayout()
            }
        }
    }

    @Composable
    fun GenerateLayout() {
        this.context = this
        thread {
            if (solve.id == -1L && solve.solvePenalty != SolvePenalty.DNF && solve.solveStatus == SolveStatus.Solved) {
                solve.date = Calendar.getInstance()
                val id = SolveAnalysisDBService(context).saveSolveWithAnalysis(solve).solveID
                solve.id = id
            }
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundDark),
            verticalArrangement = Arrangement.Top,
        ) {
            ScrambleSequenceRow()
            SolveHistoryButtonRow()
            StatisticsButtonRow()
            SettingsButtonRow()
        }
        SolveResultsUI(context).SolveResults()
        CurrentStatsColumn()
    }


    @Composable
    fun ScrambleSequenceRow() {
        if (AppSettings.isScrambleGenerationEnabled) {
            HandleScrambleDisplay()
        } else {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(color = primaryDark, shape = RoundedCornerShape(20.dp))
            ) {
                Text(
                    "Click solve time to go into inspection",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 10.dp, start = 15.dp, end = 5.dp),
                    fontSize = 25.sp,
                    maxLines = 2,
                    textAlign = TextAlign.Center,
                    color = onPrimaryDark
                )
            }
        }
    }

    @Composable
    fun HandleScrambleDisplay() {
        scrambleSequence = remember { mutableStateOf(scramble.getRemainingMoves()) }
        val scrambleHandler = ScrambleHandler(context, this, scramble, scrambleSequence)
        scrambleHandler.handle(lastState)

        val interactionSource = remember { MutableInteractionSource() }
        val rowColor = if(scramble.scramblingMode == ScramblingMode.Fixing) {
            errorDark
        } else {
            primaryDark
        }
        val textColor = if(scramble.scramblingMode == ScramblingMode.Fixing) {
            onErrorDark
        } else {
            onPrimaryDark
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(interactionSource = interactionSource, indication = null) {
                    scrambleHandler.handleScrambleGeneration()
                }
                .padding(16.dp)
                .background(color = rowColor, shape = RoundedCornerShape(20.dp))
        ) {
            Text(
                scrambleSequence.value,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp, start = 15.dp, end = 5.dp),
                fontSize = 25.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                textAlign = TextAlign.Center,
                color = textColor
            )
        }
    }

    @Composable
    fun SolveHistoryButtonRow() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 8.dp, top = 5.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    val intent = Intent(context, HistoryActivity::class.java)
                    context.startActivity(intent)
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_history_24),
                    contentDescription = "Solve history button",
                    modifier = Modifier.size(40.dp),
                    tint = primaryDark
                )
            }
        }
    }

    @Composable
    fun StatisticsButtonRow() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 8.dp, top = 5.dp),
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
                    modifier = Modifier.size(40.dp),
                    tint = primaryDark
                )
            }
        }
    }

    @Composable
    fun SettingsButtonRow() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 8.dp, top = 5.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                val intent = Intent(context, SettingsActivity::class.java)
                context.startActivity(intent)
                finish()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_settings_24),
                    contentDescription = "Settings button",
                    modifier = Modifier.size(40.dp),
                    tint = primaryDark
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
            ao5 = millisToSeconds(statsService.averageOf(5)).toString()
        }
        if (noSolves >= 12) {
            ao12 = millisToSeconds(statsService.averageOf(12)).toString()
        }
        if (noSolves >= 50) {
            ao50 = millisToSeconds(statsService.averageOf(50)).toString()
        }
        if (noSolves >= 100) {
            ao100 = millisToSeconds(statsService.averageOf(100)).toString()
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
                Text(text = "ao5: $ao5", fontSize = 20.sp, color = onBackgroundDark)
                Text(text = "ao12: $ao12", fontSize = 20.sp, color = onBackgroundDark)
            }
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "ao50: $ao50", fontSize = 20.sp, color = onBackgroundDark)
                Text(text = "ao100: $ao100", fontSize = 20.sp, color = onBackgroundDark)
            }

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .padding(bottom = 30.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Solves: $noSolves", fontSize = 20.sp, color = onBackgroundDark)
            }
        }
    }
}

@Preview
@Composable
fun PreviewStateSolveFinishedLayout() {
    SolvePreparationActivity().GenerateLayout()
}
