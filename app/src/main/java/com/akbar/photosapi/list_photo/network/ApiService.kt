package com.akbar.photosapi.list_photo.network

import com.akbar.photosapi.list_photo.model.Photo
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("photos")
    suspend fun getPhotos(
    ): List<Photo>
}