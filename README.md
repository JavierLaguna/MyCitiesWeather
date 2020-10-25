# My Cities Weather

[![forthebadge](https://forthebadge.com/images/badges/built-for-android.svg)](https://forthebadge.com)

> My Cities Weather is a practice of 'android-avanzado' for KeepCoding Mobile 10

![Welcome](/screenCaptures/welcome.jpg)
![Add Cities](/screenCaptures/add_cities.jpg)
![My Cities](/screenCaptures/my_cities.jpg)

![Weather detail 1](/screenCaptures/weather_detail_1.jpg)
![Weather detail 2](/screenCaptures/weather_detail_2.jpg)
![Weather detail 3](/screenCaptures/weather_detail_3.jpg)

## Application Structure

Little Android client for consume [MetaWeather](https://www.metaweather.com/api/) API.

[Kotlin](https://kotlinlang.org/) app based on ([MVVM](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) + [Delegate Pattern](https://en.wikipedia.org/wiki/Delegation_pattern)) as main architecture.

[Retrofit](https://square.github.io/retrofit/) as networking library.

[Glide](https://bumptech.github.io/glide/) as image loading library.

[Room](https://developer.android.com/topic/libraries/architecture/room) for local database.

[LiveData](https://developer.android.com/topic/libraries/architecture/livedata) for observe database changes.

App Features:

###### Cities
   - Search cities of the world on metaweather API
   - Add cities to local database
   - List my saved cities
   - Remove cities from local database

###### Weather
   - Get the weather of the current day and the next 6 days and cache on the local database.
   - Display weather info.
   - Floating button for refresh the weather data with API info.

#### Author
> Javier Laguna

###### TODO - Next steps
   - Implement [WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager) for update cities weather.
   - Add settings screen and uses different units (Km, Farenheit...).
   - Testing.
   - Add debounce on cities search.
