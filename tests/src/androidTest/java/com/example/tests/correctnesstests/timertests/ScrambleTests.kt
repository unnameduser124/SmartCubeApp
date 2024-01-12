package com.example.tests.correctnesstests.timertests

import com.example.cube_cube.scramble.Scramble
import com.example.cube_cube.scramble.ScramblingMode
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test

class ScrambleTests {

    private lateinit var scramble: Scramble


    @Before
    fun setUp() {
            val sequence = "F U' L B' D' F' L2 D' B' U2 R2 D2 B2 F2 U' L' R' F2 B2 L' D2 B2 U2 F2 U"
            scramble = Scramble(sequence)
    }

    @Test
    fun nextMoveTest() {
        val movesList = scramble.getRemainingMoves().split(" ")

        for (move in movesList) {
            val nextMove = scramble.nextMove(move)
            assert(nextMove)
        }
    }

    @Test
    fun nextMoveNoDoubleMovesTest() {
        val movesDoublesReplacedWithSingle = scramble.getRemainingMoves().replace("R2", "R R")
            .replace("L2", "L L")
            .replace("U2", "U U")
            .replace("D2", "D' D'")
            .replace("F2", "F F")
            .replace("B2", "B B")

        val movesList = movesDoublesReplacedWithSingle.split(" ")

        for (move in movesList) {
            val nextMove = scramble.nextMove(move)
            assert(nextMove)
        }
    }

    @Test
    fun nextMoveTestScrambleCompleted() {
        val movesList = scramble.getRemainingMoves().split(" ")

        for (move in movesList) {
            val nextMove = scramble.nextMove(move)
            assert(nextMove)
        }

        val nextMove = scramble.nextMove("U")
        assert(!nextMove)
    }

    @Test
    fun getCurrentMoveTest() {
        val movesList = scramble.getRemainingMoves().split(" ")

        for (move in movesList) {
            val currentMove = scramble.getCurrentMove()
            assertEquals(move, currentMove)
            val nextMove = scramble.nextMove(move)
            assert(nextMove)
        }
    }

    @Test
    fun getCurrentMoveScramblingCompleted() {
        val movesList = scramble.getRemainingMoves().split(" ")

        for (move in movesList) {
            val currentMove = scramble.getCurrentMove()
            assert(currentMove == move)
            val nextMove = scramble.nextMove(move)
            assert(nextMove)
        }

        val currentMove = scramble.getCurrentMove()
        assertEquals("", currentMove)
    }

    @Test
    fun getCurrentMoveFixingMode() {
        scramble.scramblingMode = ScramblingMode.Fixing

        scramble.wrongMoves.add("U")
        scramble.wrongMoves.add("R")
        scramble.wrongMoves.add("F")

        val movesToReverse = scramble.wrongMoves.toMutableList()

        movesToReverse.reversed().forEach {
            val currentMove = scramble.getCurrentMove()
            val reversedMove = reverseMove(it)
            assertEquals(reversedMove, currentMove)
            assert(scramble.fixMove(reversedMove))
        }
    }

    @Test
    fun getCurrentMoveFixingModeFixingFinished() {
        val movesToReverse = listOf("U", "R", "B'")
        scramble.wrongMoves.addAll(movesToReverse)
        scramble.scramblingMode = ScramblingMode.Fixing
        movesToReverse.reversed().forEach{
            assert(scramble.fixMove(reverseMove(it)))
        }
        assert(scramble.nextMove("F"))
        assertEquals(scramble.scramblingMode, ScramblingMode.Scrambling)
    }

    @Test
    fun getRemainingMovesTest() {
        val movesList = scramble.getRemainingMoves().split(" ")

        movesList.forEach{ move ->
            val nextMove = scramble.nextMove(move)
            assert(nextMove)
        }

        val remainingMoves = scramble.getRemainingMoves()
        assertEquals("", remainingMoves)
    }

    @Test
    fun generateNewScrambleTest() {
        val currentScramble = scramble.getRemainingMoves()
        scramble.generateNewScramble()
        val newScramble = scramble.getRemainingMoves()
        assertNotEquals(currentScramble, newScramble)
    }

    @Test
    fun fixMoveTest() {
        scramble.scramblingMode = ScramblingMode.Fixing

        scramble.wrongMoves.add("U")
        scramble.wrongMoves.add("R'")
        scramble.wrongMoves.add("F")
        val movesToReverse = scramble.wrongMoves.toMutableList()

        movesToReverse.reversed().forEach {
            val moveReversed = reverseMove(it)
            val currentMove = scramble.getCurrentMove()
            assertEquals(moveReversed, currentMove)
            assert(scramble.fixMove(moveReversed))
        }
    }

    @Test
    fun fixMoveNoWrongMoves() {
        scramble.scramblingMode = ScramblingMode.Fixing

        val currentMove = scramble.getCurrentMove()
        assertEquals("", currentMove)
        assert(!scramble.fixMove("U"))
    }

    @Test
    fun fullScramblingTest() {
        //scramble sequence:
        //F U' L B' D' F' L2 D' B' U2 R2 D2 B2 F2 U' L' R' F2 B2 L' D2 B2 U2 F2 U
        val moves =
            "F U U' U' L B' D' F' L L D D' D' B' U' U' R R D D B' B' F' F' U U' U' L' R' F F B B L' D D B B U U F' F' U' U U"
        val movesToList = moves.split(" ")

        for (move in movesToList) {
            scramble.processMove(move)
        }
        assert(scramble.getRemainingMoves() == "")
    }


    private fun reverseMove(move: String): String {
        return when (move) {
            "U" -> "U'"
            "U'" -> "U"
            "R" -> "R'"
            "R'" -> "R"
            "L" -> "L'"
            "L'" -> "L"
            "F" -> "F'"
            "F'" -> "F"
            "B" -> "B'"
            "B'" -> "B"
            "D" -> "D'"
            "D'" -> "D"
            else -> move
        }
    }

}