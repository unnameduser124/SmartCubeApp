package com.example.smartcubeapp.cube

import com.example.smartcubeapp.solvedatabase.dataclasses.CubeStateData
import java.util.Calendar

data class CubeState(
    val cornerPositions: MutableList<Int>,
    val cornerOrientations: MutableList<Int>,
    val edgePositions: MutableList<Int>,
    val edgeOrientations: MutableList<Boolean>,
    var lastMove: Move = Move(),
    var timestamp: Long = Calendar.getInstance().timeInMillis,
    var id: Long = -1,
    var solveID: Long = -1
) {

    constructor(state: CubeStateData): this(
        cornerPositions = intListFromString(state.cornerPositions),
        cornerOrientations = intListFromString(state.cornerOrientations),
        edgePositions = intListFromString(state.edgePositions),
        edgeOrientations = booleanListFromString(state.edgeOrientations),
        timestamp = state.timestamp,
        lastMove = Move(state.lastMove),
        id = state.id,
        solveID = state.solveID
    )

    fun setCornerPositions(newCornerPositions: MutableList<Int>) {
        cornerPositions.clear()
        cornerPositions.addAll(newCornerPositions)
    }

    fun setEdgePositions(newEdgePositions: MutableList<Int>) {
        edgePositions.clear()
        edgePositions.addAll(newEdgePositions)
    }

    fun getCorrectlySolvedPieces(): Pair<MutableList<Int>, MutableList<Int>> {
        val correctlySolvedCorners = mutableListOf<Int>()
        for (i in 0..7) {
            if (cornerPositions[i] == i && cornerOrientations[i] == 3) {
                correctlySolvedCorners.add(i)
            }
        }

        val correctlySolvedEdges = mutableListOf<Int>()
        for (i in 0..11) {
            if (edgePositions[i] == i && !edgeOrientations[i]) {
                correctlySolvedEdges.add(i)
            }
        }
        return Pair(correctlySolvedCorners, correctlySolvedEdges)
    }

    override fun equals(other: Any?): Boolean {
        if (other is CubeState) {
            if (this.cornerPositions == other.cornerPositions &&
                this.cornerOrientations == other.cornerOrientations &&
                this.edgePositions == other.edgePositions &&
                this.edgeOrientations == other.edgeOrientations
            ) {
                return true
            }
        }
        return false
    }

    fun isSolved(): Boolean {
        return this == SOLVED_CUBE_STATE
    }

    override fun hashCode(): Int {
        var result = cornerPositions.hashCode()
        result = 31 * result + cornerOrientations.hashCode()
        result = 31 * result + edgePositions.hashCode()
        result = 31 * result + edgeOrientations.hashCode()
        result = 31 * result + lastMove.hashCode()
        result = 31 * result + timestamp.hashCode()
        return result
    }



    companion object {

        val SOLVED_CUBE_STATE = CubeState(
            cornerPositions = mutableListOf(0, 1, 2, 3, 4, 5, 6, 7),
            cornerOrientations = mutableListOf(3, 3, 3, 3, 3, 3, 3, 3),
            edgePositions = mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11),
            edgeOrientations = mutableListOf(
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false
            ),
            lastMove = Move("F", 1, "F")
        )

        fun intListFromString(string: String): MutableList<Int> {
            val list = mutableListOf<Int>()
            val split = string.split(",")
            for (i in split) {
                list.add(i.toInt())
            }
            return list
        }

        fun booleanListFromString(string: String): MutableList<Boolean> {
            val list = mutableListOf<Boolean>()
            val split = string.split(",")
            for (value in split) {
                list.add(value=="1")
            }
            return list
        }
    }
}
