package com.example.smartcubeapp.correctnesstests.solvedatabasetests

import android.content.Context
import android.provider.BaseColumns
import androidx.test.platform.app.InstrumentationRegistry
import com.example.smartcubeapp.solvedatabase.SolvesDatabaseConstants
import com.example.smartcubeapp.solvedatabase.dataclasses.OLLData
import com.example.smartcubeapp.solvedatabase.services.OLLDBService
import junit.framework.TestCase
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class OLLDBServiceTests {

    private lateinit var context: Context
    private lateinit var ollDBService: OLLDBService

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        ollDBService = OLLDBService(context, SolvesDatabaseConstants.TEST_DATABASE_NAME)
    }

    @After
    fun tearDown() {
        ollDBService.close()
        context.deleteDatabase(SolvesDatabaseConstants.TEST_DATABASE_NAME)
    }

    @Test
    fun addOLLDataTest() {
        val ollData = OLLData(
            solveID = 1,
            duration = 1000,
            moveCount = 10,
            startStateID = 1,
            endStateID = 11,
            case = 1
        )

        val id = ollDBService.addOLLData(ollData)

        val projection = arrayOf(
            SolvesDatabaseConstants.OLLTable.SOLVE_ID_COLUMN,
            SolvesDatabaseConstants.OLLTable.DURATION_COLUMN,
            SolvesDatabaseConstants.OLLTable.MOVE_COUNT_COLUMN,
            SolvesDatabaseConstants.OLLTable.END_CUBE_STATE_ID_COLUMN,
            SolvesDatabaseConstants.OLLTable.START_CUBE_STATE_ID_COLUMN,
            SolvesDatabaseConstants.OLLTable.CASE_COLUMN,
            BaseColumns._ID
        )

        val cursor = ollDBService.readableDatabase.query(
            SolvesDatabaseConstants.OLLTable.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        with(cursor) {
            if (moveToFirst()) {
                assert(getLong(getColumnIndex(SolvesDatabaseConstants.OLLTable.SOLVE_ID_COLUMN)) == ollData.solveID)
                assert(getLong(getColumnIndex(SolvesDatabaseConstants.OLLTable.DURATION_COLUMN)) == ollData.duration)
                assert(getInt(getColumnIndex(SolvesDatabaseConstants.OLLTable.MOVE_COUNT_COLUMN)) == ollData.moveCount)
                assert(getLong(getColumnIndex(SolvesDatabaseConstants.OLLTable.END_CUBE_STATE_ID_COLUMN)) == ollData.endStateID)
                assert(getLong(getColumnIndex(SolvesDatabaseConstants.OLLTable.START_CUBE_STATE_ID_COLUMN)) == ollData.startStateID)
                assert(getInt(getColumnIndex(SolvesDatabaseConstants.OLLTable.CASE_COLUMN)) == ollData.case)
                assert(getLong(getColumnIndex(BaseColumns._ID)) == id)
            } else {
                TestCase.fail()
            }
        }
    }

    @Test
    fun addOLLDataFailInvalidDuration() {
        val ollData = OLLData(
            solveID = 1,
            duration = -1000,
            moveCount = 10,
            startStateID = 1,
            endStateID = 11,
            case = 1
        )

        Assert.assertThrows(IllegalArgumentException::class.java) {
            ollDBService.addOLLData(ollData)
        }

        val projection = arrayOf(
            SolvesDatabaseConstants.OLLTable.SOLVE_ID_COLUMN,
            SolvesDatabaseConstants.OLLTable.DURATION_COLUMN,
            SolvesDatabaseConstants.OLLTable.MOVE_COUNT_COLUMN,
            SolvesDatabaseConstants.OLLTable.END_CUBE_STATE_ID_COLUMN,
            SolvesDatabaseConstants.OLLTable.START_CUBE_STATE_ID_COLUMN,
            BaseColumns._ID
        )

        val cursor = ollDBService.readableDatabase.query(
            SolvesDatabaseConstants.OLLTable.TABLE_NAME,
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
    fun addOLLDataFailInvalidMoveCount() {
        val ollData = OLLData(
            solveID = 1,
            duration = 1000,
            moveCount = -10,
            startStateID = 1,
            endStateID = 11,
            case = 1
        )

        Assert.assertThrows(IllegalArgumentException::class.java) {
            ollDBService.addOLLData(ollData)
        }

        val projection = arrayOf(
            SolvesDatabaseConstants.OLLTable.SOLVE_ID_COLUMN,
            SolvesDatabaseConstants.OLLTable.DURATION_COLUMN,
            SolvesDatabaseConstants.OLLTable.MOVE_COUNT_COLUMN,
            SolvesDatabaseConstants.OLLTable.END_CUBE_STATE_ID_COLUMN,
            SolvesDatabaseConstants.OLLTable.START_CUBE_STATE_ID_COLUMN,
            BaseColumns._ID
        )

        val cursor = ollDBService.readableDatabase.query(
            SolvesDatabaseConstants.OLLTable.TABLE_NAME,
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
    fun addOLLDataFailInvalidCase(){
        val ollData = OLLData(
            solveID = 1,
            duration = 1000,
            moveCount = 10,
            startStateID = 1,
            endStateID = 11,
            case = 57
        )

        Assert.assertThrows(IllegalArgumentException::class.java) {
            ollDBService.addOLLData(ollData)
        }

        val projection = arrayOf(
            SolvesDatabaseConstants.OLLTable.SOLVE_ID_COLUMN,
            SolvesDatabaseConstants.OLLTable.DURATION_COLUMN,
            SolvesDatabaseConstants.OLLTable.MOVE_COUNT_COLUMN,
            SolvesDatabaseConstants.OLLTable.END_CUBE_STATE_ID_COLUMN,
            SolvesDatabaseConstants.OLLTable.START_CUBE_STATE_ID_COLUMN,
            BaseColumns._ID
        )

        val cursor = ollDBService.readableDatabase.query(
            SolvesDatabaseConstants.OLLTable.TABLE_NAME,
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
    fun getOLLDataTest() {
        val ollData = OLLData(
            solveID = 1,
            duration = 1000,
            moveCount = 10,
            startStateID = 1,
            endStateID = 11,
            case = 1
        )

        val id = ollDBService.addOLLData(ollData)

        val ollDataFromDB = ollDBService.getOLLData(id)

        assertOLLDataEquals(ollDataFromDB, ollData, id)
    }

    @Test
    fun getOLLDataFailInvalidID() {
        val ollData = OLLData(
            solveID = 1,
            duration = 1000,
            moveCount = 10,
            startStateID = 1,
            endStateID = 11,
            case = 1
        )

        val id = ollDBService.addOLLData(ollData)

        val ollDataFromDB = ollDBService.getOLLData(id + 1)

        assert(ollDataFromDB == null)
    }

    @Test
    fun deleteOLLData() {
        val ollData = OLLData(
            solveID = 1,
            duration = 1000,
            moveCount = 10,
            startStateID = 1,
            endStateID = 11,
            case = 1
        )

        val id = ollDBService.addOLLData(ollData)

        ollDBService.deleteOLLData(id)

        val ollDataFromDB = ollDBService.getOLLData(id)

        assert(ollDataFromDB == null)
    }

    @Test
    fun deleteOLLDataFailInvalidID() {
        val ollData = OLLData(
            solveID = 1,
            duration = 1000,
            moveCount = 10,
            startStateID = 1,
            endStateID = 11,
            case = 1
        )

        val id = ollDBService.addOLLData(ollData)

        ollDBService.deleteOLLData(id + 1)

        val ollDataFromDB = ollDBService.getOLLData(id)

        assert(ollDataFromDB != null)
    }

    @Test
    fun updateOLLDataTest() {
        val ollData = OLLData(
            solveID = 1,
            duration = 1000,
            moveCount = 10,
            startStateID = 1,
            endStateID = 11,
            case = 1
        )

        val id = ollDBService.addOLLData(ollData)

        val newOLLData = OLLData(
            solveID = 2,
            duration = 2000,
            moveCount = 20,
            startStateID = 2,
            endStateID = 22,
            case = 2,
            id = id
        )

        ollDBService.updateOLLData(newOLLData, id)

        val ollDataFromDB = ollDBService.getOLLData(id)

        assertOLLDataEquals(ollDataFromDB, newOLLData, id)
    }

    @Test
    fun updateOLLDataFailInvalidDuration() {
        val ollData = OLLData(
            solveID = 1,
            duration = 1000,
            moveCount = 10,
            startStateID = 1,
            endStateID = 11,
            case = 1
        )

        val id = ollDBService.addOLLData(ollData)

        val newOLLData = OLLData(
            solveID = 2,
            duration = -2000,
            moveCount = 20,
            startStateID = 2,
            endStateID = 22,
            case = 2,
            id = id
        )

        Assert.assertThrows(IllegalArgumentException::class.java) {
            ollDBService.updateOLLData(newOLLData, id)
        }

        val ollDataFromDB = ollDBService.getOLLData(id)

        assertOLLDataEquals(ollDataFromDB, ollData, id)
    }

    @Test
    fun updateOLLDataFailInvalidMoveCount() {
        val ollData = OLLData(
            solveID = 1,
            duration = 1000,
            moveCount = 10,
            startStateID = 1,
            endStateID = 11,
            case = 1
        )

        val id = ollDBService.addOLLData(ollData)

        val newOLLData = OLLData(
            solveID = 2,
            duration = 2000,
            moveCount = -20,
            startStateID = 2,
            endStateID = 22,
            case = 2,
            id = id
        )

        Assert.assertThrows(IllegalArgumentException::class.java) {
            ollDBService.updateOLLData(newOLLData, id)
        }

        val ollDataFromDB = ollDBService.getOLLData(id)

        assertOLLDataEquals(ollDataFromDB, ollData, id)
    }

    @Test
    fun updateF2lDataFailInvalidID() {
        val ollData = OLLData(
            solveID = 1,
            duration = 2000,
            moveCount = 20,
            startStateID = 2,
            endStateID = 22,
            case = 1
        )

        val id = ollDBService.addOLLData(ollData)

        val newOLLData = OLLData(
            solveID = 2,
            duration = 2000,
            moveCount = 20,
            startStateID = 2,
            endStateID = 22,
            case = 2,
            id = id
        )

        ollDBService.updateOLLData(newOLLData, id + 1)

        val ollDataFromDB = ollDBService.getOLLData(id)

        assertOLLDataEquals(ollDataFromDB, ollData, id)
    }

    @Test
    fun updateOLLDataFailInvalidCase(){
        val ollData = OLLData(
            solveID = 1,
            duration = 2000,
            moveCount = 20,
            startStateID = 2,
            endStateID = 22,
            case = 1
        )

        val id = ollDBService.addOLLData(ollData)

        val newOLLData = OLLData(
            solveID = 2,
            duration = 2000,
            moveCount = 20,
            startStateID = 2,
            endStateID = 22,
            case = 57,
            id = id
        )

        Assert.assertThrows(IllegalArgumentException::class.java) {
            ollDBService.updateOLLData(newOLLData, id)
        }

        val ollDataFromDB = ollDBService.getOLLData(id)

        assertOLLDataEquals(ollDataFromDB, ollData, id)
    }

    @Test
    fun getOLLForSolveTest(){
        val ollData1 = OLLData(
            solveID = 1,
            duration = 1000,
            moveCount = 10,
            startStateID = 1,
            endStateID = 11,
            case = 1
        )

        val ollData2 = OLLData(
            solveID = 2,
            duration = 2000,
            moveCount = 20,
            startStateID = 2,
            endStateID = 22,
            case = 1
        )

        val id1 = ollDBService.addOLLData(ollData1)
        ollDBService.addOLLData(ollData2)

        val ollForSolveID = ollDBService.getOLLForSolve(1)

        if(ollForSolveID == null){
            TestCase.fail()
            return
        }
        assertOLLDataEquals(ollForSolveID, ollData1, id1)
    }

    @Test
    fun getOLLDataForSolveFailInvalidID(){
        val ollData1 = OLLData(
            solveID = 1,
            duration = 1000,
            moveCount = 10,
            startStateID = 1,
            endStateID = 11,
            case = 1
        )

        val ollData2 = OLLData(
            solveID = 2,
            duration = 2000,
            moveCount = 20,
            startStateID = 2,
            endStateID = 22,
            case = 1
        )

        val id1 = ollDBService.addOLLData(ollData1)
        ollDBService.addOLLData(ollData2)

        val ollForSolveID = ollDBService.getOLLForSolve(3)

        assert(ollForSolveID == null)
    }

    @Test
    fun deleteOLLDataForSolveTest(){
        val ollData1 = OLLData(
            solveID = 1,
            duration = 1000,
            moveCount = 10,
            startStateID = 1,
            endStateID = 11,
            case = 1,
        )
        val ollData2 = OLLData(
            solveID = 1,
            duration = 2000,
            moveCount = 20,
            startStateID = 2,
            endStateID = 22,
            case = 1,
        )
        val ollData3 = OLLData(
            solveID = 2,
            duration = 3000,
            moveCount = 30,
            startStateID = 3,
            endStateID = 33,
            case = 1,
        )

        ollDBService.addOLLData(ollData1)
        ollDBService.addOLLData(ollData2)
        val id3 = ollDBService.addOLLData(ollData3)

        ollDBService.deleteOLLDataForSolve(1)

        val ollDataForSolve1 = ollDBService.getOLLForSolve(1)
        assertNull(ollDataForSolve1)
        val ollDataForSolve2 = ollDBService.getOLLForSolve(2)
        assertOLLDataEquals(ollDataForSolve2, ollData3, id3)

    }

    private fun assertOLLDataEquals(retrievedData: OLLData?, expectedData: OLLData, id: Long) {
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
