package com.example.smartcubeapp.bluetooth

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import android.Manifest
import android.app.AlertDialog
import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.ComponentActivity
import kotlin.system.exitProcess

class BluetoothUtilities(private val activity: ComponentActivity, private val activityContext: Context) {

    @RequiresApi(Build.VERSION_CODES.S)
    fun requestBluetoothConnectPermission() {
        //request BLUETOOTH_CONNECT permission
        if (!checkForBluetoothConnectPermission()) {
            val permissions = arrayOf(Manifest.permission.BLUETOOTH_CONNECT)
            ActivityCompat.requestPermissions(activity, permissions, 0)
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    fun requestBluetoothScanPermission() {
        //request BLUETOOTH_SCAN permission
        if (!checkForBluetoothScanPermission()) {
            val permissions = arrayOf(Manifest.permission.BLUETOOTH_SCAN)
            ActivityCompat.requestPermissions(activity, permissions, 1)
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    fun checkFineLocationPermission(): Boolean {
        //check FINE_LOCATION permission
        return ActivityCompat.checkSelfPermission(
            activityContext,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    @RequiresApi(Build.VERSION_CODES.S)
    fun checkCoarseLocationPermission(): Boolean {
        //check COARSE_LOCATION permission
        return ActivityCompat.checkSelfPermission(
            activityContext,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }
    fun checkIfBluetoothIsAvailable(bluetoothAdapter: BluetoothAdapter?): Boolean {
        // Phone does not support Bluetooth so let the user know and exit.
        if (bluetoothAdapter == null) {
            AlertDialog.Builder(activityContext)
                .setTitle("Not compatible")
                .setMessage("Your phone does not support Bluetooth")
                .setPositiveButton(
                    "Exit"
                ) { _, _ -> exitProcess(0) }
                .show()
            return false
        }
        return true
    }

    @RequiresApi(Build.VERSION_CODES.S)
    fun checkIfBluetoothIsOn(bluetoothAdapter: BluetoothAdapter): Boolean {

        // Bluetooth is not enabled so let the user turn it on.
        if (!bluetoothAdapter.isEnabled) {
            val chooserIntent = Intent(Intent.ACTION_CHOOSER)
            chooserIntent.putExtra(
                Intent.EXTRA_INTENT,
                Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            )
            if (checkForBluetoothConnectPermission()) {
                requestBluetoothConnectPermission()
                return false
            }
            val startActivityIntent: ActivityResultLauncher<Intent> =
                activity.registerForActivityResult(
                    ActivityResultContracts.StartActivityForResult()
                ) { result ->
                    if (result.resultCode == 0) {
                        println("Bluetooth is on")
                    }
                }
            startActivityIntent.launch(chooserIntent)
            return false
        }
        return true
    }

    @RequiresApi(Build.VERSION_CODES.S)
    fun checkForBluetoothConnectPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            activityContext,
            Manifest.permission.BLUETOOTH_CONNECT
        ) == PackageManager.PERMISSION_GRANTED
    }

    @RequiresApi(Build.VERSION_CODES.S)
    fun checkForBluetoothScanPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            activityContext,
            Manifest.permission.BLUETOOTH_SCAN
        ) == PackageManager.PERMISSION_GRANTED
    }

    @RequiresApi(Build.VERSION_CODES.S)
    fun requestAllPermissions() {
        val permissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.BLUETOOTH_CONNECT,
            Manifest.permission.BLUETOOTH_SCAN
        )
        activity.requestPermissions(permissions, 5)
    }
}
