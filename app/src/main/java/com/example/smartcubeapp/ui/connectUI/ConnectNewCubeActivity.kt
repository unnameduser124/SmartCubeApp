package com.example.smartcubeapp.ui.connectUI

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cube_cube.CubeDevice
import com.example.cube_database.solvedatabase.solvesDB.services.DeviceDBService
import com.example.cube_bluetooth.bluetooth.BluetoothService
import com.example.smartcubeapp.ui.timerUI.TimerActivity

class ConnectNewCubeActivity: ComponentActivity() {

    private lateinit var context: Context
    private lateinit var devices: SnapshotStateList<CubeDevice>
    private lateinit var bluetoothService: BluetoothService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.context = this
        setContent{
            println("Recomposing in onCreate")
            GenerateLayout()
        }
    }

    @Composable
    fun GenerateLayout() {
        bluetoothService = BluetoothService(context, this, Intent(this, TimerActivity::class.java), Intent(this, ConnectActivity::class.java))
        Column(modifier = Modifier.fillMaxSize()) {
            println("Recomposing in GenerateLayout")
            DeviceListLazyColumn()
            RefreshButton()
        }
    }

    @Composable
    fun DeviceListLazyColumn() {
        devices = remember { mutableStateListOf() }
        devices.addAll(DeviceDBService(context).getAllDevices())
        LazyColumn(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .fillMaxHeight(0.93f)
        ) {
            items(devices.size) { index ->
                DeviceItem(device = devices[index])
            }
        }
    }

    @Composable
    fun DeviceItem(device: CubeDevice) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = device.name, fontSize = 20.sp, modifier = Modifier
                    .clickable {
                        bluetoothService.connectToDevice(device)
                    }
            )
        }
    }

    @Composable
    fun RefreshButton() {
        Button(
            onClick = {
                devices.clear()
                devices.addAll(DeviceDBService(context).getAllDevices())
                bluetoothService.deviceList = devices
                bluetoothService.scanForAvailableDevices()
            },
            modifier = Modifier
                .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Refresh", fontSize = 20.sp)
        }
    }
}

@Preview
@Composable
fun ConnectNewCubeLayoutPreview() {
    val deviceList = mutableListOf<CubeDevice>()
    for (i in 0..20) {
        deviceList.add(
            CubeDevice(
                "test_name_$i",
                "test_address_$i"
            )
        )
    }
    val context = LocalContext.current
    ConnectNewCubeActivity().GenerateLayout()//doesn't work anymore (I think)
}