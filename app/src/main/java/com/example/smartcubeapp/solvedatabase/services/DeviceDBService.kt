package com.example.smartcubeapp.solvedatabase.services

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import com.example.smartcubeapp.bluetooth.CubeDevice
import com.example.smartcubeapp.solvedatabase.SolveDB
import com.example.smartcubeapp.solvedatabase.SolvesDatabaseConstants

class DeviceDBService(
    context: Context,
    dbName: String = SolvesDatabaseConstants.SOLVE_DATABASE_NAME
) : SolveDB(context, dbName) {

    fun addDevice(device: CubeDevice): Long {
        if(device.name == "" || device.address == ""){
            println("Device name or address is empty")
            return -1
        }

        val values = ContentValues().apply {
            put(SolvesDatabaseConstants.DeviceTable.DEVICE_NAME_COLUMN, device.name)
            put(SolvesDatabaseConstants.DeviceTable.DEVICE_ADDRESS_COLUMN, device.address)
        }

        return writableDatabase.insert(SolvesDatabaseConstants.DeviceTable.TABLE_NAME, null, values)
    }

    fun removeDevice(id: Long) {
        val selection = "${BaseColumns._ID} = ?"
        val selectionArgs = arrayOf(id.toString())

        writableDatabase.delete(SolvesDatabaseConstants.DeviceTable.TABLE_NAME, selection, selectionArgs)
    }

    fun updateDevice(newDevice: CubeDevice, id: Long) {
        if(newDevice.name == "" || newDevice.address == ""){
            println("Device name or address is empty")
            return
        }
        val values = ContentValues().apply {
            put(SolvesDatabaseConstants.DeviceTable.DEVICE_NAME_COLUMN, newDevice.name)
            put(SolvesDatabaseConstants.DeviceTable.DEVICE_ADDRESS_COLUMN, newDevice.address)
        }

        val selection = "${BaseColumns._ID} = ?"
        val selectionArgs = arrayOf(id.toString())

        writableDatabase.update(SolvesDatabaseConstants.DeviceTable.TABLE_NAME, values, selection, selectionArgs)
    }

    fun getDevice(id: Long): CubeDevice? {
        val projection = arrayOf(
            SolvesDatabaseConstants.DeviceTable.DEVICE_NAME_COLUMN,
            SolvesDatabaseConstants.DeviceTable.DEVICE_ADDRESS_COLUMN
        )
        val selection = "${BaseColumns._ID} = ?"
        val selectionArgs = arrayOf(id.toString())

        val cursor = readableDatabase.query(
            SolvesDatabaseConstants.DeviceTable.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )
        with(cursor) {
            if (moveToFirst()) {
                val name = getString(getColumnIndexOrThrow(SolvesDatabaseConstants.DeviceTable.DEVICE_NAME_COLUMN))
                val address = getString(getColumnIndexOrThrow(SolvesDatabaseConstants.DeviceTable.DEVICE_ADDRESS_COLUMN))

                return CubeDevice(name, address, id)
            }
        }
        return null
    }
}