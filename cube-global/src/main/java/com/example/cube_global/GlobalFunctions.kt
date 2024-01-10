package com.example.cube_global

import com.example.cube_cube.cube.CubeState
import com.example.cube_cube.cube.Move
import com.example.cube_cube.cube.Solve
import kotlin.math.roundToInt

fun roundDouble(value: Double, multiplier: Int): Double {
    return (value * multiplier).roundToInt() / multiplier.toDouble()
}
fun simpleTestSolve(): Solve {
    return Solve(
        scrambledState = CubeState(
            mutableListOf(2, 1, 7, 4, 3, 5, 0, 6),
            mutableListOf(1, 3, 3, 1, 3, 3, 1, 1),
            mutableListOf(3, 1, 2, 0, 8, 5, 7, 11, 4, 9, 10, 6),
            mutableListOf(
                true, false, false, false, false, false, false, false, true, false, false, false
            ),
            Move(),
            0
        ),
        solveStateSequence = mutableListOf(
            //scrambled
            CubeState(
                mutableListOf(2, 1, 7, 4, 3, 5, 0, 6),
                mutableListOf(1, 3, 3, 1, 3, 3, 1, 1),
                mutableListOf(3, 1, 2, 0, 8, 5, 7, 11, 4, 9, 10, 6),
                mutableListOf(
                    true, false, false, false, false, false, false, false, true, false, false, false
                ),
                Move(),
                0
            ),
            //cross solved
            CubeState(
                mutableListOf(2, 1, 0, 7, 3, 5, 6, 4),
                mutableListOf(1, 3, 3, 1, 3, 3, 3, 3),
                mutableListOf(3, 1, 2, 7, 8, 5, 6, 0, 4, 9, 10, 11),
                mutableListOf(
                    true, false, false, false, false, false, false, false, true, false, false, false
                ),
                Move(),
                100
            ),
            //start of F2L
            CubeState(
                mutableListOf(2, 1, 6, 0, 3, 5, 4, 7),
                mutableListOf(1, 3, 1, 1, 3, 3, 1, 3),
                mutableListOf(3, 1, 2, 6, 8, 5, 11, 7, 4, 9, 10, 0),
                mutableListOf(
                    true, false, false, false, false, false, false, false, true, false, false, false
                ),
                Move(),
                200
            ),
            CubeState(
                mutableListOf(3, 1, 6, 2, 7, 5, 4, 0),
                mutableListOf(2, 3, 1, 1, 2, 3, 1, 1),
                mutableListOf(8, 1, 2, 6, 4, 5, 11, 3, 7, 9, 10, 0),
                mutableListOf(
                    true, false, false, false, false, false, false, false, true, false, false, false
                ),
                Move(),
                300
            ),
            //F2L solved
            CubeState(
                mutableListOf(3, 1, 2, 0, 7, 5, 6, 4),
                mutableListOf(2, 3, 3, 3, 2, 3, 3, 3),
                mutableListOf(8, 1, 2, 3, 4, 5, 6, 0, 7, 9, 10, 11),
                mutableListOf(
                    true, false, false, false, false, false, false, false, true, false, false, false
                ),
                Move(),
                400
            ),
            //start of OLL
            CubeState(
                mutableListOf(1, 2, 0, 3, 7, 5, 6, 4),
                mutableListOf(3, 3, 3, 1, 2, 3, 3, 3),
                mutableListOf(1, 2, 3, 8, 4, 5, 6, 0, 7, 9, 10, 11),
                mutableListOf(
                    false, false, false, true, false, false, false, false, true, false, false, false
                ),
                Move(),
                500
            ),
            CubeState(
                mutableListOf(3, 2, 0, 4, 1, 5, 6, 7),
                mutableListOf(1, 3, 3, 2, 2, 3, 3, 3),
                mutableListOf(0, 2, 3, 8, 1, 5, 6, 7, 4, 9, 10, 11),
                mutableListOf(
                    true, false, false, true, true, false, false, false, true, false, false, false
                ),
                Move(),
                600
            ),
            CubeState(
                mutableListOf(3, 2, 6, 0, 1, 5, 7, 4),
                mutableListOf(1, 3, 1, 1, 2, 3, 1, 2),
                mutableListOf(0, 2, 3, 6, 1,  5, 11, 8, 4, 9, 10, 7),
                mutableListOf(
                    true, false, false, false, true, false, false, true, true, false, false, false
                ),
                Move(),
                700
            ),
            CubeState(
                mutableListOf(1, 2, 6, 3, 4, 5, 7, 0),
                mutableListOf(3, 3, 1, 1, 3, 3, 1, 1),
                mutableListOf(1, 2, 3, 6, 4, 5, 11, 0, 8, 9, 10, 7),
                mutableListOf(
                    false, false, false, false, false, false, false, false, false, false, false, false
                ),
                Move(),
                800
            ),
            CubeState(
                mutableListOf(1, 2, 3, 0, 4, 5, 6, 7),
                mutableListOf(3, 3, 3, 3, 3, 3, 3, 3),
                mutableListOf(1, 2, 3, 0, 4, 5, 6, 7, 8, 9, 10, 11),
                mutableListOf(
                    false, false, false, false, false, false, false, false, false, false, false, false
                ),
                Move(),
                900
            ),
            //OLL solved, cube solved
            CubeState(
                CubeState.SOLVED_CUBE_STATE.cornerPositions,
                CubeState.SOLVED_CUBE_STATE.cornerOrientations,
                CubeState.SOLVED_CUBE_STATE.edgePositions,
                CubeState.SOLVED_CUBE_STATE.edgeOrientations,
                Move(),
                1000
            ),
        ),
    )
}
fun simpleSolveStateSequence(): MutableList<CubeState> {
    return mutableListOf(
        CubeState(
            cornerPositions = mutableListOf(2, 3, 6, 7, 4, 1, 5, 0),
            cornerOrientations = mutableListOf(1, 1, 3, 1, 1, 2, 1, 1),
            edgePositions = mutableListOf(0, 3, 2, 8, 11, 4, 9, 6, 7, 1, 5, 10),
            edgeOrientations = mutableListOf(true, false, false, true, true, true, true, true, false, true, true, false),
            lastMove = Move("L", 1, "L"),
            timestamp = 1696663618649
        ),
        CubeState(
            cornerPositions = mutableListOf(4, 3, 6, 2, 0, 1, 5, 7),
            cornerOrientations = mutableListOf(1, 1, 3, 1, 1, 2, 1, 1),
            edgePositions = mutableListOf(11, 3, 2, 8, 7, 4, 9, 0, 6, 1, 5, 10),
            edgeOrientations = mutableListOf(false, false, false, true, true, true, true, false, false, true, true, false),
            lastMove = Move("U", -1, "U'"),
            timestamp = 1696663618847
        ),
        CubeState(
            cornerPositions = mutableListOf(4, 3, 6, 2, 1, 5, 7, 0),
            cornerOrientations = mutableListOf(1, 1, 3, 1, 1, 2, 2, 2),
            edgePositions = mutableListOf(11, 3, 2, 8, 7, 4, 9, 0, 1, 5, 10, 6),
            edgeOrientations = mutableListOf(false, false, false, true, true, true, true, false, true, true, false, false),
            lastMove = Move("F", -1, "F'"),
            timestamp = 1696663619345
        ),
        CubeState(
            cornerPositions = mutableListOf(4, 3, 7, 6, 1, 5, 0, 2),
            cornerOrientations = mutableListOf(1, 1, 2, 1, 1, 2, 2, 3),
            edgePositions = mutableListOf(11, 3, 2, 9, 7, 4, 6, 8, 1, 5, 10, 0),
            edgeOrientations = mutableListOf(false, false, false, true, true, true, false, true, true, true, false, false),
            lastMove = Move("L", 1, "L"),
            timestamp = 1696663619545
        ),
        CubeState(
            cornerPositions = mutableListOf(4, 7, 0, 6, 1, 3, 5, 2),
            cornerOrientations = mutableListOf(1, 3, 3, 1, 1, 1, 3, 3),
            edgePositions = mutableListOf(11, 3, 6, 9, 7, 2, 10, 8, 1, 5, 4, 0),
            edgeOrientations = mutableListOf(false, false, true, true, true, true, true, true, true, true, false, false),
            lastMove = Move("D", -1, "D'"),
            timestamp = 1696663620250
        ),
        CubeState(
            cornerPositions = mutableListOf(4, 7, 0, 6, 2, 1, 3, 5),
            cornerOrientations = mutableListOf(1, 3, 3, 1, 3, 2, 2, 3),
            edgePositions = mutableListOf(11, 3, 6, 9, 7, 2, 10, 8, 0, 1, 5, 4),
            edgeOrientations = mutableListOf(false, false, true, true, true, true, true, true, false, true, true, false),
            lastMove = Move("F", 1, "F"),
            timestamp = 1696663620645
        ),
        CubeState(
            cornerPositions = mutableListOf(2, 4, 0, 6, 1, 7, 3, 5),
            cornerOrientations = mutableListOf(1, 3, 3, 1, 2, 1, 2, 3),
            edgePositions = mutableListOf(11, 7, 6, 9, 1, 3, 10, 8, 0, 2, 5, 4),
            edgeOrientations = mutableListOf(false, true, true, true, true, false, true, true, false, true, true, false),
            lastMove = Move("R", 1, "R"),
            timestamp = 1696663621095
        ),
        CubeState(
            cornerPositions = mutableListOf(6, 4, 0, 5, 2, 7, 3, 1),
            cornerOrientations = mutableListOf(1, 3, 3, 2, 1, 1, 2, 3),
            edgePositions = mutableListOf(8, 7, 6, 9, 11, 3, 10, 0, 1, 2, 5, 4),
            edgeOrientations = mutableListOf(false, true, true, true, true, false, true, true, false, true, true, false),
            lastMove = Move("U", 1, "U"),
            timestamp = 1696663621197
        ),
        CubeState(
            cornerPositions = mutableListOf(4, 7, 0, 5, 6, 2, 3, 1),
            cornerOrientations = mutableListOf(1, 3, 3, 2, 3, 3, 2, 3),
            edgePositions = mutableListOf(8, 3, 6, 9, 7, 2, 10, 0, 1, 11, 5, 4),
            edgeOrientations = mutableListOf(false, false, true, true, true, true, true, true, false, true, true, false),
            lastMove = Move("R", -1, "R'"),
            timestamp = 1696663621296
        ),
        CubeState(
            cornerPositions = mutableListOf(4, 2, 7, 5, 6, 3, 0, 1),
            cornerOrientations = mutableListOf(1, 2, 2, 2, 3, 3, 2, 3),
            edgePositions = mutableListOf(8, 3, 2, 9, 7, 5, 6, 0, 1, 11, 10, 4),
            edgeOrientations = mutableListOf(false, false, false, true, true, false, false, true, false, true, false, false),
            lastMove = Move("D", 1, "D"),
            timestamp = 1696663621445
        ),
        CubeState(
            cornerPositions = mutableListOf(6, 2, 7, 4, 1, 3, 0, 5),
            cornerOrientations = mutableListOf(2, 2, 2, 1, 2, 3, 2, 3),
            edgePositions = mutableListOf(7, 3, 2, 9, 1, 5, 6, 8, 0, 11, 10, 4),
            edgeOrientations = mutableListOf(false, false, false, true, true, false, false, true, false, true, false, false),
            lastMove = Move("U", -1, "U'"),
            timestamp = 1696663622199
        ),
        CubeState(
            cornerPositions = mutableListOf(2, 7, 4, 6, 1, 3, 0, 5),
            cornerOrientations = mutableListOf(1, 1, 2, 1, 2, 3, 2, 3),
            edgePositions = mutableListOf(3, 2, 9, 7, 1, 5, 6, 8, 0, 11, 10, 4),
            edgeOrientations = mutableListOf(false, false, true, false, true, false, false, true, false, true, false, false),
            lastMove = Move("B", 1, "B"),
            timestamp = 1696663622646
        ),
        CubeState(
            cornerPositions = mutableListOf(1, 7, 4, 2, 5, 3, 0, 6),
            cornerOrientations = mutableListOf(3, 1, 2, 1, 2, 3, 2, 1),
            edgePositions = mutableListOf(1, 2, 9, 7, 0, 5, 6, 3, 8, 11, 10, 4),
            edgeOrientations = mutableListOf(false, false, true, false, true, false, false, true, false, true, false, false),
            lastMove = Move("U", -1, "U'"),
            timestamp = 1696663622749
        ),
        CubeState(
            cornerPositions = mutableListOf(2, 1, 7, 4, 5, 3, 0, 6),
            cornerOrientations = mutableListOf(2, 3, 2, 1, 2, 3, 2, 1),
            edgePositions = mutableListOf(7, 1, 2, 9, 0, 5, 6, 3, 8, 11, 10, 4),
            edgeOrientations = mutableListOf(false, false, false, true, true, false, false, true, false, true, false, false),
            lastMove = Move("B", -1, "B'"),
            timestamp = 1696663622847
        ),
        CubeState(
            cornerPositions = mutableListOf(2, 1, 7, 4, 3, 0, 6, 5),
            cornerOrientations = mutableListOf(2, 3, 2, 1, 3, 1, 2, 1),
            edgePositions = mutableListOf(7, 1, 2, 9, 0, 5, 6, 3, 11, 10, 4, 8),
            edgeOrientations = mutableListOf(false, false, false, true, true, false, false, true, true, false, false, false),
            lastMove = Move("F", -1, "F'"),
            timestamp = 1696663624452
        ),
        CubeState(
            cornerPositions = mutableListOf(4, 1, 7, 5, 2, 0, 6, 3),
            cornerOrientations = mutableListOf(1, 3, 2, 1, 3, 1, 2, 2),
            edgePositions = mutableListOf(3, 1, 2, 9, 7, 5, 6, 11, 0, 10, 4, 8),
            edgeOrientations = mutableListOf(false, false, false, true, true, false, false, false, false, false, false, false),
            lastMove = Move("U", 1, "U"),
            timestamp = 1696663624549
        ),
        CubeState(
            cornerPositions = mutableListOf(4, 1, 7, 5, 3, 2, 0, 6),
            cornerOrientations = mutableListOf(1, 3, 2, 1, 1, 3, 2, 1),
            edgePositions = mutableListOf(3, 1, 2, 9, 7, 5, 6, 11, 8, 0, 10, 4),
            edgeOrientations = mutableListOf(false, false, false, true, true, false, false, false, false, false, false, false),
            lastMove = Move("F", 1, "F"),
            timestamp = 1696663624647
        ),
        CubeState(
            cornerPositions = mutableListOf(3, 1, 7, 4, 6, 2, 0, 5),
            cornerOrientations = mutableListOf(1, 3, 2, 1, 1, 3, 2, 1),
            edgePositions = mutableListOf(7, 1, 2, 9, 8, 5, 6, 3, 11, 0, 10, 4),
            edgeOrientations = mutableListOf(false, false, false, true, true, false, false, true, true, false, false, false),
            lastMove = Move("U", -1, "U'"),
            timestamp = 1696663625245
        ),
        CubeState(
            cornerPositions = mutableListOf(3, 1, 4, 5, 6, 2, 7, 0),
            cornerOrientations = mutableListOf(1, 3, 3, 3, 1, 3, 2, 2),
            edgePositions = mutableListOf(7, 1, 2, 3, 8, 5, 9, 4, 11, 0, 10, 6),
            edgeOrientations = mutableListOf(false, false, false, true, true, false, true, false, true, false, false, false),
            lastMove = Move("L", -1, "L'"),
            timestamp = 1696663625496
        ),
        CubeState(
            cornerPositions = mutableListOf(5, 1, 4, 0, 3, 2, 7, 6),
            cornerOrientations = mutableListOf(2, 3, 3, 3, 1, 3, 2, 1),
            edgePositions = mutableListOf(4, 1, 2, 3, 7, 5, 9, 11, 8, 0, 10, 6),
            edgeOrientations = mutableListOf(true, false, false, true, true, false, true, false, false, false, false, false),
            lastMove = Move("U", 1, "U"),
            timestamp = 1696663625595
        ),
        CubeState(
            cornerPositions = mutableListOf(5, 1, 7, 4, 3, 2, 6, 0),
            cornerOrientations = mutableListOf(2, 3, 2, 1, 1, 3, 3, 1),
            edgePositions = mutableListOf(4, 1, 2, 9, 7, 5, 6, 3, 8, 0, 10, 11),
            edgeOrientations = mutableListOf(true, false, false, true, true, false, false, true, false, false, false, false),
            lastMove = Move("L", 1, "L"),
            timestamp = 1696663625746
        ),
        CubeState(
            cornerPositions = mutableListOf(4, 1, 7, 0, 5, 2, 6, 3),
            cornerOrientations = mutableListOf(1, 3, 2, 1, 3, 3, 3, 1),
            edgePositions = mutableListOf(3, 1, 2, 9, 4, 5, 6, 8, 7, 0, 10, 11),
            edgeOrientations = mutableListOf(false, false, false, true, false, false, false, true, false, false, false, false),
            lastMove = Move("U", 1, "U"),
            timestamp = 1696663626346
        ),
        CubeState(
            cornerPositions = mutableListOf(0, 4, 1, 7, 5, 2, 6, 3),
            cornerOrientations = mutableListOf(2, 2, 3, 1, 3, 3, 3, 1),
            edgePositions = mutableListOf(9, 3, 1, 2, 4, 5, 6, 8, 7, 0, 10, 11),
            edgeOrientations = mutableListOf(true, false, false, false, false, false, false, true, false, false, false, false),
            lastMove = Move("B", -1, "B'"),
            timestamp = 1696663626446
        ),
        CubeState(
            cornerPositions = mutableListOf(5, 4, 1, 0, 3, 2, 6, 7),
            cornerOrientations = mutableListOf(2, 2, 3, 3, 1, 3, 3, 1),
            edgePositions = mutableListOf(4, 3, 1, 2, 7, 5, 6, 9, 8, 0, 10, 11),
            edgeOrientations = mutableListOf(true, false, false, false, true, false, false, false, false, false, false, false),
            lastMove = Move("U", -1, "U'"),
            timestamp = 1696663626596
        ),
        CubeState(
            cornerPositions = mutableListOf(4, 1, 0, 5, 3, 2, 6, 7),
            cornerOrientations = mutableListOf(1, 3, 3, 1, 1, 3, 3, 1),
            edgePositions = mutableListOf(3, 1, 2, 4, 7, 5, 6, 9, 8, 0, 10, 11),
            edgeOrientations = mutableListOf(false, false, false, true, true, false, false, false, false, false, false, false),
            lastMove = Move("B", 1, "B"),
            timestamp = 1696663626696
        ),
        CubeState(
            cornerPositions = mutableListOf(3, 1, 0, 4, 7, 2, 6, 5),
            cornerOrientations = mutableListOf(1, 3, 3, 1, 1, 3, 3, 1),
            edgePositions = mutableListOf(7, 1, 2, 4, 8, 5, 6, 3, 9, 0, 10, 11),
            edgeOrientations = mutableListOf(false, false, false, true, true, false, false, true, true, false, false, false),
            lastMove = Move("U", -1, "U'"),
            timestamp = 1696663626996
        ),
        CubeState(
            cornerPositions = mutableListOf(7, 3, 0, 4, 2, 1, 6, 5),
            cornerOrientations = mutableListOf(3, 3, 3, 1, 1, 1, 3, 1),
            edgePositions = mutableListOf(7, 8, 2, 4, 0, 1, 6, 3, 9, 5, 10, 11),
            edgeOrientations = mutableListOf(false, true, false, true, false, false, false, true, true, false, false, false),
            lastMove = Move("R", 1, "R"),
            timestamp = 1696663627395
        ),
        CubeState(
            cornerPositions = mutableListOf(2, 3, 0, 7, 5, 1, 6, 4),
            cornerOrientations = mutableListOf(1, 3, 3, 2, 1, 1, 3, 1),
            edgePositions = mutableListOf(0, 8, 2, 4, 9, 1, 6, 7, 3, 5, 10, 11),
            edgeOrientations = mutableListOf(true, true, false, true, false, false, false, true, false, false, false, false),
            lastMove = Move("U", -1, "U'"),
            timestamp = 1696663627445
        ),
        CubeState(
            cornerPositions = mutableListOf(3, 1, 0, 7, 2, 5, 6, 4),
            cornerOrientations = mutableListOf(1, 3, 3, 2, 3, 3, 3, 1),
            edgePositions = mutableListOf(0, 1, 2, 4, 8, 5, 6, 7, 3, 9, 10, 11),
            edgeOrientations = mutableListOf(true, false, false, true, true, false, false, true, false, false, false, false),
            lastMove = Move("R", -1, "R'"),
            timestamp = 1696663627594
        ),
        CubeState(
            cornerPositions = mutableListOf(7, 1, 0, 4, 3, 5, 6, 2),
            cornerOrientations = mutableListOf(3, 3, 3, 1, 1, 3, 3, 2),
            edgePositions = mutableListOf(7, 1, 2, 4, 0, 5, 6, 3, 8, 9, 10, 11),
            edgeOrientations = mutableListOf(false, false, false, true, false, false, false, true, false, false, false, false),
            lastMove = Move("U", 1, "U"),
            timestamp = 1696663628400
        ),
        CubeState(
            cornerPositions = mutableListOf(4, 7, 1, 0, 3, 5, 6, 2),
            cornerOrientations = mutableListOf(2, 3, 3, 3, 1, 3, 3, 2),
            edgePositions = mutableListOf(4, 7, 1, 2, 0, 5, 6, 3, 8, 9, 10, 11),
            edgeOrientations = mutableListOf(true, false, false, false, false, false, false, true, false, false, false, false),
            lastMove = Move("B", -1, "B'"),
            timestamp = 1696663628745
        ),
        CubeState(
            cornerPositions = mutableListOf(0, 7, 1, 2, 4, 5, 6, 3),
            cornerOrientations = mutableListOf(2, 3, 3, 3, 3, 3, 3, 1),
            edgePositions = mutableListOf(3, 7, 1, 2, 4, 5, 6, 8, 0, 9, 10, 11),
            edgeOrientations = mutableListOf(false, false, false, false, false, false, false, true, true, false, false, false),
            lastMove = Move("U", 1, "U"),
            timestamp = 1696663628847
        ),
        CubeState(
            cornerPositions = mutableListOf(7, 1, 2, 0, 4, 5, 6, 3),
            cornerOrientations = mutableListOf(3, 3, 3, 1, 3, 3, 3, 1),
            edgePositions = mutableListOf(7, 1, 2, 3, 4, 5, 6, 8, 0, 9, 10, 11),
            edgeOrientations = mutableListOf(false, false, false, false, false, false, false, true, true, false, false, false),
            lastMove = Move("B", 1, "B"),
            timestamp = 1696663628996
        ),
        CubeState(
            cornerPositions = mutableListOf(0, 1, 2, 3, 7, 5, 6, 4),
            cornerOrientations = mutableListOf(1, 3, 3, 1, 2, 3, 3, 2),
            edgePositions = mutableListOf(8, 1, 2, 3, 7, 5, 6, 0, 4, 9, 10, 11),
            edgeOrientations = mutableListOf(false, false, false, false, true, false, false, false, true, false, false, false),
            lastMove = Move("U", 1, "U"),
            timestamp = 1696663629446
        ),
        CubeState(
            cornerPositions = mutableListOf(0, 1, 2, 3, 4, 7, 5, 6),
            cornerOrientations = mutableListOf(1, 3, 3, 1, 1, 1, 3, 3),
            edgePositions = mutableListOf(8, 1, 2, 3, 7, 5, 6, 0, 11, 4, 9, 10),
            edgeOrientations = mutableListOf(false, false, false, false, true, false, false, false, false, true, false, false),
            lastMove = Move("F", 1, "F"),
            timestamp = 1696663629796
        ),
        CubeState(
            cornerPositions = mutableListOf(3, 1, 2, 6, 0, 7, 5, 4),
            cornerOrientations = mutableListOf(1, 3, 3, 2, 1, 1, 3, 1),
            edgePositions = mutableListOf(0, 1, 2, 3, 8, 5, 6, 11, 7, 4, 9, 10),
            edgeOrientations = mutableListOf(true, false, false, false, true, false, false, true, false, true, false, false),
            lastMove = Move("U", 1, "U"),
            timestamp = 1696663629895
        ),
        CubeState(
            cornerPositions = mutableListOf(3, 1, 2, 6, 7, 5, 4, 0),
            cornerOrientations = mutableListOf(1, 3, 3, 2, 2, 3, 2, 2),
            edgePositions = mutableListOf(0, 1, 2, 3, 8, 5, 6, 11, 4, 9, 10, 7),
            edgeOrientations = mutableListOf(true, false, false, false, true, false, false, true, true, false, false, false),
            lastMove = Move("F", -1, "F'"),
            timestamp = 1696663629945
        ),
        CubeState(
            cornerPositions = mutableListOf(7, 1, 2, 3, 0, 5, 4, 6),
            cornerOrientations = mutableListOf(3, 3, 3, 1, 3, 3, 2, 3),
            edgePositions = mutableListOf(8, 1, 2, 3, 4, 5, 6, 0, 11, 9, 10, 7),
            edgeOrientations = mutableListOf(false, false, false, false, false, false, false, false, false, false, false, false),
            lastMove = Move("U", -1, "U'"),
            timestamp = 1696663630046
        ),
        CubeState(
            cornerPositions = mutableListOf(7, 1, 2, 3, 5, 4, 6, 0),
            cornerOrientations = mutableListOf(3, 3, 3, 1, 3, 1, 3, 3),
            edgePositions = mutableListOf(8, 1, 2, 3, 4, 5, 6, 0, 9, 10, 7, 11),
            edgeOrientations = mutableListOf(false, false, false, false, false, false, false, false, false, false, false, false),
            lastMove = Move("F", -1, "F'"),
            timestamp = 1696663630296
        ),
        CubeState(
            cornerPositions = mutableListOf(7, 1, 6, 2, 5, 4, 0, 3),
            cornerOrientations = mutableListOf(3, 3, 1, 1, 3, 1, 1, 3),
            edgePositions = mutableListOf(8, 1, 2, 6, 4, 5, 11, 3, 9, 10, 7, 0),
            edgeOrientations = mutableListOf(false, false, false, false, false, false, false, false, false, false, false, false),
            lastMove = Move("L", 1, "L"),
            timestamp = 1696663630345
        ),
        CubeState(
            cornerPositions = mutableListOf(7, 1, 6, 2, 3, 5, 4, 0),
            cornerOrientations = mutableListOf(3, 3, 1, 1, 3, 3, 2, 2),
            edgePositions = mutableListOf(8, 1, 2, 6, 4, 5, 11, 3, 0, 9, 10, 7),
            edgeOrientations = mutableListOf(false, false, false, false, false, false, false, false, false, false, false, false),
            lastMove = Move("F", 1, "F"),
            timestamp = 1696663630495
        ),
        CubeState(
            cornerPositions = mutableListOf(7, 1, 2, 0, 3, 5, 6, 4),
            cornerOrientations = mutableListOf(3, 3, 3, 2, 3, 3, 3, 2),
            edgePositions = mutableListOf(8, 1, 2, 3, 4, 5, 6, 7, 0, 9, 10, 11),
            edgeOrientations = mutableListOf(false, false, false, false, false, false, false, false, false, false, false, false),
            lastMove = Move("L", -1, "L'"),
            timestamp = 1696663631249
        ),
        CubeState(
            cornerPositions = mutableListOf(0, 1, 2, 4, 7, 5, 6, 3),
            cornerOrientations = mutableListOf(3, 3, 3, 3, 2, 3, 3, 2),
            edgePositions = mutableListOf(7, 1, 2, 3, 8, 5, 6, 0, 4, 9, 10, 11),
            edgeOrientations = mutableListOf(true, false, false, false, true, false, false, true, true, false, false, false),
            lastMove = Move("U", 1, "U"),
            timestamp = 1696663631251
        ),
        CubeState(
            cornerPositions = mutableListOf(4, 1, 2, 3, 0, 5, 6, 7),
            cornerOrientations = mutableListOf(2, 3, 3, 3, 2, 3, 3, 3),
            edgePositions = mutableListOf(0, 1, 2, 3, 7, 5, 6, 4, 8, 9, 10, 11),
            edgeOrientations = mutableListOf(false, false, false, false, false, false, false, false, false, false, false, false),
            lastMove = Move("U", 1, "U"),
            timestamp = 1696663631546
        ),
        CubeState(
            cornerPositions = mutableListOf(3, 1, 2, 7, 4, 5, 6, 0),
            cornerOrientations = mutableListOf(2, 3, 3, 2, 3, 3, 3, 3),
            edgePositions = mutableListOf(4, 1, 2, 3, 0, 5, 6, 8, 7, 9, 10, 11),
            edgeOrientations = mutableListOf(true, false, false, false, true, false, false, true, true, false, false, false),
            lastMove = Move("U", 1, "U"),
            timestamp = 1696663632249
        ),
        CubeState(
            cornerPositions = mutableListOf(3, 1, 2, 7, 0, 4, 5, 6),
            cornerOrientations = mutableListOf(2, 3, 3, 2, 3, 3, 3, 3),
            edgePositions = mutableListOf(4, 1, 2, 3, 0, 5, 6, 8, 11, 7, 9, 10),
            edgeOrientations = mutableListOf(true, false, false, false, true, false, false, true, false, true, false, false),
            lastMove = Move("F", 1, "F"),
            timestamp = 1696663632546
        ),
        CubeState(
            cornerPositions = mutableListOf(7, 1, 2, 6, 3, 4, 5, 0),
            cornerOrientations = mutableListOf(3, 3, 3, 2, 3, 3, 3, 2),
            edgePositions = mutableListOf(8, 1, 2, 3, 4, 5, 6, 11, 0, 7, 9, 10),
            edgeOrientations = mutableListOf(false, false, false, false, false, false, false, true, false, true, false, false),
            lastMove = Move("U", 1, "U"),
            timestamp = 1696663632646
        ),
        CubeState(
            cornerPositions = mutableListOf(7, 1, 2, 6, 4, 5, 0, 3),
            cornerOrientations = mutableListOf(3, 3, 3, 2, 3, 3, 1, 3),
            edgePositions = mutableListOf(8, 1, 2, 3, 4, 5, 6, 11, 7, 9, 10, 0),
            edgeOrientations = mutableListOf(false, false, false, false, false, false, false, true, true, false, false, false),
            lastMove = Move("F", -1, "F'"),
            timestamp = 1696663632646
        ),
        CubeState(
            cornerPositions = mutableListOf(4, 1, 2, 7, 3, 5, 0, 6),
            cornerOrientations = mutableListOf(2, 3, 3, 2, 2, 3, 1, 3),
            edgePositions = mutableListOf(4, 1, 2, 3, 7, 5, 6, 8, 11, 9, 10, 0),
            edgeOrientations = mutableListOf(true, false, false, false, false, false, false, true, false, false, false, false),
            lastMove = Move("U", -1, "U'"),
            timestamp = 1696663632746
        ),
        CubeState(
            cornerPositions = mutableListOf(4, 1, 2, 7, 5, 0, 6, 3),
            cornerOrientations = mutableListOf(2, 3, 3, 2, 3, 2, 3, 1),
            edgePositions = mutableListOf(4, 1, 2, 3, 7, 5, 6, 8, 9, 10, 0, 11),
            edgeOrientations = mutableListOf(true, false, false, false, false, false, false, true, false, false, false, false),
            lastMove = Move("F", -1, "F'"),
            timestamp = 1696663632946
        ),
        CubeState(
            cornerPositions = mutableListOf(4, 1, 6, 2, 5, 0, 3, 7),
            cornerOrientations = mutableListOf(2, 3, 1, 1, 3, 2, 3, 2),
            edgePositions = mutableListOf(4, 1, 2, 6, 7, 5, 11, 3, 9, 10, 0, 8),
            edgeOrientations = mutableListOf(true, false, false, false, false, false, false, false, false, false, false, true),
            lastMove = Move("L", 1, "L"),
            timestamp = 1696663633047
        ),
        CubeState(
            cornerPositions = mutableListOf(4, 1, 6, 2, 7, 5, 0, 3),
            cornerOrientations = mutableListOf(2, 3, 1, 1, 1, 3, 1, 3),
            edgePositions = mutableListOf(4, 1, 2, 6, 7, 5, 11, 3, 8, 9, 10, 0),
            edgeOrientations = mutableListOf(true, false, false, false, false, false, false, false, true, false, false, false),
            lastMove = Move("F", 1, "F"),
            timestamp = 1696663633098
        ),
        CubeState(
            cornerPositions = mutableListOf(4, 1, 6, 2, 3, 7, 5, 0),
            cornerOrientations = mutableListOf(2, 3, 1, 1, 3, 2, 3, 2),
            edgePositions = mutableListOf(4, 1, 2, 6, 7, 5, 11, 3, 0, 8, 9, 10),
            edgeOrientations = mutableListOf(true, false, false, false, false, false, false, false, false, true, false, false),
            lastMove = Move("F", 1, "F"),
            timestamp = 1696663633148
        ),
        CubeState(
            cornerPositions = mutableListOf(3, 1, 6, 4, 0, 7, 5, 2),
            cornerOrientations = mutableListOf(2, 3, 1, 3, 3, 2, 3, 1),
            edgePositions = mutableListOf(7, 1, 2, 6, 0, 5, 11, 4, 3, 8, 9, 10),
            edgeOrientations = mutableListOf(true, false, false, false, true, false, false, false, true, true, false, false),
            lastMove = Move("U", -1, "U'"),
            timestamp = 1696663633197
        ),
        CubeState(
            cornerPositions = mutableListOf(3, 1, 6, 4, 7, 5, 2, 0),
            cornerOrientations = mutableListOf(2, 3, 1, 3, 1, 3, 2, 3),
            edgePositions = mutableListOf(7, 1, 2, 6, 0, 5, 11, 4, 8, 9, 10, 3),
            edgeOrientations = mutableListOf(true, false, false, false, true, false, false, false, true, false, false, true),
            lastMove = Move("F", -1, "F'"),
            timestamp = 1696663633345
        ),
        CubeState(
            cornerPositions = mutableListOf(7, 1, 6, 3, 0, 5, 2, 4),
            cornerOrientations = mutableListOf(1, 3, 1, 3, 2, 3, 2, 2),
            edgePositions = mutableListOf(0, 1, 2, 6, 8, 5, 11, 7, 4, 9, 10, 3),
            edgeOrientations = mutableListOf(false, false, false, false, false, false, false, false, true, false, false, true),
            lastMove = Move("U", -1, "U'"),
            timestamp = 1696663633546
        ),
        CubeState(
            cornerPositions = mutableListOf(7, 1, 6, 3, 4, 0, 5, 2),
            cornerOrientations = mutableListOf(1, 3, 1, 3, 1, 1, 3, 1),
            edgePositions = mutableListOf(0, 1, 2, 6, 8, 5, 11, 7, 3, 4, 9, 10),
            edgeOrientations = mutableListOf(false, false, false, false, false, false, false, false, true, true, false, false),
            lastMove = Move("F", 1, "F"),
            timestamp = 1696663633746
        ),
        CubeState(
            cornerPositions = mutableListOf(3, 1, 6, 2, 7, 0, 5, 4),
            cornerOrientations = mutableListOf(2, 3, 1, 1, 1, 1, 3, 1),
            edgePositions = mutableListOf(7, 1, 2, 6, 0, 5, 11, 3, 8, 4, 9, 10),
            edgeOrientations = mutableListOf(true, false, false, false, true, false, false, false, true, true, false, false),
            lastMove = Move("U", 1, "U"),
            timestamp = 1696663633795
        ),
        CubeState(
            cornerPositions = mutableListOf(3, 1, 6, 2, 0, 5, 4, 7),
            cornerOrientations = mutableListOf(2, 3, 1, 1, 2, 3, 2, 2),
            edgePositions = mutableListOf(7, 1, 2, 6, 0, 5, 11, 3, 4, 9, 10, 8),
            edgeOrientations = mutableListOf(true, false, false, false, true, false, false, false, true, false, false, true),
            lastMove = Move("F", -1, "F'"),
            timestamp = 1696663633896
        ),
        CubeState(
            cornerPositions = mutableListOf(3, 1, 2, 7, 0, 5, 6, 4),
            cornerOrientations = mutableListOf(2, 3, 3, 2, 2, 3, 3, 2),
            edgePositions = mutableListOf(7, 1, 2, 3, 0, 5, 6, 8, 4, 9, 10, 11),
            edgeOrientations = mutableListOf(true, false, false, false, true, false, false, true, true, false, false, false),
            lastMove = Move("L", -1, "L'"),
            timestamp = 1696663633997
        ),
        CubeState(
            cornerPositions = mutableListOf(0, 1, 2, 3, 4, 5, 6, 7),
            cornerOrientations = mutableListOf(3, 3, 3, 3, 3, 3, 3, 3),
            edgePositions = mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11),
            edgeOrientations = mutableListOf(false, false, false, false, false, false, false, false, false, false, false, false),
            lastMove = Move("U", -1, "U'"),
            timestamp = 1696663634445
        ),
    )
}
fun millisToSeconds(millis: Double): Double {
    val time = millis / MILLIS_IN_SECOND.toDouble()
    return roundDouble(time, 100)
}

fun millisToHours(millis: Long): Double {
    val time = millis / MILLIS_IN_HOUR.toDouble()
    return roundDouble(time, 10)
}
