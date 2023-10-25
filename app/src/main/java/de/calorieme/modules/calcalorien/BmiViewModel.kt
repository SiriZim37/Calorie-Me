package de.calorieme.modules.calcalorien

import androidx.lifecycle.MutableLiveData
import de.calorieme.R
import de.calorieme.common.base.BaseViewModel
import java.math.RoundingMode
import java.text.DecimalFormat

class BmiViewModel : BaseViewModel()  {

  //  val whenDataLoadItem = MutableLiveData<List<MenuItem>>()
    val whenDataLoadCal = MutableLiveData<String>()

    fun calculationCalorien( gender : String , height: Float,weight: Float  , age: Float , value : Float){
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

        if(gender == "F"){
            val bmr = (weight*10)+(6.25*height)-(5*age)-161
            val goal = df.format(bmr*value)
            whenDataLoadCal.postValue(goal.toString())
        } else if(gender == "M") {
            val bmr = (weight*10)+(6.25*height)+5-(5*age)
            val goal = df.format(bmr*value)
            whenDataLoadCal.postValue(goal.toString())
        }

    }


}