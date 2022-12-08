package com.fcenesiz.servicetypes

import android.os.AsyncTask
import android.util.Log

class MyAsyncTask : AsyncTask<Void, Void, Int>() {

    companion object{
        public val TAG: String = MyAsyncTask::class.simpleName.toString()
    }

    override fun onPreExecute() {
        super.onPreExecute()
        Log.i(TAG, "onPreExecute: " + Thread.currentThread().name)
    }

    override fun doInBackground(vararg p0: Void?): Int {
        Log.i(TAG, "doInBackground: " + Thread.currentThread().name)
        return 0
    }

    override fun onPostExecute(result: Int?) {
        super.onPostExecute(result)
        Log.i(TAG, "onPostExecute: " + Thread.currentThread().name)

    }



}