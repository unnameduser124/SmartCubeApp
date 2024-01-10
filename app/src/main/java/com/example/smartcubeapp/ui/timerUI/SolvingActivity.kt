package com.example.smartcubeapp.ui.timerUI

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
            GenerateLayout()
        }
    }

    @Composable
    fun GenerateLayout() {
        solvingLayoutState = remember { mutableStateOf(SolvingLayoutState.Inspection) }
        if (solvingLayoutState.value == SolvingLayoutState.Inspection) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                InspectionPhaseLayout()
            }
        } else if (solvingLayoutState.value == SolvingLayoutState.Solving) {
            initializeSolve()
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SolvingPhaseLayout()
            }
        }
    }

    @Composable
    fun InspectionPhaseLayout() {
        val inspectionTime = remember { mutableStateOf(1L) }

        if(AppSettings.isInspectionEnabled){
            InitializeInspectionTimer(inspectionTime)
            if (inspectionTime.value <= 0 && inspectionTime.value > -2000) {
                solve.solvePenalty = SolvePenalty.PlusTwo
            } else if (inspectionTime.value < -2000) {
                solve.solvePenalty = SolvePenalty.DNF
                val intent = Intent(this, SolvePreparationActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        if (cubeState.value != solve.scrambledState) {
            solvingLayoutState.value = SolvingLayoutState.Solving
        }

        val inspectionTimeFontSize = if(AppSettings.isInspectionEnabled) 50.sp else 25.sp

        Text(
            text = inspectionTimeString(inspectionTime.value),
            fontSize = inspectionTimeFontSize,
            maxLines = 2,
            modifier = Modifier.padding(10.dp)
        )
    }

    private fun inspectionTimeString(inspectionTimeValue: Long): String{
        if(AppSettings.isInspectionEnabled){
            val valueSeconds = "${
                roundDouble(
                    inspectionTimeValue / 1000.0,
                    1
                ).toInt()
            }"
            return if (valueSeconds.toInt() > 0) valueSeconds
            else if (valueSeconds.toInt() <= 0 && valueSeconds.toInt() > -2) "+2"
            else "DNF"
        }
        return "Make a move to start the solve"
    }

    @Composable
    fun InitializeInspectionTimer(inspectionTime: MutableState<Long>) {
        LaunchedEffect(solve.solveStatus) {
            while (inspectionTime.value < 17000) {
                delay(100)
                inspectionTime.value =
                    15000 - (Calendar.getInstance().timeInMillis - inspectionStartTime)
            }
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
        val solveTime = remember { mutableStateOf(0L) }

        if (AppSettings.isSolvingTimeVisible) {
            InitializeTimer(solveTime)
        }
        CheckCubeState()

        val solveTimeString = if (AppSettings.isSolvingTimeVisible) "${
            roundDouble(
                solveTime.value / 1000.0,
                100
            )
        }s" else "..."
        Text(
            text = solveTimeString,
            fontSize = 50.sp
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
    fun CheckCubeState() {
        if (newCubeState(cubeState.value)) {
            solve.solveStateSequence.add(cubeState.value)
        }
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