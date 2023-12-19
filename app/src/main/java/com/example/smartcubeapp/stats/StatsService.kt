package com.example.smartcubeapp.stats

import android.content.Context
import android.provider.BaseColumns
import com.example.smartcubeapp.casedetection.olldetection.ollcase.PredefinedOLLCase
import com.example.smartcubeapp.casedetection.plldetection.pllcase.PredefinedPLLCase
import com.example.smartcubeapp.phasedetection.SolvePhase
import com.example.smartcubeapp.solvedatabase.SolveDB
import com.example.smartcubeapp.solvedatabase.SolvesDatabaseConstants
import com.example.smartcubeapp.solvedatabase.dataclasses.SolveData
import kotlin.math.pow

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

    fun bestTime(): SolveData? {
        val db = this.readableDatabase
        print(databaseName)

        val projection = arrayOf("*")

        val orderBy = "${SolvesDatabaseConstants.SolveTable.DURATION_COLUMN} LIMIT 1"


        val cursor = db.query(
            SolvesDatabaseConstants.SolveTable.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            orderBy
        )

        with(cursor){
            if(moveToFirst()){
                val bestSolve = SolveData(
                    id = getLong(getColumnIndexOrThrow(BaseColumns._ID)),
                    solveDuration = getLong(getColumnIndexOrThrow(SolvesDatabaseConstants.SolveTable.DURATION_COLUMN)),
                    timestamp = getLong(getColumnIndexOrThrow(SolvesDatabaseConstants.SolveTable.TIMESTAMP_COLUMN)),
                    scrambledStateID = getLong(getColumnIndexOrThrow(SolvesDatabaseConstants.SolveTable.SCRAMBLED_STATE_ID_COLUMN)),
                    scramble = getString(getColumnIndexOrThrow(SolvesDatabaseConstants.SolveTable.SCRAMBLE_SEQUENCE_COLUMN)),
                    moveCount = getInt(getColumnIndexOrThrow(SolvesDatabaseConstants.SolveTable.MOVE_COUNT))
                )
                close()
                return bestSolve
            }
        }
        return null
    }

    fun worstTime(): SolveData{
        val db = this.readableDatabase

        val projection = arrayOf("*")

        val orderBy = "${SolvesDatabaseConstants.SolveTable.DURATION_COLUMN} DESC LIMIT 1"

        val cursor = db.query(
            SolvesDatabaseConstants.SolveTable.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            orderBy
        )

        with(cursor){
            moveToFirst()
            val worstSolve = SolveData(
                id = getLong(getColumnIndexOrThrow(BaseColumns._ID)),
                solveDuration = getLong(getColumnIndexOrThrow(SolvesDatabaseConstants.SolveTable.DURATION_COLUMN)),
                timestamp = getLong(getColumnIndexOrThrow(SolvesDatabaseConstants.SolveTable.TIMESTAMP_COLUMN)),
                scrambledStateID = getLong(getColumnIndexOrThrow(SolvesDatabaseConstants.SolveTable.SCRAMBLED_STATE_ID_COLUMN)),
                scramble = getString(getColumnIndexOrThrow(SolvesDatabaseConstants.SolveTable.SCRAMBLE_SEQUENCE_COLUMN)),
                moveCount = getInt(getColumnIndexOrThrow(SolvesDatabaseConstants.SolveTable.MOVE_COUNT))
            )
            close()
            return worstSolve
        }
    }

    fun totalNumberOfSolves(): Int{
        val db = this.readableDatabase

        val result = "total_solves"
        val projection = arrayOf("COUNT(*) as $result")

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
            val totalSolves = getInt(getColumnIndexOrThrow(result))
            close()
            return totalSolves
        }
    }

    fun totalSolvingTime(): Long{
        val db = this.readableDatabase

        val result = "total_time"
        val projection = arrayOf("SUM(${SolvesDatabaseConstants.SolveTable.DURATION_COLUMN}) as $result")

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
            val totalTime = getLong(getColumnIndexOrThrow(result))
            close()
            return totalTime
        }
    }

    fun standardDeviation(): Double {
        val db = this.readableDatabase

        val projection = arrayOf(SolvesDatabaseConstants.SolveTable.DURATION_COLUMN)
        val cursor = db.query(
            SolvesDatabaseConstants.SolveTable.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        val average = meanTime()

        val sumForStandardDeviation = cursor.use {
            var sum = 0.0
            while (it.moveToNext()) {
                val solveDuration =
                    it.getLong(it.getColumnIndexOrThrow(SolvesDatabaseConstants.SolveTable.DURATION_COLUMN))
                sum += (solveDuration.toDouble() - average).pow(2)
            }
            sum
        }

        return (sumForStandardDeviation / totalNumberOfSolves()).pow(0.5)
    }

    fun averageOf(numberOfSolves: Int): Double{
        val db = this.readableDatabase

        val result = "average_time"
        val projection = arrayOf("AVG(${SolvesDatabaseConstants.SolveTable.DURATION_COLUMN}) as $result")
        val table = "(SELECT ${SolvesDatabaseConstants.SolveTable.DURATION_COLUMN} " +
                "FROM ${SolvesDatabaseConstants.SolveTable.TABLE_NAME} " +
                "ORDER BY ${SolvesDatabaseConstants.SolveTable.TIMESTAMP_COLUMN} DESC LIMIT $numberOfSolves)"

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
            val averageTime = getDouble(getColumnIndexOrThrow(result))
            close()
            return averageTime
        }
    }

    fun bestAverageOf(numberOfSolves: Int): Double{
        if(numberOfSolves !in StatsDBConstants.numberOfSolvesValues){
            throw IllegalArgumentException("numberOfSolves must be one of the following numbers: ${StatsDBConstants.numberOfSolvesValues}")
        }

        val statsDB = StatsDB(context, statsDBName)
        val field = StatsDBConstants.BEST_AVERAGE_TIME_FOR_SOLVE_IN_X_SOLVES
            .replace("X", numberOfSolves.toString())

        val fieldValue = statsDB.getFieldValue(field)
        val currentValue = averageOf(numberOfSolves)
        if(fieldValue == 0.0 || currentValue < fieldValue){
            statsDB.updateFieldValue(field, currentValue.toString())
            return currentValue
        }
        return fieldValue
    }

    fun meanTime(): Double {
        val totalNumberOfSolves = totalNumberOfSolves()
        if(totalNumberOfSolves == 0){
            return 0.0
        }
        return totalSolvingTime().toDouble() / totalNumberOfSolves
    }

    fun meanMoveCount(): Double{
        val totalNumberOfSolves = totalNumberOfSolves()
        if(totalNumberOfSolves == 0){
            return 0.0
        }
        return totalNumberOfMoves().toDouble() / totalNumberOfSolves
    }
}