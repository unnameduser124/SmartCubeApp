package com.example.smartcubeapp.ui.timerUI

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.runtime.MutableState
import com.example.cube_bluetooth.bluetooth.cubeState
import com.example.cube_bluetooth.bluetooth.lastMove
import com.example.cube_cube.cube.CubeState
import com.example.cube_cube.scramble.Scramble
import com.example.cube_cube.scramble.ScramblingMode
import com.example.cube_global.solve

class ScrambleHandler(
    val context: Context,
    private val activityContext: Activity,
    private val scramble: Scramble,
    private val scrambleSequence: MutableState<String>,
) {

    fun handle(
        lastState: CubeState,
    ) {
        handleCubeNotSolved(scramble, lastState)
        handleScrambling(scramble, scrambleSequence, lastState)
    }

    fun handleScrambleGeneration(){
        scramble.generateNewScramble()
        scrambleSequence.value = scramble.getRemainingMoves()
        if (!cubeState.value.isSolved()) {
            scramble.scramblingMode = ScramblingMode.PreparingToScramble
            scrambleSequence.value = "Solve the cube before scrambling"
        } else {
            scrambleSequence.value = scramble.getRemainingMoves()
        }
    }

    private fun handleCubeNotSolved(scramble: Scramble, lastState: CubeState) {
        if (
            !cubeState.value.isSolved()
            && scramble.getRemainingMoves() == scramble.getScramble()
            && !lastState.isSolved()
        ) {
            scramble.scramblingMode = ScramblingMode.PreparingToScramble
        }
    }

    private fun handleScrambling(
        scramble: Scramble,
        scrambleSequence: MutableState<String>,
        lastState: CubeState
    ) {
        if (scramble.scramblingMode == ScramblingMode.PreparingToScramble) {
            processMovePreparingToScramble(lastState)
        } else if (cubeState.value != lastState) {
            if (lastState.cornerPositions.isNotEmpty()) {
                changeLastState(cubeState.value, lastState)
                scramble.processMove(lastMove.value.notation)
                scrambleSequence.value = scramble.getRemainingMoves()
            } else {
                changeLastState(cubeState.value, lastState)
            }
            if (scramble.scramblingMode == ScramblingMode.Scrambled) {
                scramblingFinished()
            }
        }
    }

    private fun changeLastState(newState: CubeState, lastState: CubeState) {
        lastState.cornerPositions.clear()
        lastState.cornerPositions.addAll(newState.cornerPositions)
        lastState.cornerOrientations.clear()
        lastState.cornerOrientations.addAll(newState.cornerOrientations)
        lastState.edgePositions.clear()
        lastState.edgePositions.addAll(newState.edgePositions)
        lastState.edgeOrientations.clear()
        lastState.edgeOrientations.addAll(newState.edgeOrientations)
    }

    private fun processMovePreparingToScramble(lastState: CubeState){
        scrambleSequence.value = "Solve the cube before scrambling"
        if (scramble.scramblingMode == ScramblingMode.PreparingToScramble && cubeState.value.isSolved()) {
            scramble.scramblingMode = ScramblingMode.Scrambling
            scramble.wrongMoves.clear()
            scrambleSequence.value = scramble.getRemainingMoves()
            changeLastState(cubeState.value, lastState)
        }
    }

    private fun scramblingFinished(){
        solve.prepareForNewSolve()
        solve.scrambledState = cubeState.value
        solve.scrambleSequence = scramble.getScramble()
        Toast.makeText(context, "Scrambled", Toast.LENGTH_SHORT).show()
        val intent = Intent(context, SolvingActivity::class.java)
        context.startActivity(intent)
        activityContext.finish()
    }
}