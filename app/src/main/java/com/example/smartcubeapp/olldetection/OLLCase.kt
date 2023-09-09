package com.example.smartcubeapp.olldetection

import com.example.smartcubeapp.cube.piece.PositionRepresentationElement

interface OLLCase {
    val incorrectlyOrientedPieces: MutableList<PositionRepresentationElement>


    override fun equals(other: Any?): Boolean
}