package com.example.smartcubeapp.casedetection.olldetection.ollcase

import com.example.smartcubeapp.casedetection.olldetection.OLLElementOrientation

class CustomOLLCase(override var incorrectlyOrientedPieces: MutableList<OLLElementOrientation>) :
    OLLCase {

    constructor() : this(mutableListOf())

    private infix fun <T> List<T>.equalsIgnoreOrder(other: List<T>) =
        this.size == other.size && this.toSet() == other.toSet()

    override fun equals(other: Any?): Boolean {
        if (other is OLLCase) {
            val otherPiecesCopy = other.incorrectlyOrientedPieces.toMutableList()

            if(incorrectlyOrientedPieces.size != otherPiecesCopy.size){
                return false
            }

            incorrectlyOrientedPieces.forEach { piece ->
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