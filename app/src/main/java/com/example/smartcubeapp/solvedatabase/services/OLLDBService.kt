package com.example.smartcubeapp.solvedatabase.services

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import com.example.smartcubeapp.cube.Solve
import com.example.smartcubeapp.dbAccesses
import com.example.smartcubeapp.solvedatabase.SolveDB
import com.example.smartcubeapp.solvedatabase.SolvesDatabaseConstants
import com.example.smartcubeapp.solvedatabase.dataclasses.OLLData

class OLLDBService(context: Context, databaseName: String = SolvesDatabaseConstants.SOLVE_DATABASE_NAME) : SolveDB(context, databaseName) {

    fun addOLLData(ollData: OLLData): Long {
        if (ollData.duration < 0) {
            throw IllegalArgumentException("Duration must be greater than or equal to 0")
        }
        if (ollData.moveCount < 0) {
            throw IllegalArgumentException("Move count must be greater than or equal to 0")
        }
        if (ollData.case < 0 || ollData.case > 56) {
            throw IllegalArgumentException("OLL case must be between 0 and 56")
        }
        dbAccesses++

        val db = this.writableDatabase

        val contentValues = ContentValues().apply {
            put(SolvesDatabaseConstants.OLLTable.SOLVE_ID_COLUMN, ollData.solveID)
            put(SolvesDatabaseConstants.OLLTable.DURATION_COLUMN, ollData.duration)
            put(SolvesDatabaseConstants.OLLTable.MOVE_COUNT_COLUMN, ollData.moveCount)
            put(SolvesDatabaseConstants.OLLTable.START_CUBE_STATE_ID_COLUMN, ollData.startStateID)
            put(SolvesDatabaseConstants.OLLTable.END_CUBE_STATE_ID_COLUMN, ollData.endStateID)
            put(SolvesDatabaseConstants.OLLTable.CASE_COLUMN, ollData.case)
        }

        return db.insert(SolvesDatabaseConstants.OLLTable.TABLE_NAME, null, contentValues)
    }

