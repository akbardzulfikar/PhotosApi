package com.akbar.photosapi.list_photo.data

import android.util.Log
import com.akbar.photosapi.list_photo.model.Photo
import com.akbar.photosapi.list_photo.network.ApiResponse
import com.akbar.photosapi.list_photo.network.ApiService
import com.akbar.photosapi.list_photo.network.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemotePhotoDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : RemotePhotoDataSource {
    override suspend fun getPhotos(): Flow<ApiResponse<List<Photo>>> {
        return flow {
            try {
                val response = apiService.getPhotos()
                if (response.isNullOrEmpty()){
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
//                apiService.getPhotos().let {
//                    if (it.isSuccessful) emit(State.success(it.body()))
//                }
            }
            catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }

    }
}