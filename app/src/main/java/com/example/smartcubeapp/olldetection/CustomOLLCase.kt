package com.example.smartcubeapp.olldetection

import com.example.smartcubeapp.cube.piece.OLLPositionRepresentationElement

class CustomOLLCase(override val incorrectlyOrientedPieces: MutableList<OLLPositionRepresentationElement>) :
    OLLCase {

    private infix fun <T> List<T>.equalsIgnoreOrder(other: List<T>) =
        this.size == other.size && this.toSet() == other.toSet()

    override fun equals(other: Any?): Boolean {
        if (other is OLLCase) {
            val otherPiecesCopy = other.incorrectlyOrientedPieces.toMutableList()

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