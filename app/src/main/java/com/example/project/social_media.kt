package com.example.project

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.ScaleGestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_social_media.*

class social_media:AppCompatActivity() {
    private lateinit var scaleGestureDetector: ScaleGestureDetector
    private var scaleFactor = 1.0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_social_media)
        scaleGestureDetector = ScaleGestureDetector(this, ScaleListener())
    }
    fun keHome(view: View?) {
        val i = Intent(applicationContext, HomeFragment::class.java)
        startActivity(i)
    }
    fun Card1(view: View?) {
        val i = Intent(applicationContext, Sejarah_SMK::class.java)
        startActivity(i)
    }
    override fun onTouchEvent(motionEvent: MotionEvent): Boolean {
        scaleGestureDetector.onTouchEvent(motionEvent)
        return true
    }
    fun Card2(view: View?) {
        val url = "https://www.instagram.com/smkmuda_pekanbaru/?next=%2F"
        val bukabrowser = Intent(Intent.ACTION_VIEW)
        bukabrowser.data = Uri.parse(url)
        startActivity(bukabrowser)
    }
    fun Card3(view: View?) {
        val url = "https://www.smkmhd2pku.sch.id/"
        val bukabrowser = Intent(Intent.ACTION_VIEW)
        bukabrowser.data = Uri.parse(url)
        startActivity(bukabrowser)
    }
    private inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener(),
        ScaleGestureDetector.OnScaleGestureListener {
        override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean {
            scaleFactor *= scaleGestureDetector.scaleFactor
            scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 10.0f))
            img_logo.scaleX = scaleFactor
            img_logo.scaleY = scaleFactor
            return true
        }
    }
}