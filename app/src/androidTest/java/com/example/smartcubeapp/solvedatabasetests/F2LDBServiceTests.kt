package com.example.smartcubeapp.solvedatabasetests

import android.content.Context
import android.provider.BaseColumns
import androidx.test.platform.app.InstrumentationRegistry
import com.example.smartcubeapp.cube.CubeState
import com.example.smartcubeapp.solvedatabase.SolvesDatabaseConstants
import com.example.smartcubeapp.solvedatabase.dataclasses.F2LData
import com.example.smartcubeapp.solvedatabase.services.F2LDBService
import junit.framework.TestCase
import org.junit.After
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

class F2LDBServiceTests {

    private lateinit var context: Context
    private lateinit var f2lDBService: F2LDBService

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        f2lDBService = F2LDBService(context, SolvesDatabaseConstants.TEST_DATABASE_NAME)
    }

    @After
    fun tearDown() {
        f2lDBService.close()
        context.deleteDatabase(SolvesDatabaseConstants.TEST_DATABASE_NAME)
    }

    fun addF2LDataTest() {
        val f2lData = F2LData(
            solveID = 1,
            duration = 1000,
            moveCount = 10,
            startStateID = 1,
            endStateID = 11
        )

        val id = f2lDBService.addF2LData(f2lData)

        val projection = arrayOf(
            SolvesDatabaseConstants.F2LTable.SOLVE_ID_COLUMN,
            SolvesDatabaseConstants.F2LTable.DURATION_COLUMN,
            SolvesDatabaseConstants.F2LTable.MOVE_COUNT_COLUMN,
            SolvesDatabaseConstants.F2LTable.END_CUBE_STATE_ID_COLUMN,
            SolvesDatabaseConstants.F2LTable.START_CUBE_STATE_ID_COLUMN,
            BaseColumns._ID
        )

        val cursor = f2lDBService.readableDatabase.query(
            SolvesDatabaseConstants.F2LTable.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        with(cursor) {
            if (moveToFirst()) {
                assert(getLong(getColumnIndex(SolvesDatabaseConstants.F2LTable.SOLVE_ID_COLUMN)) == f2lData.solveID)
                assert(getLong(getColumnIndex(SolvesDatabaseConstants.F2LTable.DURATION_COLUMN)) == f2lData.duration)
                assert(getLong(getColumnIndex(SolvesDatabaseConstants.F2LTable.MOVE_COUNT_COLUMN)) == f2lData.moveCount)
                assert(getLong(getColumnIndex(SolvesDatabaseConstants.F2LTable.END_CUBE_STATE_ID_COLUMN)) == f2lData.endStateID)
                assert(getLong(getColumnIndex(SolvesDatabaseConstants.F2LTable.START_CUBE_STATE_ID_COLUMN)) == f2lData.startStateID)
                assert(getLong(getColumnIndex(BaseColumns._ID)) == id)
            } else {
                TestCase.fail()
            }
        }
    }

    @Test
    fun addF2LDataFailInvalidDuration(){
        val f2lData = F2LData(
            solveID = 1,
            duration = -1000,
            moveCount = 10,
            startStateID = 1,
            endStateID = 11
        )

        assertThrows(IllegalArgumentException::class.java){
            val id = f2lDBService.addF2LData(f2lData)
        }

        val projection = arrayOf(
            SolvesDatabaseConstants.F2LTable.SOLVE_ID_COLUMN,
            SolvesDatabaseConstants.F2LTable.DURATION_COLUMN,
            SolvesDatabaseConstants.F2LTable.MOVE_COUNT_COLUMN,
            SolvesDatabaseConstants.F2LTable.END_CUBE_STATE_ID_COLUMN,
            SolvesDatabaseConstants.F2LTable.START_CUBE_STATE_ID_COLUMN,
            BaseColumns._ID
        )

        val cursor = f2lDBService.readableDatabase.query(
            SolvesDatabaseConstants.F2LTable.TABLE_NAME,
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
    fun addF2LDataFailInvalidMoveCount(){
        val f2lData = F2LData(
            solveID = 1,
            duration = 1000,
            moveCount = -10,
            startStateID = 1,
            endStateID = 11
        )

        assertThrows(IllegalArgumentException::class.java){
            val id = f2lDBService.addF2LData(f2lData)
        }

        val projection = arrayOf(
            SolvesDatabaseConstants.F2LTable.SOLVE_ID_COLUMN,
            SolvesDatabaseConstants.F2LTable.DURATION_COLUMN,
            SolvesDatabaseConstants.F2LTable.MOVE_COUNT_COLUMN,
            SolvesDatabaseConstants.F2LTable.END_CUBE_STATE_ID_COLUMN,
            SolvesDatabaseConstants.F2LTable.START_CUBE_STATE_ID_COLUMN,
            BaseColumns._ID
        )

        val cursor = f2lDBService.readableDatabase.query(
            SolvesDatabaseConstants.F2LTable.TABLE_NAME,
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
    fun getF2LDataTest(){
        val f2lData = F2LData(
            solveID = 1,
            duration = 1000,
            moveCount = 10,
            startStateID = 1,
            endStateID = 11
        )

        val id = f2lDBService.addF2LData(f2lData)

        val f2lDataFromDB = f2lDBService.getF2LData(id)

        assertF2LDataEquals(f2lDataFromDB, f2lData, id)
    }

    @Test
    fun getF2LDataFailInvalidID(){
        val f2lData = F2LData(
            solveID = 1,
            duration = 1000,
            moveCount = 10,
            startStateID = 1,
            endStateID = 11
        )

        val id = f2lDBService.addF2LData(f2lData)

        val f2lDataFromDB = f2lDBService.getF2LData(id + 1)

        assert(f2lDataFromDB == null)
    }

    @Test
    fun deleteF2LData(){
        val f2lData = F2LData(
            solveID = 1,
            duration = 1000,
            moveCount = 10,
            startStateID = 1,
            endStateID = 11
        )

        val id = f2lDBService.addF2LData(f2lData)

        f2lDBService.deleteF2LData(id)

        val f2lDataFromDB = f2lDBService.getF2LData(id)

        assert(f2lDataFromDB == null)
    }

    @Test
    fun deleteF2LDataFailInvalidID(){
        val f2lData = F2LData(
            solveID = 1,
            duration = 1000,
            moveCount = 10,
            startStateID = 1,
            endStateID = 11
        )

        val id = f2lDBService.addF2LData(f2lData)

        f2lDBService.deleteF2LData(id + 1)

        val f2lDataFromDB = f2lDBService.getF2LData(id)

        assert(f2lDataFromDB != null)
    }

    @Test
    fun updateF2LDataTest(){
        val f2lData = F2LData(
            solveID = 1,
            duration = 1000,
            moveCount = 10,
            startStateID = 1,
            endStateID = 11
        )

        val id = f2lDBService.addF2LData(f2lData)

        val newF2LData = F2LData(
            solveID = 2,
            duration = 2000,
            moveCount = 20,
            startStateID = 2,
            endStateID = 22,
            id = id
        )

        f2lDBService.updateF2LData(newF2LData, id)

        val f2lDataFromDB = f2lDBService.getF2LData(id)

        assertF2LDataEquals(f2lDataFromDB, newF2LData, id)
    }

    @Test
    fun updateF2LDataFailInvalidDuration(){
        val f2lData = F2LData(
            solveID = 1,
            duration = 1000,
            moveCount = 10,
            startStateID = 1,
            endStateID = 11
        )

        val id = f2lDBService.addF2LData(f2lData)

        val newF2LData = F2LData(
            solveID = 2,
            duration = -2000,
            moveCount = 20,
            startStateID = 2,
            endStateID = 22,
            id = id
        )

        assertThrows(IllegalArgumentException::class.java){
            f2lDBService.updateF2LData(newF2LData, id)
        }

        val f2lDataFromDB = f2lDBService.getF2LData(id)

        assertF2LDataEquals(f2lDataFromDB, f2lData, id)
    }

    @Test
    fun updateF2LDataFailInvalidMoveCount(){
        val f2lData = F2LData(
            solveID = 1,
            duration = 1000,
            moveCount = 10,
            startStateID = 1,
            endStateID = 11
        )

        val id = f2lDBService.addF2LData(f2lData)

        val newF2LData = F2LData(
            solveID = 2,
            duration = 2000,
            moveCount = -20,
            startStateID = 2,
            endStateID = 22,
            id = id
        )

        assertThrows(IllegalArgumentException::class.java){
            f2lDBService.updateF2LData(newF2LData, id)
        }

        val f2lDataFromDB = f2lDBService.getF2LData(id)

        assertF2LDataEquals(f2lDataFromDB, f2lData, id)
    }

    @Test
    fun updateF2lDataFailInvalidID(){
        val f2lData = F2LData(
            solveID = 1,
            duration = 2000,
            moveCount = 20,
            startStateID = 2,
            endStateID = 22
        )

        val id = f2lDBService.addF2LData(f2lData)

        val newF2LData = F2LData(
            solveID = 2,
            duration = 2000,
            moveCount = 20,
            startStateID = 2,
            endStateID = 22,
            id = id
        )

        f2lDBService.updateF2LData(f2lData, id)

        val f2lDataFromDB = f2lDBService.getF2LData(id)

        assertF2LDataEquals(f2lDataFromDB, f2lData, id)
    }

    private fun assertF2LDataEquals(retrievedData: F2LData?, expectedData: F2LData, id: Long){
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