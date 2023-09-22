package com.example.smartcubeapp.casedetection.olldetection.ollcase

import com.example.smartcubeapp.casedetection.olldetection.OLLPositionRepresentationElement

interface OLLCase {
    val incorrectlyOrientedPieces: MutableList<OLLPositionRepresentationElement>

    override fun equals(other: Any?): Boolean
}