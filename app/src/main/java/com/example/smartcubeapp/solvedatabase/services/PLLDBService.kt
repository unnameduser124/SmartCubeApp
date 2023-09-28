package com.example.smartcubeapp.solvedatabase.services

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import com.example.smartcubeapp.solvedatabase.SolveDB
import com.example.smartcubeapp.solvedatabase.SolvesDatabaseConstants
import com.example.smartcubeapp.solvedatabase.dataclasses.PLLData

class PLLDBService(context: Context, databaseName: String) : SolveDB(context, databaseName) {

    fun addPLLData(pllData: PLLData): Long{
        if(pllData.duration < 0){
            throw IllegalArgumentException("Duration must be greater than or equal to 0")
        }
        if(pllData.moveCount < 0){
            throw IllegalArgumentException("Move count must be greater than or equal to 0")
        }
        if(pllData.case < 0 || pllData.case > 56){
            throw IllegalArgumentException("PLL case must be between 0 and 56")
        }

        val db = this.writableDatabase

        val contentValues = ContentValues().apply{
            put(SolvesDatabaseConstants.PLLTable.SOLVE_ID_COLUMN, pllData.solveID)
            put(SolvesDatabaseConstants.PLLTable.DURATION_COLUMN, pllData.duration)
            put(SolvesDatabaseConstants.PLLTable.MOVE_COUNT_COLUMN, pllData.moveCount)
            put(SolvesDatabaseConstants.PLLTable.START_CUBE_STATE_ID_COLUMN, pllData.startStateID)
            put(SolvesDatabaseConstants.PLLTable.END_CUBE_STATE_ID_COLUMN, pllData.endStateID)
            put(SolvesDatabaseConstants.PLLTable.CASE_COLUMN, pllData.case)
        }

        return db.insert(SolvesDatabaseConstants.PLLTable.TABLE_NAME, null, contentValues)
    }

    fun getPLLData(id: Long): PLLData?{
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

        with(cursor){
            if(moveToFirst()){
                val solveID = getLong(getColumnIndexOrThrow(SolvesDatabaseConstants.PLLTable.SOLVE_ID_COLUMN))
                val duration = getLong(getColumnIndexOrThrow(SolvesDatabaseConstants.PLLTable.DURATION_COLUMN))
                val moveCount = getLong(getColumnIndexOrThrow(SolvesDatabaseConstants.PLLTable.MOVE_COUNT_COLUMN))
                val startStateID = getLong(getColumnIndexOrThrow(SolvesDatabaseConstants.PLLTable.START_CUBE_STATE_ID_COLUMN))
                val endStateID = getLong(getColumnIndexOrThrow(SolvesDatabaseConstants.PLLTable.END_CUBE_STATE_ID_COLUMN))
                val case = getInt(getColumnIndexOrThrow(SolvesDatabaseConstants.PLLTable.CASE_COLUMN))
                val retrievedID = getLong(getColumnIndexOrThrow(BaseColumns._ID))

                return PLLData(solveID, duration, moveCount, startStateID, endStateID, case, retrievedID)
            }
            return null
        }
    }

    fun deletePLLData(id: Long){
        val db = this.writableDatabase

        val selection = "${BaseColumns._ID} = ?"
        val selectionArgs = arrayOf(id.toString())

        db.delete(SolvesDatabaseConstants.PLLTable.TABLE_NAME, selection, selectionArgs)
    }

    fun updatePLLData(pllData: PLLData, id: Long){
        if(pllData.duration < 0){
            throw IllegalArgumentException("Duration must be greater than or equal to 0")
        }
        if(pllData.moveCount < 0){
            throw IllegalArgumentException("Move count must be greater than or equal to 0")
        }
        if(pllData.case < 0 || pllData.case > 56){
            throw IllegalArgumentException("PLL case must be between 0 and 56")
        }

        val db = this.writableDatabase

        val contentValues = ContentValues().apply{
            put(SolvesDatabaseConstants.PLLTable.SOLVE_ID_COLUMN, pllData.solveID)
            put(SolvesDatabaseConstants.PLLTable.DURATION_COLUMN, pllData.duration)
            put(SolvesDatabaseConstants.PLLTable.MOVE_COUNT_COLUMN, pllData.moveCount)
            put(SolvesDatabaseConstants.PLLTable.START_CUBE_STATE_ID_COLUMN, pllData.startStateID)
            put(SolvesDatabaseConstants.PLLTable.END_CUBE_STATE_ID_COLUMN, pllData.endStateID)
            put(SolvesDatabaseConstants.PLLTable.CASE_COLUMN, pllData.case)
        }

        val selection = "${BaseColumns._ID} = ?"
        val selectionArgs = arrayOf(id.toString())

        db.update(SolvesDatabaseConstants.PLLTable.TABLE_NAME, contentValues, selection, selectionArgs)
    }
}