package com.example.tests.correctnesstests.statstests.timetests

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.example.cube_database.solvedatabase.solvesDB.SolvesDatabaseConstants
import com.example.cube_database.solvedatabase.statsDB.StatsDB
import com.example.cube_database.solvedatabase.statsDB.StatsDBConstants
import com.example.cube_database.solvedatabase.statsDB.StatsService
import com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase
import com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase
import com.example.cube_detection.phasedetection.SolvePhase
import org.junit.AfterClass
import org.junit.Assert.assertEquals
import org.junit.BeforeClass
import org.junit.Test
import java.io.FileOutputStream

class AbsoluteTimeTests {


    @Test
    fun bestAverageTimeForPLLPhaseIn3SolvesTest() {
        val statsDB = StatsDB(context, StatsDBConstants.TEST_DATABASE_NAME)
        val fieldName = StatsDBConstants.BEST_AVERAGE_TIME_FOR_PLL_PHASE_IN_X_SOLVES
            .replace("X", "3")

        statsDB.updateFieldValue(fieldName, Double.MAX_VALUE.toString())

        val expectedValue = 2000.0

        val result = statsService.bestAverageTimeForPhaseInXSolves(3, SolvePhase.PLL)
        assertEquals(expectedValue, result, 0.01)
    }

    @Test
    fun bestAverageTimeForCrossPhaseIn5SolvesTest() {
        val statsDB = StatsDB(context, StatsDBConstants.TEST_DATABASE_NAME)
        val fieldName = StatsDBConstants.BEST_AVERAGE_TIME_FOR_CROSS_PHASE_IN_X_SOLVES
            .replace("X", "5")

        statsDB.updateFieldValue(fieldName, Double.MAX_VALUE.toString())

        val expectedValue = 2800.0

        val result = statsService.bestAverageTimeForPhaseInXSolves(5, SolvePhase.Cross)
        assertEquals(expectedValue, result, 0.01)
    }

    @Test
    fun bestAverageTimeForF2lPhaseIn12SolvesTest() {
        val statsDB = StatsDB(context, StatsDBConstants.TEST_DATABASE_NAME)
        val fieldName = StatsDBConstants.BEST_AVERAGE_TIME_FOR_F2L_PHASE_IN_X_SOLVES
            .replace("X", "12")

        statsDB.updateFieldValue(fieldName, Double.MAX_VALUE.toString())

        val expectedValue = 2500.0

        val result = statsService.bestAverageTimeForPhaseInXSolves(12, SolvePhase.F2L)
        assertEquals(expectedValue, result, 0.01)
    }

    @Test
    fun bestAverageTimeForOLLPhaseIn50SolvesTest() {
        val statsDB = StatsDB(context, StatsDBConstants.TEST_DATABASE_NAME)
        val fieldName = StatsDBConstants.BEST_AVERAGE_TIME_FOR_OLL_PHASE_IN_X_SOLVES
            .replace("X", "50")

        statsDB.updateFieldValue(fieldName, Double.MAX_VALUE.toString())

        val expectedValue = 2020.0

        val result = statsService.bestAverageTimeForPhaseInXSolves(50, SolvePhase.OLL)
        assertEquals(expectedValue, result, 0.01)
    }

    @Test
    fun bestAverageTimeForPLLPhaseIn100SolvesTest() {
        val statsDB = StatsDB(context, StatsDBConstants.TEST_DATABASE_NAME)
        val fieldName = StatsDBConstants.BEST_AVERAGE_TIME_FOR_PLL_PHASE_IN_X_SOLVES
            .replace("X", "100")

        statsDB.updateFieldValue(fieldName, Double.MAX_VALUE.toString())

        val expectedValue = 2010.0

        val result = statsService.bestAverageTimeForPhaseInXSolves(100, SolvePhase.PLL)
        assertEquals(expectedValue, result, 0.01)
    }

