package com.example.smartcubeapp.stats

import android.content.Context
import com.example.smartcubeapp.casedetection.olldetection.ollcase.PredefinedOLLCase
import com.example.smartcubeapp.casedetection.plldetection.pllcase.PredefinedPLLCase
import com.example.smartcubeapp.phasedetection.SolvePhase
import com.example.smartcubeapp.solvedatabase.SolveDB
import com.example.smartcubeapp.solvedatabase.SolvesDatabaseConstants
import java.io.FileOutputStream

class StatsService(private val context: Context, private val dbName: String = SolvesDatabaseConstants.SOLVE_DATABASE_NAME):
    SolveDB(context, dbName){

    init {
        if (!context.getDatabasePath(dbName).exists()
            && databaseName != SolvesDatabaseConstants.STATS_TESTS_DATABASE_NAME
        ) {
            copyDatabaseForStatsTests()
        }
    }

    fun averageTimeForPhaseInLastXSolves(x: Int, phase: SolvePhase): Double{
        TODO("Not implemented yet")
    }

    fun bestAverageTimeForPhaseInXSolves(x: Int, phase: SolvePhase): Double{
        TODO("Not implemented yet")
    }

    fun averageTimeForPLLCaseInLastXSolves(x: Int, case: PredefinedPLLCase): Double{
        TODO("Not implemented yet")
    }

    fun bestAverageTimeForPLLCaseInXSolves(x: Int, case: PredefinedPLLCase): Double{
        TODO("Not implemented yet")
    }

    fun averageTimeForOLLCaseInLastXSolves(x: Int, case: PredefinedOLLCase): Double{
        TODO("Not implemented yet")
    }

    fun bestAverageTimeForOLLCaseInXSolves(x: Int, case: PredefinedOLLCase): Double{
        TODO("Not implemented yet")
    }

    fun averageNumberOfMovesPerSolveInLastXSolves(x: Int): Double{
        TODO("Not implemented yet")
    }

    fun bestAverageNumberOfMovesPerSolveInXSolves(x: Int): Double{
        TODO("Not implemented yet")
    }

    fun averageNumberOfMovesForPhaseInLastXSolves(x: Int, phase: SolvePhase): Double{
        TODO("Not implemented yet")
    }

    fun bestAverageNumberOfMovesForPhaseInXSolves(x: Int, phase: SolvePhase): Double{
        TODO("Not implemented yet")
    }

    fun averageNumberOfMovesForPLLCaseInLastXSolves(x: Int, case: PredefinedPLLCase): Double{
        TODO("Not implemented yet")
    }

    fun bestAverageNumberOfMovesForPLLCaseInXSolves(x: Int, case: PredefinedPLLCase): Double{
        TODO("Not implemented yet")
    }

    fun averageNumberOfMovesForOLLCaseInLastXSolves(x: Int, case: PredefinedOLLCase): Double{
        TODO("Not implemented yet")
    }

    fun bestAverageNumberOfMovesForOLLCaseInXSolves(x: Int, case: PredefinedOLLCase): Double{
        TODO("Not implemented yet")
    }

    fun totalNumberOfMoves(): Int{
        TODO("Not implemented yet")
    }

    //TODO("Write more methods for stats not utilizing smart cube potential")

    private fun copyDatabaseForStatsTests() {
        val dbPath = context.getDatabasePath(dbName)
        if (!dbPath.exists()) {
            val inputStream = context.assets.open(dbName)
            val outputStream = FileOutputStream(dbPath)
            val buffer = ByteArray(1024)
            var length: Int
            while (inputStream.read(buffer).also { length = it } > 0) {
                outputStream.write(buffer, 0, length)
            }
            outputStream.flush()
            outputStream.close()
            inputStream.close()
        }
    }
}