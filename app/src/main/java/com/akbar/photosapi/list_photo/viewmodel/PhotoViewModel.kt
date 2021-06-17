package com.akbar.photosapi.list_photo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.akbar.photosapi.list_photo.model.Photo
import com.akbar.photosapi.list_photo.network.State
import com.akbar.photosapi.list_photo.repository.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(private val remotePhotoRepository: PhotoRepository) :
    ViewModel() {

    fun getPhotos(): LiveData<State<List<Photo>>> = remotePhotoRepository.getPhotos()
}