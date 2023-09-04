package com.example.smartcubeapp.phasedetection

import android.content.Context
import com.example.smartcubeapp.cube.CubeSide
import com.example.smartcubeapp.cube.CubeState
import com.example.smartcubeapp.cube.cubeSides
import com.example.smartcubeapp.cube.getSideIntersectionIndexes
import com.example.smartcubeapp.elementdatabase.ElementOrientationDBService

class CubeStatePhaseDetection(private var cubeState: CubeState) {

    fun changeState(newCubeState: CubeState) {
        cubeState = newCubeState
    }

    fun crossSolved(): Boolean {
        val correctlySolvedEdges = cubeState.getCorrectlySolvedPieces().second
        for (side in cubeSides) {
            if (correctlySolvedEdges.containsAll(side.edgeIndexes.toList())) {
                return true
            }
        }
        return false
    }

    @Suppress("FunctionName")
    fun F2LSolved(
        predeterminedSolvedSide: CubeSide? = null
    ): Boolean {
        //check if theres a side that has all corners and edges solved correctly
        //if there isn't then return false
        //if there is then find the side that has no intersecting pieces with that side
        //if all of the pieces that are not on the solved side or the opposite side are solved then return true

        val solvedSide = predeterminedSolvedSide ?: (cubeSides.find {
            checkIfSideIsSolved(it.cornerIndexes, it.edgeIndexes)
        } ?: return false)

        val oppositeSide = cubeSides.find {
            getSideIntersectionIndexes(
                solvedSide,
                it
            ).isEmpty()
        } ?: return false

        for (i in 0..11) {
            if (!solvedSide.edgeIndexes.contains(i) && !oppositeSide.edgeIndexes.contains(i)) {
                if (cubeState.edgeOrientations[i] || cubeState.edgePositions[i] != i) {
                    return false
                }
            }
        }
        return true
    }

    @Suppress("FunctionName")
    fun OLLSolved(
        context: Context,
        predeterminedSolvedSide: CubeSide? = null,
    ): Boolean {

        val solvedSide = predeterminedSolvedSide ?: (cubeSides.find {
            checkIfSideIsSolved(it.cornerIndexes, it.edgeIndexes)
        } ?: return false)

        val oppositeSide = cubeSides.find {
            getSideIntersectionIndexes(
                solvedSide,
                it
            ).isEmpty()
        } ?: return false

        val correctPositions =
            ElementOrientationDBService(context).getElementOrientationItemsBySideCorrectlySideRelativeOriented(
                oppositeSide.sideName
            )

        oppositeSide.edgeIndexes.forEach { edge ->
            val edgePosition = getEdgePositionAndOrientation(edge)
            if (correctPositions.none { it.piecePosition == edgePosition.first && it.pieceOrientation == edgePosition.second && it.pieceNumber == edge }) {
                return false
            }
        }
        oppositeSide.cornerIndexes.forEach { corner ->
            val cornerPosition = getCornerPositionAndOrientation(corner)
            if (correctPositions.none { it.piecePosition == cornerPosition.first && it.pieceOrientation == cornerPosition.second && it.pieceNumber == corner }) {
                return false
            }
        }
        return true
    }

    fun getFinishedPhaseForState(context: Context): SolvePhase {
        if (cubeState.isSolved()) {
            return SolvePhase.PLL
        }
        if (OLLSolved(context)) {
            return SolvePhase.OLL
        }
        if (F2LSolved()) {
            return SolvePhase.F2L
        }
        if (crossSolved()) {
            return SolvePhase.Cross
        }
        return SolvePhase.Scrambled
    }

    private fun getEdgePositionAndOrientation(edgeIndex: Int): Pair<Int, Int> {
        val edgePosition = cubeState.edgePositions[edgeIndex]
        val edgeOrientation = if (cubeState.edgeOrientations[edgeIndex]) 1 else 0
        return Pair(edgePosition, edgeOrientation)
    }

    private fun getCornerPositionAndOrientation(edgeIndex: Int): Pair<Int, Int> {
        val cornerPosition = cubeState.cornerPositions[edgeIndex]
        val cornerOrientation = cubeState.cornerOrientations[edgeIndex]
        return Pair(cornerPosition, cornerOrientation)
    }

    fun checkIfSideIsSolved(sideCorners: Array<Int>, sideEdges: Array<Int>): Boolean {
        val correctlySolvedEdges = cubeState.getCorrectlySolvedPieces().second
        val correctlySolvedCorners = cubeState.getCorrectlySolvedPieces().first
        if (correctlySolvedEdges.containsAll(sideEdges.toList()) && correctlySolvedCorners.containsAll(
                sideCorners.toList()
            )
        ) {
            return true
        }
        return false
    }

}
