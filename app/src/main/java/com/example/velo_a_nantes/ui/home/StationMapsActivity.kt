package com.example.velo_a_nantes.ui.home


import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.velo_a_nantes.R
import com.example.velo_a_nantes.databinding.ActivityStationMapsBinding
import com.example.velo_a_nantes.models.allStations
import com.example.velo_a_nantes.models.currentLocation

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.velo_a_nantes.models.stationSelected
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory

class StationMapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityStationMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStationMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        stationSelected?.let{station ->
            allStations?.let{
                for(st in it){
                    // Add a marker in Sydney and move the camera
                    val stationPosition = LatLng(st.latitude, st.longitude)
                    if(st.id===station!!.id ){
                        mMap.addMarker(
                            MarkerOptions()
                                .position(stationPosition)
                                .title("${st.name} : ${st.address}" + st.showDetails())
                                .icon(BitmapFromVector(this, R.drawable.ic_baseline_directions_bike_24)))
                    }else{
                        mMap.addMarker(
                            MarkerOptions()
                                .position(stationPosition)
                                .title("${st.name} : ${st.address}" + st.showDetails())
                        )
                    }

                }

            }
            // Add a marker in Sydney and move the camera
            val stationPosition = LatLng(station.latitude, station.longitude)
//            mMap.addMarker(MarkerOptions().position(stationPosition).title("${station.name} : ${station.address}"))
            if(currentLocation!=null){
                val myPosition = LatLng(currentLocation!!.latitude, currentLocation!!.longitude)
                mMap.addMarker(MarkerOptions()
                    .position(myPosition)
                    .title("Ma position")
                    .icon(BitmapFromVector(this, R.drawable.ic_baseline_my_location_24)))
            }

            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(stationPosition, 20f))


        }
    }
    private fun BitmapFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        // below line is use to generate a drawable.
        val vectorDrawable = ContextCompat.getDrawable(context, vectorResId)

        // below line is use to set bounds to our vector drawable.
        vectorDrawable!!.setBounds(
            0,
            0,
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight
        )

        // below line is use to create a bitmap for our
        // drawable which we have added.
        val bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )

        // below line is use to add bitmap in our canvas.
        val canvas = Canvas(bitmap)

        // below line is use to draw our
        // vector drawable in canvas.
        vectorDrawable.draw(canvas)

        // after generating our bitmap we are returning our bitmap.
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }
}