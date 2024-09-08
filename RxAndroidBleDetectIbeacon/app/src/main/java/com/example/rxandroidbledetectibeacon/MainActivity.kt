package com.example.rxandroidbledetectibeacon

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import com.polidea.rxandroidble3.RxBleClient
import com.polidea.rxandroidble3.scan.ScanFilter
import com.polidea.rxandroidble3.scan.ScanSettings
import org.altbeacon.beacon.BeaconManager
import org.altbeacon.beacon.BeaconParser
import org.altbeacon.beacon.Region
import java.nio.ByteBuffer
import java.util.UUID

class MainActivity : AppCompatActivity() {
    private var PERMISSION_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        requestBleScanPermission()
    }

    private fun requestBleScanPermission() {
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
                // Android 12及以上
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(arrayOf(Manifest.permission.BLUETOOTH_SCAN, Manifest.permission.BLUETOOTH_CONNECT, Manifest.permission.ACCESS_FINE_LOCATION), PERMISSION_REQUEST_CODE)
                } else {
                    startScanBle()
                }
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                // Android 6.0 到 Android 11
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_ADMIN) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.BLUETOOTH, Manifest.permission.BLUETOOTH_ADMIN), PERMISSION_REQUEST_CODE)
                } else {
                    startScanBle()
                }
            }
            else -> {
                // 低于 Android 6.0
                startScanBle()
            }
        }
    }

//    private  fun rxAndroidBleCannotDetectIbeacon(){
//        RxBleClient.create(this)
//            .scanBleDevices(
//                ScanSettings.Builder().setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY).build(),
//                ScanFilter.Builder().build()
//            )
//            .subscribe( { scanResult ->
//                val bleDevice = scanResult.bleDevice
//                val rssi = scanResult.rssi
//                val scanRecord = scanResult.scanRecord
//
//                // 解析 iBeacon 数据：9字节开头；UUID 16字节；Major 2字节；Minor 2字节；TxPower 1字节
////                val manufacturerData = scanRecord?.getManufacturerSpecificData(0x004C)
//                val manufacturerData = scanRecord?.bytes
//
////                val manufacturerData = scanRecord?.manufacturerSpecificData
//                var str = ""
//
//                manufacturerData?.forEach {
//                    str += it.toString()
//                    str += ","
//                }
//                Log.w("manufacturerData", str)
//
//                if (manufacturerData != null && manufacturerData.size >= 23) {
//
//                    val proximityUuid = UUID(
//                        ByteBuffer.wrap(manufacturerData, 2, 8).long,
//                        ByteBuffer.wrap(manufacturerData, 10, 8).long
//                    )
//
//                    val major = ((manufacturerData[18].toInt() and 0xff) shl 8) or (manufacturerData[19].toInt() and 0xff)
//                    val minor = ((manufacturerData[20].toInt() and 0xff) shl 8) or (manufacturerData[21].toInt() and 0xff)
//                    val txPower = manufacturerData[22].toInt()
//
//
//
//                    // 在此处理 iBeacon 数据
//                    Log.d("iBeacon", "UUID: $proximityUuid, Major: $major, Minor: $minor, TxPower: $txPower, RSSI: $rssi")
//                }
//            },{
//                Log.e("iBeacon", "Error during scanning")
//            })
//
//    }

    private fun startScanBle() {
//        rxAndroidBleCannotDetectIbeacon()

        val beaconMnger = BeaconManager.getInstanceForApplication(this).apply {
            beaconParsers.clear()
            val ibeaconParser = BeaconParser().apply {
                setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24")
            }
            beaconParsers.add(ibeaconParser)
        }

        beaconMnger.addRangeNotifier { beacons, region ->
            for (beacon in beacons) {
                Log.d("iBeacon", "UUID: ${beacon.id1}, Major: ${beacon.id2}, Minor: ${beacon.id3}, RSSI: ${beacon.rssi}")
            }
        }

        //uuid是第二个参数设置
        val region = Region("myRangingUniqueId", null, null, null)
//        beaconMnger.startMonitoring(region)
        beaconMnger.startRangingBeacons(region)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                // All permissions granted
                startScanBle()
            } else {
                // Permission denied
                Toast.makeText(this, "权限被拒绝，请在设置中启用权限", Toast.LENGTH_SHORT).show()
            }
        }
    }

}