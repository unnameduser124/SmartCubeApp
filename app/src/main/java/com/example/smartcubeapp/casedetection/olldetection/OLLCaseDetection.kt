package com.example.smartcubeapp.casedetection.olldetection

import android.content.Context
import com.example.smartcubeapp.casedetection.PositionRepresentationTransformer
import com.example.smartcubeapp.casedetection.olldetection.ollcase.CustomOLLCase
import com.example.smartcubeapp.casedetection.olldetection.ollcase.PredefinedOLLCase
import com.example.smartcubeapp.cube.CubeSide
import com.example.smartcubeapp.cube.CubeState
import com.example.smartcubeapp.phasedetection.CubeStatePhaseDetection

class OLLCaseDetection(var cubeState: CubeState, private var cubeSide: CubeSide) {

    fun detectCase(context: Context): PredefinedOLLCase {
        val phaseDetection = CubeStatePhaseDetection(cubeState)
        if(phaseDetection.OLLSolved(context)){
            return PredefinedOLLCase.OLLSkip
        }
        val positionTransformer =
            PositionRepresentationTransformer(
                cubeState,
                cubeSide
            )
        var positionRepresentation: Array<Array<OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)
        for (i in 0..3) {
            val customCase =
                positionTransformer.positionRepresentationToCase<CustomOLLCase, OLLElementOrientation>(
                    positionRepresentation
                )
            val foundCase = matchCase(customCase)
            if (foundCase != null) {
                return foundCase
            }
            positionRepresentation =
                positionTransformer.rotatePositionClockwise(positionRepresentation)
        }
        println("OLL case not found")
        return PredefinedOLLCase.OLLSkip
    }


    fun matchCase(case: CustomOLLCase): PredefinedOLLCase? {
        for (value in PredefinedOLLCase.values()) {
            if (case.equals(value)) {
                return value
            }
        }
        return null
    }

    fun changeCubeSide(cubeSide: CubeSide) {
        this.cubeSide = cubeSide
    }

    fun changeCubeState(cubeState: CubeState) {
        this.cubeState = cubeState
    }

    fun getCubeSide(): CubeSide = cubeSide

}