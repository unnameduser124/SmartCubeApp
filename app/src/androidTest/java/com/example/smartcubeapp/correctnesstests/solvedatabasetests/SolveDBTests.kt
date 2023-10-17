package com.example.smartcubeapp.correctnesstests.solvedatabasetests

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.example.smartcubeapp.elementdatabase.ElementDatabaseConstants
import com.example.smartcubeapp.solvedatabase.SolveDB
import com.example.smartcubeapp.solvedatabase.SolvesDatabaseConstants
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Test

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
        appContext.deleteDatabase(ElementDatabaseConstants.TEST_DATABASE_NAME)
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
            TestCase.assertTrue(cursor.moveToFirst())
            TestCase.assertEquals(
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
            TestCase.assertTrue(cursor.moveToFirst())
            TestCase.assertEquals(
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
            TestCase.assertTrue(cursor.moveToFirst())
            TestCase.assertEquals(
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
            TestCase.assertTrue(cursor.moveToFirst())
            TestCase.assertEquals(
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
            TestCase.assertTrue(cursor.moveToFirst())
            TestCase.assertEquals(
                SolvesDatabaseConstants.PLLTable.TABLE_NAME,
                cursor.getString(0)
            )
        }
    }
}