package com.example.personal.sensorlistsample

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var sensorManager: SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        var sensorNames = StringBuilder()

        var list = sensorManager.getSensorList(Sensor.TYPE_ALL)
        for (sensor in list){
            sensorNames.append(sensor.name).append(System.getProperty("line.separator"))
        }
        sensorList.text = sensorNames
    }
}
