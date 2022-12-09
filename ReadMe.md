## 1. started-service

- Asynctask is deprecated!
- works on main thread

use this instead of asynctask on Java
````java
ExecutorService executor = Executors.newSingleThreadExecutor();
Handler handler = new Handler(Looper.getMainLooper());

executor.execute(() -> { // doInBackground
    //Background work here
        
    handler.post(() -> { // onPostExecute
        //UI Thread work here
        
    });
});
````

or with kotlin coroutines

````kotlin
GlobalScope.launch {
    coroutineScope {
        // onPreExecute
        
        launch {
            // doInBackground
            delay(5000)
        }
    }
}
````
or with ``runOnUIThread()``

````kotlin
Thread{
    // background 
    runOnUiThread { 
        // main
    }
}.start()
````
or with ``post()`` method
````kotlin
Thread{
    // background 
    someView.post {
        // main
    }
}.start()
````
or with ``handler``
````kotlin
Thread{
    // background
    Handler(Looper.getMainLooper()).post {
        // main thread
    }
}.start()
````

## 2. intent-service (Deprecated!)

- sub-class of started-service
- has own worker thread (asynctask)
- need to override ``onHandleIntent()``
- handles multi-threading details internally, creates a work queue for handle all intents
- automatically stops itself when work is complete (No need to use ``stopSelf()`` or ``stopService()``) 
- running style: ``onStartCommand()`` -> ``work queue`` -> ``onHandleIntent()``

# started-service vs intent-service

- started-service
  - operates in main thread
  - may block the ui
  - used for short duration

- intent-service
  - operates in worker thread
  - does not block the ui
  - used for long process

## 3. bound-service

A bound service is the server in a client-server interface.

- returns data 
- need to override ``bindService()``
- works on main thread
- for long-standing connection
- ``onBind()`` returns ``IBinder``
- If the calling component is destroyed then Bound Services too gets destroyed
- usage
  - Generally ``bindService()`` in ``onStart()`` and ``unBindService()`` in ``onStop()``
  - If you want your Activity to bind to the service even when it is not visible, ``bindService()`` in ``onCreate()`` and ``unBindService()`` in ``onDestroy()``
