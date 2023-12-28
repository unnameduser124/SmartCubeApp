package com.example.smartcubeapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.cube_bluetooth.bluetooth.BluetoothState
import com.example.cube_bluetooth.bluetooth.bluetoothState
import com.example.smartcubeapp.ui.connectUI.ConnectActivity
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
                print("Recomposing in launchActivity")
                val intent = Intent(this, ConnectActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}

