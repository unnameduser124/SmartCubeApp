package com.example.smartcubeapp.ui.settingsUI

import android.os.Bundle
import androidx.activity.ComponentActivity
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
import com.example.smartcubeapp.R

class SettingsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GenerateLayout()
        }
    }

    @Composable
    fun GenerateLayout() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            SwitchButtonRow(this@SettingsActivity.getString(R.string.inspection_timer_switch_label)) {
                //TODO("Switch AppSettings variable, save change to database")
            }
            SwitchButtonRow(this@SettingsActivity.getString(R.string.solve_time_visibility_during_solve_switch_label)) {
                //TODO("Switch AppSettings variable, save change to database")
            }
            SwitchButtonRow(this@SettingsActivity.getString(R.string.scramble_generation_switch_label)) {
                //TODO("Switch AppSettings variable, save change to database")
            }
            ClearAllDataButtonRow()
        }
    }

    @Composable
    fun ClearAllDataButtonRow() {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { /*TODO("Call database method")*/ },
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text(text = "Clear All Data", fontSize = 20.sp, color = Color.White)
            }
        }
    }

    @Composable
    fun SwitchButtonRow(text: String, onCheckedChanged: () -> Unit) {
        val checked = remember { mutableStateOf(false) }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Switch Button", fontSize = 20.sp)
            Switch(checked = checked.value, onCheckedChange = {
                checked.value = it
                onCheckedChanged()
            })
        }
    }
}