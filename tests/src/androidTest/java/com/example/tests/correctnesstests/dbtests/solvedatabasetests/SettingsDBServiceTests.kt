package com.example.tests.correctnesstests.dbtests.solvedatabasetests

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.example.cube_database.solvedatabase.solvesDB.SolvesDatabaseConstants
import com.example.cube_database.solvedatabase.solvesDB.services.SettingsDBService
import com.example.cube_global.AppSettings
import junit.framework.TestCase
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

class SettingsDBServiceTests {

    private lateinit var context: Context
    private lateinit var dbService: SettingsDBService

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        dbService = SettingsDBService(context, SolvesDatabaseConstants.TEST_DATABASE_NAME)
    }

    @After
    fun tearDown() {
        dbService.close()
        context.deleteDatabase(SolvesDatabaseConstants.TEST_DATABASE_NAME)
    }

    @Test
    fun initializeSettingsTest() {
        val db = dbService.readableDatabase
        val projection = arrayOf(
            SolvesDatabaseConstants.SettingsTable.SETTING_NAME,
            SolvesDatabaseConstants.SettingsTable.SETTING_VALUE
        )
        val cursor = db.query(
            SolvesDatabaseConstants.SettingsTable.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )
        val settings = mutableListOf<Pair<String, String>>()
        with(cursor) {
            while (moveToNext()) {
                val settingName =
                    getString(getColumnIndexOrThrow(SolvesDatabaseConstants.SettingsTable.SETTING_NAME))
                val settingValue =
                    getString(getColumnIndexOrThrow(SolvesDatabaseConstants.SettingsTable.SETTING_VALUE))
                settings.add(Pair(settingName, settingValue))
            }
        }
        assertEquals(3, settings.size)
        assert(settings.contains(Pair(SolvesDatabaseConstants.SettingsTable.INSPECTION_ENABLED, "1")))
        assert(settings.contains(Pair(SolvesDatabaseConstants.SettingsTable.SCRAMBLE_GENERATION_ENABLED, "1")))
        assert(settings.contains(Pair(SolvesDatabaseConstants.SettingsTable.SOLVING_TIME_VISIBLE, "1")))
    }

    @Test
    fun updateSettingTest(){
        val setting = Pair(SolvesDatabaseConstants.SettingsTable.INSPECTION_ENABLED, "0")
        dbService.updateSetting(setting.first, setting.second)

        val db = dbService.readableDatabase
        val projection = arrayOf(
            SolvesDatabaseConstants.SettingsTable.SETTING_NAME,
            SolvesDatabaseConstants.SettingsTable.SETTING_VALUE
        )
        val selection = "${SolvesDatabaseConstants.SettingsTable.SETTING_NAME} = ?"
        val selectionArgs = arrayOf(setting.first)
        val cursor = db.query(
            SolvesDatabaseConstants.SettingsTable.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        with(cursor){
            if(moveToFirst()){
                assert(getString(getColumnIndexOrThrow(SolvesDatabaseConstants.SettingsTable.SETTING_VALUE)) == setting.second)
            }
            else{
                TestCase.fail()
            }
        }
    }

    @Test
    fun getSettingTest(){
        val setting = Pair(SolvesDatabaseConstants.SettingsTable.INSPECTION_ENABLED, "1")
        dbService.updateSetting(setting.first, setting.second)

        val settingFromDB = dbService.getSetting(setting.first)
        assertEquals(setting.second, settingFromDB)
    }

    @Test
    fun getSettingTestFailWrongSettingName(){
        val setting = Pair(SolvesDatabaseConstants.SettingsTable.INSPECTION_ENABLED, "1")
        dbService.updateSetting(setting.first, setting.second)

        assertThrows(Exception::class.java) {
            val settingFromDB = dbService.getSetting("Wrong setting name")
        }
    }

    @Test
    fun loadSettingsTest(){
        val settings = mutableListOf<Pair<String, String>>()
        settings.add(Pair(SolvesDatabaseConstants.SettingsTable.INSPECTION_ENABLED, "0"))
        settings.add(Pair(SolvesDatabaseConstants.SettingsTable.SCRAMBLE_GENERATION_ENABLED, "0"))
        settings.add(Pair(SolvesDatabaseConstants.SettingsTable.SOLVING_TIME_VISIBLE, "0"))

        settings.forEach {
            dbService.updateSetting(it.first, it.second)
        }

        dbService.loadSettings()

        assertEquals(false, AppSettings.isInspectionEnabled)
        assertEquals(false, AppSettings.isScrambleGenerationEnabled)
        assertEquals(false, AppSettings.isSolvingTimeVisible)
    }
}