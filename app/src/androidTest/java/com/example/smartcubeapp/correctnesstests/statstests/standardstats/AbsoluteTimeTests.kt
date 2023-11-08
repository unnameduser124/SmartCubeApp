package com.example.smartcubeapp.correctnesstests.statstests.standardstats

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.example.smartcubeapp.solvedatabase.SolvesDatabaseConstants
import com.example.smartcubeapp.solvedatabase.dataclasses.SolveData
import com.example.smartcubeapp.stats.StatsDBConstants
import com.example.smartcubeapp.stats.StatsService
import org.junit.AfterClass
import org.junit.Assert.assertEquals
import org.junit.BeforeClass
import org.junit.Test
import java.io.FileOutputStream

class AbsoluteTimeTests {

    @Test
    fun bestTimeTest() {
        val statsService = StatsService(context, SolvesDatabaseConstants.STATS_TESTS_DATABASE_NAME)
        val bestTime = statsService.bestTime()

        val expectedSolve = SolveData(
            id = 1,
            solveDuration = 7000,
            timestamp = 1698747280352,
            scramble = "F F F U R F B F F L L F U F B B F D R D",
            scrambledStateID = 1,
            moveCount = 60
        )

        assertEquals(bestTime, expectedSolve)
    }

    @Test
    fun worstTimeTest(){
        val statsService = StatsService(context, SolvesDatabaseConstants.STATS_TESTS_DATABASE_NAME)
        val worstTime = statsService.worstTime()

        val expectedSolve = SolveData(
            id = 4,
            solveDuration = 11000,
            timestamp = 1698747280368,
            scramble = "D D L F U R R L U F D B F F D F U F F B",
            scrambledStateID = 222,
            moveCount = 60
        )

        assertEquals(worstTime, expectedSolve)
    }

    @Test
    fun numberOfSolvesTest(){
        val statsService = StatsService(context, SolvesDatabaseConstants.STATS_TESTS_DATABASE_NAME)
        val numberOfSolves = statsService.totalNumberOfSolves()

        assertEquals(numberOfSolves, 4788)
    }

    @Test
    fun totalSolvingTimeTest(){
        val statsService = StatsService(context, SolvesDatabaseConstants.STATS_TESTS_DATABASE_NAME)
        val totalTime = statsService.totalSolvingTime()

        assertEquals(totalTime, 44289000)
    }

    @Test
    fun meanTimeTest(){
        val statsService = StatsService(context, SolvesDatabaseConstants.STATS_TESTS_DATABASE_NAME)
        val meanTime = statsService.meanTime()

        assertEquals(meanTime, 9250.0, 0.01)
    }

    @Test
    fun bestAverageTimeFor3SolvesTest(){
        val statsService = StatsService(context, SolvesDatabaseConstants.STATS_TESTS_DATABASE_NAME)
        val bestAverageTimeFor3Solves = statsService.bestAverageOf(3)

        assertEquals(bestAverageTimeFor3Solves, 9333.33, 0.01)
    }

    @Test
    fun bestAverageTimeFor5SolvesTest(){
        val statsService = StatsService(context, SolvesDatabaseConstants.STATS_TESTS_DATABASE_NAME)
        val bestAverageTimeFor5Solves = statsService.bestAverageOf(5)

        assertEquals(bestAverageTimeFor5Solves, 9400.0, 0.01)
    }

    @Test
    fun bestAverageTimeFor12SolvesTest(){
        val statsService = StatsService(context, SolvesDatabaseConstants.STATS_TESTS_DATABASE_NAME)
        val bestAverageTimeFor12Solves = statsService.bestAverageOf(12)

        assertEquals(bestAverageTimeFor12Solves, 9250.0, 0.01)
    }

    @Test
    fun bestAverageTimeFor50SolvesTest(){
        val statsService = StatsService(context, SolvesDatabaseConstants.STATS_TESTS_DATABASE_NAME)
        val bestAverageTimeFor50Solves = statsService.bestAverageOf(50)

        assertEquals(bestAverageTimeFor50Solves, 9300.0, 0.01)
    }

    @Test
    fun bestAverageTimeFor100SolvesTest(){
        val statsService = StatsService(context, SolvesDatabaseConstants.STATS_TESTS_DATABASE_NAME)
        val bestAverageTimeFor100Solves = statsService.bestAverageOf(100)

        assertEquals(bestAverageTimeFor100Solves, 9270.0, 0.01)
    }

    @Test
    fun bestAverageTimeFor500SolvesTest(){
        val statsService = StatsService(context, SolvesDatabaseConstants.STATS_TESTS_DATABASE_NAME)
        val bestAverageTimeFor500Solves = statsService.bestAverageOf(500)

        assertEquals(bestAverageTimeFor500Solves, 9254.0, 0.01)
    }

    @Test
    fun bestAverageTimeFor1000SolvesTest(){
        val statsService = StatsService(context, SolvesDatabaseConstants.STATS_TESTS_DATABASE_NAME)
        val bestAverageTimeFor1000Solves = statsService.bestAverageOf(1000)

        assertEquals(bestAverageTimeFor1000Solves, 9254.0, 0.01)
    }

    @Test
    fun standardDeviationTest(){
        val statsService = StatsService(context, SolvesDatabaseConstants.STATS_TESTS_DATABASE_NAME)
        val standardDeviation = statsService.standardDeviation()

        assertEquals(standardDeviation, 1479.019945774904, 0.01)
    }

    companion object {
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

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

        private fun copyStatsTestDB() {
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
            context.deleteDatabase(SolvesDatabaseConstants.STATS_TESTS_DATABASE_NAME)
            context.deleteDatabase(StatsDBConstants.TEST_DATABASE_NAME)
        }
    }
}