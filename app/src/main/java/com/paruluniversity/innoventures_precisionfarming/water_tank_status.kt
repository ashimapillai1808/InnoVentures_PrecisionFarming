package com.paruluniversity.innoventures_precisionfarming

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database


class water_tank_status : AppCompatActivity() {

    var count = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        supportActionBar?.hide()
        setContentView(R.layout.activity_water_tank_status)

        data class Message(val author: String, val content: String)
        // Write a message to the database
        val database = Firebase.database.reference
        database.child("WaterLevel")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    // Retrieve the value
                    val gateStatus = snapshot.getValue(Int::class.java)

                    val view = findViewById<View>(R.id.water_level)
                    view.layoutParams = view.layoutParams.apply {
                        if (gateStatus != null) {
                            height = gateStatus
                        }
                    }
                    view.requestLayout()
                }

                override fun onCancelled(error: DatabaseError) {
                    // Log error
                    Log.e("Firebase", "Failed to read value", error.toException())
                }

            })



        //        database.child("WaterLevel").setValue(500)

        val tankButton: AppCompatButton = findViewById(R.id.tank_button)

        tankButton.setOnClickListener{
            updateWaterLevel()
        }

        // Swich button Code



        val slidebtn = findViewById<TextView>(R.id.slidebtn)
        val slidebtnlayout = findViewById<RelativeLayout>(R.id.slidebtnlayout)



        database.child("MotorStatus")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                @SuppressLint("SetTextI18n")
                override fun onDataChange(snapshot: DataSnapshot) {
                    // Retrieve the value
                    val motorStatus = snapshot.getValue(Int::class.java)
                    if (motorStatus != null) {
                        count = motorStatus

                        if(count==0){
                            slidebtn.text ="OFF"
                        }else if(count==1){
                            slidebtn.text ="ON"
                        }

                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Log error
                    Log.e("Firebase", "Failed to read value", error.toException())
                }

            })


        slidebtnlayout.setOnClickListener {
            if (count == 0) {
                val moveAnimation = AnimationUtils.loadAnimation(this, R.anim.btnmove)
                moveAnimation.fillAfter = true
                slidebtn.startAnimation(moveAnimation)
                count = 1
                slidebtn.text ="ON"


            }
            else if (count == 1){
                val moveAnimation = AnimationUtils.loadAnimation(this, R.anim.btnmoveback)
                moveAnimation.fillAfter = true
                slidebtn.startAnimation(moveAnimation)
                count = 0
                slidebtn.text ="OFF"
            }
            motorStatus()
        }

    }

    private fun updateWaterLevel() {

        var database = Firebase.database.reference
        database.child("WaterLevel")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    // Retrieve the value
                    val gateStatus = snapshot.getValue(Int::class.java)

                    val view = findViewById<View>(R.id.water_level)
                    view.layoutParams = view.layoutParams.apply {
                        if (gateStatus != null) {
                            height = gateStatus
                        }
                    }
                    view.requestLayout()
                }

                override fun onCancelled(error: DatabaseError) {
                    // Log error
                    Log.e("Firebase", "Failed to read value", error.toException())
                }
            })
    }

    private fun motorStatus(){

        var database = Firebase.database.reference
        database.child("MotorStatus")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    // Retrieve the value
                    database.child("MotorStatus").setValue(count)
                }

                override fun onCancelled(error: DatabaseError) {
                    // Log error
                    Log.e("Firebase", "Failed to read value", error.toException())
                }
            })
    }


}