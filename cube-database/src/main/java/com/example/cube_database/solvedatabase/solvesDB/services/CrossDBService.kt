package com.example.cube_database.solvedatabase.solvesDB.services

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import com.example.cube_database.solvedatabase.solvesDB.SolveDB
import com.example.cube_database.solvedatabase.solvesDB.SolvesDatabaseConstants
import com.example.cube_cube.solveDBDataClasses.CrossData
import com.example.cube_global.dbAccesses

class CrossDBService(context: Context, dbName: String = SolvesDatabaseConstants.SOLVE_DATABASE_NAME) :
    SolveDB(context, dbName) {


    fun addCrossData(crossData: CrossData): Long{
        if(crossData.duration < 0){
            throw IllegalArgumentException("Duration must be greater than or equal to 0")
        }
        if(crossData.moveCount < 0){
            throw IllegalArgumentException("Move count must be greater than or equal to 0")
        }
        dbAccesses++
        val db = this.writableDatabase

        val contentValues = ContentValues().apply{
            put(SolvesDatabaseConstants.CrossTable.SOLVE_ID_COLUMN, crossData.solveID)
            put(SolvesDatabaseConstants.CrossTable.DURATION_COLUMN, crossData.duration)
            put(SolvesDatabaseConstants.CrossTable.MOVE_COUNT_COLUMN, crossData.moveCount)
            put(SolvesDatabaseConstants.CrossTable.START_CUBE_STATE_ID_COLUMN, crossData.startStateID)
            put(SolvesDatabaseConstants.CrossTable.END_CUBE_STATE_ID_COLUMN, crossData.endStateID)
        }

        return db.insert(SolvesDatabaseConstants.CrossTable.TABLE_NAME, null, contentValues)
    }

    fun getCrossData(id: Long): CrossData?{
        dbAccesses++
        val db = this.readableDatabase

        val projection = arrayOf(
            SolvesDatabaseConstants.CrossTable.SOLVE_ID_COLUMN,
            SolvesDatabaseConstants.CrossTable.DURATION_COLUMN,
            SolvesDatabaseConstants.CrossTable.MOVE_COUNT_COLUMN,
            SolvesDatabaseConstants.CrossTable.START_CUBE_STATE_ID_COLUMN,
            SolvesDatabaseConstants.CrossTable.END_CUBE_STATE_ID_COLUMN,
            BaseColumns._ID
        )

        val selection = "${BaseColumns._ID} = ?"
        val selectionArgs = arrayOf(id.toString())

        val cursor = db.query(
            SolvesDatabaseConstants.CrossTable.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        with(cursor){
            if(moveToFirst()){
                val solveID = getLong(getColumnIndexOrThrow(com.example.cube_database.solvedatabase.solvesDB.SolvesDatabaseConstants.CrossTable.SOLVE_ID_COLUMN))
                val duration = getLong(getColumnIndexOrThrow(com.example.cube_database.solvedatabase.solvesDB.SolvesDatabaseConstants.CrossTable.DURATION_COLUMN))
                val moveCount = getInt(getColumnIndexOrThrow(com.example.cube_database.solvedatabase.solvesDB.SolvesDatabaseConstants.CrossTable.MOVE_COUNT_COLUMN))
                val startStateID = getLong(getColumnIndexOrThrow(com.example.cube_database.solvedatabase.solvesDB.SolvesDatabaseConstants.CrossTable.START_CUBE_STATE_ID_COLUMN))
                val endStateID = getLong(getColumnIndexOrThrow(com.example.cube_database.solvedatabase.solvesDB.SolvesDatabaseConstants.CrossTable.END_CUBE_STATE_ID_COLUMN))
                val retrievedID = getLong(getColumnIndexOrThrow(BaseColumns._ID))

                cursor.close()
                db.close()
                return CrossData(solveID, duration, moveCount, startStateID, endStateID, retrievedID)
            }
            cursor.close()
            db.close()
            return null
        }
    }

    fun deleteCrossData(id: Long){
        dbAccesses++
        val db = this.writableDatabase

        val selection = "${BaseColumns._ID} = ?"
        val selectionArgs = arrayOf(id.toString())

        db.delete(SolvesDatabaseConstants.CrossTable.TABLE_NAME, selection, selectionArgs)
    }

    fun updateCrossData(crossData: CrossData, id: Long){
        if(crossData.duration < 0){
            throw IllegalArgumentException("Duration must be greater than or equal to 0")
        }
        if(crossData.moveCount < 0){
            throw IllegalArgumentException("Move count must be greater than or equal to 0")
        }
        dbAccesses++

        val db = this.writableDatabase

        val contentValues = ContentValues().apply{
            put(SolvesDatabaseConstants.CrossTable.SOLVE_ID_COLUMN, crossData.solveID)
            put(SolvesDatabaseConstants.CrossTable.DURATION_COLUMN, crossData.duration)
            put(SolvesDatabaseConstants.CrossTable.MOVE_COUNT_COLUMN, crossData.moveCount)
            put(SolvesDatabaseConstants.CrossTable.START_CUBE_STATE_ID_COLUMN, crossData.startStateID)
            put(SolvesDatabaseConstants.CrossTable.END_CUBE_STATE_ID_COLUMN, crossData.endStateID)
        }

        val selection = "${BaseColumns._ID} = ?"
        val selectionArgs = arrayOf(id.toString())

        db.update(SolvesDatabaseConstants.CrossTable.TABLE_NAME, contentValues, selection, selectionArgs)
    }

    fun getCrossForSolve(solveID: Long): CrossData?{
        dbAccesses++
        val db = this.readableDatabase

        val projection = arrayOf(
            SolvesDatabaseConstants.CrossTable.DURATION_COLUMN,
            SolvesDatabaseConstants.CrossTable.MOVE_COUNT_COLUMN,
            SolvesDatabaseConstants.CrossTable.START_CUBE_STATE_ID_COLUMN,
            SolvesDatabaseConstants.CrossTable.END_CUBE_STATE_ID_COLUMN,
            BaseColumns._ID
        )

        val selection = "${SolvesDatabaseConstants.CrossTable.SOLVE_ID_COLUMN} = ?"
        val selectionArgs = arrayOf(solveID.toString())

        val cursor = db.query(
            SolvesDatabaseConstants.CrossTable.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        with(cursor){
            if(moveToFirst()){
                val duration = getLong(getColumnIndexOrThrow(com.example.cube_database.solvedatabase.solvesDB.SolvesDatabaseConstants.CrossTable.DURATION_COLUMN))
                val moveCount = getInt(getColumnIndexOrThrow(com.example.cube_database.solvedatabase.solvesDB.SolvesDatabaseConstants.CrossTable.MOVE_COUNT_COLUMN))
                val startStateID = getLong(getColumnIndexOrThrow(com.example.cube_database.solvedatabase.solvesDB.SolvesDatabaseConstants.CrossTable.START_CUBE_STATE_ID_COLUMN))
                val endStateID = getLong(getColumnIndexOrThrow(com.example.cube_database.solvedatabase.solvesDB.SolvesDatabaseConstants.CrossTable.END_CUBE_STATE_ID_COLUMN))
                val retrievedID = getLong(getColumnIndexOrThrow(BaseColumns._ID))

                cursor.close()
                db.close()
                return CrossData(solveID, duration, moveCount, startStateID, endStateID, retrievedID)
            }
            cursor.close()
            db.close()
            return null
        }
    }

    fun deleteCrossDataForSolve(solveID: Long){
        dbAccesses++
        val db = this.writableDatabase

        val selection = "${SolvesDatabaseConstants.CrossTable.SOLVE_ID_COLUMN} = ?"
        val selectionArgs = arrayOf(solveID.toString())

        db.delete(SolvesDatabaseConstants.CrossTable.TABLE_NAME, selection, selectionArgs)
    }
}