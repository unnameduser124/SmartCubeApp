package com.example.cube_database.solvedatabase.solvesDB

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.cube_global.dbAccesses

open class SolveDB(
    private val context: Context,
    private val dbName: String = SolvesDatabaseConstants.SOLVE_DATABASE_NAME
) :
    SQLiteOpenHelper(context, dbName, null, SolvesDatabaseConstants.DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        dbAccesses++
        createTables(db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    private fun createSolveTable(db: SQLiteDatabase?) {
        db?.execSQL(SolvesDatabaseConstants.CREATE_SOLVE_TABLE)
    }

    private fun createCubeStateTable(db: SQLiteDatabase?) {
        db?.execSQL(SolvesDatabaseConstants.CREATE_CUBE_STATE_TABLE)
    }

    private fun createF2LTable(db: SQLiteDatabase?) {
        db?.execSQL(SolvesDatabaseConstants.CREATE_F2L_TABLE)
    }

    private fun createOLLTable(db: SQLiteDatabase?) {
        db?.execSQL(SolvesDatabaseConstants.CREATE_OLL_TABLE)
    }

    private fun createPLLTable(db: SQLiteDatabase?) {
        db?.execSQL(SolvesDatabaseConstants.CREATE_PLL_TABLE)
    }

    private fun createCrossTable(db: SQLiteDatabase?) {
        db?.execSQL(SolvesDatabaseConstants.CREATE_CROSS_TABLE)
    }

    private fun createDeviceTable(db: SQLiteDatabase?){
        db?.execSQL(SolvesDatabaseConstants.CREATE_DEVICE_TABLE)
    }

    private fun createSettingsTable(db: SQLiteDatabase?){
        db?.execSQL(SolvesDatabaseConstants.CREATE_SETTINGS_TABLE)
    }

    private fun initializeSettings(db: SQLiteDatabase?){
        db?.execSQL(SolvesDatabaseConstants.INITIALIZE_SETTINGS_QUERY)
    }

    fun clearAllData(){
        val db = this.writableDatabase
        val dropQueries = SolvesDatabaseConstants.CLEAR_ALL_DATA_QUERY.split(';')
        for (query in dropQueries){
            if(query.isEmpty()) continue
            db.execSQL(query)
        }
        createTablesAfterDataClearing(db)
    }

    private fun createTables(db: SQLiteDatabase?){
        createSolveTable(db)
        createCubeStateTable(db)
        createF2LTable(db)
        createOLLTable(db)
        createPLLTable(db)
        createCrossTable(db)
        createDeviceTable(db)
        createSettingsTable(db)
        initializeSettings(db)
    }

    private fun createTablesAfterDataClearing(db: SQLiteDatabase?){
        createSolveTable(db)
        createCubeStateTable(db)
        createF2LTable(db)
        createOLLTable(db)
        createPLLTable(db)
        createCrossTable(db)
    }

    fun getDatabaseSizeInMegabytes(): Double {
        val dbFile = context.getDatabasePath(dbName)
        return if (dbFile.exists()) {
            val megabyte = 1024 * 1024
            dbFile.length() / megabyte.toDouble()
        } else {
            0.0
        }
    }
}