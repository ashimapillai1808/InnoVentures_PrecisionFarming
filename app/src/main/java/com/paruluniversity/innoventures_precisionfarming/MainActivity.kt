package com.paruluniversity.innoventures_precisionfarming

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var crops: CardView
    private lateinit var temp: CardView
    private lateinit var soil: CardView
    private lateinit var water: CardView
    private lateinit var weather: CardView
    private lateinit var profile: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable edge-to-edge for immersive experience
        window.decorView.systemUiVisibility = (
                android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                )

        setContentView(R.layout.activity_main)


        // Handle window insets for edge-to-edge padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            // Get system bar insets (status bar, navigation bar)
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            // Apply padding based on system bar insets
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            // Return the insets to allow further handling
            insets
        }

        // Initialize CardViews
        crops = findViewById(R.id.crops)
        temp = findViewById(R.id.temp)
        soil = findViewById(R.id.soil)
        water = findViewById(R.id.water)
        weather = findViewById(R.id.weather)
        profile = findViewById(R.id.profile)

        // Set onClickListeners for each card
        crops.setOnClickListener {
            showToast("Home Clicked")
        }
        temp.setOnClickListener {
            showToast("Chat Clicked")
        }
        soil.setOnClickListener {
            showToast("Profile Clicked")
        }
        water.setOnClickListener {
            showToast("Widget Clicked")
            val nextWater = Intent(this@MainActivity, water_tank_status::class.java)
            startActivity(nextWater)
        }
        weather.setOnClickListener {
            showToast("Settings Clicked")
        }
        profile.setOnClickListener {
            showToast("Logout Clicked")
        }



    }

    // Show toast message
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
