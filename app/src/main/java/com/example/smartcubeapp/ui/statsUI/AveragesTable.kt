package com.example.smartcubeapp.ui.statsUI

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartcubeapp.R
import com.example.smartcubeapp.millisToSeconds
import com.example.smartcubeapp.roundDouble
import com.example.smartcubeapp.stats.StatsDBConstants

class AveragesTable(val values: List<Pair<Double, Double>>) {

    private lateinit var context: Context

    @Composable
    fun GenerateTableLayout(context: Context) {
        this.context = context
        Column(
            modifier = Modifier
                .padding(10.dp)
                .widthIn(min = 200.dp, max = 350.dp)
        ) {
            Header()
            values.forEachIndexed{ index, averagesPair ->
                val average = averagesPair.first
                val bestAverage = averagesPair.second
                val numberOfSolves = StatsDBConstants.numberOfSolvesValues[index]
                TableRow(average, bestAverage, numberOfSolves)
            }
        }
    }

    @Composable
    fun Header() {
        Row {
            TableCell(
                text = context.getString(R.string.average_of_stats_table_header_label),
                weight = 0.7f,
                textAlign = TextAlign.End
            )
            TableCell(text = context.getString(R.string.current_stats_table_header), weight = 1f)
            TableCell(text = context.getString(R.string.best_stats_table_header), weight = 1f)
        }
    }

    @Composable
    fun TableRow(average: Double, bestAverage: Double, numberOfSolves: Int) {
        Row {
            TableCell(
                text = String.format(
                    context.getString(R.string.aox_average_stats_label),
                    numberOfSolves
                ),
                weight = 0.7f,
                textAlign = TextAlign.End
            )
            TableCell(
                text =
                roundDouble(average, 100).toString(), weight = 1f
            )
            TableCell(
                text =
                roundDouble(bestAverage, 100).toString(), weight = 1f
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
    val averagesList = mutableListOf<Pair<Double, Double>>()
    for(value in StatsDBConstants.numberOfSolvesValues){
        val average = millisToSeconds((10000..20000).random().toDouble())
        val bestAverage = millisToSeconds((9000..average.toInt()).random().toDouble())
        averagesList.add(Pair(average, bestAverage))
    }
    AveragesTable(averagesList).GenerateTableLayout(context = context)
}
