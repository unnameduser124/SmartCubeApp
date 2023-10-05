package com.example.smartcubeapp.cube

data class Move(
    val face: String,
    val amount: Int,
    val notation: String
) {
    constructor() : this("", 0, "")
    constructor(moveNotation: String) : this(
        face = moveNotation[0].toString(),
        amount = if (moveNotation.contains("2")) 2
        else if (moveNotation.contains("'")) -1
        else 1,
        notation = moveNotation
    )
}
