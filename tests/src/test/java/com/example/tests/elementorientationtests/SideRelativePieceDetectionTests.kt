package com.example.tests.elementorientationtests

import com.example.cube_cube.cube.BlueSide
import com.example.cube_cube.cube.CubeState
import com.example.cube_cube.cube.GreenSide
import com.example.cube_cube.cube.OrangeSide
import com.example.cube_cube.cube.RedSide
import com.example.cube_cube.cube.WhiteSide
import com.example.cube_cube.cube.YellowSide
import com.example.cube_cube.cube.piece.ElementOrientation
import com.example.cube_cube.cube.piece.PieceType
import com.example.cube_detection.element.SideRelativePieceDetection
import org.junit.Test

class SideRelativePieceDetectionTests {

    @Test
    fun getYellowSidePiecesTest() {
        val cubeState = CubeState.SOLVED_CUBE_STATE
        val sideRelativePieceDetection = SideRelativePieceDetection(cubeState, YellowSide)
        val pieces = sideRelativePieceDetection.getPiecesForSide()
        assert(pieces.size == 8)
        assert(
            pieces.contains(
                ElementOrientation(
                    "yellow", PieceType.CORNER, 0, 0, 3
                )
            )
        )
        assert(
            pieces.contains(
                ElementOrientation(
                    "yellow", PieceType.CORNER, 3, 3, 3
                )
            )
        )
        assert(
            pieces.contains(
                ElementOrientation(
                    "yellow", PieceType.CORNER, 4, 4, 3
                )
            )
        )
        assert(
            pieces.contains(
                ElementOrientation(
                    "yellow", PieceType.CORNER, 7, 7, 3
                )
            )
        )

        assert(
            pieces.contains(
                ElementOrientation(
                    "yellow", PieceType.EDGE, 0, 0, 0
                )
            )
        )
        assert(
            pieces.contains(
                ElementOrientation(
                    "yellow", PieceType.EDGE, 4, 4, 0
                )
            )
        )
        assert(
            pieces.contains(
                ElementOrientation(
                    "yellow", PieceType.EDGE, 7, 7, 0
                )
            )
        )
        assert(
            pieces.contains(
                ElementOrientation(
                    "yellow", PieceType.EDGE, 8, 8, 0
                )
            )
        )
    }

    @Test
    fun getWhiteSidePiecesTest(){
        val cubeState = CubeState.SOLVED_CUBE_STATE
        val sideRelativePieceDetection = SideRelativePieceDetection(cubeState, WhiteSide)
        val pieces = sideRelativePieceDetection.getPiecesForSide()
        assert(pieces.size == 8)
        assert(
            pieces.contains(
                ElementOrientation(
                    "white", PieceType.CORNER, 1, 1, 3
                )
            )
        )
        assert(
            pieces.contains(
                ElementOrientation(
                    "white", PieceType.CORNER, 2, 2, 3
                )
            )
        )
        assert(
            pieces.contains(
                ElementOrientation(
                    "white", PieceType.CORNER, 5, 5, 3
                )
            )
        )
        assert(
            pieces.contains(
                ElementOrientation(
                    "white", PieceType.CORNER, 6, 6, 3
                )
            )
        )

        assert(
            pieces.contains(
                ElementOrientation(
                    "white", PieceType.EDGE, 2, 2, 0
                )
            )
        )
        assert(
            pieces.contains(
                ElementOrientation(
                    "white", PieceType.EDGE, 5, 5, 0
                )
            )
        )
        assert(
            pieces.contains(
                ElementOrientation(
                    "white", PieceType.EDGE, 6, 6, 0
                )
            )
        )
        assert(
            pieces.contains(
                ElementOrientation(
                    "white", PieceType.EDGE, 10, 10, 0
                )
            )
        )
    }

    @Test
    fun getBlueSidePiecesTest(){
        val cubeState = CubeState.SOLVED_CUBE_STATE
        val sideRelativePieceDetection = SideRelativePieceDetection(cubeState, BlueSide)
        val pieces = sideRelativePieceDetection.getPiecesForSide()
        assert(pieces.size == 8)
        assert(
            pieces.contains(
                ElementOrientation(
                    "blue", PieceType.CORNER, 4, 4, 3
                )
            )
        )
        assert(
            pieces.contains(
                ElementOrientation(
                    "blue", PieceType.CORNER, 5, 5, 3
                )
            )
        )
        assert(
            pieces.contains(
                ElementOrientation(
                    "blue", PieceType.CORNER, 6, 6, 3
                )
            )
        )
        assert(
            pieces.contains(
                ElementOrientation(
                    "blue", PieceType.CORNER, 7, 7, 3
                )
            )
        )

        assert(
            pieces.contains(
                ElementOrientation(
                    "blue", PieceType.EDGE, 8, 8, 0
                )
            )
        )
        assert(
            pieces.contains(
                ElementOrientation(
                    "blue", PieceType.EDGE, 9, 9, 0
                )
            )
        )
        assert(
            pieces.contains(
                ElementOrientation(
                    "blue", PieceType.EDGE, 10, 10, 0
                )
            )
        )
        assert(
            pieces.contains(
                ElementOrientation(
                    "blue", PieceType.EDGE, 11, 11, 0
                )
            )
        )
    }

