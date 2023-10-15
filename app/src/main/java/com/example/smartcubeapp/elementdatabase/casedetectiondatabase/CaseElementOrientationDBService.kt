package com.example.smartcubeapp.elementdatabase.casedetectiondatabase

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.smartcubeapp.cube.piece.ElementOrientation
import com.example.smartcubeapp.cube.piece.Orientation
import com.example.smartcubeapp.cube.piece.PieceType
import com.example.smartcubeapp.dbAccesses
import com.example.smartcubeapp.elementdatabase.ElementDatabase
import com.example.smartcubeapp.elementdatabase.ElementDatabaseConstants

class CaseElementOrientationDBService(
    context: Context,
    databaseName: String = ElementDatabaseConstants.CASE_DATABASE_NAME
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
            put(
                ElementDatabaseConstants.ElementOrientationTable.PIECE_NUMBER_COLUMN,
                element.pieceNumber
            )
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
                when (element.sideRelativeOrientation) {
                    Orientation.Correct -> 0
                    Orientation.OneRotation -> 1
                    Orientation.TwoRotations -> 2
                    else -> 3
                }
            )
        }

        writableDatabase.insert(
            ElementDatabaseConstants.ElementOrientationTable.TABLE_NAME,
            null,
            contentValues
        )
        this.close()
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
            put(
                ElementDatabaseConstants.ElementOrientationTable.PIECE_NUMBER_COLUMN,
                element.pieceNumber
            )
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
                when (element.sideRelativeOrientation) {
                    Orientation.Correct -> 0
                    Orientation.OneRotation -> 1
                    Orientation.TwoRotations -> 2
                    else -> 3
                }
            )
        }

        val selection =
            "${ElementDatabaseConstants.ElementOrientationTable.SIDE_NAME_COLUMN} = ? AND " +
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
        val selection =
            "${ElementDatabaseConstants.ElementOrientationTable.SIDE_NAME_COLUMN} = ? AND " +
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
        dbAccesses++
        val db = this.readableDatabase
        val selection =
            "${ElementDatabaseConstants.ElementOrientationTable.SIDE_NAME_COLUMN} = ? AND " +
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

                cursor.close()
                db.close()
                return ElementOrientation(
                    sideName,
                    PieceType.values()[pieceType],
                    pieceNumber,
                    piecePosition,
                    pieceOrientation,
                    Orientation.values()[sideRelativeOrientation],
                    true
                )
            }
        }
        cursor.close()
        db.close()
        return null
    }

    fun getOrientationForElement(element: ElementOrientation): Orientation? {
        dbAccesses++
        val db = this.readableDatabase
        val selection =
            "${ElementDatabaseConstants.ElementOrientationTable.SIDE_NAME_COLUMN} = ? AND " +
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

        val projection = arrayOf(
            ElementDatabaseConstants.ElementOrientationTable.SIDE_RELATIVE_ORIENTATION_COLUMN
        )

        val cursor = db.query(
            ElementDatabaseConstants.ElementOrientationTable.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        with(cursor) {
            if (moveToNext()) {
                val sideRelativeOrientation =
                    getInt(getColumnIndexOrThrow(ElementDatabaseConstants.ElementOrientationTable.SIDE_RELATIVE_ORIENTATION_COLUMN))
                cursor.close()
                db.close()
                return Orientation.values()[sideRelativeOrientation]
            }
        }
        cursor.close()
        db.close()
        return null
    }

    fun getAllElementOrientationItems(): List<ElementOrientation> {
        dbAccesses++
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
        dbAccesses++
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

    private fun getElements(cursor: Cursor): List<ElementOrientation> {
        dbAccesses++
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
                    Orientation.values()[sideRelativeOrientation],
                    true
                )
                elementOrientationList.add(element)
            }
        }
        cursor.close()
        this.close()
        return elementOrientationList
    }
}
