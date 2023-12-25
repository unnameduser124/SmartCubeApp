package com.example.cube_detection.casedetection.plldetection.pllcase

import com.example.cube_detection.casedetection.plldetection.PLLElementPosition

class CustomPLLCase(override var lastLayerPieces: MutableList<PLLElementPosition>) :
    PLLCase {

    constructor() : this(mutableListOf())

    override fun equals(other: Any?): Boolean {
        if (other is PLLCase) {
            val otherPiecesCopy = other.lastLayerPieces.toMutableList()

            if (lastLayerPieces.size != otherPiecesCopy.size) {
                return false
            }

            lastLayerPieces.forEach { piece ->
                if (otherPiecesCopy.contains(piece)) {
                    otherPiecesCopy.remove(piece)
                } else {
                    return false
                }
            }
            return otherPiecesCopy.isEmpty()
        }
        return false
    }
}