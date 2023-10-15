package com.example.smartcubeapp.solvedatabase.services

import android.content.Context
import com.example.smartcubeapp.cube.CubeState
import com.example.smartcubeapp.cube.Solve
import com.example.smartcubeapp.cube.SolveStatus
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

class SolveAnalysisDBService(val context: Context, private val dbName: String = SolvesDatabaseConstants.SOLVE_DATABASE_NAME) :
    SolveDB(context, dbName) {

    fun saveSolveWithAnalysis(solve: Solve): SolveAnalysisData{
        solveValidation(solve)

        solve.calculateTimeFromStateSequence()

        val solveData = SolveData(solve)
        solveData.id = SolveDBService(context, dbName).addSolve(solveData)
        solve.id = solveData.id

        saveSolveSequence(solve)

        val pllData = getPLLData(solve)
        val ollData = getOLLData(solve)
        val f2lData = getF2LData(solve)
        val crossData = getCrossData(solve)

        savePhaseData(pllData, ollData, f2lData, crossData)

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
        SolveDBService(context, dbName).deleteSolve(solveID)
        CubeStateDBService(context, dbName).deleteCubeStatesForSolve(solveID)
        PLLDBService(context, dbName).deletePLLDataForSolve(solveID)
        OLLDBService(context, dbName).deleteOLLDataForSolve(solveID)
        F2LDBService(context, dbName).deleteF2LDataForSolve(solveID)
        CrossDBService(context, dbName).deleteCrossDataForSolve(solveID)
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
        val solveIDs = SolveDBService(context, dbName).getAllSolveIDs()
        val solveAnalysisDataList = mutableListOf<SolveAnalysisData>()
        solveIDs.forEach { id ->
            val solveAnalysisData = getSolveAnalysisForSolve(id)
            if(solveAnalysisData != null){
                solveAnalysisDataList.add(solveAnalysisData)
            }
        }
        return solveAnalysisDataList
    }

    fun getPLLData(solve: Solve): PLLData? {
        return SolutionPhaseDetection(
            solve,
            CubeStatePhaseDetection(CubeState.SOLVED_CUBE_STATE)
        ).getPLLData(context)
    }

    fun getOLLData(solve: Solve): OLLData?{
        return SolutionPhaseDetection(
            solve,
            CubeStatePhaseDetection(CubeState.SOLVED_CUBE_STATE)
        ).getOLLData(context)
    }

    fun getF2LData(solve: Solve): F2LData?{
        return SolutionPhaseDetection(
            solve,
            CubeStatePhaseDetection(CubeState.SOLVED_CUBE_STATE)
        ).getF2LData(context)
    }

    fun getCrossData(solve: Solve): CrossData?{
        return SolutionPhaseDetection(
            solve,
            CubeStatePhaseDetection(CubeState.SOLVED_CUBE_STATE)
        ).getCrossData(context)
    }

    private fun solveValidation(solve: Solve){
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
        if(solve.solveStatus!= SolveStatus.Solved){
            throw IllegalArgumentException("Solve must be completed before saving")
        }
    }

    fun saveSolveSequence(solve: Solve){
        val solveData = SolveData(solve)
        val stateDB = CubeStateDBService(context, dbName)
        val solveDB = SolveDBService(context, dbName)

        val solveSequenceDataList = solve.solveStateSequence.mapIndexed { index, cubeState ->
            cubeState.solveID = solveData.id
            CubeStateData(cubeState, index)
        }

        stateDB.addCubeStateList(solveSequenceDataList)
        stateDB.close()
        solveDB.updateSolve(SolveData(solve), solve.id)
        solveDB.close()

        solveSequenceDataList.forEachIndexed{ index, cubeStateData ->
            solve.solveStateSequence[index].id = cubeStateData.id
        }
    }

    fun savePhaseData(pllData: PLLData?, ollData: OLLData?, f2lData: F2LData?, crossData: CrossData?){

        if(pllData!=null){
            val pllService = PLLDBService(context, dbName)
            pllData.id = pllService.addPLLData(pllData)
            pllService.close()
        }
        if(ollData!=null){
            val ollService = OLLDBService(context, dbName)
            ollData.id = ollService.addOLLData(ollData)
            ollService.close()
        }
        if(f2lData!=null){
            val f2lService = F2LDBService(context, dbName)
            f2lData.id = f2lService.addF2LData(f2lData)
            f2lService.close()
        }
        if(crossData!=null){
            val crossService = CrossDBService(context, dbName)
            crossData.id = crossService.addCrossData(crossData)
            crossService.close()
        }
    }

}