package com.akbar.photosapi.list_photo.room.db.module

import android.content.Context
import androidx.room.Room
import com.akbar.photosapi.list_photo.room.db.PhotoDatabase
import com.akbar.photosapi.list_photo.room.db.dao.PhotoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun providePhotoDatabase(@ApplicationContext context: Context): PhotoDatabase = Room.databaseBuilder(
        context,
        PhotoDatabase::class.java,
        "photo_db"
    ).build()

    @Singleton
    @Provides
    fun providePhotoDao(db: PhotoDatabase): PhotoDao = db.photoDao()
}