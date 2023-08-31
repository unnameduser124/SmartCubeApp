package com.example.smartcubeapp.elementorientationtests

import android.content.Context
import android.database.Cursor
import androidx.test.platform.app.InstrumentationRegistry
import com.example.smartcubeapp.elementdatabase.ElementDatabaseConstants
import com.example.smartcubeapp.elementdatabase.ElementOrientationDBService
import com.example.smartcubeapp.elementdatabase.element.PieceType
import com.example.smartcubeapp.elementdatabase.element.ElementOrientation
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import junit.framework.TestCase.fail
import org.junit.After
import org.junit.Before
import org.junit.Test

class ElementOrientationDBServiceTests {

    private lateinit var appContext: Context
    private lateinit var dbService: ElementOrientationDBService

    @Before
    fun setUp() {
        appContext = InstrumentationRegistry.getInstrumentation().targetContext
        dbService = ElementOrientationDBService(appContext, ElementDatabaseConstants.TEST_DATABASE_NAME)
        dbService.createElementOrientationTable(dbService.writableDatabase)
    }

    @After
    fun tearDown() {
        dbService.close()
        appContext.deleteDatabase(ElementDatabaseConstants.TEST_DATABASE_NAME)
    }

    @Test
    fun addElementOrientationTest() {
        val element = ElementOrientation("yellow", PieceType.CORNER, 1, 1, 1, false)
        dbService.addElementOrientation(element)

        val cursor = dbService.readableDatabase.query(
            ElementDatabaseConstants.ElementOrientationTable.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )

        assertElementExists(cursor, element)
    }

    @Test
    fun addElementOrientationFailNullSideRelativeOrientation() {
        val element = ElementOrientation("yellow", PieceType.CORNER, 1, 1, 1, null)
        dbService.addElementOrientation(element)
        val cursor = dbService.readableDatabase.query(
            ElementDatabaseConstants.ElementOrientationTable.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )

        assertTrue(cursor.count == 0)
    }

    @Test
    fun updateElementOrientationTest() {
        val element = ElementOrientation("yellow", PieceType.CORNER, 1, 1, 1, false)
        dbService.addElementOrientation(element)

        val updatedElement = ElementOrientation("yellow", PieceType.CORNER, 1, 1, 1, true)
        dbService.updateElementOrientation(updatedElement)

        val cursor = dbService.readableDatabase.query(
            ElementDatabaseConstants.ElementOrientationTable.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )

        assertElementExists(cursor, updatedElement)
    }

    @Test
    fun updateElementOrientationFailNullSideRelativeOrientation() {
        val element = ElementOrientation("yellow", PieceType.CORNER, 1, 1, 1, false)
        dbService.addElementOrientation(element)

        val updatedElement = ElementOrientation("yellow", PieceType.CORNER, 1, 1, 1, null)
        dbService.updateElementOrientation(updatedElement)

        val cursor = dbService.readableDatabase.query(
            ElementDatabaseConstants.ElementOrientationTable.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )

        assertElementExists(cursor, element)
    }

    @Test
    fun deleteElementOrientationTest() {
        val element = ElementOrientation("yellow", PieceType.CORNER, 1, 1, 1, false)
        dbService.addElementOrientation(element)

        dbService.deleteElementOrientation(element)

        val cursor = dbService.readableDatabase.query(
            ElementDatabaseConstants.ElementOrientationTable.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )

        assertTrue(cursor.count == 0)
    }

    @Test
    fun getElementOrientationTest() {
        val element = ElementOrientation("yellow", PieceType.CORNER, 1, 1, 1, false)
        dbService.addElementOrientation(element)

        val retrievedElement = dbService.getElementOrientation(element)

        assertEquals(element, retrievedElement)
    }

    @Test
    fun getElementOrientationFail() {
        val element = ElementOrientation("yellow", PieceType.CORNER, 1, 1, 1, false)
        dbService.addElementOrientation(element)

        val retrievedElement = dbService.getElementOrientation(
            ElementOrientation(
                "yellow",
                PieceType.CORNER,
                1,
                1,
                2,
                false
            )
        )

        assertEquals(null, retrievedElement)
    }

    @Test
    fun getAllElementOrientationItems() {
        val element1 = ElementOrientation("yellow", PieceType.CORNER, 1, 1, 1, false)
        val element2 = ElementOrientation("yellow", PieceType.CORNER, 1, 1, 2, false)
        val element3 = ElementOrientation("yellow", PieceType.CORNER, 1, 1, 3, false)
        dbService.addElementOrientation(element1)
        dbService.addElementOrientation(element2)
        dbService.addElementOrientation(element3)

        val retrievedElements = dbService.getAllElementOrientationItems()

        assertTrue(retrievedElements.contains(element1))
        assertTrue(retrievedElements.contains(element2))
        assertTrue(retrievedElements.contains(element3))
    }

    @Test
    fun getAllElementOrientationItemsNoItemsInDatabase() {
        val retrievedElements = dbService.getAllElementOrientationItems()

        assertTrue(retrievedElements.isEmpty())
    }

    @Test
    fun getElementsOrientationItemBySide() {
        val element1 = ElementOrientation("yellow", PieceType.CORNER, 1, 1, 1, false)
        val element2 = ElementOrientation("yellow", PieceType.CORNER, 1, 1, 2, false)
        val element3 = ElementOrientation("yellow", PieceType.CORNER, 1, 1, 3, false)
        val element4 = ElementOrientation("white", PieceType.CORNER, 1, 1, 1, false)
        val element5 = ElementOrientation("white", PieceType.CORNER, 1, 1, 2, false)
        val element6 = ElementOrientation("white", PieceType.CORNER, 1, 1, 3, false)
        dbService.addElementOrientation(element1)
        dbService.addElementOrientation(element2)
        dbService.addElementOrientation(element3)
        dbService.addElementOrientation(element4)
        dbService.addElementOrientation(element5)
        dbService.addElementOrientation(element6)

        val retrievedElements = dbService.getElementOrientationItemsBySide("yellow")

        assertTrue(retrievedElements.contains(element1))
        assertTrue(retrievedElements.contains(element2))
        assertTrue(retrievedElements.contains(element3))
        assertTrue(retrievedElements.size == 3)
    }

