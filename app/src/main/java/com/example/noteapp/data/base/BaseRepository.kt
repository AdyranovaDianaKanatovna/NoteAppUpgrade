package com.example.noteapp.data.base

import com.example.noteapp.core.Resource
import kotlinx.coroutines.flow.flow

abstract class BaseRepository {

    protected fun <T> doRequest(request: suspend () -> T) = flow{
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = request()))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error(data = null, message = e.localizedMessage?: "Unknown error"))
        }
    }
}