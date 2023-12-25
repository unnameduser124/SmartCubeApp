package com.example.cube_database.solvedatabase.solvesDB.services

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import com.example.cube_database.solvedatabase.solvesDB.SolveDB
import com.example.cube_database.solvedatabase.solvesDB.SolvesDatabaseConstants
import com.example.cube_cube.solveDBDataClasses.PLLData
import com.example.cube_global.dbAccesses

class PLLDBService(
    context: Context,
    databaseName: String = SolvesDatabaseConstants.SOLVE_DATABASE_NAME
) : SolveDB(context, databaseName) {

    fun addPLLData(pllData: PLLData): Long {
        if (pllData.duration < 0) {
            throw IllegalArgumentException("Duration must be greater than or equal to 0")
        }
        if (pllData.moveCount < 0) {
            throw IllegalArgumentException("Move count must be greater than or equal to 0")
        }
        if (pllData.case < 0 || pllData.case > 22) {
            throw IllegalArgumentException("PLL case must be between 0 and 22, it was ${pllData.case}")
        }
        dbAccesses++

        val db = this.writableDatabase

        val contentValues = ContentValues().apply {
            put(SolvesDatabaseConstants.PLLTable.SOLVE_ID_COLUMN, pllData.solveID)
            put(SolvesDatabaseConstants.PLLTable.DURATION_COLUMN, pllData.duration)
            put(SolvesDatabaseConstants.PLLTable.MOVE_COUNT_COLUMN, pllData.moveCount)
            put(SolvesDatabaseConstants.PLLTable.START_CUBE_STATE_ID_COLUMN, pllData.startStateID)
            put(SolvesDatabaseConstants.PLLTable.END_CUBE_STATE_ID_COLUMN, pllData.endStateID)
            put(SolvesDatabaseConstants.PLLTable.CASE_COLUMN, pllData.case)
        }

        return db.insert(SolvesDatabaseConstants.PLLTable.TABLE_NAME, null, contentValues)
    }

    fun getPLLData(id: Long): PLLData? {
        dbAccesses++
        val db = this.readableDatabase

        val projection = arrayOf(
            SolvesDatabaseConstants.PLLTable.SOLVE_ID_COLUMN,
            SolvesDatabaseConstants.PLLTable.DURATION_COLUMN,
            SolvesDatabaseConstants.PLLTable.MOVE_COUNT_COLUMN,
            SolvesDatabaseConstants.PLLTable.START_CUBE_STATE_ID_COLUMN,
            SolvesDatabaseConstants.PLLTable.END_CUBE_STATE_ID_COLUMN,
            SolvesDatabaseConstants.PLLTable.CASE_COLUMN,
            BaseColumns._ID
        )

        val selection = "${BaseColumns._ID} = ?"
        val selectionArgs = arrayOf(id.toString())

        val cursor = db.query(
            SolvesDatabaseConstants.PLLTable.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        with(cursor) {
            if (moveToFirst()) {
                val solveID =
                    getLong(getColumnIndexOrThrow(com.example.cube_database.solvedatabase.solvesDB.SolvesDatabaseConstants.PLLTable.SOLVE_ID_COLUMN))
                val duration =
                    getLong(getColumnIndexOrThrow(com.example.cube_database.solvedatabase.solvesDB.SolvesDatabaseConstants.PLLTable.DURATION_COLUMN))
                val moveCount =
                    getInt(getColumnIndexOrThrow(com.example.cube_database.solvedatabase.solvesDB.SolvesDatabaseConstants.PLLTable.MOVE_COUNT_COLUMN))
                val startStateID =
                    getLong(getColumnIndexOrThrow(com.example.cube_database.solvedatabase.solvesDB.SolvesDatabaseConstants.PLLTable.START_CUBE_STATE_ID_COLUMN))
                val endStateID =
                    getLong(getColumnIndexOrThrow(com.example.cube_database.solvedatabase.solvesDB.SolvesDatabaseConstants.PLLTable.END_CUBE_STATE_ID_COLUMN))
                val case =
                    getInt(getColumnIndexOrThrow(com.example.cube_database.solvedatabase.solvesDB.SolvesDatabaseConstants.PLLTable.CASE_COLUMN))
                val retrievedID = getLong(getColumnIndexOrThrow(BaseColumns._ID))
                cursor.close()
                db.close()
                return PLLData(
                    solveID,
                    duration,
                    moveCount,
                    startStateID,
                    endStateID,
                    case,
                    retrievedID
                )
            }
            cursor.close()
            db.close()
            return null
        }
    }

    fun deletePLLData(id: Long) {
        dbAccesses++
        val db = this.writableDatabase

        val selection = "${BaseColumns._ID} = ?"
        val selectionArgs = arrayOf(id.toString())

        db.delete(SolvesDatabaseConstants.PLLTable.TABLE_NAME, selection, selectionArgs)
    }

    fun updatePLLData(pllData: PLLData, id: Long) {
        if (pllData.duration < 0) {
            throw IllegalArgumentException("Duration must be greater than or equal to 0")
        }
        if (pllData.moveCount < 0) {
            throw IllegalArgumentException("Move count must be greater than or equal to 0")
        }
        if (pllData.case < 0 || pllData.case > 56) {
            throw IllegalArgumentException("PLL case must be between 0 and 56")
        }
        dbAccesses++

        val db = this.writableDatabase

        val contentValues = ContentValues().apply {
            put(SolvesDatabaseConstants.PLLTable.SOLVE_ID_COLUMN, pllData.solveID)
            put(SolvesDatabaseConstants.PLLTable.DURATION_COLUMN, pllData.duration)
            put(SolvesDatabaseConstants.PLLTable.MOVE_COUNT_COLUMN, pllData.moveCount)
            put(SolvesDatabaseConstants.PLLTable.START_CUBE_STATE_ID_COLUMN, pllData.startStateID)
            put(SolvesDatabaseConstants.PLLTable.END_CUBE_STATE_ID_COLUMN, pllData.endStateID)
            put(SolvesDatabaseConstants.PLLTable.CASE_COLUMN, pllData.case)
        }

        val selection = "${BaseColumns._ID} = ?"
        val selectionArgs = arrayOf(id.toString())

        db.update(
            SolvesDatabaseConstants.PLLTable.TABLE_NAME,
            contentValues,
            selection,
            selectionArgs
        )
    }

    fun getPLLForSolve(solveID: Long): PLLData? {
        dbAccesses++
        val db = this.readableDatabase

        val projection = arrayOf(
            SolvesDatabaseConstants.PLLTable.DURATION_COLUMN,
            SolvesDatabaseConstants.PLLTable.MOVE_COUNT_COLUMN,
            SolvesDatabaseConstants.PLLTable.START_CUBE_STATE_ID_COLUMN,
            SolvesDatabaseConstants.PLLTable.END_CUBE_STATE_ID_COLUMN,
            SolvesDatabaseConstants.PLLTable.CASE_COLUMN,
            BaseColumns._ID
        )

        val selection = "${SolvesDatabaseConstants.PLLTable.SOLVE_ID_COLUMN} = ?"
        val selectionArgs = arrayOf(solveID.toString())

        val cursor = db.query(
            SolvesDatabaseConstants.PLLTable.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        with(cursor) {
            if (cursor.moveToFirst()) {
                val duration =
                    getLong(getColumnIndexOrThrow(com.example.cube_database.solvedatabase.solvesDB.SolvesDatabaseConstants.PLLTable.DURATION_COLUMN))
                val moveCount =
                    getInt(getColumnIndexOrThrow(com.example.cube_database.solvedatabase.solvesDB.SolvesDatabaseConstants.PLLTable.MOVE_COUNT_COLUMN))
                val startStateID =
                    getLong(getColumnIndexOrThrow(com.example.cube_database.solvedatabase.solvesDB.SolvesDatabaseConstants.PLLTable.START_CUBE_STATE_ID_COLUMN))
                val endStateID =
                    getLong(getColumnIndexOrThrow(com.example.cube_database.solvedatabase.solvesDB.SolvesDatabaseConstants.PLLTable.END_CUBE_STATE_ID_COLUMN))
                val case =
                    getInt(getColumnIndexOrThrow(com.example.cube_database.solvedatabase.solvesDB.SolvesDatabaseConstants.PLLTable.CASE_COLUMN))
                val retrievedID = getLong(getColumnIndexOrThrow(BaseColumns._ID))

                cursor.close()
                db.close()
                return PLLData(
                    solveID,
                    duration,
                    moveCount,
                    startStateID,
                    endStateID,
                    case,
                    retrievedID
                )
            }
            cursor.close()
            db.close()
            return null
        }
    }

    fun deletePLLDataForSolve(solveID: Long) {
        dbAccesses++
        val db = this.writableDatabase

        val selection = "${SolvesDatabaseConstants.PLLTable.SOLVE_ID_COLUMN} = ?"
        val selectionArgs = arrayOf(solveID.toString())

        db.delete(SolvesDatabaseConstants.PLLTable.TABLE_NAME, selection, selectionArgs)
    }
}