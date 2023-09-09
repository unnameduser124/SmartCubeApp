package com.example.smartcubeapp.olldetection

import com.example.smartcubeapp.cube.CubeSide
import com.example.smartcubeapp.cube.CubeState
import com.example.smartcubeapp.cube.piece.PositionRepresentationElement

class OLLCaseDetection(var cubeState: CubeState, private var cubeSide: CubeSide) {

    fun detectOLLCase(): PredefinedOLLCase?{
        TODO("Not implemented yet")
    }

    fun transformStateToPositionRepresentation(): Array<Array<PositionRepresentationElement>>{
        TODO("Not implemented yet")
    }

    fun rotatePositionClockwise(position: Array<Array<PositionRepresentationElement>>): Array<Array<PositionRepresentationElement>>{
        TODO("Not implemented yet")
    }

    fun matchCase(case: OLLCase): PredefinedOLLCase?{
        TODO("Not implemented yet")
    }

    fun positionRepresentationToOLLCase(position: Array<Array<PositionRepresentationElement>>): OLLCase{
        TODO("Not implemented yet")
    }

    fun changeCubeSide(cubeSide: CubeSide){
        this.cubeSide = cubeSide
    }

    fun changeCubeState(cubeState: CubeState){
        this.cubeState = cubeState
    }
}