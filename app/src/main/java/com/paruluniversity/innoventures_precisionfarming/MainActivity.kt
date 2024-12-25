package com.paruluniversity.innoventures_precisionfarming

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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
        supportActionBar?.hide()
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
            showToast("CropDetails Clicked")
            val intent = Intent(this, CropDetails::class.java)
            startActivity(intent)
        }
        temp.setOnClickListener {
            showToast("CropTemperature Clicked")
            val intent = Intent(this, CropTemp::class.java)
            startActivity(intent)
        }
        soil.setOnClickListener {
            showToast("SoilMoisture Clicked")
            val intent = Intent(this, SoilMoisture::class.java)
            startActivity(intent)
        }
        water.setOnClickListener {
            showToast("WaterLevel Clicked")
            val intent = Intent(this, water_tank_status::class.java)
            startActivity(intent)
        }
        weather.setOnClickListener {
            showToast("WeatherForecast Clicked")
            val intent = Intent(this, WeatherForecast::class.java)
            startActivity(intent)
        }
        profile.setOnClickListener {
            showToast("Profile Clicked")
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }
    }

    // Show toast message
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
