package com.example.android.mydemoapp.util

import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.*

class CurrentLocation: AppCompatActivity() {

    var locationManager: LocationManager? = null
    var locationListener: LocationListener? = null

    init {
        locationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationListener = LocationListener { location -> updateLocation(location) }

        if (ContextCompat.checkSelfPermission(
                        this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 2
            )
        } else {
            locationManager!!.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    5 * 60 * 1000,
                    500f,
                    locationListener!!
            )
            val lastLocation = locationManager!!.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (lastLocation != null) {
                updateLocation(lastLocation)
            }
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(
                            this,
                            android.Manifest.permission.ACCESS_FINE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED) {
                locationManager!!.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        5 * 60 * 1000,
                        500f,
                        locationListener!!
                )
            }
        }
    }

    fun updateLocation(location: Location): String {
        var cityString = "Could not find city"
        val geocoder = Geocoder(this, Locale.getDefault())
        val cityLatLon = geocoder.getFromLocation(location.latitude, location.longitude, 1)
        if (cityLatLon[0].locality != null) cityString = cityLatLon[0].locality
        else if (cityLatLon[0].subAdminArea != null) cityString = cityLatLon[0].subAdminArea
        return cityString
    }

}