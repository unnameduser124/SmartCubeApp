package com.example.smartcubeapp.solvedatabase.services

import android.content.Context
import com.example.smartcubeapp.cube.Solve
import com.example.smartcubeapp.solvedatabase.SolveDB
import com.example.smartcubeapp.solvedatabase.dataclasses.SolveAnalysisData

class SolveAnalysisDBService(context: Context, dbName: String) :
    SolveDB(context, dbName) {

    fun saveSolveWithAnalysis(solve: Solve): Long{
        TODO("Not implemented yet")
    }
    fun deleteSolveWithAnalysisData(solveID: Long){
        TODO("Not implemented yet")
    }
    fun getSolveAnalysisForSolve(solveID: Long): SolveAnalysisData? {
        TODO("Not implemented yet")
    }

    fun getSolveAnalysisForAllSolves(): List<SolveAnalysisData> {
        TODO("Not implemented yet")
    }
}