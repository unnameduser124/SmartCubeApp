package com.example.smartcubeapp.ui.historyUI

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cube_cube.scramble.ScrambleGenerator
import com.example.cube_cube.solveDBDataClasses.SolveData
import com.example.cube_global.roundDouble
import com.example.smartcubeapp.ui.theme.onSurfaceVariantDark
import com.example.smartcubeapp.ui.theme.surfaceContainerHighestDark
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class SolveListItem(
    private val solveData: SolveData,
    private val popupVisible: MutableState<Boolean>,
    private val solveID: MutableState<Long>
) {

    @Composable
    fun GenerateLayout() {
        SolveDataRow()
    }

    @Composable
    fun SolveDataRow() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp)
                .background(color = surfaceContainerHighestDark, shape = RoundedCornerShape(16.dp))
                .padding(start = 10.dp, end = 10.dp)
                .clickable {
                    solveID.value = solveData.id
                    popupVisible.value = true
                },
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {

            val solveTPS = solveData.moveCount / (solveData.solveDuration / 1000.0)
            val dateFormat = SimpleDateFormat("dd.MM HH:mm", Locale.ROOT)
            val dateString = dateFormat.format(
                Calendar.getInstance().apply { timeInMillis = solveData.timestamp }.time
            )
            val durationString = roundDouble(
                solveData.solveDuration / 1000.0,
                100
            ).toString()

            Text(
                text = "${durationString}s",
                fontSize = 20.sp,
                modifier = Modifier
                    .weight(1f),
                textAlign = TextAlign.Center,
                color = onSurfaceVariantDark
            )
            Text(
                text = solveData.moveCount.toString(),
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .weight(1f),
                textAlign = TextAlign.Center,
                color = onSurfaceVariantDark
            )
            Text(
                text = roundDouble(solveTPS, 10).toString(),
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .weight(1f),
                textAlign = TextAlign.Center,
                color = onSurfaceVariantDark
            )
            Text(
                text = dateString,
                fontSize = 20.sp,
                modifier = Modifier
                    .weight(1.5f),
                textAlign = TextAlign.Center,
                color = onSurfaceVariantDark
            )
        }
    }
}

@Preview
@Composable
fun PreviewSolveListItem() {
    val popupVisible = remember { mutableStateOf(false) }
    val popupSolveID = remember { mutableStateOf(-1L) }
    SolveListItem(
        SolveData(
            1,
            10000,
            Calendar.getInstance().timeInMillis,
            1,
            ScrambleGenerator.generateScramble(),
            50,
            0
        ),
        popupVisible,
        popupSolveID
    ).GenerateLayout()
}