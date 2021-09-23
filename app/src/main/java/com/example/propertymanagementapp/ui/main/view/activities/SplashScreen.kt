package com.example.propertymanagementapp.ui.main.view.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.propertymanagementapp.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.animationView.alpha = 0f
        binding.animationView.animate().setDuration(5000).alpha(1f).withEndAction {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}