package com.example.cube_detection.casedetection.olldetection.ollcase

import com.example.cube_cube.cube.piece.Orientation
import com.example.cube_cube.cube.piece.PieceType
import com.example.cube_detection.casedetection.olldetection.OLLElementOrientation

enum class PredefinedOLLCase(override val incorrectlyOrientedPieces: MutableList<OLLElementOrientation> = mutableListOf()) :
    OLLCase {
    OLL_01(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 2)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 2)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 2)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_02(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 2)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 2)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 2)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_03(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 2)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 2)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 2)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_04(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 2)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 0)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 2)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_05(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 2)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 2)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 2)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_06(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 2)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_07(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 2)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 2)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 2)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_08(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 2)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 0)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_09(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 2)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 2)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            ),
        )
    ),
    OLL_10(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 2)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 2)
            ),
        )
    ),
    OLL_11(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 2)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 2)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            ),
        )
    ),
    OLL_12(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 2)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 0)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 2)
            ),
        )
    ),
    OLL_13(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 2)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 2)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_14(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 2)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 0)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_15(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 2)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 0)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_16(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 2)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_17(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 2)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 0)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 2)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_18(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 2)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 2)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_19(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 2)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 2)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_20(
        mutableListOf(
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 2)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_21(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 2)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 2)
            ),
        )
    ),
    OLL_22(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 2)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 2)
            ),
        )
    ),
    OLL_23(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 2)
            ),
        )
    ),
    OLL_24(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 0)
            ),
        )
    ),
    OLL_25(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 2)
            ),
        )
    ),
    OLL_26(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 2)
            ),
        )
    ),
    OLL_27(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 2)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 2)
            ),
        )
    ),
    OLL_28(
        mutableListOf(
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 2)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_29(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 2)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 2)
            ),
        )
    ),
    OLL_30(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 2)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 2)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_31(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 0)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_32(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 0)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            ),
        )
    ),
    OLL_33(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 0)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_34(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 2)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_35(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 2)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 0)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            ),
        )
    ),
    OLL_36(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 2)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 0)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_37(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 2)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 0)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 2)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_38(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 2)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 2)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_39(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 2)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_40(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 2)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 2)
            ),
        )
    ),
    OLL_41(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 2)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 2)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_42(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 2)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 2)
            ),
        )
    ),
    OLL_43(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 2)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            )
        )
    ),
    OLL_44(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 2)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 2)
            )
        )
    ),
    OLL_45(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 0)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_46(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 2)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 2)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 2)
            ),
        )
    ),
    OLL_47(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 2)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 2)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_48(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 2)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 2)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 2)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_49(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 2)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 2)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_50(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 2)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 2)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            ),
        )
    ),
    OLL_51(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 2)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 2)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_52(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 2)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 2)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 2)
            ),
        )
    ),
    OLL_53(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 2)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 2)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            ),
        )
    ),
    OLL_54(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 2)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 2)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_55(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 2)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 2)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 0)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(1, 2)
            ),
        )
    ),
    OLL_56(
        mutableListOf(
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(0, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(0, 2)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.OneRotation, Pair(2, 0)
            ),
            OLLElementOrientation(
                PieceType.CORNER, Orientation.TwoRotations, Pair(2, 2)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLL_57(
        mutableListOf(
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(0, 1)
            ),
            OLLElementOrientation(
                PieceType.EDGE, Orientation.Incorrect, Pair(2, 1)
            )
        )
    ),
    OLLSkip
}