This is a project which using modern Android Architecture Components (Kotlin, Coroutines, MVVM, Paging 3, Navigation, Room, ViewModel).

## The Movie Database

This app uses the [TheMovieDb API](https://www.themoviedb.org/documentation/api) to load movies on the main
screen. To use the API, you will need to obtain a free developer API key. See the
[TheMovieDb API Documentation](https://developers.themoviedb.org/3/getting-started/introduction) for instructions.

Once you have the key, add this line to the `local.properties` file, either in your user home
directory (usually `~/.gradle/local.properties` on Linux and Mac) or in the project's root folder:

```
api_key=<your TheMovieDb API key>
base_url=https://api.themoviedb.org/3/
```

## Libraries Used

- [Foundation][0] - Components for core system capabilities, Kotlin extensions and support for
  multidex and automated testing.
  - [Android KTX][1] - Write more concise, idiomatic Kotlin code.
- [Architecture][5] - A collection of libraries that help you design robust, testable, and
  maintainable apps. Start with classes for managing your UI component lifecycle and handling data
  persistence.
  - [LiveData][6] - Build data objects that notify views when the underlying database changes.
  - [Navigation][7] - Handle everything needed for in-app navigation.
  - [Room][8] - Access your app's SQLite database with in-app objects and compile-time checks.
  - [ViewModel][9] - Store UI-related data that isn't destroyed on app rotations. Easily schedule
    asynchronous tasks for optimal execution.
- [UI][10] - Details on why and how to use UI Components in your apps - together or separate
  - [Animations & Transitions][11] - Move widgets and transition between screens.
  - [Fragment][12] - A basic unit of composable UI.
  - [Layout][13] - Lay out widgets using different algorithms.
  - [Paging 3][4] - For implementing paging
  - [SplashScreen API][16] - Modern Splash Screen Android 12 without using extra activities
- Third party and miscellaneous libraries
  - [Glide][14] for image loading
  - [Dagger Hilt][2] for [Dependency Injection][3]
  - [Kotlin Coroutines][15] for managing background threads with simplified code and reducing needs for callbacks
  - [Secrets Gradle plugin][17] for Android reads secrets, including the API key, from a properties file not checked into a version control system

[0]: https://developer.android.com/jetpack/components
[1]: https://developer.android.com/kotlin/ktx
[2]: https://dagger.dev/hilt/
[3]: https://developer.android.com/training/dependency-injection/dagger-android
[4]: https://developer.android.com/topic/libraries/architecture/paging/v3-overview
[5]: https://developer.android.com/jetpack/arch/
[6]: https://developer.android.com/topic/libraries/architecture/livedata
[7]: https://developer.android.com/topic/libraries/architecture/navigation/
[8]: https://developer.android.com/topic/libraries/architecture/room
[9]: https://developer.android.com/topic/libraries/architecture/viewmodel
[10]: https://developer.android.com/guide/topics/ui
[11]: https://developer.android.com/training/animation/
[12]: https://developer.android.com/guide/components/fragments
[13]: https://developer.android.com/guide/topics/ui/declaring-layout
[14]: https://bumptech.github.io/glide/
[15]: https://kotlinlang.org/docs/reference/coroutines-overview.html
[16]: https://developer.android.com/develop/ui/views/launch/splash-screen
[17]: https://developers.google.com/maps/documentation/android-sdk/secrets-gradle-plugin

![App Preview](https://user-images.githubusercontent.com/59298779/200995848-2275d7c8-1619-42dc-bc04-3687d0219757.png)
