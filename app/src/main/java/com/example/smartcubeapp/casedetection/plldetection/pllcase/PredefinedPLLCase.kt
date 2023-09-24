package com.example.smartcubeapp.casedetection.plldetection.pllcase

import com.example.smartcubeapp.casedetection.plldetection.PLLElementPosition
import com.example.smartcubeapp.cube.piece.PieceType

class PredefinedPLLCase(val position: Array<Array<PLLElementPosition>>){
    //PLL cases to be populated with hardcoded values
    fun Aa(): Array<Array<PLLElementPosition>>{
        return arrayOf(
            arrayOf(
                PLLElementPosition(PieceType.CORNER, position[2][0].pieceNumber, Pair(0, 0)),
                PLLElementPosition(PieceType.EDGE, position[0][1].pieceNumber, Pair(0, 1)),
                PLLElementPosition(PieceType.CORNER, position[0][0].pieceNumber, Pair(0, 2))
            ),
            arrayOf(
                PLLElementPosition(PieceType.EDGE, position[1][0].pieceNumber, Pair(1, 0)),
                PLLElementPosition(PieceType.EDGE, position[1][1].pieceNumber, Pair(1, 2))
            ),
            arrayOf(
                PLLElementPosition(PieceType.CORNER, position[0][2].pieceNumber, Pair(2, 0)),
                PLLElementPosition(PieceType.EDGE, position[2][1].pieceNumber, Pair(2, 1)),
                PLLElementPosition(PieceType.CORNER, position[2][2].pieceNumber, Pair(2, 2))
            )
        )
    }
    fun Ab(): Array<Array<PLLElementPosition>>{
        return arrayOf(
            arrayOf(
                PLLElementPosition(PieceType.CORNER, position[2][2].pieceNumber, Pair(0, 0)),
                PLLElementPosition(PieceType.EDGE, position[0][1].pieceNumber, Pair(0, 1)),
                PLLElementPosition(PieceType.CORNER, position[0][2].pieceNumber, Pair(0, 2))
            ),
            arrayOf(
                PLLElementPosition(PieceType.EDGE, position[1][0].pieceNumber, Pair(1, 0)),
                PLLElementPosition(PieceType.EDGE, position[1][1].pieceNumber, Pair(1, 2))
            ),
            arrayOf(
                PLLElementPosition(PieceType.CORNER, position[0][0].pieceNumber, Pair(2, 0)),
                PLLElementPosition(PieceType.EDGE, position[2][1].pieceNumber, Pair(2, 1)),
                PLLElementPosition(PieceType.CORNER, position[2][0].pieceNumber, Pair(2, 2))
            )
        )
    }
    fun E(): Array<Array<PLLElementPosition>>{
        return arrayOf(
            arrayOf(
                PLLElementPosition(PieceType.CORNER, position[2][0].pieceNumber, Pair(0, 0)),
                PLLElementPosition(PieceType.EDGE, position[0][1].pieceNumber, Pair(0, 1)),
                PLLElementPosition(PieceType.CORNER, position[2][2].pieceNumber, Pair(0, 2))
            ),
            arrayOf(
                PLLElementPosition(PieceType.EDGE, position[1][0].pieceNumber, Pair(1, 0)),
                PLLElementPosition(PieceType.EDGE, position[1][1].pieceNumber, Pair(1, 2))
            ),
            arrayOf(
                PLLElementPosition(PieceType.CORNER, position[0][0].pieceNumber, Pair(2, 0)),
                PLLElementPosition(PieceType.EDGE, position[2][1].pieceNumber, Pair(2, 1)),
                PLLElementPosition(PieceType.CORNER, position[0][2].pieceNumber, Pair(2, 2))
            )
        )
    }
    fun F(): Array<Array<PLLElementPosition>>{
        return arrayOf(
            arrayOf(
                PLLElementPosition(PieceType.CORNER, position[0][0].pieceNumber, Pair(0, 0)),
                PLLElementPosition(PieceType.EDGE, position[2][1].pieceNumber, Pair(0, 1)),
                PLLElementPosition(PieceType.CORNER, position[2][2].pieceNumber, Pair(0, 2))
            ),
            arrayOf(
                PLLElementPosition(PieceType.EDGE, position[1][0].pieceNumber, Pair(1, 0)),
                PLLElementPosition(PieceType.EDGE, position[1][1].pieceNumber, Pair(1, 2))
            ),
            arrayOf(
                PLLElementPosition(PieceType.CORNER, position[2][0].pieceNumber, Pair(2, 0)),
                PLLElementPosition(PieceType.EDGE, position[0][1].pieceNumber, Pair(2, 1)),
                PLLElementPosition(PieceType.CORNER, position[0][2].pieceNumber, Pair(2, 2))
            )
        )
    }
    fun Ga(): Array<Array<PLLElementPosition>>{
        return arrayOf(
            arrayOf(
                PLLElementPosition(PieceType.CORNER, position[2][0].pieceNumber, Pair(0, 0)),
                PLLElementPosition(PieceType.EDGE, position[1][1].pieceNumber, Pair(0, 1)),
                PLLElementPosition(PieceType.CORNER, position[0][0].pieceNumber, Pair(0, 2))
            ),
            arrayOf(
                PLLElementPosition(PieceType.EDGE, position[0][1].pieceNumber, Pair(1, 0)),
                PLLElementPosition(PieceType.EDGE, position[1][0].pieceNumber, Pair(1, 2))
            ),
            arrayOf(
                PLLElementPosition(PieceType.CORNER, position[0][2].pieceNumber, Pair(2, 0)),
                PLLElementPosition(PieceType.EDGE, position[2][1].pieceNumber, Pair(2, 1)),
                PLLElementPosition(PieceType.CORNER, position[2][2].pieceNumber, Pair(2, 2))
            )
        )
    }
    fun Gb(): Array<Array<PLLElementPosition>>{
        return arrayOf(
            arrayOf(
                PLLElementPosition(PieceType.CORNER, position[0][2].pieceNumber, Pair(0, 0)),
                PLLElementPosition(PieceType.EDGE, position[1][0].pieceNumber, Pair(0, 1)),
                PLLElementPosition(PieceType.CORNER, position[2][0].pieceNumber, Pair(0, 2))
            ),
            arrayOf(
                PLLElementPosition(PieceType.EDGE, position[1][1].pieceNumber, Pair(1, 0)),
                PLLElementPosition(PieceType.EDGE, position[0][1].pieceNumber, Pair(1, 2))
            ),
            arrayOf(
                PLLElementPosition(PieceType.CORNER, position[0][0].pieceNumber, Pair(2, 0)),
                PLLElementPosition(PieceType.EDGE, position[2][1].pieceNumber, Pair(2, 1)),
                PLLElementPosition(PieceType.CORNER, position[2][2].pieceNumber, Pair(2, 2))
            )
        )
    }
    fun Gc(): Array<Array<PLLElementPosition>>{
        return arrayOf(
            arrayOf(
                PLLElementPosition(PieceType.CORNER, position[2][2].pieceNumber, Pair(0, 0)),
                PLLElementPosition(PieceType.EDGE, position[0][1].pieceNumber, Pair(0, 1)),
                PLLElementPosition(PieceType.CORNER, position[0][2].pieceNumber, Pair(0, 2))
            ),
            arrayOf(
                PLLElementPosition(PieceType.EDGE, position[2][1].pieceNumber, Pair(1, 0)),
                PLLElementPosition(PieceType.EDGE, position[1][0].pieceNumber, Pair(1, 2))
            ),
            arrayOf(
                PLLElementPosition(PieceType.CORNER, position[0][0].pieceNumber, Pair(2, 0)),
                PLLElementPosition(PieceType.EDGE, position[1][1].pieceNumber, Pair(2, 1)),
                PLLElementPosition(PieceType.CORNER, position[2][0].pieceNumber, Pair(2, 2))
            )
        )
    }
    fun Gd(): Array<Array<PLLElementPosition>>{
        return arrayOf(
            arrayOf(
                PLLElementPosition(PieceType.CORNER, position[2][0].pieceNumber, Pair(0, 0)),
                PLLElementPosition(PieceType.EDGE, position[2][1].pieceNumber, Pair(0, 1)),
                PLLElementPosition(PieceType.CORNER, position[0][0].pieceNumber, Pair(0, 2))
            ),
            arrayOf(
                PLLElementPosition(PieceType.EDGE, position[0][1].pieceNumber, Pair(1, 0)),
                PLLElementPosition(PieceType.EDGE, position[1][1].pieceNumber, Pair(1, 2))
            ),
            arrayOf(
                PLLElementPosition(PieceType.CORNER, position[0][2].pieceNumber, Pair(2, 0)),
                PLLElementPosition(PieceType.EDGE, position[1][0].pieceNumber, Pair(2, 1)),
                PLLElementPosition(PieceType.CORNER, position[2][2].pieceNumber, Pair(2, 2))
            )
        )
    }
    fun H(): Array<Array<PLLElementPosition>>{
        return arrayOf(
            arrayOf(
                PLLElementPosition(PieceType.CORNER, position[0][0].pieceNumber, Pair(0, 0)),
                PLLElementPosition(PieceType.EDGE, position[2][1].pieceNumber, Pair(0, 1)),
                PLLElementPosition(PieceType.CORNER, position[0][2].pieceNumber, Pair(0, 2))
            ),
            arrayOf(
                PLLElementPosition(PieceType.EDGE, position[1][1].pieceNumber, Pair(1, 0)),
                PLLElementPosition(PieceType.EDGE, position[1][0].pieceNumber, Pair(1, 2))
            ),
            arrayOf(
                PLLElementPosition(PieceType.CORNER, position[2][0].pieceNumber, Pair(2, 0)),
                PLLElementPosition(PieceType.EDGE, position[0][1].pieceNumber, Pair(2, 1)),
                PLLElementPosition(PieceType.CORNER, position[2][2].pieceNumber, Pair(2, 2))
            )
        )
    }
    fun Ja(): Array<Array<PLLElementPosition>>{
        return arrayOf(
            arrayOf(
                PLLElementPosition(PieceType.CORNER, position[0][0].pieceNumber, Pair(0, 0)),
                PLLElementPosition(PieceType.EDGE, position[1][1].pieceNumber, Pair(0, 1)),
                PLLElementPosition(PieceType.CORNER, position[2][2].pieceNumber, Pair(0, 2))
            ),
            arrayOf(
                PLLElementPosition(PieceType.EDGE, position[1][0].pieceNumber, Pair(1, 0)),
                PLLElementPosition(PieceType.EDGE, position[0][1].pieceNumber, Pair(1, 2))
            ),
            arrayOf(
                PLLElementPosition(PieceType.CORNER, position[2][0].pieceNumber, Pair(2, 0)),
                PLLElementPosition(PieceType.EDGE, position[2][1].pieceNumber, Pair(2, 1)),
                PLLElementPosition(PieceType.CORNER, position[0][2].pieceNumber, Pair(2, 2))
            )
        )
    }
    fun Jb(): Array<Array<PLLElementPosition>>{
        return arrayOf(
            arrayOf(
                PLLElementPosition(PieceType.CORNER, position[0][0].pieceNumber, Pair(0, 0)),
                PLLElementPosition(PieceType.EDGE, position[0][1].pieceNumber, Pair(0, 1)),
                PLLElementPosition(PieceType.CORNER, position[2][2].pieceNumber, Pair(0, 2))
            ),
            arrayOf(
                PLLElementPosition(PieceType.EDGE, position[1][0].pieceNumber, Pair(1, 0)),
                PLLElementPosition(PieceType.EDGE, position[2][1].pieceNumber, Pair(1, 2))
            ),
            arrayOf(
                PLLElementPosition(PieceType.CORNER, position[2][0].pieceNumber, Pair(2, 0)),
                PLLElementPosition(PieceType.EDGE, position[1][1].pieceNumber, Pair(2, 1)),
                PLLElementPosition(PieceType.CORNER, position[0][2].pieceNumber, Pair(2, 2))
            )
        )
    }
    fun Na(): Array<Array<PLLElementPosition>>{
        return arrayOf(
            arrayOf(
                PLLElementPosition(PieceType.CORNER, position[0][0].pieceNumber, Pair(0, 0)),
                PLLElementPosition(PieceType.EDGE, position[0][1].pieceNumber, Pair(0, 1)),
                PLLElementPosition(PieceType.CORNER, position[2][0].pieceNumber, Pair(0, 2))
            ),
            arrayOf(
                PLLElementPosition(PieceType.EDGE, position[1][1].pieceNumber, Pair(1, 0)),
                PLLElementPosition(PieceType.EDGE, position[1][0].pieceNumber, Pair(1, 2))
            ),
            arrayOf(
                PLLElementPosition(PieceType.CORNER, position[0][2].pieceNumber, Pair(2, 0)),
                PLLElementPosition(PieceType.EDGE, position[2][1].pieceNumber, Pair(2, 1)),
                PLLElementPosition(PieceType.CORNER, position[2][2].pieceNumber, Pair(2, 2))
            )
        )
    }
    fun Nb(): Array<Array<PLLElementPosition>>{
        return arrayOf(
            arrayOf(
                PLLElementPosition(PieceType.CORNER, position[2][2].pieceNumber, Pair(0, 0)),
                PLLElementPosition(PieceType.EDGE, position[0][1].pieceNumber, Pair(0, 1)),
                PLLElementPosition(PieceType.CORNER, position[0][2].pieceNumber, Pair(0, 2))
            ),
            arrayOf(
                PLLElementPosition(PieceType.EDGE, position[1][1].pieceNumber, Pair(1, 0)),
                PLLElementPosition(PieceType.EDGE, position[1][0].pieceNumber, Pair(1, 2))
            ),
            arrayOf(
                PLLElementPosition(PieceType.CORNER, position[2][0].pieceNumber, Pair(2, 0)),
                PLLElementPosition(PieceType.EDGE, position[2][1].pieceNumber, Pair(2, 1)),
                PLLElementPosition(PieceType.CORNER, position[0][0].pieceNumber, Pair(2, 2))
            )
        )
    }
    fun Ra(): Array<Array<PLLElementPosition>>{
        return arrayOf(
            arrayOf(
                PLLElementPosition(PieceType.CORNER, position[0][0].pieceNumber, Pair(0, 0)),
                PLLElementPosition(PieceType.EDGE, position[1][0].pieceNumber, Pair(0, 1)),
                PLLElementPosition(PieceType.CORNER, position[2][2].pieceNumber, Pair(0, 2))
            ),
            arrayOf(
                PLLElementPosition(PieceType.EDGE, position[0][1].pieceNumber, Pair(1, 0)),
                PLLElementPosition(PieceType.EDGE, position[1][1].pieceNumber, Pair(1, 2))
            ),
            arrayOf(
                PLLElementPosition(PieceType.CORNER, position[2][0].pieceNumber, Pair(2, 0)),
                PLLElementPosition(PieceType.EDGE, position[2][1].pieceNumber, Pair(2, 1)),
                PLLElementPosition(PieceType.CORNER, position[0][2].pieceNumber, Pair(2, 2))
            )
        )
    }
    fun Rb(): Array<Array<PLLElementPosition>>{
        return arrayOf(
            arrayOf(
                PLLElementPosition(PieceType.CORNER, position[0][0].pieceNumber, Pair(0, 0)),
                PLLElementPosition(PieceType.EDGE, position[0][1].pieceNumber, Pair(0, 1)),
                PLLElementPosition(PieceType.CORNER, position[2][2].pieceNumber, Pair(0, 2))
            ),
            arrayOf(
                PLLElementPosition(PieceType.EDGE, position[2][1].pieceNumber, Pair(1, 0)),
                PLLElementPosition(PieceType.EDGE, position[1][1].pieceNumber, Pair(1, 2))
            ),
            arrayOf(
                PLLElementPosition(PieceType.CORNER, position[2][0].pieceNumber, Pair(2, 0)),
                PLLElementPosition(PieceType.EDGE, position[1][0].pieceNumber, Pair(2, 1)),
                PLLElementPosition(PieceType.CORNER, position[0][2].pieceNumber, Pair(2, 2))
            )
        )
    }
    fun T(): Array<Array<PLLElementPosition>>{
        return arrayOf(
            arrayOf(
                PLLElementPosition(PieceType.CORNER, position[0][0].pieceNumber, Pair(0, 0)),
                PLLElementPosition(PieceType.EDGE, position[0][1].pieceNumber, Pair(0, 1)),
                PLLElementPosition(PieceType.CORNER, position[2][2].pieceNumber, Pair(0, 2))
            ),
            arrayOf(
                PLLElementPosition(PieceType.EDGE, position[1][1].pieceNumber, Pair(1, 0)),
                PLLElementPosition(PieceType.EDGE, position[1][0].pieceNumber, Pair(1, 2))
            ),
            arrayOf(
                PLLElementPosition(PieceType.CORNER, position[2][0].pieceNumber, Pair(2, 0)),
                PLLElementPosition(PieceType.EDGE, position[2][1].pieceNumber, Pair(2, 1)),
                PLLElementPosition(PieceType.CORNER, position[0][2].pieceNumber, Pair(2, 2))
            )
        )
    }
    fun Ua(): Array<Array<PLLElementPosition>>{
        return arrayOf(
            arrayOf(
                PLLElementPosition(PieceType.CORNER, position[0][0].pieceNumber, Pair(0, 0)),
                PLLElementPosition(PieceType.EDGE, position[0][1].pieceNumber, Pair(0, 1)),
                PLLElementPosition(PieceType.CORNER, position[0][2].pieceNumber, Pair(0, 2))
            ),
            arrayOf(
                PLLElementPosition(PieceType.EDGE, position[1][1].pieceNumber, Pair(1, 0)),
                PLLElementPosition(PieceType.EDGE, position[2][1].pieceNumber, Pair(1, 2))
            ),
            arrayOf(
                PLLElementPosition(PieceType.CORNER, position[2][0].pieceNumber, Pair(2, 0)),
                PLLElementPosition(PieceType.EDGE, position[1][0].pieceNumber, Pair(2, 1)),
                PLLElementPosition(PieceType.CORNER, position[2][2].pieceNumber, Pair(2, 2))
            )
        )
    }
    fun Ub(): Array<Array<PLLElementPosition>>{
        return arrayOf(
            arrayOf(
                PLLElementPosition(PieceType.CORNER, position[0][0].pieceNumber, Pair(0, 0)),
                PLLElementPosition(PieceType.EDGE, position[0][1].pieceNumber, Pair(0, 1)),
                PLLElementPosition(PieceType.CORNER, position[0][2].pieceNumber, Pair(0, 2))
            ),
            arrayOf(
                PLLElementPosition(PieceType.EDGE, position[2][1].pieceNumber, Pair(1, 0)),
                PLLElementPosition(PieceType.EDGE, position[1][0].pieceNumber, Pair(1, 2))
            ),
            arrayOf(
                PLLElementPosition(PieceType.CORNER, position[2][0].pieceNumber, Pair(2, 0)),
                PLLElementPosition(PieceType.EDGE, position[1][1].pieceNumber, Pair(2, 1)),
                PLLElementPosition(PieceType.CORNER, position[2][2].pieceNumber, Pair(2, 2))
            )
        )
    }
    fun V(): Array<Array<PLLElementPosition>>{
        return arrayOf(
            arrayOf(
                PLLElementPosition(PieceType.CORNER, position[2][2].pieceNumber, Pair(0, 0)),
                PLLElementPosition(PieceType.EDGE, position[1][1].pieceNumber, Pair(0, 1)),
                PLLElementPosition(PieceType.CORNER, position[0][2].pieceNumber, Pair(0, 2))
            ),
            arrayOf(
                PLLElementPosition(PieceType.EDGE, position[1][0].pieceNumber, Pair(1, 0)),
                PLLElementPosition(PieceType.EDGE, position[0][1].pieceNumber, Pair(1, 2))
            ),
            arrayOf(
                PLLElementPosition(PieceType.CORNER, position[2][0].pieceNumber, Pair(2, 0)),
                PLLElementPosition(PieceType.EDGE, position[2][1].pieceNumber, Pair(2, 1)),
                PLLElementPosition(PieceType.CORNER, position[0][0].pieceNumber, Pair(2, 2))
            )
        )
    }
    fun Y(): Array<Array<PLLElementPosition>>{
        return arrayOf(
            arrayOf(
                PLLElementPosition(PieceType.CORNER, position[2][2].pieceNumber, Pair(0, 0)),
                PLLElementPosition(PieceType.EDGE, position[1][0].pieceNumber, Pair(0, 1)),
                PLLElementPosition(PieceType.CORNER, position[0][2].pieceNumber, Pair(0, 2))
            ),
            arrayOf(
                PLLElementPosition(PieceType.EDGE, position[0][1].pieceNumber, Pair(1, 0)),
                PLLElementPosition(PieceType.EDGE, position[1][1].pieceNumber, Pair(1, 2))
            ),
            arrayOf(
                PLLElementPosition(PieceType.CORNER, position[2][0].pieceNumber, Pair(2, 0)),
                PLLElementPosition(PieceType.EDGE, position[2][1].pieceNumber, Pair(2, 1)),
                PLLElementPosition(PieceType.CORNER, position[0][0].pieceNumber, Pair(2, 2))
            )
        )
    }
    fun Z(): Array<Array<PLLElementPosition>>{
        return arrayOf(
            arrayOf(
                PLLElementPosition(PieceType.CORNER, position[0][0].pieceNumber, Pair(0, 0)),
                PLLElementPosition(PieceType.EDGE, position[1][1].pieceNumber, Pair(0, 1)),
                PLLElementPosition(PieceType.CORNER, position[0][2].pieceNumber, Pair(0, 2))
            ),
            arrayOf(
                PLLElementPosition(PieceType.EDGE, position[2][1].pieceNumber, Pair(1, 0)),
                PLLElementPosition(PieceType.EDGE, position[0][1].pieceNumber, Pair(1, 2))
            ),
            arrayOf(
                PLLElementPosition(PieceType.CORNER, position[2][0].pieceNumber, Pair(2, 0)),
                PLLElementPosition(PieceType.EDGE, position[1][0].pieceNumber, Pair(2, 1)),
                PLLElementPosition(PieceType.CORNER, position[2][2].pieceNumber, Pair(2, 2))
            )
        )
    }
}