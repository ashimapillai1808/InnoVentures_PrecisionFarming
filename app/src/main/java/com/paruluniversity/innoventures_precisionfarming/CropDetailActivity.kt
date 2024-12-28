package com.paruluniversity.innoventures_precisionfarming

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.paruluniversity.innoventures_precisionfarming.databinding.ActivityCropDetailBinding

class CropDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCropDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCropDetailBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)


        val name = intent.getStringExtra("name")
        val description = intent.getStringExtra("description")
        val location = intent.getStringExtra("location")
        val temperature = intent.getStringExtra("temperature")
        val imageId = intent.getIntExtra("image", R.drawable.placeholder)


        binding.cropImage.setImageResource(imageId)
        binding.cropName.text = name
        binding.cropDescription.text = description
        binding.cropLocation.text = location
        binding.temperature1.text = temperature
    }
}
