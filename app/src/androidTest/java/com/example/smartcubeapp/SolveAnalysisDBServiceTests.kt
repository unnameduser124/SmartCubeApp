package com.example.smartcubeapp

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.example.smartcubeapp.cube.CubeState
import com.example.smartcubeapp.cube.Solve
import com.example.smartcubeapp.phasedetection.CubeStatePhaseDetection
import com.example.smartcubeapp.phasedetection.SolutionPhaseDetection
import com.example.smartcubeapp.solvedatabase.SolvesDatabaseConstants
import com.example.smartcubeapp.solvedatabase.dataclasses.CubeStateData
import com.example.smartcubeapp.solvedatabase.services.CrossDBService
import com.example.smartcubeapp.solvedatabase.services.CubeStateDBService
import com.example.smartcubeapp.solvedatabase.services.F2LDBService
import com.example.smartcubeapp.solvedatabase.services.OLLDBService
import com.example.smartcubeapp.solvedatabase.services.PLLDBService
import com.example.smartcubeapp.solvedatabase.services.SolveAnalysisDBService
import com.example.smartcubeapp.solvedatabase.services.SolveDBService
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
            false,
            0,
            scrambleSequence = "R U R' U'"
        )

        val solveID = solveAnalysisDBService.saveSolveWithAnalysis(solve)

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
            false,
            0,
            scrambleSequence = "R U R' U'"
        )
        assertThrows(IllegalArgumentException::class.java) {
            solve.calculateTimeFromStateSequence()
        }

        assertThrows(IllegalArgumentException::class.java) {
            solveAnalysisDBService.saveSolveWithAnalysis(solve)
        }
    }

    @Test
    fun saveSolveWithAnalysisFailScrambledCubeStateSolved() {
        val solve = Solve(
            1000,
            Calendar.getInstance(),
            simpleSolveStateSequence(),
            CubeState.SOLVED_CUBE_STATE,
            false,
            0,
            scrambleSequence = "R U R' U'"
        )
        solve.calculateTimeFromStateSequence()

        assertThrows(IllegalArgumentException::class.java) {
            solveAnalysisDBService.saveSolveWithAnalysis(solve)
        }
    }

    @Test
    fun saveSolveWithAnalysisFailInvalidTime() {
        val solve = Solve(
            -1000,
            Calendar.getInstance(),
            simpleSolveStateSequence(),
            simpleSolveStateSequence().first(),
            false,
            1,
            scrambleSequence = "R U R' U'"
        )

        assertThrows(IllegalArgumentException::class.java) {
            solveAnalysisDBService.saveSolveWithAnalysis(solve)
        }
    }

    @Test
    fun saveSolveWithAnalysisFailInvalidSolveStartTime() {
        val solve = Solve(
            1000,
            Calendar.getInstance(),
            simpleSolveStateSequence(),
            simpleSolveStateSequence().first(),
            true,
            0,
            scrambleSequence = "R U R' U'"
        )
        solve.calculateTimeFromStateSequence()

        assertThrows(IllegalArgumentException::class.java) {
            solveAnalysisDBService.saveSolveWithAnalysis(solve)
        }
    }

    @Test
    fun saveSolveWithAnalysisFailSolveInProgress() {
        val solve = Solve(
            1000,
            Calendar.getInstance(),
            simpleSolveStateSequence(),
            simpleSolveStateSequence().first(),
            true,
            1,
            scrambleSequence = "R U R' U'"
        )
        solve.calculateTimeFromStateSequence()

        assertThrows(IllegalArgumentException::class.java) {
            solveAnalysisDBService.saveSolveWithAnalysis(solve)
        }
    }

    @Test
    fun deleteSolveWithAnalysisDataTest() {
        val solve = Solve(
            1000,
            Calendar.getInstance(),
            simpleSolveStateSequence(),
            simpleSolveStateSequence().first(),
            false,
            1,
            scrambleSequence = "R U R' U'"
        )

        val solveID = solveAnalysisDBService.saveSolveWithAnalysis(solve)
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
            false,
            1,
            scrambleSequence = "R U R' U'"
        )

        val solveID = solveAnalysisDBService.saveSolveWithAnalysis(solve)
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
            false,
            1,
            scrambleSequence = "R U R' U'"
        )
        val solvePhaseDetection =
            SolutionPhaseDetection(solve, CubeStatePhaseDetection(solve.scrambledState))
        val crossData = solvePhaseDetection.getCrossData(context)
        val f2lData = solvePhaseDetection.getF2LData(context)
        val ollData = solvePhaseDetection.getOLLData(context)
        val pllData = solvePhaseDetection.getPLLData(context)

        val solveID = solveAnalysisDBService.saveSolveWithAnalysis(solve)
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

        assertEquals(crossData.duration, solveAnalysisData.crossData.duration)
        assertEquals(crossData.moveCount, solveAnalysisData.crossData.moveCount)
        assertEquals(crossData.solveID, solveAnalysisData.crossData.solveID)

        assertEquals(f2lData.duration, solveAnalysisData.f2lData.duration)
        assertEquals(f2lData.moveCount, solveAnalysisData.f2lData.moveCount)
        assertEquals(f2lData.solveID, solveAnalysisData.f2lData.solveID)

        assertEquals(ollData.duration, solveAnalysisData.ollData.duration)
        assertEquals(ollData.moveCount, solveAnalysisData.ollData.moveCount)
        assertEquals(ollData.solveID, solveAnalysisData.ollData.solveID)
        assertEquals(ollData.case, solveAnalysisData.ollData.case)

        assertEquals(pllData.duration, solveAnalysisData.pllData.duration)
        assertEquals(pllData.moveCount, solveAnalysisData.pllData.moveCount)
        assertEquals(pllData.solveID, solveAnalysisData.pllData.solveID)
        assertEquals(pllData.case, solveAnalysisData.pllData.case)
    }

    @Test
    fun getSolveAnalysisWithDataFailInvalidID() {
        val solve = Solve(
            1000,
            Calendar.getInstance(),
            simpleSolveStateSequence(),
            simpleSolveStateSequence().first(),
            false,
            1,
            scrambleSequence = "R U R' U'"
        )

        val solveID = solveAnalysisDBService.saveSolveWithAnalysis(solve)

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
            false,
            1,
            scrambleSequence = "R U R' U'"
        )
        val solve2 = Solve(
            2000,
            Calendar.getInstance(),
            simpleSolveStateSequence(),
            simpleSolveStateSequence().first(),
            false,
            2,
            scrambleSequence = "R' U' R U"
        )
        val solve3 = Solve(
            3000,
            Calendar.getInstance(),
            simpleSolveStateSequence(),
            simpleSolveStateSequence().first(),
            false,
            3,
            scrambleSequence = "R U2 R' U2"
        )

        val solveID1 = solveAnalysisDBService.saveSolveWithAnalysis(solve1)
        val solveID2 = solveAnalysisDBService.saveSolveWithAnalysis(solve2)
        val solveID3 = solveAnalysisDBService.saveSolveWithAnalysis(solve3)

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

    private fun simpleSolveStateSequence(): MutableList<CubeState> {
        return mutableListOf(
            CubeState(
                cornerPositions = mutableListOf(1, 2, 3, 5, 4, 7, 6, 8),
                cornerOrientations = mutableListOf(3, 3, 3, 2, 3, 3, 2, 1),
                edgePositions = mutableListOf(9, 2, 3, 4, 5, 6, 7, 1, 8, 11, 12, 10),
                edgeOrientations = mutableListOf(
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    true,
                    false,
                    false,
                    true
                ),
                timestamp = 1000
            ),
            CubeState(
                cornerPositions = mutableListOf(1, 2, 3, 5, 8, 4, 7, 6),
                cornerOrientations = mutableListOf(3, 3, 3, 2, 2, 3, 3, 1),
                edgePositions = mutableListOf(9, 2, 3, 4, 5, 6, 7, 1, 10, 8, 11, 12),
                edgeOrientations = mutableListOf(
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    true,
                    true,
                    false,
                    false
                ),
                timestamp = 1000
            ),
            CubeState(
                cornerPositions = mutableListOf(8, 1, 3, 5, 4, 2, 7, 6),
                cornerOrientations = mutableListOf(2, 1, 3, 2, 1, 1, 3, 1),
                edgePositions = mutableListOf(9, 5, 3, 4, 8, 2, 7, 1, 10, 6, 11, 12),
                edgeOrientations = mutableListOf(
                    false,
                    false,
                    false,
                    false,
                    true,
                    false,
                    false,
                    false,
                    true,
                    false,
                    false,
                    false
                ),
                timestamp = 1000
            ),
            CubeState(
                cornerPositions = mutableListOf(4, 1, 3, 8, 6, 2, 7, 5),
                cornerOrientations = mutableListOf(1, 1, 3, 3, 1, 1, 3, 3),
                edgePositions = mutableListOf(8, 5, 3, 4, 10, 2, 7, 9, 1, 6, 11, 12),
                edgeOrientations = mutableListOf(
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    true,
                    true,
                    false,
                    false,
                    false
                ),
                timestamp = 1000
            ),
            CubeState(
                cornerPositions = mutableListOf(1, 2, 3, 8, 4, 6, 7, 5),
                cornerOrientations = mutableListOf(3, 3, 3, 3, 3, 3, 3, 3),
                edgePositions = mutableListOf(8, 2, 3, 4, 5, 6, 7, 9, 1, 10, 11, 12),
                edgeOrientations = mutableListOf(
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    true,
                    true,
                    false,
                    false,
                    false
                ),
                timestamp = 1000
            ),
            CubeState(
                cornerPositions = mutableListOf(1, 2, 3, 8, 5, 4, 6, 7),
                cornerOrientations = mutableListOf(3, 3, 3, 3, 3, 3, 3, 3),
                edgePositions = mutableListOf(8, 2, 3, 4, 5, 6, 7, 9, 12, 1, 10, 11),
                edgeOrientations = mutableListOf(
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    true,
                    false,
                    true,
                    false,
                    false
                ),
                timestamp = 1000
            ),
            CubeState(
                cornerPositions = mutableListOf(5, 1, 3, 8, 4, 2, 6, 7),
                cornerOrientations = mutableListOf(1, 1, 3, 3, 1, 1, 3, 3),
                edgePositions = mutableListOf(8, 5, 3, 4, 1, 2, 7, 9, 12, 6, 10, 11),
                edgeOrientations = mutableListOf(
                    false,
                    false,
                    false,
                    false,
                    true,
                    false,
                    false,
                    true,
                    false,
                    false,
                    false,
                    false
                ),
                timestamp = 1000
            ),
            CubeState(
                cornerPositions = mutableListOf(8, 1, 3, 7, 5, 2, 6, 4),
                cornerOrientations = mutableListOf(2, 1, 3, 2, 1, 1, 3, 1),
                edgePositions = mutableListOf(9, 5, 3, 4, 8, 2, 7, 12, 1, 6, 10, 11),
                edgeOrientations = mutableListOf(
                    false,
                    false,
                    false,
                    false,
                    true,
                    false,
                    false,
                    true,
                    false,
                    false,
                    false,
                    false
                ),
                timestamp = 1000
            ),
            CubeState(
                cornerPositions = mutableListOf(1, 2, 3, 7, 8, 5, 6, 4),
                cornerOrientations = mutableListOf(3, 3, 3, 2, 2, 3, 3, 1),
                edgePositions = mutableListOf(9, 2, 3, 4, 5, 6, 7, 12, 1, 8, 10, 11),
                edgeOrientations = mutableListOf(
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    true,
                    false,
                    true,
                    false,
                    false
                ),
                timestamp = 1000
            ),
            CubeState(
                cornerPositions = mutableListOf(8, 2, 3, 1, 4, 5, 6, 7),
                cornerOrientations = mutableListOf(3, 3, 3, 2, 1, 3, 3, 3),
                edgePositions = mutableListOf(5, 2, 3, 4, 1, 6, 7, 9, 12, 8, 10, 11),
                edgeOrientations = mutableListOf(
                    true,
                    false,
                    false,
                    false,
                    true,
                    false,
                    false,
                    true,
                    false,
                    true,
                    false,
                    false
                ),
                timestamp = 1000
            ),
            CubeState(
                cornerPositions = mutableListOf(8, 2, 3, 1, 5, 6, 7, 4),
                cornerOrientations = mutableListOf(3, 3, 3, 2, 3, 3, 3, 2),
                edgePositions = mutableListOf(5, 2, 3, 4, 1, 6, 7, 9, 8, 10, 11, 12),
                edgeOrientations = mutableListOf(
                    true,
                    false,
                    false,
                    false,
                    true,
                    false,
                    false,
                    true,
                    true,
                    false,
                    false,
                    false
                ),
                timestamp = 1000
            ),
            CubeState(
                cornerPositions = mutableListOf(5, 8, 3, 1, 6, 2, 7, 4),
                cornerOrientations = mutableListOf(1, 1, 3, 2, 1, 1, 3, 2),
                edgePositions = mutableListOf(5, 1, 3, 4, 10, 2, 7, 9, 8, 6, 11, 12),
                edgeOrientations = mutableListOf(
                    true,
                    true,
                    false,
                    false,
                    false,
                    false,
                    false,
                    true,
                    true,
                    false,
                    false,
                    false
                ),
                timestamp = 1000
            ),
            CubeState(
                cornerPositions = mutableListOf(1, 8, 3, 4, 5, 2, 7, 6),
                cornerOrientations = mutableListOf(3, 1, 3, 3, 1, 1, 3, 1),
                edgePositions = mutableListOf(9, 1, 3, 4, 5, 2, 7, 8, 10, 6, 11, 12),
                edgeOrientations = mutableListOf(
                    false,
                    true,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    true,
                    false,
                    false,
                    false
                ),
                timestamp = 1000
            ),
            CubeState(
                cornerPositions = mutableListOf(8, 2, 3, 4, 1, 5, 7, 6),
                cornerOrientations = mutableListOf(3, 3, 3, 3, 1, 3, 3, 1),
                edgePositions = mutableListOf(9, 2, 3, 4, 1, 6, 7, 8, 10, 5, 11, 12),
                edgeOrientations = mutableListOf(
                    false,
                    false,
                    false,
                    false,
                    true,
                    false,
                    false,
                    false,
                    true,
                    false,
                    false,
                    false
                ),
                timestamp = 1000
            ),
            CubeState(
                cornerPositions = mutableListOf(1, 2, 3, 8, 6, 5, 7, 4),
                cornerOrientations = mutableListOf(1, 3, 3, 2, 1, 3, 3, 2),
                edgePositions = mutableListOf(1, 2, 3, 4, 10, 6, 7, 9, 8, 5, 11, 12),
                edgeOrientations = mutableListOf(
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    true,
                    true,
                    false,
                    false,
                    false
                ),
                timestamp = 1000
            ),
            CubeState(
                cornerPositions = mutableListOf(2, 5, 3, 8, 1, 6, 7, 4),
                cornerOrientations = mutableListOf(1, 1, 3, 2, 3, 3, 3, 2),
                edgePositions = mutableListOf(1, 6, 3, 4, 2, 5, 7, 9, 8, 10, 11, 12),
                edgeOrientations = mutableListOf(
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    true,
                    true,
                    false,
                    false,
                    false
                ),
                timestamp = 1000
            ),
            CubeState(
                cornerPositions = mutableListOf(2, 5, 3, 8, 4, 1, 6, 7),
                cornerOrientations = mutableListOf(1, 1, 3, 2, 1, 3, 3, 3),
                edgePositions = mutableListOf(1, 6, 3, 4, 2, 5, 7, 9, 12, 8, 10, 11),
                edgeOrientations = mutableListOf(
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    true,
                    false,
                    true,
                    false,
                    false
                ),
                timestamp = 1000
            ),
            CubeState(
                cornerPositions = mutableListOf(4, 2, 3, 8, 1, 5, 6, 7),
                cornerOrientations = mutableListOf(3, 3, 3, 2, 1, 3, 3, 3),
                edgePositions = mutableListOf(1, 2, 3, 4, 8, 6, 7, 9, 12, 5, 10, 11),
                edgeOrientations = mutableListOf(
                    false,
                    false,
                    false,
                    false,
                    true,
                    false,
                    false,
                    true,
                    false,
                    false,
                    false,
                    false
                ),
                timestamp = 1000
            ),
            CubeState(
                cornerPositions = mutableListOf(1, 4, 3, 8, 5, 2, 6, 7),
                cornerOrientations = mutableListOf(3, 1, 3, 2, 1, 1, 3, 3),
                edgePositions = mutableListOf(1, 8, 3, 4, 5, 2, 7, 9, 12, 6, 10, 11),
                edgeOrientations = mutableListOf(
                    false,
                    true,
                    false,
                    false,
                    false,
                    false,
                    false,
                    true,
                    false,
                    false,
                    false,
                    false
                ),
                timestamp = 1000
            ),
            CubeState(
                cornerPositions = mutableListOf(5, 4, 3, 1, 7, 2, 6, 8),
                cornerOrientations = mutableListOf(1, 1, 3, 2, 2, 1, 3, 3),
                edgePositions = mutableListOf(5, 8, 3, 4, 12, 2, 7, 1, 9, 6, 10, 11),
                edgeOrientations = mutableListOf(
                    true,
                    true,
                    false,
                    false,
                    true,
                    false,
                    false,
                    true,
                    false,
                    false,
                    false,
                    false
                ),
                timestamp = 1000
            ),
            CubeState(
                cornerPositions = mutableListOf(4, 2, 3, 1, 5, 7, 6, 8),
                cornerOrientations = mutableListOf(3, 3, 3, 2, 3, 2, 3, 3),
                edgePositions = mutableListOf(5, 2, 3, 4, 8, 6, 7, 1, 9, 12, 10, 11),
                edgeOrientations = mutableListOf(
                    true,
                    false,
                    false,
                    false,
                    true,
                    false,
                    false,
                    true,
                    false,
                    true,
                    false,
                    false
                ),
                timestamp = 1000
            ),
            CubeState(
                cornerPositions = mutableListOf(5, 2, 3, 4, 8, 7, 6, 1),
                cornerOrientations = mutableListOf(2, 3, 3, 2, 2, 2, 3, 3),
                edgePositions = mutableListOf(8, 2, 3, 4, 9, 6, 7, 5, 1, 12, 10, 11),
                edgeOrientations = mutableListOf(
                    false,
                    false,
                    false,
                    false,
                    true,
                    false,
                    false,
                    false,
                    false,
                    true,
                    false,
                    false
                ),
                timestamp = 1000
            ),
            CubeState(
                cornerPositions = mutableListOf(8, 5, 3, 4, 7, 2, 6, 1),
                cornerOrientations = mutableListOf(2, 2, 3, 2, 2, 1, 3, 3),
                edgePositions = mutableListOf(8, 9, 3, 4, 12, 2, 7, 5, 1, 6, 10, 11),
                edgeOrientations = mutableListOf(
                    false,
                    true,
                    false,
                    false,
                    true,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false
                ),
                timestamp = 1000
            ),
            CubeState(
                cornerPositions = mutableListOf(4, 5, 3, 1, 8, 2, 6, 7),
                cornerOrientations = mutableListOf(3, 2, 3, 2, 3, 1, 3, 3),
                edgePositions = mutableListOf(5, 9, 3, 4, 8, 2, 7, 1, 12, 6, 10, 11),
                edgeOrientations = mutableListOf(
                    true,
                    true,
                    false,
                    false,
                    true,
                    false,
                    false,
                    true,
                    false,
                    false,
                    false,
                    false
                ),
                timestamp = 1000
            ),
            CubeState(
                cornerPositions = mutableListOf(5, 2, 3, 1, 4, 8, 6, 7),
                cornerOrientations = mutableListOf(2, 3, 3, 2, 1, 1, 3, 3),
                edgePositions = mutableListOf(5, 2, 3, 4, 9, 6, 7, 1, 12, 8, 10, 11),
                edgeOrientations = mutableListOf(
                    true,
                    false,
                    false,
                    false,
                    true,
                    false,
                    false,
                    true,
                    false,
                    true,
                    false,
                    false
                ),
                timestamp = 1000
            ),
            CubeState(
                cornerPositions = mutableListOf(5, 2, 3, 1, 8, 6, 7, 4),
                cornerOrientations = mutableListOf(2, 3, 3, 2, 2, 3, 3, 2),
                edgePositions = mutableListOf(5, 2, 3, 4, 9, 6, 7, 1, 8, 10, 11, 12),
                edgeOrientations = mutableListOf(
                    true,
                    false,
                    false,
                    false,
                    true,
                    false,
                    false,
                    true,
                    true,
                    false,
                    false,
                    false
                ),
                timestamp = 1000
            ),
            CubeState(
                cornerPositions = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8),
                cornerOrientations = mutableListOf(3, 3, 3, 3, 3, 3, 3, 3),
                edgePositions = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12),
                edgeOrientations = mutableListOf(
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
                ),
                timestamp = 1000
            )

        )
    }
}