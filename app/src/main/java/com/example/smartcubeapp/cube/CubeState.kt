package com.example.smartcubeapp.cube

import java.util.Calendar

data class CubeState(
    val cornerPositions: MutableList<Int>,
    val cornerOrientations: MutableList<Int>,
    val edgePositions: MutableList<Int>,
    val edgeOrientations: MutableList<Boolean>,
    var lastMove: Move = Move(),
    var timestamp: Long = Calendar.getInstance().timeInMillis
) {
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

    }
}
