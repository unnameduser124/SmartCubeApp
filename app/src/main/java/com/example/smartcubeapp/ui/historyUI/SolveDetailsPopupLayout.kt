package com.example.smartcubeapp.ui.historyUI

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import com.example.cube_cube.solveDBDataClasses.CrossData
import com.example.cube_cube.solveDBDataClasses.CubeStateData
import com.example.cube_cube.solveDBDataClasses.F2LData
import com.example.cube_cube.solveDBDataClasses.OLLData
import com.example.cube_cube.solveDBDataClasses.PLLData
import com.example.cube_cube.solveDBDataClasses.SolveAnalysisData
import com.example.cube_cube.solveDBDataClasses.SolveData
import com.example.cube_database.solvedatabase.solvesDB.SolvesDatabaseConstants
import com.example.cube_database.solvedatabase.solvesDB.services.CrossDBService
import com.example.cube_database.solvedatabase.solvesDB.services.CubeStateDBService
import com.example.cube_database.solvedatabase.solvesDB.services.F2LDBService
import com.example.cube_database.solvedatabase.solvesDB.services.OLLDBService
import com.example.cube_database.solvedatabase.solvesDB.services.PLLDBService
import com.example.cube_database.solvedatabase.solvesDB.services.SolveAnalysisDBService
import com.example.cube_database.solvedatabase.solvesDB.services.SolveDBService
import com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase
import com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase
import com.example.cube_detection.phasedetection.SolvePhase
import com.example.cube_global.roundDouble
import com.example.smartcubeapp.R
import com.example.smartcubeapp.ui.popups.ConfirmationPopup
import com.example.smartcubeapp.ui.theme.backgroundDark
import com.example.smartcubeapp.ui.theme.errorDark
import com.example.smartcubeapp.ui.theme.onBackgroundDark
import com.example.smartcubeapp.ui.theme.onErrorDark
import com.example.smartcubeapp.ui.theme.onSurfaceVariantDark
import com.example.smartcubeapp.ui.theme.surfaceContainerHighestDark
import com.example.smartcubeapp.ui.theme.surfaceContainerLowDark
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.concurrent.thread

