package de.calorieme.modules.calcalorien

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import de.calorieme.R
import de.calorieme.common.base.BaseViewModel
import java.lang.Exception
import java.math.RoundingMode
import java.text.DecimalFormat

class BmiViewModel : BaseViewModel() {

    //  val whenDataLoadItem = MutableLiveData<List<MenuItem>>()
    val whenDataLoadCal = MutableLiveData<String>()
    val whenDataLoadBMI = MutableLiveData<bmiResult>()

    fun calculationCalorien(
        gender: String,
        height: Float,
        weight: Float,
        age: Float,
        value: Float
    ) {
        /* HOW TO calculate calorien Male & Female
            Mifflin-St Jeor Equation:
            For men:
            BMR = 10W + 6.25H - 5A + 5
            For women:
            BMR = 10W + 6.25H - 5A - 161

            W is body weight in kg
            H is body height in cm
            A is age
            F is body fat in percentage
         */
        val df = DecimalFormat("#.###")
        df.roundingMode = RoundingMode.CEILING

        if (gender == "F") {
            val bmr = (weight * 10) + (6.25 * height) - (5 * age) - 161
            val goal = df.format(bmr * value)
            whenDataLoadCal.postValue(goal.toString())
        } else if (gender == "M") {
            val bmr = (weight * 10) + (6.25 * height) + 5 - (5 * age)
            val goal = df.format(bmr * value)
            whenDataLoadCal.postValue(goal.toString())
        }
        /// BMI percent
        val bmi = ((weight/(height*height))*10000)
        var dataBMI : bmiResult? = null

        when(bmi){
            in 0.0..18.5 -> {
                dataBMI =   bmiResult( "Under weight" , df.format(bmi).toString())
            }
            in 18.5..25.0 -> {
                dataBMI = bmiResult( "Normal" , df.format(bmi).toString())
            }
            in 25.0..30.0 -> {
                dataBMI = bmiResult( "Over weight" , df.format(bmi).toString())
            }
            in 30.0..35.0 -> {
                dataBMI = bmiResult( "Obese" , df.format(bmi).toString())
            }
            in 35.0..40.0 -> {
                dataBMI =bmiResult( "Severely obese" , df.format(bmi).toString())
            }
            in 40.0..Double.MAX_VALUE -> {
                dataBMI = bmiResult( "Very severely obese" , df.format(bmi).toString())
            }
        }
        try {
            whenDataLoadBMI.postValue(dataBMI!!)
        }catch (e : Exception){
            whenDataLoadBMI.postValue(bmiResult("",""))
        }


    }


    data class bmiResult(
        val  rEsult : String = "," ,
        val score  : String = ""
    )
}
