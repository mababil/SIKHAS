package com.example.project

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class profil:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profil)
    }
    fun keLogin(view: View?) {
        val i = Intent(applicationContext, LoginActivity::class.java)
        startActivity(i)

    }
}