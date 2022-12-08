package com.fcenesiz.servicetypes

import android.os.AsyncTask
import android.util.Log
import android.widget.Toast

class MyAsyncTask(
    val myStartedService: MyStartedService
) : AsyncTask<Int, Int, String>() {

    companion object{
        public val TAG: String = MyAsyncTask::class.simpleName.toString()
    }

    override fun onPreExecute() {
        super.onPreExecute()
        Log.i(TAG, "onPreExecute: " + Thread.currentThread().name)
    }

    override fun doInBackground(vararg p0: Int?): String {
        Log.i(TAG, "doInBackground: " + Thread.currentThread().name)
        val sleepTime : Int = p0[0]!!
        var update = 0;
        while (update < sleepTime){
            if (isCancelled)
                break
            try {
                Thread.sleep(1000)
            }catch (e: InterruptedException){
                Thread.currentThread().interrupt()
            }

            publishProgress(update)
            update++;
        }

        return "Process successfully ended!"
    }

    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
        Log.i(TAG, "loading... " + values[0]!!)
        Toast.makeText(myStartedService, "loading... " + values[0]!!, Toast.LENGTH_SHORT).show()
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        Log.i(TAG, "onPostExecute: " + Thread.currentThread().name)
        Toast.makeText(myStartedService, result, Toast.LENGTH_SHORT).show()
    }
}