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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cube_cube.cube.CubeState
import com.example.cube_cube.cube.Solve
import com.example.cube_cube.scramble.Scramble
import com.example.cube_cube.scramble.ScrambleGenerator
import com.example.cube_cube.scramble.ScramblingMode
import com.example.cube_database.solvedatabase.stats.StatsService
import com.example.cube_global.TimerState
import com.example.smartcubeapp.R
import com.example.smartcubeapp.ui.historyUI.HistoryActivity
import com.example.smartcubeapp.ui.statsUI.StatsActivity

class StateScramblingLayout(
    val solve: Solve,
) {

    private lateinit var scrambleSequence: MutableState<String>
    private lateinit var context: Context
    private val generatedScramble = ScrambleGenerator.generateScramble()
    private val scramble: Scramble = Scramble(generatedScramble)
    private var lastState = CubeState(
        mutableListOf(),
        mutableListOf(),
        mutableListOf(),
        mutableListOf(),
    )

    @Composable
    fun GenerateLayout(context: Context) {
        this.context = context

        SolveTimeRow()
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
            if (scramble.scramblingMode == ScramblingMode.PreparingToScramble && com.example.cube_bluetooth.bluetooth.cubeState.value.isSolved()) {
                scramble.scramblingMode = ScramblingMode.Scrambling
                scramble.wrongMoves.clear()
                scrambleSequence.value = scramble.getRemainingMoves()
                lastState = com.example.cube_bluetooth.bluetooth.cubeState.value
            }
        } else if (com.example.cube_bluetooth.bluetooth.cubeState.value != lastState) {
            if (lastState.cornerPositions.isNotEmpty()) {
                lastState = com.example.cube_bluetooth.bluetooth.cubeState.value
                scramble.processMove(com.example.cube_bluetooth.bluetooth.lastMove.value.notation)
                scrambleSequence.value = scramble.getRemainingMoves()
            } else {
                lastState = com.example.cube_bluetooth.bluetooth.cubeState.value
            }
            if (scramble.scramblingMode == ScramblingMode.Scrambled) {
                solve.prepareForNewSolve()
                solve.scrambledState = com.example.cube_bluetooth.bluetooth.cubeState.value
                solve.scrambleSequence = scramble.getScramble()
                Toast.makeText(context, "Scrambled", Toast.LENGTH_SHORT).show()
                com.example.cube_bluetooth.bluetooth.timerState.value = TimerState.Solving
            }
        }
    }

    @Composable
    fun ScrambleSequenceRow() {
        scrambleSequence = remember { mutableStateOf(scramble.getRemainingMoves()) }
        if (
            !com.example.cube_bluetooth.bluetooth.cubeState.value.isSolved()
            && scramble.getRemainingMoves() == scramble.getScramble()
            && !lastState.isSolved()
        ) {
            scramble.scramblingMode = ScramblingMode.PreparingToScramble
        }
        handleScrambling()
        val interactionSource = remember { MutableInteractionSource() }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(interactionSource = interactionSource, indication = null) {
                    try {
                        scramble.generateNewScramble()
                        if (!com.example.cube_bluetooth.bluetooth.cubeState.value.isSolved()) {
                            scramble.scramblingMode = ScramblingMode.PreparingToScramble
                            scrambleSequence.value = "Solve the cube before scrambling"
                        } else {
                            scrambleSequence.value = scramble.getRemainingMoves()
                        }
                    } catch (error: Exception) {
                        println(error.message)
                    }
                }
                .padding(16.dp)
                .background(color = Color.LightGray, shape = RoundedCornerShape(20.dp))
        ) {
            Text(
                if (scramble.scramblingMode != ScramblingMode.PreparingToScramble) scrambleSequence.value
                else "Solve the cube before scrambling",
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

    private fun handleCubeNotSolved() {
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
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "0.",
                    fontSize = 70.sp,
                )
                Text(
                    text = "00",
                    fontSize = 50.sp,
                    modifier = Modifier.padding(bottom = 6.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun StateScramblingLayoutPreview() {
    val solve = remember { mutableStateOf(Solve()) }
    val context = LocalContext.current

    StateScramblingLayout(solve.value).GenerateLayout(context)
}
