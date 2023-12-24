package com.example.tests.performancetests

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.example.cube_cube.cube.Solve
import com.example.cube_cube.cube.SolveStatus
import com.example.cube_cube.cubeDatabaseClasses.CrossData
import com.example.cube_cube.cubeDatabaseClasses.F2LData
import com.example.cube_cube.cubeDatabaseClasses.OLLData
import com.example.cube_cube.cubeDatabaseClasses.PLLData
import com.example.cube_cube.cubeDatabaseClasses.SolveData
import com.example.cube_database.solvedatabase.SolveDB
import com.example.cube_database.solvedatabase.SolvesDatabaseConstants
import com.example.cube_database.solvedatabase.services.CrossDBService
import com.example.cube_database.solvedatabase.services.F2LDBService
import com.example.cube_database.solvedatabase.services.OLLDBService
import com.example.cube_database.solvedatabase.services.PLLDBService
import com.example.cube_database.solvedatabase.services.SolveAnalysisDBService
import com.example.cube_database.solvedatabase.services.SolveDBService
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.Calendar

class DatabaseSizeTest {

    private lateinit var context: Context
    private lateinit var solveAnalysisDBService: SolveAnalysisDBService
    private lateinit var solveDB: SolveDB

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        solveAnalysisDBService =
            SolveAnalysisDBService(context, SolvesDatabaseConstants.TEST_DATABASE_NAME)
        solveDB = SolveDB(context, SolvesDatabaseConstants.TEST_DATABASE_NAME)
    }

    @After
    fun tearDown() {
        solveAnalysisDBService.close()
        solveDB.close()
        context.deleteDatabase(SolvesDatabaseConstants.TEST_DATABASE_NAME)
    }

    @Test
    fun databaseSizeFor10Solves() {
        val solve = createSolve()
        var solveCount = 0

        while (solveCount < 10) {
            solveAnalysisDBService.saveSolveWithAnalysis(solve)
            solveCount++
        }
        println(solveCount)
        println("Database size: ${solveDB.getDatabaseSizeInMegabytes()} MB")
    }

    @Test
    fun databaseSizeFor100SolvesWithoutStateSequence() {
        val solve = createSolve()
        var solveCount = 0

        while (solveCount < 100) {
            solve.calculateTimeFromStateSequence()

            val solveData = SolveData(solve)
            solveData.id =
                SolveDBService(context, SolvesDatabaseConstants.TEST_DATABASE_NAME).addSolve(
                    solveData
                )
            solve.id = solveData.id

            PLLDBService(context, SolvesDatabaseConstants.SOLVE_DATABASE_NAME).addPLLData(
                generatePLLData(solveData.id)
            )
            OLLDBService(context, SolvesDatabaseConstants.SOLVE_DATABASE_NAME).addOLLData(
                generateOLLData(solveData.id)
            )
            F2LDBService(context, SolvesDatabaseConstants.SOLVE_DATABASE_NAME).addF2LData(
                generateF2LData(solveData.id)
            )
            CrossDBService(context, SolvesDatabaseConstants.SOLVE_DATABASE_NAME).addCrossData(
                generateCrossData(solveData.id)
            )
            solveCount++
        }
        println(solveCount)
        println("Database size: ${solveDB.getDatabaseSizeInMegabytes()} MB")
    }

    private fun createSolve(): Solve {
        return Solve(
            1000,
            Calendar.getInstance(),
            com.example.cube_global.simpleSolveStateSequence(),
            com.example.cube_global.simpleSolveStateSequence().first(),
            SolveStatus.Solved,
            1,
            scrambleSequence = "R U R' U'"
        )
    }

    private fun generatePLLData(solveID: Long): PLLData {
        return PLLData(
            solveID,
            (1L..1000L).random(),
            (1..20).random(),
            1,
            1,
            1
        )
    }

    private fun generateOLLData(solveID: Long): OLLData {
        return OLLData(
            solveID,
            (1L..1000L).random(),
            (1..20).random(),
            1,
            1,
            1
        )
    }

    private fun generateF2LData(solveID: Long): F2LData {
        return F2LData(
            solveID,
            (1L..1000L).random(),
            (1..20).random(),
            1,
            1
        )
    }

    private fun generateCrossData(solveID: Long): CrossData {
        return CrossData(
            solveID,
            (1L..1000L).random(),
            (1..20).random(),
            1,
            1
        )
    }
}