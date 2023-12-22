package com.example.smartcubeapp.ui.connectUI

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartcubeapp.bluetooth.CubeDevice

class ConnectLastCubeActivity: ComponentActivity() {

    private lateinit var device: CubeDevice

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            GenerateLayout()
        }
    }

    @Composable
    fun GenerateLayout() {
        device = CubeDevice("test_name", "test_address") //TODO("Get device from database")

        Column(
            modifier = Modifier
                .fillMaxHeight(0.8f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            DeviceNameRow()
            ConnectButtonRow()
        }
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            AddNewDeviceButtonRow()
        }
    }

    @Composable
    fun DeviceNameRow() {
        Row(modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp), horizontalArrangement = Arrangement.Center) {
            Text(text = device.name, fontSize = 25.sp)
        }
    }

    @Composable
    fun ConnectButtonRow() {
        Row(modifier = Modifier.fillMaxWidth().padding(bottom = 100.dp), horizontalArrangement = Arrangement.Center) {
            Button(onClick = {
                TODO("Try connecting to device")
            }) {
                Text(text = "Connect", fontSize = 20.sp)
            }
        }
    }

    @Composable
    fun AddNewDeviceButtonRow() {
        Row(modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp), horizontalArrangement = Arrangement.Center) {
            Button(onClick = {
                TODO("Switch layout to ConnectNewCubeLayout")
            }) {
                Text(text = "Add new cube", fontSize = 20.sp)
            }
        }
    }
}

@Preview
@Composable
fun ConnectLastCubeLayoutPreview() {
    ConnectLastCubeActivity().GenerateLayout()
}