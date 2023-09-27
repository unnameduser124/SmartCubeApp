package com.example.smartcubeapp.solvedatabasetests

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.example.smartcubeapp.cube.CubeState
import com.example.smartcubeapp.cube.Solve
import com.example.smartcubeapp.solvedatabase.SolveDBService
import com.example.smartcubeapp.solvedatabase.SolvesDatabaseConstants
import com.example.smartcubeapp.solvedatabase.dataclasses.SolveData
import org.junit.After
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test
import java.util.Calendar

class SolveDBServiceTests {

    private lateinit var context: Context
    private lateinit var solveDBService: SolveDBService

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        solveDBService = SolveDBService(context, SolvesDatabaseConstants.TEST_DATABASE_NAME)
    }

    @After
    fun tearDown() {
        solveDBService.close()
        context.deleteDatabase(SolvesDatabaseConstants.TEST_DATABASE_NAME)
    }

    @Test
    fun addSolveTest() {
        val solve = Solve(
            time = 1000,
            date = Calendar.getInstance(),
            scrambleSequence = "R U R' U'",
            scrambledState = CubeState.SOLVED_CUBE_STATE
        )

        solveDBService.addSolve(SolveData(solve))

        val projection = arrayOf(
            SolvesDatabaseConstants.SolveTable.DURATION_COLUMN,
            SolvesDatabaseConstants.SolveTable.TIMESTAMP_COLUMN,
            SolvesDatabaseConstants.SolveTable.SCRAMBLED_STATE_ID_COLUMN,
            SolvesDatabaseConstants.SolveTable.SCRAMBLE_SEQUENCE_COLUMN
        )

        val cursor = solveDBService.readableDatabase.query(
            SolvesDatabaseConstants.SolveTable.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        with(cursor) {
            moveToFirst()
            assert(getLong(getColumnIndexOrThrow(SolvesDatabaseConstants.SolveTable.DURATION_COLUMN)) == 1000L)
            assert(getLong(getColumnIndexOrThrow(SolvesDatabaseConstants.SolveTable.TIMESTAMP_COLUMN)) == solve.date.timeInMillis)
            assert(getLong(getColumnIndexOrThrow(SolvesDatabaseConstants.SolveTable.SCRAMBLED_STATE_ID_COLUMN)) == -1L)
            assert(getString(getColumnIndexOrThrow(SolvesDatabaseConstants.SolveTable.SCRAMBLE_SEQUENCE_COLUMN)) == "R U R' U'")
        }
    }

    @Test
    fun addSolveFailInvalidDuration() {
        val solve = Solve(
            time = 0,
            date = Calendar.getInstance(),
            scrambleSequence = "R U R' U'",
            scrambledState = CubeState.SOLVED_CUBE_STATE
        )

        assertThrows(IllegalArgumentException::class.java) {
            solveDBService.addSolve(SolveData(solve))
        }


        val projection = arrayOf(
            SolvesDatabaseConstants.SolveTable.DURATION_COLUMN,
            SolvesDatabaseConstants.SolveTable.TIMESTAMP_COLUMN,
            SolvesDatabaseConstants.SolveTable.SCRAMBLED_STATE_ID_COLUMN,
            SolvesDatabaseConstants.SolveTable.SCRAMBLE_SEQUENCE_COLUMN
        )

        val cursor = solveDBService.readableDatabase.query(
            SolvesDatabaseConstants.SolveTable.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        with(cursor) {
            assert(!moveToFirst())
        }
    }

    @Test
    fun addSolveFailInvalidScrambleSequence() {
        val solve = Solve(
            time = 1000,
            date = Calendar.getInstance(),
            scrambleSequence = "",
            scrambledState = CubeState.SOLVED_CUBE_STATE
        )

        assertThrows(IllegalArgumentException::class.java) {
            solveDBService.addSolve(SolveData(solve))
        }

        val projection = arrayOf(
            SolvesDatabaseConstants.SolveTable.DURATION_COLUMN,
            SolvesDatabaseConstants.SolveTable.TIMESTAMP_COLUMN,
            SolvesDatabaseConstants.SolveTable.SCRAMBLED_STATE_ID_COLUMN,
            SolvesDatabaseConstants.SolveTable.SCRAMBLE_SEQUENCE_COLUMN
        )

        val cursor = solveDBService.readableDatabase.query(
            SolvesDatabaseConstants.SolveTable.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        with(cursor) {
            assert(!moveToFirst())
        }
    }

    @Test
    fun getSolveTest(){
        val solve = Solve(
            time = 1000,
            date = Calendar.getInstance(),
            scrambleSequence = "R U R' U'",
            scrambledState = CubeState.SOLVED_CUBE_STATE
        )

        val solveData = SolveData(solve)
        val id = solveDBService.addSolve(solveData)

        val retrievedSolveData = solveDBService.getSolve(id)
        assert(retrievedSolveData != null)
        assert(retrievedSolveData!!.id == id)
        assert(retrievedSolveData.solveDuration == solveData.solveDuration)
        assert(retrievedSolveData.timestamp == solveData.timestamp)
        assert(retrievedSolveData.scrambledStateID == solveData.scrambledStateID)
    }

    @Test
    fun getSolveFailInvalidID(){
        val solve = Solve(
            time = 1000,
            date = Calendar.getInstance(),
            scrambleSequence = "R U R' U'",
            scrambledState = CubeState.SOLVED_CUBE_STATE
        )

        val solveData = SolveData(solve)
        val id = solveDBService.addSolve(solveData)

        val retrievedSolveData = solveDBService.getSolve(id + 1)
        assert(retrievedSolveData == null)
    }

    @Test
    fun deleteSolveTest(){
        val solve = Solve(
            time = 1000,
            date = Calendar.getInstance(),
            scrambleSequence = "R U R' U'",
            scrambledState = CubeState.SOLVED_CUBE_STATE
        )

        val id = solveDBService.addSolve(SolveData(solve))

        solveDBService.deleteSolve(id)

        val retrievedSolveData = solveDBService.getSolve(id)
        assert(retrievedSolveData == null)
    }

    @Test
    fun deleteSolveFailInvalidID(){
        val solve = Solve(
            time = 1000,
            date = Calendar.getInstance(),
            scrambleSequence = "R U R' U'",
            scrambledState = CubeState.SOLVED_CUBE_STATE
        )

        val id = solveDBService.addSolve(SolveData(solve))

        solveDBService.deleteSolve(id + 1)

        val retrievedSolveData = solveDBService.getSolve(id)
        assert(retrievedSolveData != null)
    }

    @Test
    fun updateSolveDataTest(){
        val solve = Solve(
            time = 1000,
            date = Calendar.getInstance(),
            scrambleSequence = "R U R' U'",
            scrambledState = CubeState.SOLVED_CUBE_STATE
        )

        val id = solveDBService.addSolve(SolveData(solve))

        val newSolve = Solve(
            time = 2000,
            date = Calendar.getInstance(),
            scrambleSequence = "R U R' U' R U R' U'",
            scrambledState = CubeState.SOLVED_CUBE_STATE
        )

        solveDBService.updateSolve(SolveData(newSolve), id)

        val retrievedSolveData = solveDBService.getSolve(id)
        assert(retrievedSolveData != null)
        assert(retrievedSolveData!!.id == id)
        assert(retrievedSolveData.solveDuration == newSolve.time)
        assert(retrievedSolveData.timestamp == newSolve.date.timeInMillis)
        assert(retrievedSolveData.scrambledStateID == newSolve.scrambledState.id)
        assert(retrievedSolveData.scramble == newSolve.scrambleSequence)
    }

    @Test
    fun updateSolveDataFailInvalidDuration(){
        val solve = Solve(
            time = 1000,
            date = Calendar.getInstance(),
            scrambleSequence = "R U R' U'",
            scrambledState = CubeState.SOLVED_CUBE_STATE
        )

        val id = solveDBService.addSolve(SolveData(solve))

        val newSolve = Solve(
            time = 0,
            date = Calendar.getInstance(),
            scrambleSequence = "R U R' U' R U R' U'",
            scrambledState = CubeState.SOLVED_CUBE_STATE
        )

        assertThrows(IllegalArgumentException::class.java) {
            solveDBService.updateSolve(SolveData(newSolve), id)
        }

        val retrievedSolveData = solveDBService.getSolve(id)
        assert(retrievedSolveData != null)
        assert(retrievedSolveData!!.id == id)
        assert(retrievedSolveData.solveDuration == solve.time)
        assert(retrievedSolveData.timestamp == solve.date.timeInMillis)
        assert(retrievedSolveData.scrambledStateID == solve.scrambledState.id)
        assert(retrievedSolveData.scramble == solve.scrambleSequence)
    }

    @Test
    fun updateSolveDataInvalidScrambleSequence(){
        val solve = Solve(
            time = 1000,
            date = Calendar.getInstance(),
            scrambleSequence = "R U R' U'",
            scrambledState = CubeState.SOLVED_CUBE_STATE
        )

        val id = solveDBService.addSolve(SolveData(solve))

        val newSolve = Solve(
            time = 2000,
            date = Calendar.getInstance(),
            scrambleSequence = "",
            scrambledState = CubeState.SOLVED_CUBE_STATE
        )

        assertThrows(IllegalArgumentException::class.java) {
            solveDBService.updateSolve(SolveData(newSolve), id)
        }

        val retrievedSolveData = solveDBService.getSolve(id)
        assert(retrievedSolveData != null)
        assert(retrievedSolveData!!.id == id)
        assert(retrievedSolveData.solveDuration == solve.time)
        assert(retrievedSolveData.timestamp == solve.date.timeInMillis)
        assert(retrievedSolveData.scrambledStateID == solve.scrambledState.id)
        assert(retrievedSolveData.scramble == solve.scrambleSequence)
    }
}