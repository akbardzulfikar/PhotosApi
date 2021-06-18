package com.akbar.photosapi.list_photo.room.module

import com.akbar.photosapi.list_photo.room.data.LocalPhotoDataSource
import com.akbar.photosapi.list_photo.room.data.LocalPhotoDataSourceImpl
import com.akbar.photosapi.list_photo.room.repository.LocalPhotoRepository
import com.akbar.photosapi.list_photo.room.repository.LocalPhotoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalPhotoModule {

    @Singleton
    @Provides
    fun providePhotoDataSource(photoDataSource: LocalPhotoDataSourceImpl): LocalPhotoDataSource = photoDataSource

    @Singleton
    @Provides
    fun providePhotoRepository(photoRepository: LocalPhotoRepositoryImpl): LocalPhotoRepository = photoRepository

}