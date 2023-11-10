package com.example.smartcubeapp

import com.example.smartcubeapp.timerUI.ScrambleGenerator
import org.junit.Test

class ScrambleTest {

    @Test
    fun generate3x3ScrambleTest(){
        val generator = ScrambleGenerator()
        val scramble = generator.generate3x3CubeScramble()
        println(scramble)
        assert(scramble.isNotEmpty())
    }
}