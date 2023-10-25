package de.calorieme.modules.calcalorien

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import de.calorieme.R
import kotlinx.android.synthetic.main.activity_cal_calorien.*

class MainCalSelectGenderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cal_calorien)

        male_bt.setOnClickListener {
            BmiActivity.start(this , "M")
//            val intent = Intent(this, MaleBmiActivity::class.java)
//            startActivity(intent)
        }
        female_bt.setOnClickListener {
            BmiActivity.start(this ,"F" )
//            val intent = Intent(this, FemaleBmiActivity::class.java)
//            startActivity(intent)
        }



    }

    companion object {
        fun start(context: Context?  ) {
            val intent = Intent(context, MainCalSelectGenderActivity::class.java)
            context?.startActivity(intent)
        }
    }
}