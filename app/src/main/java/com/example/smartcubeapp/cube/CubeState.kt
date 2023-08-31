package com.example.smartcubeapp.cube

data class CubeState(
    val cornerPositions: MutableList<Int>,
    val cornerOrientations: MutableList<Int>,
    val edgePositions: MutableList<Int>,
    val edgeOrientations: MutableList<Boolean>
)
{
    fun setCornerPositions(newCornerPositions: MutableList<Int>) {
        cornerPositions.clear()
        cornerPositions.addAll(newCornerPositions)
    }

    fun setEdgePositions(newEdgePositions: MutableList<Int>) {
        edgePositions.clear()
        edgePositions.addAll(newEdgePositions)
    }

    fun getIncorrectlySolvedPieces(): Pair<MutableList<Int>, MutableList<Int>> {
        val incorrectlySolvedCorners = mutableListOf<Int>()
        for (i in 0..7) {
            if (cornerPositions[i] != i) {
                incorrectlySolvedCorners.add(i)
            }
            else if(cornerOrientations[i] != 3) {
                incorrectlySolvedCorners.add(i)
            }
        }

        val incorrectlySolvedEdges = mutableListOf<Int>()
        for (i in 0..11) {
            if (edgePositions[i] != i) {
                incorrectlySolvedEdges.add(i)
            }
            else if(edgeOrientations[i]) {
                incorrectlySolvedEdges.add(i)
            }
        }
        return Pair(incorrectlySolvedCorners, incorrectlySolvedEdges)
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
        if(other is CubeState){
            if(this.cornerPositions == other.cornerPositions &&
                this.cornerOrientations == other.cornerOrientations &&
                this.edgePositions == other.edgePositions &&
                this.edgeOrientations == other.edgeOrientations){
                return true
            }
        }
        return false
    }

    fun isSolved(): Boolean{
        return this == SOLVED_CUBE_STATE
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
            )
        )

    }
}
