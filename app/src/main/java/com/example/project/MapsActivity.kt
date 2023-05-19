package com.example.project

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.core.app.ActivityCompat.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private lateinit var mMap: GoogleMap

    companion object {
        private val MY_PERMISSION_FINE_LOCATION = 101
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val SMK2 = LatLng( 0.511965116669, 101.437293883)
        mMap.addMarker(
            MarkerOptions().position(SMK2)
                .title("SMK MUHAMMADIYAH 2 PEKANBARU")
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SMK2, 12F))
        mMap.uiSettings.isZoomControlsEnabled = true

        if (checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            mMap.isMyLocationEnabled = true
        }
        mMap.setOnMarkerClickListener(this)
        tambahMarkerLongClick(mMap)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_tampilan_map, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.map_normal -> {
                mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
                true
            }
            R.id.map_hybrid -> {
                mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
                true
            }
            R.id.map_satelit -> {
                mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
                true
            }
            R.id.map_terrain -> {
                mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onMarkerClick(p0: Marker): Boolean = false

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            MY_PERMISSION_FINE_LOCATION -> if (grantResults[0] ==
                PackageManager.PERMISSION_GRANTED
            ) {
                if (checkSelfPermission(
                        this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    mMap.isMyLocationEnabled = true
                }
            } else {
                Toast.makeText(
                    this,
                    "Aplikasi ini membutuhkan izin akses lokasi",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }

    private fun getAlamat(lat: LatLng): String? {
        val geocoder = Geocoder(this)
        val list = geocoder.getFromLocation(lat.latitude, lat.longitude, 1)
        return list!![0].getAddressLine(0)
    }

    fun tambahMarkerLongClick(googleMap: GoogleMap) {
        googleMap.setOnMapLongClickListener { latLng ->

            val koordinat = LatLng(latLng.latitude, latLng.longitude)
            val markerOptions = MarkerOptions().position(koordinat)
            val namaJalan = getAlamat(koordinat)
            markerOptions.title(namaJalan)

            googleMap.addMarker(
                MarkerOptions().position(koordinat).title("Marker Baru").snippet(namaJalan)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA))
            )
        }
    }
}
