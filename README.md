# android-moshi
Sample projects using Android Architecture Components to illustrate Moshi parsing of JSON into Kotlin objects with codegen and reflection

There are a number of libraries for parsing json into kotlin/java objects including Gson, Jackson and Moshi

[Moshi](https://github.com/square/moshi) is developed by Square and is targeted at the Android platform. It uses Kotlin laguage features to make parsing JSON data simpler and and more robust when handling null and absent responses. This repo illustrates some of Moshi's features and different implementations of how the library can be added in android projects using the reflection and codegen artifacts

### App Description and Implementation

Both samples use androidx and ViewModel, LiveData and Navigation architecture components to display a list of Popular movies from [The Movie Database](https://www.themoviedb.org) which has been added as a file in the Assets folder. Selecting a movie will display a movie detail page including title, overview, image, the popularity of the movie in terms of the votes received and the genres it belongs to. The genres demonstrate bespoke parsing with Moshi to map Genre ids to Genres from the ingested JSON.\
\
Unit tests use [JUnit 5](https://junit.org/junit5) and [Mockk](https://github.com/mockk/mockk)\
Integration Tests use [Espresso](https://developer.android.com/training/testing/espresso)

### Moshi Codegen and Reflection
The two samples differ only in how Moshi has been included and implemented with either the moshi-kotlin-codegen artifact for codegen and the moshi-kotlin artifact for reflection. The pros and cons of both are detailed in the [Moshi](https://github.com/square/moshi) repo.




