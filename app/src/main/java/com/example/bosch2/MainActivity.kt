package com.example.bosch2

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class MainActivity : AppCompatActivity() {
    lateinit var tvMain:TextView
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvMain = findViewById(R.id.textView)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

    }

    @SuppressLint("MissingPermission")
    private fun getLastKnownLocation() {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location : Location? ->
                tvMain.setText("location--"+location?.latitude+"\nlongitude--"+location?.longitude+"\n altitude ="+location?.altitude)
                Log.i("mainactivity","your locationnn is -- lat ="+location?.latitude)
            }
    }



    override fun onStart() {
        super.onStart()
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
        } else {
            // Permission is already granted
            getLastKnownLocation()
        }

    }

    fun insertContentProvider(view: View) {
        var uri = Uri.parse("content://com.example.appa.provider/contacts")
        var cursor = contentResolver.query(uri,null,null,null,null)
        var values = ContentValues()
        values.put("name","abdul-ansari")
        values.put("phone",123456)
        contentResolver.insert(uri,values)


    }

    fun retreiveContentProvider(view: View) {

        var uri = Uri.parse("content://com.example.appa.provider/contacts")
        var cursor = contentResolver.query(uri,null,null,null,null)
        cursor?.moveToLast()
        var name = cursor?.getColumnIndex("name")?.let { cursor.getString(it) }
        var phone = cursor?.getColumnIndex("phone")?.let { cursor.getString(it) }
        tvMain.setText("name--"+ name+"-phone no is--"+phone)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Permission granted, get the last known location
            getLastKnownLocation()
        } else {
            // Permission denied, handle accordingly
            Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show()
        }
    }

}

