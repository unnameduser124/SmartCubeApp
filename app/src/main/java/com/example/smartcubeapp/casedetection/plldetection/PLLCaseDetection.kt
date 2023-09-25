package com.example.smartcubeapp.casedetection.plldetection

import com.example.smartcubeapp.casedetection.plldetection.pllcase.PLLCase
import com.example.smartcubeapp.casedetection.plldetection.pllcase.PredefinedPLLCase
import com.example.smartcubeapp.cube.CubeSide
import com.example.smartcubeapp.cube.CubeState

class PLLCaseDetection(private var cubeState: CubeState, private var cubeSide: CubeSide) {

    fun detectPLLCase(): PredefinedPLLCase? {
        TODO("Not implemented yet")
    }

    fun matchCase(): PredefinedPLLCase? {
        TODO("Not implemented yet")
    }

    fun changeCubeSide(cubeSide: CubeSide) {
        this.cubeSide = cubeSide
    }

    fun changeCubeState(cubeState: CubeState) {
        this.cubeState = cubeState
    }

}