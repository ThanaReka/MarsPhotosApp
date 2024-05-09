package com.example.marsphotos.fake

import com.example.marsphotos.data.NetworkMarsPhotosRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * to test the getMarsPhotos() method of the NetworkMarsPhotosRepository class, clarifying the
 * usage of fake classes and demonstrate how to test coroutines.
 */

/**
 * By passing the fake API service, any calls to the marsApiService property in the repository result
 * in a call to the FakeMarsApiService. By passing fake classes for dependencies, you can control
 * exactly what the dependency returns. This approach ensures that the code you are testing doesn't
 * depend on untested code or APIs that could change or have unforeseen problems. Such situations
 * can cause your test to fail, even when nothing is wrong with the code you wrote. Fakes help create
 * a more consistent test environment, reduce test flakiness, and facilitate concise tests that test a single functionality.
 */

class NetworkMarsRepositoryTest {

    // leverage the fake API service from the previous section

    /**
     * You must also call suspend functions, like getMarsPhotos(), from a coroutine in a test.
     * However, the approach is different.
     * @Test
     *     fun networkMarsPhotosRepository_getMarsPhotos_verifyPhotoList(){
     *         val repository = NetworkMarsPhotosRepository(
     *             marsApiService = FakeMarsApiService()
     *         )
     *         assertEquals(FakeDataSource.photosList, repository.getMarsPhotos())
     *     }
      */

    /**
     *  The coroutine test library provides the runTest() function which takes the method that you
     *  passed in the lambda and runs it from TestScope, which inherits from CoroutineScope.
     */

    @Test
    fun networkMarsPhotosRepository_getMarsPhotos_verifyPhotoList() =
        runTest {
            val repository = NetworkMarsPhotosRepository(
                marsApiService = FakeMarsApiService()
            )
            assertEquals(FakeDataSource.photosList, repository.getMarsPhotos())
        }

}