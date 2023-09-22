package com.example.smartcubeapp.casedetection.plldetection.pllcase

import com.example.smartcubeapp.casedetection.plldetection.PLLPositionRepresentationElement

interface PLLCase {
    val lastLayerPieces: MutableList<PLLPositionRepresentationElement>

    override fun equals(other: Any?): Boolean
}