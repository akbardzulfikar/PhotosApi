package com.akbar.photosapi.list_photo.room.data

import androidx.paging.DataSource
import com.akbar.photosapi.list_photo.model.Photo
import com.akbar.photosapi.list_photo.room.db.dao.PhotoDao
import javax.inject.Inject

class LocalPhotoDataSourceImpl @Inject constructor(
    private val photoDao: PhotoDao
) : LocalPhotoDataSource {
    override fun getAllPhoto(): DataSource.Factory<Int, Photo> {
        return photoDao.getAll()
    }

    override suspend fun insertPhoto(photo: Photo) {
        return photoDao.insert(photo)
    }
}