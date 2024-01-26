package com.example.smartcubeapp.ui.connectUI

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import com.example.cube_bluetooth.bluetooth.BluetoothService
import com.example.cube_bluetooth.bluetooth.BluetoothState
import com.example.cube_bluetooth.bluetooth.BluetoothUtilities
import com.example.cube_bluetooth.bluetooth.bluetoothState
import com.example.cube_cube.CubeDevice
import com.example.cube_database.solvedatabase.solvesDB.services.DeviceDBService
import com.example.smartcubeapp.MainActivity
import com.example.smartcubeapp.ui.popups.ConfirmationPopup
import com.example.smartcubeapp.ui.theme.SmartCubeAppTheme
import com.example.smartcubeapp.ui.theme.backgroundDark
import com.example.smartcubeapp.ui.theme.onPrimaryDark
import com.example.smartcubeapp.ui.theme.onSurfaceVariantDark
import com.example.smartcubeapp.ui.theme.primaryDark
import com.example.smartcubeapp.ui.theme.surfaceContainerHighestDark
import com.example.smartcubeapp.ui.timerUI.TimerActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ConnectNewCubeActivity : ComponentActivity() {

    private lateinit var devices: SnapshotStateList<CubeDevice>
    private lateinit var bluetoothService: BluetoothService
    private lateinit var bluetoothUtilities: BluetoothUtilities
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>
    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bluetoothUtilities = BluetoothUtilities(this, this)

        permissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { }
        if (!bluetoothUtilities.checkAllPermissions()) {
            bluetoothUtilities.requestAllPermissions(permissionLauncher)
        }

        setContent {
            SmartCubeAppTheme {
                GenerateLayout()
            }
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (doubleBackToExitPressedOnce) {
                    finishAffinity()
                    return
                }

                doubleBackToExitPressedOnce = true
                Toast.makeText(
                    this@ConnectNewCubeActivity,
                    "Press BACK again to exit",
                    Toast.LENGTH_SHORT
                ).show()

                lifecycleScope.launch {
                    delay(2000)
                    doubleBackToExitPressedOnce = false
                }
            }
        }
        onBackPressedDispatcher.addCallback(callback)
    }

    @Composable
    fun GenerateLayout() {
        bluetoothService = BluetoothService(
            this,
            this,
            Intent(this, TimerActivity::class.java),
            Intent(this, MainActivity::class.java)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundDark)
        ) {
            DeviceListLazyColumn()
            RefreshButton()
        }
    }

    @Composable
    fun DeviceListLazyColumn() {
        devices = remember { mutableStateListOf() }
        devices.addAll(DeviceDBService(this).getAllDevices())
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

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun DeviceItem(device: CubeDevice) {
        val deleteConfirmationPopupVisible = remember { mutableStateOf(false) }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp)
                .background(color = surfaceContainerHighestDark, shape = RoundedCornerShape(10.dp))
                .combinedClickable(
                    onClick = {
                        connectToDevice(device)
                    },
                    onLongClick = {
                        deleteConfirmationPopupVisible.value = true
                    }
                )
        ) {
            Text(
                text = device.name, fontSize = 20.sp, modifier = Modifier
                    .padding(10.dp),
                color = onSurfaceVariantDark
            )
        }

        if (deleteConfirmationPopupVisible.value) {
            ConfirmationPopup(
                this@ConnectNewCubeActivity,
                deleteConfirmationPopupVisible,
                "Remove device?"
            ).GeneratePopup {
                DeviceDBService(this@ConnectNewCubeActivity).removeDevice(device.id)
                devices.remove(device)
            }
        }
    }

    @Composable
    fun RefreshButton() {
        Button(
            onClick = {
                refreshDeviceList()
            },
            modifier = Modifier
                .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = primaryDark)
        ) {
            val refreshButtonTest =
                when (bluetoothState.value) {
                    BluetoothState.Disconnected -> "Refresh"
                    BluetoothState.Connecting -> "Connecting"
                    BluetoothState.Connected -> "Connected"
                    BluetoothState.Scanning -> "Scanning"
                }
            Text(text = refreshButtonTest, fontSize = 20.sp, color = onPrimaryDark)
        }
    }

    private fun connectToDevice(device: CubeDevice) {
        ifPermissionsGranted {
            bluetoothService.connectToDevice(device)
        }
    }

    private fun refreshDeviceList() {
        ifPermissionsGranted {
            devices.clear()
            devices.addAll(DeviceDBService(this).getAllDevices())
            bluetoothService.deviceList = devices
            bluetoothService.scanForAvailableDevices()
        }
    }

    private fun ifPermissionsGranted(unit: () -> Unit) {
        if (!bluetoothUtilities.checkAllPermissions()) {
            bluetoothUtilities.requestAllPermissions(permissionLauncher)
        } else {
            unit()
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
    ConnectNewCubeActivity().GenerateLayout()//doesn't work anymore (I think)
}