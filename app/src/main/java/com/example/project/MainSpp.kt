package com.example.project

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainSpp: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spp)
    }

    fun keBinsus(view: View?) {
        val i = Intent(applicationContext, binsus::class.java)
        startActivity(i)

    }
    fun keReguler(view: View?) {
        val i = Intent(applicationContext, reguler::class.java)
        startActivity(i)
    }
    fun keHome(view: View?) {
        val i = Intent(applicationContext, HomeFragment::class.java)
        startActivity(i)
    }
}