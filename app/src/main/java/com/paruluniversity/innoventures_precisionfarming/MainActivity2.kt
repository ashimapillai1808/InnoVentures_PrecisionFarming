package com.paruluniversity.innoventures_precisionfarming

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import com.paruluniversity.innoventures_precisionfarming.databinding.ActivityMain2Binding
import com.paruluniversity.innoventures_precisionfarming.databinding.ActivityMainBinding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    private lateinit var cropList: ArrayList<Crop>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using view binding
        binding = ActivityMain2Binding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        // Data for crops
        val images = intArrayOf(
            R.drawable.paddy,
            R.drawable.orange,
            R.drawable.tomato
        )

        val names = arrayOf("Paddy", "Orange", "Tomato")
        val descriptions = arrayOf(
            "Paddy, a water-loving crop, thrives in hot and humid climates with temperatures between 20–30°C.",
            "Orange is a citrus fruit that thrives in well-drained, sandy loam soil with moderate water.",
            "Tomato is a warm-season crop that thrives in well-drained, fertile loamy soil with temperatures of 20–30°C."
        )
        val locations = arrayOf("Punjab", "Tamil Nadu", "Maharashtra")
        val temperature = arrayOf("20-30","40-45","25-30")

        // Populate the crop list
        cropList = ArrayList()
        for (i in names.indices) {
            cropList.add(Crop(names[i], descriptions[i], locations[i], images[i], temperature[i]))
        }

        val cropAdapter = CropAdapter(this, cropList)

        binding.listView.adapter = cropAdapter

        // Handle item click
        binding.listView
            .setOnItemClickListener { _, _, position, _ ->
                val selectedCrop = cropList[position]


                // Open the detail activity and pass the data
                val intent = Intent(this, CropDetailActivity::class.java).apply {
                    putExtra("name", selectedCrop.name)
                    putExtra("description", selectedCrop.description)
                    putExtra("location", selectedCrop.location)
                    putExtra("image", selectedCrop.imageId)
                    putExtra("temperature" , selectedCrop.temperature)
                }
                startActivity(intent)
            }
    }
}
