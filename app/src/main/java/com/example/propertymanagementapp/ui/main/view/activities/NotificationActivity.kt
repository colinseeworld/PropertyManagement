package com.example.propertymanagementapp.ui.main.view.activities

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.example.propertymanagementapp.R
import com.example.propertymanagementapp.databinding.ActivityNotificationBinding
import kotlin.random.Random

class NotificationActivity : AppCompatActivity() {

    lateinit var binding: ActivityNotificationBinding
    private var CHANNEL_ID = "Mail Alerts"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNotification.setOnClickListener{
            createNotification()
        }
    }

    private fun createNotification() {
        val notification = NotificationCompat.Builder(baseContext,CHANNEL_ID).apply {
            setContentTitle("Cheer up!")
            setContentText("Hey, you're getting better every day!")
            setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.congrats))
            setSmallIcon(R.drawable.ic_small_icon)
        }.build()

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        // Oreo and above devices
        // Create notification channel

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val notificationChannel = NotificationChannel(CHANNEL_ID,"Important",NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
        }
        val id = Random.nextInt(Int.MIN_VALUE, Int.MAX_VALUE)
        notificationManager.notify(id,notification)
    }
}