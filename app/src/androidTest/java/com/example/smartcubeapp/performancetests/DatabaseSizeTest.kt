package com.example.smartcubeapp.performancetests

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.example.smartcubeapp.cube.Solve
import com.example.smartcubeapp.cube.SolveStatus
import com.example.smartcubeapp.simpleSolveStateSequence
import com.example.smartcubeapp.solvedatabase.SolveDB
import com.example.smartcubeapp.solvedatabase.SolvesDatabaseConstants
import com.example.smartcubeapp.solvedatabase.services.SolveAnalysisDBService
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

    private fun createSolve(): Solve {
        return Solve(
            1000,
            Calendar.getInstance(),
            simpleSolveStateSequence(),
            simpleSolveStateSequence().first(),
            SolveStatus.Solved,
            1,
            scrambleSequence = "R U R' U'"
        )
    }
}