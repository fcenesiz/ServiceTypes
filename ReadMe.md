## started-service

Asynctask is deprecated!
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