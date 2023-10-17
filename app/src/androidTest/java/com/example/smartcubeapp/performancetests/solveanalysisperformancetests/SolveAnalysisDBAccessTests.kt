package com.example.smartcubeapp.performancetests.solveanalysisperformancetests

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.example.smartcubeapp.cube.Solve
import com.example.smartcubeapp.cube.SolveStatus
import com.example.smartcubeapp.dbAccesses
import com.example.smartcubeapp.simpleSolveStateSequence
import com.example.smartcubeapp.solvedatabase.SolvesDatabaseConstants
import com.example.smartcubeapp.solvedatabase.services.SolveAnalysisDBService
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.Calendar

class SolveAnalysisDBAccessTests {

    private lateinit var solveAnalysisDBService: SolveAnalysisDBService
    private lateinit var context: Context

    //before optimization:
    //DB accesses for saving solve analysis = 664
    //DB accesses for saving phase analysis = 5
    //DB accesses for generating OLL data = 260
    //DB accesses for generating PLL data = 336
    //DB accesses for saving solve state sequence = 63
    //F2L and Cross generation = 0
    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        solveAnalysisDBService = SolveAnalysisDBService(context, SolvesDatabaseConstants.TEST_DATABASE_NAME)
    }

    @After
    fun tearDown() {
        context.deleteDatabase(SolvesDatabaseConstants.TEST_DATABASE_NAME)
    }

    @Test
    fun saveSolveAnalysisDBAccessTest(){
        val solve = createSolve()

        dbAccesses = 0

        solveAnalysisDBService.saveSolveWithAnalysis(solve)

        println("Number of DB accesses: $dbAccesses")
    }

    @Test
    fun generatePLLDataDBAccessTest(){
        val solve = createSolve()

        dbAccesses = 0

        solveAnalysisDBService.getPLLData(solve)

        println("Number of DB accesses: $dbAccesses")
    }

    @Test
    fun generateOLLDataDBAccessTest(){
        val solve = createSolve()

        dbAccesses = 0

        solveAnalysisDBService.getOLLData(solve)

        println("Number of DB accesses: $dbAccesses")
    }

    @Test
    fun generateF2LDataDBAccessTest(){
        val solve = createSolve()

        dbAccesses = 0

        solveAnalysisDBService.getF2LData(solve)

        println("Number of DB accesses: $dbAccesses")
        assertEquals(0, dbAccesses)
    }

    @Test
    fun generateCrossDataDBAccessTest(){

        val solve = createSolve()

        dbAccesses = 0

        solveAnalysisDBService.getCrossData(solve)

        println("Number of DB accesses: $dbAccesses")
        assertEquals(0, dbAccesses)

    }

    @Test
    fun savePhaseAnalysisDBAccessTest(){
        val solve = createSolve()
        val pllData = solveAnalysisDBService.getPLLData(solve)
        val ollData = solveAnalysisDBService.getOLLData(solve)
        val f2lData = solveAnalysisDBService.getF2LData(solve)
        val crossData = solveAnalysisDBService.getCrossData(solve)

        dbAccesses = 0

        solveAnalysisDBService.savePhaseData(pllData, ollData, f2lData, crossData)

        println("Number of DB accesses: $dbAccesses")
    }

    @Test
    fun saveSolveStateSequenceDBAccessTest(){
        val solve = createSolve()

        dbAccesses = 0

        solveAnalysisDBService.saveSolveSequence(solve)

        println("Number of DB accesses: $dbAccesses")
    }



    private fun createSolve(): Solve {
        return Solve(
            1000,
            Calendar.getInstance(),
            simpleSolveStateSequence(),
            simpleSolveStateSequence().first(),
            SolveStatus.Solved,
            Calendar.getInstance().timeInMillis,
            scrambleSequence = "R U R' U'"
        )
    }
}