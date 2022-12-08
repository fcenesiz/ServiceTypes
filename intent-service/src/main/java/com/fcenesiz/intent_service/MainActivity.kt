package com.fcenesiz.intent_service

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.os.ResultReceiver
import android.widget.Toast
import com.fcenesiz.intent_service.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonStartIntentService.setOnClickListener {
            startIntentService()
        }
    }

    private fun startIntentService(){
        val resultReceiver = MyResultReceiver(handler)

        val intent = Intent(this, MyIntentService::class.java)
        intent.putExtra("receiver", resultReceiver)
        intent.putExtra("process_time", 5)
        startService(intent)
    }

    inner class MyResultReceiver(val handler: Handler?) : android.os.ResultReceiver(handler){

        override fun onReceiveResult(resultCode: Int, resultData: Bundle?) {
            super.onReceiveResult(resultCode, resultData)

            if (resultCode != 16 && resultData == null)
                return

            val processResult = resultData?.getString("process_result")
            handler?.post {
                Toast.makeText(this@MainActivity, "process_result: $processResult", Toast.LENGTH_SHORT).show()
            }

        }
    }

}