package com.example.smartcubeapp.elementdatabase

object ElementDatabaseConstants {

    const val PHASE_DATABASE_NAME = "PhaseElementOrientationDB.db"
    const val CASE_DATABASE_NAME = "CaseElementOrientationDB.db"
    const val TEST_DATABASE_NAME = "ElementOrientationDBTest"
    const val DATABASE_VERSION = 1

    const val CREATE_ELEMENT_ORIENTATION_TABLE =
        "CREATE TABLE IF NOT EXISTS ${ElementOrientationTable.TABLE_NAME} (" +
                "${ElementOrientationTable.SIDE_NAME_COLUMN} TEXT NOT NULL, " +
                "${ElementOrientationTable.PIECE_TYPE_COLUMN} INTEGER NOT NULL, " +
                "${ElementOrientationTable.PIECE_NUMBER_COLUMN} INTEGER NOT NULL, " +
                "${ElementOrientationTable.PIECE_POSITION_COLUMN} INTEGER NOT NULL, " +
                "${ElementOrientationTable.PIECE_ORIENTATION_COLUMN} INTEGER NOT NULL, " +
                "${ElementOrientationTable.SIDE_RELATIVE_ORIENTATION_COLUMN} INTEGER, " +
                "PRIMARY KEY (${ElementOrientationTable.SIDE_NAME_COLUMN}, " +
                "${ElementOrientationTable.PIECE_TYPE_COLUMN}, " +
                "${ElementOrientationTable.PIECE_NUMBER_COLUMN}, " +
                "${ElementOrientationTable.PIECE_POSITION_COLUMN}," +
                "${ElementOrientationTable.PIECE_ORIENTATION_COLUMN}))"

    object ElementOrientationTable {
        const val TABLE_NAME = "ElementOrientation"
        const val SIDE_NAME_COLUMN = "SideName"
        const val PIECE_TYPE_COLUMN = "PieceType"
        const val PIECE_NUMBER_COLUMN = "PieceNumber"
        const val PIECE_POSITION_COLUMN = "PiecePosition"
        const val PIECE_ORIENTATION_COLUMN = "PieceOrientation"
        const val SIDE_RELATIVE_ORIENTATION_COLUMN = "SideRelativeOrientation"
    }
}