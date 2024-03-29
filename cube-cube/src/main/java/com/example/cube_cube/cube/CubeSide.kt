package com.example.cube_cube.cube

open class CubeSide(
    val sideName: String,
    val cornerIndexes: Array<Int>,
    val edgeIndexes: Array<Int>,
    val solvedState: Array<Array<Int>>
) {
    override fun equals(other: Any?): Boolean {
        return if (other is CubeSide) {
            sideName == other.sideName
                    && cornerIndexes.contentEquals(other.cornerIndexes)
                    && edgeIndexes.contentEquals(other.edgeIndexes)
        } else {
            false
        }
    }

    override fun hashCode(): Int {
        var result = sideName.hashCode()
        result = 31 * result + cornerIndexes.contentHashCode()
        result = 31 * result + edgeIndexes.contentHashCode()
        return result
    }
}

val YellowSide = CubeSide(
    "yellow",
    CubeSideIndexes.YELLOW_SIDE_CORNER_INDEXES,
    CubeSideIndexes.YELLOW_SIDE_EDGE_INDEXES,
    arrayOf(
        arrayOf(4, 8, 7),
        arrayOf(4, 7),
        arrayOf(0, 0, 3)
    )
)

val WhiteSide = CubeSide(
    "white",
    CubeSideIndexes.WHITE_SIDE_CORNER_INDEXES,
    CubeSideIndexes.WHITE_SIDE_EDGE_INDEXES,
    arrayOf(
        arrayOf(2, 6, 6),
        arrayOf(2, 10),
        arrayOf(1, 5, 5)
    )
)

val GreenSide = CubeSide(
    "green",
    CubeSideIndexes.GREEN_SIDE_CORNER_INDEXES,
    CubeSideIndexes.GREEN_SIDE_EDGE_INDEXES,
    arrayOf(
        arrayOf(3, 3, 2),
        arrayOf(0, 2),
        arrayOf(0, 1, 1)
    )
)

val BlueSide = CubeSide(
    "blue",
    CubeSideIndexes.BLUE_SIDE_CORNER_INDEXES,
    CubeSideIndexes.BLUE_SIDE_EDGE_INDEXES,
    arrayOf(
        arrayOf(5, 10, 6),
        arrayOf(9, 11),
        arrayOf(4, 8, 7)
    )
)

val RedSide = CubeSide(
    "red",
    CubeSideIndexes.RED_SIDE_CORNER_INDEXES,
    CubeSideIndexes.RED_SIDE_EDGE_INDEXES,
    arrayOf(
        arrayOf(1, 5, 5),
        arrayOf(1, 9),
        arrayOf(0, 4, 4)
    )
)

val OrangeSide = CubeSide(
    "orange",
    CubeSideIndexes.ORANGE_SIDE_CORNER_INDEXES,
    CubeSideIndexes.ORANGE_SIDE_EDGE_INDEXES,
    arrayOf(
        arrayOf(3, 7, 7),
        arrayOf(3, 11),
        arrayOf(2, 6, 6)
    )
)

val cubeSides = listOf(
    WhiteSide,
    YellowSide,
    GreenSide,
    BlueSide,
    RedSide,
    OrangeSide
)

fun getCubeSideFromName(name: String): CubeSide {
    for (side in cubeSides) {
        if (side.sideName == name) {
            return side
        }
    }
    return WhiteSide
}

object CubeSideIndexes {
    val WHITE_SIDE_CORNER_INDEXES = arrayOf(1, 2, 5, 6)
    val WHITE_SIDE_EDGE_INDEXES = arrayOf(2, 5, 6, 10)

    val YELLOW_SIDE_CORNER_INDEXES = arrayOf(0, 3, 4, 7)
    val YELLOW_SIDE_EDGE_INDEXES = arrayOf(0, 4, 7, 8)

    val GREEN_SIDE_CORNER_INDEXES = arrayOf(0, 1, 2, 3)
    val GREEN_SIDE_EDGE_INDEXES = arrayOf(0, 1, 2, 3)

    val BLUE_SIDE_CORNER_INDEXES = arrayOf(4, 5, 6, 7)
    val BLUE_SIDE_EDGE_INDEXES = arrayOf(8, 9, 10, 11)

    val RED_SIDE_CORNER_INDEXES = arrayOf(0, 1, 4, 5)
    val RED_SIDE_EDGE_INDEXES = arrayOf(1, 4, 5, 9)

    val ORANGE_SIDE_CORNER_INDEXES = arrayOf(2, 3, 6, 7)
    val ORANGE_SIDE_EDGE_INDEXES = arrayOf(3, 6, 7, 11)

}

fun getSideIntersectionIndexes(
    side1: CubeSide,
    side2: CubeSide
): List<Int> {
    val intersection = mutableListOf<Int>()
    for (edge in side1.edgeIndexes) {
        if (side2.edgeIndexes.contains(edge)) {
            intersection.add(edge)
        }
    }
    for (corner in side1.cornerIndexes) {
        if (side2.cornerIndexes.contains(corner)) {
            intersection.add(corner)
        }
    }
    return intersection
}

fun getSidesForCorner(cornerNumber: Int): List<CubeSide> {
    val sides = mutableListOf<CubeSide>()
    for (side in cubeSides) {
        if (side.cornerIndexes.contains(cornerNumber)) {
            sides.add(side)
        }
    }
    return sides
}

fun getSidesForEdge(edgeNumber: Int): List<CubeSide> {
    val sides = mutableListOf<CubeSide>()
    for (side in cubeSides) {
        if (side.edgeIndexes.contains(edgeNumber)) {
            sides.add(side)
        }
    }
    return sides
}
