package com.example.smartcubeapp.stats

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