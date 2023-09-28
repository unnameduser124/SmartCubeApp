package com.example.smartcubeapp.solvedatabasetests

import android.content.Context
import android.provider.BaseColumns
import androidx.test.platform.app.InstrumentationRegistry
import com.example.smartcubeapp.solvedatabase.SolvesDatabaseConstants
import com.example.smartcubeapp.solvedatabase.dataclasses.PLLData
import com.example.smartcubeapp.solvedatabase.services.PLLDBService
import junit.framework.TestCase
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class PLLDBServiceTests {

    private lateinit var context: Context
    private lateinit var pllDBService: PLLDBService

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        pllDBService = PLLDBService(context, SolvesDatabaseConstants.TEST_DATABASE_NAME)
    }

    @After
    fun tearDown() {
        pllDBService.close()
        context.deleteDatabase(SolvesDatabaseConstants.TEST_DATABASE_NAME)
    }

    @Test
    fun addPLLDataTest() {
        val pllData = PLLData(
            solveID = 1,
            duration = 1000,
            moveCount = 10,
            startStateID = 1,
            endStateID = 11,
            case = 1
        )

        val id = pllDBService.addPLLData(pllData)

        val projection = arrayOf(
            SolvesDatabaseConstants.PLLTable.SOLVE_ID_COLUMN,
            SolvesDatabaseConstants.PLLTable.DURATION_COLUMN,
            SolvesDatabaseConstants.PLLTable.MOVE_COUNT_COLUMN,
            SolvesDatabaseConstants.PLLTable.END_CUBE_STATE_ID_COLUMN,
            SolvesDatabaseConstants.PLLTable.START_CUBE_STATE_ID_COLUMN,
            SolvesDatabaseConstants.PLLTable.CASE_COLUMN,
            BaseColumns._ID
        )

        val cursor = pllDBService.readableDatabase.query(
            SolvesDatabaseConstants.PLLTable.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        with(cursor) {
            if (moveToFirst()) {
                assert(getLong(getColumnIndex(SolvesDatabaseConstants.PLLTable.SOLVE_ID_COLUMN)) == pllData.solveID)
                assert(getLong(getColumnIndex(SolvesDatabaseConstants.PLLTable.DURATION_COLUMN)) == pllData.duration)
                assert(getLong(getColumnIndex(SolvesDatabaseConstants.PLLTable.MOVE_COUNT_COLUMN)) == pllData.moveCount)
                assert(getLong(getColumnIndex(SolvesDatabaseConstants.PLLTable.END_CUBE_STATE_ID_COLUMN)) == pllData.endStateID)
                assert(getLong(getColumnIndex(SolvesDatabaseConstants.PLLTable.START_CUBE_STATE_ID_COLUMN)) == pllData.startStateID)
                assert(getInt(getColumnIndex(SolvesDatabaseConstants.PLLTable.CASE_COLUMN)) == pllData.case)
                assert(getLong(getColumnIndex(BaseColumns._ID)) == id)
            } else {
                TestCase.fail()
            }
        }
    }

    @Test
    fun addPLLDataFailInvalidDuration() {
        val pllData = PLLData(
            solveID = 1,
            duration = -1000,
            moveCount = 10,
            startStateID = 1,
            endStateID = 11,
            case = 1
        )

        Assert.assertThrows(IllegalArgumentException::class.java) {
            pllDBService.addPLLData(pllData)
        }

        val projection = arrayOf(
            SolvesDatabaseConstants.PLLTable.SOLVE_ID_COLUMN,
            SolvesDatabaseConstants.PLLTable.DURATION_COLUMN,
            SolvesDatabaseConstants.PLLTable.MOVE_COUNT_COLUMN,
            SolvesDatabaseConstants.PLLTable.END_CUBE_STATE_ID_COLUMN,
            SolvesDatabaseConstants.PLLTable.START_CUBE_STATE_ID_COLUMN,
            BaseColumns._ID
        )

        val cursor = pllDBService.readableDatabase.query(
            SolvesDatabaseConstants.PLLTable.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        assert(!cursor.moveToFirst())
    }

    @Test
    fun addPLLDataFailInvalidMoveCount() {
        val pllData = PLLData(
            solveID = 1,
            duration = 1000,
            moveCount = -10,
            startStateID = 1,
            endStateID = 11,
            case = 1
        )

        Assert.assertThrows(IllegalArgumentException::class.java) {
            pllDBService.addPLLData(pllData)
        }

        val projection = arrayOf(
            SolvesDatabaseConstants.PLLTable.SOLVE_ID_COLUMN,
            SolvesDatabaseConstants.PLLTable.DURATION_COLUMN,
            SolvesDatabaseConstants.PLLTable.MOVE_COUNT_COLUMN,
            SolvesDatabaseConstants.PLLTable.END_CUBE_STATE_ID_COLUMN,
            SolvesDatabaseConstants.PLLTable.START_CUBE_STATE_ID_COLUMN,
            BaseColumns._ID
        )

        val cursor = pllDBService.readableDatabase.query(
            SolvesDatabaseConstants.PLLTable.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        assert(!cursor.moveToFirst())
    }

    @Test
    fun addPLLDataFailInvalidCase(){
        val pllData = PLLData(
            solveID = 1,
            duration = 1000,
            moveCount = 10,
            startStateID = 1,
            endStateID = 11,
            case = 57
        )

        Assert.assertThrows(IllegalArgumentException::class.java) {
            pllDBService.addPLLData(pllData)
        }

        val projection = arrayOf(
            SolvesDatabaseConstants.PLLTable.SOLVE_ID_COLUMN,
            SolvesDatabaseConstants.PLLTable.DURATION_COLUMN,
            SolvesDatabaseConstants.PLLTable.MOVE_COUNT_COLUMN,
            SolvesDatabaseConstants.PLLTable.END_CUBE_STATE_ID_COLUMN,
            SolvesDatabaseConstants.PLLTable.START_CUBE_STATE_ID_COLUMN,
            BaseColumns._ID
        )

        val cursor = pllDBService.readableDatabase.query(
            SolvesDatabaseConstants.PLLTable.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        assert(!cursor.moveToFirst())
    }

    @Test
    fun getPLLDataTest() {
        val pllData = PLLData(
            solveID = 1,
            duration = 1000,
            moveCount = 10,
            startStateID = 1,
            endStateID = 11,
            case = 1
        )

        val id = pllDBService.addPLLData(pllData)

        val pllDataFromDB = pllDBService.getPLLData(id)

        assertPLLDataEquals(pllDataFromDB, pllData, id)
    }

    @Test
    fun getPLLDataFailInvalidID() {
        val pllData = PLLData(
            solveID = 1,
            duration = 1000,
            moveCount = 10,
            startStateID = 1,
            endStateID = 11,
            case = 1
        )

        val id = pllDBService.addPLLData(pllData)

        val pllDataFromDB = pllDBService.getPLLData(id + 1)

        assert(pllDataFromDB == null)
    }

    @Test
    fun deletePLLData() {
        val pllData = PLLData(
            solveID = 1,
            duration = 1000,
            moveCount = 10,
            startStateID = 1,
            endStateID = 11,
            case = 1
        )

        val id = pllDBService.addPLLData(pllData)

        pllDBService.deletePLLData(id)

        val pllDataFromDB = pllDBService.getPLLData(id)

        assert(pllDataFromDB == null)
    }

    @Test
    fun deletePLLDataFailInvalidID() {
        val pllData = PLLData(
            solveID = 1,
            duration = 1000,
            moveCount = 10,
            startStateID = 1,
            endStateID = 11,
            case = 1
        )

        val id = pllDBService.addPLLData(pllData)

        pllDBService.deletePLLData(id + 1)

        val pllDataFromDB = pllDBService.getPLLData(id)

        assert(pllDataFromDB != null)
    }

    @Test
    fun updatePLLDataTest() {
        val pllData = PLLData(
            solveID = 1,
            duration = 1000,
            moveCount = 10,
            startStateID = 1,
            endStateID = 11,
            case = 1
        )

        val id = pllDBService.addPLLData(pllData)

        val newPLLData = PLLData(
            solveID = 2,
            duration = 2000,
            moveCount = 20,
            startStateID = 2,
            endStateID = 22,
            case = 2,
            id = id
        )

        pllDBService.updatePLLData(newPLLData, id)

        val pllDataFromDB = pllDBService.getPLLData(id)

        assertPLLDataEquals(pllDataFromDB, newPLLData, id)
    }

    @Test
    fun updatePLLDataFailInvalidDuration() {
        val pllData = PLLData(
            solveID = 1,
            duration = 1000,
            moveCount = 10,
            startStateID = 1,
            endStateID = 11,
            case = 1
        )

        val id = pllDBService.addPLLData(pllData)

        val newPLLData = PLLData(
            solveID = 2,
            duration = -2000,
            moveCount = 20,
            startStateID = 2,
            endStateID = 22,
            case = 2,
            id = id
        )

        Assert.assertThrows(IllegalArgumentException::class.java) {
            pllDBService.updatePLLData(newPLLData, id)
        }

        val pllDataFromDB = pllDBService.getPLLData(id)

        assertPLLDataEquals(pllDataFromDB, pllData, id)
    }

    @Test
    fun updatePLLDataFailInvalidMoveCount() {
        val pllData = PLLData(
            solveID = 1,
            duration = 1000,
            moveCount = 10,
            startStateID = 1,
            endStateID = 11,
            case = 1
        )

        val id = pllDBService.addPLLData(pllData)

        val newPLLData = PLLData(
            solveID = 2,
            duration = 2000,
            moveCount = -20,
            startStateID = 2,
            endStateID = 22,
            case = 2,
            id = id
        )

        Assert.assertThrows(IllegalArgumentException::class.java) {
            pllDBService.updatePLLData(newPLLData, id)
        }

        val pllDataFromDB = pllDBService.getPLLData(id)

        assertPLLDataEquals(pllDataFromDB, pllData, id)
    }

    @Test
    fun updateF2lDataFailInvalidID() {
        val pllData = PLLData(
            solveID = 1,
            duration = 2000,
            moveCount = 20,
            startStateID = 2,
            endStateID = 22,
            case = 1
        )

        val id = pllDBService.addPLLData(pllData)

        val newPLLData = PLLData(
            solveID = 2,
            duration = 2000,
            moveCount = 20,
            startStateID = 2,
            endStateID = 22,
            case = 2,
            id = id
        )

        pllDBService.updatePLLData(newPLLData, id + 1)

        val pllDataFromDB = pllDBService.getPLLData(id)

        assertPLLDataEquals(pllDataFromDB, pllData, id)
    }

    @Test
    fun updatePLLDataFailInvalidCase(){
        val pllData = PLLData(
            solveID = 1,
            duration = 2000,
            moveCount = 20,
            startStateID = 2,
            endStateID = 22,
            case = 1
        )

        val id = pllDBService.addPLLData(pllData)

        val newPLLData = PLLData(
            solveID = 2,
            duration = 2000,
            moveCount = 20,
            startStateID = 2,
            endStateID = 22,
            case = 57,
            id = id
        )

        Assert.assertThrows(IllegalArgumentException::class.java) {
            pllDBService.updatePLLData(newPLLData, id)
        }

        val pllDataFromDB = pllDBService.getPLLData(id)

        assertPLLDataEquals(pllDataFromDB, pllData, id)
    }

    private fun assertPLLDataEquals(retrievedData: PLLData?, expectedData: PLLData, id: Long) {
        if (retrievedData == null) {
            TestCase.fail()
            return
        }
        assert(retrievedData.id == id)
        assert(retrievedData.solveID == expectedData.solveID)
        assert(retrievedData.duration == expectedData.duration)
        assert(retrievedData.moveCount == expectedData.moveCount)
        assert(retrievedData.startStateID == expectedData.startStateID)
        assert(retrievedData.endStateID == expectedData.endStateID)
        assert(retrievedData.case == expectedData.case)
    }
}