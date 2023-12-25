package com.example.smartcubeapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.smartcubeapp.ui.connectUI.ConnectActivity
import com.example.smartcubeapp.ui.timerUI.TimerActivity

class MainActivity : ComponentActivity() {
    private lateinit var bluetoothUtilities: com.example.cube_bluetooth.bluetooth.BluetoothUtilities

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bluetoothUtilities = com.example.cube_bluetooth.bluetooth.BluetoothUtilities(this, this)
        if (!bluetoothUtilities.checkForBluetoothConnectPermission()
            || !bluetoothUtilities.checkForBluetoothScanPermission()
            || !bluetoothUtilities.checkFineLocationPermission()
            || !bluetoothUtilities.checkCoarseLocationPermission()
        ) {
            bluetoothUtilities.requestAllPermissions()
        }
        launchActivity()
    }

    private fun launchActivity() {
        when (com.example.cube_bluetooth.bluetooth.bluetoothState.value) {
            com.example.cube_bluetooth.bluetooth.BluetoothState.Connected -> {
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

