package com.example.cube_detection.casedetection.olldetection.ollcase

import com.example.cube_detection.casedetection.olldetection.OLLElementOrientation

interface OLLCase {
    val incorrectlyOrientedPieces: MutableList<OLLElementOrientation>

    override fun equals(other: Any?): Boolean
}