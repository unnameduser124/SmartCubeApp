package com.example.tests.correctnesstests.statstests.timetests

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.example.cube_database.solvedatabase.solvesDB.SolvesDatabaseConstants
import com.example.cube_database.solvedatabase.statsDB.StatsService
import com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase
import com.example.cube_detection.casedetection.plldetection.pllcase.PredefinedPLLCase
import com.example.cube_detection.phasedetection.SolvePhase
import org.junit.AfterClass
import org.junit.Assert.assertEquals
import org.junit.BeforeClass
import org.junit.Test
import java.io.FileOutputStream

class RelativeTimeTests {

    @Test
    fun averageTimeForCrossInLast3SolvesTest() {
        val averageTime =
            statsService.averageTimeForPhaseInLastXSolves(3, SolvePhase.Cross)
        assert(averageTime == 3000.0)
    }

    @Test
    fun averageTimeForF2LInLast5SolvesTest() {
        val averageTime =
            statsService.averageTimeForPhaseInLastXSolves(5, SolvePhase.F2L)
        assertEquals(2800.0, averageTime, 0.01)
    }

    @Test
    fun averageTimeForOLLInLast12SolvesTest() {
        val averageTime =
            statsService.averageTimeForPhaseInLastXSolves(12, SolvePhase.OLL)
        assert(averageTime == 2000.0)
    }

    @Test
    fun averageTimeForPLLInLast50SolvesTest() {
        val averageTime =
            statsService.averageTimeForPhaseInLastXSolves(50, SolvePhase.PLL)
        assert(averageTime == 2020.0)
    }

    @Test
    fun averageTimeForCrossInLast100SolvesTest() {
        val averageTime =
            statsService.averageTimeForPhaseInLastXSolves(100, SolvePhase.Cross)
        assert(averageTime == 2500.0)
    }

    @Test
    fun averageTimeForF2LInLast500SolvesTest() {
        val averageTime =
            statsService.averageTimeForPhaseInLastXSolves(500, SolvePhase.F2L)
        assert(averageTime == 2500.0)
    }

    @Test
    fun averageTimeForOLLInLast1000SolvesTest() {
        val averageTime =
            statsService.averageTimeForPhaseInLastXSolves(1000, SolvePhase.OLL)
        assertEquals(2001.0, averageTime, 0.01)
    }

    @Test
    fun averageTimeForPLLAbInLast3SolvesTest() {
        val averageTime =
            statsService.averageTimeForPLLCaseInLastXSolves(3, PredefinedPLLCase.Ab)
        assert(averageTime == 2000.0)
    }

    @Test
    fun averageTimeForPLLTInLast5SolvesTest(){
        val averageTime =
            statsService.averageTimeForPLLCaseInLastXSolves(5, PredefinedPLLCase.T)
        assert(averageTime == 1000.0)
    }

    @Test
    fun averageTimeForPLLZInLast12SolvesTest(){
        val averageTime =
            statsService.averageTimeForPLLCaseInLastXSolves(12, PredefinedPLLCase.Z)
        assert(averageTime == 3000.0)
    }

    @Test
    fun averageTimeForPLLGcInLast50SolvesTest(){
        val averageTime =
            statsService.averageTimeForPLLCaseInLastXSolves(50, PredefinedPLLCase.Gc)
        assert(averageTime == 1000.0)
    }

    @Test
    fun averageTimeForOLL01InLast3SolvesTest(){
        val averageTime =
            statsService.averageTimeForOLLCaseInLastXSolves(3, PredefinedOLLCase.OLL_01)
        assert(averageTime == 1000.0)
    }

    @Test
    fun averageTimeForOLL39InLast5Solves(){
        val averageTime =
            statsService.averageTimeForOLLCaseInLastXSolves(5, PredefinedOLLCase.OLL_39)
        assert(averageTime == 3000.0)
    }

    @Test
    fun averageTimeForOLL52InLast12Solves(){
        val averageTime =
            statsService.averageTimeForOLLCaseInLastXSolves(12, PredefinedOLLCase.OLL_52)
        assert(averageTime == 1000.0)
    }

    @Test
    fun averageTimeForOLL12InLast50Solves(){
        val averageTime =
            statsService.averageTimeForOLLCaseInLastXSolves(50, PredefinedOLLCase.OLL_12)
        assert(averageTime == 3000.0)
    }


    companion object {
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val statsService = StatsService(context, SolvesDatabaseConstants.STATS_TESTS_DATABASE_NAME)
        private fun copyDatabaseForStatsTests() {
            val dbPath = context.getDatabasePath(SolvesDatabaseConstants.STATS_TESTS_DATABASE_NAME)
            if (!dbPath.exists()) {
                val inputStream = context.assets.open(SolvesDatabaseConstants.STATS_TESTS_DATABASE_NAME)
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
        fun copyDatabase(){
            copyDatabaseForStatsTests()
        }

        @AfterClass
        @JvmStatic
        fun deleteDatabase(){
            statsService.close()
            context.deleteDatabase(SolvesDatabaseConstants.STATS_TESTS_DATABASE_NAME)
        }
    }
}