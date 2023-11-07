package com.example.smartcubeapp.correctnesstests.detectiontests.casedetection.ollcasedetectiontests

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.example.smartcubeapp.cube.BlueSide
import com.example.smartcubeapp.cube.CubeState
import com.example.smartcubeapp.cube.GreenSide
import com.example.smartcubeapp.cube.OrangeSide
import com.example.smartcubeapp.cube.RedSide
import com.example.smartcubeapp.cube.WhiteSide
import com.example.smartcubeapp.cube.YellowSide
import com.example.smartcubeapp.casedetection.olldetection.OLLCaseDetection
import com.example.smartcubeapp.casedetection.olldetection.ollcase.PredefinedOLLCase
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class DetectOLLCaseTests {

    private lateinit var caseDetection: OLLCaseDetection
    private lateinit var context: Context

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        caseDetection = OLLCaseDetection(CubeState.SOLVED_CUBE_STATE, WhiteSide)
    }

    @Test
    fun detectOLLCaseTestWhite(){

        val cubeState = CubeState(
            mutableListOf(0, 2, 1, 3, 4, 5, 6, 7),
            mutableListOf(3, 2, 1, 3, 3, 3, 2, 3),
            mutableListOf(0, 1, 2, 3, 4, 10, 6, 7, 8, 9, 5, 11),
            mutableListOf(false, false, true, false, false, false, false, false, false, false, true, false),
        )
        caseDetection.changeCubeState(cubeState)
        caseDetection.changeCubeSide(WhiteSide)

        val case = caseDetection.detectCase(context)

        TestCase.assertEquals(PredefinedOLLCase.OLL_29, case)
    }

    @Test
    fun detectOLLCaseTestYellow(){
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 7, 4, 5, 6, 3),
            mutableListOf(2, 3, 3, 2, 3, 3, 3, 3),
            mutableListOf(0, 1, 2, 3, 7, 5, 6, 4, 8, 9, 10 ,11),
            mutableListOf(true, false, false, false, true, false, false, false, false, false, false, false),
        )
        caseDetection.changeCubeState(cubeState)
        caseDetection.changeCubeSide(YellowSide)

        val case = caseDetection.detectCase(context)

        TestCase.assertEquals(PredefinedOLLCase.OLL_38, case)
    }

    @Test
    fun detectOLLCaseTestBlue(){
        val cubeState = CubeState(
            mutableListOf(0, 2, 1, 3, 4, 6, 5, 7),
            mutableListOf(3, 3, 3, 3, 2, 1, 3, 1),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 11, 8, 10 , 9),
            mutableListOf(false, false, false, false, false, false, false, false, true, true, true, true),
        )
        caseDetection.changeCubeState(cubeState)
        caseDetection.changeCubeSide(BlueSide)

        val case = caseDetection.detectCase(context)

        TestCase.assertEquals(PredefinedOLLCase.OLL_03, case)
    }

    @Test
    fun detectOLLCaseTestGreen(){
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7),
            mutableListOf(2, 2, 1, 1, 3, 3, 3, 3),
            mutableListOf(0, 2, 3, 1, 4, 5, 6, 7, 8, 9, 10, 11),
            mutableListOf(false, true, true, false, false, false, false, false, false, false, false, false),
        )
        caseDetection.changeCubeState(cubeState)
        caseDetection.changeCubeSide(GreenSide)

        val case = caseDetection.detectCase(context)

        TestCase.assertEquals(PredefinedOLLCase.OLL_49, case)
    }

    @Test
    fun detectOLLCaseRed(){
        val cubeState = CubeState(
            mutableListOf(4, 1, 2, 3, 5, 0, 6, 7),
            mutableListOf(1, 3, 3, 3, 3, 2, 3, 3),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11),
            mutableListOf(false, false, false, false, false, false, false, false, false, false, false, false),
        )
        caseDetection.changeCubeState(cubeState)
        caseDetection.changeCubeSide(RedSide)

        val case = caseDetection.detectCase(context)

        TestCase.assertEquals(PredefinedOLLCase.OLL_24, case)
    }

    @Test
    fun detectOLLCaseOrange(){
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7),
            mutableListOf(3, 3, 3, 1, 3, 3, 2, 3),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 ,11),
            mutableListOf(false, false, false, false, false, false, false, true, false, false, false, true),
        )
        caseDetection.changeCubeState(cubeState)
        caseDetection.changeCubeSide(OrangeSide)

        val case = caseDetection.detectCase(context)

        TestCase.assertEquals(PredefinedOLLCase.OLL_35, case)
    }

    @Test
    fun detectOLLCaseFailNotInOLLPhase(){
        val cubeState = CubeState(
            mutableListOf(0, 7, 2, 4, 3, 5, 1, 6),
            mutableListOf(3, 3, 3, 1, 3, 3, 2, 3),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 ,11),
            mutableListOf(false, false, false, false, false, false, false, true, false, false, false, true),
        )
        caseDetection.changeCubeState(cubeState)
        caseDetection.changeCubeSide(OrangeSide)

        val case = caseDetection.detectCase(context)

        TestCase.assertNull(case)
    }
}