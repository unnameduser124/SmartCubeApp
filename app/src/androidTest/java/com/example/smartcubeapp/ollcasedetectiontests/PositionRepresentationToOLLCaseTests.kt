package com.example.smartcubeapp.ollcasedetectiontests

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.example.smartcubeapp.cube.BlueSide
import com.example.smartcubeapp.cube.CubeState
import com.example.smartcubeapp.cube.GreenSide
import com.example.smartcubeapp.cube.OrangeSide
import com.example.smartcubeapp.cube.RedSide
import com.example.smartcubeapp.cube.WhiteSide
import com.example.smartcubeapp.cube.YellowSide
import com.example.smartcubeapp.olldetection.CustomOLLCase
import com.example.smartcubeapp.olldetection.PredefinedOLLCase
import com.example.smartcubeapp.olldetection.OLLCaseDetection
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test

class PositionRepresentationToOLLCaseTests {

    private lateinit var ollCaseDetection: OLLCaseDetection
    private lateinit var context: Context


    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        ollCaseDetection = OLLCaseDetection(CubeState.SOLVED_CUBE_STATE, WhiteSide)
    }

    @Test
    fun positionRepresentationToOLLCaseTestWhite() {
        val cubeState = CubeState(
            mutableListOf(0, 2, 1, 3, 4, 6, 5, 7),
            mutableListOf(3, 2, 3, 3, 3, 2, 3, 3),
            mutableListOf(0, 1, 6, 3, 4, 5, 10, 7, 8, 9, 2, 11),
            mutableListOf(false, false, true, false, false, false, false, false, false, false, true, false),
        )
        ollCaseDetection.changeCubeState(cubeState)
        ollCaseDetection.changeCubeSide(WhiteSide)
        var positionRepresentation = ollCaseDetection.transformStateToPositionRepresentation(context)
        var caseMatch = false
        for(i in 0..3){
            val ollCaseRepresentation =
                ollCaseDetection.positionRepresentationToOLLCase(positionRepresentation)
            if(ollCaseRepresentation.equals(PredefinedOLLCase.OLL_44)){
                caseMatch = true
                break
            }
            else{
                positionRepresentation = ollCaseDetection.rotatePositionClockwise(positionRepresentation)
            }
        }

        assertTrue(caseMatch)
    }

    @Test
    fun positionRepresentationToOLLCaseTestYellow(){
        val cubeState = CubeState(
            mutableListOf(7, 1, 2, 3, 4, 5, 6, 0),
            mutableListOf(3, 3, 3, 1, 3, 3, 3, 1),
            mutableListOf(4, 1, 2, 3, 0, 5, 6, 7, 8, 9, 10, 11),
            mutableListOf(false, false, false, false, true, false, false, true, false ,false, false, false),
        )
        ollCaseDetection.changeCubeState(cubeState)
        ollCaseDetection.changeCubeSide(YellowSide)
        var positionRepresentation = ollCaseDetection.transformStateToPositionRepresentation(context)
        var caseMatch = false
        for(i in 0..3){
            val ollCaseRepresentation =
                ollCaseDetection.positionRepresentationToOLLCase(positionRepresentation)
            if(ollCaseRepresentation.equals(PredefinedOLLCase.OLL_44)){
                caseMatch = true
                break
            }
            else{
                positionRepresentation = ollCaseDetection.rotatePositionClockwise(positionRepresentation)
            }
        }

        assertTrue(caseMatch)
    }

    @Test
    fun positionRepresentationToOLLCaseTestBlue(){
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 7, 6, 5, 4),
            mutableListOf(3, 3, 3, 3, 3, 3, 2, 2),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 10, 9, 11, 8),
            mutableListOf(false, false, false, false, false, false, false, false, true, false, false, true),
        )
        ollCaseDetection.changeCubeState(cubeState)
        ollCaseDetection.changeCubeSide(BlueSide)
        var positionRepresentation = ollCaseDetection.transformStateToPositionRepresentation(context)
        var caseMatch = false
        for(i in 0..3){
            val ollCaseRepresentation =
                ollCaseDetection.positionRepresentationToOLLCase(positionRepresentation)
            if(ollCaseRepresentation.equals(PredefinedOLLCase.OLL_44)){
                caseMatch = true
                break
            }
            else{
                positionRepresentation = ollCaseDetection.rotatePositionClockwise(positionRepresentation)
            }
        }

        assertTrue(caseMatch)
    }

    @Test
    fun positionRepresentationToOLLCaseTestGreen(){
        val cubeState = CubeState(
            mutableListOf(3, 2, 1, 0, 4, 5, 6, 7),
            mutableListOf(3, 3, 2, 2, 3, 3, 3, 3),
            mutableListOf(3, 1, 0, 2, 4, 5, 6, 7, 8, 9, 10, 11),
            mutableListOf(false, false, true, true, false, false, false, false, false, false, false, false),
        )
        ollCaseDetection.changeCubeState(cubeState)
        ollCaseDetection.changeCubeSide(GreenSide)
        var positionRepresentation = ollCaseDetection.transformStateToPositionRepresentation(context)
        var caseMatch = false
        for(i in 0..3){
            val ollCaseRepresentation =
                ollCaseDetection.positionRepresentationToOLLCase(positionRepresentation)
            if(ollCaseRepresentation.equals(PredefinedOLLCase.OLL_44)){
                caseMatch = true
                break
            }
            else{
                positionRepresentation = ollCaseDetection.rotatePositionClockwise(positionRepresentation)
            }
        }

        assertTrue(caseMatch)
    }

    @Test
    fun positionRepresentationToOLLCaseTestRed(){
        val cubeState = CubeState(
            mutableListOf(4, 5, 2, 3, 0, 1, 6, 7),
            mutableListOf(2, 2, 3, 3, 1, 1, 3, 3),
            mutableListOf(0, 5, 2, 3, 1, 4, 6, 7, 8, 9, 10, 11),
            mutableListOf(false, true, false, false, false, true, false, false, false, false, false, false),
        )
        ollCaseDetection.changeCubeState(cubeState)
        ollCaseDetection.changeCubeSide(RedSide)
        var positionRepresentation = ollCaseDetection.transformStateToPositionRepresentation(context)
        var caseMatch = false
        for(i in 0..3){
            val ollCaseRepresentation =
                ollCaseDetection.positionRepresentationToOLLCase(positionRepresentation)
            if(ollCaseRepresentation.equals(PredefinedOLLCase.OLL_44)){
                caseMatch = true
                break
            }
            else{
                positionRepresentation = ollCaseDetection.rotatePositionClockwise(positionRepresentation)
            }
        }


        assertTrue(caseMatch)
    }

    @Test
    fun positionRepresentationToOLLCaseTestOrange(){
        val cubeState = CubeState(
            mutableListOf(0, 1, 6, 7, 4, 5, 2, 3),
            mutableListOf(3, 3, 2, 2, 3, 3, 1, 1),
            mutableListOf(0, 1, 2, 7, 4, 5, 3, 6, 8, 9, 10, 11),
            mutableListOf(false, false, false, true, false, false, false, true, false, false, false, false),
        )
        ollCaseDetection.changeCubeState(cubeState)
        ollCaseDetection.changeCubeSide(OrangeSide)
        var positionRepresentation = ollCaseDetection.transformStateToPositionRepresentation(context)
        var caseMatch = false
        for(i in 0..3){
            val ollCaseRepresentation =
                ollCaseDetection.positionRepresentationToOLLCase(positionRepresentation)
            if(ollCaseRepresentation.equals(PredefinedOLLCase.OLL_44)){
                caseMatch = true
                break
            }
            else{
                positionRepresentation = ollCaseDetection.rotatePositionClockwise(positionRepresentation)
            }
        }

        assertTrue(caseMatch)
    }
}