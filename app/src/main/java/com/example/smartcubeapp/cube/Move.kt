package com.example.smartcubeapp.cube

data class Move(
    val face: String,
    val amount: Int,
    val notation: String
){
    constructor(): this("", 0, "")
}
