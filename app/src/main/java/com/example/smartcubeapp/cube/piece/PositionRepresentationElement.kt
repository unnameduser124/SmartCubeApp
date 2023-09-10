package com.example.smartcubeapp.cube.piece

class PositionRepresentationElement(
    val pieceType: PieceType,
    val pieceNumber: Int,
    var sideRelativeOrientation: Orientation? = null,
    var sideRelativePosition: Pair<Int, Int>? = null
){
    override fun equals(other: Any?): Boolean {
        return if (other is PositionRepresentationElement) {
            pieceType == other.pieceType
                    && pieceNumber == other.pieceNumber
                    && sideRelativeOrientation == other.sideRelativeOrientation
                    && sideRelativePosition == other.sideRelativePosition
        } else {
            false
        }
    }
}