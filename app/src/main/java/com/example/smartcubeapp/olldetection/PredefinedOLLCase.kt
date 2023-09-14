package com.example.smartcubeapp.olldetection

import com.example.smartcubeapp.cube.piece.Orientation
import com.example.smartcubeapp.cube.piece.PieceType
import com.example.smartcubeapp.cube.piece.PositionRepresentationElement

enum class PredefinedOLLCase(override val incorrectlyOrientedPieces: MutableList<PositionRepresentationElement> = mutableListOf()): OLLCase{
    //OLLs 1 to 57 to be populated with incorrectlyOrientedPieces later
    OLL_01(),
    OLL_02(),
    OLL_03(),
    OLL_04(),
    OLL_05(),
    OLL_06(),
    OLL_07(),
    OLL_08(),
    OLL_09(),
    OLL_10(),
    OLL_11(),
    OLL_12(),
    OLL_13(),
    OLL_14(),
    OLL_15(),
    OLL_16(),
    OLL_17(),
    OLL_18(),
    OLL_19(),
    OLL_20(),
    OLL_21(),
    OLL_22(),
    OLL_23(),
    OLL_24(),
    OLL_25(),
    OLL_26(),
    OLL_27(),
    OLL_28(),
    OLL_29(),
    OLL_30(),
    OLL_31(),
    OLL_32(),
    OLL_33(),
    OLL_34(),
    OLL_35(),
    OLL_36(),
    OLL_37(),
    OLL_38(),
    OLL_39(),
    OLL_40(),
    OLL_41(),
    OLL_42(),
    OLL_43(mutableListOf(
        PositionRepresentationElement(PieceType.CORNER, 1, Orientation.OneRotation, Pair(0, 0)),
        PositionRepresentationElement(PieceType.CORNER, 5, Orientation.TwoRotations, Pair(0, 2)),
        PositionRepresentationElement(PieceType.EDGE, 10, Orientation.Incorrect, Pair(0, 1)),
        PositionRepresentationElement(PieceType.EDGE, 2, Orientation.Incorrect, Pair(1, 0))
    )),
    OLL_44(mutableListOf(
        PositionRepresentationElement(PieceType.CORNER, 1, Orientation.OneRotation, Pair(0, 0)),
        PositionRepresentationElement(PieceType.CORNER, 5, Orientation.TwoRotations, Pair(0, 2)),
        PositionRepresentationElement(PieceType.EDGE, 10, Orientation.Incorrect, Pair(0, 1)),
        PositionRepresentationElement(PieceType.EDGE, 2, Orientation.Incorrect, Pair(1, 2))
    )),
    OLL_45(),
    OLL_46(),
    OLL_47(),
    OLL_48(),
    OLL_49(),
    OLL_50(),
    OLL_51(),
    OLL_52(),
    OLL_53(),
    OLL_54(),
    OLL_55(),
    OLL_56(),
    OLL_57();
}