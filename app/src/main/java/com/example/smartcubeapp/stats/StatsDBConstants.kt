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

    const val BEST_AVERAGE_TIME_FOR_CROSS_PHASE_IN_X_SOLVES = "bestAverageTimeForCrossPhaseInXSolves"
    const val BEST_AVERAGE_TIME_FOR_F2L_PHASE_IN_X_SOLVES = "bestAverageTimeForF2LPhaseInXSolves"
    const val BEST_AVERAGE_TIME_FOR_OLL_PHASE_IN_X_SOLVES = "bestAverageTimeForOLLPhaseInXSolves"
    const val BEST_AVERAGE_TIME_FOR_PLL_PHASE_IN_X_SOLVES = "bestAverageTimeForPLLPhaseInXSolves"
    const val BEST_AVERAGE_TIME_FOR_SOLVE_IN_X_SOLVES = "bestAverageTimeForSolveInXSolves"
    const val BEST_AVERAGE_TIME_FOR_PLL_X_IN_Y_SOLVES = "bestAverageTimeForPLLXInYSolves"
    const val BEST_AVERAGE_TIME_FOR_OLL_X_IN_Y_SOLVES = "bestAverageTimeForOLLXInYSolves"
    const val BEST_AVERAGE_NUMBER_OF_MOVES_IN_X_SOLVES = "bestAverageNumberOfMovesInXSolves"
    const val BEST_AVERAGE_NUMBER_OF_MOVES_FOR_CROSS_IN_X_SOLVES = "bestAverageNumberOfMovesForCrossInXSolves"
    const val BEST_AVERAGE_NUMBER_OF_MOVES_FOR_F2L_IN_X_SOLVES = "bestAverageNumberOfMovesForF2LInXSolves"
    const val BEST_AVERAGE_NUMBER_OF_MOVES_FOR_OLL_IN_X_SOLVES = "bestAverageNumberOfMovesForOLLInXSolves"
    const val BEST_AVERAGE_NUMBER_OF_MOVES_FOR_PLL_IN_X_SOLVES = "bestAverageNumberOfMovesForPLLInXSolves"
    const val BEST_AVERAGE_NUMBER_OF_MOVES_FOR_PLL_X_IN_Y_SOLVES = "bestAverageNumberOfMovesForPLLXInYSolves"
    const val BEST_AVERAGE_NUMBER_OF_MOVES_FOR_OLL_X_IN_Y_SOLVES = "bestAverageNumberOfMovesForOLLXInYSolves"
    const val TOTAL_NUMBER_OF_MOVES = "totalNumberOfMoves"

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