package com.example.smartcubeapp.correctnesstests.solvedatabasetests

import android.content.Context
import android.provider.BaseColumns
import androidx.test.platform.app.InstrumentationRegistry
import com.example.smartcubeapp.solvedatabase.SolvesDatabaseConstants
import com.example.smartcubeapp.solvedatabase.dataclasses.CrossData
import com.example.smartcubeapp.solvedatabase.services.CrossDBService
import junit.framework.TestCase
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class CrossDBServiceTests {

    private lateinit var context: Context
    private lateinit var crossDBService: CrossDBService


    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        crossDBService = CrossDBService(context, SolvesDatabaseConstants.TEST_DATABASE_NAME)
    }

    @After
    fun tearDown() {
        crossDBService.close()
        context.deleteDatabase(SolvesDatabaseConstants.TEST_DATABASE_NAME)
    }

    fun addCrossDataTest() {
        val crossData = CrossData(
            solveID = 1,
            duration = 1000,
            moveCount = 10,
            startStateID = 1,
            endStateID = 11
        )

        val id = crossDBService.addCrossData(crossData)

        val projection = arrayOf(
            SolvesDatabaseConstants.CrossTable.SOLVE_ID_COLUMN,
            SolvesDatabaseConstants.CrossTable.DURATION_COLUMN,
            SolvesDatabaseConstants.CrossTable.MOVE_COUNT_COLUMN,
            SolvesDatabaseConstants.CrossTable.END_CUBE_STATE_ID_COLUMN,
            SolvesDatabaseConstants.CrossTable.START_CUBE_STATE_ID_COLUMN,
            BaseColumns._ID
        )

        val cursor = crossDBService.readableDatabase.query(
            SolvesDatabaseConstants.CrossTable.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        with(cursor) {
            if (moveToFirst()) {
                assert(getLong(getColumnIndex(com.example.smartcubeapp.solvedatabase.SolvesDatabaseConstants.CrossTable.SOLVE_ID_COLUMN)) == crossData.solveID)
                assert(getLong(getColumnIndex(com.example.smartcubeapp.solvedatabase.SolvesDatabaseConstants.CrossTable.DURATION_COLUMN)) == crossData.duration)
                assert(getInt(getColumnIndex(com.example.smartcubeapp.solvedatabase.SolvesDatabaseConstants.CrossTable.MOVE_COUNT_COLUMN)) == crossData.moveCount)
                assert(getLong(getColumnIndex(com.example.smartcubeapp.solvedatabase.SolvesDatabaseConstants.CrossTable.END_CUBE_STATE_ID_COLUMN)) == crossData.endStateID)
                assert(getLong(getColumnIndex(com.example.smartcubeapp.solvedatabase.SolvesDatabaseConstants.CrossTable.START_CUBE_STATE_ID_COLUMN)) == crossData.startStateID)
                assert(getLong(getColumnIndex(android.provider.BaseColumns._ID)) == id)
            } else {
                junit.framework.TestCase.fail()
            }
        }
    }

    @Test
    fun addCrossDataFailInvalidDuration(){
        val crossData = CrossData(
            solveID = 1,
            duration = -1000,
            moveCount = 10,
            startStateID = 1,
            endStateID = 11
        )

        Assert.assertThrows(IllegalArgumentException::class.java) {
            val id = crossDBService.addCrossData(crossData)
        }

        val projection = arrayOf(
            SolvesDatabaseConstants.CrossTable.SOLVE_ID_COLUMN,
            SolvesDatabaseConstants.CrossTable.DURATION_COLUMN,
            SolvesDatabaseConstants.CrossTable.MOVE_COUNT_COLUMN,
            SolvesDatabaseConstants.CrossTable.END_CUBE_STATE_ID_COLUMN,
            SolvesDatabaseConstants.CrossTable.START_CUBE_STATE_ID_COLUMN,
            BaseColumns._ID
        )

        val cursor = crossDBService.readableDatabase.query(
            SolvesDatabaseConstants.CrossTable.TABLE_NAME,
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
    fun addCrossDataFailInvalidMoveCount(){
        val crossData = CrossData(
            solveID = 1,
            duration = 1000,
            moveCount = -10,
            startStateID = 1,
            endStateID = 11
        )

        Assert.assertThrows(IllegalArgumentException::class.java) {
            val id = crossDBService.addCrossData(crossData)
        }

        val projection = arrayOf(
            SolvesDatabaseConstants.CrossTable.SOLVE_ID_COLUMN,
            SolvesDatabaseConstants.CrossTable.DURATION_COLUMN,
            SolvesDatabaseConstants.CrossTable.MOVE_COUNT_COLUMN,
            SolvesDatabaseConstants.CrossTable.END_CUBE_STATE_ID_COLUMN,
            SolvesDatabaseConstants.CrossTable.START_CUBE_STATE_ID_COLUMN,
            BaseColumns._ID
        )

        val cursor = crossDBService.readableDatabase.query(
            SolvesDatabaseConstants.CrossTable.TABLE_NAME,
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
    fun getCrossDataTest(){
        val crossData = CrossData(
            solveID = 1,
            duration = 1000,
            moveCount = 10,
            startStateID = 1,
            endStateID = 11
        )

        val id = crossDBService.addCrossData(crossData)

        val crossDataFromDB = crossDBService.getCrossData(id)

        assertCrossDataEquals(crossDataFromDB, crossData, id)
    }

    @Test
    fun getCrossDataFailInvalidID(){
        val crossData = CrossData(
            solveID = 1,
            duration = 1000,
            moveCount = 10,
            startStateID = 1,
            endStateID = 11
        )

        val id = crossDBService.addCrossData(crossData)

        val crossDataFromDB = crossDBService.getCrossData(id + 1)

        assert(crossDataFromDB == null)
    }

    @Test
    fun deleteCrossData(){
        val crossData = CrossData(
            solveID = 1,
            duration = 1000,
            moveCount = 10,
            startStateID = 1,
            endStateID = 11
        )

        val id = crossDBService.addCrossData(crossData)

        crossDBService.deleteCrossData(id)

        val crossDataFromDB = crossDBService.getCrossData(id)

        assert(crossDataFromDB == null)
    }

    @Test
    fun deleteCrossDataFailInvalidID(){
        val crossData = CrossData(
            solveID = 1,
            duration = 1000,
            moveCount = 10,
            startStateID = 1,
            endStateID = 11
        )

        val id = crossDBService.addCrossData(crossData)

        crossDBService.deleteCrossData(id + 1)

        val crossDataFromDB = crossDBService.getCrossData(id)

        assert(crossDataFromDB != null)
    }

    @Test
    fun updateCrossDataTest(){
        val crossData = CrossData(
            solveID = 1,
            duration = 1000,
            moveCount = 10,
            startStateID = 1,
            endStateID = 11
        )

        val id = crossDBService.addCrossData(crossData)

        val newCrossData = CrossData(
            solveID = 2,
            duration = 2000,
            moveCount = 20,
            startStateID = 2,
            endStateID = 22,
            id = id
        )

        crossDBService.updateCrossData(newCrossData, id)

        val crossDataFromDB = crossDBService.getCrossData(id)

        assertCrossDataEquals(crossDataFromDB, newCrossData, id)
    }

    @Test
    fun updateCrossDataFailInvalidDuration(){
        val crossData = CrossData(
            solveID = 1,
            duration = 1000,
            moveCount = 10,
            startStateID = 1,
            endStateID = 11
        )

        val id = crossDBService.addCrossData(crossData)

        val newCrossData = CrossData(
            solveID = 2,
            duration = -2000,
            moveCount = 20,
            startStateID = 2,
            endStateID = 22,
            id = id
        )

        Assert.assertThrows(IllegalArgumentException::class.java) {
            crossDBService.updateCrossData(newCrossData, id)
        }

        val crossDataFromDB = crossDBService.getCrossData(id)

        assertCrossDataEquals(crossDataFromDB, crossData, id)
    }

    @Test
    fun updateCrossDataFailInvalidMoveCount(){
        val crossData = CrossData(
            solveID = 1,
            duration = 1000,
            moveCount = 10,
            startStateID = 1,
            endStateID = 11
        )

        val id = crossDBService.addCrossData(crossData)

        val newCrossData = CrossData(
            solveID = 2,
            duration = 2000,
            moveCount = -20,
            startStateID = 2,
            endStateID = 22,
            id = id
        )

        Assert.assertThrows(IllegalArgumentException::class.java) {
            crossDBService.updateCrossData(newCrossData, id)
        }

        val crossDataFromDB = crossDBService.getCrossData(id)

        assertCrossDataEquals(crossDataFromDB, crossData, id)
    }

    @Test
    fun updateF2lDataFailInvalidID(){
        val crossData = CrossData(
            solveID = 1,
            duration = 2000,
            moveCount = 20,
            startStateID = 2,
            endStateID = 22
        )

        val id = crossDBService.addCrossData(crossData)

        val newCrossData = CrossData(
            solveID = 2,
            duration = 2000,
            moveCount = 20,
            startStateID = 2,
            endStateID = 22,
            id = id
        )

        crossDBService.updateCrossData(crossData, id)

        val crossDataFromDB = crossDBService.getCrossData(id)

        assertCrossDataEquals(crossDataFromDB, crossData, id)
    }

    @Test
    fun getCrossForSolveTest(){
        val crossData1 = CrossData(
            solveID = 1,
            duration = 1000,
            moveCount = 10,
            startStateID = 1,
            endStateID = 11
        )

        val crossData2 = CrossData(
            solveID = 2,
            duration = 2000,
            moveCount = 20,
            startStateID = 2,
            endStateID = 22
        )

        val id1 = crossDBService.addCrossData(crossData1)
        crossDBService.addCrossData(crossData2)

        val crossForSolveID = crossDBService.getCrossForSolve(1)

        if(crossForSolveID == null){
            TestCase.fail()
            return
        }
        assertCrossDataEquals(crossForSolveID, crossData1, id1)
    }

    @Test
    fun getCrossDataForSolveFailInvalidID(){
        val crossData1 = CrossData(
            solveID = 1,
            duration = 1000,
            moveCount = 10,
            startStateID = 1,
            endStateID = 11
        )

        val crossData2 = CrossData(
            solveID = 2,
            duration = 2000,
            moveCount = 20,
            startStateID = 2,
            endStateID = 22
        )

        val id1 = crossDBService.addCrossData(crossData1)
        crossDBService.addCrossData(crossData2)

        val crossForSolveID = crossDBService.getCrossForSolve(3)

        assert(crossForSolveID == null)
    }

    @Test
    fun deleteCrossDataForSolveTest(){
        val crossData1 = CrossData(
            solveID = 1,
            duration = 1000,
            moveCount = 10,
            startStateID = 1,
            endStateID = 11
        )
        val crossData2 = CrossData(
            solveID = 2,
            duration = 2000,
            moveCount = 20,
            startStateID = 2,
            endStateID = 22
        )
        val crossData3 = CrossData(
            solveID = 3,
            duration = 3000,
            moveCount = 30,
            startStateID = 3,
            endStateID = 33
        )

        val id1 = crossDBService.addCrossData(crossData1)
        val id2 = crossDBService.addCrossData(crossData2)
        crossDBService.addCrossData(crossData3)

        crossDBService.deleteCrossDataForSolve(3)

        val crossDataForSolve3 = crossDBService.getCrossForSolve(3)
        assertNull(crossDataForSolve3)

        val crossDataForSolve1 = crossDBService.getCrossForSolve(1)
        assertCrossDataEquals(crossDataForSolve1, crossData1, id1)
        val crossDataForSolve2 = crossDBService.getCrossForSolve(2)
        assertCrossDataEquals(crossDataForSolve2, crossData2, id2)
    }

    private fun assertCrossDataEquals(retrievedData: CrossData?, expectedData: CrossData, id: Long){
        if(retrievedData == null){
            TestCase.fail()
            return
        }
        assert(retrievedData.id == id)
        assert(retrievedData.solveID == expectedData.solveID)
        assert(retrievedData.duration == expectedData.duration)
        assert(retrievedData.moveCount == expectedData.moveCount)
        assert(retrievedData.startStateID == expectedData.startStateID)
        assert(retrievedData.endStateID == expectedData.endStateID)
    }
}