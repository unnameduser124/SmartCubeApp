package com.example.smartcubeapp.correctnesstests.dbtests

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.example.smartcubeapp.cube.CubeState
import com.example.smartcubeapp.cube.Solve
import com.example.smartcubeapp.cube.SolveStatus
import com.example.smartcubeapp.phasedetection.CubeStatePhaseDetection
import com.example.smartcubeapp.phasedetection.SolutionPhaseDetection
import com.example.smartcubeapp.simpleSolveStateSequence
import com.example.smartcubeapp.solvedatabase.SolvesDatabaseConstants
import com.example.smartcubeapp.solvedatabase.dataclasses.CubeStateData
import com.example.smartcubeapp.solvedatabase.services.CrossDBService
import com.example.smartcubeapp.solvedatabase.services.CubeStateDBService
import com.example.smartcubeapp.solvedatabase.services.F2LDBService
import com.example.smartcubeapp.solvedatabase.services.OLLDBService
import com.example.smartcubeapp.solvedatabase.services.PLLDBService
import com.example.smartcubeapp.solvedatabase.services.SolveAnalysisDBService
import com.example.smartcubeapp.solvedatabase.services.SolveDBService
import junit.framework.TestCase
import org.junit.Assert.assertNotNull
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test
import java.util.Calendar

class SolveAnalysisDBServiceTests {

