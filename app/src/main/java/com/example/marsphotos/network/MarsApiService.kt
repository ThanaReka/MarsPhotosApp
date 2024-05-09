package com.example.marsphotos.network

import com.example.marsphotos.model.MarsPhoto
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
//import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

//private const val BASE_URL =
//    "https://android-kotlin-fun-mars-server.appspot.com"
//
//// the following is a Retrofit builder to build and create a Retrofit object
//
//private val retrofit = Retrofit.Builder()
////    .addConverterFactory(ScalarsConverterFactory.create())
//    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
//    .baseUrl(BASE_URL)
//    .build()

// This interface defines the structure of the API calls and Retrofit handles the communication
/**
 * A public interface that exposes the [getPhotos] method
 */
interface MarsApiService {
    @GET("photos")
//    suspend fun getPhotos(): String
    suspend fun getPhotos(): List<MarsPhoto>
    /**
     * function to get the response string from the web service by appending the endpoint "photos"
     * to the base URL (defined in the Retrofit builder) used to start the request.
     * Add a return type of the function to String.
     **/

    /**
     * Returns a [List] of [MarsPhoto] and this method can be called from a Coroutine.
     * The @GET annotation indicates that the "photos" endpoint will be requested with the GET
     * HTTP method
     */


}

// object declarations used to declare singleton objects
// Note: Use Dependency injection over singleton pattern

//object MarsApi {
    // You make this lazy initialization to make sure it is initialized at its first usage.
//    val retrofitService : MarsApiService by lazy {
// Initialize the retrofitService variable using the retrofit.create() method with the MarsApiService interface
//        retrofit.create(MarsApiService::class.java)
//    }
//}
