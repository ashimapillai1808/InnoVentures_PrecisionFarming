package com.paruluniversity.innoventures_precisionfarming

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView



class CropAdapter(
    private val context: Activity,
    private val cropList: ArrayList<Crop>
) : ArrayAdapter<Crop>(context, R.layout.list_item, cropList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = convertView ?: inflater.inflate(R.layout.list_item, null)

        val cropImage: ImageView = view.findViewById(R.id.cropImage)
        val cropName: TextView = view.findViewById(R.id.cropName)

        cropImage.setImageResource(cropList[position].imageId)
        cropName.text = cropList[position].name

        return view
    }
}

