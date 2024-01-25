package com.example.smartcubeapp.ui.statsUI

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cube_database.solvedatabase.statsDB.StatsDBConstants
import com.example.smartcubeapp.R
import com.example.smartcubeapp.ui.theme.surfaceContainerHighestDark
import com.example.smartcubeapp.ui.theme.surfaceContainerLowDark

class AveragesTable(val values: List<Pair<String, String>>) {

    private lateinit var context: Context

    @Composable
    fun GenerateTableLayout(context: Context) {
        this.context = context
        Column(
            modifier = Modifier
                .padding(10.dp)
                .widthIn(min = 200.dp, max = 350.dp)
                .background(color = surfaceContainerHighestDark, shape = RoundedCornerShape(10.dp))
        ) {
            Header()
            values.forEachIndexed { index, averagesPair ->
                val average = averagesPair.first
                val bestAverage = averagesPair.second
                val numberOfSolves = StatsDBConstants.numberOfSolvesValues[index]
                TableRow(average, bestAverage, numberOfSolves, index)
            }
        }
    }

    @Composable
    fun Header() {
        Row(
            modifier = Modifier
                .padding(1.dp)
                .background(
                    color = surfaceContainerLowDark,
                    shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
                )
        ) {
            TableCell(
                text = context.getString(R.string.average_of_stats_table_header_label),
                weight = 1f,
                textAlign = TextAlign.End
            )
            TableCell(text = context.getString(R.string.current_stats_table_header), weight = 1f)
            TableCell(text = context.getString(R.string.best_stats_table_header), weight = 1f)
        }
    }

    @Composable
    fun TableRow(average: String, bestAverage: String, numberOfSolves: Int, index: Int) {
        val modifier = if (index == values.size - 1) {
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
        Row(modifier = modifier) {
            TableCell(
                text = String.format(
                    context.getString(R.string.aox_average_stats_label),
                    numberOfSolves
                ),
                weight = 1f,
                textAlign = TextAlign.End
            )
            TableCell(
                text =
                average, weight = 1f
            )
            TableCell(
                text =
                bestAverage, weight = 1f
            )
        }
    }

    @Composable
    fun RowScope.TableCell(
        text: String,
        weight: Float,
        textAlign: TextAlign = TextAlign.Center
    ) {
        Text(
            text = text,
            modifier = Modifier.weight(weight),
            maxLines = 1,
            fontSize = 20.sp,
            textAlign = textAlign
        )
    }
}

@Preview
@Composable
fun AveragesTableLayoutPreview() {
    val context = LocalContext.current
    val averagesList = mutableListOf<Pair<String, String>>()
    for (value in StatsDBConstants.numberOfSolvesValues) {
        val average = com.example.cube_global.millisToSeconds((10000..20000).random().toDouble())
        val bestAverage =
            com.example.cube_global.millisToSeconds((9000..average.toInt()).random().toDouble())
        val averageRounded = com.example.cube_global.roundDouble(average, 100).toString()
        val bestAverageRounded = com.example.cube_global.roundDouble(bestAverage, 100).toString()
        averagesList.add(Pair(averageRounded, bestAverageRounded))
    }
    AveragesTable(averagesList).GenerateTableLayout(context = context)
}
