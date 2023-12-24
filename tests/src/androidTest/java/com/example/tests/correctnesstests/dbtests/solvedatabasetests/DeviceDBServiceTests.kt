package com.example.tests.correctnesstests.dbtests.solvedatabasetests

import android.content.Context
import android.provider.BaseColumns
import androidx.test.platform.app.InstrumentationRegistry
import com.example.cube_cube.CubeDevice
import com.example.cube_database.solvedatabase.SolvesDatabaseConstants
import com.example.cube_database.solvedatabase.services.DeviceDBService
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.Calendar

class DeviceDBServiceTests {

    private lateinit var context: Context
    private lateinit var deviceDBService: DeviceDBService

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        deviceDBService = DeviceDBService(context, SolvesDatabaseConstants.TEST_DATABASE_NAME)
    }

    @After
    fun tearDown() {
        deviceDBService.close()
        context.deleteDatabase(SolvesDatabaseConstants.TEST_DATABASE_NAME)
    }

    @Test
    fun addDeviceTest() {
        val device = CubeDevice("test_name", "test_address")

        val id = deviceDBService.addDevice(device)

        val projection = arrayOf(
            SolvesDatabaseConstants.DeviceTable.DEVICE_NAME_COLUMN,
            SolvesDatabaseConstants.DeviceTable.DEVICE_ADDRESS_COLUMN,
            SolvesDatabaseConstants.DeviceTable.LAST_CONNECTION_TIME_COLUMN
        )
        val selection = "${BaseColumns._ID} = ?"
        val selectionArgs = arrayOf(id.toString())

        val cursor = deviceDBService.readableDatabase.query(
            SolvesDatabaseConstants.DeviceTable.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )
        with(cursor) {
            if (moveToFirst()) {
                val name =
                    getString(getColumnIndexOrThrow(SolvesDatabaseConstants.DeviceTable.DEVICE_NAME_COLUMN))
                val address =
                    getString(getColumnIndexOrThrow(SolvesDatabaseConstants.DeviceTable.DEVICE_ADDRESS_COLUMN))

                assert(name == device.name)
                assert(address == device.address)
            } else {
                TestCase.fail()
            }
        }
    }

    @Test
    fun addDeviceTestFailEmptyName() {
        val device = CubeDevice("", "test_address")

        val id = deviceDBService.addDevice(device)

        assert(id == -1L)
    }

    @Test
    fun addDeviceTestFailEmptyAddress() {
        val device = CubeDevice("test_name", "")

        val id = deviceDBService.addDevice(device)

        assert(id == -1L)
    }

    @Test
    fun addDeviceTestFailInvalidTime() {
        val device = CubeDevice("test_name", "test_address")
        device.lastConnectionTime.set(0, 0, 0, 0, 0, 0)

        val id = deviceDBService.addDevice(device)

        assert(id == -1L)
    }

    @Test
    fun getDeviceTest() {
        val device = CubeDevice("test_name", "test_address")
        val id = deviceDBService.addDevice(device)

        val result = deviceDBService.getDevice(id)

        assertNotNull(result)
        assertEquals(device.name, result!!.name)
        assertEquals(device.address, result.address)
        assertEquals(device.lastConnectionTime, result.lastConnectionTime)
    }

    @Test
    fun getDeviceTestFailInvalidID() {
        val device = CubeDevice("test_name", "test_address")
        val id = deviceDBService.addDevice(device)

        val result = deviceDBService.getDevice(id + 1)

        assertNull(result)
    }

    @Test
    fun removeDeviceTest() {
        val device = CubeDevice("test_name", "test_address")
        val id = deviceDBService.addDevice(device)

        deviceDBService.removeDevice(id)

        val getDevice = deviceDBService.getDevice(id)
        assertNull(getDevice)
    }

    @Test
    fun removeDeviceTestFailInvalidID() {
        val device = CubeDevice("test_name", "test_address")
        val id = deviceDBService.addDevice(device)

        deviceDBService.removeDevice(id + 1)

        val getDevice = deviceDBService.getDevice(id)
        assertNotNull(getDevice)
        assertEquals(device.name, getDevice!!.name)
        assertEquals(device.address, getDevice.address)
        assertEquals(device.lastConnectionTime, getDevice.lastConnectionTime)
    }

    @Test
    fun updateDeviceTest() {
        val device = CubeDevice("test_name", "test_address")
        val id = deviceDBService.addDevice(device)

        val newDevice = CubeDevice("new_name", "new_address")
        deviceDBService.updateDevice(newDevice, id)

        val getDevice = deviceDBService.getDevice(id)
        assertNotNull(getDevice)
        assertEquals(getDevice!!.name, newDevice.name)
        assertEquals(getDevice.address, newDevice.address)
        assertEquals(getDevice.lastConnectionTime, newDevice.lastConnectionTime)
    }

    @Test
    fun updateDeviceTestFailEmptyName() {
        val device = CubeDevice("test_name", "test_address")
        val id = deviceDBService.addDevice(device)

        val newDevice = CubeDevice("", "new_address")
        deviceDBService.updateDevice(newDevice, id)

        val getDevice = deviceDBService.getDevice(id)
        assertNotNull(getDevice)
        assertEquals(getDevice!!.name, device.name)
        assertEquals(getDevice.address, device.address)
        assertEquals(getDevice.lastConnectionTime, device.lastConnectionTime)
    }

    @Test
    fun updateDeviceTestFailEmptyAddress() {
        val device = CubeDevice("test_name", "test_address")
        val id = deviceDBService.addDevice(device)

        val newDevice = CubeDevice("new_name", "")
        deviceDBService.updateDevice(newDevice, id)

        val getDevice = deviceDBService.getDevice(id)
        assertNotNull(getDevice)
        assertEquals(getDevice!!.name, device.name)
        assertEquals(getDevice.address, device.address)
        assertEquals(getDevice.lastConnectionTime, device.lastConnectionTime)
    }

    @Test
    fun updateDeviceTestFailInvalidID() {
        val device = CubeDevice("test_name", "test_address")
        val id = deviceDBService.addDevice(device)

        val newDevice = CubeDevice("new_name", "new_address")
        deviceDBService.updateDevice(newDevice, id + 1)

        val getDevice = deviceDBService.getDevice(id)
        assertNotNull(getDevice)
        assertEquals(getDevice!!.name, device.name)
        assertEquals(getDevice.address, device.address)
        assertEquals(getDevice.lastConnectionTime, device.lastConnectionTime)
    }

    @Test
    fun updateDeviceFailInvalidTime() {
        val device = CubeDevice("test_name", "test_address")
        val id = deviceDBService.addDevice(device)

        val newDevice = CubeDevice("new_name", "new_address")
        newDevice.lastConnectionTime.set(0, 0, 0, 0, 0, 0)
        deviceDBService.updateDevice(newDevice, id)

        val getDevice = deviceDBService.getDevice(id)
        assertNotNull(getDevice)
        assertEquals(getDevice!!.name, device.name)
        assertEquals(getDevice.address, device.address)
        assertEquals(getDevice.lastConnectionTime, device.lastConnectionTime)
    }

    @Test
    fun getAllDevicesTest() {
        val device1 = CubeDevice("test_name1", "test_address1")
        val device2 = CubeDevice("test_name2", "test_address2")
        val device3 = CubeDevice("test_name3", "test_address3")
        val id1 = deviceDBService.addDevice(device1)
        val id2 = deviceDBService.addDevice(device2)
        val id3 = deviceDBService.addDevice(device3)

        val result = deviceDBService.getAllDevices()

        assertNotNull(result)
        assertEquals(3, result.size)
        assertEquals(id1, result[0].id)
        assertEquals(device1.name, result[0].name)
        assertEquals(device1.address, result[0].address)
        assertEquals(device1.lastConnectionTime, result[0].lastConnectionTime)
        assertEquals(id2, result[1].id)
        assertEquals(device2.name, result[1].name)
        assertEquals(device2.address, result[1].address)
        assertEquals(device2.lastConnectionTime, result[1].lastConnectionTime)
        assertEquals(id3, result[2].id)
        assertEquals(device3.name, result[2].name)
        assertEquals(device3.address, result[2].address)
        assertEquals(device3.lastConnectionTime, result[2].lastConnectionTime)
    }

    @Test
    fun getAllDevicesEmptyDatabase() {
        val result = deviceDBService.getAllDevices()

        assertNotNull(result)
        assertEquals(0, result.size)
    }

    @Test
    fun getLastDeviceTest() {
        val device1 = CubeDevice("test_name1", "test_address1")
        val device2 = CubeDevice("test_name2", "test_address2")
        val device3 = CubeDevice(
            "test_name3",
            "test_address3",
            Calendar.getInstance().apply { timeInMillis += 1000 }
        )
        deviceDBService.addDevice(device1)
        deviceDBService.addDevice(device2)
        val id3 = deviceDBService.addDevice(device3)

        val result = deviceDBService.getLastDevice()

        assertNotNull(result)
        assertEquals(id3, result!!.id)
        assertEquals(device3.name, result.name)
        assertEquals(device3.address, result.address)
        assertEquals(device3.lastConnectionTime, result.lastConnectionTime)
    }

    @Test
    fun getLastDeviceEmptyDatabase() {
        val result = deviceDBService.getLastDevice()

        assertNull(result)
    }
}