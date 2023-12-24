package com.example.cube_database.solvedatabase.services

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import com.example.cube_cube.CubeDevice
import com.example.cube_database.solvedatabase.SolveDB
import com.example.cube_database.solvedatabase.SolvesDatabaseConstants
import java.util.Calendar

class DeviceDBService(
    context: Context,
    dbName: String = SolvesDatabaseConstants.SOLVE_DATABASE_NAME
) : SolveDB(context, dbName) {

    fun addDevice(device: CubeDevice): Long {
        if (device.name == "" || device.address == "" || device.lastConnectionTime.timeInMillis <= 0) {
            println("Device name or address is empty")
            return -1
        }

        val values = ContentValues().apply {
            put(SolvesDatabaseConstants.DeviceTable.DEVICE_NAME_COLUMN, device.name)
            put(SolvesDatabaseConstants.DeviceTable.DEVICE_ADDRESS_COLUMN, device.address)
            put(
                SolvesDatabaseConstants.DeviceTable.LAST_CONNECTION_TIME_COLUMN,
                device.lastConnectionTime.timeInMillis
            )
        }

        return writableDatabase.insert(SolvesDatabaseConstants.DeviceTable.TABLE_NAME, null, values)
    }

    fun removeDevice(id: Long) {
        val selection = "${BaseColumns._ID} = ?"
        val selectionArgs = arrayOf(id.toString())

        writableDatabase.delete(
            SolvesDatabaseConstants.DeviceTable.TABLE_NAME,
            selection,
            selectionArgs
        )
    }

    fun updateDevice(newDevice: CubeDevice, id: Long) {
        if (newDevice.name == "" || newDevice.address == "" || newDevice.lastConnectionTime.timeInMillis <= 0) {
            println("Device name or address is empty")
            return
        }
        val values = ContentValues().apply {
            put(SolvesDatabaseConstants.DeviceTable.DEVICE_NAME_COLUMN, newDevice.name)
            put(SolvesDatabaseConstants.DeviceTable.DEVICE_ADDRESS_COLUMN, newDevice.address)
            put(
                SolvesDatabaseConstants.DeviceTable.LAST_CONNECTION_TIME_COLUMN,
                newDevice.lastConnectionTime.timeInMillis
            )
        }

        val selection = "${BaseColumns._ID} = ?"
        val selectionArgs = arrayOf(id.toString())

        writableDatabase.update(
            SolvesDatabaseConstants.DeviceTable.TABLE_NAME,
            values,
            selection,
            selectionArgs
        )
    }

    fun getDevice(id: Long): CubeDevice? {
        val projection = arrayOf(
            SolvesDatabaseConstants.DeviceTable.DEVICE_NAME_COLUMN,
            SolvesDatabaseConstants.DeviceTable.DEVICE_ADDRESS_COLUMN,
            SolvesDatabaseConstants.DeviceTable.LAST_CONNECTION_TIME_COLUMN
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
                val name =
                    getString(getColumnIndexOrThrow(SolvesDatabaseConstants.DeviceTable.DEVICE_NAME_COLUMN))
                val address =
                    getString(getColumnIndexOrThrow(SolvesDatabaseConstants.DeviceTable.DEVICE_ADDRESS_COLUMN))
                val lastConnectionMillis =
                    getLong(getColumnIndexOrThrow(SolvesDatabaseConstants.DeviceTable.LAST_CONNECTION_TIME_COLUMN))

                val lastConnectionTime = Calendar.getInstance().apply { timeInMillis = lastConnectionMillis }

                return CubeDevice(
                    name,
                    address,
                    lastConnectionTime,
                    id
                )
            }
        }
        return null
    }

    fun getAllDevices(): List<CubeDevice>{
        val projection = arrayOf(
            BaseColumns._ID,
            SolvesDatabaseConstants.DeviceTable.DEVICE_NAME_COLUMN,
            SolvesDatabaseConstants.DeviceTable.DEVICE_ADDRESS_COLUMN,
            SolvesDatabaseConstants.DeviceTable.LAST_CONNECTION_TIME_COLUMN
        )

        val cursor = readableDatabase.query(
            SolvesDatabaseConstants.DeviceTable.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        val devices = mutableListOf<CubeDevice>()
        with(cursor){
            while(moveToNext()){
                val id = getLong(getColumnIndexOrThrow(BaseColumns._ID))
                val name = getString(getColumnIndexOrThrow(SolvesDatabaseConstants.DeviceTable.DEVICE_NAME_COLUMN))
                val address = getString(getColumnIndexOrThrow(SolvesDatabaseConstants.DeviceTable.DEVICE_ADDRESS_COLUMN))
                val lastConnectionMillis = getLong(getColumnIndexOrThrow(SolvesDatabaseConstants.DeviceTable.LAST_CONNECTION_TIME_COLUMN))

                val lastConnectionTime = Calendar.getInstance().apply { timeInMillis = lastConnectionMillis }

                devices.add(
                    CubeDevice(
                        name,
                        address,
                        lastConnectionTime,
                        id
                    )
                )
            }
        }
        return devices
    }

    fun getLastDevice(): CubeDevice?{
        val projection = arrayOf(
            BaseColumns._ID,
            SolvesDatabaseConstants.DeviceTable.DEVICE_NAME_COLUMN,
            SolvesDatabaseConstants.DeviceTable.DEVICE_ADDRESS_COLUMN,
            SolvesDatabaseConstants.DeviceTable.LAST_CONNECTION_TIME_COLUMN
        )
        val orderBy = "${SolvesDatabaseConstants.DeviceTable.LAST_CONNECTION_TIME_COLUMN} DESC LIMIT 1"

        val cursor = readableDatabase.query(
            SolvesDatabaseConstants.DeviceTable.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            orderBy
        )

        with(cursor){
            if(moveToFirst()){
                val id = getLong(getColumnIndexOrThrow(BaseColumns._ID))
                val name = getString(getColumnIndexOrThrow(SolvesDatabaseConstants.DeviceTable.DEVICE_NAME_COLUMN))
                val address = getString(getColumnIndexOrThrow(SolvesDatabaseConstants.DeviceTable.DEVICE_ADDRESS_COLUMN))
                val lastConnectionMillis = getLong(getColumnIndexOrThrow(SolvesDatabaseConstants.DeviceTable.LAST_CONNECTION_TIME_COLUMN))

                val lastConnectionTime = Calendar.getInstance().apply { timeInMillis = lastConnectionMillis }

                return CubeDevice(
                    name,
                    address,
                    lastConnectionTime,
                    id
                )
            }
        }
        return null
    }
}