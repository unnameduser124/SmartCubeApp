package com.example.smartcubeapp.phasedetection

import android.content.Context
import com.example.smartcubeapp.MILLIS_IN_SECOND
import com.example.smartcubeapp.cube.BlueSide
import com.example.smartcubeapp.cube.CubeSide
import com.example.smartcubeapp.cube.GreenSide
import com.example.smartcubeapp.cube.OrangeSide
import com.example.smartcubeapp.cube.RedSide
import com.example.smartcubeapp.cube.Solve
import com.example.smartcubeapp.cube.WhiteSide
import com.example.smartcubeapp.cube.YellowSide
import com.example.smartcubeapp.cube.cubeSides

class SolutionPhaseDetection(
    private val solution: Solve,
    private val phaseDetection: CubeStatePhaseDetection
) {

    private var crossSide: CubeSide? = null

    fun getStartIndexForPhase(phase: SolvePhase, context: Context): Int {
        if (phase == SolvePhase.Cross) {
            return 0
        }
        if (phase == SolvePhase.F2L) {
            return crossSolvedIndex()
        }
        if (phase == SolvePhase.OLL) {
            return F2LSolvedIndex()
        }
        if (phase == SolvePhase.PLL) {
            return OLLSolvedIndex(context)
        }
        return -1
    }


    fun getEndIndexForPhase(phase: SolvePhase, context: Context): Int {
        if (phase == SolvePhase.Cross) {
            return crossSolvedIndex()
        }
        if (phase == SolvePhase.F2L) {
            return F2LSolvedIndex()
        }
        if (phase == SolvePhase.OLL) {
            return OLLSolvedIndex(context)
        }
        if (phase == SolvePhase.PLL) {
            return PLLSolvedIndex()
        }
        return -1
    }


    fun crossSolvedIndex(): Int {
        solution.solveStateSequence.forEachIndexed { index, cubeState ->
            phaseDetection.changeState(cubeState)
            if (phaseDetection.crossSolved()) {
                return index
            }
        }
        return -1
    }

    fun F2LSolvedIndex(): Int {
        solution.solveStateSequence.forEachIndexed { index, cubeState ->
            phaseDetection.changeState(cubeState)
            if (phaseDetection.F2LSolved(getCrossSide())) {
                return index
            }
        }
        return -1
    }

    fun OLLSolvedIndex(context: Context): Int {
        solution.solveStateSequence.forEachIndexed { index, cubeState ->
            phaseDetection.changeState(cubeState)
            if (phaseDetection.OLLSolved(context, getCrossSide())) {
                return index
            }
        }
        return -1
    }

    fun PLLSolvedIndex(): Int {
        solution.solveStateSequence.forEachIndexed { index, cubeState ->
            if (cubeState.isSolved()) {
                return index
            }
        }
        return -1
    }

    fun getStartTimeForPhase(phase: SolvePhase, context: Context): Long {
        return solution.solveStateSequence[getStartIndexForPhase(phase, context)].timestamp
    }

    fun getEndTimeForPhase(phase: SolvePhase, context: Context): Long {
        return solution.solveStateSequence[getEndIndexForPhase(phase, context)].timestamp
    }

    fun getPhaseDurationInSeconds(phase: SolvePhase, context: Context): Double {
        val durationInMillis =
            getEndTimeForPhase(phase, context) - getStartTimeForPhase(phase, context)
        return durationInMillis.toDouble() / MILLIS_IN_SECOND
    }

    fun getPhaseMoveCount(phase: SolvePhase, context: Context): Int {
        return getEndIndexForPhase(phase, context) - getStartIndexForPhase(phase, context)
    }

    fun getPhaseTPS(phase: SolvePhase, context: Context): Double {
        val moveCount = getPhaseMoveCount(phase, context)
        val duration = getPhaseDurationInSeconds(phase, context)
        return if (duration != 0.0) moveCount / duration else 0.0
    }

    fun setCrossSide() {
        solution.solveStateSequence.forEach {
            phaseDetection.changeState(it)
            if (phaseDetection.crossSolved()) {
                val correctlySolvedEdges = it.getCorrectlySolvedPieces().second
                for (side in cubeSides) {
                    if (correctlySolvedEdges.containsAll(side.edgeIndexes.toList())) {
                        crossSide = side
                        return
                    }
                }
            }
        }
    }

    fun getCrossSide(): CubeSide? {
        if (crossSide == null) {
            setCrossSide()
            if (crossSide == null) {
                return null
            }
        }
        return crossSide
    }

    fun getCrossOppositeSide(): CubeSide? {
        setCrossSide()
        if (crossSide == null) {
            return null
        }
        if (crossSide == WhiteSide) {
            return YellowSide
        }
        if (crossSide == YellowSide) {
            return WhiteSide
        }
        if (crossSide == GreenSide) {
            return BlueSide
        }
        if (crossSide == BlueSide) {
            return GreenSide
        }
        if (crossSide == RedSide) {
            return OrangeSide
        }
        if (crossSide == OrangeSide) {
            return RedSide
        }
        return null
    }
}