## started-service

- Asynctask is deprecated!

use this instead of asynctask
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

## intent-service

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