package com.fcenesiz.servicetypes

import android.os.AsyncTask
import android.util.Log

class MyAsyncTask : AsyncTask<Int, Void, Int>() {

    companion object{
        public val TAG: String = MyAsyncTask::class.simpleName.toString()
    }

    override fun onPreExecute() {
        super.onPreExecute()
        Log.i(TAG, "onPreExecute: " + Thread.currentThread().name)
    }

    override fun doInBackground(vararg p0: Int?): Int {
        Log.i(TAG, "doInBackground: " + Thread.currentThread().name)
        val sleepTime : Int = p0[0]!!
        Thread.sleep(1000 * sleepTime.toLong())
        return 0
    }

    override fun onPostExecute(result: Int?) {
        super.onPostExecute(result)
        Log.i(TAG, "onPostExecute: " + Thread.currentThread().name)

    }


    /**
     * AsyncTask is deprecated!
     * use this instead of asynctask
     */
    /*
    ExecutorService executor = Executors.newSingleThreadExecutor();
    Handler handler = new Handler(Looper.getMainLooper());

    executor.execute(() -> {
        //Background work here
        handler.post(() -> {
            //UI Thread work here
        });
    });
     */

}