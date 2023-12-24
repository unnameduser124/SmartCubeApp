package com.example.cube_detection.casedetection.plldetection.pllcase

import com.example.cube_detection.casedetection.plldetection.PLLElementPosition

interface PLLCase {
    val lastLayerPieces: MutableList<PLLElementPosition>

    override fun equals(other: Any?): Boolean
}