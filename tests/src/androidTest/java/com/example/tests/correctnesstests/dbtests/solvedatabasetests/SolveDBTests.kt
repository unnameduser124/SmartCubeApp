package com.example.tests.correctnesstests.dbtests.solvedatabasetests

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.example.cube_database.solvedatabase.solvesDB.SolveDB
import com.example.cube_database.solvedatabase.solvesDB.SolvesDatabaseConstants
import com.example.cube_database.solvedatabase.solvesDB.services.SettingsDBService
import com.example.cube_global.AppSettings
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.FileOutputStream

class SolveDBTests {
    private lateinit var appContext: Context
    private lateinit var dbService: SolveDB

    @Before
    fun setUp() {
        appContext = InstrumentationRegistry.getInstrumentation().targetContext
        dbService = SolveDB(appContext, SolvesDatabaseConstants.TEST_DATABASE_NAME)
    }

    @After
    fun tearDown() {
        dbService.close()
        appContext.deleteDatabase(SolvesDatabaseConstants.TEST_DATABASE_NAME)
        appContext.deleteDatabase(SolvesDatabaseConstants.STATS_TESTS_DATABASE_NAME)
    }

    @Test
    fun createDatabaseTest() {
        TestCase.assertNotNull(dbService.writableDatabase)
    }

    @Test
    fun createSolveTableTest() {
        val cursor = dbService.readableDatabase.rawQuery(
            "SELECT name " +
                    "FROM sqlite_master WHERE type='table' " +
                    "AND name='${SolvesDatabaseConstants.SolveTable.TABLE_NAME}'",
            null
        )
        cursor.use {
            assertTrue(cursor.moveToFirst())
            assertEquals(
                SolvesDatabaseConstants.SolveTable.TABLE_NAME,
                cursor.getString(0)
            )
        }
    }

    @Test
    fun createCubeStateTableTest() {
        val cursor = dbService.readableDatabase.rawQuery(
            "SELECT name " +
                    "FROM sqlite_master WHERE type='table' " +
                    "AND name='${SolvesDatabaseConstants.CubeStateTable.TABLE_NAME}'",
            null
        )
        cursor.use {
            assertTrue(cursor.moveToFirst())
            assertEquals(
                SolvesDatabaseConstants.CubeStateTable.TABLE_NAME,
                cursor.getString(0)
            )
        }
    }

    @Test
    fun createF2LTableTest() {
        val cursor = dbService.readableDatabase.rawQuery(
            "SELECT name " +
                    "FROM sqlite_master WHERE type='table' " +
                    "AND name='${SolvesDatabaseConstants.F2LTable.TABLE_NAME}'",
            null
        )
        cursor.use {
            assertTrue(cursor.moveToFirst())
            assertEquals(
                SolvesDatabaseConstants.F2LTable.TABLE_NAME,
                cursor.getString(0)
            )
        }
    }

    @Test
    fun createOLLTableTest() {
        val cursor = dbService.readableDatabase.rawQuery(
            "SELECT name " +
                    "FROM sqlite_master WHERE type='table' " +
                    "AND name='${SolvesDatabaseConstants.OLLTable.TABLE_NAME}'",
            null
        )
        cursor.use {
            assertTrue(cursor.moveToFirst())
            assertEquals(
                SolvesDatabaseConstants.OLLTable.TABLE_NAME,
                cursor.getString(0)
            )
        }
    }

    @Test
    fun createPLLTableTest() {
        val cursor = dbService.readableDatabase.rawQuery(
            "SELECT name " +
                    "FROM sqlite_master WHERE type='table' " +
                    "AND name='${SolvesDatabaseConstants.PLLTable.TABLE_NAME}'",
            null
        )
        cursor.use {
            assertTrue(cursor.moveToFirst())
            assertEquals(
                SolvesDatabaseConstants.PLLTable.TABLE_NAME,
                cursor.getString(0)
            )
        }
    }

