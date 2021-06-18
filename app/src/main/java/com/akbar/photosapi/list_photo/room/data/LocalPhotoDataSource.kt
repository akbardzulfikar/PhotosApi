package com.akbar.photosapi.list_photo.room.data

import androidx.paging.DataSource
import com.akbar.photosapi.list_photo.model.Photo

interface LocalPhotoDataSource {
    fun getAllPhoto(): DataSource.Factory<Int, Photo>
    suspend fun insertPhoto(photo: Photo)
}