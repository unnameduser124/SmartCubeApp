package com.example.smartcubeapp.cube.piece

data class ElementOrientation(
    val sideName: String,
    val pieceType: PieceType,
    val pieceNumber: Int,
    val piecePosition: Int,
    val pieceOrientation: Int,
    var sideRelativeOrientation: Orientation? = null,
    var savedToDatabase: Boolean = false
){
    override fun equals(other: Any?): Boolean {
        if (other is ElementOrientation) {
            if (this.sideName == other.sideName
                && this.pieceType == other.pieceType
                && this.pieceNumber == other.pieceNumber
                && this.piecePosition == other.piecePosition
                && this.pieceOrientation == other.pieceOrientation
                && this.sideRelativeOrientation == other.sideRelativeOrientation
            ) {
                return true
            }
        }
        return false
    }

    override fun hashCode(): Int {
        var result = sideName.hashCode()
        result = 31 * result + pieceType.hashCode()
        result = 31 * result + pieceNumber
        result = 31 * result + piecePosition
        result = 31 * result + pieceOrientation
        result = 31 * result + (sideRelativeOrientation?.hashCode() ?: 0)
        return result
    }
}