package com.example.smartcubeapp.solveanalysisperformancetests

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.example.smartcubeapp.cube.Solve
import com.example.smartcubeapp.cube.SolveStatus
import com.example.smartcubeapp.simpleSolveStateSequence
import com.example.smartcubeapp.solvedatabase.SolvesDatabaseConstants
import com.example.smartcubeapp.solvedatabase.services.SolveAnalysisDBService
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.Calendar
import kotlin.system.measureTimeMillis

class SolveAnalysisTimeTests {

    private lateinit var solveAnalysisDBService: SolveAnalysisDBService
    private lateinit var context: Context

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
    fun saveSolveAnalysisTimePerformanceTest(){
        val solve = createSolve()
        var time = 0L

        for(i in 0..10){
            time += measureTimeMillis {
                solveAnalysisDBService.saveSolveWithAnalysis(solve)
            }
        }
        val average = time / 10.0
        println("Average time to save solve analysis: $average ms")
        assert(average < 1000)
    }

    @Test
    fun generatePLLDataTimePerformanceTest(){
        val solve = createSolve()
        var time = 0L

        for(i in 0..10){
            time += measureTimeMillis {
                solveAnalysisDBService.getPLLData(solve)
            }
        }

        val average = time / 10.0
        println("Average time to generate PLL data: $average ms")
        assert(average < 200)
    }

    @Test
    fun generateOLLDataTimePerformanceTest(){
        val solve = createSolve()
        var time = 0L

        for(i in 0..10){
            time += measureTimeMillis {
                solveAnalysisDBService.getOLLData(solve)
            }
        }

        val average = time / 10.0
        println("Average time to generate OLL data: $average ms")
        assert(average < 200)
    }

    @Test
    fun generateF2LDataTimePerformanceTest(){
        val solve = createSolve()
        var time = 0L

        for(i in 0..10){
            time += measureTimeMillis {
                solveAnalysisDBService.getF2LData(solve)
            }
        }

        val average = time / 10.0
        println("Average time to generate F2L data: $average ms")
        assert(average < 1)
    }

    @Test
    fun generateCrossDataTimePerformanceTest(){
        val solve = createSolve()
        var time = 0L

        for(i in 0..10){
            time += measureTimeMillis {
                solveAnalysisDBService.getCrossData(solve)
            }
        }

        val average = time / 10.0
        println("Average time to generate Cross data: $average ms")
        assert(average < 1)
    }

    @Test
    fun savePhaseAnalysisTimePerformanceTest(){
        val solve = createSolve()
        var time = 0L
        val pllData = solveAnalysisDBService.getPLLData(solve)
        val ollData = solveAnalysisDBService.getOLLData(solve)
        val f2lData = solveAnalysisDBService.getF2LData(solve)
        val crossData = solveAnalysisDBService.getCrossData(solve)

        for(i in 0..10){
            time += measureTimeMillis {
                solveAnalysisDBService.savePhaseData(pllData, ollData, f2lData, crossData)
            }
        }

        val average = time / 10.0
        println("Average time to save phase analysis data: $average ms")
        assert(average < 100)
    }

    @Test
    fun saveSolveStateSequenceTimePerformanceTest(){
        val solve = createSolve()
        var time = 0L

        for(i in 0..10){
            time += measureTimeMillis {
                solveAnalysisDBService.saveSolveSequence(solve)
            }
        }

        val average = time / 10.0
        println("Average time to save solve state sequence: $average ms")
        assert(average < 300)
    }

    private fun createSolve(): Solve{
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