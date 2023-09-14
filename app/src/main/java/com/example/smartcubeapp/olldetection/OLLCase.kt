package com.example.smartcubeapp.olldetection

import com.example.smartcubeapp.cube.piece.OLLPositionRepresentationElement

interface OLLCase {
    val incorrectlyOrientedPieces: MutableList<OLLPositionRepresentationElement>

    override fun equals(other: Any?): Boolean
}