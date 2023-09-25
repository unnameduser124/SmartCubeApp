package com.example.smartcubeapp.casedetection.plldetection.pllcase

import com.example.smartcubeapp.casedetection.plldetection.PLLElementPosition
import com.example.smartcubeapp.cube.piece.PieceType

enum class PredefinedPLLCase() {
    //PLL cases to be populated with hardcoded values
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
    },

    Ab {
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
    },

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
    },

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
    },

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
    },

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
    },

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
    },

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
    },

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
    },

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
    },

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
    },

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
    },

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
    },

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
    },

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
    },

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
    },

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
    },

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
    },

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
    },

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
    },

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
    };

    abstract fun performPermutation(position: Array<Array<PLLElementPosition>>): Array<Array<PLLElementPosition>>
}