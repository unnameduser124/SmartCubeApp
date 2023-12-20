package com.example.smartcubeapp.correctnesstests.dbtests.solvedatabasetests

import android.content.Context
import android.provider.BaseColumns
import androidx.test.platform.app.InstrumentationRegistry
import com.example.smartcubeapp.bluetooth.CubeDevice
import com.example.smartcubeapp.solvedatabase.SolvesDatabaseConstants
import com.example.smartcubeapp.solvedatabase.services.DeviceDBService
import junit.framework.TestCase
import junit.framework.TestCase.assertNull
import org.junit.After
import org.junit.Before
import org.junit.Test

class DeviceDBServiceTests {

    private lateinit var context: Context
    private lateinit var deviceDBService: DeviceDBService

    @Before
    fun setUp(){
        context = InstrumentationRegistry.getInstrumentation().targetContext
        deviceDBService = DeviceDBService(context, SolvesDatabaseConstants.TEST_DATABASE_NAME)
    }

    @After
    fun tearDown(){
        deviceDBService.close()
        context.deleteDatabase(SolvesDatabaseConstants.TEST_DATABASE_NAME)
    }

    @Test
    fun addDeviceTest(){
        val device = CubeDevice("test_name", "test_address")

        val id = deviceDBService.addDevice(device)

        val projection = arrayOf(
            SolvesDatabaseConstants.DeviceTable.DEVICE_NAME_COLUMN,
            SolvesDatabaseConstants.DeviceTable.DEVICE_ADDRESS_COLUMN
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
        with(cursor){
            if(moveToFirst()){
                val name = getString(getColumnIndexOrThrow(SolvesDatabaseConstants.DeviceTable.DEVICE_NAME_COLUMN))
                val address = getString(getColumnIndexOrThrow(SolvesDatabaseConstants.DeviceTable.DEVICE_ADDRESS_COLUMN))

                assert(name == device.name)
                assert(address == device.address)
            }
            else{
                TestCase.fail()
            }
        }
    }

    @Test
    fun addDeviceTestFailEmptyName(){
        val device = CubeDevice("", "test_address")

        val id = deviceDBService.addDevice(device)

        assert(id == -1L)
    }

    @Test
    fun addDeviceTestFailEmptyAddress(){
        val device = CubeDevice("test_name", "")

        val id = deviceDBService.addDevice(device)

        assert(id == -1L)
    }

    @Test
    fun getDeviceTest(){
        val device = CubeDevice("test_name", "test_address")
        val id = deviceDBService.addDevice(device)

        val result = deviceDBService.getDevice(id)

        assert(result != null)
        assert(result!!.name == device.name)
        assert(result.address == device.address)
    }

    @Test
    fun getDeviceTestFailInvalidID(){
        val device = CubeDevice("test_name", "test_address")
        val id = deviceDBService.addDevice(device)

        val result = deviceDBService.getDevice(id + 1)

        assertNull(result)
    }

    @Test
    fun removeDeviceTest(){
        val device = CubeDevice("test_name", "test_address")
        val id = deviceDBService.addDevice(device)

        deviceDBService.removeDevice(id)

        val getDevice = deviceDBService.getDevice(id)
        assertNull(getDevice)
    }

    @Test
    fun removeDeviceTestFailInvalidID(){
        val device = CubeDevice("test_name", "test_address")
        val id = deviceDBService.addDevice(device)

        deviceDBService.removeDevice(id + 1)

        val getDevice = deviceDBService.getDevice(id)
        assert(getDevice != null)
        assert(getDevice!!.name == device.name)
        assert(getDevice.address == device.address)
    }

    @Test
    fun updateDeviceTest(){
        val device = CubeDevice("test_name", "test_address")
        val id = deviceDBService.addDevice(device)

        val newDevice = CubeDevice("new_name", "new_address")
        deviceDBService.updateDevice(newDevice, id)

        val getDevice = deviceDBService.getDevice(id)
        assert(getDevice != null)
        assert(getDevice!!.name == newDevice.name)
        assert(getDevice.address == newDevice.address)
    }

    @Test
    fun updateDeviceTestFailEmptyName(){
        val device = CubeDevice("test_name", "test_address")
        val id = deviceDBService.addDevice(device)

        val newDevice = CubeDevice("", "new_address")
        deviceDBService.updateDevice(newDevice, id)

        val getDevice = deviceDBService.getDevice(id)
        assert(getDevice != null)
        assert(getDevice!!.name == device.name)
        assert(getDevice.address == device.address)
    }

    @Test
    fun updateDeviceTestFailEmptyAddress(){
        val device = CubeDevice("test_name", "test_address")
        val id = deviceDBService.addDevice(device)

        val newDevice = CubeDevice("new_name", "")
        deviceDBService.updateDevice(newDevice, id)

        val getDevice = deviceDBService.getDevice(id)
        assert(getDevice != null)
        assert(getDevice!!.name == device.name)
        assert(getDevice.address == device.address)
    }

    @Test
    fun updateDeviceTestFailInvalidID(){
        val device = CubeDevice("test_name", "test_address")
        val id = deviceDBService.addDevice(device)

        val newDevice = CubeDevice("new_name", "new_address")
        deviceDBService.updateDevice(newDevice, id + 1)

        val getDevice = deviceDBService.getDevice(id)
        assert(getDevice != null)
        assert(getDevice!!.name == device.name)
        assert(getDevice.address == device.address)
    }
}