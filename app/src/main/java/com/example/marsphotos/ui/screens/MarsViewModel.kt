package com.example.marsphotos.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
//import com.example.marsphotos.network.MarsApi
import kotlinx.coroutines.launch
import java.io.IOException
import com.example.marsphotos.data.NetworkMarsPhotosRepository
import retrofit2.HttpException
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.marsphotos.MarsPhotosApplication
import com.example.marsphotos.data.MarsPhotosRepository
import com.example.marsphotos.model.MarsPhoto

// the addition of the sealed interface makes the values MarsUiState object can have exhaustive.
/**
 * In the case of Loading and Error states, you don't need to set new data and create new objects;
 * you are just passing the web response. Change the data class to Object to create the objects
 * for the web responses.
 */
sealed interface MarsUiState {
//    data class Success(val photos: MarsPhoto) : MarsUiState
    data class Success(val photos: List<MarsPhoto>) : MarsUiState
    object Error : MarsUiState
    object Loading : MarsUiState
}

/**
 * private constructor parameter of type MarsPhotosRepository coming from the application container
 * is added because the app is now using dependency injection.
 */
class MarsViewModel (private val marsPhotosRepository: MarsPhotosRepository) : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
//    var marsUiState: String by mutableStateOf("")
    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)
        private set

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getMarsPhotos()
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [MutableList].
     */
//    private fun getMarsPhotos() {
    fun getMarsPhotos() {
//        marsUiState = "Set the Mars API status response here!"
        viewModelScope.launch {
            /**
             * use the singleton object MarsApi to call the getPhotos() method from the retrofitService
             * interface and Save the returned response in a val called listResult.
             */
            marsUiState = try {
//                val listResult = MarsApi.retrofitService.getPhotos()
//                marsUiState = listResult
//                marsUiState = MarsUiState.Success(listResult)
//                val marsPhotosRepository = NetworkMarsPhotosRepository()
//                val listResult = marsPhotosRepository.getMarsPhotos()
//                MarsUiState.Success("Success: ${listResult.size} Mars photos retrieved")
//                    MarsUiState.Success(marsPhotosRepository.getMarsPhotos()[0])
                MarsUiState.Success(marsPhotosRepository.getMarsPhotos())
//                MarsUiState.Success(listResult)
            }
            catch(e: IOException){
//                marsUiState = MarsUiState.Error
                MarsUiState.Error
            }
            catch(e: HttpException){
//                marsUiState = MarsUiState.Error
                MarsUiState.Error
            }

//            val listResult = MarsApi.retrofitService.getPhotos()
        }
    }

    /**
     * Because the Android framework does not allow a ViewModel to be passed values in the constructor
     * when created, we implement a ViewModelProvider.Factory object, which lets us get around this limitation.
     */

// Factory for [MarsViewModel] that takes [MarsPhotosRepository] as a dependency
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MarsPhotosApplication)
                val marsPhotosRepository = application.container.marsPhotosRepository
                MarsViewModel(marsPhotosRepository = marsPhotosRepository)
            }
        }
    }
}

/**
 * A viewModelScope is the built-in coroutine scope defined for each ViewModel in your app.
 * Any coroutine launched in this scope is automatically canceled if the ViewModel is cleared.
 * You can use viewModelScope to launch the coroutine and make the web service request in the background.
 * Since the viewModelScope belongs to the ViewModel, the request continues even if the app goes through
 * a configuration change.
 */

