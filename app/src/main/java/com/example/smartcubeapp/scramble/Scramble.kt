package com.example.smartcubeapp.scramble

enum class ScramblingMode {
    Scrambling,
    Fixing,
    PreparingToScramble,
    Scrambled
}

class Scramble(
    private var sequence: String,
    val wrongMoves: MutableList<String>,
    var scramblingMode: ScramblingMode,
    private var remainingMoves: String = sequence
) {

    constructor(sequence: String) : this(
        sequence,
        mutableListOf(),
        ScramblingMode.Scrambling
    ) {
        this.sequence = sequence
        this.remainingMoves = sequence
    }

    fun processMove(move: String): Boolean {
        if(scramblingMode == ScramblingMode.Scrambled){
            return false
        }
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
            return reverseMove(wrongMoves.last())
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
        sequence= ScrambleGenerator.generateScramble()
        remainingMoves = sequence
        wrongMoves.clear()
    }

    fun nextMove(move: String): Boolean {
        if(remainingMoves == move){
            remainingMoves = ""
            scramblingMode = ScramblingMode.Scrambled
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
        scramblingMode = ScramblingMode.Fixing
        return false
    }

    fun fixMove(move: String): Boolean {
        val expectedMove = getCurrentMove()
        if(move == expectedMove){
            wrongMoves.removeLast()
            if(wrongMoves.isEmpty()){
                scramblingMode = ScramblingMode.Scrambling
            }
            return true
        }
        wrongMoves.add(move)
        return false
    }

    fun getScramble(): String {
        return sequence
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

