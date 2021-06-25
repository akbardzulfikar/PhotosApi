package com.akbar.photosapi.list_photo.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.akbar.photosapi.list_photo.model.Photo
import com.akbar.photosapi.list_photo.room.db.dao.PhotoDao

@Database(entities = [Photo::class], version = 1, exportSchema = false)
abstract class PhotoDatabase : RoomDatabase() {
    abstract fun photoDao(): PhotoDao
}