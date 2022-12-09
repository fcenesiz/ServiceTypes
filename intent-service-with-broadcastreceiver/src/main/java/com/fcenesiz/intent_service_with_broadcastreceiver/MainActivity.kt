package com.fcenesiz.intent_service_with_broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fcenesiz.intent_service_with_broadcastreceiver.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonStartIntentService.setOnClickListener {
            startIntentService()
        }
    }

    private fun startIntentService(){
        val intent = Intent(baseContext, MyIntentService::class.java)
        intent.putExtra("sleep_time", 15)
        startService(intent)
    }

    override fun onResume() {
        super.onResume()
        val intentFilter = IntentFilter("my.action.name")
        registerReceiver(myBroadcastReceiver, intentFilter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(myBroadcastReceiver)
    }

    private val myBroadcastReceiver = object : BroadcastReceiver(){
        override fun onReceive(p0: Context?, intent: Intent?) {
            val control = intent!!.getIntExtra("control", 0)
            binding.textViewControlInfo.setText("loading... $control")
            if (control == -1)
                binding.textViewControlInfo.setText("done")
        }
    }

}