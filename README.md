# MVVMRecipes_map
An android  project with Jetpack Compose, Coroutines, ViewModel, LiveData, Retrofit, Hilt and based on MVVM by using Recipe APi, Foursquare Places API and Google Maps Compose.
# Introduction
It is about a demonstration application with a food recipes fetch the data from mealdb API and get the nearby restaurant places from Foursquare Places API. The Recipes has different catagories which consists of different meals and when it clicks goes to the detail screen. It get places(venues) information from Foursquare Places API service, then shows them on Google Maps. 

# Screenshots

**Splash**
<img src ="https://user-images.githubusercontent.com/67770426/196130031-827595f3-3fa4-4b52-89f1-429a0f38a966.png" width = 350/>
<img src ="https://user-images.githubusercontent.com/67770426/196129923-0ab9f543-7c4a-433a-b148-4e5606d039d3.png" width = 350/>
<img src ="https://user-images.githubusercontent.com/67770426/196131249-3e2f9afd-9749-4be3-92e8-9cc5978d0346.png" width = 350/>
<img src ="https://user-images.githubusercontent.com/67770426/196131334-f3a65155-7bae-4ff8-b212-c43d84d4f5fc.png" width = 350/>
<img src ="https://user-images.githubusercontent.com/67770426/196131374-50c0df68-af5c-4524-bbfd-437618923dfa.png" width = 350/>
<img src ="https://user-images.githubusercontent.com/67770426/196129984-1c288e46-8e45-4118-be11-3dd8ea2bed5d.png" width = 350/>
<img src ="https://user-images.githubusercontent.com/67770426/196130195-974c1f18-9048-4586-8d52-13f875d9e946.png" width = 350/>
<img src ="https://user-images.githubusercontent.com/67770426/196130220-c2ff6911-41f2-4b58-8997-1a0ca89eb58f.png" width = 350/>
<img src ="https://user-images.githubusercontent.com/67770426/196130165-1f726289-b91d-4dfd-a518-17d9f8280b85.png" width = 350/>



# Architecture
   -   MVVM Architecture (Declarative View - ViewModel - Model) clean architecture
   -  Repository pattern
# Tech Stack
- [Kotlin](https://kotlinlang.org/docs/home.html) - Kotlin is a cross-platform, statically typed,
'general-purpose programming language with type inference. 
Kotlin is designed to interoperate fully with Java, and the 
JVM version of Kotlin's standard library depends on the Java 
Class Library, but type inference allows its syntax to be more concise
- [Jetepack Components:](https://developer.android.com/topic/architecture?gclid=Cj0KCQjw8O-VBhCpARIsACMvVLOH1satX45o9f4PMQ4Sxr7bG9myl6-KZL9nYda8PJsHV7m2uJL8bzgaAmqiEALw_wcB&gclsrc=aw.ds)
   - [Jetpack Compose](https://developer.android.com/jetpack/compose?gclid=Cj0KCQjwhqaVBhCxARIsAHK1tiMMwHsxQ8Z25jyEdtLha9erq11wROoEfL6RqpGMprgbDTNuMO3_Ri8aAu5EEALw_wcB&gclsrc=aw.ds) - Android???s modern toolkit for building native UI. It simplifies and accelerates UI development on Android
   - [View Model](https://developer.android.com/topic/libraries/architecture/viewmodel)- store and manage UI-related data in a lifecycle conscious way.
   - [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - Perform actions in response to a change in the lifecycle status
      of another component, such as activities and fragments.
   - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata.html) - A lifecycle-aware data holder with the observer pattern
   - [Android KTX](https://developer.android.com/kotlin/ktx.html) - Android KTX is a set of Kotlin extensions that are included
     with Android Jetpack and other Android libraries. KTX extensions provide
     concise, idiomatic Kotlin to Jetpack, Android platform, and other APIs.
   - [AndroidX](https://developer.android.com/jetpack/androidx) - Major improvement to the original Android Support Library, which is no longer maintained.
- [Retrofit](https://github.com/square/retrofit)- is a type-safe REST client for Android, 
      Java and Kotlin, built as a powerful framework for consuming APIs
- [Dagger-Hilt](https://dagger.dev/hilt/) - a dependency injection library for Android 
     that reduces the boilerplate of doing manual dependency injection in your project
- [Room](https://developer.android.com/training/data-storage/room) - a persistence library provides an abstraction layer over SQLite for cache

- [Coroutines](https://developer.android.com/kotlin/coroutines) - a concurrency design pattern that you can use on Android to simplify 
  code that executes asynchronously

- [Flow](https://developer.android.com/kotlin/flow)- In coroutines, a flow is a type that
    can emit multiple values sequentially, as opposed to suspend functions that return only a single value.
- [Accompanist](https://github.com/google/accompanist)   
- [Navigation](https://developer.android.com/guide/navigation)
  
## Data Source
- [The Recipes API](https://www.themealdb.com/api.php) 
- [FourSquare Places API](https://developer.foursquare.com/docs/places-api-getting-started)
- [Google Maps with Jetpack Compose](https://developers.google.com/maps/documentation/android-sdk/maps-compose)
## How to get the Foursquare API key?
Register [here](https://developer.foursquare.com/docs/manage-api-keys) and 
get your own Foursquare API key for your builds. You should add your API key to **local.gradle**.
## How to get the Google MAPS API key?
Register [here](https://developers.google.com/maps/documentation/android-sdk/maps-compose) and 
get your own Google Maps API key for your builds. You should add your API key to as a meta-data under the **AndroidManifest.xml** file.
## Light-Sensor
The app that displays light sensor data. It displays if the device is in dark or light room. It is possible to configure the lux level to choose the what level of brightness is considered as dark or light.
## Functionality TODO
### TODO
- Implement search feature
- Room for local caching to facilitate offline support 
- Light sensor applied only in homepage and apply for other pages
## References:
1. [Jetpack Compose MVVM for Beginners](https://codingwithmitch.com/courses/jetpack-compose-mvvm-for-beginners/)
2. [Dependency Injection with Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
3. [Material UI color System](https://material.io/design/color/the-color-system.html)
4. [Jetpack compose](https://developer.android.com/jetpack/compose)
5. [Map compose](https://developers.google.com/maps/documentation/android-sdk/maps-compose)

