package com.example.smartcubeapp

import kotlin.math.roundToInt

fun roundDouble(value: Double, multiplier: Int): Double {
    return (value * multiplier).roundToInt() / multiplier.toDouble()
}