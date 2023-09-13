package com.example.smartcubeapp.elementorientationtests

import android.content.Context
import android.database.Cursor
import androidx.test.platform.app.InstrumentationRegistry
import com.example.smartcubeapp.cube.piece.ElementOrientation
import com.example.smartcubeapp.cube.piece.Orientation
import com.example.smartcubeapp.cube.piece.PieceType
import com.example.smartcubeapp.elementdatabase.ElementDatabaseConstants
import com.example.smartcubeapp.elementdatabase.casedetectiondatabase.CaseElementOrientationDBService
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Test

class CaseElementOrientationDBServiceTests {

    private lateinit var appContext: Context
    private lateinit var dbService: CaseElementOrientationDBService

    @Before
    fun setUp() {
        appContext = InstrumentationRegistry.getInstrumentation().targetContext
        dbService =
            CaseElementOrientationDBService(appContext, ElementDatabaseConstants.TEST_DATABASE_NAME)
        dbService.createElementOrientationTable(dbService.writableDatabase)
    }

    @After
    fun tearDown() {
        dbService.close()
        appContext.deleteDatabase(ElementDatabaseConstants.TEST_DATABASE_NAME)
    }

    @Test
    fun addElementOrientationTest() {
        val element = ElementOrientation("yellow", PieceType.CORNER, 1, 1, 1, Orientation.OneRotation)
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

        TestCase.assertTrue(cursor.count == 0)
    }

    @Test
    fun updateElementOrientationTest() {
        val element = ElementOrientation("yellow", PieceType.CORNER, 1, 1, 1, Orientation.OneRotation)
        dbService.addElementOrientation(element)

        val updatedElement =
            ElementOrientation("yellow", PieceType.CORNER, 1, 1, 1, Orientation.TwoRotations)
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
        val element = ElementOrientation("yellow", PieceType.CORNER, 1, 1, 1, Orientation.TwoRotations)
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
        val element = ElementOrientation("yellow", PieceType.CORNER, 1, 1, 1, Orientation.OneRotation)
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

        TestCase.assertTrue(cursor.count == 0)
    }

    @Test
    fun getElementOrientationTest() {
        val element = ElementOrientation("yellow", PieceType.CORNER, 1, 1, 1, Orientation.TwoRotations)
        dbService.addElementOrientation(element)

        val retrievedElement = dbService.getElementOrientation(element)

        TestCase.assertEquals(element, retrievedElement)
    }

    @Test
    fun getElementOrientationFail() {
        val element = ElementOrientation("yellow", PieceType.CORNER, 1, 1, 1, Orientation.Correct)
        dbService.addElementOrientation(element)

        val retrievedElement = dbService.getElementOrientation(
            ElementOrientation(
                "yellow",
                PieceType.CORNER,
                1,
                1,
                2,
                Orientation.Incorrect
            )
        )

        TestCase.assertEquals(null, retrievedElement)
    }

    @Test
    fun getAllElementOrientationItems() {
        val element1 =
            ElementOrientation("yellow", PieceType.CORNER, 1, 1, 1, Orientation.Incorrect)
        val element2 =
            ElementOrientation("yellow", PieceType.CORNER, 1, 1, 2, Orientation.OneRotation)
        val element3 =
            ElementOrientation("yellow", PieceType.CORNER, 1, 1, 3, Orientation.TwoRotations)
        dbService.addElementOrientation(element1)
        dbService.addElementOrientation(element2)
        dbService.addElementOrientation(element3)

        val retrievedElements = dbService.getAllElementOrientationItems()

        TestCase.assertTrue(retrievedElements.contains(element1))
        TestCase.assertTrue(retrievedElements.contains(element2))
        TestCase.assertTrue(retrievedElements.contains(element3))
    }

    @Test
    fun getAllElementOrientationItemsNoItemsInDatabase() {
        val retrievedElements = dbService.getAllElementOrientationItems()

        TestCase.assertTrue(retrievedElements.isEmpty())
    }

