package com.example.smartcubeapp.solvedatabase

import android.provider.BaseColumns

object SolvesDatabaseConstants {

    const val SOLVE_DATABASE_NAME = "SolvesDB.db"
    const val TEST_DATABASE_NAME = "SolvesDBTest.db"
    const val DATABASE_VERSION = 1

    const val CREATE_SOLVE_TABLE =
        "CREATE TABLE IF NOT EXISTS ${SolveTable.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${SolveTable.DURATION_COLUMN} INTEGER NOT NULL, " +
                "${SolveTable.TIMESTAMP_COLUMN} INTEGER NOT NULL, " +
                "${SolveTable.SCRAMBLED_STATE_ID_COLUMN} INTEGER NOT NULL, " +
                "${SolveTable.SCRAMBLE_SEQUENCE_COLUMN} TEXT NOT NULL, " +
                "FOREIGN KEY(${SolveTable.SCRAMBLED_STATE_ID_COLUMN}) REFERENCES ${CubeStateTable.TABLE_NAME}(${BaseColumns._ID}))"

    const val CREATE_CUBE_STATE_TABLE =
        "CREATE TABLE IF NOT EXISTS ${CubeStateTable.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${CubeStateTable.TIMESTAMP_COLUMN} INTEGER NOT NULL, " +
                "${CubeStateTable.SOLVE_ID_COLUMN} INTEGER NOT NULL, " +
                "${CubeStateTable.LAST_MOVE_COLUMN} TEXT NOT NULL, " +
                "${CubeStateTable.MOVE_INDEX_COLUMN} INTEGER NOT NULL, " +
                "${CubeStateTable.CORNER_POSITIONS_COLUMN} TEXT NOT NULL, " +
                "${CubeStateTable.CORNER_ORIENTATIONS_COLUMN} TEXT NOT NULL, " +
                "${CubeStateTable.EDGE_POSITIONS_COLUMN} TEXT NOT NULL, " +
                "${CubeStateTable.EDGE_ORIENTATIONS_COLUMN} TEXT NOT NULL, " +
                "FOREIGN KEY(${CubeStateTable.SOLVE_ID_COLUMN}) REFERENCES ${SolveTable.TABLE_NAME}(${BaseColumns._ID}))"

    const val CREATE_F2L_TABLE =
        "CREATE TABLE IF NOT EXISTS ${F2LTable.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${F2LTable.SOLVE_ID_COLUMN} INTEGER NOT NULL, " +
                "${F2LTable.DURATION_COLUMN} INTEGER NOT NULL, " +
                "${F2LTable.START_CUBE_STATE_ID_COLUMN} INTEGER NOT NULL, " +
                "${F2LTable.END_CUBE_STATE_ID_COLUMN} INTEGER NOT NULL, " +
                "${F2LTable.MOVE_COUNT_COLUMN} INTEGER NOT NULL, " +
                "FOREIGN KEY(${F2LTable.SOLVE_ID_COLUMN}) REFERENCES ${SolveTable.TABLE_NAME}(${BaseColumns._ID}), " +
                "FOREIGN KEY(${F2LTable.START_CUBE_STATE_ID_COLUMN}) REFERENCES ${CubeStateTable.TABLE_NAME}(${BaseColumns._ID}), " +
                "FOREIGN KEY(${F2LTable.END_CUBE_STATE_ID_COLUMN}) REFERENCES ${CubeStateTable.TABLE_NAME}(${BaseColumns._ID}))"

    const val CREATE_OLL_TABLE =
        "CREATE TABLE IF NOT EXISTS ${OLLTable.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${OLLTable.SOLVE_ID_COLUMN} INTEGER NOT NULL, " +
                "${OLLTable.DURATION_COLUMN} INTEGER NOT NULL, " +
                "${OLLTable.START_CUBE_STATE_ID_COLUMN} INTEGER NOT NULL, " +
                "${OLLTable.END_CUBE_STATE_ID_COLUMN} INTEGER NOT NULL, " +
                "${OLLTable.MOVE_COUNT_COLUMN} INTEGER NOT NULL, " +
                "${OLLTable.CASE_COLUMN} INTEGER NOT NULL, " +
                "FOREIGN KEY(${OLLTable.SOLVE_ID_COLUMN}) REFERENCES ${SolveTable.TABLE_NAME}(${BaseColumns._ID}), " +
                "FOREIGN KEY(${OLLTable.START_CUBE_STATE_ID_COLUMN}) REFERENCES ${CubeStateTable.TABLE_NAME}(${BaseColumns._ID}), " +
                "FOREIGN KEY(${OLLTable.END_CUBE_STATE_ID_COLUMN}) REFERENCES ${CubeStateTable.TABLE_NAME}(${BaseColumns._ID}))"

