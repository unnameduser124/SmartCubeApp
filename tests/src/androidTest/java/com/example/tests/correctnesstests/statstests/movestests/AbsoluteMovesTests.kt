package com.example.tests.correctnesstests.statstests.movestests

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.example.cube_database.solvedatabase.SolvesDatabaseConstants
import com.example.cube_database.solvedatabase.stats.StatsDB
import com.example.cube_database.solvedatabase.stats.StatsDBConstants
import com.example.cube_database.solvedatabase.stats.StatsService
import org.junit.AfterClass
import org.junit.Assert
import org.junit.BeforeClass
import org.junit.Test
import java.io.FileOutputStream

class AbsoluteMovesTests {

    @Test
    fun bestAverageNumberOfMovesIn3Solves(){
        val statsDB = StatsDB(context, StatsDBConstants.TEST_DATABASE_NAME)
        val fieldName = StatsDBConstants.BEST_AVERAGE_NUMBER_OF_MOVES_IN_X_SOLVES
            .replace("X", "3")

        statsDB.updateFieldValue(fieldName, Double.MAX_VALUE.toString())

        val expectedValue = 70.0

        val result = statsService.bestAverageNumberOfMovesPerSolveInXSolves(3)
        Assert.assertEquals(expectedValue, result, 0.01)
    }

    @Test
    fun bestAverageNumberOfMovesIn5Solves(){
        val statsDB = StatsDB(context, StatsDBConstants.TEST_DATABASE_NAME)
        val fieldName = StatsDBConstants.BEST_AVERAGE_NUMBER_OF_MOVES_IN_X_SOLVES
            .replace("X", "5")

        statsDB.updateFieldValue(fieldName, Double.MAX_VALUE.toString())

        val expectedValue = 68.0

        val result = statsService.bestAverageNumberOfMovesPerSolveInXSolves(5)
        Assert.assertEquals(expectedValue, result, 0.01)
    }

    @Test
    fun bestAverageNumberOfMovesIn12Solves(){
        val statsDB = StatsDB(context, StatsDBConstants.TEST_DATABASE_NAME)
        val fieldName = StatsDBConstants.BEST_AVERAGE_NUMBER_OF_MOVES_IN_X_SOLVES
            .replace("X", "12")

        statsDB.updateFieldValue(fieldName, Double.MAX_VALUE.toString())

        val expectedValue = 70.83

        val result = statsService.bestAverageNumberOfMovesPerSolveInXSolves(12)
        Assert.assertEquals(expectedValue, result, 0.01)
    }

    @Test
    fun bestAverageNumberOfMovesIn50Solves(){
        val statsDB = StatsDB(context, StatsDBConstants.TEST_DATABASE_NAME)
        val fieldName = StatsDBConstants.BEST_AVERAGE_NUMBER_OF_MOVES_IN_X_SOLVES
            .replace("X", "50")

        statsDB.updateFieldValue(fieldName, Double.MAX_VALUE.toString())

        val expectedValue = 70.0

        val result = statsService.bestAverageNumberOfMovesPerSolveInXSolves(50)
        Assert.assertEquals(expectedValue, result, 0.01)
    }

    @Test
    fun bestAverageNumberOfMovesIn100Solves(){
        val statsDB = StatsDB(context, StatsDBConstants.TEST_DATABASE_NAME)
        val fieldName = StatsDBConstants.BEST_AVERAGE_NUMBER_OF_MOVES_IN_X_SOLVES
            .replace("X", "100")

        statsDB.updateFieldValue(fieldName, Double.MAX_VALUE.toString())

        val expectedValue = 70.1

        val result = statsService.bestAverageNumberOfMovesPerSolveInXSolves(100)
        Assert.assertEquals(expectedValue, result, 0.01)
    }

    @Test
    fun bestAverageNumberOfMovesIn500Solves(){
        val statsDB = StatsDB(context, StatsDBConstants.TEST_DATABASE_NAME)
        val fieldName = StatsDBConstants.BEST_AVERAGE_NUMBER_OF_MOVES_IN_X_SOLVES
            .replace("X", "500")

        statsDB.updateFieldValue(fieldName, Double.MAX_VALUE.toString())

        val expectedValue = 70.02

        val result = statsService.bestAverageNumberOfMovesPerSolveInXSolves(500)
        Assert.assertEquals(expectedValue, result, 0.01)
    }

