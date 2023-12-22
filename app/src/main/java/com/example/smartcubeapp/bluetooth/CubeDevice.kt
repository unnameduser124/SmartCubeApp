package com.example.smartcubeapp.bluetooth

import java.util.Calendar


data class CubeDevice(val name: String, val address: String, var lastConnectionTime: Calendar = Calendar.getInstance(), val id: Long = -1)
