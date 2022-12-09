package com.fcenesiz.bound_service

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import com.fcenesiz.bound_service.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object {
        public val TAG: String = MainActivity::class.simpleName.toString()
    }

    lateinit var binding: ActivityMainBinding
    private var connection: ServiceConnection? = null
    var bindService: Boolean = false
    private lateinit var myBoundService: MyBoundService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.buttonStartService.setOnClickListener {
            val intent = Intent(this, MyBoundService::class.java)
            startService(intent)
        }
        binding.buttonStopService.setOnClickListener {
            val intent = Intent(this, MyBoundService::class.java)
            stopService(intent)
        }
        binding.buttonBindService.setOnClickListener {
            if (connection == null)
                connection = object : ServiceConnection {
                    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                        bindService = true
                        val myLocalBinder: MyBoundService.MyLocalBinder =
                            service as MyBoundService.MyLocalBinder
                        myBoundService = myLocalBinder.getService()
                        Log.i(TAG, "service connected called")
                    }

                    override fun onServiceDisconnected(name: ComponentName?) {
                        bindService = false
                        Log.i(TAG, "service disconnected called")
                    }
                }

            if (!bindService) {
                val intent = Intent(this@MainActivity, MyBoundService::class.java)
                bindService(intent, connection!!, BIND_AUTO_CREATE)
                bindService = true
                Log.i(TAG, "Bound to the service!")
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "already bound to the service!",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
        binding.buttonUnBindService.setOnClickListener {
            if (bindService){
                unbindService(connection!!)
                bindService = false
                Log.i(TAG, "unbind to the service!")
            }
            else{
                Log.i(TAG, "already unbind to the service!")
            }
        }
        binding.buttonGetRandomNumber.setOnClickListener {
            if (bindService){
                binding.textViewRandomNumber.text = myBoundService.getNextRandomNumber().toString()
            }else{
                Toast.makeText(this, "bind to the service before get a number", Toast.LENGTH_SHORT).show()
            }
        }

    }
}