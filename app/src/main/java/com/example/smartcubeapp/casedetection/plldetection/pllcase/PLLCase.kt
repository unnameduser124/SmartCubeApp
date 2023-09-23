package com.example.smartcubeapp.casedetection.plldetection.pllcase

import com.example.smartcubeapp.casedetection.plldetection.PLLElementPosition

interface PLLCase {
    val lastLayerPieces: MutableList<PLLElementPosition>

    override fun equals(other: Any?): Boolean
}