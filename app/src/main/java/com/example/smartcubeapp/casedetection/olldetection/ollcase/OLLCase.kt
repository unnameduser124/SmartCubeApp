package com.example.smartcubeapp.casedetection.olldetection.ollcase

import com.example.smartcubeapp.casedetection.olldetection.OLLElementOrientation

interface OLLCase {
    val incorrectlyOrientedPieces: MutableList<OLLElementOrientation>

    override fun equals(other: Any?): Boolean
}