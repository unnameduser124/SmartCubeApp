package com.example.smartcubeapp.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCallback
import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.BluetoothGattDescriptor
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothProfile
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Parcelable
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import com.example.smartcubeapp.CHARACTERISTIC_UUID
import com.example.smartcubeapp.MY_CUBE_ADDRESS
import com.example.smartcubeapp.cube.CubeState
import com.example.smartcubeapp.cube.Move
import com.example.smartcubeapp.cube.MoveDataParser
import com.example.smartcubeapp.timerUI.TimerState
import java.nio.ByteBuffer
import java.util.UUID


class BluetoothService(
    private val activityContext: Context,
    private val activity: ComponentActivity,
    val state: MutableState<TimerState>,
    val cubeState: MutableState<CubeState>,
    val lastMove: MutableState<Move>
) {

    private val bluetoothManager =
        activityContext.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    private val bluetoothAdapter = bluetoothManager.adapter
    private val bluetoothUtilities = BluetoothUtilities(activity, activityContext)

    inline fun <reified T : Parcelable> Intent.parcelable(key: String): T? = when {
        SDK_INT >= 33 -> getParcelableExtra(key, T::class.java)
        else -> @Suppress("DEPRECATION") getParcelableExtra(key) as? T
    }


    private val receiver: BroadcastReceiver = object : BroadcastReceiver() {
        @RequiresApi(Build.VERSION_CODES.S)

        override fun onReceive(context: Context?, intent: Intent) {
            val action = intent.action
            handleDiscoveryReceiverAction(action, intent)
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    fun scanForAvailableDevices() {
        if (!bluetoothUtilities.checkForBluetoothScanPermission()) {
            bluetoothUtilities.requestBluetoothScanPermission()
            return
        }

        if (!bluetoothUtilities.checkForBluetoothConnectPermission()) {
            bluetoothUtilities.requestBluetoothConnectPermission()
            return
        }

        bluetoothUtilities.checkIfBluetoothIsOn(bluetoothAdapter)

        val filter = getDeviceScanIntentFilter()

        activity.registerReceiver(receiver, filter)
        bluetoothUtilities.checkForBluetoothScanPermission()
        bluetoothAdapter.startDiscovery()
        println(bluetoothAdapter.isDiscovering)
    }

    @RequiresApi(Build.VERSION_CODES.S)
    fun isDiscovering(): Boolean {
        if (!bluetoothUtilities.checkForBluetoothScanPermission()) {
            bluetoothUtilities.requestBluetoothScanPermission()
            return false
        }
        return bluetoothAdapter.isDiscovering
    }


    @RequiresApi(Build.VERSION_CODES.S)
    fun getPairedDevices(): List<SimpleBluetoothDevice> {

        if (!bluetoothUtilities.checkIfBluetoothIsAvailable(bluetoothAdapter)) {
            return emptyList()
        }

        if (!bluetoothUtilities.checkIfBluetoothIsOn(bluetoothAdapter)) {
            return emptyList()
        }

        val pairedDevices = if (!bluetoothUtilities.checkForBluetoothConnectPermission()) {
            bluetoothUtilities.requestBluetoothConnectPermission()
            return emptyList()
        } else {
            bluetoothAdapter.bondedDevices
        }

        val devices = mutableListOf<SimpleBluetoothDevice>()
        pairedDevices.forEach { device ->
            //do something
            devices.add(SimpleBluetoothDevice(device.name, device.address))
        }
        return devices
    }


    fun onDestroy() {
        activity.unregisterReceiver(receiver)
    }

    @RequiresApi(Build.VERSION_CODES.S)
    fun connectToDevice(
        address: String = MY_CUBE_ADDRESS,
        serviceUUID: String,
        characteristicUUID: String
    ) {

        val device = bluetoothAdapter.getRemoteDevice(address)
        val gattCallback = object : BluetoothGattCallback() {
            @RequiresApi(Build.VERSION_CODES.S)
            override fun onConnectionStateChange(gatt: BluetoothGatt, status: Int, newState: Int) {
                handleConnectionStateChanged(newState, gatt)
            }

            @RequiresApi(Build.VERSION_CODES.S)
            override fun onServicesDiscovered(gatt: BluetoothGatt, status: Int) {
                handleServiceDiscovery(gatt)
            }

            @Deprecated("Deprecated for API 33+")
            override fun onCharacteristicChanged(
                gatt: BluetoothGatt,
                characteristic: BluetoothGattCharacteristic
            ) {
                val data = characteristic.value // The received data from the GiikerCube
                val dataParser = MoveDataParser(ByteBuffer.wrap(data))
                val state = dataParser.parseCubeValue()
                cubeState.value = state.first
                lastMove.value = state.second[0] as Move
            }

            @RequiresApi(Build.VERSION_CODES.TIRAMISU)
            override fun onCharacteristicChanged(
                gatt: BluetoothGatt,
                characteristic: BluetoothGattCharacteristic,
                value: ByteArray
            ) {
                val dataParser = MoveDataParser(ByteBuffer.wrap(value))
                val state = dataParser.parseCubeValue()
                cubeState.value = state.first
                lastMove.value = state.second[0] as Move
            }
        }

        if(!bluetoothUtilities.checkForBluetoothConnectPermission()){
            bluetoothUtilities.requestBluetoothConnectPermission()
            return
        }
        device.connectGatt(activityContext, false, gattCallback)
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun handleConnectionStateChanged(newState: Int, gatt: BluetoothGatt) {
        if (newState == BluetoothProfile.STATE_CONNECTED) {
            if (!bluetoothUtilities.checkForBluetoothScanPermission()) {
                bluetoothUtilities.requestBluetoothScanPermission()
                return
            }
            if (!bluetoothUtilities.checkForBluetoothConnectPermission()) {
                bluetoothUtilities.requestBluetoothConnectPermission()
                return
            }
            gatt.discoverServices()
        } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
            println("Disconnected")
        }
    }


    @RequiresApi(Build.VERSION_CODES.S)
    fun handleServiceDiscovery(gatt: BluetoothGatt) {

        val service = gatt.getService(UUID.fromString(SERVICE_UUID))
        if (service == null) {
            println("Service not found")
            return
        }

        val characteristic = service.getCharacteristic(UUID.fromString(CHARACTERISTIC_UUID))
        if (characteristic == null) {
            println("Characteristic not found")
            return
        }

        if (!bluetoothUtilities.checkForBluetoothConnectPermission()) {
            bluetoothUtilities.requestBluetoothConnectPermission()
            return
        }

        if(SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            enableCharacteristicsNotificationsAPI33Plus(gatt, characteristic)
        } else {
            enableCharacteristicsNotificationsAPI33Minus(gatt, characteristic)
        }
        gatt.setCharacteristicNotification(characteristic, true)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun enableCharacteristicsNotificationsAPI33Plus(
        bluetoothGatt: BluetoothGatt,
        characteristic: BluetoothGattCharacteristic
    ) {
        // Enable notifications for the characteristic
        if (!bluetoothUtilities.checkForBluetoothConnectPermission()) {
            bluetoothUtilities.requestBluetoothConnectPermission()
            return
        }
        bluetoothGatt.setCharacteristicNotification(characteristic, true)

        // Set the CCCD descriptor value to enable notifications
        val descriptor = characteristic.getDescriptor(UUID.fromString(GIIKER_DESCRIPTOR_UUID))
        bluetoothGatt.writeDescriptor(
            descriptor,
            BluetoothGattDescriptor.ENABLE_INDICATION_VALUE
        )
    }

    @RequiresApi(Build.VERSION_CODES.S)
    fun enableCharacteristicsNotificationsAPI33Minus(
        bluetoothGatt: BluetoothGatt,
        characteristic: BluetoothGattCharacteristic
    ) {
        // Enable notifications for the characteristic
        if (!bluetoothUtilities.checkForBluetoothConnectPermission()) {
            bluetoothUtilities.requestBluetoothConnectPermission()
            return
        }
        bluetoothGatt.setCharacteristicNotification(characteristic, true)

        // Set the CCCD descriptor value to enable notifications
        val descriptor = characteristic.getDescriptor(UUID.fromString(GIIKER_DESCRIPTOR_UUID))
        descriptor.value = BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE
        bluetoothGatt.writeDescriptor(descriptor)
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun handleDiscoveryReceiverAction(action: String?, intent: Intent) {

        if (BluetoothDevice.ACTION_FOUND == action) {
            val device = intent.parcelable<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
            if (!bluetoothUtilities.checkForBluetoothConnectPermission()) {
                bluetoothUtilities.requestBluetoothConnectPermission()
                return
            } else {
                println(action)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun getDeviceScanIntentFilter(): IntentFilter {
        val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED)
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED)
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)
        filter.addAction(BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED)
        filter.addAction(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE)
        filter.addAction(BluetoothAdapter.ACTION_REQUEST_ENABLE)
        filter.addAction(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED)
        filter.addAction(BluetoothAdapter.ACTION_LOCAL_NAME_CHANGED)
        return filter
    }

}
