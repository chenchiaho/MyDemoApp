package com.example.android.mydemoapp

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.provider.Settings.Global.putString
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.android.mydemoapp.ui.weather.current.CurrentWeatherViewModel

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.currentCoroutineContext
import java.lang.reflect.Array.get
import java.util.*
import java.util.logging.Handler

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
    var locationManager: LocationManager? = null
    var locationListener: LocationListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupActionBarWithNavController(this, navController)

        bottom_nav.setupWithNavController(navController)


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

    override fun onSupportNavigateUp(): Boolean {
        navController.navigateUp()
        return super.onSupportNavigateUp()
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


    fun updateLocation(location: Location) {

        var cityString = ""
        val geocoder = Geocoder(this, Locale.getDefault())
        val cityLatLon = geocoder.getFromLocation(location.latitude, location.longitude, 1)
        if (cityLatLon[0].locality != null) cityString = cityLatLon[0].locality
        else if (cityLatLon[0].subAdminArea != null) cityString = cityLatLon[0].subAdminArea

        val sharedPref = this.getSharedPreferences("sharedPrefLocation", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.apply{
            putString("LOCATION", cityString)
        }.apply()

    }

}