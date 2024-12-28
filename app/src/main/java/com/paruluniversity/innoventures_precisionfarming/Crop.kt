package com.paruluniversity.innoventures_precisionfarming

import android.health.connect.datatypes.units.Temperature

data class Crop(val name: String,
                val description: String,
                val location: String,
                val imageId: Int,
                val temperature: String
)
