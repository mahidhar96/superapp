package com.mahidhar.superapp.utils
//
//import android.content.Context
//import android.content.Context.LOCATION_SERVICE
//import android.location.LocationManager
//import androidx.core.content.ContextCompat
//
//class LocationUtil {
//    private lateinit var locationManager:LocationManager
//    private fun getLocation() {
//        locationManager = ContextCompat.getSystemService(LOCATION_SERVICE,LocationUtil ) as LocationManager
//        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
//            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), locationPermissionCode)
//        }
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, this)
//    }
//}