package com.example.smartcubeapp.timerUI

import org.worldcubeassociation.tnoodle.puzzle.CubePuzzle

object ScrambleGenerator {
    private const val CUBE_SIZE_3x3x3 = 3
    fun generateScramble(cubeSize: Int = CUBE_SIZE_3x3x3): String {
        val puzzle = CubePuzzle(cubeSize)
        return puzzle.generateScramble()
    }
}