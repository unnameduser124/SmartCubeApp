package com.example.tests.correctnesstests.detectiontests.casedetection.ollcasedetectiontests

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.example.cube_detection.casedetection.PositionRepresentationTransformer
import com.example.cube_cube.cube.BlueSide
import com.example.cube_cube.cube.CubeState
import com.example.cube_cube.cube.GreenSide
import com.example.cube_cube.cube.OrangeSide
import com.example.cube_cube.cube.RedSide
import com.example.cube_cube.cube.WhiteSide
import com.example.cube_cube.cube.YellowSide
import com.example.cube_detection.casedetection.olldetection.OLLCaseDetection
import com.example.cube_detection.casedetection.olldetection.OLLElementOrientation
import com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase
import com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class OLLMatchCaseTests {

    private lateinit var context: Context
    private lateinit var positionTransformer: com.example.cube_detection.casedetection.PositionRepresentationTransformer
    private lateinit var ollCaseDetection: com.example.cube_detection.casedetection.olldetection.OLLCaseDetection

    @Before
    fun setup() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        positionTransformer =
            com.example.cube_detection.casedetection.PositionRepresentationTransformer(
                CubeState.SOLVED_CUBE_STATE,
                WhiteSide
            )
        ollCaseDetection = com.example.cube_detection.casedetection.olldetection.OLLCaseDetection(
            CubeState.SOLVED_CUBE_STATE,
            WhiteSide
        )
    }

    @Test
    fun matchCaseOLL_01Test() {
        val cubeState = CubeState(
            mutableListOf(7, 1, 2, 3, 0, 5, 6, 4),
            mutableListOf(1, 3, 3, 1, 3, 3, 3, 3),
            mutableListOf(7, 1, 2, 3, 8, 5, 6, 0, 4, 9, 10, 11),
            mutableListOf(
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(YellowSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)
        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)
            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_01, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_02Test() {
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 4, 6, 5, 7),
            mutableListOf(2, 3, 3, 1, 2, 3, 3, 1),
            mutableListOf(7, 1, 2, 3, 8, 5, 6, 0, 4, 9, 10, 11),
            mutableListOf(
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(YellowSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)
        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)
            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_02, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_03Test() {
        val cubeState = CubeState(
            mutableListOf(0, 2, 1, 3, 4, 6, 5, 7),
            mutableListOf(3, 3, 3, 3, 2, 1, 3, 1),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 11, 8, 10, 9),
            mutableListOf(
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                true,
                true,
                true,
                true
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(BlueSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)
        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)
            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_03, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_04Test() {
        val cubeState = CubeState(
            mutableListOf(3, 1, 2, 7, 0, 5, 6, 4),
            mutableListOf(2, 3, 3, 3, 3, 3, 3, 1),
            mutableListOf(7, 1, 2, 3, 4, 5, 6, 0, 8, 9, 10, 11),
            mutableListOf(
                false,
                false,
                false,
                false,
                true,
                false,
                false,
                false,
                true,
                false,
                false,
                false
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(YellowSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)
        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)
            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_04, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_05Test() {
        val cubeState = CubeState(
            mutableListOf(0, 1, 7, 6, 4, 5, 3, 2),
            mutableListOf(3, 3, 1, 2, 3, 3, 2, 3),
            mutableListOf(0, 1, 2, 7, 4, 5, 3, 6, 8, 9, 10, 11),
            mutableListOf(
                false,
                false,
                false,
                true,
                false,
                false,
                true,
                false,
                false,
                false,
                false,
                false
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(OrangeSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)
        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)
            if (caseMatch != null) {
                println("--------------------")
                ollCaseRepresentation.incorrectlyOrientedPieces.forEach { piece ->
                    var printlnString = ""
                    printlnString += piece.pieceType
                    printlnString += piece.sideRelativePosition
                    printlnString += piece.sideRelativeOrientation
                    println(printlnString)
                }
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_05, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_06Test() {
        val cubeState = CubeState(
            mutableListOf(5, 4, 2, 3, 1, 0, 6, 7),
            mutableListOf(2, 1, 3, 3, 3, 2, 3, 3),
            mutableListOf(0, 4, 2, 3, 5, 1, 6, 7, 8, 9, 10, 11),
            mutableListOf(
                false,
                true,
                false,
                false,
                false,
                true,
                false,
                false,
                false,
                false,
                false,
                false
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(RedSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)
        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)
            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_06, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_07Test() {
        val cubeState = CubeState(
            mutableListOf(2, 3, 0, 1, 4, 5, 6, 7),
            mutableListOf(1, 2, 1, 3, 3, 3, 3, 3),
            mutableListOf(3, 0, 2, 1, 4, 5, 6, 7, 8, 9, 10, 11),
            mutableListOf(
                true,
                true,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(GreenSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)
        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)
            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_07, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_08Test() {
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 6, 7, 4, 5),
            mutableListOf(3, 3, 3, 3, 3, 2, 1, 2),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 10, 8, 9, 11),
            mutableListOf(
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                true,
                true,
                false
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(BlueSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)
        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)
            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_08, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_09Test() {
        val cubeState = CubeState(
            mutableListOf(0, 1, 7, 3, 4, 5, 2, 6),
            mutableListOf(3, 3, 2, 1, 3, 3, 2, 1),
            mutableListOf(0, 1, 2, 7, 4, 5, 6, 11, 8, 9, 10, 3),
            mutableListOf(
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                true,
                false,
                false,
                false,
                true
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(OrangeSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)
        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)
            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_09, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_10Test() {
        val cubeState = CubeState(
            mutableListOf(0, 2, 1, 3, 4, 6, 5, 7),
            mutableListOf(3, 1, 2, 3, 3, 3, 1, 3),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11),
            mutableListOf(
                false,
                false,
                true,
                false,
                false,
                false,
                true,
                false,
                false,
                false,
                false,
                false
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(WhiteSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)
        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)
            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_10, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_11Test() {
        val cubeState = CubeState(
            mutableListOf(1, 2, 3, 0, 4, 5, 6, 7),
            mutableListOf(1, 2, 3, 2, 3, 3, 3, 3),
            mutableListOf(0, 2, 1, 3, 4, 5, 6, 7, 8, 9, 10, 11),
            mutableListOf(
                false,
                true,
                true,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(GreenSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)
        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)
            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_11, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_12Test() {
        val cubeState = CubeState(
            mutableListOf(4, 0, 2, 3, 5, 1, 6, 7),
            mutableListOf(3, 2, 3, 3, 1, 3, 3, 3),
            mutableListOf(0, 4, 2, 3, 9, 1, 6, 7, 8, 5, 10, 11),
            mutableListOf(
                false,
                false,
                false,
                false,
                false,
                true,
                false,
                false,
                false,
                true,
                false,
                false
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(RedSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)
        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)
            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_12, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_13Test() {
        val cubeState = CubeState(
            mutableListOf(7, 1, 2, 3, 0, 5, 6, 4),
            mutableListOf(1, 3, 3, 3, 1, 3, 3, 3),
            mutableListOf(4, 1, 2, 3, 7, 5, 6, 0, 8, 9, 10, 11),
            mutableListOf(
                true,
                false,
                false,
                false,
                true,
                false,
                false,
                false,
                false,
                false,
                false,
                false
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(YellowSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)
        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)
            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_13, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_14Test() {
        val cubeState = CubeState(
            mutableListOf(0, 1, 7, 3, 4, 5, 2, 6),
            mutableListOf(3, 3, 2, 3, 3, 3, 2, 3),
            mutableListOf(0, 1, 2, 6, 4, 5, 7, 3, 8, 9, 10, 11),
            mutableListOf(
                false,
                false,
                false,
                false,
                false,
                false,
                true,
                true,
                false,
                false,
                false,
                false
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(OrangeSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)
        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)
            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_14, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_15Test() {
        val cubeState = CubeState(
            mutableListOf(0, 6, 5, 3, 4, 2, 1, 7),
            mutableListOf(3, 2, 1, 3, 3, 1, 3, 3),
            mutableListOf(0, 1, 6, 3, 4, 5, 10, 7, 8, 9, 2, 11),
            mutableListOf(
                false,
                false,
                false,
                false,
                false,
                false,
                true,
                false,
                false,
                false,
                true,
                false
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(WhiteSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)
        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)
            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_15, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_16Test() {
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 6, 7, 4, 5),
            mutableListOf(3, 3, 3, 3, 1, 2, 1, 3),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 11, 8, 10, 9),
            mutableListOf(
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                true,
                false,
                true
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(BlueSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)
        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)
            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_16, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_17Test() {
        val cubeState = CubeState(
            mutableListOf(3, 1, 0, 2, 4, 5, 6, 7),
            mutableListOf(3, 2, 3, 1, 3, 3, 3, 3),
            mutableListOf(0, 2, 3, 1, 4, 5, 6, 7, 8, 9, 10, 11),
            mutableListOf(
                true,
                true,
                true,
                true,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(GreenSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)
        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)
            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_17, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_18Test() {
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 4, 6, 5, 7),
            mutableListOf(2, 2, 3, 3, 3, 3, 3, 3),
            mutableListOf(3, 2, 1, 0, 4, 5, 6, 7, 8, 9, 10, 11),
            mutableListOf(
                true,
                true,
                true,
                true,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(GreenSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)
        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)
            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_18, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_19Test() {
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 4, 6, 5, 7),
            mutableListOf(3, 2, 3, 3, 3, 1, 2, 3),
            mutableListOf(0, 1, 5, 3, 4, 10, 2, 7, 8, 9, 6, 11),
            mutableListOf(
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(WhiteSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)
        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)
            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_19, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_20Test() {
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 5, 6, 7, 4),
            mutableListOf(3, 3, 3, 3, 3, 3, 3, 3),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 11, 8, 9, 10),
            mutableListOf(
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                true,
                true,
                true,
                true
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(BlueSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)
        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)
            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_20, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_21Test() {
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 4, 6, 5, 7),
            mutableListOf(2, 3, 3, 2, 2, 3, 3, 2),
            mutableListOf(7, 1, 2, 3, 0, 5, 6, 4, 8, 9, 10, 11),
            mutableListOf(
                true,
                false,
                false,
                false,
                true,
                false,
                false,
                false,
                false,
                false,
                false,
                false
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(YellowSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)
        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)
            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_21, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_22Test() {
        val cubeState = CubeState(
            mutableListOf(0, 1, 7, 6, 4, 5, 3, 2),
            mutableListOf(3, 3, 1, 2, 3, 3, 1, 2),
            mutableListOf(0, 1, 2, 11, 4, 5, 6, 3, 8, 9, 10, 7),
            mutableListOf(
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(OrangeSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)
        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)
            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_22, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_23Test() {
        val cubeState = CubeState(
            mutableListOf(4, 1, 2, 0, 3, 5, 6, 7),
            mutableListOf(3, 3, 3, 2, 1, 3, 3, 3),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11),
            mutableListOf(
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(YellowSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)
        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)
            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_23, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_24Test() {
        val cubeState = CubeState(
            mutableListOf(4, 1, 2, 3, 5, 0, 6, 7),
            mutableListOf(1, 3, 3, 3, 3, 2, 3, 3),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11),
            mutableListOf(
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(RedSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)

        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)
            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_24, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_25Test() {
        val cubeState = CubeState(
            mutableListOf(2, 1, 3, 0, 4, 5, 6, 7),
            mutableListOf(2, 3, 1, 3, 3, 3, 3, 3),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11),
            mutableListOf(
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(GreenSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)

        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)
            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_25, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_26Test() {
        val cubeState = CubeState(
            mutableListOf(4, 1, 2, 0, 7, 5, 6, 3),
            mutableListOf(1, 3, 3, 3, 3, 3, 3, 2),
            mutableListOf(7, 1, 2, 3, 4, 5, 6, 0, 8, 9, 10, 11),
            mutableListOf(
                true,
                false,
                false,
                false,
                false,
                false,
                false,
                true,
                false,
                false,
                false,
                false
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(YellowSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)

        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)
            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_26, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_27Test() {
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 4, 6, 5, 7),
            mutableListOf(3, 3, 3, 3, 2, 3, 2, 1),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 9, 11, 10, 8),
            mutableListOf(
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(BlueSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)

        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)
            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_27, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_28Test() {
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7),
            mutableListOf(3, 3, 3, 3, 3, 3, 3, 3),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 10, 9, 11, 8),
            mutableListOf(
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                true,
                false,
                false,
                true
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(BlueSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)

        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)
            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_28, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_29Test() {
        val cubeState = CubeState(
            mutableListOf(0, 2, 1, 3, 4, 5, 6, 7),
            mutableListOf(3, 2, 1, 3, 3, 3, 2, 3),
            mutableListOf(0, 1, 2, 3, 4, 10, 6, 7, 8, 9, 5, 11),
            mutableListOf(
                false,
                false,
                true,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                true,
                false
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(WhiteSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)

        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)
            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_29, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_30Test() {
        val cubeState = CubeState(
            mutableListOf(3, 1, 0, 2, 4, 5, 6, 7),
            mutableListOf(2, 3, 3, 2, 3, 3, 3, 3),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11),
            mutableListOf(
                false,
                false,
                true,
                true,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(GreenSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)

        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)
            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_30, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_31Test() {
        val cubeState = CubeState(
            mutableListOf(0, 1, 3, 2, 4, 5, 6, 7),
            mutableListOf(3, 3, 2, 1, 3, 3, 1, 3),
            mutableListOf(0, 1, 2, 6, 4, 5, 3, 7, 8, 9, 10, 11),
            mutableListOf(
                false,
                false,
                false,
                false,
                false,
                false,
                true,
                false,
                false,
                false,
                false,
                true
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(OrangeSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)

        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)
            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_31, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_32Test() {
        val cubeState = CubeState(
            mutableListOf(3, 1, 2, 7, 4, 5, 6, 0),
            mutableListOf(2, 3, 3, 1, 3, 3, 3, 2),
            mutableListOf(8, 1, 2, 3, 4, 5, 6, 0, 7, 9, 10, 11),
            mutableListOf(
                true,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                true,
                false,
                false,
                false
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(YellowSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)

        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)

            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_32, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_33Test() {
        val cubeState = CubeState(
            mutableListOf(4, 0, 2, 3, 1, 5, 6, 7),
            mutableListOf(2, 1, 3, 3, 1, 3, 3, 3),
            mutableListOf(0, 9, 2, 3, 4, 1, 6, 7, 8, 5, 10, 11),
            mutableListOf(
                false,
                true,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                true,
                false,
                false
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(RedSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)

        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)

            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_33, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_34Test() {
        val cubeState = CubeState(
            mutableListOf(0, 2, 6, 3, 4, 5, 1, 7),
            mutableListOf(3, 2, 1, 3, 3, 3, 2, 3),
            mutableListOf(0, 1, 2, 3, 4, 10, 5, 7, 8, 9, 6, 11),
            mutableListOf(
                false,
                false,
                false,
                false,
                false,
                false,
                true,
                false,
                false,
                false,
                true,
                false
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(WhiteSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)

        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)
            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_34, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_35Test() {
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7),
            mutableListOf(3, 3, 3, 1, 3, 3, 2, 3),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11),
            mutableListOf(
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                true,
                false,
                false,
                false,
                true
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(OrangeSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)

        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)

            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_35, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_36Test() {
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 5, 4, 6, 7),
            mutableListOf(3, 3, 3, 3, 3, 2, 3, 1),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 11, 10, 9),
            mutableListOf(
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                true,
                false,
                false,
                true
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(BlueSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)
        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)
            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_36, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_37Test() {
        val cubeState = CubeState(
            mutableListOf(1, 0, 2, 3, 4, 5, 6, 7),
            mutableListOf(2, 3, 1, 3, 3, 3, 3, 3),
            mutableListOf(0, 1, 3, 2, 4, 5, 6, 7, 8, 9, 10, 11),
            mutableListOf(
                false,
                true,
                true,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(GreenSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)

        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)

            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_37, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_38Test() {
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 7, 4, 5, 6, 3),
            mutableListOf(2, 3, 3, 2, 3, 3, 3, 3),
            mutableListOf(0, 1, 2, 3, 7, 5, 6, 4, 8, 9, 10, 11),
            mutableListOf(
                true,
                false,
                false,
                false,
                true,
                false,
                false,
                false,
                false,
                false,
                false,
                false
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(YellowSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)

        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)

            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_38, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()

    }

    @Test
    fun matchCaseOLL_39Test() {
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 5, 4, 6, 7),
            mutableListOf(2, 3, 3, 3, 1, 2, 3, 3),
            mutableListOf(0, 1, 2, 3, 9, 5, 6, 7, 8, 4, 10, 11),
            mutableListOf(
                false,
                true,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                true,
                false,
                false
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(RedSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)

        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)

            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_39, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_40Test() {
        val cubeState = CubeState(
            mutableListOf(0, 2, 1, 3, 4, 5, 6, 7),
            mutableListOf(3, 2, 1, 3, 3, 1, 3, 3),
            mutableListOf(0, 1, 5, 3, 4, 2, 6, 7, 8, 9, 10, 11),
            mutableListOf(
                false,
                false,
                false,
                false,
                false,
                true,
                false,
                false,
                false,
                false,
                true,
                false
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(WhiteSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)

        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)

            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_40, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_41Test() {
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 6, 4, 5, 3, 7),
            mutableListOf(3, 3, 1, 1, 3, 3, 3, 3),
            mutableListOf(0, 1, 2, 3, 4, 5, 7, 6, 8, 9, 10, 11),
            mutableListOf(
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                true,
                false,
                false,
                false,
                true
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(OrangeSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)

        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)

            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_41, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_42Test() {
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 7, 6, 5, 4),
            mutableListOf(3, 3, 3, 3, 2, 2, 3, 3),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 10, 11, 9),
            mutableListOf(
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                true,
                false,
                false,
                true
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(BlueSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)

        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)

            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_42, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_43Test() {
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 7, 6, 5, 4),
            mutableListOf(3, 3, 3, 3, 3, 3, 2, 2),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 11, 9, 8, 10),
            mutableListOf(
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                true,
                true
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(BlueSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)

        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)

            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_43, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_44Test() {
        val cubeState = CubeState(
            mutableListOf(0, 3, 2, 1, 4, 5, 6, 7),
            mutableListOf(3, 3, 2, 2, 3, 3, 3, 3),
            mutableListOf(0, 2, 1, 3, 4, 5, 6, 7, 8, 9, 10, 11),
            mutableListOf(
                false,
                false,
                true,
                true,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(GreenSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)

        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)

            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_44, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_45Test() {
        val cubeState = CubeState(
            mutableListOf(0, 1, 6, 7, 4, 5, 2, 3),
            mutableListOf(3, 3, 2, 2, 3, 3, 1, 1),
            mutableListOf(0, 1, 2, 3, 4, 5, 11, 6, 8, 9, 10, 7),
            mutableListOf(
                false,
                false,
                false,
                false,
                false,
                false,
                true,
                true,
                false,
                false,
                false,
                false
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(OrangeSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)

        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)

            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_45, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_46Test() {
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 4, 5, 7, 6),
            mutableListOf(3, 3, 3, 3, 3, 3, 2, 2),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 9, 11, 8, 10),
            mutableListOf(
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                true,
                false,
                true
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(BlueSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)
        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)

            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_46, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_47Test() {
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7),
            mutableListOf(1, 3, 3, 2, 1, 3, 3, 2),
            mutableListOf(7, 1, 2, 3, 4, 5, 6, 8, 0, 9, 10, 11),
            mutableListOf(
                true,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                true,
                false,
                false,
                false
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(YellowSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)
        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)

            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_47, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_48Test() {
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7),
            mutableListOf(2, 1, 3, 3, 2, 1, 3, 3),
            mutableListOf(0, 5, 2, 3, 4, 9, 6, 7, 8, 1, 10, 11),
            mutableListOf(
                false,
                false,
                false,
                false,
                false,
                true,
                false,
                false,
                false,
                true,
                false,
                false
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(RedSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)
        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)

            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_48, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_49Test() {
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7),
            mutableListOf(2, 2, 1, 1, 3, 3, 3, 3),
            mutableListOf(0, 2, 3, 1, 4, 5, 6, 7, 8, 9, 10, 11),
            mutableListOf(
                false,
                true,
                true,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(GreenSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)
        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)

            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_49, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_50Test() {
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7),
            mutableListOf(2, 3, 3, 1, 2, 3, 3, 1),
            mutableListOf(4, 1, 2, 3, 7, 5, 6, 0, 8, 9, 10, 11),
            mutableListOf(
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(YellowSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)
        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)

            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_50, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_51Test() {
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7),
            mutableListOf(3, 3, 3, 3, 2, 2, 1, 1),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 10, 8, 9, 11),
            mutableListOf(
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                true,
                false,
                true,
                false
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(BlueSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)
        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)

            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_51, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_52Test() {
        val cubeState = CubeState(
            mutableListOf(0, 1, 3, 2, 4, 5, 7, 6),
            mutableListOf(3, 3, 2, 3, 3, 3, 2, 3),
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11),
            mutableListOf(
                false,
                false,
                false,
                false,
                false,
                false,
                true,
                true,
                false,
                false,
                false,
                false
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(OrangeSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)
        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)

            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_52, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_53Test() {
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7),
            mutableListOf(2, 2, 2, 2, 3, 3, 3, 3),
            mutableListOf(3, 0, 2, 1, 4, 5, 6, 7, 8, 9, 10, 11),
            mutableListOf(
                true,
                true,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(GreenSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)
        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)

            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_53, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_54Test() {
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7),
            mutableListOf(2, 2, 2, 2, 3, 3, 3, 3),
            mutableListOf(1, 3, 2, 0, 4, 5, 6, 7, 8, 9, 10, 11),
            mutableListOf(
                true,
                false,
                false,
                true,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(GreenSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)
        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)

            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_54, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_55Test() {
        val cubeState = CubeState(
            mutableListOf(1, 4, 2, 3, 0, 5, 6, 7),
            mutableListOf(2, 1, 3, 3, 2, 1, 3, 3),
            mutableListOf(0, 4, 2, 3, 5, 1, 6, 7, 8, 9, 10, 11),
            mutableListOf(
                false,
                true,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                true,
                false,
                false
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(RedSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)
        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)

            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_55, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_56Test() {
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7),
            mutableListOf(3, 1, 1, 3, 3, 1, 1, 3),
            mutableListOf(0, 1, 10, 3, 4, 5, 2, 7, 8, 9, 6, 11),
            mutableListOf(
                false,
                false,
                true,
                false,
                false,
                false,
                true,
                false,
                false,
                false,
                false,
                false
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(WhiteSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)
        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)

            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_56, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }

    @Test
    fun matchCaseOLL_57Test() {
        val cubeState = CubeState(
            mutableListOf(0, 1, 2, 3, 4, 5, 6, 7),
            mutableListOf(3, 3, 3, 3, 3, 3, 3, 3),
            mutableListOf(0, 1, 2, 3, 9, 4, 6, 7, 8, 5, 10, 11),
            mutableListOf(
                false,
                false,
                false,
                false,
                true,
                true,
                false,
                false,
                false,
                false,
                false,
                false
            ),
        )
        positionTransformer.changeCubeState(cubeState)
        positionTransformer.changeCubeSide(RedSide)
        var positionRepresentation: Array<Array<com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>> =
            positionTransformer.transformStateToPositionRepresentation(context)
        for (i in 0..3) {
            val ollCaseRepresentation =
                positionTransformer.positionRepresentationToCase<com.example.cube_detection.casedetection.olldetection.ollcase.CustomOLLCase, com.example.cube_detection.casedetection.olldetection.OLLElementOrientation>(positionRepresentation)
            val caseMatch = ollCaseDetection.matchCase(ollCaseRepresentation)

            if (caseMatch != null) {
                TestCase.assertEquals(com.example.cube_detection.casedetection.olldetection.ollcase.PredefinedOLLCase.OLL_57, caseMatch)
                return
            } else {
                positionRepresentation =
                    positionTransformer.rotatePositionClockwise(positionRepresentation)
            }
        }
        TestCase.fail()
    }
}