    @Test
    fun bestAverageNumberOfMovesIn1000Solves(){
        val statsDB = StatsDB(context, StatsDBConstants.TEST_DATABASE_NAME)
        val fieldName = StatsDBConstants.BEST_AVERAGE_NUMBER_OF_MOVES_IN_X_SOLVES
            .replace("X", "1000")

        statsDB.updateFieldValue(fieldName, Double.MAX_VALUE.toString())

        val expectedValue = 70.02

        val result = statsService.bestAverageNumberOfMovesPerSolveInXSolves(1000)
        Assert.assertEquals(expectedValue, result, 0.01)
    }

    @Test
    fun bestAverageNumberOfMovesForCrossIn3Solves(){
        val statsDB = StatsDB(context, StatsDBConstants.TEST_DATABASE_NAME)
        val fieldName = StatsDBConstants.BEST_AVERAGE_NUMBER_OF_MOVES_FOR_CROSS_IN_X_SOLVES
            .replace("X", "3")

        statsDB.updateFieldValue(fieldName, Double.MAX_VALUE.toString())

        val expectedValue = 7.0

        val result = statsService.bestAverageNumberOfMovesForPhaseInXSolves(3, com.example.cube_detection.phasedetection.SolvePhase.Cross)
        Assert.assertEquals(expectedValue, result, 0.01)
    }

    @Test
    fun bestAverageNumberOfMovesForF2LIn5Solves(){
        val statsDB = StatsDB(context, StatsDBConstants.TEST_DATABASE_NAME)
        val fieldName = StatsDBConstants.BEST_AVERAGE_NUMBER_OF_MOVES_FOR_F2L_IN_X_SOLVES
            .replace("X", "5")

        statsDB.updateFieldValue(fieldName, Double.MAX_VALUE.toString())

        val expectedValue = 6.8

        val result = statsService.bestAverageNumberOfMovesForPhaseInXSolves(5, com.example.cube_detection.phasedetection.SolvePhase.F2L)
        Assert.assertEquals(expectedValue, result, 0.01)
    }

    @Test
    fun bestAverageNumberOfMovesForOLLIn50Solves(){
        val statsDB = StatsDB(context, StatsDBConstants.TEST_DATABASE_NAME)
        val fieldName = StatsDBConstants.BEST_AVERAGE_NUMBER_OF_MOVES_FOR_OLL_IN_X_SOLVES
            .replace("X", "50")

        statsDB.updateFieldValue(fieldName, Double.MAX_VALUE.toString())

        val expectedValue = 6.02

        val result = statsService.bestAverageNumberOfMovesForPhaseInXSolves(50, com.example.cube_detection.phasedetection.SolvePhase.OLL)
        Assert.assertEquals(expectedValue, result, 0.01)
    }

    @Test
    fun bestAverageNumberOfMovesForPLLIn500Solves(){
        val statsDB = StatsDB(context, StatsDBConstants.TEST_DATABASE_NAME)
        val fieldName = StatsDBConstants.BEST_AVERAGE_NUMBER_OF_MOVES_FOR_PLL_IN_X_SOLVES
            .replace("X", "500")

        statsDB.updateFieldValue(fieldName, Double.MAX_VALUE.toString())

        val expectedValue = 6.002

        val result = statsService.bestAverageNumberOfMovesForPhaseInXSolves(500, com.example.cube_detection.phasedetection.SolvePhase.PLL)
        Assert.assertEquals(expectedValue, result, 0.01)
    }

    @Test
    fun bestAverageNumberOfMovesForPLLUaIn5Solves(){
        val statsDB = StatsDB(context, StatsDBConstants.TEST_DATABASE_NAME)
        val fieldName = StatsDBConstants.BEST_AVERAGE_NUMBER_OF_MOVES_FOR_PLL_X_IN_Y_SOLVES
            .replace("X", "Ua")
            .replace("Y", "5")

        statsDB.updateFieldValue(fieldName, Double.MAX_VALUE.toString())

        val expectedValue = 6.0

        val result = statsService.bestAverageNumberOfMovesForPLLCaseInXSolves(5, com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase.Ua)
        Assert.assertEquals(expectedValue, result, 0.01)
    }

    @Test
    fun bestAverageNumberOfMovesForPLLHIn5Solves(){
        val statsDB = StatsDB(context, StatsDBConstants.TEST_DATABASE_NAME)
        val fieldName = StatsDBConstants.BEST_AVERAGE_NUMBER_OF_MOVES_FOR_PLL_X_IN_Y_SOLVES
            .replace("X", "H")
            .replace("Y", "5")

        statsDB.updateFieldValue(fieldName, Double.MAX_VALUE.toString())

        val expectedValue = 7.0

        val result = statsService.bestAverageNumberOfMovesForPLLCaseInXSolves(5, com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase.H)
        Assert.assertEquals(expectedValue, result, 0.01)
    }

