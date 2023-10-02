package com.example.smartcubeapp.solvedatabase.services

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import com.example.smartcubeapp.solvedatabase.SolveDB
import com.example.smartcubeapp.solvedatabase.SolvesDatabaseConstants
import com.example.smartcubeapp.solvedatabase.dataclasses.F2LData

class F2LDBService(context: Context, databaseName: String = SolvesDatabaseConstants.SOLVE_DATABASE_NAME) : SolveDB(context, databaseName) {

    fun addF2LData(f2lData: F2LData): Long{
        if(f2lData.duration < 0){
            throw IllegalArgumentException("Duration must be greater than or equal to 0")
        }
        if(f2lData.moveCount < 0){
            throw IllegalArgumentException("Move count must be greater than or equal to 0")
        }
        val db = this.writableDatabase

        val contentValues = ContentValues().apply{
            put(SolvesDatabaseConstants.F2LTable.SOLVE_ID_COLUMN, f2lData.solveID)
            put(SolvesDatabaseConstants.F2LTable.DURATION_COLUMN, f2lData.duration)
            put(SolvesDatabaseConstants.F2LTable.MOVE_COUNT_COLUMN, f2lData.moveCount)
            put(SolvesDatabaseConstants.F2LTable.START_CUBE_STATE_ID_COLUMN, f2lData.startStateID)
            put(SolvesDatabaseConstants.F2LTable.END_CUBE_STATE_ID_COLUMN, f2lData.endStateID)
        }

        return db.insert(SolvesDatabaseConstants.F2LTable.TABLE_NAME, null, contentValues)
    }

    fun getF2LData(id: Long): F2LData?{
        val db = this.readableDatabase

        val projection = arrayOf(
            SolvesDatabaseConstants.F2LTable.SOLVE_ID_COLUMN,
            SolvesDatabaseConstants.F2LTable.DURATION_COLUMN,
            SolvesDatabaseConstants.F2LTable.MOVE_COUNT_COLUMN,
            SolvesDatabaseConstants.F2LTable.START_CUBE_STATE_ID_COLUMN,
            SolvesDatabaseConstants.F2LTable.END_CUBE_STATE_ID_COLUMN,
            BaseColumns._ID
        )

        val selection = "${BaseColumns._ID} = ?"
        val selectionArgs = arrayOf(id.toString())

        val cursor = db.query(
            SolvesDatabaseConstants.F2LTable.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        with(cursor){
            if(moveToFirst()){
                val solveID = getLong(getColumnIndexOrThrow(SolvesDatabaseConstants.F2LTable.SOLVE_ID_COLUMN))
                val duration = getLong(getColumnIndexOrThrow(SolvesDatabaseConstants.F2LTable.DURATION_COLUMN))
                val moveCount = getInt(getColumnIndexOrThrow(SolvesDatabaseConstants.F2LTable.MOVE_COUNT_COLUMN))
                val startStateID = getLong(getColumnIndexOrThrow(SolvesDatabaseConstants.F2LTable.START_CUBE_STATE_ID_COLUMN))
                val endStateID = getLong(getColumnIndexOrThrow(SolvesDatabaseConstants.F2LTable.END_CUBE_STATE_ID_COLUMN))
                val retrievedID = getLong(getColumnIndexOrThrow(BaseColumns._ID))

                return F2LData(solveID, duration, moveCount, startStateID, endStateID, retrievedID)
            }
            return null
        }
    }

    fun deleteF2LData(id: Long){
        val db = this.writableDatabase

        val selection = "${BaseColumns._ID} = ?"
        val selectionArgs = arrayOf(id.toString())

        db.delete(SolvesDatabaseConstants.F2LTable.TABLE_NAME, selection, selectionArgs)
    }

    fun updateF2LData(f2lData: F2LData, id: Long){
        if(f2lData.duration < 0){
            throw IllegalArgumentException("Duration must be greater than or equal to 0")
        }
        if(f2lData.moveCount < 0){
            throw IllegalArgumentException("Move count must be greater than or equal to 0")
        }

        val db = this.writableDatabase

        val contentValues = ContentValues().apply{
            put(SolvesDatabaseConstants.F2LTable.SOLVE_ID_COLUMN, f2lData.solveID)
            put(SolvesDatabaseConstants.F2LTable.DURATION_COLUMN, f2lData.duration)
            put(SolvesDatabaseConstants.F2LTable.MOVE_COUNT_COLUMN, f2lData.moveCount)
            put(SolvesDatabaseConstants.F2LTable.START_CUBE_STATE_ID_COLUMN, f2lData.startStateID)
            put(SolvesDatabaseConstants.F2LTable.END_CUBE_STATE_ID_COLUMN, f2lData.endStateID)
        }

        val selection = "${BaseColumns._ID} = ?"
        val selectionArgs = arrayOf(id.toString())

        db.update(SolvesDatabaseConstants.F2LTable.TABLE_NAME, contentValues, selection, selectionArgs)
    }

    fun getF2LForSolve(solveID: Long): F2LData?{
        val db = this.readableDatabase

        val projection = arrayOf(
            SolvesDatabaseConstants.F2LTable.DURATION_COLUMN,
            SolvesDatabaseConstants.F2LTable.MOVE_COUNT_COLUMN,
            SolvesDatabaseConstants.F2LTable.START_CUBE_STATE_ID_COLUMN,
            SolvesDatabaseConstants.F2LTable.END_CUBE_STATE_ID_COLUMN,
            BaseColumns._ID
        )

        val selection = "${SolvesDatabaseConstants.F2LTable.SOLVE_ID_COLUMN} = ?"
        val selectionArgs = arrayOf(solveID.toString())

        val cursor = db.query(
            SolvesDatabaseConstants.F2LTable.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        with(cursor){
            if(moveToFirst()){
                val duration = getLong(getColumnIndexOrThrow(SolvesDatabaseConstants.F2LTable.DURATION_COLUMN))
                val moveCount = getInt(getColumnIndexOrThrow(SolvesDatabaseConstants.F2LTable.MOVE_COUNT_COLUMN))
                val startStateID = getLong(getColumnIndexOrThrow(SolvesDatabaseConstants.F2LTable.START_CUBE_STATE_ID_COLUMN))
                val endStateID = getLong(getColumnIndexOrThrow(SolvesDatabaseConstants.F2LTable.END_CUBE_STATE_ID_COLUMN))
                val retrievedID = getLong(getColumnIndexOrThrow(BaseColumns._ID))

                return F2LData(solveID, duration, moveCount, startStateID, endStateID, retrievedID)
            }
            return null
        }
    }
}