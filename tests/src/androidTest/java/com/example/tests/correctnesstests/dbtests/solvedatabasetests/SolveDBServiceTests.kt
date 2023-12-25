package com.example.tests.correctnesstests.dbtests.solvedatabasetests

import android.content.Context
import android.provider.BaseColumns
import androidx.test.platform.app.InstrumentationRegistry
import com.example.cube_cube.cube.CubeState
import com.example.cube_cube.cube.Solve
import com.example.cube_cube.cube.SolvePenalty
import com.example.cube_cube.solveDBDataClasses.SolveData
import com.example.cube_database.solvedatabase.solvesDB.SolvesDatabaseConstants
import com.example.cube_database.solvedatabase.solvesDB.services.SolveDBService
import junit.framework.TestCase
import org.junit.After
import org.junit.Assert.assertEquals
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

        val id = solveDBService.addSolve(SolveData(solve))

        val projection = arrayOf(
            SolvesDatabaseConstants.SolveTable.DURATION_COLUMN,
            SolvesDatabaseConstants.SolveTable.TIMESTAMP_COLUMN,
            SolvesDatabaseConstants.SolveTable.SCRAMBLED_STATE_ID_COLUMN,
            SolvesDatabaseConstants.SolveTable.SCRAMBLE_SEQUENCE_COLUMN,
            SolvesDatabaseConstants.SolveTable.MOVE_COUNT,
            SolvesDatabaseConstants.SolveTable.PENALTY_COLUMN,
            BaseColumns._ID
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
            if(moveToFirst()){
                assert(getLong(getColumnIndexOrThrow(com.example.cube_database.solvedatabase.solvesDB.SolvesDatabaseConstants.SolveTable.DURATION_COLUMN)) == 1000L)
                assert(getLong(getColumnIndexOrThrow(com.example.cube_database.solvedatabase.solvesDB.SolvesDatabaseConstants.SolveTable.TIMESTAMP_COLUMN)) == solve.date.timeInMillis)
                assert(getLong(getColumnIndexOrThrow(com.example.cube_database.solvedatabase.solvesDB.SolvesDatabaseConstants.SolveTable.SCRAMBLED_STATE_ID_COLUMN)) == -1L)
                assert(getString(getColumnIndexOrThrow(com.example.cube_database.solvedatabase.solvesDB.SolvesDatabaseConstants.SolveTable.SCRAMBLE_SEQUENCE_COLUMN)) == "R U R' U'")
                assert(getInt(getColumnIndexOrThrow(com.example.cube_database.solvedatabase.solvesDB.SolvesDatabaseConstants.SolveTable.PENALTY_COLUMN)) == 0)
                assert(getLong(getColumnIndexOrThrow(BaseColumns._ID)) == id)
                assertEquals(-1, getInt(getColumnIndexOrThrow(com.example.cube_database.solvedatabase.solvesDB.SolvesDatabaseConstants.SolveTable.MOVE_COUNT)))
            }
            else{
                TestCase.fail()
            }
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
        assert(retrievedSolveData.scramble == solveData.scramble)
        assert(retrievedSolveData.moveCount == solveData.moveCount)
        assert(retrievedSolveData.penalty == solveData.penalty)
        assertEquals(solveData.moveCount, retrievedSolveData.moveCount)
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
            scrambledState = CubeState.SOLVED_CUBE_STATE,
            solvePenalty = SolvePenalty.PlusTwo
        )

        solveDBService.updateSolve(SolveData(newSolve), id)

        val retrievedSolveData = solveDBService.getSolve(id)
        assert(retrievedSolveData != null)
        assert(retrievedSolveData!!.id == id)
        assert(retrievedSolveData.solveDuration == newSolve.time)
        assert(retrievedSolveData.timestamp == newSolve.date.timeInMillis)
        assert(retrievedSolveData.scrambledStateID == newSolve.scrambledState.id)
        assert(retrievedSolveData.scramble == newSolve.scrambleSequence)
        assertEquals(newSolve.solvePenalty.ordinal, retrievedSolveData.penalty)
        assertEquals(newSolve.solveStateSequence.size - 1, retrievedSolveData.moveCount)
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
        assert(retrievedSolveData.penalty == solve.solvePenalty.ordinal)
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
        assert(retrievedSolveData.penalty == solve.solvePenalty.ordinal)
    }

    @Test
    fun getAllSolveIDsTest(){
        val solve1 = Solve(
            time = 1000,
            date = Calendar.getInstance(),
            scrambleSequence = "R U R' U'",
            scrambledState = CubeState.SOLVED_CUBE_STATE
        )
        val solve2 = Solve(
            time = 2000,
            date = Calendar.getInstance(),
            scrambleSequence = "R U R' U' R U R' U'",
            scrambledState = CubeState.SOLVED_CUBE_STATE
        )
        val solve3 = Solve(
            time = 3000,
            date = Calendar.getInstance(),
            scrambleSequence = "R U R' U' R U R' U' R U R' U'",
            scrambledState = CubeState.SOLVED_CUBE_STATE
        )

        val id1 = solveDBService.addSolve(SolveData(solve1))
        val id2 = solveDBService.addSolve(SolveData(solve2))
        val id3 = solveDBService.addSolve(SolveData(solve3))

        val ids = solveDBService.getAllSolveIDs()

        assert(ids.size == 3)
        assert(ids.contains(id1))
        assert(ids.contains(id2))
        assert(ids.contains(id3))
    }

    @Test
    fun getAllSolvesWithPagingTest(){
        for(i in 0..100){
            val solve = Solve(
                time = 1000+i.toLong(),
                date = Calendar.getInstance(),
                scrambleSequence = "R U R' U'",
                scrambledState = CubeState.SOLVED_CUBE_STATE
            )
            solveDBService.addSolve(SolveData(solve))
        }

        var solves = solveDBService.getAllSolves(size = 10, orderBy = "")
        assert(solves.size == 10)
        solves.forEachIndexed{ index, solve ->
            assertEquals(1000+index.toLong(), solve.solveDuration)
        }
        solves = solveDBService.getAllSolves(size = 10, page = 2).toMutableList()
        assert(solves.size == 10)
        solves.forEachIndexed{ index, solve ->
            assertEquals(1080-index.toLong(), solve.solveDuration)
        }
        solves = solveDBService.getAllSolves()
        assert(solves.size == 20)
        solves.forEachIndexed{ index, solve ->
            assertEquals(1100-index.toLong(), solve.solveDuration)
        }
        solves = solveDBService.getAllSolves(size = 100).toMutableList()
        assert(solves.size == 100)
        solves.forEachIndexed{ index, solve ->
            assertEquals(1100-index.toLong(), solve.solveDuration)
        }
    }

    @Test
    fun getAllSolvesWithPagingFailInvalidPage(){
        for(i in 0..100){
            val solve = Solve(
                time = 1000+i.toLong(),
                date = Calendar.getInstance(),
                scrambleSequence = "R U R' U'",
                scrambledState = CubeState.SOLVED_CUBE_STATE
            )
            solveDBService.addSolve(SolveData(solve))
        }

        var solves = solveDBService.getAllSolves(size = 10, page = 11)
        assert(solves.isEmpty())
        solves = solveDBService.getAllSolves(size = 10, page = -1)
        assert(solves.isEmpty())
    }

}