    @Test
    fun bestAverageTimeForCrossPhaseIn500SolvesTest() {
        val statsDB = StatsDB(context, StatsDBConstants.TEST_DATABASE_NAME)
        val fieldName = StatsDBConstants.BEST_AVERAGE_TIME_FOR_CROSS_PHASE_IN_X_SOLVES
            .replace("X", "500")

        statsDB.updateFieldValue(fieldName, Double.MAX_VALUE.toString())

        val expectedValue = 2500.0

        val result = statsService.bestAverageTimeForPhaseInXSolves(500, SolvePhase.Cross)
        assertEquals(expectedValue, result, 0.01)
    }

    @Test
    fun bestAverageTimeForF2lPhaseIn1000SolvesTest() {
        val statsDB = StatsDB(context, StatsDBConstants.TEST_DATABASE_NAME)
        val fieldName = StatsDBConstants.BEST_AVERAGE_TIME_FOR_F2L_PHASE_IN_X_SOLVES
            .replace("X", "1000")

        statsDB.updateFieldValue(fieldName, Double.MAX_VALUE.toString())

        val expectedValue = 2500.0

        val result = statsService.bestAverageTimeForPhaseInXSolves(1000, SolvePhase.F2L)
        assertEquals(expectedValue, result, 0.01)
    }

    @Test
    fun bestAverageTimeForPLLUaIn3SolvesTest() {
        val statsDB = StatsDB(context, StatsDBConstants.TEST_DATABASE_NAME)
        val fieldName = StatsDBConstants.BEST_AVERAGE_TIME_FOR_PLL_X_IN_Y_SOLVES
            .replace("Y", "3").replace("X", "Ua")

        statsDB.updateFieldValue(fieldName, Double.MAX_VALUE.toString())

        val expectedValue = 2000.0

        val result = statsService.bestAverageTimeForPLLCaseInXSolves(3, PredefinedPLLCase.Ua)
        assertEquals(expectedValue, result, 0.01)
    }

    @Test
    fun bestAverageTimeForPLLRbIn5SolvesTest() {
        val statsDB = StatsDB(context, StatsDBConstants.TEST_DATABASE_NAME)
        val fieldName = StatsDBConstants.BEST_AVERAGE_TIME_FOR_PLL_X_IN_Y_SOLVES
            .replace("Y", "5").replace("X", "Rb")

        statsDB.updateFieldValue(fieldName, Double.MAX_VALUE.toString())

        val expectedValue = 3000.0

        val result = statsService.bestAverageTimeForPLLCaseInXSolves(5, PredefinedPLLCase.Rb)
        assertEquals(expectedValue, result, 0.01)
    }

    @Test
    fun bestAverageTimeForPLLVIn12SolvesTest() {
        val statsDB = StatsDB(context, StatsDBConstants.TEST_DATABASE_NAME)
        val fieldName = StatsDBConstants.BEST_AVERAGE_TIME_FOR_PLL_X_IN_Y_SOLVES
            .replace("Y", "12").replace("X", "V")

        statsDB.updateFieldValue(fieldName, Double.MAX_VALUE.toString())

        val expectedValue = 1000.0

        val result = statsService.bestAverageTimeForPLLCaseInXSolves(12, PredefinedPLLCase.V)
        assertEquals(expectedValue, result, 0.01)
    }

    @Test
    fun bestAverageTimeForPLLHIn50SolvesTest() {
        val statsDB = StatsDB(context, StatsDBConstants.TEST_DATABASE_NAME)
        val fieldName = StatsDBConstants.BEST_AVERAGE_TIME_FOR_PLL_X_IN_Y_SOLVES
            .replace("Y", "50").replace("X", "H")

        statsDB.updateFieldValue(fieldName, Double.MAX_VALUE.toString())

        val expectedValue = 3000.0

        val result = statsService.bestAverageTimeForPLLCaseInXSolves(50, PredefinedPLLCase.H)
        assertEquals(expectedValue, result, 0.01)
    }

