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
import com.example.smartcubeapp.casedetection.olldetection.OLLCaseDetection
import com.example.smartcubeapp.casedetection.olldetection.ollcase.PredefinedOLLCase
import com.example.smartcubeapp.casedetection.plldetection.PLLCaseDetection
import com.example.smartcubeapp.casedetection.plldetection.pllcase.PredefinedPLLCase
import com.example.smartcubeapp.solvedatabase.dataclasses.CrossData
import com.example.smartcubeapp.solvedatabase.dataclasses.F2LData
import com.example.smartcubeapp.solvedatabase.dataclasses.OLLData
import com.example.smartcubeapp.solvedatabase.dataclasses.PLLData

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
        return try {
            solution.solveStateSequence[getStartIndexForPhase(phase, context)].timestamp
        } catch (e: Exception) {
            0L
        }
    }

    fun getEndTimeForPhase(phase: SolvePhase, context: Context): Long {
        return try {
            solution.solveStateSequence[getEndIndexForPhase(phase, context)].timestamp
        } catch (e: Exception) {
            0L
        }
    }

    fun getPhaseDurationInSeconds(phase: SolvePhase, context: Context): Double {
        val durationInMillis =
            getEndTimeForPhase(phase, context) - getStartTimeForPhase(phase, context)
        if(durationInMillis < 0) return 0.0
        return durationInMillis.toDouble() / MILLIS_IN_SECOND
    }

    fun getPhaseDurationInMillis(phase: SolvePhase, context: Context): Long {
        return getEndTimeForPhase(phase, context) - getStartTimeForPhase(phase, context)
    }

    fun getPhaseMoveCount(phase: SolvePhase, context: Context): Int {
        return getEndIndexForPhase(phase, context) - getStartIndexForPhase(phase, context)
    }

    fun getPhaseTPS(phase: SolvePhase, context: Context): Double {
        val moveCount = getPhaseMoveCount(phase, context)
        val duration = getPhaseDurationInSeconds(phase, context)
        return if (duration != 0.0) moveCount / duration else 0.0
    }

    fun getOLL(context: Context): PredefinedOLLCase? {
        val crossOppositeSide = getCrossOppositeSide() ?: return null
        val ollStartState =
            solution.solveStateSequence[getStartIndexForPhase(SolvePhase.OLL, context)]
        val caseDetection = OLLCaseDetection(ollStartState, crossOppositeSide)

        return caseDetection.detectCase(context)
    }

    fun getPLL(context: Context): PredefinedPLLCase?{
        val crossOppositeSide = getCrossOppositeSide() ?: return null
        val pllStartState =
            solution.solveStateSequence[getStartIndexForPhase(SolvePhase.PLL, context)]
        val caseDetection = PLLCaseDetection(pllStartState, crossOppositeSide)
        return caseDetection.detectCase(context)
    }

    fun getCrossData(context: Context): CrossData {
        val crossOppositeSide = getCrossOppositeSide() ?: return CrossData()
        val crossStartState =
            solution.solveStateSequence[getStartIndexForPhase(SolvePhase.Cross, context)]
        val crossDuration = getPhaseDurationInMillis(SolvePhase.Cross, context)
        val crossStartCubeStateID = crossStartState.id
        val crossEndCubeStateID = solution.solveStateSequence[getEndIndexForPhase(SolvePhase.Cross, context)].id
        val crossMoveCount = getPhaseMoveCount(SolvePhase.Cross, context)

        return CrossData(
            solveID = solution.id,
            duration = crossDuration,
            moveCount = crossMoveCount,
            startStateID = crossStartCubeStateID,
            endStateID = crossEndCubeStateID
        )
    }

    fun getF2LData(context: Context): F2LData {
        val crossOppositeSide = getCrossOppositeSide() ?: return F2LData()
        val f2lStartState =
            solution.solveStateSequence[getStartIndexForPhase(SolvePhase.F2L, context)]
        val f2lDuration = getPhaseDurationInMillis(SolvePhase.F2L, context)
        val f2lStartCubeStateID = f2lStartState.id
        val f2lEndCubeStateID = solution.solveStateSequence[getEndIndexForPhase(SolvePhase.F2L, context)].id
        val f2lMoveCount = getPhaseMoveCount(SolvePhase.F2L, context)

        return F2LData(
            solveID = solution.id,
            duration = f2lDuration,
            moveCount = f2lMoveCount,
            startStateID = f2lStartCubeStateID,
            endStateID = f2lEndCubeStateID
        )
    }

    fun getOLLData(context: Context): OLLData {

        val crossOppositeSide = getCrossOppositeSide() ?: return OLLData()
        val ollStartState =
            solution.solveStateSequence[getStartIndexForPhase(SolvePhase.F2L, context)]
        val ollDuration = getPhaseDurationInMillis(SolvePhase.F2L, context)
        val ollStartCubeStateID = ollStartState.id
        val ollEndCubeStateID = solution.solveStateSequence[getEndIndexForPhase(SolvePhase.F2L, context)].id
        val ollMoveCount = getPhaseMoveCount(SolvePhase.F2L, context)
        val ollCaseIndex = PredefinedOLLCase.values().indexOf(getOLL(context))

        return OLLData(
            solveID = solution.id,
            duration = ollDuration,
            moveCount = ollMoveCount,
            startStateID = ollStartCubeStateID,
            endStateID = ollEndCubeStateID,
            case = ollCaseIndex
        )
    }

    fun getPLLData(context: Context): PLLData {
        val crossOppositeSide = getCrossOppositeSide() ?: return PLLData()
        val pllStartState =
            solution.solveStateSequence[getStartIndexForPhase(SolvePhase.PLL, context)]
        val pllDuration = getPhaseDurationInMillis(SolvePhase.PLL, context)
        val pllStartCubeStateID = pllStartState.id
        val pllEndCubeStateID = solution.solveStateSequence[getEndIndexForPhase(SolvePhase.PLL, context)].id
        val pllMoveCount = getPhaseMoveCount(SolvePhase.PLL, context)
        val pllCaseIndex = PredefinedPLLCase.values().indexOf(getPLL(context))

        return PLLData(
            solveID = solution.id,
            duration = pllDuration,
            moveCount = pllMoveCount,
            startStateID = pllStartCubeStateID,
            endStateID = pllEndCubeStateID,
            case = pllCaseIndex
        )
    }

    fun setCrossSide() {
        solution.solveStateSequence.forEach {
            val crossPhaseDetection = CubeStatePhaseDetection(it)
            if (crossPhaseDetection.crossSolved()) {
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