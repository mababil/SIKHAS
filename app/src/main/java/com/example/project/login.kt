package com.example.project

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class login:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
    fun keHome(view: View?) {
        val i = Intent(applicationContext, HomeFragment::class.java)
        startActivity(i)

    }
    fun keRegis(view: View?) {
        val i = Intent(applicationContext, registrasi::class.java)
        startActivity(i)

    }

}