package com.example.smartcubeapp.timerUI

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.example.smartcubeapp.WindowCenterOffsetPositionProvider
import com.example.smartcubeapp.bluetooth.BluetoothService
import com.example.smartcubeapp.bluetooth.BluetoothState
import com.example.smartcubeapp.bluetooth.CHARACTERISTIC_UUID
import com.example.smartcubeapp.bluetooth.SERVICE_UUID
import com.example.smartcubeapp.bluetooth.bluetoothState

class ConnectActivity: ComponentActivity(
) {

    private lateinit var bluetoothService: BluetoothService

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bluetoothService = BluetoothService(this, this)
        setContent {
            GenerateLayout()
            if(bluetoothState.value == BluetoothState.Connected){
                val intent = Intent(this, TimerActivity::class.java)
                startActivity(intent)
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.S)
    @Composable
    fun GenerateLayout() {
        Column(modifier = Modifier.fillMaxSize()) {
            if (bluetoothState.value != BluetoothState.Connected) {
                ConnectCubePopup()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    @Composable
    fun ConnectCubePopup() {
        Popup(popupPositionProvider = WindowCenterOffsetPositionProvider(),
            onDismissRequest = { /* Dismiss the popup */ }) {
            Column(
                modifier = Modifier
                    .background(color = Color.LightGray, shape = RoundedCornerShape(16.dp))
            ) {
                Button(
                    onClick = {
                        bluetoothService.connectToDevice(
                            serviceUUID = SERVICE_UUID,
                            characteristicUUID = CHARACTERISTIC_UUID
                        )
                    },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text = "Connect", fontSize = 25.sp)
                }
            }
        }
    }

}