    @Test
    fun bestAverageNumberOfMovesForPLLEIn12Solves(){
        val statsDB = StatsDB(context, StatsDBConstants.TEST_DATABASE_NAME)
        val fieldName = StatsDBConstants.BEST_AVERAGE_NUMBER_OF_MOVES_FOR_PLL_X_IN_Y_SOLVES
            .replace("X", "E")
            .replace("Y", "12")

        statsDB.updateFieldValue(fieldName, Double.MAX_VALUE.toString())

        val expectedValue = 7.0

        val result = statsService.bestAverageNumberOfMovesForPLLCaseInXSolves(12, com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase.E)
        Assert.assertEquals(expectedValue, result, 0.01)
    }

    @Test
    fun bestAverageNumberOfMovesForPLLJbIn50Solves(){
        val statsDB = StatsDB(context, StatsDBConstants.TEST_DATABASE_NAME)
        val fieldName = StatsDBConstants.BEST_AVERAGE_NUMBER_OF_MOVES_FOR_PLL_X_IN_Y_SOLVES
            .replace("X", "Jb")
            .replace("Y", "50")

        statsDB.updateFieldValue(fieldName, Double.MAX_VALUE.toString())

        val expectedValue = 6.0

        val result = statsService.bestAverageNumberOfMovesForPLLCaseInXSolves(50, com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase.Jb)
        Assert.assertEquals(expectedValue, result, 0.01)
    }

    @Test
    fun bestAverageNumberOfMovesForOLL7In3Solves(){
        val statsDB = StatsDB(context, StatsDBConstants.TEST_DATABASE_NAME)
        val fieldName = StatsDBConstants.BEST_AVERAGE_NUMBER_OF_MOVES_FOR_OLL_X_IN_Y_SOLVES
            .replace("OLLX", "OLL_07")
            .replace("Y", "3")

        statsDB.updateFieldValue(fieldName, Double.MAX_VALUE.toString())

        val expectedValue = 5.0

        val result = statsService.bestAverageNumberOfMovesForOLLCaseInXSolves(3, com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_07)
        Assert.assertEquals(expectedValue, result, 0.01)
    }

    @Test
    fun bestAverageNumberOfMovesForOLL24In5Solves(){
        val statsDB = StatsDB(context, StatsDBConstants.TEST_DATABASE_NAME)
        val fieldName = StatsDBConstants.BEST_AVERAGE_NUMBER_OF_MOVES_FOR_OLL_X_IN_Y_SOLVES
            .replace("OLLX", "OLL_24")
            .replace("Y", "5")

        statsDB.updateFieldValue(fieldName, Double.MAX_VALUE.toString())

        val expectedValue = 7.0

        val result = statsService.bestAverageNumberOfMovesForOLLCaseInXSolves(5, com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_24)
        Assert.assertEquals(expectedValue, result, 0.01)
    }

    @Test
    fun bestAverageNumberOfMovesForOLL8In12Solves(){
        val statsDB = StatsDB(context, StatsDBConstants.TEST_DATABASE_NAME)
        val fieldName = StatsDBConstants.BEST_AVERAGE_NUMBER_OF_MOVES_FOR_OLL_X_IN_Y_SOLVES
            .replace("OLLX", "OLL_08")
            .replace("Y", "12")

        statsDB.updateFieldValue(fieldName, Double.MAX_VALUE.toString())

        val expectedValue = 6.0

        val result = statsService.bestAverageNumberOfMovesForOLLCaseInXSolves(12, com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_08)
        Assert.assertEquals(expectedValue, result, 0.01)
    }

    @Test
    fun bestAverageNumberOfMovesForOLL4In50Solves(){
        val statsDB = StatsDB(context, StatsDBConstants.TEST_DATABASE_NAME)
        val fieldName = StatsDBConstants.BEST_AVERAGE_NUMBER_OF_MOVES_FOR_OLL_X_IN_Y_SOLVES
            .replace("OLLX", "OLL_04")
            .replace("Y", "50")

        statsDB.updateFieldValue(fieldName, Double.MAX_VALUE.toString())

        val expectedValue = 5.0

        val result = statsService.bestAverageNumberOfMovesForOLLCaseInXSolves(50, com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_04)
        Assert.assertEquals(expectedValue, result, 0.01)
    }

    @Test
    fun totalNumberOfMovesTest(){
        val expectedValue = 335160

        val result = statsService.totalNumberOfMoves()
        Assert.assertEquals(expectedValue, result)
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