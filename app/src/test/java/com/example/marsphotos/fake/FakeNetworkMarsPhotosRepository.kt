package com.example.marsphotos.fake

import com.example.marsphotos.data.MarsPhotosRepository
import com.example.marsphotos.model.MarsPhoto

/**
 * write a test for the getMarsPhotos() function from the MarsViewModel, which depends on the MarsPhotosRepository.
 * by creating a fake MarsPhotosRepository.
 * Additionally, there are some extra steps to take into account for coroutines beyond using the runTest() method.
 *
 */
class FakeNetworkMarsPhotosRepository : MarsPhotosRepository{

// create a fake class that inherits from the MarsPhotosRepository interface and overrides the getMarsPhotos() function to return fake data
    override suspend fun getMarsPhotos(): List<MarsPhoto> {
        return FakeDataSource.photosList
    }
}