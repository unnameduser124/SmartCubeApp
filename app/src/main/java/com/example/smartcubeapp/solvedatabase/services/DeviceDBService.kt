package com.example.smartcubeapp.solvedatabase.services

import android.content.Context
import com.example.smartcubeapp.bluetooth.CubeDevice
import com.example.smartcubeapp.solvedatabase.SolveDB
import com.example.smartcubeapp.solvedatabase.SolvesDatabaseConstants

class DeviceDBService(
    context: Context,
    dbName: String = SolvesDatabaseConstants.SOLVE_DATABASE_NAME
) : SolveDB(context, dbName) {

    fun addDevice(device: CubeDevice): Long {
        TODO("Not implemented yet")
    }

    fun removeDevice(id: Long) {
        TODO("Not implemented yet")
    }

    fun updateDevice(newDevice: CubeDevice, id: Long) {
        TODO("Not implemented yet")
    }

    fun getDevice(id: Long): CubeDevice? {
        TODO("Not implemented yet")
    }
}