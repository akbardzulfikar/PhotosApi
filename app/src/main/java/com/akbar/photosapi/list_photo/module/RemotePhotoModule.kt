package com.akbar.photosapi.list_photo.module

import com.akbar.photosapi.list_photo.data.RemotePhotoDataSource
import com.akbar.photosapi.list_photo.data.RemotePhotoDataSourceImpl
import com.akbar.photosapi.list_photo.repository.PhotoRepository
import com.akbar.photosapi.list_photo.repository.RemotePhotoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemotePhotoModule {

    @Singleton
    @Provides
    fun provideRemotePhotoDataSource(remotePhotoDataSource: RemotePhotoDataSourceImpl): RemotePhotoDataSource =
        remotePhotoDataSource

    @Singleton
    @Provides
    fun providePhotoMovieRepository(remotePhotoRepositoryImpl: RemotePhotoRepositoryImpl): PhotoRepository =
        remotePhotoRepositoryImpl
}