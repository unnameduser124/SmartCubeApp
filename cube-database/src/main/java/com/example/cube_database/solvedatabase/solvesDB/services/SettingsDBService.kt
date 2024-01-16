package com.example.cube_database.solvedatabase.solvesDB.services

import android.content.ContentValues
import android.content.Context
import com.example.cube_database.solvedatabase.solvesDB.SolveDB
import com.example.cube_database.solvedatabase.solvesDB.SolvesDatabaseConstants.SOLVE_DATABASE_NAME
import com.example.cube_database.solvedatabase.solvesDB.SolvesDatabaseConstants.SettingsTable
import com.example.cube_global.AppSettings

class SettingsDBService(
    context: Context,
    dbName: String = SOLVE_DATABASE_NAME
) : SolveDB(context, dbName) {

    fun updateSetting(settingName: String, settingValue: String){
        val db = writableDatabase
        val values = ContentValues().apply{
            put(SettingsTable.SETTING_VALUE, settingValue)

        }
        val selection = "${SettingsTable.SETTING_NAME} = ?"
        val selectionArgs = arrayOf(settingName)
        println("Query = UPDATE ${SettingsTable.TABLE_NAME} SET ${SettingsTable.SETTING_VALUE} = $settingValue WHERE $selection = $settingName")
        db.update(SettingsTable.TABLE_NAME, values, selection, selectionArgs)
    }

    fun getSetting(settingName: String): String{
        val db = this.readableDatabase

        val projection = arrayOf(SettingsTable.SETTING_VALUE)
        val selection = "${SettingsTable.SETTING_NAME} = ?"
        val selectionArgs = arrayOf(settingName)

        val cursor = db.query(
            SettingsTable.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        with(cursor){
            if(moveToFirst()){
                return getString(getColumnIndexOrThrow(SettingsTable.SETTING_VALUE))
            }
            else{
                throw Exception("Setting not found")
            }
        }
    }

    fun loadSettings(){
        val db = readableDatabase

        val projection = arrayOf(SettingsTable.SETTING_NAME, SettingsTable.SETTING_VALUE)

        val cursor = db.query(
            SettingsTable.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        with(cursor){
            while(moveToNext()){
                val settingName = getString(getColumnIndexOrThrow(SettingsTable.SETTING_NAME))
                val settingValue = getString(getColumnIndexOrThrow(SettingsTable.SETTING_VALUE))
                when(settingName) {
                    SettingsTable.INSPECTION_ENABLED -> {
                        AppSettings.isInspectionEnabled = settingValue == "1"
                    }

                    SettingsTable.SCRAMBLE_GENERATION_ENABLED -> {
                        AppSettings.isScrambleGenerationEnabled = settingValue == "1"
                    }

                    SettingsTable.SOLVING_TIME_VISIBLE -> {
                        AppSettings.isSolvingTimeVisible = settingValue == "1"
                    }
                }
            }
        }
    }
}