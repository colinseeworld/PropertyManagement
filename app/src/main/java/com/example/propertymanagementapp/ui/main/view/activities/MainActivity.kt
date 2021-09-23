package com.example.propertymanagementapp.ui.main.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.propertymanagementapp.data.repository.NetworkReceiver
import com.example.propertymanagementapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()

    }

    private fun init(){
        var checkNetwork = NetworkReceiver(this).isConnected
        Log.d("abc","$checkNetwork")

        binding.btnRegister.setOnClickListener(this)
        binding.btnLogin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v){
            binding.btnRegister -> startActivity(Intent(this, RegisterActivity::class.java))
            binding.btnLogin -> startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}