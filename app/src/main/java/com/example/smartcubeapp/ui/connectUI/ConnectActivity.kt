package com.example.smartcubeapp.ui.connectUI

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.cube_bluetooth.bluetooth.BluetoothState
import com.example.cube_bluetooth.bluetooth.bluetoothState
import com.example.cube_database.solvedatabase.solvesDB.services.DeviceDBService
import com.example.smartcubeapp.ui.timerUI.TimerActivity

class ConnectActivity : ComponentActivity(
) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        if (bluetoothState.value != BluetoothState.Connected) {
            if (DeviceDBService(this).getAllDevices().isEmpty()) {
                val intent = Intent(this, ConnectNewCubeActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this, ConnectLastCubeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        if (bluetoothState.value == BluetoothState.Connected) {
            val intent = Intent(this, TimerActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    @Composable
    fun GenerateLayout() {
        Column(modifier = Modifier.fillMaxSize()) {
        }
    }
}