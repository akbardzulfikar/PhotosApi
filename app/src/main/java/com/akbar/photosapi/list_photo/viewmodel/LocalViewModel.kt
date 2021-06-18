package com.akbar.photosapi.list_photo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.akbar.photosapi.list_photo.model.Photo
import com.akbar.photosapi.list_photo.network.State
import com.akbar.photosapi.list_photo.room.helper.InsertResponse
import com.akbar.photosapi.list_photo.room.repository.LocalPhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

    fun <R> execute(
        onPreExecute: () -> Unit,
        doInBackground: () -> R,
        onPostExecute: (R) -> Unit
    ) = viewModelScope.launch {
        onPreExecute()
        val result = withContext(Dispatchers.IO) { // runs in background thread without blocking the Main Thread
            doInBackground()
        }
        onPostExecute(result)
    }
}