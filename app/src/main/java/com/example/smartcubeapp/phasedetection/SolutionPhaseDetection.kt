package com.example.smartcubeapp.phasedetection

import android.content.Context
import com.example.smartcubeapp.MILLIS_IN_SECOND
import com.example.smartcubeapp.casedetection.olldetection.OLLCaseDetection
import com.example.smartcubeapp.casedetection.olldetection.ollcase.PredefinedOLLCase
import com.example.smartcubeapp.casedetection.plldetection.PLLCaseDetection
import com.example.smartcubeapp.casedetection.plldetection.pllcase.PredefinedPLLCase
import com.example.smartcubeapp.cube.BlueSide
import com.example.smartcubeapp.cube.CubeSide
import com.example.smartcubeapp.cube.GreenSide
import com.example.smartcubeapp.cube.OrangeSide
import com.example.smartcubeapp.cube.RedSide
import com.example.smartcubeapp.cube.Solve
import com.example.smartcubeapp.cube.WhiteSide
import com.example.smartcubeapp.cube.YellowSide
import com.example.smartcubeapp.cube.cubeSides
import com.example.smartcubeapp.solvedatabase.dataclasses.CrossData
import com.example.smartcubeapp.solvedatabase.dataclasses.F2LData
import com.example.smartcubeapp.solvedatabase.dataclasses.OLLData
import com.example.smartcubeapp.solvedatabase.dataclasses.PLLData

class SolutionPhaseDetection(
    private val solution: Solve,
    private val phaseDetection: CubeStatePhaseDetection
) {

    private var crossSide: CubeSide? = null
    private var startPLLEndOLLIndex = -1

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
            if (startPLLEndOLLIndex == -1) {
                setStarIndexPLLEndIndexOLL(context)
                return startPLLEndOLLIndex
            }
            return startPLLEndOLLIndex
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
            if (startPLLEndOLLIndex == -1) {
                setStarIndexPLLEndIndexOLL(context)
                return startPLLEndOLLIndex
            }
            return startPLLEndOLLIndex
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
        if (durationInMillis < 0) return 0.0
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

    fun getPLL(context: Context): PredefinedPLLCase? {
        val crossOppositeSide = getCrossOppositeSide() ?: return null
        val pllStartState =
            solution.solveStateSequence[getStartIndexForPhase(SolvePhase.PLL, context)]
        val caseDetection = PLLCaseDetection(pllStartState, crossOppositeSide)
        return caseDetection.detectCase(context)
    }

    fun getCrossData(context: Context): CrossData? {
        getCrossOppositeSide() ?: return null
        val crossStartIndex = getStartIndexForPhase(SolvePhase.Cross, context)
        val crossEndIndex = getEndIndexForPhase(SolvePhase.Cross, context)
        if (crossStartIndex == -1) return null
        if (crossEndIndex == -1) return null

        val crossStartState =
            solution.solveStateSequence[crossStartIndex]
        val crossDuration = getPhaseDurationInMillis(SolvePhase.Cross, context)
        val crossStartCubeStateID = crossStartState.id
        val crossEndCubeStateID = solution.solveStateSequence[crossEndIndex].id
        val crossMoveCount = getPhaseMoveCount(SolvePhase.Cross, context)

        if (crossDuration <= 0 || crossMoveCount <= 0) return null

        return CrossData(
            solveID = solution.id,
            duration = crossDuration,
            moveCount = crossMoveCount,
            startStateID = crossStartCubeStateID,
            endStateID = crossEndCubeStateID
        )
    }

    fun getF2LData(context: Context): F2LData? {
        getCrossOppositeSide() ?: return null
        val f2lStartIndex = getStartIndexForPhase(SolvePhase.F2L, context)
        val f2lEndIndex = getEndIndexForPhase(SolvePhase.F2L, context)
        if (f2lStartIndex == -1) return null
        if (f2lEndIndex == -1) return null

        val f2lStartState =
            solution.solveStateSequence[getStartIndexForPhase(SolvePhase.F2L, context)]
        val f2lDuration = getPhaseDurationInMillis(SolvePhase.F2L, context)
        val f2lStartCubeStateID = f2lStartState.id
        val f2lEndCubeStateID = solution.solveStateSequence[f2lEndIndex].id
        val f2lMoveCount = getPhaseMoveCount(SolvePhase.F2L, context)

        if (f2lDuration <= 0 || f2lMoveCount <= 0) return null

        return F2LData(
            solveID = solution.id,
            duration = f2lDuration,
            moveCount = f2lMoveCount,
            startStateID = f2lStartCubeStateID,
            endStateID = f2lEndCubeStateID
        )
    }

    fun getOLLData(context: Context): OLLData? {
        getCrossOppositeSide() ?: return null
        val ollStartIndex = getStartIndexForPhase(SolvePhase.OLL, context)
        val ollEndIndex = getEndIndexForPhase(SolvePhase.OLL, context)
        if (ollStartIndex == -1) return null
        if (ollEndIndex == -1) return null

        val ollStartState =
            solution.solveStateSequence[ollStartIndex]
        val ollDuration = getPhaseDurationInMillis(SolvePhase.OLL, context)
        val ollStartCubeStateID = ollStartState.id
        val ollEndCubeStateID = solution.solveStateSequence[ollEndIndex].id
        val ollMoveCount = getPhaseMoveCount(SolvePhase.OLL, context)
        val case = getOLL(context)
        val ollCaseIndex = if (PredefinedOLLCase.values().indexOf(case) != -1) {
            PredefinedOLLCase.values().indexOf(case)
        } else {
            PredefinedOLLCase.values().indexOf(PredefinedOLLCase.OLLSkip)
        }

        if (ollDuration <= 0 || ollMoveCount <= 0) return null

        return OLLData(
            solveID = solution.id,
            duration = ollDuration,
            moveCount = ollMoveCount,
            startStateID = ollStartCubeStateID,
            endStateID = ollEndCubeStateID,
            case = ollCaseIndex
        )
    }

    fun getPLLData(context: Context): PLLData? {
        getCrossOppositeSide() ?: return null
        val pllStartIndex = getStartIndexForPhase(SolvePhase.PLL, context)
        val pllEndIndex = getEndIndexForPhase(SolvePhase.PLL, context)
        if (pllStartIndex == -1) return null
        if (pllEndIndex == -1) return null

        val pllStartState =
            solution.solveStateSequence[pllStartIndex]
        val pllDuration = getPhaseDurationInMillis(SolvePhase.PLL, context)
        val pllStartCubeStateID = pllStartState.id
        val pllEndCubeStateID = solution.solveStateSequence[pllEndIndex].id
        val pllMoveCount = getPhaseMoveCount(SolvePhase.PLL, context)
        val pllCaseIndex = if (PredefinedPLLCase.values()
                .indexOf(getPLL(context)) != -1
        ) PredefinedPLLCase.values().indexOf(getPLL(context)) else PredefinedPLLCase.values()
            .indexOf(PredefinedPLLCase.PLLSkip)

        if (pllDuration <= 0 || pllMoveCount <= 0) return null

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

    fun setStarIndexPLLEndIndexOLL(context: Context) {
        startPLLEndOLLIndex = OLLSolvedIndex(context)
    }
}