    @Test
    fun getElementOrientationItemsBySideNoElementsWithSideName(){
        val element1 = ElementOrientation("yellow", PieceType.CORNER, 1, 1, 1, false)
        val element2 = ElementOrientation("yellow", PieceType.CORNER, 1, 1, 2, false)
        val element3 = ElementOrientation("yellow", PieceType.CORNER, 1, 1, 3, false)
        val element4 = ElementOrientation("white", PieceType.CORNER, 1, 1, 1, false)
        val element5 = ElementOrientation("white", PieceType.CORNER, 1, 1, 2, false)
        val element6 = ElementOrientation("white", PieceType.CORNER, 1, 1, 3, false)
        dbService.addElementOrientation(element1)
        dbService.addElementOrientation(element2)
        dbService.addElementOrientation(element3)
        dbService.addElementOrientation(element4)
        dbService.addElementOrientation(element5)
        dbService.addElementOrientation(element6)

        val retrievedElements = dbService.getElementOrientationItemsBySide("red")

        assertTrue(retrievedElements.isEmpty())
    }

    @Test
    fun getElementOrientationItemsForPiece(){
        val element1 = ElementOrientation("yellow", PieceType.CORNER, 1, 1, 1, false)
        val element2 = ElementOrientation("yellow", PieceType.EDGE, 2, 1, 2, false)
        val element3 = ElementOrientation("yellow", PieceType.CORNER, 1, 4, 3, true)
        val element4 = ElementOrientation("white", PieceType.EDGE, 1, 1, 1, false)
        val element5 = ElementOrientation("white", PieceType.CORNER, 4, 1, 2, false)
        val element6 = ElementOrientation("white", PieceType.CORNER, 1, 8, 3, false)
        dbService.addElementOrientation(element1)
        dbService.addElementOrientation(element2)
        dbService.addElementOrientation(element3)
        dbService.addElementOrientation(element4)
        dbService.addElementOrientation(element5)
        dbService.addElementOrientation(element6)

        val retrievedElements = dbService.getElementOrientationItemsForPiece(1, PieceType.CORNER)

        assertTrue(retrievedElements.contains(element1))
        assertTrue(retrievedElements.contains(element3))
        assertTrue(retrievedElements.contains(element6))
        assertTrue(retrievedElements.size == 3)
    }

    @Test
    fun getElementOrientationsForPieceNoElementsForPiece(){
        val element1 = ElementOrientation("yellow", PieceType.CORNER, 1, 1, 1, false)
        val element2 = ElementOrientation("yellow", PieceType.EDGE, 2, 1, 2, false)
        val element3 = ElementOrientation("yellow", PieceType.CORNER, 1, 4, 3, true)
        val element4 = ElementOrientation("white", PieceType.EDGE, 1, 1, 1, false)
        val element5 = ElementOrientation("white", PieceType.CORNER, 4, 1, 2, false)
        val element6 = ElementOrientation("white", PieceType.CORNER, 1, 8, 3, false)
        dbService.addElementOrientation(element1)
        dbService.addElementOrientation(element2)
        dbService.addElementOrientation(element3)
        dbService.addElementOrientation(element4)
        dbService.addElementOrientation(element5)
        dbService.addElementOrientation(element6)

        val retrievedElements = dbService.getElementOrientationItemsForPiece(2, PieceType.CORNER)

        assertTrue(retrievedElements.isEmpty())
    }

    private fun assertElementExists(cursor: Cursor, element: ElementOrientation) {
        with(cursor) {
            cursor.use {
                assertTrue(moveToFirst())
                assertEquals(
                    element.pieceNumber,
                    getInt(getColumnIndexOrThrow(ElementDatabaseConstants.ElementOrientationTable.PIECE_NUMBER_COLUMN))
                )
                assertEquals(
                    element.pieceType.ordinal,
                    getInt(getColumnIndexOrThrow(ElementDatabaseConstants.ElementOrientationTable.PIECE_TYPE_COLUMN))
                )
                assertEquals(
                    element.pieceNumber,
                    getInt(getColumnIndexOrThrow(ElementDatabaseConstants.ElementOrientationTable.PIECE_NUMBER_COLUMN))
                )
                assertEquals(
                    element.sideName,
                    getString(getColumnIndexOrThrow(ElementDatabaseConstants.ElementOrientationTable.SIDE_NAME_COLUMN))
                )

                assertEquals(
                    element.piecePosition,
                    getInt(getColumnIndexOrThrow(ElementDatabaseConstants.ElementOrientationTable.PIECE_POSITION_COLUMN))
                )
                assertEquals(
                    element.pieceOrientation,
                    getInt(getColumnIndexOrThrow(ElementDatabaseConstants.ElementOrientationTable.PIECE_ORIENTATION_COLUMN))
                )
                if (element.sideRelativeOrientation != null) {
                    assertEquals(
                        if (element.sideRelativeOrientation!!) 1 else 0,
                        getInt(getColumnIndexOrThrow(ElementDatabaseConstants.ElementOrientationTable.SIDE_RELATIVE_ORIENTATION_COLUMN))
                    )
                } else {
                    println("sideRelativeOrientation is null")
                    fail()
                }
            }
        }
    }
}