package com.fcenesiz.servicetypes

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.*

class MyStartedService : Service() {

    lateinit var player: MediaPlayer
    lateinit var myAsyncTask: MyAsyncTask

    companion object{
        public val TAG: String = MyStartedService::class.simpleName.toString()
    }

    override fun onCreate() {
        Log.i(TAG, "OnCreate: " + Thread.currentThread().name)
        super.onCreate()
    }

    override fun onBind(p0: Intent?): IBinder? {

        return null
    }

    /**
     * Don't use for taking long time processes
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(TAG, "onStartCommand: " + Thread.currentThread().name)
        val sleepTime = intent!!.getIntExtra("sleep_time", 0)
        myAsyncTask = MyAsyncTask()
        myAsyncTask.execute(sleepTime)

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.i(TAG, "onDestroy: " + Thread.currentThread().name)
        myAsyncTask.cancel(true)
        super.onDestroy()
    }

    // run at onStartCommand without asynctask
    private fun playSound(){
        // doesn't locks the ui thread
        player = MediaPlayer.create(this, R.raw.highscore)
        player.isLooping = true
        player.start()
    }

}