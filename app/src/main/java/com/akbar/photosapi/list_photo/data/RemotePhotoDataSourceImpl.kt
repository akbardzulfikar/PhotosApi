package com.akbar.photosapi.list_photo.data

import com.akbar.photosapi.list_photo.model.Photo
import com.akbar.photosapi.list_photo.network.ApiService
import com.akbar.photosapi.list_photo.network.State
import javax.inject.Inject

class RemotePhotoDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : RemotePhotoDataSource {
    override suspend fun getPhotos(): State<List<Photo>> {
        apiService.getPhotos().let {
            if (it.isSuccessful) return State.success(it.body())
            return State.error(it.errorBody().toString())
        }
    }
}