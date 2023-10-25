package de.calorieme.modules.calcalorien

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import de.calorieme.R

class SummaryResourceActivity : Activity() , SensorEventListener{

    lateinit var progressBar: ProgressBar
    lateinit var tv_steptaken: TextView
    var i = 0

    private var sensormanager : SensorManager? = null
    private var running = false
    private var totalStep =0f
    private var previousTotalStep =0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circular_progress)

        progressBar = findViewById(R.id.progress_circular)
        tv_steptaken = findViewById(R.id.tv_steptaken)

//        Handler().postDelayed(object : Runnable {
//            override fun run() {
//                // set the limitations for the numeric
//                // text under the progress bar
//                if (i <= 100) {
//                    tv_steptaken.text = "" + i
//                    progressBar.progress = i
//                    i++
//                    Handler().postDelayed(this, 200)
//                } else {
//                    Handler().removeCallbacks(this)
//                }
//            } }, 200)


//        step running

        sensormanager= getSystemService(Context.SENSOR_SERVICE) as SensorManager

    }



    override fun onResume() {
        super.onResume()
        running = true
        val stepSensor = sensormanager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        if(stepSensor == null)
            Toast.makeText(this, "No sensor detected on this device",Toast.LENGTH_LONG).show()
        else
            sensormanager?.registerListener(this,stepSensor,SensorManager.SENSOR_DELAY_UI)

    }


    override fun onSensorChanged(event: SensorEvent?) {
        if(running){
            totalStep = event!!.values[0]
            val curentStep = totalStep.toInt() - previousTotalStep.toInt()
            tv_steptaken.text = ("$curentStep")
            progressBar.apply {
                setProgress(curentStep.toInt())
            }
        }
    }

    fun resetStep() {
        tv_steptaken.setOnClickListener {
            Toast.makeText(this, "No sensor detected on this device", Toast.LENGTH_LONG).show()
        }
        tv_steptaken.setOnLongClickListener{
            previousTotalStep = totalStep
            tv_steptaken.text= 0.toString()
            saveDate()
            true
        }
    }

    private fun saveDate() {

    }


    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    companion object {
        fun open(activity: Activity?) {
            val intent = Intent(activity, SummaryResourceActivity::class.java)
            activity!!.startActivity(intent)
        }
    }
}