    private lateinit var context: Context
    private lateinit var solveAnalysisDBService: SolveAnalysisDBService

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        solveAnalysisDBService =
            SolveAnalysisDBService(context, SolvesDatabaseConstants.TEST_DATABASE_NAME)
    }

    @After
    fun tearDown() {
        solveAnalysisDBService.close()
        context.deleteDatabase(SolvesDatabaseConstants.TEST_DATABASE_NAME)
    }

    @Test
    fun saveSolveWithAnalysisTest() {
        val solve = Solve(
            1000,
            Calendar.getInstance(),
            simpleSolveStateSequence(),
            simpleSolveStateSequence().first(),
            SolveStatus.Solved,
            Calendar.getInstance().timeInMillis,
            scrambleSequence = "R U R' U'"
        )

        val solveID = solveAnalysisDBService.saveSolveWithAnalysis(solve).solveID

        val solveFromDatabase =
            SolveDBService(context, SolvesDatabaseConstants.TEST_DATABASE_NAME).getSolve(solveID)
        assertNotNull(solveFromDatabase)

        val cubeStatesFromDatabase =
            CubeStateDBService(context, SolvesDatabaseConstants.TEST_DATABASE_NAME)
                .getCubeStatesForSolve(solveID)
        assert(cubeStatesFromDatabase.size == solve.solveStateSequence.size)

        val f2lDataFromDatabase = F2LDBService(
            context,
            SolvesDatabaseConstants.TEST_DATABASE_NAME
        ).getF2LForSolve(solveID)
        assertNotNull(f2lDataFromDatabase)

        val ollDataFromDatabase = OLLDBService(
            context,
            SolvesDatabaseConstants.TEST_DATABASE_NAME
        ).getOLLForSolve(solveID)
        assertNotNull(ollDataFromDatabase)

        val pllDataFromDatabase = PLLDBService(
            context,
            SolvesDatabaseConstants.TEST_DATABASE_NAME
        ).getPLLForSolve(solveID)
        assertNotNull(pllDataFromDatabase)

        val crossDataFromDatabase =
            CrossDBService(context, SolvesDatabaseConstants.TEST_DATABASE_NAME).getCrossForSolve(
                solveID
            )
        assertNotNull(crossDataFromDatabase)
    }

    @Test
    fun saveSolveWithAnalysisFailEmptySolveStateSequence() {
        val randomCubeState = CubeState(
            mutableListOf(0, 6, 2, 3, 4, 1, 5, 7),
            mutableListOf(3, 3, 3, 3, 3, 2, 2, 3),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11),
            mutableListOf(
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false
            )
        )

        val solve = Solve(
            1000,
            Calendar.getInstance(),
            mutableListOf(),
            randomCubeState,
            SolveStatus.Solved,
            0,
            scrambleSequence = "R U R' U'"
        )
        assertThrows(IllegalArgumentException::class.java) {
            solve.calculateTimeFromStateSequence()
        }

        assertThrows(IllegalArgumentException::class.java) {
            solveAnalysisDBService.saveSolveWithAnalysis(solve).solveID
        }
    }

    @Test
    fun saveSolveWithAnalysisFailScrambledCubeStateSolved() {
        val solve = Solve(
            1000,
            Calendar.getInstance(),
            simpleSolveStateSequence(),
            CubeState.SOLVED_CUBE_STATE,
            SolveStatus.Solved,
            0,
            scrambleSequence = "R U R' U'"
        )
        solve.calculateTimeFromStateSequence()

        assertThrows(IllegalArgumentException::class.java) {
            solveAnalysisDBService.saveSolveWithAnalysis(solve).solveID
        }
    }

    @Test
    fun saveSolveWithAnalysisFailInvalidTime() {
        val solve = Solve(
            -1000,
            Calendar.getInstance(),
            simpleSolveStateSequence(),
            simpleSolveStateSequence().first(),
            SolveStatus.Solved,
            1,
            scrambleSequence = "R U R' U'"
        )

        assertThrows(IllegalArgumentException::class.java) {
            solveAnalysisDBService.saveSolveWithAnalysis(solve).solveID
        }
    }

    @Test
    fun saveSolveWithAnalysisFailInvalidSolveStartTime() {
        val solve = Solve(
            1000,
            Calendar.getInstance(),
            simpleSolveStateSequence(),
            simpleSolveStateSequence().first(),
            SolveStatus.Solved,
            0,
            scrambleSequence = "R U R' U'"
        )
        solve.calculateTimeFromStateSequence()

        assertThrows(IllegalArgumentException::class.java) {
            solveAnalysisDBService.saveSolveWithAnalysis(solve).solveID
        }
    }

    @Test
    fun saveSolveWithAnalysisFailSolveInProgress() {
        val solve = Solve(
            1000,
            Calendar.getInstance(),
            simpleSolveStateSequence(),
            simpleSolveStateSequence().first(),
            SolveStatus.Solving,
            1,
            scrambleSequence = "R U R' U'"
        )
        solve.calculateTimeFromStateSequence()

        assertThrows(IllegalArgumentException::class.java) {
            solveAnalysisDBService.saveSolveWithAnalysis(solve).solveID
        }
    }

    @Test
    fun deleteSolveWithAnalysisDataTest() {
        val solve = Solve(
            1000,
            Calendar.getInstance(),
            simpleSolveStateSequence(),
            simpleSolveStateSequence().first(),
            SolveStatus.Solved,
            1,
            scrambleSequence = "R U R' U'"
        )

        val solveID = solveAnalysisDBService.saveSolveWithAnalysis(solve).solveID
        solveAnalysisDBService.deleteSolveWithAnalysisData(solveID)

        val solveFromDatabase =
            SolveDBService(context, SolvesDatabaseConstants.TEST_DATABASE_NAME).getSolve(solveID)
        assertNull(solveFromDatabase)

        val cubeStatesFromDatabase =
            CubeStateDBService(context, SolvesDatabaseConstants.TEST_DATABASE_NAME)
                .getCubeStatesForSolve(solveID)
        assert(cubeStatesFromDatabase.isEmpty())

        val f2lDataFromDatabase = F2LDBService(
            context,
            SolvesDatabaseConstants.TEST_DATABASE_NAME
        ).getF2LForSolve(solveID)
        assertNull(f2lDataFromDatabase)

        val ollDataFromDatabase = OLLDBService(
            context,
            SolvesDatabaseConstants.TEST_DATABASE_NAME
        ).getOLLForSolve(solveID)
        assertNull(ollDataFromDatabase)

        val pllDataFromDatabase = PLLDBService(
            context,
            SolvesDatabaseConstants.TEST_DATABASE_NAME
        ).getPLLForSolve(solveID)
        assertNull(pllDataFromDatabase)

        val crossDataFromDatabase =
            CrossDBService(context, SolvesDatabaseConstants.TEST_DATABASE_NAME).getCrossForSolve(
                solveID
            )
        assertNull(crossDataFromDatabase)
    }

    @Test
    fun deleteSolveWithAnalysisDataFailInvalidID() {
        val solve = Solve(
            1000,
            Calendar.getInstance(),
            simpleSolveStateSequence(),
            simpleSolveStateSequence().first(),
            SolveStatus.Solved,
            1,
            scrambleSequence = "R U R' U'"
        )

        val solveID = solveAnalysisDBService.saveSolveWithAnalysis(solve).solveID
        solveAnalysisDBService.deleteSolveWithAnalysisData(solveID + 1)

        val solveFromDatabase =
            SolveDBService(context, SolvesDatabaseConstants.TEST_DATABASE_NAME).getSolve(solveID)
        assertNotNull(solveFromDatabase)

        val cubeStatesFromDatabase =
            CubeStateDBService(context, SolvesDatabaseConstants.TEST_DATABASE_NAME)
                .getCubeStatesForSolve(solveID)
        assert(cubeStatesFromDatabase.isNotEmpty())

        val f2lDataFromDatabase = F2LDBService(
            context,
            SolvesDatabaseConstants.TEST_DATABASE_NAME
        ).getF2LForSolve(solveID)
        assertNotNull(f2lDataFromDatabase)

        val ollDataFromDatabase = OLLDBService(
            context,
            SolvesDatabaseConstants.TEST_DATABASE_NAME
        ).getOLLForSolve(solveID)
        assertNotNull(ollDataFromDatabase)

        val pllDataFromDatabase = PLLDBService(
            context,
            SolvesDatabaseConstants.TEST_DATABASE_NAME
        ).getPLLForSolve(solveID)
        assertNotNull(pllDataFromDatabase)

        val crossDataFromDatabase =
            CrossDBService(context, SolvesDatabaseConstants.TEST_DATABASE_NAME).getCrossForSolve(
                solveID
            )
        assertNotNull(crossDataFromDatabase)
    }

    @Test
    fun getSolveAnalysisForSolveTest() {
        val solve = Solve(
            1000,
            Calendar.getInstance(),
            simpleSolveStateSequence(),
            simpleSolveStateSequence().first(),
            SolveStatus.Solved,
            1,
            scrambleSequence = "R U R' U'"
        )
        val solvePhaseDetection =
            SolutionPhaseDetection(solve, CubeStatePhaseDetection(solve.scrambledState))
        val crossData = solvePhaseDetection.getCrossData(context)
        val f2lData = solvePhaseDetection.getF2LData(context)
        val ollData = solvePhaseDetection.getOLLData(context)
        val pllData = solvePhaseDetection.getPLLData(context)

        val solveID = solveAnalysisDBService.saveSolveWithAnalysis(solve).solveID
        val solveAnalysisData = solveAnalysisDBService.getSolveAnalysisForSolve(solveID)

        assertNotNull(solveAnalysisData)
        assertEquals(solveAnalysisData!!.solveDuration, solve.time)
        assertEquals(solveAnalysisData.timestamp, solve.date.timeInMillis)
        assertEquals(solveAnalysisData.scrambleSequence, solve.scrambleSequence)
        assertEquals(solveAnalysisData.solveStateSequence.size, solve.solveStateSequence.size)
        assert(
            compareStateSequences(
                solveAnalysisData.solveStateSequence,
                solve.solveStateSequence
            )
        )
        assertNotNull(crossData)
        assertNotNull(f2lData)
        assertNotNull(ollData)
        assertNotNull(pllData)
        if (crossData == null || f2lData == null || ollData == null || pllData == null) {
            TestCase.fail()
            return
        }

        assertEquals(crossData.duration, solveAnalysisData.crossData.duration)
        assertEquals(crossData.moveCount, solveAnalysisData.crossData.moveCount)
        assertEquals(solveID, solveAnalysisData.crossData.solveID)

        assertEquals(f2lData.duration, solveAnalysisData.f2lData.duration)
        assertEquals(f2lData.moveCount, solveAnalysisData.f2lData.moveCount)
        assertEquals(solveID, solveAnalysisData.f2lData.solveID)

        assertEquals(ollData.duration, solveAnalysisData.ollData.duration)
        assertEquals(ollData.moveCount, solveAnalysisData.ollData.moveCount)
        assertEquals(solveID, solveAnalysisData.ollData.solveID)
        assertEquals(ollData.case, solveAnalysisData.ollData.case)

        assertEquals(pllData.duration, solveAnalysisData.pllData.duration)
        assertEquals(pllData.moveCount, solveAnalysisData.pllData.moveCount)
        assertEquals(solveID, solveAnalysisData.pllData.solveID)
        assertEquals(pllData.case, solveAnalysisData.pllData.case)
    }

    @Test
    fun getSolveAnalysisWithDataFailInvalidID() {
        val solve = Solve(
            1000,
            Calendar.getInstance(),
            simpleSolveStateSequence(),
            simpleSolveStateSequence().first(),
            SolveStatus.Solved,
            1,
            scrambleSequence = "R U R' U'"
        )

        val solveID = solveAnalysisDBService.saveSolveWithAnalysis(solve).solveID

        val solveAnalysisData = solveAnalysisDBService.getSolveAnalysisForSolve(solveID + 1)
        assertNull(solveAnalysisData)
    }

    @Test
    fun getAllSolveAnalysisDataTest() {
        val solve1 = Solve(
            1000,
            Calendar.getInstance(),
            simpleSolveStateSequence(),
            simpleSolveStateSequence().first(),
            SolveStatus.Solved,
            1,
            scrambleSequence = "R U R' U'"
        )
        val solve2 = Solve(
            2000,
            Calendar.getInstance(),
            simpleSolveStateSequence(),
            simpleSolveStateSequence().first(),
            SolveStatus.Solved,
            2,
            scrambleSequence = "R' U' R U"
        )
        val solve3 = Solve(
            3000,
            Calendar.getInstance(),
            simpleSolveStateSequence(),
            simpleSolveStateSequence().first(),
            SolveStatus.Solved,
            3,
            scrambleSequence = "R U2 R' U2"
        )

        val solveID1 = solveAnalysisDBService.saveSolveWithAnalysis(solve1).solveID
        val solveID2 = solveAnalysisDBService.saveSolveWithAnalysis(solve2).solveID
        val solveID3 = solveAnalysisDBService.saveSolveWithAnalysis(solve3).solveID

        val allSolves = solveAnalysisDBService.getSolveAnalysisForAllSolves()

        assertEquals(3, allSolves.size)
        assert(allSolves.any { it.solveID == solveID1 })
        assert(allSolves.any { it.solveID == solveID2 })
        assert(allSolves.any { it.solveID == solveID3 })
    }

    private fun compareStateSequences(
        sequenceFromDB: List<CubeStateData>,
        expected: List<CubeState>
    ): Boolean {
        sequenceFromDB.forEachIndexed { index, cubeStateData ->
            val cubeStateFromData = CubeState(cubeStateData)
            if (expected[index].solveID != cubeStateFromData.solveID) return false
            else if (expected[index].cornerPositions != cubeStateFromData.cornerPositions) return false
            else if (expected[index].cornerOrientations != cubeStateFromData.cornerOrientations) return false
            else if (expected[index].edgePositions != cubeStateFromData.edgePositions) return false
            else if (expected[index].edgeOrientations != cubeStateFromData.edgeOrientations) return false
            else if (expected[index].lastMove != cubeStateFromData.lastMove) return false
            else if (expected[index].timestamp != cubeStateFromData.timestamp) return false
            else if (expected[index].id != cubeStateFromData.id) return false
        }
        return true
    }
}