    const val CREATE_PLL_TABLE =
        "CREATE TABLE IF NOT EXISTS ${PLLTable.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${PLLTable.SOLVE_ID_COLUMN} INTEGER NOT NULL, " +
                "${PLLTable.DURATION_COLUMN} INTEGER NOT NULL, " +
                "${PLLTable.START_CUBE_STATE_ID_COLUMN} INTEGER NOT NULL, " +
                "${PLLTable.END_CUBE_STATE_ID_COLUMN} INTEGER NOT NULL, " +
                "${PLLTable.MOVE_COUNT_COLUMN} INTEGER NOT NULL, " +
                "${PLLTable.CASE_COLUMN} INTEGER NOT NULL, " +
                "FOREIGN KEY(${PLLTable.SOLVE_ID_COLUMN}) REFERENCES ${SolveTable.TABLE_NAME}(${BaseColumns._ID}), " +
                "FOREIGN KEY(${PLLTable.START_CUBE_STATE_ID_COLUMN}) REFERENCES ${CubeStateTable.TABLE_NAME}(${BaseColumns._ID}), " +
                "FOREIGN KEY(${PLLTable.END_CUBE_STATE_ID_COLUMN}) REFERENCES ${CubeStateTable.TABLE_NAME}(${BaseColumns._ID}))"

    const val CREATE_CROSS_TABLE = "CREATE TABLE IF NOT EXISTS ${CrossTable.TABLE_NAME} (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "${CrossTable.SOLVE_ID_COLUMN} INTEGER NOT NULL, " +
            "${CrossTable.DURATION_COLUMN} INTEGER NOT NULL, " +
            "${CrossTable.START_CUBE_STATE_ID_COLUMN} INTEGER NOT NULL, " +
            "${CrossTable.END_CUBE_STATE_ID_COLUMN} INTEGER NOT NULL, " +
            "${CrossTable.MOVE_COUNT_COLUMN} INTEGER NOT NULL, " +
            "FOREIGN KEY(${CrossTable.SOLVE_ID_COLUMN}) REFERENCES ${SolveTable.TABLE_NAME}(${BaseColumns._ID}), " +
            "FOREIGN KEY(${CrossTable.START_CUBE_STATE_ID_COLUMN}) REFERENCES ${CubeStateTable.TABLE_NAME}(${BaseColumns._ID}), " +
            "FOREIGN KEY(${CrossTable.END_CUBE_STATE_ID_COLUMN}) REFERENCES ${CubeStateTable.TABLE_NAME}(${BaseColumns._ID}))"

    object SolveTable {
        const val TABLE_NAME = "Solve"
        const val DURATION_COLUMN = "Duration"
        const val TIMESTAMP_COLUMN = "Timestamp"
        const val SCRAMBLED_STATE_ID_COLUMN = "ScrambledStateId"
        const val SCRAMBLE_SEQUENCE_COLUMN = "ScrambleSequence"
    }

    object CubeStateTable {
        const val TABLE_NAME = "CubeState"
        const val TIMESTAMP_COLUMN = "Timestamp"
        const val SOLVE_ID_COLUMN = "SolveId"
        const val LAST_MOVE_COLUMN = "LastMove"
        const val MOVE_INDEX_COLUMN = "MoveIndex"
        const val CORNER_POSITIONS_COLUMN = "CornerPositions"
        const val CORNER_ORIENTATIONS_COLUMN = "CornerOrientations"
        const val EDGE_POSITIONS_COLUMN = "EdgePositions"
        const val EDGE_ORIENTATIONS_COLUMN = "EdgeOrientations"
    }

    object F2LTable {
        const val TABLE_NAME = "F2L"
        const val SOLVE_ID_COLUMN = "SolveId"
        const val DURATION_COLUMN = "Duration"
        const val START_CUBE_STATE_ID_COLUMN = "StartCubeStateId"
        const val END_CUBE_STATE_ID_COLUMN = "EndCubeStateId"
        const val MOVE_COUNT_COLUMN = "MoveCount"
    }

    object OLLTable {
        const val TABLE_NAME = "OLL"
        const val SOLVE_ID_COLUMN = "SolveId"
        const val DURATION_COLUMN = "Duration"
        const val START_CUBE_STATE_ID_COLUMN = "StartCubeStateId"
        const val END_CUBE_STATE_ID_COLUMN = "EndCubeStateId"
        const val MOVE_COUNT_COLUMN = "MoveCount"
        const val CASE_COLUMN = "OLLCase"
    }

    object PLLTable {
        const val TABLE_NAME = "PLL"
        const val SOLVE_ID_COLUMN = "SolveId"
        const val DURATION_COLUMN = "Duration"
        const val START_CUBE_STATE_ID_COLUMN = "StartCubeStateId"
        const val END_CUBE_STATE_ID_COLUMN = "EndCubeStateId"
        const val MOVE_COUNT_COLUMN = "MoveCount"
        const val CASE_COLUMN = "PLLCase"
    }

    object CrossTable{
        const val TABLE_NAME = "Cross"
        const val SOLVE_ID_COLUMN = "SolveId"
        const val DURATION_COLUMN = "Duration"
        const val START_CUBE_STATE_ID_COLUMN = "StartCubeStateId"
        const val END_CUBE_STATE_ID_COLUMN = "EndCubeStateId"
        const val MOVE_COUNT_COLUMN = "MoveCount"
    }
}