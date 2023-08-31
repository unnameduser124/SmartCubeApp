package com.example.smartcubeapp.elementdatabase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.io.FileOutputStream

open class ElementDatabase(val context: Context, databaseName: String = ElementDatabaseConstants.DATABASE_NAME) :
    SQLiteOpenHelper(context, databaseName, null, ElementDatabaseConstants.DATABASE_VERSION) {

    init{
        copyDatabaseFromAssets()
    }
    override fun onCreate(db: SQLiteDatabase?) {
        //if (db != null) {
        //    createElementOrientationTable(db)
        //}
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun createElementOrientationTable(db: SQLiteDatabase) {
        db.execSQL(ElementDatabaseConstants.CREATE_ELEMENT_ORIENTATION_TABLE)
    }

    private fun copyDatabaseFromAssets(){
        val dbPath = context.getDatabasePath(ElementDatabaseConstants.DATABASE_NAME)
        if (!dbPath.exists()) {
            val inputStream = context.assets.open(ElementDatabaseConstants.DATABASE_NAME)
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