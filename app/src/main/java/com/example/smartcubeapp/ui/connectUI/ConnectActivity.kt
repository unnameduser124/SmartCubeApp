package com.example.smartcubeapp.ui.connectUI

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import com.example.smartcubeapp.bluetooth.BluetoothService
import com.example.smartcubeapp.bluetooth.BluetoothState
import com.example.smartcubeapp.bluetooth.CubeDevice
import com.example.smartcubeapp.bluetooth.bluetoothState
import com.example.smartcubeapp.solvedatabase.services.DeviceDBService
import com.example.smartcubeapp.ui.timerUI.TimerActivity

class ConnectActivity : ComponentActivity(
) {

    private lateinit var bluetoothService: BluetoothService
    private lateinit var context: Context
    private lateinit var devices: SnapshotStateList<CubeDevice>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bluetoothService = BluetoothService(this, this)
        context = this
        setContent {
            devices = remember { mutableStateListOf() }
            devices.addAll(DeviceDBService(context).getAllDevices())
            GenerateLayout()
            if (bluetoothState.value == BluetoothState.Connected) {
                val intent = Intent(this, TimerActivity::class.java)
                startActivity(intent)
            }
        }
    }

    @Composable
    fun GenerateLayout() {
        Column(modifier = Modifier.fillMaxSize()) {
            if (bluetoothState.value != BluetoothState.Connected) {
                if (devices.none { it.id != -1L }) {
                    ConnectNewCubeLayout().GenerateLayout(context, bluetoothService, devices)
                } else {
                    ConnectLastCubeLayout().GenerateLayout()
                }
            }
        }
    }
}