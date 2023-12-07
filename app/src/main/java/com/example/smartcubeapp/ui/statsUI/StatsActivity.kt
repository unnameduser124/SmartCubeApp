package com.example.smartcubeapp.ui.statsUI

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartcubeapp.R
import com.example.smartcubeapp.stats.StatsService

class StatsActivity : ComponentActivity() {

    private var context: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Layout()
        }
    }

    @Composable
    fun Layout(context: Context = this@StatsActivity) {
        this.context = context
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TotalStats()
        }
    }

    @Composable
    fun TotalStats() {
        StatLabelAndValue(
            label = context.getString(R.string.total_solves_stats_label),
            value = StatsService(context).totalNumberOfSolves().toString()
        )
        StatLabelAndValue(
            label = context.getString(R.string.total_time_spent_solving_stats_label),
            value = StatsService(context).totalSolvingTime().toString()
        )
    }

    @Composable
    fun StatLabelAndValue(label: String, value: String) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = label, fontSize = 15.sp, textAlign = TextAlign.Center)
            Text(text = value, fontSize = 25.sp, textAlign = TextAlign.Center)
        }
    }

}

@Preview
@Composable
fun StatsActivityLayoutPreview() {
    val context = LocalContext.current
    StatsActivity().Layout(context)
}