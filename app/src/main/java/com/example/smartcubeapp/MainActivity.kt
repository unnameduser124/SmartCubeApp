package com.example.smartcubeapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.smartcubeapp.bluetooth.BluetoothState
import com.example.smartcubeapp.bluetooth.BluetoothUtilities
import com.example.smartcubeapp.bluetooth.bluetoothState
import com.example.smartcubeapp.ui.connectUI.ConnectActivity
import com.example.smartcubeapp.ui.timerUI.TimerActivity

class MainActivity : ComponentActivity() {
    private lateinit var bluetoothUtilities: BluetoothUtilities

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bluetoothUtilities = BluetoothUtilities(this, this)
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

