package com.example.tests.correctnesstests.dbtests

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.example.cube_database.solvedatabase.statsDB.StatsDB
import com.example.cube_database.solvedatabase.statsDB.StatsDBConstants
import com.example.cube_database.solvedatabase.statsDB.StatsDBConstants.numberOfSolvesValues
import com.example.cube_database.solvedatabase.statsDB.StatsDBConstants.statsNamesList
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Test

class StatsDBTests {

    private lateinit var context: Context
    private lateinit var dbService: StatsDB

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        dbService = StatsDB(context, StatsDBConstants.TEST_DATABASE_NAME)
    }

    @After
    fun tearDown() {
        dbService.close()
        context.deleteDatabase(StatsDBConstants.TEST_DATABASE_NAME)
    }

    @Test
    fun createStatsTableTest() {
        val cursor = dbService.readableDatabase.rawQuery(
            "SELECT name " +
                    "FROM sqlite_master WHERE type='table' " +
                    "AND name='${StatsDBConstants.StatsTable.TABLE_NAME}'",
            null
        )
        cursor.use {
            TestCase.assertTrue(cursor.moveToFirst())
            TestCase.assertEquals(
                StatsDBConstants.StatsTable.TABLE_NAME,
                cursor.getString(0)
            )
        }
    }

    @Test
    fun databasePopulatedWithStats() {
        val cursor = dbService.readableDatabase.rawQuery(
            "SELECT * FROM ${StatsDBConstants.StatsTable.TABLE_NAME}",
            null
        )

        cursor.use {
            TestCase.assertTrue(cursor.moveToFirst())
            val expectedStats = getExpectedStats()
            val statsNames = mutableListOf<String>()

            do {
                statsNames.add(cursor.getString(0))
            } while (cursor.moveToNext())

            val statsNotInStatsNames = expectedStats.filter { !statsNames.contains(it) }
            statsNotInStatsNames.forEach{
                println(it)
            }
            TestCase.assertEquals(expectedStats.size, statsNames.size)
            for (stat in expectedStats) {
                TestCase.assertTrue(statsNames.contains(stat))
            }
        }
    }

    @Test
    fun getFieldValueTest() {
        val cursor = dbService.readableDatabase.rawQuery(
            "SELECT * FROM ${StatsDBConstants.StatsTable.TABLE_NAME}",
            null
        )

        cursor.use {
            TestCase.assertTrue(cursor.moveToFirst())
            do {
                val statName =
                    cursor.getString(cursor.getColumnIndexOrThrow(StatsDBConstants.StatsTable.STATISTIC_NAME_COLUMN))
                val statValue =
                    cursor.getString(cursor.getColumnIndexOrThrow(StatsDBConstants.StatsTable.STATISTIC_VALUE_COLUMN))
                val expectedValue = dbService.getFieldValue(statName)
                TestCase.assertEquals(statValue.toDouble(), expectedValue)
            } while (cursor.moveToNext())
        }
    }

    @Test
    fun updateFieldValueTest() {
        val cursor = dbService.readableDatabase.rawQuery(
            "SELECT * FROM ${StatsDBConstants.StatsTable.TABLE_NAME}",
            null
        )

        cursor.use {
            TestCase.assertTrue(cursor.moveToFirst())
            do {
                val statName =
                    cursor.getString(cursor.getColumnIndexOrThrow(StatsDBConstants.StatsTable.STATISTIC_NAME_COLUMN))
                val statValue =
                    cursor.getString(cursor.getColumnIndexOrThrow(StatsDBConstants.StatsTable.STATISTIC_VALUE_COLUMN))
                val newValue = statValue.toDouble() + 1
                dbService.updateFieldValue(statName, newValue.toString())
                val expectedValue = dbService.getFieldValue(statName)
                TestCase.assertEquals(newValue, expectedValue)
            } while (cursor.moveToNext())
        }
    }

    private fun getExpectedStats(): List<String> {
        val expectedStats = mutableListOf<String>()
        for (name in statsNamesList) {
            if (name.contains('X') && !name.contains('Y')) {
                for (numberOfSolves in numberOfSolvesValues) {
                    expectedStats.add(name.replace("X", numberOfSolves.toString()))
                }
            } else if (name.contains('X') && name.contains('Y')) {
                //sublist in for loops to avoid OLLSkip and PLLSkip enum values
                if (name.contains("PLL")) {
                    for (pll in com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase.values().toMutableList().subList(0, 21)) {
                        for (numberOfSolves in numberOfSolvesValues) {
                            var modifiedName = name.replace("Y", numberOfSolves.toString())
                            modifiedName = modifiedName.replace("X", pll.name)
                            expectedStats.add(modifiedName)
                        }
                    }
                } else if (name.contains("OLL")) {
                    for (oll in com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.values().toMutableList().subList(0, 57)) {
                        for (numberOfSolves in numberOfSolvesValues) {
                            var modifiedName = name.replace("Y", numberOfSolves.toString())
                            modifiedName = modifiedName.replace("OLLX", oll.name)
                            expectedStats.add(modifiedName)
                        }
                    }
                }
            } else {
                expectedStats.add(name)
            }
        }
        return expectedStats
    }
}