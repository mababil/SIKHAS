package com.example.project

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class HomeFragment : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_home)
        }
        fun keSpp(view: View?) {
            val i = Intent(applicationContext, MainSpp::class.java)
            startActivity(i)

        }
        fun keAbout(view: View?) {
            val i = Intent(applicationContext, social_media::class.java)
            startActivity(i)

        }
        fun kePelaporan(view: View?) {
            val i = Intent(applicationContext, pelaporan::class.java)
            startActivity(i)

        }
        fun keReguler(view: View?) {
            val i = Intent(applicationContext, PelaporanRegulerActivity::class.java)
            startActivity(i)

        }
        fun keBinsus(view: View?) {
            val i = Intent(applicationContext, PelaporanBinsusActivity::class.java)
            startActivity(i)

        }
    fun keMaps(view: View?) {
        val i = Intent(applicationContext, MapsActivity::class.java)
        startActivity(i)

    }
    fun keProfil(view: View?) {
        val i = Intent(applicationContext, profil::class.java)
        startActivity(i)

    }

}