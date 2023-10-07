package com.example.smartcubeapp.solvedatabase.services

import android.content.Context
import com.example.smartcubeapp.cube.CubeState
import com.example.smartcubeapp.cube.Solve
import com.example.smartcubeapp.phasedetection.CubeStatePhaseDetection
import com.example.smartcubeapp.phasedetection.SolutionPhaseDetection
import com.example.smartcubeapp.solvedatabase.SolveDB
import com.example.smartcubeapp.solvedatabase.SolvesDatabaseConstants
import com.example.smartcubeapp.solvedatabase.dataclasses.CrossData
import com.example.smartcubeapp.solvedatabase.dataclasses.CubeStateData
import com.example.smartcubeapp.solvedatabase.dataclasses.F2LData
import com.example.smartcubeapp.solvedatabase.dataclasses.OLLData
import com.example.smartcubeapp.solvedatabase.dataclasses.PLLData
import com.example.smartcubeapp.solvedatabase.dataclasses.SolveAnalysisData
import com.example.smartcubeapp.solvedatabase.dataclasses.SolveData

class SolveAnalysisDBService(val context: Context, val dbName: String = SolvesDatabaseConstants.SOLVE_DATABASE_NAME) :
    SolveDB(context, dbName) {

    fun saveSolveWithAnalysis(solve: Solve): SolveAnalysisData{

        if(solve.time < 0){
            throw IllegalArgumentException("Solve time must be greater than 0")
        }
        if(solve.solveStateSequence.isEmpty()){
            throw IllegalArgumentException("Solve must have at least one cube state")
        }
        if(solve.scrambledState == CubeState.SOLVED_CUBE_STATE){
            throw IllegalArgumentException("Solve start state cannot be solved state")
        }
        if(solve.solveStartTime <= 0){
            throw IllegalArgumentException("Solve start time must be greater than 0")
        }
        if(solve.solveInProgress){
            throw IllegalArgumentException("Solve must be completed before saving")
        }

        solve.calculateTimeFromStateSequence()
        val solveData = SolveData(solve)
        solveData.id = SolveDBService(context, dbName).addSolve(solveData)
        solve.id = solveData.id
        solve.solveStateSequence.forEachIndexed{ index, cubeState ->
            cubeState.solveID = solveData.id
            val cubeStateData = CubeStateData(cubeState, index)
            cubeState.id = CubeStateDBService(context, dbName).addCubeState(cubeStateData)
        }

        val pllData = getPLLData(solve)
        val ollData = getOLLData(solve)
        val f2lData = getF2LData(solve)
        val crossData = getCrossData(solve)

        if(pllData!=null){
            pllData.id = PLLDBService(context, dbName).addPLLData(pllData)
        }
        if(ollData!=null){
            ollData.id = OLLDBService(context, dbName).addOLLData(ollData)
        }
        if(f2lData!=null){
            f2lData.id = F2LDBService(context, dbName).addF2LData(f2lData)
        }
        if(crossData!=null){
            crossData.id = CrossDBService(context, dbName).addCrossData(crossData)
        }
        return SolveAnalysisData(
            solveData,
            crossData,
            f2lData,
            ollData,
            pllData,
            solve.solveStateSequence
        )
    }


    fun deleteSolveWithAnalysisData(solveID: Long){
        SolveDBService(context).deleteSolve(solveID)
        //TODO("Add service methods for deleting analysis data for solve ID")
        //CubeStateDBService(context).deleteCubeStatesForSolve(solveID)
        //PLLDBService(context).deletePLLDataForSolve(solveID)
        //OLLDBService(context).deleteOLLDataForSolve(solveID)
        //F2LDBService(context).deleteF2LDataForSolve(solveID)
        //CrossDBService(context).deleteCrossDataForSolve(solveID)
    }
    fun getSolveAnalysisForSolve(solveID: Long): SolveAnalysisData? {
        val solveData = SolveDBService(context, dbName).getSolve(solveID) ?: return null
        val solve = Solve(solveData)
        val cubeStateDataList = CubeStateDBService(context, dbName).getCubeStatesForSolve(solveID)
        val cubeStateList = cubeStateDataList.map { CubeState(it) }
        solve.solveStateSequence = cubeStateList.toMutableList()
        val pllData = PLLDBService(context, dbName).getPLLData(solveID)
        val ollData = OLLDBService(context, dbName).getOLLData(solveID)
        val f2lData = F2LDBService(context, dbName).getF2LData(solveID)
        val crossData = CrossDBService(context, dbName).getCrossData(solveID)
        return SolveAnalysisData(
            solveData,
            crossData,
            f2lData,
            ollData,
            pllData,
            solve.solveStateSequence
        )
    }

    fun getSolveAnalysisForAllSolves(): List<SolveAnalysisData> {
        TODO("Not implemented yet")
    }

    private fun getPLLData(solve: Solve): PLLData? {
        return SolutionPhaseDetection(
            solve,
            CubeStatePhaseDetection(CubeState.SOLVED_CUBE_STATE)
        ).getPLLData(context)
    }

    private fun getOLLData(solve: Solve): OLLData?{
        return SolutionPhaseDetection(
            solve,
            CubeStatePhaseDetection(CubeState.SOLVED_CUBE_STATE)
        ).getOLLData(context)
    }

    private fun getF2LData(solve: Solve): F2LData?{
        return SolutionPhaseDetection(
            solve,
            CubeStatePhaseDetection(CubeState.SOLVED_CUBE_STATE)
        ).getF2LData(context)
    }

    private fun getCrossData(solve: Solve): CrossData?{
        return SolutionPhaseDetection(
            solve,
            CubeStatePhaseDetection(CubeState.SOLVED_CUBE_STATE)
        ).getCrossData(context)
    }
}