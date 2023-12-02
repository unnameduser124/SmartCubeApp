package com.example.smartcubeapp.ui.historyUI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartcubeapp.roundDouble
import com.example.smartcubeapp.scramble.ScrambleGenerator
import com.example.smartcubeapp.solvedatabase.dataclasses.SolveData
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class SolveListItem(val solveData: SolveData) {

    @Composable
    fun GenerateLayout() {
        SolveDataRow()
    }

    @Composable
    fun SolveDataRow() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .background(color = Color.LightGray, shape = RoundedCornerShape(16.dp)),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            val solveTPS = solveData.moveCount / (solveData.solveDuration / 1000.0)
            val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.ROOT)
            val dateString = dateFormat.format(
                Calendar.getInstance().apply { timeInMillis = solveData.timestamp }.time
            )
            val durationString = roundDouble(solveData.solveDuration / 1000.0, 100).toString()

            Text(
                text = durationString,
                fontSize = 25.sp,
                modifier = Modifier.padding(vertical = 10.dp)
            )
            Text(
                text = solveData.moveCount.toString(),
                fontSize = 25.sp
            )
            Text(
                text = roundDouble(solveTPS, 10).toString(),
                fontSize = 25.sp
            )
            Text(
                text = dateString,
                fontSize = 25.sp
            )
        }
    }
}

@Preview
@Composable
fun PreviewSolveListItem() {
    SolveListItem(
        SolveData(
            1,
            10000,
            Calendar.getInstance().timeInMillis,
            1,
            ScrambleGenerator.generateScramble(),
            50,
            0
        )
    ).GenerateLayout()
}