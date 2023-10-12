package com.example.smartcubeapp.solvedatabase.services

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import com.example.smartcubeapp.cube.CubeState
import com.example.smartcubeapp.dbAccesses
import com.example.smartcubeapp.solvedatabase.SolveDB
import com.example.smartcubeapp.solvedatabase.SolvesDatabaseConstants
import com.example.smartcubeapp.solvedatabase.dataclasses.CubeStateData

class CubeStateDBService(context: Context, databaseName: String = SolvesDatabaseConstants.SOLVE_DATABASE_NAME) : SolveDB(context, databaseName) {

    fun addCubeState(cubeStateData: CubeStateData): Long {
        if (cubeStateData.timestamp <= 0 || cubeStateData.moveIndex < 0) {
            throw IllegalArgumentException("Invalid timestamp or moveIndex")
        } else if (!validateMove(cubeStateData.lastMove)) {
            throw IllegalArgumentException("Invalid lastMove")
        }
        dbAccesses++

        val db = this.writableDatabase

        val contentValues = ContentValues().apply {
            put(SolvesDatabaseConstants.CubeStateTable.TIMESTAMP_COLUMN, cubeStateData.timestamp)
            put(SolvesDatabaseConstants.CubeStateTable.SOLVE_ID_COLUMN, cubeStateData.solveID)
            put(SolvesDatabaseConstants.CubeStateTable.MOVE_INDEX_COLUMN, cubeStateData.moveIndex)
            put(SolvesDatabaseConstants.CubeStateTable.LAST_MOVE_COLUMN, cubeStateData.lastMove)
            put(
                SolvesDatabaseConstants.CubeStateTable.CORNER_POSITIONS_COLUMN,
                cubeStateData.cornerPositions
            )
            put(
                SolvesDatabaseConstants.CubeStateTable.EDGE_POSITIONS_COLUMN,
                cubeStateData.edgePositions
            )
            put(
                SolvesDatabaseConstants.CubeStateTable.CORNER_ORIENTATIONS_COLUMN,
                cubeStateData.cornerOrientations
            )
            put(
                SolvesDatabaseConstants.CubeStateTable.EDGE_ORIENTATIONS_COLUMN,
                cubeStateData.edgeOrientations
            )
        }

        return db.insert(SolvesDatabaseConstants.CubeStateTable.TABLE_NAME, null, contentValues)
    }

