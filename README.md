# Demo Weather Application

This is a personal project that demonstrates my current knowledge of Kotlin and Android App development.

## Weather Application

The first tab of the app includes an overview page where I tried to make it interactive with data-binding techniques.<br>
The second tab displays weather condition based on user's current location using [Openweathermap](https://openweathermap.org/api/) and Location Manager.<br>
The third tab displays a list of 3 hour future forecast using RecyclerView.<br>
When clicks on the RecyclerView, it will open up a detail page and carry over selected data with Safe Args.

This app demonstrates the following techniques:

* Two-way databinding
* RecyclerView
* Room database
* Coroutines
* LiveData 
* Navigation with the SafeArgs plugin
* Retrofit to make api calls to an HTTP web service
* Moshi which handles the deserialization of the returned JSON to Kotlin data objects
* Glide to load and cache images by URL
  

## Screenshots

<img src="screenshots/screen_1.png" alt="Screenshot 1" width="250"/> <img src="screenshots/screen_2.png" alt="Screenshot 2" width="250"/>



