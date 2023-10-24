package com.example.smartcubeapp.correctnesstests.dbtests.solvedatabasetests

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.example.smartcubeapp.casedetection.olldetection.ollcase.PredefinedOLLCase
import com.example.smartcubeapp.casedetection.plldetection.pllcase.PredefinedPLLCase
import com.example.smartcubeapp.elementdatabase.ElementDatabaseConstants
import com.example.smartcubeapp.solvedatabase.SolveDB
import com.example.smartcubeapp.solvedatabase.SolvesDatabaseConstants
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Test

class SolveDBTests {
    private lateinit var appContext: Context
    private lateinit var dbService: SolveDB
    private val statsNamesList = listOf(
        "bestAverageTimeForCrossPhaseInXSolves",
        "bestAverageTimeForF2LPhaseInXSolves",
        "bestAverageTimeForOLLPhaseInXSolves",
        "bestAverageTimeForPLLPhaseInXSolves",
        "bestAverageTimeForSolveInXSolves",
        "bestAverageTimeForPLLXInYSolves",
        "bestAverageTimeForOLLXInYSolves",
        "bestAverageNumberOfMovesInXSolves",
        "bestAverageNumberOfMovesForCrossInXSolves",
        "bestAverageNumberOfMovesForF2LInXSolves",
        "bestAverageNumberOfMovesForOLLInXSolves",
        "bestAverageNumberOfMovesForPLLInXSolves",
        "bestAverageNumberOfMovesForPLLXInYSolves",
        "bestAverageNumberOfMovesForOLLXInYSolves",
        "totalNumberOfMoves"
    )

    private val numberOfSolvesValues = listOf(3, 5, 12, 50, 100, 500, 1000)

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
    fun createStatsTableTest(){
        val cursor = dbService.readableDatabase.rawQuery(
            "SELECT name " +
                    "FROM sqlite_master WHERE type='table' " +
                    "AND name='${SolvesDatabaseConstants.StatsTable.TABLE_NAME}'",
            null
        )
        cursor.use {
            assertTrue(cursor.moveToFirst())
            assertEquals(
                SolvesDatabaseConstants.StatsTable.TABLE_NAME,
                cursor.getString(0)
            )
        }
    }

    @Test
    fun populateStatsTableTest(){
        val expectedStats = getExpectedStats()
        assertEquals(1163, expectedStats.size)
        val cursor = dbService.readableDatabase.rawQuery(
            "SELECT * " +
                    "FROM ${SolvesDatabaseConstants.StatsTable.TABLE_NAME}",
            null
        )
        val stats = mutableListOf<String>()
        with(cursor){
            while(moveToNext()){
                val statName = getString(getColumnIndexOrThrow(SolvesDatabaseConstants.StatsTable.STATISTIC_NAME_COLUMN))
                stats.add(statName)
                assertEquals(0.0, getString(getColumnIndexOrThrow(SolvesDatabaseConstants.StatsTable.STATISTIC_VALUE_COLUMN)).toDouble())
            }
        }
        for(stat in expectedStats){
            assert(stats.contains(stat))
        }
        cursor.close()
    }


    private fun getExpectedStats(): List<String>{
        val expectedStats = mutableListOf<String>()
        for(name in statsNamesList){
            if(name.contains('X') && !name.contains('Y')){
                for(numberOfSolves in numberOfSolvesValues){
                    expectedStats.add(name.replace("X", numberOfSolves.toString()))
                }
            }
            else if(name.contains('X') && name.contains('Y')) {
                if(name.contains("PLL")){
                    for(pll in PredefinedPLLCase.values()){
                        for(numberOfSolves in numberOfSolvesValues){
                            var modifiedName = name.replace("Y", numberOfSolves.toString())
                            modifiedName = modifiedName.replace("X", pll.name)
                            expectedStats.add(modifiedName)
                        }
                    }
                }
                else if(name.contains("OLL")){
                    for(oll in PredefinedOLLCase.values()){
                        for(numberOfSolves in numberOfSolvesValues){
                            var modifiedName = name.replace("Y", numberOfSolves.toString())
                            modifiedName = modifiedName.replace("OLLX", oll.name)
                            expectedStats.add(modifiedName)
                        }
                    }
                }
            }
            else{
                expectedStats.add(name)
            }
        }
        return expectedStats
    }
}