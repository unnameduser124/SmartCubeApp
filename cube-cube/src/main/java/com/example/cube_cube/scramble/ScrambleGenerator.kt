package com.example.cube_cube.scramble

import org.worldcubeassociation.tnoodle.puzzle.CubePuzzle

object ScrambleGenerator {
    private const val CUBE_SIZE_3x3x3 = 3
    fun generateScramble(cubeSize: Int = CUBE_SIZE_3x3x3): String {
        return try{
            val puzzle = CubePuzzle(cubeSize) // for some reason this try doesn't catch the error thrown, even though stack trace points to this call as a source of the error
            puzzle.generateScramble()
        } catch(e: Exception){
            println("Error generating scramble: $e")
            println("Returning default scramble")
            "F U' L B' D' F' L2 D' B' U2 R2 D2 B2 F2 U' L' R' F2 B2 L' D2 B2 U2 F2 U"
        }
    }
}