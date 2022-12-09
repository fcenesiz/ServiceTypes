package com.fcenesiz.bound_service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class MyBoundService : Service() {

    private var number = 0

    companion object {
        public val TAG: String = MyBoundService::class.simpleName.toString()
    }

    var randomGeneratorActive = true

    inner class MyLocalBinder : Binder(){

        public fun getService() : MyBoundService{
            return this@MyBoundService
        }

    }

    private val myLocalBinder : IBinder = MyLocalBinder()

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "onCreate: ")
    }

    override fun onBind(p0: Intent?): IBinder? {
        Log.i(TAG, "onBind: ")
        return myLocalBinder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Thread {
            while (randomGeneratorActive) {
                try {
                    Thread.sleep(500)
                } catch (e: InterruptedException) {
                    Thread.currentThread().interrupt()
                }
                number = randomGenerator()
                Log.i(TAG, "onStartCommand, random:$number")
            }
        }.start()

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        randomGeneratorActive = false
        Log.i(TAG, "onDestroy: ")
    }

    private fun randomGenerator(): Int {
        return (Math.random() * 100).roundToInt()
    }

    public fun getNextRandomNumber() : Int{
        return number
    }

}