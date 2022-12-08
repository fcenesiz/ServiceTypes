package com.fcenesiz.intent_service

import android.app.IntentService
import android.content.Intent
import android.os.Bundle
import android.os.ResultReceiver
import android.util.Log

class MyIntentService(name: String = "MyWorkerThread") : IntentService(name) {

    companion object{
        public val TAG: String = MyIntentService::class.simpleName.toString()
    }

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "onCreate: " + Thread.currentThread().name)
    }

    override fun onHandleIntent(intent: Intent?) {
        Log.i(TAG, "onHandleIntent: " + Thread.currentThread().name)
        val processTime = intent!!.getIntExtra("process_time", 0)
        val resultReceiver = intent.getParcelableExtra<ResultReceiver>("receiver")
        var nextProcess = 0
        while (nextProcess < processTime){
            try{
                Thread.sleep(1000)
                val bundle = Bundle()
                bundle.putString("process_result", "process running... $nextProcess")
                resultReceiver!!.send(16, bundle)
            }catch (e : InterruptedException){
                Thread.currentThread().interrupt()
            }
            nextProcess++
        }
        val bundle = Bundle()
        bundle.putString("process_result", "process successfully ended!")
        resultReceiver!!.send(16, bundle)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy: " + Thread.currentThread().name)
    }

}