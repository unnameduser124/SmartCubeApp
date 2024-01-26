package com.example.smartcubeapp.ui.connectUI

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.smartcubeapp.ui.theme.SmartCubeAppTheme
import com.example.smartcubeapp.ui.theme.backgroundDark
import com.example.smartcubeapp.ui.theme.onBackgroundDark
import com.example.smartcubeapp.ui.theme.onPrimaryDark
import com.example.smartcubeapp.ui.theme.primaryDark
import com.example.smartcubeapp.ui.timerUI.TimerActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ConnectLastCubeActivity : ComponentActivity() {

    private lateinit var device: CubeDevice
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
                Toast.makeText(this@ConnectLastCubeActivity, "Press BACK again to exit", Toast.LENGTH_SHORT).show()

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
        loadDevice()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = backgroundDark),
            verticalArrangement = Arrangement.Bottom
        ) {
            AddNewDeviceButtonRow()
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
    }


    @Composable
    fun DeviceNameRow() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = device.name, fontSize = 25.sp, color = onBackgroundDark)
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
            Button(
                onClick = {
                    connectToDevice()
                },
                colors = ButtonDefaults.buttonColors(containerColor = primaryDark)
            ) {
                val connectButtonText =
                    when (bluetoothState.value) {
                        BluetoothState.Connecting -> "Connecting"
                        BluetoothState.Connected -> "Connected"
                        else -> "Connect"
                    }
                Text(text = connectButtonText, fontSize = 20.sp, color = onPrimaryDark)
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
            Button(
                onClick = {
                    val intent =
                        Intent(this@ConnectLastCubeActivity, ConnectNewCubeActivity::class.java)
                    startActivity(intent)
                },
                colors = ButtonDefaults.buttonColors(containerColor = primaryDark)
            ) {
                Text(text = "Add new cube", fontSize = 20.sp, color = onPrimaryDark)
            }
        }
    }

    private fun loadDevice() {
        val deviceFromDB = DeviceDBService(this).getLastDevice()
        device = if (deviceFromDB != null) {
            deviceFromDB
        } else {
            val intent = Intent(this, ConnectNewCubeActivity::class.java)
            startActivity(intent)
            finish()
            return
        }
    }

    private fun connectToDevice() {
        ifPermissionsGranted {
            BluetoothService(
                this@ConnectLastCubeActivity,
                this@ConnectLastCubeActivity,
                Intent(this@ConnectLastCubeActivity, TimerActivity::class.java),
                Intent(this@ConnectLastCubeActivity, MainActivity::class.java)
            ).connectToDevice(device)
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
fun ConnectLastCubeLayoutPreview() {
    ConnectLastCubeActivity().GenerateLayout()
}