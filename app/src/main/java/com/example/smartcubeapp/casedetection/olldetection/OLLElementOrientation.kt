package com.example.smartcubeapp.casedetection.olldetection

import com.example.smartcubeapp.cube.piece.Orientation
import com.example.smartcubeapp.cube.piece.PieceType

class OLLElementOrientation(
    var pieceType: PieceType,
    var sideRelativeOrientation: Orientation? = null,
    var sideRelativePosition: Pair<Int, Int>? = null
) {

    constructor(): this(PieceType.CORNER)

    override fun equals(other: Any?): Boolean {
        return if (other is OLLElementOrientation) {
            pieceType == other.pieceType
                    && sideRelativeOrientation == other.sideRelativeOrientation
                    && sideRelativePosition == other.sideRelativePosition
        } else {
            false
        }
    }

    override fun hashCode(): Int {
        var result = pieceType.hashCode()
        result = 31 * result + (sideRelativeOrientation?.hashCode() ?: 0)
        result = 31 * result + (sideRelativePosition?.hashCode() ?: 0)
        return result
    }
}