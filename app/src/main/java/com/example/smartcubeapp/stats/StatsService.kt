package com.example.smartcubeapp.stats

import android.content.Context
import android.text.TextUtils.replace
import com.example.smartcubeapp.casedetection.olldetection.ollcase.PredefinedOLLCase
import com.example.smartcubeapp.casedetection.plldetection.pllcase.PredefinedPLLCase
import com.example.smartcubeapp.cube.Solve
import com.example.smartcubeapp.phasedetection.SolvePhase
import com.example.smartcubeapp.solvedatabase.SolveDB
import com.example.smartcubeapp.solvedatabase.SolvesDatabaseConstants
import com.example.smartcubeapp.solvedatabase.dataclasses.SolveData

class StatsService(private val context: Context, dbName: String = SolvesDatabaseConstants.SOLVE_DATABASE_NAME):
    SolveDB(context, dbName){

    private val statsDBName = if(dbName == SolvesDatabaseConstants.SOLVE_DATABASE_NAME){
        StatsDBConstants.STATS_DATABASE_NAME
    } else{
        StatsDBConstants.TEST_DATABASE_NAME
    }

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

        if(x !in StatsDBConstants.numberOfSolvesValues){
            throw IllegalArgumentException("x must be one of the following numbers: ${StatsDBConstants.numberOfSolvesValues}")
        }

        val statsDB = StatsDB(context, statsDBName)
        val field = when(phase){
            SolvePhase.Cross -> {
                StatsDBConstants.BEST_AVERAGE_TIME_FOR_CROSS_PHASE_IN_X_SOLVES
            }
            SolvePhase.F2L -> {
                StatsDBConstants.BEST_AVERAGE_TIME_FOR_F2L_PHASE_IN_X_SOLVES
            }
            SolvePhase.OLL -> {
                StatsDBConstants.BEST_AVERAGE_TIME_FOR_OLL_PHASE_IN_X_SOLVES
            }
            SolvePhase.PLL -> {
                StatsDBConstants.BEST_AVERAGE_TIME_FOR_PLL_PHASE_IN_X_SOLVES
            }
            else -> {
                throw IllegalArgumentException("Phase must be one of the four phases")
            }
        }.replace("X", x.toString())

        val fieldValue = statsDB.getFieldValue(field)
        val currentValue = averageTimeForPhaseInLastXSolves(x, phase)
        if(fieldValue == 0.0 || currentValue < fieldValue){
            statsDB.updateFieldValue(field, currentValue.toString())
            return currentValue
        }
        return fieldValue
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
        if(x !in StatsDBConstants.numberOfSolvesValues){
            throw IllegalArgumentException("x must be one of the following numbers: ${StatsDBConstants.numberOfSolvesValues}")
        }

        val statsDB = StatsDB(context, statsDBName)
        val field = StatsDBConstants.BEST_AVERAGE_TIME_FOR_PLL_X_IN_Y_SOLVES
            .replace("Y", x.toString())
            .replace("X", case.name)

        val fieldValue = statsDB.getFieldValue(field)
        val currentValue = averageTimeForPLLCaseInLastXSolves(x, case)
        if(fieldValue == 0.0 || currentValue < fieldValue){
            statsDB.updateFieldValue(field, currentValue.toString())
            return currentValue
        }
        return fieldValue
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
        if(x !in StatsDBConstants.numberOfSolvesValues){
            throw IllegalArgumentException("x must be one of the following numbers: ${StatsDBConstants.numberOfSolvesValues}")
        }

        val statsDB = StatsDB(context, statsDBName)
        val field = StatsDBConstants.BEST_AVERAGE_TIME_FOR_OLL_X_IN_Y_SOLVES
            .replace("Y", x.toString())
            .replace("OLLX", case.name)

        val fieldValue = statsDB.getFieldValue(field)
        val currentValue = averageTimeForOLLCaseInLastXSolves(x, case)
        if(fieldValue == 0.0 || currentValue < fieldValue){
            statsDB.updateFieldValue(field, currentValue.toString())
            return currentValue
        }
        return fieldValue
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
        if(x !in StatsDBConstants.numberOfSolvesValues){
            throw IllegalArgumentException("x must be one of the following numbers: ${StatsDBConstants.numberOfSolvesValues}")
        }

        val statsDB = StatsDB(context, statsDBName)
        val field = StatsDBConstants.BEST_AVERAGE_NUMBER_OF_MOVES_IN_X_SOLVES
            .replace("X", x.toString())

        val fieldValue = statsDB.getFieldValue(field)
        val currentValue = averageNumberOfMovesPerSolveInLastXSolves(x)
        if(fieldValue == 0.0 || currentValue < fieldValue){
            statsDB.updateFieldValue(field, currentValue.toString())
            return currentValue
        }
        return fieldValue
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
        if(x !in StatsDBConstants.numberOfSolvesValues){
            throw IllegalArgumentException("x must be one of the following numbers: ${StatsDBConstants.numberOfSolvesValues}")
        }

        val statsDB = StatsDB(context, statsDBName)
        val field = when(phase){
            SolvePhase.Cross -> {
                StatsDBConstants.BEST_AVERAGE_NUMBER_OF_MOVES_FOR_CROSS_IN_X_SOLVES
            }
            SolvePhase.F2L -> {
                StatsDBConstants.BEST_AVERAGE_NUMBER_OF_MOVES_FOR_F2L_IN_X_SOLVES
            }
            SolvePhase.OLL -> {
                StatsDBConstants.BEST_AVERAGE_NUMBER_OF_MOVES_FOR_OLL_IN_X_SOLVES
            }
            SolvePhase.PLL -> {
                StatsDBConstants.BEST_AVERAGE_NUMBER_OF_MOVES_FOR_PLL_IN_X_SOLVES
            }
            else -> {
                throw IllegalArgumentException("Phase must be one of the four phases")
            }
        }.replace("X", x.toString())

        val fieldValue = statsDB.getFieldValue(field)
        val currentValue = averageNumberOfMovesForPhaseInLastXSolves(x, phase)
        if(fieldValue == 0.0 || currentValue < fieldValue){
            statsDB.updateFieldValue(field, currentValue.toString())
            return currentValue
        }
        return fieldValue
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
        if(x !in StatsDBConstants.numberOfSolvesValues){
            throw IllegalArgumentException("x must be one of the following numbers: ${StatsDBConstants.numberOfSolvesValues}")
        }

        val statsDB = StatsDB(context, statsDBName)
        val field = StatsDBConstants.BEST_AVERAGE_NUMBER_OF_MOVES_FOR_PLL_X_IN_Y_SOLVES
            .replace("Y", x.toString())
            .replace("X", case.name)

        val fieldValue = statsDB.getFieldValue(field)
        val currentValue = averageNumberOfMovesForPLLCaseInLastXSolves(x, case)
        if(fieldValue == 0.0 || currentValue < fieldValue){
            statsDB.updateFieldValue(field, currentValue.toString())
            return currentValue
        }
        return fieldValue
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
        if(x !in StatsDBConstants.numberOfSolvesValues){
            throw IllegalArgumentException("x must be one of the following numbers: ${StatsDBConstants.numberOfSolvesValues}")
        }

        val statsDB = StatsDB(context, statsDBName)
        val field = StatsDBConstants.BEST_AVERAGE_NUMBER_OF_MOVES_FOR_OLL_X_IN_Y_SOLVES
            .replace("Y", x.toString())
            .replace("OLLX", case.name)

        val fieldValue = statsDB.getFieldValue(field)
        val currentValue = averageNumberOfMovesForOLLCaseInLastXSolves(x, case)
        if(fieldValue == 0.0 || currentValue < fieldValue){
            statsDB.updateFieldValue(field, currentValue.toString())
            return currentValue
        }
        return fieldValue
    }

    fun totalNumberOfMoves(): Int{
        val db = this.readableDatabase

        val projection = arrayOf("SUM(${SolvesDatabaseConstants.SolveTable.MOVE_COUNT}) as total_moves")

        val cursor = db.query(
            SolvesDatabaseConstants.SolveTable.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        with(cursor){
            moveToFirst()
            val totalMoves = getInt(getColumnIndexOrThrow("total_moves"))
            close()
            return totalMoves
        }
    }

    fun bestTime(): SolveData {
        TODO("Not implemented yet")
    }

    fun worstTime(): SolveData{
        TODO("Not implemented yet")
    }

    fun totalNumberOfSolves(): Int{
        TODO("Not implemented yet")
    }

    fun totalSolvingTime(): Long{
        TODO("Not implemented yet")
    }

    fun standardDeviation(): Double{
        TODO("Not implemented yet")
    }

    fun averageOf(numberOfSolves: Int): Double{
        TODO("Not implemented yet")
    }

    fun bestAverageOf(numberOfSolves: Int): Double{
        TODO("Not implemented yet")
    }

    fun meanTime(): Double {
        TODO("Not implemented yet")
    }
}