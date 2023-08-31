package com.example.smartcubeapp.elementdatabase.element

data class ElementOrientation(
    val sideName: String,
    val pieceType: PieceType,
    val pieceNumber: Int,
    val piecePosition: Int,
    val pieceOrientation: Int,
    var sideRelativeOrientation: Boolean? = null,
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
}