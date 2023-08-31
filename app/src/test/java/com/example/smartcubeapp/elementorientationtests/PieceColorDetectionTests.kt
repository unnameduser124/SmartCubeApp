package com.example.smartcubeapp.elementorientationtests

import com.example.smartcubeapp.elementdatabase.element.PieceType
import com.example.smartcubeapp.elementdatabase.element.SideRelativePieceDetection
import org.junit.Test

class PieceColorDetectionTests {

    @Test
    fun whiteBlueRedCornerColorTest(){
        val detection = SideRelativePieceDetection.getPieceColors(5, PieceType.CORNER)

        assert(detection.size == 3)
        assert(detection.contains("white"))
        assert(detection.contains("blue"))
        assert(detection.contains("red"))
    }

    @Test
    fun yellowOrangeEdgeColorTest(){
        val detection = SideRelativePieceDetection.getPieceColors(7, PieceType.EDGE)

        assert(detection.size == 2)
        assert(detection.contains("yellow"))
        assert(detection.contains("orange"))
    }

    @Test
    fun yellowBlueOrangeCornerColorTest(){
        val detection = SideRelativePieceDetection.getPieceColors(7, PieceType.CORNER)

        assert(detection.size == 3)
        assert(detection.contains("yellow"))
        assert(detection.contains("blue"))
        assert(detection.contains("orange"))
    }

    @Test
    fun redGreenEdgeColorTest(){
        val detection = SideRelativePieceDetection.getPieceColors(1, PieceType.EDGE)

        assert(detection.size == 2)
        assert(detection.contains("red"))
        assert(detection.contains("green"))
    }
}