package com.example.smartcubeapp.solvedatabase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.smartcubeapp.dbAccesses

open class SolveDB(context: Context, databaseName: String = SolvesDatabaseConstants.SOLVE_DATABASE_NAME) :
    SQLiteOpenHelper(context, databaseName, null, SolvesDatabaseConstants.DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        dbAccesses++
        createSolveTable(db)
        createCubeStateTable(db)
        createF2LTable(db)
        createOLLTable(db)
        createPLLTable(db)
        createCrossTable(db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun createSolveTable(db: SQLiteDatabase?) {
        db?.execSQL(SolvesDatabaseConstants.CREATE_SOLVE_TABLE)
    }

    fun createCubeStateTable(db: SQLiteDatabase?) {
        db?.execSQL(SolvesDatabaseConstants.CREATE_CUBE_STATE_TABLE)
    }

    fun createF2LTable(db: SQLiteDatabase?) {
        db?.execSQL(SolvesDatabaseConstants.CREATE_F2L_TABLE)
    }

    fun createOLLTable(db: SQLiteDatabase?) {
        db?.execSQL(SolvesDatabaseConstants.CREATE_OLL_TABLE)
    }

    fun createPLLTable(db: SQLiteDatabase?) {
        db?.execSQL(SolvesDatabaseConstants.CREATE_PLL_TABLE)
    }

    fun createCrossTable(db: SQLiteDatabase?){
        db?.execSQL(SolvesDatabaseConstants.CREATE_CROSS_TABLE)
    }
}