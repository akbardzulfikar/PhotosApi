package com.akbar.photosapi.list_photo.network

import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<ResultType, RequestType> {

    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
//        val dbSource = loadFromDB().first()
        when (val apiResponse = createCall().first()) {
            is ApiResponse.Success -> {
                Resource.Success(apiResponse.data)
            }
            is ApiResponse.Empty -> {
                Resource.Success(apiResponse)
            }
            is ApiResponse.Error -> {
                onFetchFailed()
                emit(Resource.Error<ResultType>(apiResponse.errorMessage))
            }
        }
    }

    protected open fun onFetchFailed() {}

//    protected abstract fun loadFromDB(): Flow<ResultType>
//
//    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

//    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<Resource<ResultType>> = result
}