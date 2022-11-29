package com.example.clean_architecture.data.base

import com.example.clean_architecture.data.mapper.toEntity
import com.example.clean_architecture.domain.model.Note
import com.example.clean_architecture.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException

abstract class BaseRepository {

    protected fun <T> doRequest(request : suspend ()-> T )= flow{
        emit(Resource.Loading())
        try {
            val data = request()
            emit(Resource.Success(data))
        }catch (ioException: IOException){
            emit(Resource.Error(ioException.localizedMessage?:"Unknown exception"))
        }
    }.flowOn(Dispatchers.IO)
}