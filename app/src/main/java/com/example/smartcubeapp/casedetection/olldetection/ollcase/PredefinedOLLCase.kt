package com.example.smartcubeapp.casedetection.olldetection.ollcase

import com.example.smartcubeapp.cube.piece.Orientation
import com.example.smartcubeapp.cube.piece.PieceType
import com.example.smartcubeapp.casedetection.olldetection.OLLPositionRepresentationElement

enum class PredefinedOLLCase(override val incorrectlyOrientedPieces: MutableList<OLLPositionRepresentationElement> = mutableListOf()) :
    OLLCase {
    OLL_01(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_02(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_03(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_04(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_05(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_06(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_07(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_08(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_09(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            ),
        )
    ),
    OLL_10(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 2)
            ),
        )
    ),
    OLL_11(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            ),
        )
    ),
    OLL_12(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 2)
            ),
        )
    ),
    OLL_13(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_14(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_15(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_16(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_17(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_18(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_19(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_20(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_21(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 2)
            ),
        )
    ),
    OLL_22(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 2)
            ),
        )
    ),
    OLL_23(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 2)
            ),
        )
    ),
    OLL_24(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 0)
            ),
        )
    ),
    OLL_25(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 2)
            ),
        )
    ),
    OLL_26(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 2)
            ),
        )
    ),
    OLL_27(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 2)
            ),
        )
    ),
    OLL_28(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_29(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 2)
            ),
        )
    ),
    OLL_30(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_31(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_32(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            ),
        )
    ),
    OLL_33(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_34(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_35(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            ),
        )
    ),
    OLL_36(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_37(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_38(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_39(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_40(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 2)
            ),
        )
    ),
    OLL_41(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_42(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 2)
            ),
        )
    ),
    OLL_43(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            )
        )
    ),
    OLL_44(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 2)
            )
        )
    ),
    OLL_45(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_46(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 2)
            ),
        )
    ),
    OLL_47(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_48(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_49(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_50(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            ),
        )
    ),
    OLL_51(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_52(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 2)
            ),
        )
    ),
    OLL_53(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            ),
        )
    ),
    OLL_54(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_55(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 2)
            ),
        )
    ),
    OLL_56(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 0)
            ),
            OLLPositionRepresentationElement(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 2)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_57(
        mutableListOf(
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLPositionRepresentationElement(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    )
}