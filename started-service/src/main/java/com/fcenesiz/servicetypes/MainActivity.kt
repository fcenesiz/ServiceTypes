package com.fcenesiz.servicetypes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fcenesiz.servicetypes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonStartService.setOnClickListener { startService() }
        binding.buttonStopService.setOnClickListener { stopService() }
    }


    private fun startService(){
        val intent = Intent(this, MyStartedService::class.java).apply {

        }
        startService(intent)
    }

    private fun stopService(){
        val intent = Intent(this, MyStartedService::class.java)
        stopService(intent)
    }

}