package com.example.smartcubeapp.scramble

import androidx.compose.runtime.MutableState

enum class ScramblingMode {
    Scrambling,
    Fixing,
    PreparingToScramble
}

class Scramble(
    private var sequence: MutableState<String>,
    val wrongMoves: MutableList<String>,
    private var currentMoveIndex: Int,
    var scramblingMode: ScramblingMode,
    private var remainingMoves: String = sequence.value
) {
    constructor(sequence: MutableState<String>) : this(
        sequence,
        mutableListOf(),
        0,
        ScramblingMode.Scrambling
    ) {
        this.sequence = sequence
    }

    fun processMove(move: String): Boolean {
        if (scramblingMode == ScramblingMode.Fixing) {
            return fixMove(move)
        }
        return nextMove(move)
    }

    fun getCurrentMove(): String {
        if (scramblingMode == ScramblingMode.Fixing) {
            if (wrongMoves.isEmpty()) {
                return ""
            }
            return reverseMove(wrongMoves.first())
        }
        if (remainingMoves.isEmpty()) {
            return ""
        }
        return remainingMoves.split(" ").first()
    }

    fun getRemainingMoves(): String {
        return remainingMoves
    }

    fun generateNewScramble() {
        sequence.value = ScrambleGenerator.generateScramble()
        remainingMoves = sequence.value
    }

    fun nextMove(move: String): Boolean {
        if(scramblingMode == ScramblingMode.Fixing && wrongMoves.isEmpty()){
            scramblingMode = ScramblingMode.Scrambling
        }
        if(remainingMoves == move){
            remainingMoves = ""
            return true
        }
        val expectedMove = getCurrentMove()
        if (expectedMove.contains("2")) {
            if(expectedMove == move){
                remainingMoves = remainingMoves.replaceFirst("$expectedMove ", "")
                return true
            }
            if (move[0] == expectedMove[0]) {
                remainingMoves = remainingMoves.replaceFirst(expectedMove, move)
                return true
            }
        } else if (move == expectedMove) {
            remainingMoves = remainingMoves.replaceFirst("$expectedMove ", "")
            return true
        }
        wrongMoves.add(move)
        return false
    }

    fun fixMove(move: String): Boolean {
        val expectedMove = getCurrentMove()
        if(move == expectedMove){
            wrongMoves.remove(reverseMove(move))
            if(wrongMoves.isEmpty()){
                scramblingMode = ScramblingMode.Scrambling
            }
            return true
        }
        return false
    }

    fun getScramble(): String {
        return sequence.value
    }

    private fun reverseMove(move: String): String {
        return when (move) {
            "U" -> "U'"
            "U'" -> "U"
            "D" -> "D'"
            "D'" -> "D"
            "L" -> "L'"
            "L'" -> "L"
            "R" -> "R'"
            "R'" -> "R"
            "F" -> "F'"
            "F'" -> "F"
            "B" -> "B'"
            "B'" -> "B"
            else -> move
        }
    }
}