    @Test
    fun getGreenSidePiecesTest(){
        val cubeState = CubeState.SOLVED_CUBE_STATE
        val sideRelativePieceDetection = SideRelativePieceDetection(cubeState, GreenSide)
        val pieces = sideRelativePieceDetection.getPiecesForSide()
        assert(pieces.size == 8)
        assert(
            pieces.contains(
                ElementOrientation(
                    "green", PieceType.CORNER, 0, 0, 3
                )
            )
        )
        assert(
            pieces.contains(
                ElementOrientation(
                    "green", PieceType.CORNER, 1, 1, 3
                )
            )
        )
        assert(
            pieces.contains(
                ElementOrientation(
                    "green", PieceType.CORNER, 2, 2, 3
                )
            )
        )
        assert(
            pieces.contains(
                ElementOrientation(
                    "green", PieceType.CORNER, 3, 3, 3
                )
            )
        )

        assert(
            pieces.contains(
                ElementOrientation(
                    "green", PieceType.EDGE, 0, 0, 0
                )
            )
        )
        assert(
            pieces.contains(
                ElementOrientation(
                    "green", PieceType.EDGE, 1, 1, 0
                )
            )
        )
        assert(
            pieces.contains(
                ElementOrientation(
                    "green", PieceType.EDGE, 2, 2, 0
                )
            )
        )
        assert(
            pieces.contains(
                ElementOrientation(
                    "green", PieceType.EDGE, 3, 3, 0
                )
            )
        )
    }

    @Test
    fun getOrangeSidePiecesTest(){
        val cubeState = CubeState.SOLVED_CUBE_STATE
        val sideRelativePieceDetection = SideRelativePieceDetection(cubeState, OrangeSide)
        val pieces = sideRelativePieceDetection.getPiecesForSide()
        assert(pieces.size == 8)
        assert(
            pieces.contains(
                ElementOrientation(
                    "orange", PieceType.CORNER, 2, 2, 3
                )
            )
        )
        assert(
            pieces.contains(
                ElementOrientation(
                    "orange", PieceType.CORNER, 3, 3, 3
                )
            )
        )
        assert(
            pieces.contains(
                ElementOrientation(
                    "orange", PieceType.CORNER, 6, 6, 3
                )
            )
        )
        assert(
            pieces.contains(
                ElementOrientation(
                    "orange", PieceType.CORNER, 7, 7, 3
                )
            )
        )

        assert(
            pieces.contains(
                ElementOrientation(
                    "orange", PieceType.EDGE, 3, 3, 0
                )
            )
        )
        assert(
            pieces.contains(
                ElementOrientation(
                    "orange", PieceType.EDGE, 6, 6, 0
                )
            )
        )
        assert(
            pieces.contains(
                ElementOrientation(
                    "orange", PieceType.EDGE, 7, 7, 0
                )
            )
        )
        assert(
            pieces.contains(
                ElementOrientation(
                    "orange", PieceType.EDGE, 11, 11, 0
                )
            )
        )
    }

    @Test
    fun getRedSidePiecesTest(){
        val cubeState = CubeState.SOLVED_CUBE_STATE
        val sideRelativePieceDetection = SideRelativePieceDetection(cubeState, RedSide)
        val pieces = sideRelativePieceDetection.getPiecesForSide()
        assert(pieces.size == 8)
        assert(
            pieces.contains(
                ElementOrientation(
                    "red", PieceType.CORNER, 0, 0, 3
                )
            )
        )
        assert(
            pieces.contains(
                ElementOrientation(
                    "red", PieceType.CORNER, 1, 1, 3
                )
            )
        )
        assert(
            pieces.contains(
                ElementOrientation(
                    "red", PieceType.CORNER, 4, 4, 3
                )
            )
        )
        assert(
            pieces.contains(
                ElementOrientation(
                    "red", PieceType.CORNER, 5, 5, 3
                )
            )
        )

        assert(
            pieces.contains(
                ElementOrientation(
                    "red", PieceType.EDGE, 1, 1, 0
                )
            )
        )
        assert(
            pieces.contains(
                ElementOrientation(
                    "red", PieceType.EDGE, 4, 4, 0
                )
            )
        )
        assert(
            pieces.contains(
                ElementOrientation(
                    "red", PieceType.EDGE, 5, 5, 0
                )
            )
        )
        assert(
            pieces.contains(
                ElementOrientation(
                    "red", PieceType.EDGE, 9, 9, 0
                )
            )
        )
    }

}