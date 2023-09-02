package com.example.smartcubeapp.elementdatabase.element

import com.example.smartcubeapp.cube.CubeSide
import com.example.smartcubeapp.cube.CubeState
import com.example.smartcubeapp.cube.cubeSides
import com.example.smartcubeapp.cube.getSidesForCorner

class SideRelativePieceDetection(private val cubeState: CubeState, private val relativeSide: CubeSide) {

    fun getPiecesForSide(): List<ElementOrientation> {
        val pieces = mutableListOf<ElementOrientation>()
        val cornerIndexes = relativeSide.cornerIndexes
        val edgeIndexes = relativeSide.edgeIndexes

        for (cornerNumber in cornerIndexes) {
            val position =
                cubeState.cornerPositions.indexOf(cubeState.cornerPositions.find { it == cornerNumber })
            val orientation = cubeState.cornerOrientations[position]

            val element = ElementOrientation(
                relativeSide.sideName,
                PieceType.CORNER,
                cornerNumber,
                position,
                orientation
            )

            pieces.add(element)
        }

        for (edgeIndex in edgeIndexes) {
            val position =
                cubeState.edgePositions.indexOf(cubeState.edgePositions.find { it == edgeIndex })
            val orientation = cubeState.edgeOrientations[position]

            val element = ElementOrientation(
                relativeSide.sideName,
                PieceType.EDGE,
                edgeIndex,
                position,
                if(orientation) 1 else 0
            )

            pieces.add(element)
        }

        return pieces
    }

    companion object{
        fun getPieceColors(pieceNumber: Int, pieceType: PieceType): List<String>{
            val colors = mutableListOf<String>()
            if(pieceType == PieceType.CORNER){
                val sides = getSidesForCorner(pieceNumber)
                for(side in sides){
                    colors.add(side.sideName)
                }
            }
            else{
                val sides = cubeSides
                for(side in sides){
                    if(side.edgeIndexes.contains(pieceNumber)){
                        colors.add(side.sideName)
                    }
                }
            }
            return colors
        }
    }
}