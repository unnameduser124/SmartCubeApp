package com.example.smartcubeapp.correctnesstests.statstests.movestests

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.example.smartcubeapp.casedetection.olldetection.ollcase.PredefinedOLLCase
import com.example.smartcubeapp.casedetection.plldetection.pllcase.PredefinedPLLCase
import com.example.smartcubeapp.phasedetection.SolvePhase
import com.example.smartcubeapp.solvedatabase.SolvesDatabaseConstants
import com.example.smartcubeapp.stats.StatsService
import org.junit.After
import org.junit.AfterClass
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import java.io.FileOutputStream

class RelativeMovesTests {

    @Test
    fun averageNumberOfMovesForCrossPhaseInLast3SolvesTest(){
        val averageNumberOfMoves =
            statsService.averageNumberOfMovesForPhaseInLastXSolves(3, SolvePhase.Cross)
        assert(averageNumberOfMoves == 7.0)
    }

    @Test
    fun averageNumberOfMovesForF2LPhaseInLast12SolvesTest(){
        val averageNumberOfMoves =
            statsService.averageNumberOfMovesForPhaseInLastXSolves(12, SolvePhase.F2L)
        assert(averageNumberOfMoves == 6.5)
    }

    @Test
    fun averageNumberOfMovesForOLLPhaseInLast100SolvesTest(){
        val averageNumberOfMoves =
            statsService.averageNumberOfMovesForPhaseInLastXSolves(100, SolvePhase.OLL)
        assert(averageNumberOfMoves == 6.01)
    }

    @Test
    fun averageNumberOfMovesForPLLPhaseInLast1000SolvesTest(){
        val averageNumberOfMoves =
            statsService.averageNumberOfMovesForPhaseInLastXSolves(1000, SolvePhase.PLL)
        assert(averageNumberOfMoves == 6.001)
    }

    @Test
    fun averageNumberOfMovesForPLLAInLast3SolvesTest(){
        val averageNumberOfMoves =
            statsService.averageNumberOfMovesForPLLCaseInLastXSolves(3, PredefinedPLLCase.Aa)
        assert(averageNumberOfMoves == 5.0)
    }

    @Test
    fun averageNumberOfMoveForPLLTInLast5SolvesTest(){
        val averageNumberOfMoves =
            statsService.averageNumberOfMovesForPLLCaseInLastXSolves(5, PredefinedPLLCase.T)
        assert(averageNumberOfMoves == 5.0)
    }

    @Test
    fun averageNumberOfMovesForPLLZInLast12SolvesTest(){
        val averageNumberOfMoves =
            statsService.averageNumberOfMovesForPLLCaseInLastXSolves(12, PredefinedPLLCase.Z)
        assert(averageNumberOfMoves == 7.0)
    }

    @Test
    fun averageNumberOfMovesForPLLGbInLast50SolvesTest(){
        val averageNumberOfMoves =
            statsService.averageNumberOfMovesForPLLCaseInLastXSolves(50, PredefinedPLLCase.Gb)
        assert(averageNumberOfMoves == 7.0)
    }

    @Test
    fun averageNumberOfMovesForOLL33InLast3SolvesTest(){
        val averageNumberOfMoves =
            statsService.averageNumberOfMovesForOLLCaseInLastXSolves(3, PredefinedOLLCase.OLL_33)
        assert(averageNumberOfMoves == 7.0)
    }

    @Test
    fun averageNumberOfMovesForOLL36InLast5SolvesTest(){
        val averageNumberOfMoves =
            statsService.averageNumberOfMovesForOLLCaseInLastXSolves(5, PredefinedOLLCase.OLL_36)
        assert(averageNumberOfMoves == 7.0)
    }

    @Test
    fun averageNumberOfMovesForOLL12InLast12MovesTest(){
        val averageNumberOfMoves =
            statsService.averageNumberOfMovesForOLLCaseInLastXSolves(12, PredefinedOLLCase.OLL_12)
        assert(averageNumberOfMoves == 7.0)
    }

    @Test
    fun averageNumberOfMovesForOLL20InLast50SolvesTest(){
        val averageNumberOfMoves =
            statsService.averageNumberOfMovesForOLLCaseInLastXSolves(50, PredefinedOLLCase.OLL_20)
        assert(averageNumberOfMoves == 6.0)
    }

    @Test
    fun averageNumberOfMovesPerSolveInLast3Solves(){
        val averageNumberOfMoves =
            statsService.averageNumberOfMovesPerSolveInLastXSolves(3)
        assert(averageNumberOfMoves == 70.0)
    }

    @Test
    fun averageNumberOfMovesPerSolveInLast5Solves(){
        val averageNumberOfMoves =
            statsService.averageNumberOfMovesPerSolveInLastXSolves(5)
        assert(averageNumberOfMoves == 68.0)
    }

    @Test
    fun averageNumberOfMovesPerSolveInLast12Solves(){
        val averageNumberOfMoves =
            statsService.averageNumberOfMovesPerSolveInLastXSolves(12)
        assertEquals(averageNumberOfMoves, 70.8333, 0.01)
    }

    @Test
    fun averageNumberOfMovesPerSolveInLast50Solves(){
        val averageNumberOfMoves =
            statsService.averageNumberOfMovesPerSolveInLastXSolves(50)
        assertEquals(averageNumberOfMoves, 70.0, 0.01)
    }

    @Test
    fun averageNumberOfMovesPerSolveInLast100Solves(){
        val averageNumberOfMoves =
            statsService.averageNumberOfMovesPerSolveInLastXSolves(100)
        assertEquals(averageNumberOfMoves, 70.1, 0.01)
    }

    @Test
    fun averageNumberOfMovesPerSolveInLast500Solves(){
        val averageNumberOfMoves =
            statsService.averageNumberOfMovesPerSolveInLastXSolves(500)
        assertEquals(averageNumberOfMoves, 70.02, 0.01)
    }

    @Test
    fun averageNumberOfMovesPerSolveInLast1000Solves(){
        val averageNumberOfMoves =
            statsService.averageNumberOfMovesPerSolveInLastXSolves(1000)
        assertEquals(averageNumberOfMoves, 70.02, 0.01)
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