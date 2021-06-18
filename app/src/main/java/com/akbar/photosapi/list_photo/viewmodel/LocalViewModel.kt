package com.akbar.photosapi.list_photo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.akbar.photosapi.list_photo.model.Photo
import com.akbar.photosapi.list_photo.network.State
import com.akbar.photosapi.list_photo.room.helper.InsertResponse
import com.akbar.photosapi.list_photo.room.repository.LocalPhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocalViewModel @Inject constructor(
    private val localPhotoRepository: LocalPhotoRepository
) : ViewModel() {
    fun getAllPhotos(): LiveData<PagedList<Photo>> {
        return localPhotoRepository.getAllPhoto()
    }

    fun insertPhoto(photo: Photo): LiveData<State<InsertResponse>> {
        return localPhotoRepository.insertPhoto(photo)
    }

    fun searchTitle(photo: String) : List<Photo>? {
        return localPhotoRepository.searchTitle(photo)
    }
}