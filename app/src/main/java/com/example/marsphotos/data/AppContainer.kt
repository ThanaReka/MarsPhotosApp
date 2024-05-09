package com.example.marsphotos.data
import retrofit2.Retrofit
import com.example.marsphotos.network.MarsApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType

/**
 * Dependency Injection container at the application level.
 */
interface AppContainer {
    val marsPhotosRepository: MarsPhotosRepository
}

/**
 * Implementation for the Dependency Injection container at the application level.
 *
 * Variables are initialized lazily and the same instance is shared across the whole app.
 */

class DefaultAppContainer : AppContainer {

    /**
     * the code for variables BASE_URL, retrofit, and retrofitService so that
     * they are all located within the container that maintains the dependencies.
     */

//    private const val BASE_URL =
    /**
     * remove the const keyword and refactor it to camelcase baseUrl since BASE_URL is no longer
     * a top level variable and is now a property of the DefaultAppContainer class.
     */

    /**
     * Use the Retrofit builder to build a retrofit object using a kotlinx.serialization converter
     */

    private val baseUrl =
        "https://android-kotlin-fun-mars-server.appspot.com"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    /**
     * Retrofit service object for creating api calls
     */

    /**
     * private modifier is added because variable retrofitService is only used inside the class
     * by property marsPhotosRepository, so it does not need to be accessible outside the class.
     */

    private val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }

    /**
     * DI implementation for Mars photos repository
     */

    /**
     * override the marsPhotosRepository property since The DefaultAppContainer class implements
     * the interface AppContainer
     */

    override val marsPhotosRepository: MarsPhotosRepository by lazy {
        NetworkMarsPhotosRepository(retrofitService)
    }



}