package com.example.smartcubeapp.ui.timerUI

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cube_bluetooth.bluetooth.cubeState
import com.example.cube_cube.cube.CubeState
import com.example.cube_cube.cube.SolvePenalty
import com.example.cube_cube.cube.SolveStatus
import com.example.cube_global.AppSettings
import com.example.cube_global.roundDouble
import com.example.cube_global.solve
import com.example.smartcubeapp.ui.theme.SmartCubeAppTheme
import com.example.smartcubeapp.ui.theme.backgroundDark
import com.example.smartcubeapp.ui.theme.errorDark
import com.example.smartcubeapp.ui.theme.onBackgroundDark
import kotlinx.coroutines.delay
import java.util.Calendar

enum class SolvingLayoutState {
    Inspection,
    Solving
}

class SolvingActivity : ComponentActivity(
) {

    private lateinit var solvingLayoutState: MutableState<SolvingLayoutState>
    private val inspectionStartTime = Calendar.getInstance().timeInMillis

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
        solvingLayoutState = remember { mutableStateOf(SolvingLayoutState.Inspection) }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = backgroundDark),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (solvingLayoutState.value == SolvingLayoutState.Inspection) {
                InspectionPhaseLayout()
            } else if (solvingLayoutState.value == SolvingLayoutState.Solving) {
                initializeSolve()
                SolvingPhaseLayout()
            }
        }
    }

    @Composable
    fun InspectionPhaseLayout() {
        val inspectionTime = remember { mutableLongStateOf(1L) }

        if (AppSettings.isInspectionEnabled) {
            InitializeInspectionTimer(inspectionTime)
        }

        if (cubeState.value != solve.scrambledState) {
            solvingLayoutState.value = SolvingLayoutState.Solving
        }

        val inspectionTimeFontSize = if (AppSettings.isInspectionEnabled) 50.sp else 25.sp

        val textValues = inspectionTimeString(inspectionTime.longValue)
        Text(
            text = textValues.first,
            fontSize = inspectionTimeFontSize,
            maxLines = 2,
            modifier = Modifier.padding(10.dp),
            color = textValues.second
        )
    }

    private fun inspectionTimeString(inspectionTimeValue: Long): Pair<String, Color> {
        if (AppSettings.isInspectionEnabled) {
            val valueSeconds = "${
                roundDouble(
                    inspectionTimeValue / 1000.0,
                    1
                ).toInt()
            }"
            return if (valueSeconds.toInt() > 0) Pair(valueSeconds, onBackgroundDark)
            else if (valueSeconds.toInt() <= 0 && valueSeconds.toInt() > -2) Pair("+2", errorDark)
            else Pair("DNF", errorDark)
        }
        return Pair("Make a move to start the solve", onBackgroundDark)
    }

    @Composable
    fun InitializeInspectionTimer(inspectionTime: MutableState<Long>) {
        LaunchedEffect(solve.solveStatus) {
            while (inspectionTime.value < 17000 && solve.solvePenalty != SolvePenalty.DNF && solve.solveStatus != SolveStatus.Solving) {
                delay(100)
                inspectionTime.value =
                    15000 - (Calendar.getInstance().timeInMillis - inspectionStartTime)
                evaluatePenalty(inspectionTime.value)
            }
        }
    }

    private fun evaluatePenalty(inspectionTime: Long){
        if (inspectionTime <= 0 && inspectionTime > -2000) {
            solve.solvePenalty = SolvePenalty.PlusTwo
        } else if (inspectionTime < -2000 && solve.solvePenalty != SolvePenalty.DNF) {
            solve.solvePenalty = SolvePenalty.DNF
            solve.solveStatus = SolveStatus.Solved
            val intent = Intent(this, SolvePreparationActivity::class.java)
            this.startActivity(intent)
            this.finish()
        }
    }

    private fun initializeSolve() {
        solve.solveStartTime = Calendar.getInstance().timeInMillis
        solve.solveStatus = SolveStatus.Solving
        solve.scrambledState.timestamp = solve.solveStartTime
        solve.solveStateSequence.add(solve.scrambledState)
    }

    @Composable
    fun SolvingPhaseLayout() {
        val solveTime = remember { mutableLongStateOf(0L) }

        if (AppSettings.isSolvingTimeVisible) {
            InitializeTimer(solveTime)
        }
        AddNewCubeState()

        val solveTimeString = if (AppSettings.isSolvingTimeVisible) "${
            roundDouble(
                solveTime.longValue / 1000.0,
                100
            )
        }s" else "..."
        Text(
            text = solveTimeString,
            fontSize = 50.sp,
            color = onBackgroundDark
        )
    }

    @Composable
    fun InitializeTimer(solveTime: MutableState<Long>) {
        LaunchedEffect(solve.solveStatus) {
            while (solve.solveStatus == SolveStatus.Solving) {
                delay(100)
                solveTime.value =
                    Calendar.getInstance().timeInMillis - solve.solveStartTime
            }
        }
    }

    @Composable
    fun AddNewCubeState(){
        if (newCubeState(cubeState.value)) {
            checkCubeState()
            solve.solveStateSequence.add(cubeState.value)
        }
    }

    private fun checkCubeState() {
        if (cubeState.value.isSolved()) {
            solve.solveStatus = SolveStatus.Solved
            solve.calculateTimeFromStateSequence()
            val intent = Intent(this, SolvePreparationActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}

private fun newCubeState(cubeState: CubeState): Boolean {
    return cubeState != solve.scrambledState
            && cubeState != solve.solveStateSequence.lastOrNull()
            && solve.solveStatus == SolveStatus.Solving
}

@Preview
@Composable
fun StateSolvingLayoutPreview() {
    SolvingActivity().GenerateLayout()
}