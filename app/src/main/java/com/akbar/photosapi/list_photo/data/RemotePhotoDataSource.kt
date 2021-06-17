package com.akbar.photosapi.list_photo.data

import com.akbar.photosapi.list_photo.model.Photo
import com.akbar.photosapi.list_photo.network.State

interface RemotePhotoDataSource {
    suspend fun getPhotos(): State<List<Photo>>
}