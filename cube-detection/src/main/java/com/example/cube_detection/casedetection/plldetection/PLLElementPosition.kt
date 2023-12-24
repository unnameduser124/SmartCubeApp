package com.example.cube_detection.casedetection.plldetection

import com.example.cube_cube.cube.piece.PieceType

class PLLElementPosition(
    var pieceType: PieceType,
    var pieceNumber: Int,
    var sideRelativePosition: Pair<Int, Int>? = null,
){
    constructor(): this(PieceType.CORNER, 0)

    override fun equals(other: Any?): Boolean {
        if(other is PLLElementPosition){
            return pieceType == other.pieceType
                    && pieceNumber == other.pieceNumber
                    && sideRelativePosition == other.sideRelativePosition
        }
        return false
    }
}