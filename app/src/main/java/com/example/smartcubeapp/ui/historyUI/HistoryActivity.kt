package com.example.smartcubeapp.ui.historyUI

import android.os.Bundle
import android.os.Looper
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.lifecycle.lifecycleScope
import com.example.cube_cube.solveDBDataClasses.SolveData
import com.example.cube_database.solvedatabase.solvesDB.SolvesDatabaseConstants
import com.example.cube_database.solvedatabase.solvesDB.services.SolveDBService
import com.example.smartcubeapp.R
import com.example.smartcubeapp.ui.theme.SmartCubeAppTheme
import com.example.smartcubeapp.ui.theme.backgroundDark
import com.example.smartcubeapp.ui.theme.onPrimaryDark
import com.example.smartcubeapp.ui.theme.onSecondaryDark
import com.example.smartcubeapp.ui.theme.primaryDark
import com.example.smartcubeapp.ui.theme.secondaryDark
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class HistoryActivity : ComponentActivity() {

    private lateinit var solvesList: SnapshotStateList<SolveData>

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
        val popupVisible = remember { mutableStateOf(false) }
        val popupSolveID = remember { mutableStateOf(-1L) }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundDark)
        ) {
            ListHeader()
            SolvesListLazyColumn(popupVisible, popupSolveID)
        }

        ShowPopup(popupVisible, popupSolveID)
    }

    @Composable
    fun ShowPopup(popupVisible: MutableState<Boolean>, popupSolveID: MutableState<Long>) {
        if (popupVisible.value) {
            Popup(
                alignment = Alignment.Center,
                onDismissRequest = {
                    popupVisible.value = false
                },
                properties = PopupProperties(focusable = true)
            ) {
                SolveDetailsPopupLayout(popupVisible, solvesList).GenerateLayout(
                    context = this,
                    solveID = popupSolveID.value,
                    lifecycleScope = lifecycleScope
                )
            }
        }
    }

    @Composable
    fun ListHeader() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                .background(color = primaryDark, shape = RoundedCornerShape(16.dp))
                .padding(start = 10.dp, end = 10.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.solve_time_24),
                contentDescription = "Solve time",
                modifier = Modifier.weight(1f),
                tint = onPrimaryDark
            )
            Icon(
                painter = painterResource(id = R.drawable.outline_123_24),
                contentDescription = "Move count",
                modifier = Modifier
                    .size(40.dp)
                    .weight(1f),
                tint = onPrimaryDark
            )
            Text(
                text = "TPS",
                fontSize = 17.sp,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                color = onPrimaryDark
            )
            Icon(
                painter = painterResource(id = R.drawable.baseline_calendar_month_24),
                contentDescription = "Date",
                modifier = Modifier.weight(1.5f),
                tint = onPrimaryDark,
            )
        }
    }

    @Composable
    fun SolvesListLazyColumn(
        popupVisible: MutableState<Boolean>,
        popupSolveID: MutableState<Long>
    ) {
        solvesList = remember { mutableStateListOf() }
        val page = remember { mutableStateOf(1) }

        LaunchedEffect(lifecycleScope) {
            lifecycleScope.launch {
                if (solvesList.isEmpty()) {
                    solvesList.addAll(SolveDBService(this@HistoryActivity).getAllSolves())
                }
            }
        }

        Column(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {
                items(solvesList.size) { index ->
                    SolveListItem(solvesList[index], popupVisible, popupSolveID).GenerateLayout()
                }
                item {
                    LoadMoreButtonRow(page)
                }
            }
        }
    }

    @Composable
    fun LoadMoreButtonRow(page: MutableState<Int>) {
        Row() {
            Button(
                onClick = {
                    loadMoreSolves(page)
                }, modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = secondaryDark),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(text = "Load more", color = onSecondaryDark)
            }
        }
    }

    private fun loadMoreSolves(page: MutableState<Int>) {
        thread {
            val newSolves = SolveDBService(this@HistoryActivity).getAllSolves(page = page.value)
            if (newSolves.isEmpty()) {
                Looper.prepare()
                Toast.makeText(this@HistoryActivity, "All solves loaded", Toast.LENGTH_SHORT)
                    .show()
            } else {
                solvesList.addAll(SolveDBService(this@HistoryActivity).getAllSolves(page = page.value))
                page.value++
            }
        }
    }


}

@Preview
@Composable
fun PreviewHistoryActivityLayout() {
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
    HistoryActivity().GenerateLayout()
}