package com.example.smartcubeapp.elementdatabase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.smartcubeapp.dbAccesses
import java.io.FileOutputStream

open class ElementDatabase(val context: Context, databaseName: String = ElementDatabaseConstants.PHASE_DATABASE_NAME) :
    SQLiteOpenHelper(context, databaseName, null, ElementDatabaseConstants.DATABASE_VERSION) {

    init{
        if(databaseName!=ElementDatabaseConstants.TEST_DATABASE_NAME){
            copyDatabaseFromAssets()
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        dbAccesses++
        if(databaseName == ElementDatabaseConstants.TEST_DATABASE_NAME){
            createElementOrientationTable(db!!)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun createElementOrientationTable(db: SQLiteDatabase) {
        db.execSQL(ElementDatabaseConstants.CREATE_ELEMENT_ORIENTATION_TABLE)
    }

    private fun copyDatabaseFromAssets(){
        val dbPath = context.getDatabasePath(databaseName)
        if (!dbPath.exists()) {
            val inputStream = context.assets.open(databaseName)
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