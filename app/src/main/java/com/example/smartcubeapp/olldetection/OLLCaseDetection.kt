package com.example.smartcubeapp.olldetection

import com.example.smartcubeapp.cube.BlueSide
import com.example.smartcubeapp.cube.CubeSide
import com.example.smartcubeapp.cube.CubeState
import com.example.smartcubeapp.cube.GreenSide
import com.example.smartcubeapp.cube.RedSide
import com.example.smartcubeapp.cube.WhiteSide
import com.example.smartcubeapp.cube.YellowSide
import com.example.smartcubeapp.cube.piece.Orientation
import com.example.smartcubeapp.cube.piece.PieceType
import com.example.smartcubeapp.cube.piece.PositionRepresentationElement

class OLLCaseDetection(var cubeState: CubeState, private var cubeSide: CubeSide) {

    fun detectOLLCase(): PredefinedOLLCase?{
        TODO("Not implemented yet")
    }

    fun transformStateToPositionRepresentation(): Array<Array<PositionRepresentationElement>>{
        when (cubeSide) {
            YellowSide -> {
                return stateToPositionRepresentationYellow()
            }
            WhiteSide -> {
                return stateToPositionRepresentationWhite()
            }
            GreenSide -> {
                return stateToPositionRepresentationGreen()
            }
            BlueSide -> {
                return stateToPositionRepresentationBlue()
            }
            RedSide -> {
                return stateToPositionRepresentationRed()
            }
            else -> return stateToPositionRepresentationOrange()
        }
    }

    fun rotatePositionClockwise(position: Array<Array<PositionRepresentationElement>>): Array<Array<PositionRepresentationElement>>{
        TODO("Not implemented yet")
    }

    fun matchCase(case: OLLCase): PredefinedOLLCase?{
        TODO("Not implemented yet")
    }

    fun positionRepresentationToOLLCase(position: Array<Array<PositionRepresentationElement>>): OLLCase{
        TODO("Not implemented yet")
    }

    fun changeCubeSide(cubeSide: CubeSide){
        this.cubeSide = cubeSide
    }

    fun changeCubeState(cubeState: CubeState){
        this.cubeState = cubeState
    }

