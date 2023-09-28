package com.example.smartcubeapp.solvedatabasetests

import android.content.Context
import android.provider.BaseColumns
import androidx.test.platform.app.InstrumentationRegistry
import com.example.smartcubeapp.cube.CubeState
import com.example.smartcubeapp.cube.Move
import com.example.smartcubeapp.solvedatabase.SolvesDatabaseConstants
import com.example.smartcubeapp.solvedatabase.dataclasses.CubeStateData
import com.example.smartcubeapp.solvedatabase.services.CubeStateDBService
import junit.framework.TestCase
import org.junit.After
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

class CubeStateDBServiceTests {

    private lateinit var context: Context
    private lateinit var cubeStateDBService: CubeStateDBService

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        cubeStateDBService = CubeStateDBService(context, SolvesDatabaseConstants.TEST_DATABASE_NAME)
    }

    @After
    fun tearDown() {
        cubeStateDBService.close()
        context.deleteDatabase(SolvesDatabaseConstants.TEST_DATABASE_NAME)
    }

    @Test
    fun addCubeStateTest() {
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7),
            mutableListOf(3, 3, 3, 3, 3, 3, 3, 3),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11),
            mutableListOf(
                false, false, false, false, false, false, false, false, false, false, false, false
            ),
            Move("F", 1, "F"),
            100,
            solveID = 1
        )

        val cubeStateData = CubeStateData(cubeState, 1)

        val id = cubeStateDBService.addCubeState(cubeStateData)

        val projection = arrayOf(
            SolvesDatabaseConstants.CubeStateTable.CORNER_POSITIONS_COLUMN,
            SolvesDatabaseConstants.CubeStateTable.CORNER_ORIENTATIONS_COLUMN,
            SolvesDatabaseConstants.CubeStateTable.EDGE_POSITIONS_COLUMN,
            SolvesDatabaseConstants.CubeStateTable.EDGE_ORIENTATIONS_COLUMN,
            SolvesDatabaseConstants.CubeStateTable.LAST_MOVE_COLUMN,
            SolvesDatabaseConstants.CubeStateTable.TIMESTAMP_COLUMN,
            SolvesDatabaseConstants.CubeStateTable.SOLVE_ID_COLUMN,
            BaseColumns._ID
        )

        val cursor = cubeStateDBService.readableDatabase.query(
            SolvesDatabaseConstants.CubeStateTable.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        with(cursor) {
            if (moveToFirst()) {
                assert(
                    getString(
                        getColumnIndexOrThrow(
                            SolvesDatabaseConstants.CubeStateTable.CORNER_POSITIONS_COLUMN
                        )
                    ) == cubeStateData.cornerPositions
                )
                assert(
                    getString(
                        getColumnIndexOrThrow(
                            SolvesDatabaseConstants.CubeStateTable.CORNER_ORIENTATIONS_COLUMN
                        )
                    ) == cubeStateData.cornerOrientations
                )
                assert(
                    getString(
                        getColumnIndexOrThrow(
                            SolvesDatabaseConstants.CubeStateTable.EDGE_POSITIONS_COLUMN
                        )
                    ) == cubeStateData.edgePositions
                )
                assert(
                    getString(
                        getColumnIndexOrThrow(
                            SolvesDatabaseConstants.CubeStateTable.EDGE_ORIENTATIONS_COLUMN
                        )
                    ) == cubeStateData.edgeOrientations
                )

                assert(
                    getString(getColumnIndexOrThrow(SolvesDatabaseConstants.CubeStateTable.LAST_MOVE_COLUMN)) == cubeStateData.lastMove
                )

                assert(
                    getLong(getColumnIndexOrThrow(SolvesDatabaseConstants.CubeStateTable.TIMESTAMP_COLUMN)) == cubeStateData.timestamp
                )

                assert(
                    getLong(getColumnIndexOrThrow(SolvesDatabaseConstants.CubeStateTable.SOLVE_ID_COLUMN)) == cubeStateData.solveID
                )
                assert(getLong(getColumnIndexOrThrow(BaseColumns._ID)) == id)
            } else {
                TestCase.fail()
            }
        }
    }

    @Test
    fun addCubeStateFailInvalidTimestamp() {
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7),
            mutableListOf(3, 3, 3, 3, 3, 3, 3, 3),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11),
            mutableListOf(
                false, false, false, false, false, false, false, false, false, false, false, false
            ),
            Move("F", 1, "F"),
            0
        )

        val cubeStateData = CubeStateData(cubeState, 1)

        assertThrows(IllegalArgumentException::class.java) {
            cubeStateDBService.addCubeState(cubeStateData)
        }
    }

    @Test
    fun addCubeStateFailInvalidMoveIndex() {
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7),
            mutableListOf(3, 3, 3, 3, 3, 3, 3, 3),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11),
            mutableListOf(
                false, false, false, false, false, false, false, false, false, false, false, false
            ),
            Move("F", 1, "F"),
            100
        )

        val cubeStateData = CubeStateData(cubeState, -1)

        assertThrows(IllegalArgumentException::class.java) {
            cubeStateDBService.addCubeState(cubeStateData)
        }
    }

    @Test
    fun addCubeStateFailInvalidLastMove(){
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7),
            mutableListOf(3, 3, 3, 3, 3, 3, 3, 3),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11),
            mutableListOf(
                false, false, false, false, false, false, false, false, false, false, false, false
            ),
            Move("G", 7, "F3"),
            100
        )

        val cubeStateData = CubeStateData(cubeState, 1)

        assertThrows(IllegalArgumentException::class.java) {
            cubeStateDBService.addCubeState(cubeStateData)
        }
    }

    @Test
    fun getCubeStateTest(){
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7),
            mutableListOf(3, 3, 3, 3, 3, 3, 3, 3),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11),
            mutableListOf(
                false, false, false, false, false, false, false, false, false, false, false, false
            ),
            Move("F", 1, "F"),
            100
        )

        val cubeStateData = CubeStateData(cubeState, 1)
        val id = cubeStateDBService.addCubeState(cubeStateData)

        val retrievedCubeStateData = cubeStateDBService.getCubeState(id)

        assertCubeStatesEqual(retrievedCubeStateData, cubeStateData, id)
    }

    @Test
    fun getCubeStateFailInvalidID(){
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7),
            mutableListOf(3, 3, 3, 3, 3, 3, 3, 3),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11),
            mutableListOf(
                false, false, false, false, false, false, false, false, false, false, false, false
            ),
            Move("F", 1, "F"),
            100
        )

        val cubeStateData = CubeStateData(cubeState, 1)
        val id = cubeStateDBService.addCubeState(cubeStateData)

        val retrievedCubeStateData = cubeStateDBService.getCubeState(id + 1)
        assert(retrievedCubeStateData == null)
    }

    @Test
    fun deleteCubeStateTest(){
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7),
            mutableListOf(3, 3, 3, 3, 3, 3, 3, 3),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11),
            mutableListOf(
                false, false, false, false, false, false, false, false, false, false, false, false
            ),
            Move("F", 1, "F"),
            100
        )

        val id = cubeStateDBService.addCubeState(CubeStateData(cubeState, 1))

        cubeStateDBService.deleteCubeState(id)

        val retrieveDeleted = cubeStateDBService.getCubeState(id)
        assert(retrieveDeleted == null)
    }

    @Test
    fun deleteCubeStateFailInvalidID(){
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7),
            mutableListOf(3, 3, 3, 3, 3, 3, 3, 3),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11),
            mutableListOf(
                false, false, false, false, false, false, false, false, false, false, false, false
            ),
            Move("F", 1, "F"),
            100
        )

        val id = cubeStateDBService.addCubeState(CubeStateData(cubeState, 1))

        cubeStateDBService.deleteCubeState(id + 1)

        val retrieveDeleted = cubeStateDBService.getCubeState(id)
        assert(retrieveDeleted != null)
    }

    @Test
    fun updateCubeStateTest(){
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7),
            mutableListOf(3, 3, 3, 3, 3, 3, 3, 3),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11),
            mutableListOf(
                false, false, false, false, false, false, false, false, false, false, false, false
            ),
            Move("F", 1, "F"),
            100
        )

        val id = cubeStateDBService.addCubeState(CubeStateData(cubeState, 1))

        val newCubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 4, 5, 7, 6),
            mutableListOf(2, 2, 2, 2, 2, 2, 2, 2),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 10),
            mutableListOf(
                false, true, false, false, false, false, false, false, false, false, false, false
            ),
            Move("R", 1, "R"),
            200
        )
        val newCubeStateData = CubeStateData(newCubeState, 2)

        cubeStateDBService.updateCubeState(newCubeStateData, id)

        val retrievedCubeStateData = cubeStateDBService.getCubeState(id)

        assertCubeStatesEqual(retrievedCubeStateData, newCubeStateData, id)
    }

    @Test
    fun updateCubeStateFailInvalidID(){
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 4, 5, 7, 6),
            mutableListOf(2, 2, 2, 2, 2, 2, 2, 2),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 10),
            mutableListOf(
                false, true, false, false, false, false, false, false, false, false, false, false
            ),
            Move("R", 1, "R"),
            200
        )
        val cubeStateData = CubeStateData(cubeState, 2)

        val newCubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7),
            mutableListOf(3, 3, 3, 3, 3, 3, 3, 3),
            mutableListOf(0, 1, 2, 3, 4, 5, 7, 6),
            mutableListOf(
                false, false, false, false, false, false, false, false, false, false, false, false
            ),
            Move("F", 1, "F"),
            100
        )
        val newCubeStateData = CubeStateData(newCubeState, 1)

        val id = cubeStateDBService.addCubeState(cubeStateData)

        cubeStateDBService.updateCubeState(newCubeStateData, id + 1)

        val retrievedCubeStateData = cubeStateDBService.getCubeState(id)

        assertCubeStatesEqual(retrievedCubeStateData, cubeStateData, id)
    }

    @Test
    fun updateCubeStateFailInvalidTimestamp(){
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 4, 5, 7, 6),
            mutableListOf(2, 2, 2, 2, 2, 2, 2, 2),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 10),
            mutableListOf(
                false, true, false, false, false, false, false, false, false, false, false, false
            ),
            Move("R", 1, "R"),
            200
        )

        val cubeStateData = CubeStateData(cubeState, 2)

        val newCubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7),
            mutableListOf(3, 3, 3, 3, 3, 3, 3, 3),
            mutableListOf(0, 1, 2, 3, 4, 5, 7, 6),
            mutableListOf(
                false, false, false, false, false, false, false, false, false, false, false, false
            ),
            Move("F", 1, "F"),
            0
        )

        val newCubeStateData = CubeStateData(newCubeState, 1)

        val id = cubeStateDBService.addCubeState(cubeStateData)

        assertThrows(IllegalArgumentException::class.java){
            cubeStateDBService.updateCubeState(newCubeStateData, id)
        }

        val retrievedCubeStateData = cubeStateDBService.getCubeState(id)

        assertCubeStatesEqual(retrievedCubeStateData, cubeStateData, id)
    }

    @Test
    fun updateCubeStateFailInvalidMoveIndex(){
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 4, 5, 7, 6),
            mutableListOf(2, 2, 2, 2, 2, 2, 2, 2),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 10),
            mutableListOf(
                false, true, false, false, false, false, false, false, false, false, false, false
            ),
            Move("R", 1, "R"),
            200
        )
        val cubeStateData = CubeStateData(cubeState, 2)

        val newCubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7),
            mutableListOf(3, 3, 3, 3, 3, 3, 3, 3),
            mutableListOf(0, 1, 2, 3, 4, 5, 7, 6),
            mutableListOf(
                false, false, false, false, false, false, false, false, false, false, false, false
            ),
            Move("F", 1, "F"),
            100
        )
        val newCubeStateData = CubeStateData(newCubeState, -1)

        val id = cubeStateDBService.addCubeState(cubeStateData)

        assertThrows(IllegalArgumentException::class.java){
            cubeStateDBService.updateCubeState(newCubeStateData, 1)
        }

        val retrievedCubeStateData = cubeStateDBService.getCubeState(id)

        assertCubeStatesEqual(retrievedCubeStateData, cubeStateData, id)
    }

    @Test
    fun updateCubeStateFailInvalidLastMove(){
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 4, 5, 7, 6),
            mutableListOf(2, 2, 2, 2, 2, 2, 2, 2),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 10),
            mutableListOf(
                false, true, false, false, false, false, false, false, false, false, false, false
            ),
            Move("R", 1, "R"),
            200
        )
        val cubeStateData = CubeStateData(cubeState, 2)

        val newCubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7),
            mutableListOf(3, 3, 3, 3, 3, 3, 3, 3),
            mutableListOf(0, 1, 2, 3, 4, 5, 7, 6),
            mutableListOf(
                false, false, false, false, false, false, false, false, false, false, false, false
            ),
            Move("G", 7, "F3"),
            100
        )
        val newCubeStateData = CubeStateData(newCubeState, 1)

        val id = cubeStateDBService.addCubeState(cubeStateData)

        assertThrows(IllegalArgumentException::class.java){
            cubeStateDBService.updateCubeState(newCubeStateData, 1)
        }

        val retrievedCubeStateData = cubeStateDBService.getCubeState(id)
        assertCubeStatesEqual(retrievedCubeStateData, cubeStateData, id)
    }


    private fun assertCubeStatesEqual(retrievedState: CubeStateData?, expectedState: CubeStateData, id: Long){
        assert(retrievedState != null)
        assert(retrievedState!!.id == id)
        assert(retrievedState.timestamp == expectedState.timestamp)
        assert(retrievedState.solveID == expectedState.solveID)
        assert(retrievedState.moveIndex == expectedState.moveIndex)
        assert(retrievedState.lastMove == expectedState.lastMove)
        assert(retrievedState.cornerPositions == expectedState.cornerPositions)
        assert(retrievedState.edgePositions == expectedState.edgePositions)
        assert(retrievedState.cornerOrientations == expectedState.cornerOrientations)
        assert(retrievedState.edgeOrientations == expectedState.edgeOrientations)
    }
}
