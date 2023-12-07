package com.example.smartcubeapp.casedetection.plldetection

import android.content.Context
import com.example.smartcubeapp.casedetection.PositionRepresentationTransformer
import com.example.smartcubeapp.casedetection.plldetection.pllcase.PredefinedPLLCase
import com.example.smartcubeapp.cube.CubeSide
import com.example.smartcubeapp.cube.CubeState

class PLLCaseDetection(private var cubeState: CubeState, private var cubeSide: CubeSide) {

    fun detectCase(context: Context): PredefinedPLLCase? {
        val positionTransformer = PositionRepresentationTransformer(cubeState, cubeSide)
        val position =
            positionTransformer.transformStateToPositionRepresentation<PLLElementPosition>(context)
        return matchCase(position)
    }

    fun matchCase(
        position: Array<Array<PLLElementPosition>>
    ): PredefinedPLLCase? {
        val positionTransformer = PositionRepresentationTransformer(cubeState, cubeSide)
        for (case in PredefinedPLLCase.values()) {
            var positionCopy = position.copyOf()
            for (prePermutationRotationCount in 0..3) {
                var applyCase = case.performPermutation(positionCopy)
                for (postPermutationRotationCount in 0..3) {
                    if (comparePositions(applyCase, cubeSide.solvedState)) {
                        return case
                    }
                    applyCase = positionTransformer.rotatePositionClockwise(applyCase)
                }
                positionCopy = positionTransformer.rotatePositionClockwise(positionCopy)
            }
        }
        println("PLL case not found")
        return PredefinedPLLCase.PLLSkip
    }

    fun comparePositions(
        permutationPosition: Array<Array<PLLElementPosition>>,
        solvedSidePosition: Array<Array<Int>>
    ): Boolean {
        if (permutationPosition[0][0].pieceNumber != solvedSidePosition[0][0]) {
            return false
        }
        if (permutationPosition[0][1].pieceNumber != solvedSidePosition[0][1]) {
            return false
        }
        if (permutationPosition[0][2].pieceNumber != solvedSidePosition[0][2]) {
            return false
        }
        if (permutationPosition[1][0].pieceNumber != solvedSidePosition[1][0]) {
            return false
        }
        if (permutationPosition[1][1].pieceNumber != solvedSidePosition[1][1]) {
            return false
        }
        if (permutationPosition[2][0].pieceNumber != solvedSidePosition[2][0]) {
            return false
        }
        if (permutationPosition[2][1].pieceNumber != solvedSidePosition[2][1]) {
            return false
        }
        return permutationPosition[2][2].pieceNumber == solvedSidePosition[2][2]
    }

    fun changeCubeSide(cubeSide: CubeSide) {
        this.cubeSide = cubeSide
    }

    fun changeCubeState(cubeState: CubeState) {
        this.cubeState = cubeState
    }

}