package com.example.smartcubeapp.ui.historyUI

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartcubeapp.solvedatabase.SolvesDatabaseConstants
import com.example.smartcubeapp.solvedatabase.dataclasses.SolveData
import com.example.smartcubeapp.solvedatabase.services.SolveDBService

class HistoryActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GenerateLayout()
        }
    }

    @Composable
    fun GenerateLayout(context: Context = this@HistoryActivity) {
        Column(modifier = Modifier.fillMaxSize()) {
            ListHeader()
            SolvesListLazyColumn(context)
        }
    }

    @Composable
    fun ListHeader() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 20.dp, end = 20.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.Bottom
        ) {
            Text(text = "Duration", fontSize = 17.sp, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
            Text(text = "Moves", fontSize = 17.sp, modifier = Modifier.weight(0.7f), textAlign = TextAlign.Center)
            Text(text = "TPS", fontSize = 17.sp, modifier = Modifier.weight(0.5f), textAlign = TextAlign.Center)
            Text(text = "Date", fontSize = 17.sp, modifier = Modifier.weight(2f), textAlign = TextAlign.Center)
        }
    }

    @Composable
    fun SolvesListLazyColumn(context: Context) {
        val solvesList = remember { mutableStateListOf<SolveData>() }
        val page = remember { mutableStateOf(2) }
        solvesList.addAll(SolveDBService(context).getAllSolves().toMutableList())

        Column(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {
                items(solvesList.size) { index ->
                    SolveListItem(solvesList[index]).GenerateLayout()
                }
                item {
                    Row(modifier = Modifier.padding(horizontal = 10.dp)) {
                        Button(onClick = {
                            val newSolves = SolveDBService(context).getAllSolves(page = page.value)
                            if (newSolves.isEmpty()) {
                                Toast.makeText(context, "All solves loaded", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                solvesList.addAll(SolveDBService(context).getAllSolves(page = page.value))
                                page.value++
                            }
                        }, modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Load more")
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewSolvesListLazyColumn() {
    val context = LocalContext.current
    context.deleteDatabase(SolvesDatabaseConstants.SOLVE_DATABASE_NAME)
    for (i in 0..100) {
        SolveDBService(context).addSolve(
            SolveData(
                id = i.toLong(),
                solveDuration = (i + 1) * 2137.toLong(),
                timestamp = System.currentTimeMillis(),
                scrambledStateID = i.toLong(),
                scramble = "R U R' U'",
                moveCount = (i + 1) * 10,
                penalty = 0
            )
        )
    }
    HistoryActivity().GenerateLayout(context)
}