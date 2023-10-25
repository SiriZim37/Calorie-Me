package de.calorieme.modules.calcalorien

import android.content.Context
import android.content.Intent
import android.hardware.SensorManager
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import de.calorieme.R
import kotlinx.android.synthetic.main.activity_bmi.*
import kotlinx.android.synthetic.main.item_healthymenu.view.icon_type

class BmiActivity  : AppCompatActivity(){

    lateinit var progressBar: ProgressBar
    var i = 0

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(BmiViewModel::class.java)
    }

    private val data_id by lazy {
        intent.getStringExtra(GENDER_ID) ?: ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi)


        initInstances()
        initViewModel()

    }

    fun  initInstances(){

        progressBar = findViewById(R.id.progress_circular)
        progress_layout.visibility = View.GONE

        if(data_id == "F"){
            img_fm.visibility = View.VISIBLE
            img_m.visibility = View.GONE
        } else if(data_id == "M") {
            img_m.visibility = View.VISIBLE
            img_fm.visibility = View.GONE
        }

        var value: Float = 0f

        s1.setOnCheckedChangeListener { compoundButton, b ->
            value = 1.20f
        }

        s2.setOnCheckedChangeListener { compoundButton, b ->
            value = 1.375f
        }

        s3.setOnCheckedChangeListener { compoundButton, b ->
            value = 1.55f
        }

        s4.setOnCheckedChangeListener { compoundButton, b ->
            value = 1.725f
        }

        s5.setOnCheckedChangeListener { compoundButton, b ->
            value = 1.90f
        }

        calculate.setOnClickListener {
            var height = height_ET.text.toString()
            var weight = weight_ET.text.toString()
            var age = age_ET.text.toString()
            if( height == "" || weight == "" || age == "" || value ==  0f){
                Toast.makeText(this, "Please fill all your information", Toast.LENGTH_LONG).show()
            }else{
                viewModel.calculationCalorien( data_id!! , height.toFloat() ,weight.toFloat()  , age.toFloat() , value)
            }

        }

    }

    fun  initViewModel(){
        viewModel.whenDataLoadCal.observe(this , Observer {
            it?.let {
               summProgressbar(it)
            }
        })
    }

    fun summProgressbar( sum : String){
        progress_layout.visibility = View.VISIBLE
        Handler().postDelayed(object : Runnable {
            override fun run() {
                // set the limitations for the numeric
                // text under the progress bar
                if (i <= 100) {
                    progressBar.progress = i
                    i++
                    Handler().postDelayed(this, 20)
                } else {
                    tv_bmisum.text = "" + sum
                    Handler().removeCallbacks(this)
                }
            } }, 200)


    }

    companion object {
        const val GENDER_ID = "GENDER_ID"
        fun start(context: Context?,
                  gender: String ) {
            val intent = Intent(context, BmiActivity::class.java)
            intent.putExtra(GENDER_ID, gender)
            context?.startActivity(intent)
        }
    }
}