    @Test
    fun bestAverageTimeForOLL47In3SolvesTest() {
        val statsDB = StatsDB(context, StatsDBConstants.TEST_DATABASE_NAME)
        val fieldName = StatsDBConstants.BEST_AVERAGE_TIME_FOR_OLL_X_IN_Y_SOLVES
            .replace("Y", "3").replace("OLLX", "OLL_47")

        statsDB.updateFieldValue(fieldName, Double.MAX_VALUE.toString())

        val expectedValue = 2000.0

        val result = statsService.bestAverageTimeForOLLCaseInXSolves(3, PredefinedOLLCase.OLL_47)
        assertEquals(expectedValue, result, 0.01)
    }

    @Test
    fun bestAverageTimeForOLL22In5Solves(){
        val statsDB = StatsDB(context, StatsDBConstants.TEST_DATABASE_NAME)
        val fieldName = StatsDBConstants.BEST_AVERAGE_TIME_FOR_OLL_X_IN_Y_SOLVES
            .replace("Y", "5").replace("OLLX", "OLL_22")

        statsDB.updateFieldValue(fieldName, Double.MAX_VALUE.toString())

        val expectedValue = 1000.0

        val result = statsService.bestAverageTimeForOLLCaseInXSolves(5, PredefinedOLLCase.OLL_22)
        assertEquals(expectedValue, result, 0.01)
    }

    @Test
    fun bestAverageTimeForOLL9In12Solves(){
        val statsDB = StatsDB(context, StatsDBConstants.TEST_DATABASE_NAME)
        val fieldName = StatsDBConstants.BEST_AVERAGE_TIME_FOR_OLL_X_IN_Y_SOLVES
            .replace("Y", "12").replace("OLLX", "OLL_09")

        statsDB.updateFieldValue(fieldName, Double.MAX_VALUE.toString())

        val expectedValue = 3000.0

        val result = statsService.bestAverageTimeForOLLCaseInXSolves(12, PredefinedOLLCase.OLL_09)
        assertEquals(expectedValue, result, 0.01)
    }

    @Test
    fun bestAverageTimeForOLL30In50Solves(){
        val statsDB = StatsDB(context, StatsDBConstants.TEST_DATABASE_NAME)
        val fieldName = StatsDBConstants.BEST_AVERAGE_TIME_FOR_OLL_X_IN_Y_SOLVES
            .replace("Y", "50").replace("OLLX", "OLL_30")

        statsDB.updateFieldValue(fieldName, Double.MAX_VALUE.toString())

        val expectedValue = 3000.0

        val result = statsService.bestAverageTimeForOLLCaseInXSolves(50, PredefinedOLLCase.OLL_30)
        assertEquals(expectedValue, result, 0.01)
    }


    companion object {
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        private val statsService =
            StatsService(context, SolvesDatabaseConstants.STATS_TESTS_DATABASE_NAME)

        private fun copyDatabaseForStatsTests() {
            val dbPath = context.getDatabasePath(SolvesDatabaseConstants.STATS_TESTS_DATABASE_NAME)
            if (!dbPath.exists()) {
                val inputStream =
                    context.assets.open(SolvesDatabaseConstants.STATS_TESTS_DATABASE_NAME)
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

        private fun copyStatsTestDB(){
            val dbPath = context.getDatabasePath(StatsDBConstants.TEST_DATABASE_NAME)
            if (!dbPath.exists()) {
                val inputStream =
                    context.assets.open(StatsDBConstants.TEST_DATABASE_NAME)
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

        @BeforeClass
        @JvmStatic
        fun copyDatabase() {
            copyDatabaseForStatsTests()
            copyStatsTestDB()
        }

        @AfterClass
        @JvmStatic
        fun deleteDatabase() {
            statsService.close()
            context.deleteDatabase(SolvesDatabaseConstants.STATS_TESTS_DATABASE_NAME)
            context.deleteDatabase(StatsDBConstants.TEST_DATABASE_NAME)
        }
    }
}