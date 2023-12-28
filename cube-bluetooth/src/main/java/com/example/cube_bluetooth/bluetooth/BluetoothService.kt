package com.example.cube_bluetooth.bluetooth

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
import com.example.cube_cube.CubeDevice
import com.example.cube_cube.cube.MoveDataParser
import com.example.cube_database.solvedatabase.solvesDB.services.DeviceDBService
import java.nio.ByteBuffer
import java.util.Calendar
import java.util.UUID


class BluetoothService(
    private val activityContext: Context,
    private val activity: ComponentActivity,
    private val onConnectActivityIntent: Intent,
    private val onDisconnectActivityIntent: Intent,
    var deviceList: MutableList<CubeDevice> = mutableListOf()
) {
    private val bluetoothManager =
        activityContext.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    private val bluetoothAdapter = bluetoothManager.adapter
    private val bluetoothUtilities = BluetoothUtilities(activity, activityContext)
    private var device: CubeDevice? = null


    inline fun <reified T : Parcelable> Intent.parcelable(key: String): T? = when {
        SDK_INT >= 33 -> getParcelableExtra(key, T::class.java)
        else -> @Suppress("DEPRECATION") getParcelableExtra(key) as? T
    }

    private val receiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            val action = intent.action
            handleDiscoveryReceiverAction(action, intent)
        }
    }

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

        if (bluetoothAdapter.isDiscovering) {
            println("Already discovering")
        } else {
            bluetoothAdapter.startDiscovery()
        }
    }

    fun isDiscovering(): Boolean {
        if (!bluetoothUtilities.checkForBluetoothScanPermission()) {
            bluetoothUtilities.requestBluetoothScanPermission()
            return false
        }
        return bluetoothAdapter.isDiscovering
    }


    fun getPairedDevices(): List<CubeDevice> {

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

        val devices = mutableListOf<CubeDevice>()
        pairedDevices.forEach { device ->
            //do something
            devices.add(CubeDevice(device.name, device.address))
        }
        return devices
    }


    fun onDestroy() {
        activity.unregisterReceiver(receiver)
    }

    fun connectToDevice(
        device: CubeDevice
    ) {

        val bluetoothDevice = bluetoothAdapter.getRemoteDevice(device.address)
        val gattCallback = object : BluetoothGattCallback() {
            override fun onConnectionStateChange(gatt: BluetoothGatt, status: Int, newState: Int) {
                handleConnectionStateChanged(newState, gatt)
            }

            override fun onServicesDiscovered(gatt: BluetoothGatt, status: Int) {
                handleServiceDiscovery(gatt)
            }

            @Deprecated("Deprecated for API 33+")
            override fun onCharacteristicChanged(
                gatt: BluetoothGatt,
                characteristic: BluetoothGattCharacteristic
            ) {
                val timestamp = Calendar.getInstance().timeInMillis
                val data = characteristic.value // The received data from the GiikerCube
                val dataParser = MoveDataParser(ByteBuffer.wrap(data))
                val state = dataParser.parseCubeValue()
                state.timestamp = timestamp
                cubeState.value = state
                lastMove.value = state.lastMove
            }

            override fun onCharacteristicChanged(
                gatt: BluetoothGatt,
                characteristic: BluetoothGattCharacteristic,
                value: ByteArray
            ) {
                val dataParser = MoveDataParser(ByteBuffer.wrap(value))
                val state = dataParser.parseCubeValue()
                cubeState.value = state
                lastMove.value = state.lastMove
            }
        }

        if (!bluetoothUtilities.checkForBluetoothConnectPermission()) {
            bluetoothUtilities.requestBluetoothConnectPermission()
            return
        }
        bluetoothState.value = BluetoothState.Connecting
        this.device = device
        bluetoothDevice.connectGatt(activityContext, false, gattCallback)
    }

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
            bluetoothState.value = BluetoothState.Connected
            try {
                if (device!!.id == -1L) {
                    DeviceDBService(activityContext).addDevice(device!!)
                } else {
                    device!!.lastConnectionTime = Calendar.getInstance()
                    DeviceDBService(activityContext).updateDevice(device!!, device!!.id)
                }
            } catch (exception: NullPointerException) {
                println("Device is null")
            }
            gatt.discoverServices()
            activity.startActivity(onConnectActivityIntent)
            activity.finishAffinity()
        } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
            bluetoothState.value = BluetoothState.Disconnected
            println("Disconnected")
            activity.startActivity(onDisconnectActivityIntent)
        }
    }


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

        if (SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
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

    private fun enableCharacteristicsNotificationsAPI33Minus(
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

    private fun handleDiscoveryReceiverAction(action: String?, intent: Intent) {

        if (BluetoothDevice.ACTION_FOUND == action) {
            val device = intent.parcelable<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
            if (!bluetoothUtilities.checkForBluetoothConnectPermission()) {
                bluetoothUtilities.requestBluetoothConnectPermission()
                return
            } else {
                println(action)
            }

            if (device != null && device.name != null && device.address != null) {
                if(deviceList.none{ it.address == device.address}){
                    val cubeDevice = CubeDevice(device.name, device.address)
                    deviceList.add(cubeDevice)
                    println(cubeDevice)
                }
            }
        } else {
            println(action)
        }
    }

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