    @Test
    fun getElementsOrientationItemBySide() {
        val element1 =
            ElementOrientation("yellow", PieceType.CORNER, 1, 1, 1, Orientation.Incorrect)
        val element2 =
            ElementOrientation("yellow", PieceType.CORNER, 1, 1, 2, Orientation.OneRotation)
        val element3 =
            ElementOrientation("yellow", PieceType.CORNER, 1, 1, 3, Orientation.TwoRotations)
        val element4 = ElementOrientation("white", PieceType.CORNER, 1, 1, 1, Orientation.Correct)
        val element5 = ElementOrientation("white", PieceType.CORNER, 1, 1, 2, Orientation.TwoRotations)
        val element6 = ElementOrientation("white", PieceType.CORNER, 1, 1, 3, Orientation.OneRotation)
        dbService.addElementOrientation(element1)
        dbService.addElementOrientation(element2)
        dbService.addElementOrientation(element3)
        dbService.addElementOrientation(element4)
        dbService.addElementOrientation(element5)
        dbService.addElementOrientation(element6)

        val retrievedElements = dbService.getElementOrientationItemsBySide("yellow")

        TestCase.assertTrue(retrievedElements.contains(element1))
        TestCase.assertTrue(retrievedElements.contains(element2))
        TestCase.assertTrue(retrievedElements.contains(element3))
        TestCase.assertTrue(retrievedElements.size == 3)
    }

    @Test
    fun getElementOrientationItemsBySideNoElementsWithSideName() {
        val element1 =
            ElementOrientation("yellow", PieceType.CORNER, 1, 1, 1, Orientation.Incorrect)
        val element2 =
            ElementOrientation("yellow", PieceType.CORNER, 1, 1, 2, Orientation.OneRotation)
        val element3 =
            ElementOrientation("yellow", PieceType.CORNER, 1, 1, 3, Orientation.Correct)
        val element4 = ElementOrientation("white", PieceType.CORNER, 1, 1, 1, Orientation.TwoRotations)
        val element5 = ElementOrientation("white", PieceType.CORNER, 1, 1, 2, Orientation.Incorrect)
        val element6 = ElementOrientation("white", PieceType.CORNER, 1, 1, 3, Orientation.Correct)
        dbService.addElementOrientation(element1)
        dbService.addElementOrientation(element2)
        dbService.addElementOrientation(element3)
        dbService.addElementOrientation(element4)
        dbService.addElementOrientation(element5)
        dbService.addElementOrientation(element6)

        val retrievedElements = dbService.getElementOrientationItemsBySide("red")

        TestCase.assertTrue(retrievedElements.isEmpty())
    }

    private fun assertElementExists(cursor: Cursor, element: ElementOrientation) {
        with(cursor) {
            cursor.use {
                TestCase.assertTrue(moveToFirst())
                TestCase.assertEquals(
                    element.pieceNumber,
                    getInt(getColumnIndexOrThrow(ElementDatabaseConstants.ElementOrientationTable.PIECE_NUMBER_COLUMN))
                )
                TestCase.assertEquals(
                    element.pieceType.ordinal,
                    getInt(getColumnIndexOrThrow(ElementDatabaseConstants.ElementOrientationTable.PIECE_TYPE_COLUMN))
                )
                TestCase.assertEquals(
                    element.pieceNumber,
                    getInt(getColumnIndexOrThrow(ElementDatabaseConstants.ElementOrientationTable.PIECE_NUMBER_COLUMN))
                )
                TestCase.assertEquals(
                    element.sideName,
                    getString(getColumnIndexOrThrow(ElementDatabaseConstants.ElementOrientationTable.SIDE_NAME_COLUMN))
                )

                TestCase.assertEquals(
                    element.piecePosition,
                    getInt(getColumnIndexOrThrow(ElementDatabaseConstants.ElementOrientationTable.PIECE_POSITION_COLUMN))
                )
                TestCase.assertEquals(
                    element.pieceOrientation,
                    getInt(getColumnIndexOrThrow(ElementDatabaseConstants.ElementOrientationTable.PIECE_ORIENTATION_COLUMN))
                )
                if (element.sideRelativeOrientation != null) {
                    TestCase.assertEquals(
                        when (element.sideRelativeOrientation) {
                            Orientation.Correct -> 0
                            Orientation.OneRotation -> 1
                            Orientation.TwoRotations -> 2
                            else -> 3
                        },
                        getInt(getColumnIndexOrThrow(ElementDatabaseConstants.ElementOrientationTable.SIDE_RELATIVE_ORIENTATION_COLUMN))
                    )
                } else {
                    println("sideRelativeOrientation is null")
                    TestCase.fail()
                }
            }
        }
    }
}