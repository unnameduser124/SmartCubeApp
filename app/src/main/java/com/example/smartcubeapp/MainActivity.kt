package com.example.smartcubeapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.cube_bluetooth.bluetooth.BluetoothState
import com.example.cube_bluetooth.bluetooth.bluetoothState
import com.example.cube_database.solvedatabase.solvesDB.services.DeviceDBService
import com.example.smartcubeapp.ui.connectUI.ConnectLastCubeActivity
import com.example.smartcubeapp.ui.connectUI.ConnectNewCubeActivity
import com.example.smartcubeapp.ui.timerUI.TimerActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launchActivity()
    }

    private fun launchActivity() {
        when (bluetoothState.value) {
            BluetoothState.Connected -> {
                val intent = Intent(this, TimerActivity::class.java)
                startActivity(intent)
                finish()
            }
            else -> {
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
        }
    }
}

