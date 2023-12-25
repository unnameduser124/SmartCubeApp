package com.example.cube_cube.cube

import java.nio.ByteBuffer

class MoveDataParser(private val data: ByteBuffer) {

    private val turns = mapOf(
        0 to 1,
        1 to 2,
        2 to -1,
        8 to -2
    )

    private val faces = listOf("F", "U", "L", "D", "R", "B")

    fun parseCubeValue(): CubeState {
        val state = CubeState(
            cornerPositions = mutableListOf(),
            cornerOrientations = mutableListOf(),
            edgePositions = mutableListOf(),
            edgeOrientations = mutableListOf(),
            lastMove = Move()
        )
        val moves = mutableListOf<Any>()

        if (data.get(18) == 0xa7.toByte()) { // decrypt
            val key = listOf(
                176, 81, 104, 224, 86, 137, 237, 119, 38, 26, 193, 161, 210, 126, 150, 81,
                93, 13, 236, 249, 89, 235, 88, 24, 113, 81, 214, 131, 130, 199, 2, 169, 39,
                165, 171, 41
            )
            val k = data.get(19)
            val k1 = k.toInt() shr 4 and 0xf
            val k2 = k.toInt() and 0xf

            for (i in 0 until data.limit()) {
                val move = (data.get(i).toInt() + key[i + k1] + key[i + k2]) and 0xff
                val highNibble = move shr 4
                val lowNibble = move and 0b1111

                when {
                    i < 4 -> state.cornerPositions.addAll(listOf(highNibble, lowNibble))
                    i < 8 -> state.cornerOrientations.addAll(listOf(highNibble, lowNibble))
                    i < 14 -> state.edgePositions.addAll(listOf(highNibble, lowNibble))
                    i < 16 -> {
                        state.edgeOrientations.add(move and 0b10000000 != 0)
                        state.edgeOrientations.add(move and 0b01000000 != 0)
                        state.edgeOrientations.add(move and 0b00100000 != 0)
                        state.edgeOrientations.add(move and 0b00010000 != 0)
                        if (i == 14) {
                            state.edgeOrientations.add(move and 0b00001000 != 0)
                            state.edgeOrientations.add(move and 0b00000100 != 0)
                            state.edgeOrientations.add(move and 0b00000010 != 0)
                            state.edgeOrientations.add(move and 0b00000001 != 0)
                        }
                    }

                    else -> {
                        val newMove = parseMove(highNibble, lowNibble)
                        if (newMove != null) {
                            moves.add(newMove)
                            break
                        }
                    }
                }
            }
        }

        val cornerPositionStartingFromZero = state.cornerPositions.map { it - 1 }.toMutableList()
        state.setCornerPositions(cornerPositionStartingFromZero)
        val edgePositionsStartingFromZero = state.edgePositions.map { it - 1 }.toMutableList()
        state.setEdgePositions(edgePositionsStartingFromZero)
        state.lastMove = moves.last() as Move
        return state
    }

    private fun parseMove(faceIndex: Int, turnIndex: Int): Move? {
        return try {
            val face = faces[faceIndex - 1]
            val amount = turns[turnIndex - 1]
            var notation = face

            notation = when (amount) {
                -1 -> "${face}'"
                else -> notation
            }
            Move(face, amount!!, notation)
        } catch (e: Exception) {
            null
        }
    }
}