    fun getCubeState(id: Long): CubeStateData? {
        dbAccesses++
        val db = this.readableDatabase

        val projection = arrayOf(
            SolvesDatabaseConstants.CubeStateTable.TIMESTAMP_COLUMN,
            SolvesDatabaseConstants.CubeStateTable.SOLVE_ID_COLUMN,
            SolvesDatabaseConstants.CubeStateTable.MOVE_INDEX_COLUMN,
            SolvesDatabaseConstants.CubeStateTable.LAST_MOVE_COLUMN,
            SolvesDatabaseConstants.CubeStateTable.CORNER_POSITIONS_COLUMN,
            SolvesDatabaseConstants.CubeStateTable.EDGE_POSITIONS_COLUMN,
            SolvesDatabaseConstants.CubeStateTable.CORNER_ORIENTATIONS_COLUMN,
            SolvesDatabaseConstants.CubeStateTable.EDGE_ORIENTATIONS_COLUMN,
            BaseColumns._ID
        )

        val selection = "${BaseColumns._ID} = ?"
        val selectionArgs = arrayOf(id.toString())

        val cursor = db.query(
            SolvesDatabaseConstants.CubeStateTable.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        with(cursor) {
            if (cursor.moveToFirst()) {
                val retrievedID = getLong(getColumnIndexOrThrow(BaseColumns._ID))
                val timestamp =
                    getLong(getColumnIndexOrThrow(SolvesDatabaseConstants.CubeStateTable.TIMESTAMP_COLUMN))
                val solveID =
                    getLong(getColumnIndexOrThrow(SolvesDatabaseConstants.CubeStateTable.SOLVE_ID_COLUMN))
                val moveIndex =
                    getInt(getColumnIndexOrThrow(SolvesDatabaseConstants.CubeStateTable.MOVE_INDEX_COLUMN))
                val lastMove =
                    getString(getColumnIndexOrThrow(SolvesDatabaseConstants.CubeStateTable.LAST_MOVE_COLUMN))
                val cornerPositions =
                    getString(getColumnIndexOrThrow(SolvesDatabaseConstants.CubeStateTable.CORNER_POSITIONS_COLUMN))
                val edgePositions =
                    getString(getColumnIndexOrThrow(SolvesDatabaseConstants.CubeStateTable.EDGE_POSITIONS_COLUMN))
                val cornerOrientations =
                    getString(getColumnIndexOrThrow(SolvesDatabaseConstants.CubeStateTable.CORNER_ORIENTATIONS_COLUMN))
                val edgeOrientations =
                    getString(getColumnIndexOrThrow(SolvesDatabaseConstants.CubeStateTable.EDGE_ORIENTATIONS_COLUMN))

                return CubeStateData(
                    id = retrievedID,
                    timestamp = timestamp,
                    solveID = solveID,
                    moveIndex = moveIndex,
                    lastMove = lastMove,
                    cornerPositions = cornerPositions,
                    edgePositions = edgePositions,
                    cornerOrientations = cornerOrientations,
                    edgeOrientations = edgeOrientations
                )
            }
            return null
        }
    }

    fun deleteCubeState(id: Long) {
        dbAccesses++
        val db = this.writableDatabase

        val selection = "${BaseColumns._ID} = ?"
        val selectionArgs = arrayOf(id.toString())

        db.delete(SolvesDatabaseConstants.CubeStateTable.TABLE_NAME, selection, selectionArgs)
    }

    fun updateCubeState(cubeStateData: CubeStateData, id: Long) {
        if (cubeStateData.timestamp <= 0 || cubeStateData.moveIndex < 0) {
            throw IllegalArgumentException("Invalid timestamp or moveIndex")
        } else if (!validateMove(cubeStateData.lastMove)) {
            throw IllegalArgumentException("Invalid lastMove")
        }
        dbAccesses++

        val db = this.writableDatabase

        val contentValues = ContentValues().apply {
            put(SolvesDatabaseConstants.CubeStateTable.TIMESTAMP_COLUMN, cubeStateData.timestamp)
            put(SolvesDatabaseConstants.CubeStateTable.SOLVE_ID_COLUMN, cubeStateData.solveID)
            put(SolvesDatabaseConstants.CubeStateTable.MOVE_INDEX_COLUMN, cubeStateData.moveIndex)
            put(SolvesDatabaseConstants.CubeStateTable.LAST_MOVE_COLUMN, cubeStateData.lastMove)
            put(
                SolvesDatabaseConstants.CubeStateTable.CORNER_POSITIONS_COLUMN,
                cubeStateData.cornerPositions
            )
            put(
                SolvesDatabaseConstants.CubeStateTable.EDGE_POSITIONS_COLUMN,
                cubeStateData.edgePositions
            )
            put(
                SolvesDatabaseConstants.CubeStateTable.CORNER_ORIENTATIONS_COLUMN,
                cubeStateData.cornerOrientations
            )
            put(
                SolvesDatabaseConstants.CubeStateTable.EDGE_ORIENTATIONS_COLUMN,
                cubeStateData.edgeOrientations
            )
        }

        val selection = "${BaseColumns._ID} = ?"
        val selectionArgs = arrayOf(id.toString())

        db.update(
            SolvesDatabaseConstants.CubeStateTable.TABLE_NAME,
            contentValues,
            selection,
            selectionArgs
        )
    }

    fun getCubeStatesForSolve(solveID: Long): List<CubeStateData> {
        dbAccesses++
        val db = this.readableDatabase

        val projection = arrayOf(
            SolvesDatabaseConstants.CubeStateTable.TIMESTAMP_COLUMN,
            SolvesDatabaseConstants.CubeStateTable.MOVE_INDEX_COLUMN,
            SolvesDatabaseConstants.CubeStateTable.LAST_MOVE_COLUMN,
            SolvesDatabaseConstants.CubeStateTable.CORNER_POSITIONS_COLUMN,
            SolvesDatabaseConstants.CubeStateTable.EDGE_POSITIONS_COLUMN,
            SolvesDatabaseConstants.CubeStateTable.CORNER_ORIENTATIONS_COLUMN,
            SolvesDatabaseConstants.CubeStateTable.EDGE_ORIENTATIONS_COLUMN,
            BaseColumns._ID
        )

        val selection = "${SolvesDatabaseConstants.CubeStateTable.SOLVE_ID_COLUMN} = ?"
        val selectionArgs = arrayOf(solveID.toString())

        val cursor = db.query(
            SolvesDatabaseConstants.CubeStateTable.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        val cubeStates = mutableListOf<CubeStateData>()

        with(cursor) {
            while (moveToNext()) {
                val retrievedID = getLong(getColumnIndexOrThrow(BaseColumns._ID))
                val timestamp =
                    getLong(getColumnIndexOrThrow(SolvesDatabaseConstants.CubeStateTable.TIMESTAMP_COLUMN))
                val moveIndex =
                    getInt(getColumnIndexOrThrow(SolvesDatabaseConstants.CubeStateTable.MOVE_INDEX_COLUMN))
                val lastMove =
                    getString(getColumnIndexOrThrow(SolvesDatabaseConstants.CubeStateTable.LAST_MOVE_COLUMN))
                val cornerPositions =
                    getString(getColumnIndexOrThrow(SolvesDatabaseConstants.CubeStateTable.CORNER_POSITIONS_COLUMN))
                val edgePositions =
                    getString(getColumnIndexOrThrow(SolvesDatabaseConstants.CubeStateTable.EDGE_POSITIONS_COLUMN))
                val cornerOrientations =
                    getString(getColumnIndexOrThrow(SolvesDatabaseConstants.CubeStateTable.CORNER_ORIENTATIONS_COLUMN))
                val edgeOrientations =
                    getString(getColumnIndexOrThrow(SolvesDatabaseConstants.CubeStateTable.EDGE_ORIENTATIONS_COLUMN))

                cubeStates.add(
                    CubeStateData(
                        id = retrievedID,
                        timestamp = timestamp,
                        solveID = solveID,
                        moveIndex = moveIndex,
                        lastMove = lastMove,
                        cornerPositions = cornerPositions,
                        edgePositions = edgePositions,
                        cornerOrientations = cornerOrientations,
                        edgeOrientations = edgeOrientations)
                )
            }
        }
        return cubeStates
    }

    fun deleteCubeStatesForSolve(solveID: Long){
        dbAccesses++
        val db = this.writableDatabase

        val selection = "${SolvesDatabaseConstants.CubeStateTable.SOLVE_ID_COLUMN} = ?"
        val selectionArgs = arrayOf(solveID.toString())

        db.delete(SolvesDatabaseConstants.CubeStateTable.TABLE_NAME, selection, selectionArgs)
    }

    fun addCubeStateList(states: List<CubeStateData>){
        val db = this.writableDatabase

        db.beginTransaction()

        try{
            states.forEach { cubeStateData ->
                val contentValues = ContentValues().apply{
                    put(SolvesDatabaseConstants.CubeStateTable.TIMESTAMP_COLUMN, cubeStateData.timestamp)
                    put(SolvesDatabaseConstants.CubeStateTable.SOLVE_ID_COLUMN, cubeStateData.solveID)
                    put(SolvesDatabaseConstants.CubeStateTable.MOVE_INDEX_COLUMN, cubeStateData.moveIndex)
                    put(SolvesDatabaseConstants.CubeStateTable.LAST_MOVE_COLUMN, cubeStateData.lastMove)
                    put(
                        SolvesDatabaseConstants.CubeStateTable.CORNER_POSITIONS_COLUMN,
                        cubeStateData.cornerPositions
                    )
                    put(
                        SolvesDatabaseConstants.CubeStateTable.EDGE_POSITIONS_COLUMN,
                        cubeStateData.edgePositions
                    )
                    put(
                        SolvesDatabaseConstants.CubeStateTable.CORNER_ORIENTATIONS_COLUMN,
                        cubeStateData.cornerOrientations
                    )
                    put(
                        SolvesDatabaseConstants.CubeStateTable.EDGE_ORIENTATIONS_COLUMN,
                        cubeStateData.edgeOrientations
                    )
                }

                db.insert(SolvesDatabaseConstants.CubeStateTable.TABLE_NAME, null, contentValues)
            }
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }
    }

    private fun validateMove(move: String): Boolean {
        val validNotations =
            listOf(
                "R",
                "R'",
                "R2",
                "L",
                "L'",
                "L2",
                "U",
                "U'",
                "U2",
                "D",
                "D'",
                "D2",
                "F",
                "F'",
                "F2",
                "B",
                "B'",
                "B2"
            )

        if (move !in validNotations) {
            return false
        }
        return true
    }
}