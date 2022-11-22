package com.example.velo_a_nantes.ui.station

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.velo_a_nantes.R
import com.example.velo_a_nantes.models.stationSelected

class StationDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_station_detail)
        val stationName = findViewById<TextView>(R.id.stationName)

        val buttonOpen = findViewById<Button>(R.id.buttonOpenMap)


        stationSelected?.let{station ->
            stationName.text = station.name
            buttonOpen.setOnClickListener{
                // Creates an Intent that will load a map of San Francisco
                val gmmIntentUri = Uri.parse("geo:0,0?q=${station.latitude},${station.longitude}(${station.address})")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                startActivity(mapIntent)
            }
        }
    }
}