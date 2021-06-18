package com.akbar.photosapi.list_photo.repository

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.akbar.photosapi.MyApplication
import com.akbar.photosapi.list_photo.data.RemotePhotoDataSource
import com.akbar.photosapi.list_photo.model.Photo
import com.akbar.photosapi.list_photo.network.NetworkHelper
import com.akbar.photosapi.list_photo.network.RequestStatus
import com.akbar.photosapi.list_photo.network.State
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class RemotePhotoRepositoryImpl @Inject constructor
    (private val remoteMovieDataSource: RemotePhotoDataSource, private val networkHelper: NetworkHelper) :
    PhotoRepository {

    override fun getPhotos(
    ): LiveData<State<List<Photo>>> = liveData {
        val loadingState: State<List<Photo>> = State.loading()
        val errorState: State<List<Photo>>
        if (networkHelper.isNetworkConnected()) {
            emit(loadingState)
            val response =
                remoteMovieDataSource.getPhotos()
            when (response.requestStatus) {
                RequestStatus.ERROR -> {
                    errorState = State.error(response.message ?: "")
                    emit(errorState)
                }
                else -> {
                    emit(State.success(response.data))
                }
            }
        } else {
            errorState = State.error("No internet connection")
            emit(errorState)
//            Toast.makeText(MyApplication.applicationContext(), errorState.toString(), Toast.LENGTH_LONG).show()
        }

    }
}
