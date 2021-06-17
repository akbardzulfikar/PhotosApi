package com.akbar.photosapi.list_photo.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.akbar.photosapi.list_photo.data.RemotePhotoDataSource
import com.akbar.photosapi.list_photo.model.Photo
import com.akbar.photosapi.list_photo.network.RequestStatus
import com.akbar.photosapi.list_photo.network.State
import javax.inject.Inject

class RemotePhotoRepositoryImpl @Inject constructor
    (private val remoteMovieDataSource: RemotePhotoDataSource) :
    PhotoRepository {

    override fun getPhotos(
    ): LiveData<State<List<Photo>>> = liveData {
        val loadingState: State<List<Photo>> = State.loading()
        val errorState: State<List<Photo>>
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

    }
}
