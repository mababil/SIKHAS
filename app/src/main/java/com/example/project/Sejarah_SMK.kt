package com.example.project

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class Sejarah_SMK:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sejarah)
    }
    fun Kembali(view: View?) {
        val i = Intent(applicationContext, social_media::class.java)
        startActivity(i)
    }
}