package com.example.smartcubeapp.ui.connectUI

import android.content.Intent
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
import com.example.smartcubeapp.bluetooth.BluetoothService
import com.example.cube_cube.CubeDevice
import com.example.cube_database.solvedatabase.services.DeviceDBService

class ConnectLastCubeActivity : ComponentActivity() {

    private lateinit var device: CubeDevice

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GenerateLayout()
        }
    }

    @Composable
    fun GenerateLayout() {
        val deviceFromDB = DeviceDBService(this).getLastDevice()
        device = if (deviceFromDB != null) {
            deviceFromDB
        } else {
            val intent = Intent(this, ConnectNewCubeActivity::class.java)
            startActivity(intent)
            return
        }

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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = device.name, fontSize = 25.sp)
        }
    }

    @Composable
    fun ConnectButtonRow() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 100.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                BluetoothService(
                    this@ConnectLastCubeActivity,
                    this@ConnectLastCubeActivity
                ).connectToDevice(device)
            }) {
                Text(text = "Connect", fontSize = 20.sp)
            }
        }
    }

    @Composable
    fun AddNewDeviceButtonRow() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                val intent = Intent(this@ConnectLastCubeActivity, ConnectNewCubeActivity::class.java)
                startActivity(intent)
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