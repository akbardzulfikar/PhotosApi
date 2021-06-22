package com.akbar.photosapi.list_photo.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.akbar.photosapi.R
import com.akbar.photosapi.list_photo.data.RemotePhotoDataSource
import com.akbar.photosapi.list_photo.model.Photo
import com.akbar.photosapi.list_photo.network.RequestStatus
import com.akbar.photosapi.list_photo.network.State
import dagger.hilt.android.qualifiers.ApplicationContext
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

class RemotePhotoRepositoryImpl @Inject constructor
    (
    private val remoteMovieDataSource: RemotePhotoDataSource,
    @ApplicationContext private val context: Context
) :
    PhotoRepository {

    override fun getPhotos(
    ): LiveData<State<List<Photo>>> = liveData {
        val loadingState: State<List<Photo>> = State.loading()
        var errorState: State<List<Photo>>
        try {
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
        } catch (e: Exception) {
            errorState = State.error(e.toString())
            when (e) {
                is UnknownHostException ->
                    errorState = context.getString(R.string.error_server).let {
                        State.error(
                            it
                        )
                    }
                is ConnectException ->
                    errorState = context.getString(R.string.error_server).let {
                        State.error(
                            it
                        )
                    }
            }
            emit(errorState)
        }
    }
}
