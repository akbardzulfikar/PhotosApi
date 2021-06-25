package com.akbar.photosapi.list_photo.data

import com.akbar.photosapi.list_photo.model.Photo
import com.akbar.photosapi.list_photo.network.ApiResponse
import com.akbar.photosapi.list_photo.network.State
import kotlinx.coroutines.flow.Flow

interface RemotePhotoDataSource {
    suspend fun getPhotos(): Flow<ApiResponse<List<Photo>>>
}