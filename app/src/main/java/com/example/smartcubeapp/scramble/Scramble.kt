package com.example.smartcubeapp.scramble

import androidx.compose.runtime.MutableState

enum class ScramblingMode {
    Scrambling,
    Fixing
}
class Scramble(
    private var sequence: MutableState<String>,
    val wrongMoves: MutableList<String>,
    private var currentMoveIndex: Int,
    var scramblingMode: ScramblingMode
) {
    constructor(sequence: MutableState<String>): this(sequence, mutableListOf(), 0, ScramblingMode.Scrambling){
        generateNewScramble()
    }

    fun getCurrentMove(): String{
        TODO("Not implemented yet")
    }

    fun getRemainingMoves(): String{
        TODO("Not implemented yet")
    }

    fun generateNewScramble(){
        TODO("Not implemented yet")
    }

    fun nextMove(move: String): Boolean{
        TODO("Not implemented yet")
    }

    fun fixMove(move: String): Boolean{
        TODO("Not implemented yet")
    }

    private fun reverseMove(move: String): String{
        TODO("Not implemented yet")
    }
}

