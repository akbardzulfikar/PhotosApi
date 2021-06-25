package com.akbar.photosapi.list_photo.repository

import androidx.lifecycle.LiveData
import com.akbar.photosapi.list_photo.model.Photo
import com.akbar.photosapi.list_photo.model.PhotoResponse
import com.akbar.photosapi.list_photo.network.Resource
import com.akbar.photosapi.list_photo.network.State
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {
    fun getPhotos(): Flow<Resource<List<PhotoResponse>>>
}