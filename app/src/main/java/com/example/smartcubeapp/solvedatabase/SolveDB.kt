package com.example.smartcubeapp.solvedatabase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.smartcubeapp.casedetection.olldetection.ollcase.PredefinedOLLCase
import com.example.smartcubeapp.casedetection.plldetection.pllcase.PredefinedPLLCase
import com.example.smartcubeapp.dbAccesses

open class SolveDB(
    private val context: Context,
    private val dbName: String = SolvesDatabaseConstants.SOLVE_DATABASE_NAME
) :
    SQLiteOpenHelper(context, dbName, null, SolvesDatabaseConstants.DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        dbAccesses++
        createSolveTable(db)
        createCubeStateTable(db)
        createF2LTable(db)
        createOLLTable(db)
        createPLLTable(db)
        createCrossTable(db)
        createStatsTable(db)
        populateStatsTable(db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    private fun createSolveTable(db: SQLiteDatabase?) {
        db?.execSQL(SolvesDatabaseConstants.CREATE_SOLVE_TABLE)
    }

    private fun createCubeStateTable(db: SQLiteDatabase?) {
        db?.execSQL(SolvesDatabaseConstants.CREATE_CUBE_STATE_TABLE)
    }

    private fun createF2LTable(db: SQLiteDatabase?) {
        db?.execSQL(SolvesDatabaseConstants.CREATE_F2L_TABLE)
    }

    private fun createOLLTable(db: SQLiteDatabase?) {
        db?.execSQL(SolvesDatabaseConstants.CREATE_OLL_TABLE)
    }

    private fun createPLLTable(db: SQLiteDatabase?) {
        db?.execSQL(SolvesDatabaseConstants.CREATE_PLL_TABLE)
    }

    private fun createCrossTable(db: SQLiteDatabase?) {
        db?.execSQL(SolvesDatabaseConstants.CREATE_CROSS_TABLE)
    }

    private fun createStatsTable(db: SQLiteDatabase?) {
        db?.execSQL(SolvesDatabaseConstants.CREATE_STATS_TABLE)
    }

    private fun populateStatsTable(db: SQLiteDatabase?) {
        val statsNames = generateStatsNames()
        db?.beginTransaction()
        try{
            for (statName in statsNames){
                db?.execSQL(
                    "INSERT INTO ${SolvesDatabaseConstants.StatsTable.TABLE_NAME} " +
                            "(${SolvesDatabaseConstants.StatsTable.STATISTIC_NAME_COLUMN}, ${SolvesDatabaseConstants.StatsTable.STATISTIC_VALUE_COLUMN}) " +
                            "VALUES ('${statName}', '0.0')"
                )
            }
            db?.setTransactionSuccessful()
            db?.endTransaction()
        }
        catch(exception: Exception) {
            db?.endTransaction()
            println(exception.message)
        }
    }

    fun getDatabaseSizeInMegabytes(): Double {
        val dbFile = context.getDatabasePath(dbName)
        return if (dbFile.exists()) {
            val megabyte = 1024 * 1024
            dbFile.length() / megabyte.toDouble()
        } else {
            0.0
        }
    }

    private fun generateStatsNames(): List<String> {
        val statsNames = mutableListOf<String>()
        for (name in statsNamesList) {
            if (name.contains('X') && !name.contains('Y')) {
                for (numberOfSolves in numberOfSolvesValues) {
                    statsNames.add(name.replace("X", numberOfSolves.toString()))
                }
            } else if (name.contains('X') && name.contains('Y')) {
                if (name.contains("PLL")) {
                    for (pll in PredefinedPLLCase.values()) {
                        for (numberOfSolves in numberOfSolvesValues) {
                            var modifiedName = name.replace("Y", numberOfSolves.toString())
                            modifiedName = modifiedName.replace("X", pll.name)
                            statsNames.add(modifiedName)
                        }
                    }
                } else if (name.contains("OLL")) {
                    for (oll in PredefinedOLLCase.values()) {
                        for (numberOfSolves in numberOfSolvesValues) {
                            var modifiedName = name.replace("Y", numberOfSolves.toString())
                            modifiedName = modifiedName.replace("OLLX", oll.name)
                            statsNames.add(modifiedName)
                        }
                    }
                }
            } else {
                statsNames.add(name)
            }
        }
        return statsNames
    }

    companion object {
        val statsNamesList = listOf(
            "bestAverageTimeForCrossPhaseInXSolves",
            "bestAverageTimeForF2LPhaseInXSolves",
            "bestAverageTimeForOLLPhaseInXSolves",
            "bestAverageTimeForPLLPhaseInXSolves",
            "bestAverageTimeForSolveInXSolves",
            "bestAverageTimeForPLLXInYSolves",
            "bestAverageTimeForOLLXInYSolves",
            "bestAverageNumberOfMovesInXSolves",
            "bestAverageNumberOfMovesForCrossInXSolves",
            "bestAverageNumberOfMovesForF2LInXSolves",
            "bestAverageNumberOfMovesForOLLInXSolves",
            "bestAverageNumberOfMovesForPLLInXSolves",
            "bestAverageNumberOfMovesForPLLXInYSolves",
            "bestAverageNumberOfMovesForOLLXInYSolves",
            "totalNumberOfMoves"
        )

        val numberOfSolvesValues = listOf(3, 5, 12, 50, 100, 500, 1000)
    }
}