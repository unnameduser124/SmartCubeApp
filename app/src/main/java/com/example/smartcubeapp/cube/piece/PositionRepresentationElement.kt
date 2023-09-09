package com.example.smartcubeapp.cube.piece

class PositionRepresentationElement(
    val pieceType: PieceType,
    val pieceNumber: Int,
    var sideRelativeOrientation: Orientation? = null,
    var sideRelativePosition: Pair<Int, Int>? = null
)