# Calories-Me

# Example source code 
Example source code for calculate BMI (Body Mass Index) & Calorie per Day

The BMI calculator analyzes the data received from the user and displays relevant information about the correct weight.

## Development Roadmap

- [x] [Kotlin](https://kotlinlang.org/)
- [x] [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)


## Features 

- [x] Calorie calculator 
- [ ] Food Info & Recommend Food 
- [ ] Fitness media
- [ ] Healthy Care

Set NumberPicker for select Age , Height , Weight


.xml
```
<NumberPicker
                android:id="@+id/filter_picker_age"
                android:layout_width="70dp"
                android:layout_height="150dp"
                android:descendantFocusability="blocksDescendants"
                android:orientation="horizontal"/>

```

BmiActivity.kt
```

                filter_picker_age.setOnValueChangedListener { picker, oldVal, newVal ->
//              Toast.makeText(this, newVal, Toast.LENGTH_LONG).show()
        }

```

Android ProgressBar using Kotlin


.xml
```
 <ProgressBar
                android:id="@+id/progress_circular_cal"
                style="?android:attr/progressBarStyleHorizontal"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:background="@drawable/circular_shape"
                android:indeterminate="false"
                android:progressDrawable="@drawable/circular_progress_bar"
                android:textAlignment="center" />
```


BmiActivity.kt
```
        Handler().postDelayed(object : Runnable {
            override fun run() {
                // set the limitations for the numeric
                // text under the progress bar
                if (i <= 100) {
                    progressBarBMI.progress = i
                    i++
                    Handler().postDelayed(this, 100)
                } else {
                    tv_bmisum.text = "" + sum.score
                    Handler().removeCallbacks(this)
                }
            } }, 200)
```           

![ezcv logo](https://github.com/SiriZim37/Calorie-Me/blob/main/ImageCalorieMe/calorie_menu1.png)
![ezcv logo](https://github.com/SiriZim37/Calorie-Me/blob/main/ImageCalorieMe/calorie_menu3.png)



