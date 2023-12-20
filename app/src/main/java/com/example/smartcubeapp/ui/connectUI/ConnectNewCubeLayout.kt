package com.example.smartcubeapp.ui.connectUI

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartcubeapp.bluetooth.CubeDevice

class ConnectNewCubeLayout {

    private lateinit var devices: SnapshotStateList<CubeDevice>

    @Composable
    fun GenerateLayout(deviceList: List<CubeDevice> = listOf()) { //TODO("Parameter only for testing")
        devices =
            remember { mutableStateListOf() } //TODO("Make sure none of the devices have null name or address")
        devices.addAll(deviceList)
        Column(modifier = Modifier.fillMaxSize()) {
            DeviceListLazyColumn()
            RefreshButton()
        }
    }

    @Composable
    fun DeviceListLazyColumn() {
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
                text = device.name!!, fontSize = 20.sp, modifier = Modifier
                    .clickable {
                        TODO("Connect to device on click")
                    })
        }
    }

    @Composable
    fun RefreshButton() {
        Button(
            onClick = { TODO("Start scanning for new devices") },
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
        deviceList.add(CubeDevice("test_name_$i", "test_address_$i"))
    }
    ConnectNewCubeLayout().GenerateLayout(deviceList)
}