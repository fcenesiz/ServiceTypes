package com.fcenesiz.intent_service_with_broadcastreceiver

import android.app.IntentService
import android.content.Intent
import android.os.Handler
import android.util.Log

class MyIntentService(name: String = "MyIntentService") : IntentService(name) {

    companion object{
        public val TAG: String = MyIntentService::class.simpleName.toString()
    }

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "onCreate: "+ Thread.currentThread().name)
    }

    override fun onHandleIntent(intent: Intent?) {
        Log.i(TAG, "onHandleIntent: "+ Thread.currentThread().name)
        val sleepTime = intent!!.getIntExtra("sleep_time", 0)

        var control = 0
        while (control < sleepTime){
            try {
                Thread.sleep(1000)
                val intentBroadcast = Intent("my.action.name")
                intentBroadcast.putExtra("control", control)
                sendBroadcast(intentBroadcast)
            } catch (e: InterruptedException) {
                Thread.currentThread().interrupt()
            }
            Log.i(TAG, "onHandleIntent: $control")
            control++
        }

        val intentBroadcast = Intent("my.action.name")
        intentBroadcast.putExtra("control", -1)
        sendBroadcast(intentBroadcast)

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy: "+ Thread.currentThread().name)
    }
}