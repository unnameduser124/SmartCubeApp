package com.example.cube_detection.casedetection.plldetection.pllcase

import com.example.cube_detection.casedetection.plldetection.PLLElementPosition
import com.example.cube_cube.cube.piece.PieceType

enum class PredefinedPLLCase() {
    Aa {
        override fun performPermutation(position: Array<Array<PLLElementPosition>>): Array<Array<PLLElementPosition>> {
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
    }, //#1

    Ab { //#1
        override fun performPermutation(position: Array<Array<PLLElementPosition>>): Array<Array<PLLElementPosition>> {
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
    }, //#2

    E {
        override fun performPermutation(position: Array<Array<PLLElementPosition>>): Array<Array<PLLElementPosition>> {
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
    }, //#3

    F {
        override fun performPermutation(position: Array<Array<PLLElementPosition>>): Array<Array<PLLElementPosition>> {
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
    }, //#4

    Ga {
        override fun performPermutation(position: Array<Array<PLLElementPosition>>): Array<Array<PLLElementPosition>> {
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
    }, //#5

    Gb {
        override fun performPermutation(position: Array<Array<PLLElementPosition>>): Array<Array<PLLElementPosition>> {
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
    }, //#6

    Gc {
        override fun performPermutation(position: Array<Array<PLLElementPosition>>): Array<Array<PLLElementPosition>> {
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
    }, //#7

    Gd {
        override fun performPermutation(position: Array<Array<PLLElementPosition>>): Array<Array<PLLElementPosition>> {
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
    }, //#8

    H {
        override fun performPermutation(position: Array<Array<PLLElementPosition>>): Array<Array<PLLElementPosition>> {
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
    }, //#9

    Ja {
        override fun performPermutation(position: Array<Array<PLLElementPosition>>): Array<Array<PLLElementPosition>> {
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
    }, //#10

    Jb {
        override fun performPermutation(position: Array<Array<PLLElementPosition>>): Array<Array<PLLElementPosition>> {
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
    }, //#11

    Na {
        override fun performPermutation(position: Array<Array<PLLElementPosition>>): Array<Array<PLLElementPosition>> {
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
    }, //#12

    Nb {
        override fun performPermutation(position: Array<Array<PLLElementPosition>>): Array<Array<PLLElementPosition>> {
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
    }, //#13

    Ra {
        override fun performPermutation(position: Array<Array<PLLElementPosition>>): Array<Array<PLLElementPosition>> {
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
    }, //#14

    Rb {
        override fun performPermutation(position: Array<Array<PLLElementPosition>>): Array<Array<PLLElementPosition>> {
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
    }, //#15

    T {
        override fun performPermutation(position: Array<Array<PLLElementPosition>>): Array<Array<PLLElementPosition>> {
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
    }, //#16

    Ua {
        override fun performPermutation(position: Array<Array<PLLElementPosition>>): Array<Array<PLLElementPosition>> {
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
    }, //#17

    Ub {
        override fun performPermutation(position: Array<Array<PLLElementPosition>>): Array<Array<PLLElementPosition>> {
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
    }, //#18

    V {
        override fun performPermutation(position: Array<Array<PLLElementPosition>>): Array<Array<PLLElementPosition>> {
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
    }, //#19

    Y {
        override fun performPermutation(position: Array<Array<PLLElementPosition>>): Array<Array<PLLElementPosition>> {
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
    }, //#20

    Z {
        override fun performPermutation(position: Array<Array<PLLElementPosition>>): Array<Array<PLLElementPosition>> {
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
    }, //#21
    PLLSkip{
        override fun performPermutation(position: Array<Array<PLLElementPosition>>): Array<Array<PLLElementPosition>> {
            return position
        }
    };


    abstract fun performPermutation(position: Array<Array<PLLElementPosition>>): Array<Array<PLLElementPosition>>
}