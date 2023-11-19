package com.example.smartcubeapp.timerUI

import android.content.Context
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
import com.example.smartcubeapp.R
import com.example.smartcubeapp.bluetooth.lastMove
import com.example.smartcubeapp.bluetooth.timerState
import com.example.smartcubeapp.cube.CubeState
import com.example.smartcubeapp.cube.Solve
import com.example.smartcubeapp.scramble.Scramble
import com.example.smartcubeapp.scramble.ScrambleGenerator
import com.example.smartcubeapp.scramble.ScramblingMode

class StateScramblingLayout(
    private val state: MutableState<TimerState>,
    val cubeState: MutableState<CubeState>,
    val solve: MutableState<Solve>
) {

    private lateinit var scrambleSequence: MutableState<String>
    private lateinit var context: Context
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

    @Composable
    fun GenerateLayout(context: Context) {
        scrambleSequence = remember { mutableStateOf(scramble.getRemainingMoves()) }

        solveTime = remember { mutableStateOf("0.00") }
        ao5 = remember { mutableStateOf(0.00) }
        ao12 = remember { mutableStateOf(0.00) }
        ao50 = remember { mutableStateOf(0.00) }
        ao100 = remember { mutableStateOf(0.00) }
        noSolves = remember { mutableStateOf(0) }
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
            if (cubeState.value != lastState) {
                if(lastState.cornerPositions.isNotEmpty()){
                    lastState = cubeState.value
                    scramble.processMove(lastMove.value.notation)
                    if (scramble.scramblingMode == ScramblingMode.Scrambling) {
                        scrambleSequence.value = scramble.getRemainingMoves()
                    } else {
                        scrambleSequence.value = scramble.getCurrentMove()
                    }
                }
                else{
                    lastState = cubeState.value
                }
                if(scramble.getRemainingMoves() == ""){
                    Toast.makeText(context, "Scrambled", Toast.LENGTH_SHORT).show()
                }
            }

        }

    }

    @Composable
    fun ScrambleSequenceRow() {
        val interactionSource = remember { MutableInteractionSource() }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(interactionSource = interactionSource, indication = null) {
                    try {
                        scramble.generateNewScramble()
                        scrambleSequence.value = scramble.getRemainingMoves()
                    } catch (error: Exception) {
                        println(error.message)
                    }
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
                Text(text = "ao5: ${ao5.value}", fontSize = 20.sp)
                Text(text = "ao12: ${ao12.value}", fontSize = 20.sp)
            }
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "ao50: ${ao50.value}", fontSize = 20.sp)
                Text(text = "ao100: ${ao100.value}", fontSize = 20.sp)
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
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
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
    }
}

@Preview
@Composable
fun StateScramblingLayoutPreview() {
    val state = remember { mutableStateOf(TimerState.Scrambling) }
    val cubeState = remember {
        mutableStateOf(
            CubeState(
                mutableListOf(),
                mutableListOf(),
                mutableListOf(),
                mutableListOf(),
            )
        )
    }
    val solve = remember { mutableStateOf(Solve()) }
    val context = LocalContext.current

    StateScramblingLayout(state, cubeState, solve).GenerateLayout(context)
}
