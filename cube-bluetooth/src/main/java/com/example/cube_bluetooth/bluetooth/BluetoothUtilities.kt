package com.example.cube_bluetooth.bluetooth

import android.Manifest
import android.app.AlertDialog
import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import kotlin.system.exitProcess

class BluetoothUtilities(
    private val activity: ComponentActivity,
    private val activityContext: Context
) {
    fun requestBluetoothConnectPermission() {
        //request BLUETOOTH_CONNECT permission
        if (!checkForBluetoothConnectPermission()) {
            val permissions = arrayOf(Manifest.permission.BLUETOOTH_CONNECT)
            requestPermissions(activity, permissions, 0)
        }
    }

    fun requestBluetoothScanPermission() {
        //request BLUETOOTH_SCAN permission
        if (!checkForBluetoothScanPermission()) {
            val permissions = arrayOf(Manifest.permission.BLUETOOTH_SCAN)
            requestPermissions(activity, permissions, 1)
        }
    }

    fun checkFineLocationPermission(): Boolean {
        //check FINE_LOCATION permission
        return ActivityCompat.checkSelfPermission(
            activityContext,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

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

    fun checkForBluetoothConnectPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            activityContext,
            Manifest.permission.BLUETOOTH_CONNECT
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun checkForBluetoothScanPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            activityContext,
            Manifest.permission.BLUETOOTH_SCAN
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun requestAllPermissions(permissionLauncher: ActivityResultLauncher<Array<String>>) {
        val permissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.BLUETOOTH_CONNECT,
            Manifest.permission.BLUETOOTH_SCAN
        )
        permissionLauncher.launch(permissions)
    }

    fun checkAllPermissions(): Boolean {
        return checkFineLocationPermission() &&
                checkCoarseLocationPermission() &&
                checkForBluetoothConnectPermission() &&
                checkForBluetoothScanPermission()
    }
}
