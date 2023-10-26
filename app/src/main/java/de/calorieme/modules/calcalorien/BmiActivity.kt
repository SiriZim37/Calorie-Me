package de.calorieme.modules.calcalorien

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import de.calorieme.R
import kotlinx.android.synthetic.main.activity_bmi.*


class BmiActivity  : AppCompatActivity(){

    lateinit var progressBarCal: ProgressBar
    lateinit var progressBarBMI: ProgressBar
    var i = 0
    var aGE = 0
    var hEIGHT = 0
    var wEIGHT = 0
    var gendertype : String= ""

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

        progressBarCal = findViewById(R.id.progress_circular_cal)
        progressBarBMI = findViewById(R.id.progress_bmi)
        progress_layout1.visibility = View.GONE
        configPickerNummer()

        if(data_id == "F"){
            img_fm.visibility = View.VISIBLE
//            img_m.visibility = View.GONE
        } else if(data_id == "M") {
//            img_m.visibility = View.VISIBLE
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


        select_fm.setOnClickListener {
            gendertype = "F"
            li_fm.setBackgroundColor(ContextCompat.getColor(this@BmiActivity, R.color.teal_700))
            li_m.setBackgroundColor(ContextCompat.getColor(this@BmiActivity, R.color.white))
        }
        select_m.setOnClickListener {
            gendertype = "M"
            li_m.setBackgroundColor(ContextCompat.getColor(this@BmiActivity, R.color.teal_700))
            li_fm.setBackgroundColor(ContextCompat.getColor(this@BmiActivity, R.color.white))
        }

        calculate.setOnClickListener {

            if( hEIGHT.toString()  == ""  || hEIGHT == null
                || wEIGHT.toString()  == "" || wEIGHT == null
                || aGE.toString() == "" || aGE == null
                || value ==  0f || value == null
                || gendertype == "" || gendertype == null  ){
                Toast.makeText(this, "Please fill all your information", Toast.LENGTH_LONG).show()
            }else{
                viewModel.calculationCalorien( gendertype!! , hEIGHT.toFloat() ,wEIGHT.toFloat()  , aGE.toFloat() , value)

            }

        }

    }

    fun configPickerNummer(){

        filter_picker_age.maxValue = 99
        filter_picker_age.minValue = 0
        filter_picker_age.value = 10

        filter_picker_height.maxValue = 300
        filter_picker_height.minValue = 0
        filter_picker_height.value = 150

        filter_picker_weight.maxValue = 400
        filter_picker_weight.minValue = 0
        filter_picker_weight.value = 45


        filter_picker_age.setOnValueChangedListener { picker, oldVal, newVal ->
            aGE = newVal
//            Toast.makeText(this, aGE.toString(), Toast.LENGTH_LONG).show()
        }

        filter_picker_height.setOnValueChangedListener { picker, oldVal, newVal ->
            hEIGHT = newVal
//            Toast.makeText(this, hEIGHT.toString(), Toast.LENGTH_LONG).show()
        }

        filter_picker_weight.setOnValueChangedListener { picker, oldVal, newVal ->
            wEIGHT = newVal
//            Toast.makeText(this, hEIGHT.toString(), Toast.LENGTH_LONG).show()
        }
    }

    fun  initViewModel(){
        viewModel.whenDataLoadCal.observe(this , Observer {
            it?.let {
                summCalProgressbar(it)

            }
        })

        viewModel.whenDataLoadBMI.observe(this , {
            it?.let {
                if(it.rEsult == "")
                    Toast.makeText(this, "Please fill all your information", Toast.LENGTH_LONG).show()
               else
                   summBMIProgressbar(it)

            }
        })
    }

    fun summCalProgressbar( sum : String){
        progress_layout1.visibility = View.VISIBLE

        Handler().postDelayed(object : Runnable {
            override fun run() {
                // set the limitations for the numeric
                // text under the progress bar
                if (i <= 100) {
                    progressBarCal.progress = i
                    i++
                    Handler().postDelayed(this, 10)
                } else {
                    tv_calosum.text = "" + sum
                    Handler().removeCallbacks(this)
                }
            } }, 200)
//        progress_layout.post { progress_circular_cal.requestFocus() }
    }

    fun summBMIProgressbar( sum : BmiViewModel.bmiResult){
        progress_layout1.visibility = View.VISIBLE
        Handler().postDelayed(object : Runnable {
            override fun run() {
                // set the limitations for the numeric
                // text under the progress bar
                if (i <= 100) {
                    progressBarBMI.progress = i
                    i++
                    Handler().postDelayed(this, 10)
                } else {
                    tv_bmisum.text = "" + sum.score
                    tv_bmires.text = "" + sum.rEsult
                    Handler().removeCallbacks(this)
                }
            } }, 200)
        calculate.post { calculate.requestFocus() }
    }

    companion object {
        const val GENDER_ID = "GENDER_ID"
        fun start(context: Context?,
                  gender: String ) {
            val intent = Intent(context, BmiActivity::class.java)
            intent.putExtra(GENDER_ID, gender)
            context?.startActivity(intent)
        }

        fun startWithOutData(context: Context?) {
            val intent = Intent(context, BmiActivity::class.java)
            context?.startActivity(intent)
        }
    }
}
