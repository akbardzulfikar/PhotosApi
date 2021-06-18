package com.akbar.photosapi.list_photo.room.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.akbar.photosapi.list_photo.model.Photo
import com.akbar.photosapi.list_photo.network.State
import com.akbar.photosapi.list_photo.room.helper.InsertResponse

interface LocalPhotoRepository {
    fun getAllPhoto(): LiveData<PagedList<Photo>>
    fun insertPhoto(photo: Photo): LiveData<State<InsertResponse>>
}