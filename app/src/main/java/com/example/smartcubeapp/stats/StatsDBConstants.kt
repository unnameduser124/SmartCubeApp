package com.example.smartcubeapp.stats

object StatsDBConstants {
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

    const val STATS_DATABASE_NAME = "StatsDB.db"
    const val TEST_DATABASE_NAME = "StatsTestDB.db"
    const val DATABASE_VERSION = 1

    const val CREATE_STATS_TABLE = "CREATE TABLE IF NOT EXISTS ${StatsTable.TABLE_NAME} (" +
            "${StatsTable.STATISTIC_NAME_COLUMN} TEXT NOT NULL, " +
            "${StatsTable.STATISTIC_VALUE_COLUMN} TEXT NOT NULL)"

    object StatsTable{
        const val TABLE_NAME = "Stats"
        const val STATISTIC_NAME_COLUMN = "StatisticName"
        const val STATISTIC_VALUE_COLUMN = "StatisticValue"
    }
}