package com.example.smartcubeapp.solvedatabase.services

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import com.example.smartcubeapp.solvedatabase.SolveDB
import com.example.smartcubeapp.solvedatabase.SolvesDatabaseConstants
import com.example.smartcubeapp.solvedatabase.dataclasses.SolveData

class SolveDBService(context: Context, databaseName: String) : SolveDB(context, databaseName) {

    fun addSolve(solveData: SolveData): Long {
        if(solveData.solveDuration <= 0){
            throw IllegalArgumentException("Solve duration must be greater than 0")
        }
        else if(solveData.scramble == ""){
            throw IllegalArgumentException("Scramble must not be empty")
        }
        val db = this.writableDatabase

        val contentValues = ContentValues().apply {
            put(SolvesDatabaseConstants.SolveTable.DURATION_COLUMN, solveData.solveDuration)
            put(SolvesDatabaseConstants.SolveTable.TIMESTAMP_COLUMN, solveData.timestamp)
            put(
                SolvesDatabaseConstants.SolveTable.SCRAMBLED_STATE_ID_COLUMN,
                solveData.scrambledStateID
            )
            put(SolvesDatabaseConstants.SolveTable.SCRAMBLE_SEQUENCE_COLUMN, solveData.scramble)
        }
        //TODO("Add solveStateSequence to database")

        return db.insert(SolvesDatabaseConstants.SolveTable.TABLE_NAME, null, contentValues)
    }

    fun getSolve(id: Long): SolveData? {
        val db = this.readableDatabase

        val projection = arrayOf(
            BaseColumns._ID,
            SolvesDatabaseConstants.SolveTable.DURATION_COLUMN,
            SolvesDatabaseConstants.SolveTable.TIMESTAMP_COLUMN,
            SolvesDatabaseConstants.SolveTable.SCRAMBLED_STATE_ID_COLUMN,
            SolvesDatabaseConstants.SolveTable.SCRAMBLE_SEQUENCE_COLUMN
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

                return SolveData(
                    solveId,
                    duration,
                    timestamp,
                    scrambledStateID,
                    scramble
                )
            }
        }
        return null
    }

    fun deleteSolve(id: Long){
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

        val contentValues = ContentValues().apply {
            put(SolvesDatabaseConstants.SolveTable.DURATION_COLUMN, solveData.solveDuration)
            put(SolvesDatabaseConstants.SolveTable.TIMESTAMP_COLUMN, solveData.timestamp)
            put(SolvesDatabaseConstants.SolveTable.SCRAMBLED_STATE_ID_COLUMN, solveData.scrambledStateID)
            put(SolvesDatabaseConstants.SolveTable.SCRAMBLE_SEQUENCE_COLUMN, solveData.scramble)
        }

        val selection = "${BaseColumns._ID} = ?"
        val selectionArgs = arrayOf(id.toString())

        db.update(SolvesDatabaseConstants.SolveTable.TABLE_NAME, contentValues, selection, selectionArgs)

        //TODO("Update solveStateSequence in database")
    }
}