package com.example.smartcubeapp.elementdatabase

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.smartcubeapp.cube.piece.ElementOrientation
import com.example.smartcubeapp.cube.piece.Orientation
import com.example.smartcubeapp.cube.piece.PieceType

class ElementOrientationDBService(
    context: Context,
    databaseName: String = ElementDatabaseConstants.DATABASE_NAME
) :
    ElementDatabase(context, databaseName) {

    private val fullElementProjection = arrayOf(
        ElementDatabaseConstants.ElementOrientationTable.SIDE_NAME_COLUMN,
        ElementDatabaseConstants.ElementOrientationTable.PIECE_TYPE_COLUMN,
        ElementDatabaseConstants.ElementOrientationTable.PIECE_NUMBER_COLUMN,
        ElementDatabaseConstants.ElementOrientationTable.PIECE_POSITION_COLUMN,
        ElementDatabaseConstants.ElementOrientationTable.PIECE_ORIENTATION_COLUMN,
        ElementDatabaseConstants.ElementOrientationTable.SIDE_RELATIVE_ORIENTATION_COLUMN
    )

    fun addElementOrientation(element: ElementOrientation) {
        if (element.sideRelativeOrientation == null) {
            return
        }
        val contentValues = ContentValues().apply {
            put(ElementDatabaseConstants.ElementOrientationTable.SIDE_NAME_COLUMN, element.sideName)
            put(
                ElementDatabaseConstants.ElementOrientationTable.PIECE_TYPE_COLUMN,
                element.pieceType.ordinal
            )
            put(ElementDatabaseConstants.ElementOrientationTable.PIECE_NUMBER_COLUMN, element.pieceNumber)
            put(
                ElementDatabaseConstants.ElementOrientationTable.PIECE_POSITION_COLUMN,
                element.piecePosition
            )
            put(
                ElementDatabaseConstants.ElementOrientationTable.PIECE_ORIENTATION_COLUMN,
                element.pieceOrientation
            )
            put(
                ElementDatabaseConstants.ElementOrientationTable.SIDE_RELATIVE_ORIENTATION_COLUMN,
                if ((element.sideRelativeOrientation!!)==Orientation.Correct) 1 else 0
            )
        }

        writableDatabase.insert(
            ElementDatabaseConstants.ElementOrientationTable.TABLE_NAME,
            null,
            contentValues
        )
    }

    fun updateElementOrientation(element: ElementOrientation) {
        val tryGetElement = getElementOrientation(element)
        if (tryGetElement == null) {
            addElementOrientation(element)
            return
        }
        if (element.sideRelativeOrientation == null) {
            return
        }
        val contentValues = ContentValues().apply {
            put(ElementDatabaseConstants.ElementOrientationTable.SIDE_NAME_COLUMN, element.sideName)
            put(
                ElementDatabaseConstants.ElementOrientationTable.PIECE_TYPE_COLUMN,
                element.pieceType.ordinal
            )
            put(ElementDatabaseConstants.ElementOrientationTable.PIECE_NUMBER_COLUMN, element.pieceNumber)
            put(
                ElementDatabaseConstants.ElementOrientationTable.PIECE_POSITION_COLUMN,
                element.piecePosition
            )
            put(
                ElementDatabaseConstants.ElementOrientationTable.PIECE_ORIENTATION_COLUMN,
                element.pieceOrientation
            )
            put(
                ElementDatabaseConstants.ElementOrientationTable.SIDE_RELATIVE_ORIENTATION_COLUMN,
                if ((element.sideRelativeOrientation!!)==Orientation.Correct) 1 else 0
            )
        }

        val selection = "${ElementDatabaseConstants.ElementOrientationTable.SIDE_NAME_COLUMN} = ? AND " +
                "${ElementDatabaseConstants.ElementOrientationTable.PIECE_TYPE_COLUMN} = ? AND " +
                "${ElementDatabaseConstants.ElementOrientationTable.PIECE_NUMBER_COLUMN} = ? AND " +
                "${ElementDatabaseConstants.ElementOrientationTable.PIECE_POSITION_COLUMN} = ? AND " +
                "${ElementDatabaseConstants.ElementOrientationTable.PIECE_ORIENTATION_COLUMN} = ?"

        val selectionArgs = arrayOf(
            element.sideName,
            element.pieceType.ordinal.toString(),
            element.pieceNumber.toString(),
            element.piecePosition.toString(),
            element.pieceOrientation.toString()
        )

        this.writableDatabase.update(
            ElementDatabaseConstants.ElementOrientationTable.TABLE_NAME,
            contentValues,
            selection,
            selectionArgs
        )

    }

    fun deleteElementOrientation(element: ElementOrientation) {
        val selection = "${ElementDatabaseConstants.ElementOrientationTable.SIDE_NAME_COLUMN} = ? AND " +
                "${ElementDatabaseConstants.ElementOrientationTable.PIECE_TYPE_COLUMN} = ? AND " +
                "${ElementDatabaseConstants.ElementOrientationTable.PIECE_NUMBER_COLUMN} = ? AND " +
                "${ElementDatabaseConstants.ElementOrientationTable.PIECE_POSITION_COLUMN} = ? AND " +
                "${ElementDatabaseConstants.ElementOrientationTable.PIECE_ORIENTATION_COLUMN} = ?"
        val selectionArgs = arrayOf(
            element.sideName,
            element.pieceType.ordinal.toString(),
            element.pieceNumber.toString(),
            element.piecePosition.toString(),
            element.pieceOrientation.toString()
        )

        this.writableDatabase.delete(
            ElementDatabaseConstants.ElementOrientationTable.TABLE_NAME,
            selection,
            selectionArgs
        )
    }

    fun getElementOrientation(element: ElementOrientation): ElementOrientation? {
        val db = this.readableDatabase
        val selection = "${ElementDatabaseConstants.ElementOrientationTable.SIDE_NAME_COLUMN} = ? AND " +
                "${ElementDatabaseConstants.ElementOrientationTable.PIECE_TYPE_COLUMN} = ? AND " +
                "${ElementDatabaseConstants.ElementOrientationTable.PIECE_NUMBER_COLUMN} = ? AND " +
                "${ElementDatabaseConstants.ElementOrientationTable.PIECE_POSITION_COLUMN} = ? AND " +
                "${ElementDatabaseConstants.ElementOrientationTable.PIECE_ORIENTATION_COLUMN} = ?"
        val selectionArgs = arrayOf(
            element.sideName,
            element.pieceType.ordinal.toString(),
            element.pieceNumber.toString(),
            element.piecePosition.toString(),
            element.pieceOrientation.toString()
        )

        val cursor = db.query(
            ElementDatabaseConstants.ElementOrientationTable.TABLE_NAME,
            fullElementProjection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        with(cursor) {
            if (moveToNext()) {
                val sideName =
                    getString(getColumnIndexOrThrow(ElementDatabaseConstants.ElementOrientationTable.SIDE_NAME_COLUMN))
                val pieceType =
                    getInt(getColumnIndexOrThrow(ElementDatabaseConstants.ElementOrientationTable.PIECE_TYPE_COLUMN))
                val pieceNumber =
                    getInt(getColumnIndexOrThrow(ElementDatabaseConstants.ElementOrientationTable.PIECE_NUMBER_COLUMN))
                val piecePosition =
                    getInt(getColumnIndexOrThrow(ElementDatabaseConstants.ElementOrientationTable.PIECE_POSITION_COLUMN))
                val pieceOrientation =
                    getInt(getColumnIndexOrThrow(ElementDatabaseConstants.ElementOrientationTable.PIECE_ORIENTATION_COLUMN))
                val sideRelativeOrientation =
                    getInt(getColumnIndexOrThrow(ElementDatabaseConstants.ElementOrientationTable.SIDE_RELATIVE_ORIENTATION_COLUMN))

                return ElementOrientation(
                    sideName,
                    PieceType.values()[pieceType],
                    pieceNumber,
                    piecePosition,
                    pieceOrientation,
                    if(sideRelativeOrientation == 1) Orientation.Correct else Orientation.Incorrect,
                )
            }
        }
        return null
    }

    fun getAllElementOrientationItems(): List<ElementOrientation> {

        val cursor = readableDatabase.query(
            ElementDatabaseConstants.ElementOrientationTable.TABLE_NAME,
            fullElementProjection,
            null,
            null,
            null,
            null,
            null
        )

        return getElements(cursor)
    }

    fun getElementOrientationItemsBySide(sideName: String): List<ElementOrientation> {

        val selection = "${ElementDatabaseConstants.ElementOrientationTable.SIDE_NAME_COLUMN} = ?"
        val selectionArgs = arrayOf(sideName)

        val cursor = readableDatabase.query(
            ElementDatabaseConstants.ElementOrientationTable.TABLE_NAME,
            fullElementProjection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        return getElements(cursor)
    }

    fun getElementOrientationItemsBySideCorrectlySideRelativeOriented(sideName: String): List<ElementOrientation> {

        val selection = "${ElementDatabaseConstants.ElementOrientationTable.SIDE_NAME_COLUMN} = ? AND " +
                "${ElementDatabaseConstants.ElementOrientationTable.SIDE_RELATIVE_ORIENTATION_COLUMN} = ?"
        val selectionArgs = arrayOf(sideName, "1")

        val cursor = readableDatabase.query(
            ElementDatabaseConstants.ElementOrientationTable.TABLE_NAME,
            fullElementProjection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        return getElements(cursor)
    }

    fun getElementOrientationItemsForPiece(
        pieceNumber: Int,
        pieceType: PieceType
    ): List<ElementOrientation> {

        val selection =
            "${ElementDatabaseConstants.ElementOrientationTable.PIECE_NUMBER_COLUMN} = ? AND " +
                    "${ElementDatabaseConstants.ElementOrientationTable.PIECE_TYPE_COLUMN} = ?"
        val selectionArgs = arrayOf(pieceNumber.toString(), pieceType.ordinal.toString())

        val cursor = readableDatabase.query(
            ElementDatabaseConstants.ElementOrientationTable.TABLE_NAME,
            fullElementProjection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        return getElements(cursor)
    }

    private fun getElements(cursor: Cursor): List<ElementOrientation> {

        val elementOrientationList = mutableListOf<ElementOrientation>()

        with(cursor) {
            while (moveToNext()) {
                val elementSideName =
                    getString(getColumnIndexOrThrow(ElementDatabaseConstants.ElementOrientationTable.SIDE_NAME_COLUMN))
                val pieceType =
                    getInt(getColumnIndexOrThrow(ElementDatabaseConstants.ElementOrientationTable.PIECE_TYPE_COLUMN))
                val pieceNumber =
                    getInt(getColumnIndexOrThrow(ElementDatabaseConstants.ElementOrientationTable.PIECE_NUMBER_COLUMN))
                val piecePosition =
                    getInt(getColumnIndexOrThrow(ElementDatabaseConstants.ElementOrientationTable.PIECE_POSITION_COLUMN))
                val pieceOrientation =
                    getInt(getColumnIndexOrThrow(ElementDatabaseConstants.ElementOrientationTable.PIECE_ORIENTATION_COLUMN))
                val sideRelativeOrientation =
                    getInt(getColumnIndexOrThrow(ElementDatabaseConstants.ElementOrientationTable.SIDE_RELATIVE_ORIENTATION_COLUMN))
                val element = ElementOrientation(
                    elementSideName,
                    PieceType.values()[pieceType],
                    pieceNumber,
                    piecePosition,
                    pieceOrientation,
                    if(sideRelativeOrientation == 1) Orientation.Correct else Orientation.Incorrect,
                    true
                )
                elementOrientationList.add(element)
            }
        }
        return elementOrientationList
    }
}