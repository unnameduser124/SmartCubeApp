package com.example.smartcubeapp.olldetection

import com.example.smartcubeapp.cube.piece.PositionRepresentationElement

class CustomOLLCase(override val incorrectlyOrientedPieces: MutableList<PositionRepresentationElement>) : OLLCase {

    private infix fun <T> List<T>.equalsIgnoreOrder(other: List<T>) =
        this.size == other.size && this.toSet() == other.toSet()
    override fun equals(other: Any?): Boolean {
        if(other is OLLCase){
            return incorrectlyOrientedPieces equalsIgnoreOrder other.incorrectlyOrientedPieces
        }
        return false
    }
}