package com.example.smartcubeapp.olldetection

import com.example.smartcubeapp.cube.CubeSide
import com.example.smartcubeapp.cube.CubeState
import com.example.smartcubeapp.cube.piece.ElementOrientation
import com.example.smartcubeapp.cube.piece.PositionRepresentationElement

class OLLCaseDetection(val cubeState: CubeState, private var cubeSide: CubeSide) {

    fun detectOLLCase(): OLLCase?{
        TODO("Not implemented yet")
    }

    fun transformStateToPositionRepresentation(): Array<Array<PositionRepresentationElement>>{
        TODO("Not implemented yet")
    }

    fun rotatePositionClockwise(position: Array<Array<PositionRepresentationElement>>): Array<Array<PositionRepresentationElement>>{
        TODO("Not implemented yet")
    }

    fun matchCase(case: OLLCase): OLLCase?{
        TODO("Not implemented yet")
    }

    fun positionRepresentationToOLLCase(position: Array<Array<PositionRepresentationElement>>): OLLCase{
        TODO("Not implemented yet")
    }

    fun setCubeSide(cubeSide: CubeSide){
        this.cubeSide = cubeSide
    }
}