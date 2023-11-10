package com.example.smartcubeapp.timerUI

import org.worldcubeassociation.tnoodle.puzzle.CubePuzzle

class ScrambleGenerator {
    fun generate3x3CubeScramble(): String {
        val puzzle = CubePuzzle(CUBE_SIZE)
        return puzzle.generateScramble()
    }

    companion object{
        const val CUBE_SIZE = 3
    }
}