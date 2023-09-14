package com.example.smartcubeapp.cube.piece

class OLLPositionRepresentationElement(
    val pieceType: PieceType,
    var sideRelativeOrientation: Orientation? = null,
    var sideRelativePosition: Pair<Int, Int>? = null
) {
    //This override is temporary, excluding pieceNumber is necessary for OLLDetection tests to work
    //but it may be necessary to include it for PLLDetection or write a separate class for PLLDetection
    override fun equals(other: Any?): Boolean {
        return if (other is OLLPositionRepresentationElement) {
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