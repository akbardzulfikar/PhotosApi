package com.akbar.photosapi.list_photo.repository

import androidx.lifecycle.LiveData
import com.akbar.photosapi.list_photo.model.Photo
import com.akbar.photosapi.list_photo.network.State

interface PhotoRepository {
    fun getPhotos(): LiveData<State<List<Photo>>>
}