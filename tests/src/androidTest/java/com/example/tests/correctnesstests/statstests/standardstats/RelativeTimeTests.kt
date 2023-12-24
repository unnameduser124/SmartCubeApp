package com.example.tests.correctnesstests.statstests.standardstats

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.example.cube_database.solvedatabase.SolvesDatabaseConstants
import com.example.cube_database.solvedatabase.stats.StatsDBConstants
import com.example.cube_database.solvedatabase.stats.StatsService
import org.junit.AfterClass
import org.junit.Assert.assertEquals
import org.junit.BeforeClass
import org.junit.Test
import java.io.FileOutputStream

class RelativeTimeTests {

    @Test
    fun averageTimeFor3LastSolvesTest(){
        val statsService = StatsService(context, SolvesDatabaseConstants.STATS_TESTS_DATABASE_NAME)
        val averageTimeFor3LastSolves = statsService.averageOf(3)

        assertEquals(averageTimeFor3LastSolves, 9333.33, 0.01)
    }

    @Test
    fun averageTimeForLast5SolvesTest(){
        val statsService = StatsService(context, SolvesDatabaseConstants.STATS_TESTS_DATABASE_NAME)
        val averageTimeForLast5Solves = statsService.averageOf(5)

        assertEquals(averageTimeForLast5Solves, 9400.0, 0.01)
    }

    @Test
    fun averageTimeForLast12SolvesTest(){
        val statsService = StatsService(context, SolvesDatabaseConstants.STATS_TESTS_DATABASE_NAME)
        val averageTimeForLast12Solves = statsService.averageOf(12)

        assertEquals(averageTimeForLast12Solves, 9250.0, 0.01)
    }

    @Test
    fun averageTimeForLast50SolvesTest(){
        val statsService = StatsService(context, SolvesDatabaseConstants.STATS_TESTS_DATABASE_NAME)
        val averageTimeForLast50Solves = statsService.averageOf(50)

        assertEquals(averageTimeForLast50Solves, 9300.0, 0.01)
    }

    @Test
    fun averageTimeForLast100SolvesTest(){
        val statsService = StatsService(context, SolvesDatabaseConstants.STATS_TESTS_DATABASE_NAME)
        val averageTimeForLast100Solves = statsService.averageOf(100)

        assertEquals(averageTimeForLast100Solves, 9270.0, 0.01)
    }

    @Test
    fun averageTimeForLast500SolvesTest(){
        val statsService = StatsService(context, SolvesDatabaseConstants.STATS_TESTS_DATABASE_NAME)
        val averageTimeForLast500Solves = statsService.averageOf(500)

        assertEquals(averageTimeForLast500Solves, 9254.0, 0.01)
    }

    @Test
    fun averageTimeForLast1000SolvesTest(){
        val statsService = StatsService(context, SolvesDatabaseConstants.STATS_TESTS_DATABASE_NAME)
        val averageTimeForLast1000Solves = statsService.averageOf(1000)

        assertEquals(averageTimeForLast1000Solves, 9254.0, 0.01)
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
            context.deleteDatabase(SolvesDatabaseConstants.STATS_TESTS_DATABASE_NAME)
            context.deleteDatabase(StatsDBConstants.TEST_DATABASE_NAME)
        }
    }
}