package com.akbar.photosapi.list_photo.network

import com.akbar.photosapi.list_photo.model.PhotoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("photos")
    suspend fun getPhotos(
    ): Response<PhotoResponse>
}