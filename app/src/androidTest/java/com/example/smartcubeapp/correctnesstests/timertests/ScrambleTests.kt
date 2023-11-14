package com.example.smartcubeapp.correctnesstests.timertests

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.smartcubeapp.scramble.Scramble
import com.example.smartcubeapp.scramble.ScramblingMode
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ScrambleTests {

    private lateinit var scramble: Scramble
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        var sequence: MutableState<String>
        composeTestRule.setContent {
            sequence =
                remember {
                    mutableStateOf("F U' L B' D' F' L2 D' B' U2 R2 D2 B2 F2 U' L' R' F2 B2 L' D2 B2 U2 F2 U")
                }

            scramble = Scramble(sequence)
        }
    }

    @Test
    fun nextMoveTest(){
        val movesList = scramble.getRemainingMoves().split(" ")

        for(move in movesList){
            val nextMove = scramble.nextMove(move)
            assert(nextMove)
        }
    }

    @Test
    fun nextMoveTestScrambleCompleted(){
        val movesList = scramble.getRemainingMoves().split(" ")

        for(move in movesList){
            val nextMove = scramble.nextMove(move)
            assert(nextMove)
        }

        val nextMove = scramble.nextMove("U")
        assert(!nextMove)
    }

    @Test
    fun getCurrentMoveTest(){
        val movesList = scramble.getRemainingMoves().split(" ")

        for(move in movesList){
            val currentMove = scramble.getCurrentMove()
            assertEquals(move, currentMove)
            val nextMove = scramble.nextMove(move)
            assert(nextMove)
        }
    }

    @Test
    fun getCurrentMoveScramblingCompleted(){
        val movesList = scramble.getRemainingMoves().split(" ")

        for(move in movesList){
            val currentMove = scramble.getCurrentMove()
            assert(currentMove == move)
            val nextMove = scramble.nextMove(move)
            assert(nextMove)
        }

        val currentMove = scramble.getCurrentMove()
        assertEquals("", currentMove)
    }

    @Test
    fun getCurrentMoveFixingMode(){
        scramble.scramblingMode = ScramblingMode.Fixing

        scramble.wrongMoves.add("U")
        scramble.wrongMoves.add("R")
        scramble.wrongMoves.add("F")

        scramble.wrongMoves.forEach {
            val currentMove = scramble.getCurrentMove()
            assertEquals(it, currentMove)
            assert(scramble.fixMove(it))
        }
    }

    @Test
    fun getCurrentMoveFixingModeFixingFinished(){
        scramble.scramblingMode = ScramblingMode.Fixing

        assertEquals(scramble.scramblingMode, ScramblingMode.Scrambling)
        assert(scramble.nextMove("F"))

    }

    @Test
    fun getRemainingMovesTest(){
        val movesList = scramble.getRemainingMoves().split(" ")

        movesList.forEachIndexed{ index, move ->
            val remainingMoves = scramble.getRemainingMoves()
            val expectedRemainingMoves = movesList.subList(index, movesList.size).joinToString(" ")
            val nextMove = scramble.nextMove(move)
            assert(nextMove)
        }

        val remainingMoves = scramble.getRemainingMoves()
        assertEquals("", remainingMoves)
    }

    @Test
    fun generateNewScrambleTest(){
        val currentScramble = scramble.getRemainingMoves()
        scramble.generateNewScramble()
        val newScramble = scramble.getRemainingMoves()
        assertNotEquals(currentScramble, newScramble)
    }

    @Test
    fun fixMoveTest(){
        scramble.scramblingMode = ScramblingMode.Fixing

        scramble.wrongMoves.add("U")
        scramble.wrongMoves.add("R")
        scramble.wrongMoves.add("F")

        scramble.wrongMoves.forEach {
            val currentMove = scramble.getCurrentMove()
            assertEquals(it, currentMove)
            assert(scramble.fixMove(it))
        }
    }

    @Test
    fun fixMoveNoWrongMoves(){
        scramble.scramblingMode = ScramblingMode.Fixing

        val currentMove = scramble.getCurrentMove()
        assertEquals("", currentMove)
        assert(!scramble.fixMove("U"))
    }

}