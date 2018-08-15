package com.example.personal.sensorlistsample

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SensorEventListener {


    lateinit var sensorManager: SensorManager
    lateinit var sensorLuz: Sensor
    lateinit var sensorProximidad: Sensor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        var sensorNames = StringBuilder()

        sensorProximidad = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        sensorLuz = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        val error = "Sensor no disponible"
        if (sensorProximidad == null) labelProximidad.text = error
        if (sensorLuz == null) labelLuz.text = error


    }

    override fun onStart() {
        super.onStart()
        if (sensorProximidad != null) sensorManager.registerListener(this, sensorProximidad, SensorManager.SENSOR_DELAY_NORMAL)
        if (sensorLuz != null) sensorManager.registerListener(this, sensorLuz, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onStop() {
        super.onStop()
        sensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }

    override fun onSensorChanged(p0: SensorEvent?) {
        when (p0!!.sensor.type) {
            Sensor.TYPE_LIGHT -> {
                labelLuz.text = resources.getString(R.string.luz_label,p0.values[0])
                if(p0.values[0]<100f){
                    window.decorView.setBackgroundColor(resources.getColor(R.color.bg_black))
                    labelProximidad.setTextColor(resources.getColor(R.color.text_white))
                    labelLuz.setTextColor(resources.getColor(R.color.text_white))
                }else{
                    window.decorView.setBackgroundColor(resources.getColor(R.color.bg_white))
                    labelProximidad.setTextColor(resources.getColor(R.color.text_black))
                    labelLuz.setTextColor(resources.getColor(R.color.text_black))
                }
            }
            else -> labelProximidad.text = resources.getString(R.string.proximidad_label,p0.values[0])
        }
    }
}
