# MVVMRecipes_map

A food Recipes App with Nearby Restaurants Built with Jetpack Compose. The app uses API that follows the MVVM Clean Architectural pattern.

# Screenshots


# Demo Architecture
## 1.UI layer
The role of the UI layer (or presentation layer) is to display the application data on the screen. Whenever the data changes, either due to user interaction (such as pressing a button) or external input (such as a network response), the UI should update to reflect the changes. The UI layer is made up of two things:

UI elements that render the data on the screen. You build these elements using Views or Jetpack Compose functions.
State holders (such as ViewModel classes) that hold data, expose it to the UI, and handle logic.

## 2.Data layer
The data layer of an app contains the business logic. 
The business logic is what gives value to your app—it's
made of rules that determine how your app creates, stores,
and changes data. The data layer is made of repositories 
that each can contain zero to many data sources. 
You should create a repository class for each different
type of data you handle in your app.

## 3.Domain Layer
The domain layer is an optional layer that sits between the
UI and data layers. The domain layer is responsible for 
encapsulating complex business logic, or simple business 
logic that is reused by multiple ViewModels. This layer is
optional because not all apps will have these requirements.
You should use it only when needed—for example, to handle
complexity or favor reusability

# Tech Stack
- [Kotlin](https://kotlinlang.org/docs/home.html) - Kotlin is a cross-platform, statically typed,
'general-purpose programming language with type inference. 
Kotlin is designed to interoperate fully with Java, and the 
JVM version of Kotlin's standard library depends on the Java 
Class Library, but type inference allows its syntax to be more concise
- [Jetepack Components:](https://developer.android.com/topic/architecture?gclid=Cj0KCQjw8O-VBhCpARIsACMvVLOH1satX45o9f4PMQ4Sxr7bG9myl6-KZL9nYda8PJsHV7m2uJL8bzgaAmqiEALw_wcB&gclsrc=aw.ds)
