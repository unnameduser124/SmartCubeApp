package com.example.smartcubeapp.casedetection.plldetection

import com.example.smartcubeapp.cube.piece.PieceType

class PLLElementPosition(
    var pieceType: PieceType,
    var pieceNumber: Int,
    var sideRelativePosition: Pair<Int, Int>? = null,
){
    constructor(): this(PieceType.CORNER, 0)
}