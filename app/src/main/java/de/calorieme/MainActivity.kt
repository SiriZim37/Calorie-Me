package de.calorieme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.add
import androidx.fragment.app.commit
import de.calorieme.modules.mainmenu.HealthyOptionMenuFragment

class MainActivity : AppCompatActivity() {
    private lateinit var male:Button
    private lateinit var female: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<HealthyOptionMenuFragment>(R.id.fragment_container_view)
            }
        }

    }
}