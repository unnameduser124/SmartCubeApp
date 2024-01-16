package com.example.smartcubeapp.ui.settingsUI

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cube_database.solvedatabase.solvesDB.SolveDB
import com.example.cube_database.solvedatabase.solvesDB.SolvesDatabaseConstants
import com.example.cube_database.solvedatabase.solvesDB.services.SettingsDBService
import com.example.cube_global.AppSettings
import com.example.smartcubeapp.R
import com.example.smartcubeapp.ui.popups.ConfirmationPopup
import com.example.smartcubeapp.ui.timerUI.SolvePreparationActivity
import kotlin.concurrent.thread

class SettingsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GenerateLayout()
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val intent = Intent(this@SettingsActivity, SolvePreparationActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        onBackPressedDispatcher.addCallback(callback)
    }

    @Composable
    fun GenerateLayout() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            SwitchButtonRow(
                this@SettingsActivity.getString(R.string.inspection_timer_switch_label),
                AppSettings.isInspectionEnabled
            ) {
                AppSettings.isInspectionEnabled = !AppSettings.isInspectionEnabled
                thread {
                    val service = SettingsDBService(this@SettingsActivity)
                    service.updateSetting(
                        SolvesDatabaseConstants.SettingsTable.INSPECTION_ENABLED,
                        if (AppSettings.isInspectionEnabled) "1" else "0"
                    )
                }
            }
            SwitchButtonRow(
                this@SettingsActivity.getString(R.string.solve_time_visibility_during_solve_switch_label),
                AppSettings.isSolvingTimeVisible
            ) {
                AppSettings.isSolvingTimeVisible = !AppSettings.isSolvingTimeVisible
                thread {
                    val service = SettingsDBService(this@SettingsActivity)
                    service.updateSetting(
                        SolvesDatabaseConstants.SettingsTable.SOLVING_TIME_VISIBLE,
                        if (AppSettings.isSolvingTimeVisible) "1" else "0"
                    )
                }
            }
            SwitchButtonRow(
                this@SettingsActivity.getString(R.string.scramble_generation_switch_label),
                AppSettings.isScrambleGenerationEnabled
            ) {
                AppSettings.isScrambleGenerationEnabled = !AppSettings.isScrambleGenerationEnabled
                thread {
                    val service = SettingsDBService(this@SettingsActivity)
                    service.updateSetting(
                        SolvesDatabaseConstants.SettingsTable.SCRAMBLE_GENERATION_ENABLED,
                        if (AppSettings.isScrambleGenerationEnabled) "1" else "0"
                    )
                }
            }
            ClearAllDataButtonRow()
        }
    }

    @Composable
    fun ClearAllDataButtonRow() {
        val confirmationPopupVisible = remember { mutableStateOf(false) }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    confirmationPopupVisible.value = true
                },
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text(text = "Clear All Data", fontSize = 20.sp, color = Color.White)
            }
        }
        if (confirmationPopupVisible.value) {
            ConfirmationPopup(this@SettingsActivity, confirmationPopupVisible)
                .GeneratePopup {
                    thread {
                        val solveDB = SolveDB(this@SettingsActivity).clearAllData()
                        val settingsDB = SettingsDBService(this@SettingsActivity).clearAllData()
                    }
                }
        }
    }

    @Composable
    fun SwitchButtonRow(
        text: String,
        checkedInitValue: Boolean = false,
        onCheckedChanged: () -> Unit
    ) {
        val checked = remember { mutableStateOf(checkedInitValue) }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = text, fontSize = 20.sp)
            Switch(checked = checked.value, onCheckedChange = {
                checked.value = it
                onCheckedChanged()
            })
        }
    }


}