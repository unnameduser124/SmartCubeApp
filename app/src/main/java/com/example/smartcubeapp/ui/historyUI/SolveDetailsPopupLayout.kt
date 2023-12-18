package com.example.smartcubeapp.ui.historyUI

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartcubeapp.casedetection.olldetection.ollcase.PredefinedOLLCase
import com.example.smartcubeapp.casedetection.plldetection.pllcase.PredefinedPLLCase
import com.example.smartcubeapp.phasedetection.SolvePhase
import com.example.smartcubeapp.roundDouble
import com.example.smartcubeapp.simpleSolveStateSequence
import com.example.smartcubeapp.solvedatabase.SolvesDatabaseConstants
import com.example.smartcubeapp.solvedatabase.dataclasses.CrossData
import com.example.smartcubeapp.solvedatabase.dataclasses.CubeStateData
import com.example.smartcubeapp.solvedatabase.dataclasses.F2LData
import com.example.smartcubeapp.solvedatabase.dataclasses.OLLData
import com.example.smartcubeapp.solvedatabase.dataclasses.PLLData
import com.example.smartcubeapp.solvedatabase.dataclasses.SolveAnalysisData
import com.example.smartcubeapp.solvedatabase.dataclasses.SolveData
import com.example.smartcubeapp.solvedatabase.services.CrossDBService
import com.example.smartcubeapp.solvedatabase.services.CubeStateDBService
import com.example.smartcubeapp.solvedatabase.services.F2LDBService
import com.example.smartcubeapp.solvedatabase.services.OLLDBService
import com.example.smartcubeapp.solvedatabase.services.PLLDBService
import com.example.smartcubeapp.solvedatabase.services.SolveAnalysisDBService
import com.example.smartcubeapp.solvedatabase.services.SolveDBService
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class SolveDetailsPopupLayout(
    val popupVisible: MutableState<Boolean>,
    val solvesList: SnapshotStateList<SolveData>
) {


    @Composable
    fun GenerateLayout(context: Context, solveID: Long) {
        val solveDB = SolveAnalysisDBService(context)
        val data = solveDB.getSolveAnalysisForSolve(solveID)
        if (data == null) {
            popupVisible.value = false
            return
        }

        Column(
            modifier = Modifier
                .padding(10.dp)
                .shadow(elevation = 10.dp, shape = RoundedCornerShape(20.dp))
                .background(color = Color.White)
                .widthIn(max = 300.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DateRow(solveDate = data.timestamp)
            ScrambleRow(scrambleSequence = data.scrambleSequence)
            DurationRow(duration = data.solveDuration)
            val tps = roundDouble(
                data.solveStateSequence.size.toDouble() / (data.solveDuration / 1000.0),
                10
            )
            MovesTPSRow(moves = data.solveStateSequence.size, tps = tps)
            PhaseStatsDataTable(data = data)
            val ollCase =
                if (data.ollData.case > 0) PredefinedOLLCase.values()[data.ollData.case] else null
            val pllCase =
                if (data.pllData.case > 0) PredefinedPLLCase.values()[data.pllData.case] else null
            LLCasesRow(ollCase = ollCase, pllCase = pllCase)
            val sequenceWithoutScrambledState =
                data.solveStateSequence.subList(1, data.solveStateSequence.size)
            SolveMoveSequenceRow(sequence = sequenceWithoutScrambledState)
            DeleteButtonRow(id = data.solveID, context = context)
        }
    }

    @Composable
    fun DateRow(solveDate: Long) {
        val cal = Calendar.getInstance().apply { timeInMillis = solveDate }
        val sdf = SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ROOT)
        Text(text = sdf.format(cal.time), fontSize = 18.sp, modifier = Modifier.padding(10.dp))
    }

    @Composable
    fun ScrambleRow(scrambleSequence: String) {
        Text(
            text = scrambleSequence,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(10.dp)
        )
    }

    @Composable
    fun DurationRow(duration: Long) {
        val durationString = roundDouble(duration / 1000.0, 100)
        Text(text = durationString.toString(), fontSize = 25.sp)
    }

    @Composable
    fun MovesTPSRow(moves: Int, tps: Double) {
        val tpsString = roundDouble(tps, 100)
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.padding(10.dp)) {
            Text(text = "$moves moves", fontSize = 20.sp, modifier = Modifier.padding(end = 10.dp))
            Text(
                text = "$tpsString tps",
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 10.dp)
            )
        }

    }


    @Composable
    fun PhaseStatsDataTable(data: SolveAnalysisData) {
        val columnWeight = 1f

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .widthIn(100.dp, 300.dp)
                .padding(10.dp)
        ) {
            item {
                Row(horizontalArrangement = Arrangement.Center) {
                    TableCell("Phase", columnWeight)
                    TableCell("Time", columnWeight)
                    TableCell("Moves", columnWeight)
                    TableCell("TPS", columnWeight)
                }
            }
            val phases = SolvePhase.values().toMutableList()
            phases.remove(SolvePhase.Scrambled)

            items(phases.size) { index ->

                val phase = phases[index]

                val phaseTime = when (phase) {
                    SolvePhase.Cross -> roundDouble(data.crossData.duration / 1000.0, 100)
                    SolvePhase.F2L -> roundDouble(data.f2lData.duration / 1000.0, 100)
                    SolvePhase.OLL -> roundDouble(data.ollData.duration / 1000.0, 100)
                    else -> roundDouble(data.pllData.duration / 1000.0, 100)
                }

                val phaseMoves = when (phase) {
                    SolvePhase.Cross -> data.crossData.moveCount
                    SolvePhase.F2L -> data.f2lData.moveCount
                    SolvePhase.OLL -> data.ollData.moveCount
                    else -> data.pllData.moveCount
                }

                val phaseTps = when (phase) {
                    SolvePhase.Cross -> roundDouble(
                        data.crossData.moveCount.toDouble() / (data.crossData.duration / 1000.0),
                        10
                    )

                    SolvePhase.F2L -> roundDouble(
                        data.f2lData.moveCount.toDouble() / (data.f2lData.duration / 1000.0),
                        10
                    )

                    SolvePhase.OLL -> roundDouble(
                        data.ollData.moveCount.toDouble() / (data.ollData.duration / 1000.0),
                        10
                    )

                    else -> roundDouble(
                        data.pllData.moveCount.toDouble() / (data.pllData.duration / 1000.0),
                        10
                    )
                }

                Row(horizontalArrangement = Arrangement.Center) {
                    TableCell(phase.toString(), columnWeight)
                    TableCell(phaseTime.toString(), columnWeight)
                    TableCell(phaseMoves.toString(), columnWeight)
                    TableCell(phaseTps.toString(), columnWeight)
                }
            }
        }
    }

    @Composable
    fun LLCasesRow(ollCase: PredefinedOLLCase?, pllCase: PredefinedPLLCase?) {
        val columnWeight = 1f
        Column {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth()
            ) {
                TableCell(text = "OLL case", weight = columnWeight)
                TableCell(text = "PLL case", weight = columnWeight)
            }
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .fillMaxWidth()
            ) {
                TableCell(text = ollCase.toString(), weight = columnWeight)
                TableCell(text = pllCase.toString(), weight = columnWeight)
            }
        }
    }

    @Composable
    fun SolveMoveSequenceRow(sequence: List<CubeStateData>) {
        val movesString = sequence.joinToString(separator = " ") { it.lastMove }
        Text(text = "Moves", fontSize = 20.sp, modifier = Modifier.padding(top = 10.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(0.85f)
        ) {
            Text(
                text = movesString,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }
    }

    @Composable
    fun DeleteButtonRow(id: Long, context: Context) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Button(
                onClick = {
                    SolveAnalysisDBService(context).deleteSolveWithAnalysisData(id)
                    solvesList.removeIf { it.id == id }
                    popupVisible.value = false
                },
                modifier = Modifier
                    .fillMaxWidth(0.9f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red
                )
            ) {
                Text(text = "Delete")
            }
        }
    }

    @Composable
    fun RowScope.TableCell(
        text: String,
        weight: Float
    ) {
        Text(
            text = text,
            modifier = Modifier.weight(weight),
            maxLines = 1,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun SolveDetailsPopupPreview() {
    val popupVisible = remember { mutableStateOf(true) }
    val context = LocalContext.current
    context.deleteDatabase(SolvesDatabaseConstants.SOLVE_DATABASE_NAME)
    val solve = SolveData(
        id = 1,
        solveDuration = 15420,
        timestamp = System.currentTimeMillis(),
        scrambledStateID = 1,
        scramble = "R U R' U' D F' U F2 B R R U R' U' D F' U F2 B R R U R' U' D F' U F2 B R",
        moveCount = (50..100).random(),
        penalty = 0
    )
    val id = SolveDBService(context).addSolve(solve)
    val cubeStateList = simpleSolveStateSequence()
    val cubeStateDataList = cubeStateList.map {
        it.solveID = id
        CubeStateData(it, cubeStateList.indexOf(it))
    }
    CubeStateDBService(context).addCubeStateList(cubeStateDataList)
    val pllData = PLLData(
        solveID = id,
        duration = (1000..3000).random().toLong(),
        moveCount = (15..30).random(),
        startStateID = 1,
        endStateID = 1,
        case = 11
    )
    PLLDBService(context).addPLLData(pllData)
    val ollData = OLLData(
        solveID = id,
        duration = (1000..3000).random().toLong(),
        moveCount = (15..30).random(),
        startStateID = 1,
        endStateID = 1,
        case = 32
    )
    OLLDBService(context).addOLLData(ollData)
    val f2lData = F2LData(
        solveID = id,
        duration = (5000..10000).random().toLong(),
        moveCount = (30..50).random(),
        startStateID = 1,
        endStateID = 1
    )
    F2LDBService(context).addF2LData(f2lData)
    val crossData = CrossData(
        solveID = id,
        duration = (1000..5000).random().toLong(),
        moveCount = (10..20).random(),
        startStateID = 1,
        endStateID = 1
    )
    CrossDBService(context).addCrossData(crossData)
    val solvesList = remember { mutableStateListOf<SolveData>() }
    SolveDetailsPopupLayout(popupVisible, solvesList).GenerateLayout(context = context, solveID = 1)
}