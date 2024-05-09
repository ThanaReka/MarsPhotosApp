package com.example.marsphotos.data

import com.example.marsphotos.model.MarsPhoto
//import com.example.marsphotos.network.MarsApi
import com.example.marsphotos.network.MarsApiService

/**
 * Repository that fetch mars photos list from marsApi.
 */
interface MarsPhotosRepository {
    /** abstract function returning a list of MarsPhoto objects and called from a coroutine, so
     * declare it with suspend. Fetches list of MarsPhoto from marsApi */
    suspend fun getMarsPhotos(): List<MarsPhoto>
}

/**
 * Network Implementation of Repository that fetch mars photos list from marsApi.
 */
// class to implement the MarsPhotosRepository interface.
/**
 * since retrofitService is passed to NetworkMarsPhotosRepository, you need to add the
 * constructor parameter as follows.
 */
class NetworkMarsPhotosRepository(
    private val marsApiService: MarsApiService
) : MarsPhotosRepository {
//    override suspend fun getMarsPhotos(): List<MarsPhoto> {
//      return MarsApi.retrofitService.getPhotos()
//}
    /** Fetches list of MarsPhoto from marsApi*/
override suspend fun getMarsPhotos(): List<MarsPhoto> = marsApiService.getPhotos()

}