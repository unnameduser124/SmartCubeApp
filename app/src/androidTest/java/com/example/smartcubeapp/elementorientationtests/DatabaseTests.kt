package com.example.smartcubeapp.elementorientationtests

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.example.smartcubeapp.elementdatabase.ElementDatabase
import com.example.smartcubeapp.elementdatabase.ElementDatabaseConstants
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test

class DatabaseTests {
    private lateinit var appContext: Context
    private lateinit var dbService: ElementDatabase

    @Before
    fun setUp() {
        appContext = InstrumentationRegistry.getInstrumentation().targetContext
        dbService = ElementDatabase(appContext, ElementDatabaseConstants.TEST_DATABASE_NAME)
        dbService.createElementOrientationTable(dbService.writableDatabase)
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
    fun createSessionsTableTest() {
        val cursor = dbService.readableDatabase.rawQuery(
            "SELECT name " +
                    "FROM sqlite_master WHERE type='table' " +
                    "AND name='${ElementDatabaseConstants.ElementOrientationTable.TABLE_NAME}'",
            null
        )
        cursor.use {
            TestCase.assertTrue(cursor.moveToFirst())
            assertEquals(ElementDatabaseConstants.ElementOrientationTable.TABLE_NAME, cursor.getString(0))
        }
    }

}