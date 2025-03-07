# The Movie App
TheMovieApp is an Android application developed in Kotlin, allowing users to explore movie information via [The Movie Database](https://developers.themoviedb.org/3) API.

## How to run
1. Open Android Studio
2. Clone the repository
3. To access The Movie Database (TMDb) API, you must obtain an Access Token: Follow these steps [TMDB](https://developers.themoviedb.org/3/getting-started/introduction)
4. Replace the following line in the `gradle.properties` file: `access_token`
5. Sync the project in Android Studio, Clean & Rebuild project
6. Start the emulator and click Run in Android Studio to install and launch the app.

## Technologies used
* [Kotlin](https://kotlinlang.org/): Modern, safe, and efficient programming language for Android.
* [Jetpack Compose](https://developer.android.com/compose): Declarative framework for building modern UI reactively and modularly.
* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel): Manages UI-related data in a lifecycle-conscious way.
* [Flow](https://developer.android.com/kotlin/flow): Handles asynchronous data streams efficiently within Kotlin Coroutines.
* [Retrofit2](https://github.com/square/retrofit): Eases communication with REST APIs through HTTP requests.
* [Room](https://developer.android.com/jetpack/androidx/releases/room): ORM for managing SQLite databases easily and efficiently.
* [Hilt](https://dagger.dev/hilt/): Simplifies dependency injection and enhances scalability.
* [Glide](https://github.com/bumptech/glide): Efficiently loads and optimizes images.

## Architecture
The project follows Clean Architecture with MVVM, organizing code into well-structured layers:

### Data Layer
  * Handles data access logic
  * Uses Room for local storage and Retrofit for API calls

`MoviesDataBase`, `PopularMoviesDao` and `MovieDetailsDao` these are used for the offline operation of the app. They have methods to save/get data from a database.

`MovieApi` and `AuthInterceptor` these are used for the operation of the online app. They consume data from the API.

`*Mapper` and `*Converter` these facilitate the transformation of data between layers, from data to domain for use in presentation, and from domain to data for local persistence.

`PopularMoviesRemoteDataSource`,`PopularMoviesLocalDataSource`, `MovieDetailsRemoteDataSource`, `MovieDetailsLocalDataSource`, `PopularMoviesRepository` and `MovieDetailsRepository` these are use for managing the consume of the API and the Database as needed.

### Domain Layer 
  * Defines Use Cases to manage business logic.

`GetPopularMoviesUseCase`,`GetMovieDetailsUseCase`, `PopularMovies` and `MovieDetails` these contain the data that is transmitted to the presentation layer to display in the UI.

###  Presentation Layer 
  * Implements ViewModels and Jetpack Compose for UI.

`PopularMoviesScreen` and `MovieDetailsScreen` these contain the views that the user sees and handle user interactions.

`PopularMoviesViewModel`, `MovieDetailsViewModel` and `UiState` ViewModel was used as a bridge between Presentation and Domain. User interaction with the view calls the `ViewModel`, which obtains data from Domain and communicates it through view states to identify loader, data, and errors using `Flow`

`MainNavGraph` and `Route` these contain the definition of the graph for navigation within the application and the routes to follow.

* DI (Dependency Injection) Layer
  * Uses Hilt for dependency injection, improving modularization.

`NetworkModule`,`DataBaseModule`, `DataSourceModule`, `RepositoryModule` and `UseCaseModule` these contain the declaration of the singletons and provides dependency injection with Hilt

* Core Layer
  * Contains shared common code across modules.

`App` enables Hilt code generation, including a base class for your application that serves as an application-level dependency container.
