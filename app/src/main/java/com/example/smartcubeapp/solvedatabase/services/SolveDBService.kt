package com.example.smartcubeapp.solvedatabase.services

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import com.example.smartcubeapp.dbAccesses
import com.example.smartcubeapp.solvedatabase.SolveDB
import com.example.smartcubeapp.solvedatabase.SolvesDatabaseConstants
import com.example.smartcubeapp.solvedatabase.dataclasses.SolveData

class SolveDBService(context: Context, databaseName: String = SolvesDatabaseConstants.SOLVE_DATABASE_NAME) : SolveDB(context, databaseName) {

    fun addSolve(solveData: SolveData): Long {
        if(solveData.solveDuration <= 0){
            throw IllegalArgumentException("Solve duration must be greater than 0")
        }
        else if(solveData.scramble == ""){
            throw IllegalArgumentException("Scramble must not be empty")
        }
        dbAccesses++
        val db = this.writableDatabase

        val contentValues = ContentValues().apply {
            put(SolvesDatabaseConstants.SolveTable.DURATION_COLUMN, solveData.solveDuration)
            put(SolvesDatabaseConstants.SolveTable.TIMESTAMP_COLUMN, solveData.timestamp)
            put(
                SolvesDatabaseConstants.SolveTable.SCRAMBLED_STATE_ID_COLUMN,
                solveData.scrambledStateID
            )
            put(SolvesDatabaseConstants.SolveTable.SCRAMBLE_SEQUENCE_COLUMN, solveData.scramble)
            put(SolvesDatabaseConstants.SolveTable.MOVE_COUNT, solveData.moveCount)
            put(SolvesDatabaseConstants.SolveTable.PENALTY_COLUMN, solveData.penalty)
        }

        val id = db.insert(SolvesDatabaseConstants.SolveTable.TABLE_NAME, null, contentValues)
        db.close()
        return id
    }

    fun getSolve(id: Long): SolveData? {
        dbAccesses++
        val db = this.readableDatabase

        val projection = arrayOf(
            BaseColumns._ID,
            SolvesDatabaseConstants.SolveTable.DURATION_COLUMN,
            SolvesDatabaseConstants.SolveTable.TIMESTAMP_COLUMN,
            SolvesDatabaseConstants.SolveTable.SCRAMBLED_STATE_ID_COLUMN,
            SolvesDatabaseConstants.SolveTable.SCRAMBLE_SEQUENCE_COLUMN,
            SolvesDatabaseConstants.SolveTable.MOVE_COUNT,
            SolvesDatabaseConstants.SolveTable.PENALTY_COLUMN
        )

        val selection = "${BaseColumns._ID} = ?"
        val selectionArgs = arrayOf(id.toString())

        val cursor = db.query(
            SolvesDatabaseConstants.SolveTable.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        with(cursor) {
            while (moveToNext()) {
                val solveId = getLong(getColumnIndexOrThrow(BaseColumns._ID))
                val duration =
                    getLong(getColumnIndexOrThrow(SolvesDatabaseConstants.SolveTable.DURATION_COLUMN))
                val timestamp =
                    getLong(getColumnIndexOrThrow(SolvesDatabaseConstants.SolveTable.TIMESTAMP_COLUMN))
                val scrambledStateID =
                    getLong(getColumnIndexOrThrow(SolvesDatabaseConstants.SolveTable.SCRAMBLED_STATE_ID_COLUMN))
                val scramble =
                    getString(getColumnIndexOrThrow(SolvesDatabaseConstants.SolveTable.SCRAMBLE_SEQUENCE_COLUMN))
                val moveCount =
                    getInt(getColumnIndexOrThrow(SolvesDatabaseConstants.SolveTable.MOVE_COUNT))
                val penalty =
                    getInt(getColumnIndexOrThrow(SolvesDatabaseConstants.SolveTable.PENALTY_COLUMN))

                cursor.close()
                db.close()
                return SolveData(
                    solveId,
                    duration,
                    timestamp,
                    scrambledStateID,
                    scramble,
                    moveCount,
                    penalty
                )
            }
        }
        cursor.close()
        db.close()
        return null
    }

    fun deleteSolve(id: Long){
        dbAccesses++
        val db = this.writableDatabase

        val selection = "${BaseColumns._ID} = ?"
        val selectionArgs = arrayOf(id.toString())

        db.delete(SolvesDatabaseConstants.SolveTable.TABLE_NAME, selection, selectionArgs)
    }

    fun updateSolve(solveData: SolveData, id: Long){
        val db = this.writableDatabase

        if(solveData.solveDuration <= 0){
            throw IllegalArgumentException("Solve duration must be greater than 0")
        }
        else if(solveData.scramble == ""){
            throw IllegalArgumentException("Scramble must not be empty")
        }
        dbAccesses++

        val contentValues = ContentValues().apply {
            put(SolvesDatabaseConstants.SolveTable.DURATION_COLUMN, solveData.solveDuration)
            put(SolvesDatabaseConstants.SolveTable.TIMESTAMP_COLUMN, solveData.timestamp)
            put(SolvesDatabaseConstants.SolveTable.SCRAMBLED_STATE_ID_COLUMN, solveData.scrambledStateID)
            put(SolvesDatabaseConstants.SolveTable.SCRAMBLE_SEQUENCE_COLUMN, solveData.scramble)
            put(SolvesDatabaseConstants.SolveTable.MOVE_COUNT, solveData.moveCount)
            put(SolvesDatabaseConstants.SolveTable.PENALTY_COLUMN, solveData.penalty)
        }

        val selection = "${BaseColumns._ID} = ?"
        val selectionArgs = arrayOf(id.toString())

        db.update(SolvesDatabaseConstants.SolveTable.TABLE_NAME, contentValues, selection, selectionArgs)
    }

    fun getAllSolveIDs(): List<Long>{
        dbAccesses++
        val db = this.readableDatabase

        val projection = arrayOf(
            BaseColumns._ID
        )

        val cursor = db.query(
            SolvesDatabaseConstants.SolveTable.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        val solveIDs = mutableListOf<Long>()
        with(cursor) {
            while (moveToNext()) {
                val solveId = getLong(getColumnIndexOrThrow(BaseColumns._ID))
                solveIDs.add(solveId)
            }
        }
        cursor.close()
        db.close()
        return solveIDs
    }
}