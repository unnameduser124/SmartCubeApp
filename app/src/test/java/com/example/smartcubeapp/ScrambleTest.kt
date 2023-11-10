package com.example.smartcubeapp

import com.example.smartcubeapp.timerUI.ScrambleGenerator
import org.junit.Test

class ScrambleTest {

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