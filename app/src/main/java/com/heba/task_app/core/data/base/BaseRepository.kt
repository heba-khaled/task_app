package com.heba.task_app.core.data.base

import com.heba.task_app.core.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

abstract class BaseRepository {
    suspend fun <MODEL> executeFun(request: suspend () -> MODEL): Flow<Resource<MODEL>> =
        flow {
            emit(Resource.Loading(true))
            val responseStatus = request.invoke()
            emit(Resource.Success(responseStatus))
        }.catch {
            emit(Resource.Failure("Something went wrong"))
        }
}