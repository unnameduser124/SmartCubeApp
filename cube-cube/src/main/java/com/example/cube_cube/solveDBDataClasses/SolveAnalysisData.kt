package com.example.cube_cube.solveDBDataClasses

import com.example.cube_cube.cube.CubeState

data class SolveAnalysisData(
    val solveID: Long,
    val solveDuration: Long,
    val timestamp: Long,
    val solveStateSequence: List<CubeStateData>,
    val scrambleSequence: String,
    var crossData: CrossData,
    var f2lData: F2LData,
    var ollData: OLLData,
    var pllData: PLLData
) {
    constructor(
        solveData: SolveData,
        crossData: CrossData?,
        f2lData: F2LData?,
        ollData: OLLData?,
        pllData: PLLData?,
        solveStateSequence: List<CubeState>
    ) : this(
        solveID = solveData.id,
        solveDuration = solveData.solveDuration,
        timestamp = solveData.timestamp,
        solveStateSequence = solveStateSequence.mapIndexed { index, cubeState ->
            CubeStateData(
                cubeState,
                index
            )
        },
        scrambleSequence = solveData.scramble,
        crossData = crossData ?: CrossData(),
        f2lData = f2lData ?: F2LData(),
        ollData = ollData ?: OLLData(),
        pllData = pllData ?: PLLData()
    )
}