package com.akbar.photosapi.list_photo.room.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.akbar.photosapi.list_photo.model.Photo
import com.akbar.photosapi.list_photo.network.State
import com.akbar.photosapi.list_photo.room.data.LocalPhotoDataSource
import com.akbar.photosapi.list_photo.room.helper.InsertResponse
import javax.inject.Inject

class LocalPhotoRepositoryImpl @Inject constructor(
    private val localPhotoDataSource: LocalPhotoDataSource
) : LocalPhotoRepository {
    override fun getAllPhoto(): LiveData<PagedList<Photo>> {

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10)
            .setPageSize(10)
            .build()

        return LivePagedListBuilder(localPhotoDataSource.getAllPhoto(), config).build()
    }

    override fun insertPhoto(photo: Photo): LiveData<State<InsertResponse>> = liveData {
        localPhotoDataSource.insertPhoto(photo)
        emit(State.success(InsertResponse.INSERTED))
    }
}