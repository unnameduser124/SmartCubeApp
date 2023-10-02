package com.example.smartcubeapp.solvedatabase.dataclasses

import android.content.Context
import com.example.smartcubeapp.casedetection.olldetection.ollcase.PredefinedOLLCase
import com.example.smartcubeapp.casedetection.plldetection.pllcase.PredefinedPLLCase
import com.example.smartcubeapp.cube.CubeState
import com.example.smartcubeapp.cube.Solve
import com.example.smartcubeapp.phasedetection.CubeStatePhaseDetection
import com.example.smartcubeapp.phasedetection.SolutionPhaseDetection
import com.example.smartcubeapp.phasedetection.SolvePhase
import com.example.smartcubeapp.solvedatabase.services.CubeStateDBService
import com.example.smartcubeapp.solvedatabase.services.F2LDBService
import com.example.smartcubeapp.solvedatabase.services.OLLDBService
import com.example.smartcubeapp.solvedatabase.services.PLLDBService
import com.example.smartcubeapp.solvedatabase.services.SolveDBService

data class SolveAnalysisData(
    val solveID: Long,
    val solveDuration: Long,
    val timestamp: Long,
    val solveStateSequence: List<CubeStateData>,
    val scrambleSequence: String,
    var f2lData: F2LData,
    var ollData: OLLData,
    var pllData: PLLData
) {
    //Solve cube states, F2L, OLL and PLL data are saved to the database in this constructor
    //I essentially save the whole thing to the database here
    //Which is not how I want it to work at all
    //Apparently it turns out that if I want to convert solve to solveAnalysisData I have to save things to the database
    //if I want ids for the cube states and solve
    //TODO("Reconsider the need for SolveAnalysisData class")
    //TODO("Add cross data class")
    constructor(solve: Solve, context: Context) : this(
        solveID = solve.id,
        solveDuration = solve.time,
        timestamp = solve.date.timeInMillis,
        solveStateSequence = solve.solveStateSequence.mapIndexed { index, cubeState ->
            CubeStateData(cubeState, index)
        },
        scrambleSequence = solve.scrambleSequence,
        f2lData = F2LData(),
        ollData = OLLData(),
        pllData = PLLData()
    ) {
        val solutionPhaseDetection = SolutionPhaseDetection(
            solve,
            CubeStatePhaseDetection(CubeState.SOLVED_CUBE_STATE)
        )

        val f2lDuration = solutionPhaseDetection.getPhaseDurationInMillis(SolvePhase.F2L, context)
        val ollDuration = solutionPhaseDetection.getPhaseDurationInMillis(SolvePhase.OLL, context)
        val pllDuration = solutionPhaseDetection.getPhaseDurationInMillis(SolvePhase.PLL, context)

        val f2lMoveCount = solutionPhaseDetection.getPhaseMoveCount(SolvePhase.F2L, context)
        val ollMoveCount = solutionPhaseDetection.getPhaseMoveCount(SolvePhase.OLL, context)
        val pllMoveCount = solutionPhaseDetection.getPhaseMoveCount(SolvePhase.PLL, context)

        val ollCase = solutionPhaseDetection.getOLL(context)
        val pllCase = solutionPhaseDetection.getPLL(context)

        val solveDBService = SolveDBService(context)
        val solveID = solveDBService.addSolve(SolveData(solve))

        val cubeStateDBService = CubeStateDBService(context)
        solve.solveStateSequence.forEachIndexed { index, cubeState ->
            cubeState.solveID = solveID
            solveStateSequence[index].solveID = solveID
            solveStateSequence[index].id = cubeStateDBService.addCubeState(
                CubeStateData(cubeState, index)
            )
        }

        f2lData = F2LData(
            solveID,
            f2lDuration,
            f2lMoveCount,
            solveStateSequence[solutionPhaseDetection.getStartIndexForPhase(SolvePhase.F2L, context)].id,
            solveStateSequence[solutionPhaseDetection.getEndIndexForPhase(SolvePhase.F2L, context)].id
        )

        ollData = OLLData(
            solveID,
            ollDuration,
            ollMoveCount,
            solveStateSequence[solutionPhaseDetection.getStartIndexForPhase(SolvePhase.OLL, context)].id,
            solveStateSequence[solutionPhaseDetection.getEndIndexForPhase(SolvePhase.OLL, context)].id,
            PredefinedOLLCase.values().indexOf(ollCase)
        )

        pllData = PLLData(
            solveID,
            pllDuration,
            pllMoveCount,
            solveStateSequence[solutionPhaseDetection.getStartIndexForPhase(SolvePhase.PLL, context)].id,
            solveStateSequence[solutionPhaseDetection.getEndIndexForPhase(SolvePhase.PLL, context)].id,
            PredefinedPLLCase.values().indexOf(pllCase)
        )

        F2LDBService(context).addF2LData(f2lData)
        OLLDBService(context).addOLLData(ollData)
        PLLDBService(context).addPLLData(pllData)
    }
}