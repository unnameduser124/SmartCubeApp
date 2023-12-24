package com.example.cube_database.solvedatabase.stats

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.io.FileOutputStream

class StatsDB(val context: Context, private val dbName: String = StatsDBConstants.STATS_DATABASE_NAME) :
    SQLiteOpenHelper(context, dbName, null, StatsDBConstants.DATABASE_VERSION) {

    init{
        if (!context.getDatabasePath(dbName).exists()) {
            copyDatabaseFromAssets()
        }
    }
    override fun onCreate(db: SQLiteDatabase?) {

    }

    private fun createStatsTable(db: SQLiteDatabase?) {
        db?.execSQL(StatsDBConstants.CREATE_STATS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun getFieldValue(field: String): Double{
        val db = this.readableDatabase
        val projection = arrayOf(StatsDBConstants.StatsTable.STATISTIC_VALUE_COLUMN)
        val selection = "${StatsDBConstants.StatsTable.STATISTIC_NAME_COLUMN} = ?"
        val selectionArgs = arrayOf(field)
        val cursor = db.query(
            StatsDBConstants.StatsTable.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )
        with(cursor){
            if(moveToFirst()){
                val result = getDouble(getColumnIndexOrThrow(StatsDBConstants.StatsTable.STATISTIC_VALUE_COLUMN))
                close()
                return result
            }
        }
        throw Exception("Field $field not found in database")
    }

    fun updateFieldValue(field: String, value: String){
        val db = this.writableDatabase
        val selection = "${StatsDBConstants.StatsTable.STATISTIC_NAME_COLUMN} = ?"
        val selectionArgs = arrayOf(field)
        val values = ContentValues().apply {
            put(StatsDBConstants.StatsTable.STATISTIC_VALUE_COLUMN, value)
        }
        db.update(
            StatsDBConstants.StatsTable.TABLE_NAME,
            values,
            selection,
            selectionArgs
        )
    }

    private fun copyDatabaseFromAssets(){
        val dbPath = context.getDatabasePath(dbName)
        if (!dbPath.exists()) {
            val inputStream = context.assets.open(dbName)
            val outputStream = FileOutputStream(dbPath)
            val buffer = ByteArray(1024)
            var length: Int
            while (inputStream.read(buffer).also { length = it } > 0) {
                outputStream.write(buffer, 0, length)
            }
            outputStream.flush()
            outputStream.close()
            inputStream.close()
        }
    }

}