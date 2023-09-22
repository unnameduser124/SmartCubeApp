package com.example.smartcubeapp.casedetection.plldetection.pllcase

import com.example.smartcubeapp.casedetection.plldetection.PLLPositionRepresentationElement

enum class PredefinedPLLCase(override val lastLayerPieces: MutableList<PLLPositionRepresentationElement> = mutableListOf()): PLLCase{
    //PLL cases to be populated with hardcoded values
    Aa(),
    Ab(),
    E(),
    F(),
    Ga(),
    Gb(),
    Gc(),
    Gd(),
    H(),
    Ja(),
    Jb(),
    Na(),
    Nb(),
    Ra(),
    Rb(),
    T(),
    Ua(),
    Ub(),
    V(),
    Y(),
    Z()
}