package com.example.project

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RemoteViews
import androidx.appcompat.app.AppCompatActivity

class lapor : AppCompatActivity() {

    // declaring variables
    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId = "i.apps.notifications"
    private val description = "Test notification"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lapor)


        val btn = findViewById<Button>(R.id.button)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        btn.setOnClickListener {
            val intent = Intent(this, HomeFragment::class.java)
            startActivity(intent)
            val pendingIntent =
                PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            val contentView = RemoteViews(packageName, R.layout.notif)


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel =
                    NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
                notificationChannel.enableLights(true)
                notificationChannel.lightColor = Color.GREEN
                notificationChannel.enableVibration(false)
                notificationManager.createNotificationChannel(notificationChannel)

                builder = Notification.Builder(this, channelId)
                    .setContent(contentView)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setLargeIcon(
                        BitmapFactory.decodeResource(
                            this.resources,
                            R.drawable.ic_launcher_background
                        )
                    )
                    .setContentIntent(pendingIntent)
            } else {

                builder = Notification.Builder(this)
                    .setContent(contentView)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setLargeIcon(
                        BitmapFactory.decodeResource(
                            this.resources,
                            R.drawable.ic_launcher_background
                        )
                    )
                    .setContentIntent(pendingIntent)
            }
            notificationManager.notify(1234, builder.build())
        }

        }
    fun back(view: View?) {
        val i = Intent(applicationContext, reguler::class.java)
        startActivity(i)
    }
}
