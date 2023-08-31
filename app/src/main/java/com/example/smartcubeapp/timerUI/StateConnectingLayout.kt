package com.example.smartcubeapp.timerUI

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.example.smartcubeapp.WindowCenterOffsetPositionProvider
import com.example.smartcubeapp.bluetooth.BluetoothService
import com.example.smartcubeapp.bluetooth.CHARACTERISTIC_UUID
import com.example.smartcubeapp.bluetooth.SERVICE_UUID

class StateConnectingLayout(
    private val state: MutableState<TimerState>,
    private val bluetoothService: BluetoothService
) {

    @RequiresApi(Build.VERSION_CODES.S)
    @Composable
    fun GenerateLayout() {
        Column(modifier = Modifier.fillMaxSize()) {
            if (state.value == TimerState.Connecting) {
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