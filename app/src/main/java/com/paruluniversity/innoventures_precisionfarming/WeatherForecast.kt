package com.paruluniversity.innoventures_precisionfarming

import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.paruluniversity.innoventures_precisionfarming.R.id.loader
import org.json.JSONObject
import org.w3c.dom.Text
import java.net.URL
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class WeatherForecast : AppCompatActivity() {

    val CITY : String = "Raipur"
    val API : String = "6e9031235d2c3d0a2848e99dc2ebf3bf"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_weather_forecast)

        weatherTask().execute()

    }

    inner class weatherTask():AsyncTask<String,Void,String>(){
        override fun onPreExecute() {
            super.onPreExecute()
            findViewById<ProgressBar>(loader).visibility = View.VISIBLE
            findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.GONE
            findViewById<TextView>(R.id.errortext).visibility = View.GONE

    }

        override fun doInBackground(vararg params: String?): String? {
            var  response : String?
            try{
                response = URL("https://api.openweathermap.org/data/2.5/weather?q=$CITY&units=metric&appid=$API")
                    .readText(Charsets.UTF_8)
            }
            catch (e : Exception){

                response=null
            }
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try{
                val jsobObj = JSONObject(result)
                val main = jsobObj.getJSONObject("main")
                val sys = jsobObj.getJSONObject("sys")
                val wind = jsobObj.getJSONObject("wind")
                val weather = jsobObj.getJSONArray("weather").getJSONObject(0)
                val updatedAt : Long = jsobObj.getLong("dt")
                val updatedAtText = "Updated at:"+SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(Date(updatedAt*1000))
                val temp = main.getString("temp")+"°C"
                val tempMin = "Min Temp:" + main.getString("temp_min") + "°C"
                val tempMax = "Max Temp:" + main.getString("temp_max") + "°C"
                val pressure = main.getString("pressure")
                val humidity = main.getString("humidity")
                val sunrise: Long = sys.getLong("sunrise")
                val sunset:Long = sys.getLong("sunset")
                val windSpeed = wind.getString("speed")
                val weatherDescription = weather.getString("description")
                val address = jsobObj.getString("name")+", "+sys.getString("country")

                findViewById<TextView>(R.id.address).text = address
                findViewById<TextView>(R.id.updated_at).text = updatedAtText
                findViewById<TextView>(R.id.status).text = weatherDescription.capitalize()
                findViewById<TextView>(R.id.temp).text = temp
                findViewById<TextView>(R.id.temp_max).text = tempMax
                findViewById<TextView>(R.id.temp_min).text = tempMin
                findViewById<TextView>(R.id.sunrise).text = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(
                    Date(sunrise * 1000
                    )
                )
                findViewById<TextView>(R.id.sunset).text = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(
                    Date(sunset * 1000
                    )
                )
                findViewById<TextView>(R.id.wind).text = windSpeed
                findViewById<TextView>(R.id.pressure).text = pressure
                findViewById<TextView>(R.id.humidity).text = humidity

                findViewById<ProgressBar>(loader).visibility = View.GONE
                findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.VISIBLE

            }
            catch (e:Exception){
                findViewById<ProgressBar>(R.id.loader).visibility = View.GONE
                findViewById<TextView>(R.id.errortext).visibility = View.VISIBLE
            }

        }
        }
}