class SolveDetailsPopupLayout(
    private val popupVisible: MutableState<Boolean>,
    private val solvesList: SnapshotStateList<SolveData>
) {

    @Composable
    fun loadData(
        context: Context,
        solveID: Long,
        lifecycleScope: LifecycleCoroutineScope
    ): SolveAnalysisData? {
        val data: MutableState<SolveAnalysisData?> = remember {
            mutableStateOf(null)
        }
        LaunchedEffect(data) {
            lifecycleScope.launch {
                data.value = SolveAnalysisDBService(context).getSolveAnalysisForSolve(solveID)
                if (data.value == null) {
                    popupVisible.value = false
                }
            }
        }
        return data.value
    }


    @Composable
    fun GenerateLayout(context: Context, solveID: Long, lifecycleScope: LifecycleCoroutineScope) {
        val data = loadData(context, solveID, lifecycleScope)

        if(data == null){
            SolveDataLoadingLayout()
        }
        else {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .shadow(elevation = 10.dp, shape = RoundedCornerShape(20.dp))
                    .background(color = backgroundDark)
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
                MovesTPSRow(moves = data.solveStateSequence.size - 1, tps = tps)

                PhaseStatsDataTable(data = data)
                val ollCase =
                    if (data.ollData.case >= 0) PredefinedOLLCase.values()[data.ollData.case] else null
                val pllCase =
                    if (data.pllData.case >= 0) PredefinedPLLCase.values()[data.pllData.case] else null

                LLCasesRow(ollCase = ollCase, pllCase = pllCase)
                val sequenceWithoutScrambledState =
                    data.solveStateSequence.subList(
                        1,
                        data.solveStateSequence.size
                    )
                SolveMoveSequenceRow(sequence = sequenceWithoutScrambledState)
                DeleteButtonRow(id = data.solveID, context = context)
            }
        }
    }

    @Composable
    fun SolveDataLoadingLayout() {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .shadow(elevation = 10.dp, shape = RoundedCornerShape(20.dp))
                .background(color = backgroundDark)
                .widthIn(max = 300.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Loading...", modifier = Modifier.padding(20.dp), color = onBackgroundDark)
        }
    }

    @Composable
    fun DateRow(solveDate: Long) {
        val cal = Calendar.getInstance().apply { timeInMillis = solveDate }
        val sdf = SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ROOT)
        Text(
            text = sdf.format(cal.time),
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 20.dp, bottom = 10.dp),
            color = onBackgroundDark
        )
    }

    @Composable
    fun ScrambleRow(scrambleSequence: String) {
        Text(text = "Scramble", fontSize = 15.sp, color = onBackgroundDark)
        Row(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .padding(top = 2.dp, bottom = 10.dp)
                .background(
                    color = surfaceContainerHighestDark,
                    shape = RoundedCornerShape(10.dp)
                )
        ) {
            Text(
                text = scrambleSequence,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(5.dp),
                color = onSurfaceVariantDark
            )
        }
    }

    @Composable
    fun DurationRow(duration: Long) {
        val durationString = "${roundDouble(duration / 1000.0, 100)}s"
        Text(text = durationString, fontSize = 25.sp, color = onBackgroundDark)
    }

    @Composable
    fun MovesTPSRow(moves: Int, tps: Double) {
        val tpsString = roundDouble(tps, 100)
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.padding(10.dp)) {
            Text(
                text = "$moves moves",
                fontSize = 20.sp,
                modifier = Modifier.padding(end = 10.dp),
                color = onBackgroundDark
            )
            Text(
                text = "$tpsString tps",
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 10.dp),
                color = onBackgroundDark
            )
        }

    }


    @Composable
    fun PhaseStatsDataTable(data: SolveAnalysisData) {
        val columnWeight = 1f

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 10.dp)
                .widthIn(100.dp, 300.dp)
                .background(color = surfaceContainerHighestDark, shape = RoundedCornerShape(10.dp))
        ) {
            item {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(1.dp)
                        .background(
                            color = surfaceContainerLowDark,
                            shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
                        )
                ) {
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

                val phaseTime = phaseTimeText(phase, data)

                val phaseMoves = phaseMovesText(phase, data)

                val phaseTps = phaseTPSText(phase, data)

                val modifier = if (index == phases.size - 1) {
                    Modifier
                        .padding(start = 1.dp, end = 1.dp, bottom = 1.dp)
                        .background(
                            color = surfaceContainerLowDark,
                            shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp)
                        )
                } else {
                    Modifier
                        .padding(start = 1.dp, end = 1.dp, bottom = 1.dp)
                        .background(
                            color = surfaceContainerLowDark,
                        )
                }

                Row(horizontalArrangement = Arrangement.Center, modifier = modifier) {
                    TableCell(phase.toString(), columnWeight)
                    TableCell(phaseTime, columnWeight)
                    TableCell(phaseMoves, columnWeight)
                    TableCell(phaseTps, columnWeight)
                }
            }
        }
    }

    private fun phaseTimeText(phase: SolvePhase, data: SolveAnalysisData): String {
        return when (phase) {
            SolvePhase.Cross -> roundDouble(
                data.crossData.duration / 1000.0,
                100
            )

            SolvePhase.F2L -> roundDouble(
                data.f2lData.duration / 1000.0,
                100
            )

            SolvePhase.OLL -> roundDouble(
                data.ollData.duration / 1000.0,
                100
            )

            else -> roundDouble(data.pllData.duration / 1000.0, 100)
        }.toString()
    }

    private fun phaseMovesText(phase: SolvePhase, data: SolveAnalysisData): String {
        return when (phase) {
            SolvePhase.Cross -> data.crossData.moveCount
            SolvePhase.F2L -> data.f2lData.moveCount
            SolvePhase.OLL -> data.ollData.moveCount
            else -> data.pllData.moveCount
        }.toString()
    }

    private fun phaseTPSText(phase: SolvePhase, data: SolveAnalysisData): String {
        return when (phase) {
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
        }.toString()
    }


    @Composable
    fun LLCasesRow(ollCase: PredefinedOLLCase?, pllCase: PredefinedPLLCase?) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                "OLL case",
                fontSize = 20.sp,
                color = onBackgroundDark,
            )
            Row(
                modifier = Modifier
                    .padding(top = 5.dp, bottom = 10.dp)
                    .background(
                        color = surfaceContainerHighestDark,
                        shape = RoundedCornerShape(16.dp)
                    )
            ) {
                Image(
                    painter = painterResource(getOLLDrawableID(ollCase!!)),
                    contentDescription = "OLL case image",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(5.dp)
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .fillMaxWidth(0.75f)
            ) {
                Text(text = "PLL case", fontSize = 20.sp, color = onBackgroundDark)
                Text(text = pllCase.toString(), fontSize = 20.sp, color = onBackgroundDark)
            }
        }
    }

    @Composable
    fun SolveMoveSequenceRow(sequence: List<CubeStateData>) {
        val movesString = sequence.joinToString(separator = " ") { it.lastMove }
        val showAllMoves = remember { mutableStateOf(false) }
        Text(
            text = "Moves",
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 10.dp),
            color = onBackgroundDark
        )
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(0.85f)
                .clickable {
                    showAllMoves.value = !showAllMoves.value
                }
        ) {
            Text(
                text = movesString,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = onBackgroundDark,
                overflow = TextOverflow.Ellipsis,
                maxLines = if (showAllMoves.value) Int.MAX_VALUE else 1,
            )
        }
    }

    @Composable
    fun DeleteButtonRow(id: Long, context: Context) {
        val confirmationPopupVisible = remember { mutableStateOf(false) }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Button(
                onClick = {
                    confirmationPopupVisible.value = true
                },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = errorDark
                )
            ) {
                Text(text = "Delete", color = onErrorDark)
            }
        }
        if (confirmationPopupVisible.value) {
            ConfirmationPopup(context, confirmationPopupVisible).GeneratePopup {
                thread {
                    SolveAnalysisDBService(context).deleteSolveWithAnalysisData(id)
                    solvesList.removeIf { it.id == id }
                    popupVisible.value = false
                }
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
            modifier = Modifier
                .weight(weight)
                .padding(vertical = 3.dp),
            maxLines = 1,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            color = onBackgroundDark
        )
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
    val cubeStateList = com.example.cube_global.simpleSolveStateSequence()
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
    //SolveDetailsPopupLayout(popupVisible, solvesList).GenerateLayout(context = context, solveID = 1, lifecycleScope)
}