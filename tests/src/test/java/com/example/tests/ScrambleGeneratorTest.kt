package com.example.tests

import com.example.cube_cube.scramble.ScrambleGenerator
import org.junit.Test

class ScrambleGeneratorTest {

    @Test
    fun generate2x2ScrambleTest(){
        val scramble = ScrambleGenerator.generateScramble(2)
        println(scramble)
        assert(scramble.isNotEmpty())
    }

    @Test
    fun generate3x3ScrambleTest(){
        val scramble = ScrambleGenerator.generateScramble()
        println(scramble)
        assert(scramble.isNotEmpty())
    }

    @Test
    fun generate4x4ScrambleTest(){
        val scramble = ScrambleGenerator.generateScramble(4)
        println(scramble)
        assert(scramble.isNotEmpty())
    }
}