    @Test
    fun createDeviceTableTest() {
        val cursor = dbService.readableDatabase.rawQuery(
            "SELECT name " +
                    "FROM sqlite_master WHERE type='table' " +
                    "AND name='${SolvesDatabaseConstants.DeviceTable.TABLE_NAME}'",
            null
        )
        cursor.use {
            assertTrue(cursor.moveToFirst())
            assertEquals(
                SolvesDatabaseConstants.DeviceTable.TABLE_NAME,
                cursor.getString(0)
            )
        }
    }

    @Test
    fun createSettingsTableTest() {
        val cursor = dbService.readableDatabase.rawQuery(
            "SELECT name " +
                    "FROM sqlite_master WHERE type='table' " +
                    "AND name='${SolvesDatabaseConstants.SettingsTable.TABLE_NAME}'",
            null
        )
        cursor.use {
            assertTrue(cursor.moveToFirst())
            assertEquals(
                SolvesDatabaseConstants.SettingsTable.TABLE_NAME,
                cursor.getString(0)
            )
        }
    }

    @Test
    fun clearDataTest() {
        copyDatabaseFromAssets(SolvesDatabaseConstants.STATS_TESTS_DATABASE_NAME)
        val db = SolveDB(appContext, SolvesDatabaseConstants.STATS_TESTS_DATABASE_NAME)
        val settingsDBService = SettingsDBService(appContext, SolvesDatabaseConstants.STATS_TESTS_DATABASE_NAME)
        settingsDBService.updateSetting(
            SolvesDatabaseConstants.SettingsTable.INSPECTION_ENABLED,
            "0"
        )
        settingsDBService.updateSetting(
            SolvesDatabaseConstants.SettingsTable.SCRAMBLE_GENERATION_ENABLED,
            "0"
        )
        settingsDBService.updateSetting(
            SolvesDatabaseConstants.SettingsTable.SOLVING_TIME_VISIBLE,
            "0"
        )
        db.clearAllData()

        val crossTableCount = checkTableCount(SolvesDatabaseConstants.CrossTable.TABLE_NAME, db)
        val cubeStateTableCount =
            checkTableCount(SolvesDatabaseConstants.CubeStateTable.TABLE_NAME, db)
        val deviceTableCount = checkTableCount(SolvesDatabaseConstants.DeviceTable.TABLE_NAME, db)
        val f2lTableCount = checkTableCount(SolvesDatabaseConstants.F2LTable.TABLE_NAME, db)
        val ollTableCount = checkTableCount(SolvesDatabaseConstants.OLLTable.TABLE_NAME, db)
        val pllTableCount = checkTableCount(SolvesDatabaseConstants.PLLTable.TABLE_NAME, db)
        val solveTableCount = checkTableCount(SolvesDatabaseConstants.SolveTable.TABLE_NAME, db)

        assertEquals(0, crossTableCount)
        assertEquals(0, cubeStateTableCount)
        assertEquals(0, deviceTableCount)
        assertEquals(0, f2lTableCount)
        assertEquals(0, ollTableCount)
        assertEquals(0, pllTableCount)
        assertEquals(0, solveTableCount)

        settingsDBService.loadSettings()
        assertEquals(false, AppSettings.isSolvingTimeVisible)
        assertEquals(false, AppSettings.isInspectionEnabled)
        assertEquals(false, AppSettings.isScrambleGenerationEnabled)
    }

    private fun checkTableCount(tableName: String, dbService: SolveDB): Long {
        val cursor = dbService.readableDatabase.rawQuery(
            "SELECT COUNT(*) FROM $tableName",
            null
        )
        cursor.use {
            assertTrue(cursor.moveToFirst())
            return cursor.getLong(0)
        }
    }

    private fun copyDatabaseFromAssets(dbName: String) {
        val dbPath = appContext.getDatabasePath(dbName)
        println(dbPath.absolutePath)
        if (!dbPath.exists()) {
            val inputStream = appContext.assets.open(dbName)
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