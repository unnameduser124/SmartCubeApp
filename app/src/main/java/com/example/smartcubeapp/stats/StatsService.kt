package com.example.smartcubeapp.stats

import android.content.Context
import com.example.smartcubeapp.casedetection.olldetection.ollcase.PredefinedOLLCase
import com.example.smartcubeapp.casedetection.plldetection.pllcase.PredefinedPLLCase
import com.example.smartcubeapp.phasedetection.SolvePhase
import com.example.smartcubeapp.solvedatabase.SolveDB
import com.example.smartcubeapp.solvedatabase.SolvesDatabaseConstants

class StatsService(context: Context, dbName: String = SolvesDatabaseConstants.SOLVE_DATABASE_NAME):
    SolveDB(context, dbName){

    fun averageTimeForPhaseInLastXSolves(x: Int, phase: SolvePhase): Double{
        val phaseTable = when (phase) {
            SolvePhase.Cross -> {
                SolvesDatabaseConstants.CrossTable
            }
            SolvePhase.F2L -> {
                SolvesDatabaseConstants.F2LTable
            }
            SolvePhase.OLL -> {
                SolvesDatabaseConstants.OLLTable
            }
            SolvePhase.PLL -> {
                SolvesDatabaseConstants.PLLTable
            }
            else -> {
                throw IllegalArgumentException("Phase must be one of the four phases")
            }
        }

        val db = this.readableDatabase

        val resultColumn = "average_time"
        val projection = arrayOf("AVG(${phaseTable.DURATION_COLUMN}) as $resultColumn")
        val table = "(SELECT ${phaseTable.DURATION_COLUMN} " +
                "FROM ${phaseTable.TABLE_NAME} " +
                "ORDER BY ${phaseTable.SOLVE_ID_COLUMN} DESC LIMIT $x)"

        val cursor = db.query(
            table,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        with(cursor){
            moveToFirst()
            val averageTime = getDouble(getColumnIndexOrThrow(resultColumn))
            close()
            return averageTime
        }
    }

    fun bestAverageTimeForPhaseInXSolves(x: Int, phase: SolvePhase): Double{
        TODO("Not implemented yet")
    }

    fun averageTimeForPLLCaseInLastXSolves(x: Int, case: PredefinedPLLCase): Double{
        val db = this.readableDatabase

        val projection = arrayOf("AVG(${SolvesDatabaseConstants.PLLTable.DURATION_COLUMN}) as average_time")

        val table = "(SELECT ${SolvesDatabaseConstants.PLLTable.DURATION_COLUMN} " +
                "FROM ${SolvesDatabaseConstants.PLLTable.TABLE_NAME} " +
                "WHERE ${SolvesDatabaseConstants.PLLTable.CASE_COLUMN} = ${case.ordinal} " +
                "ORDER BY ${SolvesDatabaseConstants.PLLTable.SOLVE_ID_COLUMN} DESC LIMIT $x)"

        val cursor = db.query(
            table,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        with(cursor){
            moveToFirst()
            val averageTime = getDouble(getColumnIndexOrThrow("average_time"))
            close()
            return averageTime
        }
    }

    fun bestAverageTimeForPLLCaseInXSolves(x: Int, case: PredefinedPLLCase): Double{
        TODO("Not implemented yet")
    }

    fun averageTimeForOLLCaseInLastXSolves(x: Int, case: PredefinedOLLCase): Double{
        val db = this.readableDatabase

        val projection = arrayOf("AVG(${SolvesDatabaseConstants.OLLTable.DURATION_COLUMN}) as average_time")

        val table = "(SELECT ${SolvesDatabaseConstants.OLLTable.DURATION_COLUMN} " +
                "FROM ${SolvesDatabaseConstants.OLLTable.TABLE_NAME} " +
                "WHERE ${SolvesDatabaseConstants.OLLTable.CASE_COLUMN} = ${case.ordinal} " +
                "ORDER BY ${SolvesDatabaseConstants.OLLTable.SOLVE_ID_COLUMN} DESC LIMIT $x)"

        val cursor = db.query(
            table,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        with(cursor){
            moveToFirst()
            val averageTime = getDouble(getColumnIndexOrThrow("average_time"))
            close()
            return averageTime
        }
    }

    fun bestAverageTimeForOLLCaseInXSolves(x: Int, case: PredefinedOLLCase): Double{
        TODO("Not implemented yet")
    }

    fun averageNumberOfMovesPerSolveInLastXSolves(x: Int): Double{
        val db = this.readableDatabase

        val result = "average_moves"
        val projection = arrayOf("AVG(${SolvesDatabaseConstants.SolveTable.MOVE_COUNT}) as $result")
        val table = "(SELECT ${SolvesDatabaseConstants.SolveTable.MOVE_COUNT} " +
                "FROM ${SolvesDatabaseConstants.SolveTable.TABLE_NAME} " +
                "ORDER BY ${SolvesDatabaseConstants.SolveTable.TIMESTAMP_COLUMN} DESC LIMIT $x)"

        val cursor = db.query(
            table,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        with(cursor){
            moveToFirst()
            val averageMoves = getDouble(getColumnIndexOrThrow(result))
            close()
            return averageMoves
        }
    }

    fun bestAverageNumberOfMovesPerSolveInXSolves(x: Int): Double{
        TODO("Not implemented yet")
    }

    fun averageNumberOfMovesForPhaseInLastXSolves(x: Int, phase: SolvePhase): Double{
        val db = this.readableDatabase

        val phaseTable = when (phase) {
            SolvePhase.Cross -> {
                SolvesDatabaseConstants.CrossTable
            }
            SolvePhase.F2L -> {
                SolvesDatabaseConstants.F2LTable
            }
            SolvePhase.OLL -> {
                SolvesDatabaseConstants.OLLTable
            }
            SolvePhase.PLL -> {
                SolvesDatabaseConstants.PLLTable
            }
            else -> {
                throw IllegalArgumentException("Phase must be one of the four phases")
            }
        }

        val result = "average_moves"
        val projection = arrayOf("AVG(${phaseTable.MOVE_COUNT_COLUMN}) as $result")

        val table = "(SELECT ${phaseTable.MOVE_COUNT_COLUMN} " +
                "FROM ${phaseTable.TABLE_NAME} " +
                "ORDER BY ${phaseTable.SOLVE_ID_COLUMN} DESC LIMIT $x)"

        val cursor = db.query(
            table,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        with(cursor){
            moveToFirst()
            val averageMoves = getDouble(getColumnIndexOrThrow(result))
            close()
            return averageMoves
        }
    }

    fun bestAverageNumberOfMovesForPhaseInXSolves(x: Int, phase: SolvePhase): Double{
        TODO("Not implemented yet")
    }

    fun averageNumberOfMovesForPLLCaseInLastXSolves(x: Int, case: PredefinedPLLCase): Double{
        val db = this.readableDatabase

        val result = "average_moves"
        val projection = arrayOf("AVG(${SolvesDatabaseConstants.PLLTable.MOVE_COUNT_COLUMN}) as $result")

        val table = "(SELECT ${SolvesDatabaseConstants.PLLTable.MOVE_COUNT_COLUMN} " +
                "FROM ${SolvesDatabaseConstants.PLLTable.TABLE_NAME} " +
                "WHERE ${SolvesDatabaseConstants.PLLTable.CASE_COLUMN} = ${case.ordinal} " +
                "ORDER BY ${SolvesDatabaseConstants.PLLTable.SOLVE_ID_COLUMN} DESC LIMIT $x)"

        val cursor = db.query(
            table,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        with(cursor){
            moveToFirst()
            val averageMoves = getDouble(getColumnIndexOrThrow(result))
            close()
            return averageMoves
        }

    }

    fun bestAverageNumberOfMovesForPLLCaseInXSolves(x: Int, case: PredefinedPLLCase): Double{
        TODO("Not implemented yet")
    }

    fun averageNumberOfMovesForOLLCaseInLastXSolves(x: Int, case: PredefinedOLLCase): Double{
        val db = this.readableDatabase

        val result = "average_moves"
        val projection = arrayOf("AVG(${SolvesDatabaseConstants.OLLTable.MOVE_COUNT_COLUMN}) as $result")

        val table = "(SELECT ${SolvesDatabaseConstants.OLLTable.MOVE_COUNT_COLUMN} " +
                "FROM ${SolvesDatabaseConstants.OLLTable.TABLE_NAME} " +
                "WHERE ${SolvesDatabaseConstants.OLLTable.CASE_COLUMN} = ${case.ordinal} " +
                "ORDER BY ${SolvesDatabaseConstants.OLLTable.SOLVE_ID_COLUMN} DESC LIMIT $x)"

        val cursor = db.query(
            table,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        with(cursor){
            moveToFirst()
            val averageMoves = getDouble(getColumnIndexOrThrow(result))
            close()
            return averageMoves
        }
    }

    fun bestAverageNumberOfMovesForOLLCaseInXSolves(x: Int, case: PredefinedOLLCase): Double{
        TODO("Not implemented yet")
    }

    fun totalNumberOfMoves(): Int{
        TODO("Not implemented yet")
    }

    //TODO("Write more methods for stats not utilizing smart cube potential")

}