    fun getOLLData(id: Long): OLLData? {
        dbAccesses++
        val db = this.readableDatabase

        val projection = arrayOf(
            SolvesDatabaseConstants.OLLTable.SOLVE_ID_COLUMN,
            SolvesDatabaseConstants.OLLTable.DURATION_COLUMN,
            SolvesDatabaseConstants.OLLTable.MOVE_COUNT_COLUMN,
            SolvesDatabaseConstants.OLLTable.START_CUBE_STATE_ID_COLUMN,
            SolvesDatabaseConstants.OLLTable.END_CUBE_STATE_ID_COLUMN,
            SolvesDatabaseConstants.OLLTable.CASE_COLUMN,
            BaseColumns._ID
        )

        val selection = "${BaseColumns._ID} = ?"
        val selectionArgs = arrayOf(id.toString())

        val cursor = db.query(
            SolvesDatabaseConstants.OLLTable.TABLE_NAME,
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
                    getLong(getColumnIndexOrThrow(SolvesDatabaseConstants.OLLTable.SOLVE_ID_COLUMN))
                val duration =
                    getLong(getColumnIndexOrThrow(SolvesDatabaseConstants.OLLTable.DURATION_COLUMN))
                val moveCount =
                    getInt(getColumnIndexOrThrow(SolvesDatabaseConstants.OLLTable.MOVE_COUNT_COLUMN))
                val startStateID =
                    getLong(getColumnIndexOrThrow(SolvesDatabaseConstants.OLLTable.START_CUBE_STATE_ID_COLUMN))
                val endStateID =
                    getLong(getColumnIndexOrThrow(SolvesDatabaseConstants.OLLTable.END_CUBE_STATE_ID_COLUMN))
                val case =
                    getInt(getColumnIndexOrThrow(SolvesDatabaseConstants.OLLTable.CASE_COLUMN))
                val retrievedID = getLong(getColumnIndexOrThrow(BaseColumns._ID))

                cursor.close()
                db.close()
                return OLLData(
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

    fun deleteOLLData(id: Long) {
        dbAccesses++
        val db = this.writableDatabase

        val selection = "${BaseColumns._ID} = ?"
        val selectionArgs = arrayOf(id.toString())

        db.delete(SolvesDatabaseConstants.OLLTable.TABLE_NAME, selection, selectionArgs)
    }

    fun updateOLLData(ollData: OLLData, id: Long) {
        if (ollData.duration < 0) {
            throw IllegalArgumentException("Duration must be greater than or equal to 0")
        }
        if (ollData.moveCount < 0) {
            throw IllegalArgumentException("Move count must be greater than or equal to 0")
        }
        if (ollData.case < 0 || ollData.case > 56) {
            throw IllegalArgumentException("OLL case must be between 0 and 56")
        }
        dbAccesses++

        val db = this.writableDatabase

        val contentValues = ContentValues().apply {
            put(SolvesDatabaseConstants.OLLTable.SOLVE_ID_COLUMN, ollData.solveID)
            put(SolvesDatabaseConstants.OLLTable.DURATION_COLUMN, ollData.duration)
            put(SolvesDatabaseConstants.OLLTable.MOVE_COUNT_COLUMN, ollData.moveCount)
            put(SolvesDatabaseConstants.OLLTable.START_CUBE_STATE_ID_COLUMN, ollData.startStateID)
            put(SolvesDatabaseConstants.OLLTable.END_CUBE_STATE_ID_COLUMN, ollData.endStateID)
            put(SolvesDatabaseConstants.OLLTable.CASE_COLUMN, ollData.case)
        }

        val selection = "${BaseColumns._ID} = ?"
        val selectionArgs = arrayOf(id.toString())

        db.update(
            SolvesDatabaseConstants.OLLTable.TABLE_NAME,
            contentValues,
            selection,
            selectionArgs
        )
    }

    fun getOLLForSolve(solveID: Long): OLLData? {
        dbAccesses++
        val db = this.readableDatabase

        val projection = arrayOf(
            SolvesDatabaseConstants.OLLTable.DURATION_COLUMN,
            SolvesDatabaseConstants.OLLTable.MOVE_COUNT_COLUMN,
            SolvesDatabaseConstants.OLLTable.START_CUBE_STATE_ID_COLUMN,
            SolvesDatabaseConstants.OLLTable.END_CUBE_STATE_ID_COLUMN,
            SolvesDatabaseConstants.OLLTable.CASE_COLUMN,
            BaseColumns._ID
        )

        val selection = "${SolvesDatabaseConstants.OLLTable.SOLVE_ID_COLUMN} = ?"
        val selectionArgs = arrayOf(solveID.toString())

        val cursor = db.query(
            SolvesDatabaseConstants.OLLTable.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        with(cursor){
            if(cursor.moveToFirst()){
                val duration = getLong(getColumnIndexOrThrow(SolvesDatabaseConstants.OLLTable.DURATION_COLUMN))
                val moveCount = getInt(getColumnIndexOrThrow(SolvesDatabaseConstants.OLLTable.MOVE_COUNT_COLUMN))
                val startStateID = getLong(getColumnIndexOrThrow(SolvesDatabaseConstants.OLLTable.START_CUBE_STATE_ID_COLUMN))
                val endStateID = getLong(getColumnIndexOrThrow(SolvesDatabaseConstants.OLLTable.END_CUBE_STATE_ID_COLUMN))
                val case = getInt(getColumnIndexOrThrow(SolvesDatabaseConstants.OLLTable.CASE_COLUMN))
                val retrievedID = getLong(getColumnIndexOrThrow(BaseColumns._ID))

                cursor.close()
                db.close()
                return OLLData(solveID, duration, moveCount, startStateID, endStateID, case, retrievedID)
            }
            cursor.close()
            db.close()
            return null
        }
    }

    fun deleteOLLDataForSolve(solveID: Long){
        dbAccesses++
        val db = this.writableDatabase

        val selection = "${SolvesDatabaseConstants.OLLTable.SOLVE_ID_COLUMN} = ?"
        val selectionArgs = arrayOf(solveID.toString())

        db.delete(SolvesDatabaseConstants.OLLTable.TABLE_NAME, selection, selectionArgs)
    }
}