    //TODO("Hardcoded orientation has to be changed to orientation from database (once orientations are mapped)")
    private fun stateToPositionRepresentationYellow(): Array<Array<PositionRepresentationElement>>{
        return arrayOf(
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, cubeState.cornerPositions[4], Orientation.Correct, Pair(0, 0)),
                PositionRepresentationElement(PieceType.EDGE, cubeState.edgePositions[8], Orientation.Correct, Pair(0, 1)),
                PositionRepresentationElement(PieceType.CORNER, cubeState.cornerPositions[7], Orientation.Correct, Pair(0, 2))
            ),
            arrayOf(
                PositionRepresentationElement(PieceType.EDGE, cubeState.edgePositions[4], Orientation.Correct, Pair(1, 0)),
                PositionRepresentationElement(PieceType.EDGE, cubeState.edgePositions[7], Orientation.Correct, Pair(1, 2))
            ),
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, cubeState.cornerPositions[0], Orientation.Correct, Pair(2, 0)),
                PositionRepresentationElement(PieceType.EDGE, cubeState.edgePositions[0], Orientation.Correct, Pair(2, 1)),
                PositionRepresentationElement(PieceType.CORNER, cubeState.cornerPositions[3], Orientation.Correct, Pair(2, 2))
            )
        )
    }

    private fun stateToPositionRepresentationWhite(): Array<Array<PositionRepresentationElement>>{
        return arrayOf(
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, cubeState.cornerPositions[2], Orientation.Correct, Pair(0, 0)),
                PositionRepresentationElement(PieceType.EDGE, cubeState.edgePositions[6], Orientation.Correct, Pair(0, 1)),
                PositionRepresentationElement(PieceType.CORNER, cubeState.cornerPositions[6], Orientation.Correct, Pair(0, 2))
            ),
            arrayOf(
                PositionRepresentationElement(PieceType.EDGE, cubeState.edgePositions[2], Orientation.Correct, Pair(1, 0)),
                PositionRepresentationElement(PieceType.EDGE, cubeState.edgePositions[10], Orientation.Correct, Pair(1, 2))
            ),
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, cubeState.cornerPositions[1], Orientation.Correct, Pair(2, 0)),
                PositionRepresentationElement(PieceType.EDGE, cubeState.edgePositions[5], Orientation.Correct, Pair(2, 1)),
                PositionRepresentationElement(PieceType.CORNER, cubeState.cornerPositions[5], Orientation.Correct, Pair(2, 2))
            )
        )
    }

    private fun stateToPositionRepresentationGreen(): Array<Array<PositionRepresentationElement>>{
        return arrayOf(
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, cubeState.cornerPositions[3], Orientation.Correct, Pair(0, 0)),
                PositionRepresentationElement(PieceType.EDGE, cubeState.edgePositions[3], Orientation.Correct, Pair(0, 1)),
                PositionRepresentationElement(PieceType.CORNER, cubeState.cornerPositions[2], Orientation.Correct, Pair(0, 2))
            ),
            arrayOf(
                PositionRepresentationElement(PieceType.EDGE, cubeState.edgePositions[0], Orientation.Correct, Pair(1, 0)),
                PositionRepresentationElement(PieceType.EDGE, cubeState.edgePositions[2], Orientation.Correct, Pair(1, 2))
            ),
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, cubeState.cornerPositions[0], Orientation.Correct, Pair(2, 0)),
                PositionRepresentationElement(PieceType.EDGE, cubeState.edgePositions[1], Orientation.Correct, Pair(2, 1)),
                PositionRepresentationElement(PieceType.CORNER, cubeState.cornerPositions[1], Orientation.Correct, Pair(2, 2))
            )
        )
    }

    private fun stateToPositionRepresentationBlue(): Array<Array<PositionRepresentationElement>>{
        return arrayOf(
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, cubeState.cornerPositions[5], Orientation.Correct, Pair(0, 0)),
                PositionRepresentationElement(PieceType.EDGE, cubeState.edgePositions[10], Orientation.Correct, Pair(0, 1)),
                PositionRepresentationElement(PieceType.CORNER, cubeState.cornerPositions[6], Orientation.Correct, Pair(0, 2))
            ),
            arrayOf(
                PositionRepresentationElement(PieceType.EDGE, cubeState.edgePositions[9], Orientation.Correct, Pair(1, 0)),
                PositionRepresentationElement(PieceType.EDGE, cubeState.edgePositions[11], Orientation.Correct, Pair(1, 2))
            ),
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, cubeState.cornerPositions[4], Orientation.Correct, Pair(2, 0)),
                PositionRepresentationElement(PieceType.EDGE, cubeState.edgePositions[8], Orientation.Correct, Pair(2, 1)),
                PositionRepresentationElement(PieceType.CORNER, cubeState.cornerPositions[7], Orientation.Correct, Pair(2, 2))
            )
        )
    }

    private fun stateToPositionRepresentationRed(): Array<Array<PositionRepresentationElement>>{
        return arrayOf(
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, cubeState.cornerPositions[1], Orientation.Correct, Pair(0, 0)),
                PositionRepresentationElement(PieceType.EDGE, cubeState.edgePositions[5], Orientation.Correct, Pair(0, 1)),
                PositionRepresentationElement(PieceType.CORNER, cubeState.cornerPositions[5], Orientation.Correct, Pair(0, 2))
            ),
            arrayOf(
                PositionRepresentationElement(PieceType.EDGE, cubeState.edgePositions[1], Orientation.Correct, Pair(1, 0)),
                PositionRepresentationElement(PieceType.EDGE, cubeState.edgePositions[9], Orientation.Correct, Pair(1, 2))
            ),
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, cubeState.cornerPositions[0], Orientation.Correct, Pair(2, 0)),
                PositionRepresentationElement(PieceType.EDGE, cubeState.edgePositions[4], Orientation.Correct, Pair(2, 1)),
                PositionRepresentationElement(PieceType.CORNER, cubeState.cornerPositions[4], Orientation.Correct, Pair(2, 2))
            )
        )
    }

    private fun stateToPositionRepresentationOrange(): Array<Array<PositionRepresentationElement>>{
        return arrayOf(
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, cubeState.cornerPositions[3], Orientation.Correct, Pair(0, 0)),
                PositionRepresentationElement(PieceType.EDGE, cubeState.edgePositions[7], Orientation.Correct, Pair(0, 1)),
                PositionRepresentationElement(PieceType.CORNER, cubeState.cornerPositions[7], Orientation.Correct, Pair(0, 2))
            ),
            arrayOf(
                PositionRepresentationElement(PieceType.EDGE, cubeState.edgePositions[3], Orientation.Correct, Pair(1, 0)),
                PositionRepresentationElement(PieceType.EDGE, cubeState.edgePositions[11], Orientation.Correct, Pair(1, 2))
            ),
            arrayOf(
                PositionRepresentationElement(PieceType.CORNER, cubeState.cornerPositions[2], Orientation.Correct, Pair(2, 0)),
                PositionRepresentationElement(PieceType.EDGE, cubeState.edgePositions[6], Orientation.Correct, Pair(2, 1)),
                PositionRepresentationElement(PieceType.CORNER, cubeState.cornerPositions[6], Orientation.Correct, Pair(2, 2))
            )
        )
    }

}