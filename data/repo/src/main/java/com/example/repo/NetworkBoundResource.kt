package com.example.repo

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import com.example.core.domain.DataState
import com.example.core.domain.StateMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext


abstract class NetworkBoundResource<DataModel, DomainModel>(var context: CoroutineContext = Dispatchers.IO) {

    fun asFlow() = flow {
        emit(DataState.Loading())
        if (shouldRemoteFetch()) {
            try {
                val result = fetchFromNetwork()
                result?.let {
                    handleApiSuccessResponse(it)?.let {
                        emit(it)
                    }
                    cacheRawResponse(it)
                }
            } catch (e: Exception) {
                if (hasCachedData())
                    loadFromCache()?.let {
                        handleCacheSuccessResponse(
                            it,
                            extractErrorStateMessage(e)
                        )?.let { emit(it) }
                    }
                else
                    emit(handleException(e))
            }
        } else if (hasCachedData())
            loadFromCache()?.let { handleCacheSuccessResponse(it, null)?.let { emit(it) } }
    }.flowOn(context)

    protected open fun handleApiSuccessResponse(response: DataModel): DataState<DomainModel>? =
        null

    @MainThread
    protected abstract fun shouldRemoteFetch(): Boolean

    @MainThread
    protected abstract suspend fun fetchFromNetwork(): DataModel

    @WorkerThread
    open suspend fun cacheRawResponse(item: DataModel) {
    }

    @MainThread
    protected open suspend fun loadFromCache(): DataModel? = null

    protected open fun handleCacheSuccessResponse(
        response: DataModel,
        message: StateMessage?
    ): DataState<DomainModel>? = handleApiSuccessResponse(response)

    protected open suspend fun hasCachedData(): Boolean = false

    protected open suspend fun handleException(e: java.lang.Exception): DataState<DomainModel> {
        return DataState.Error(extractErrorStateMessage(e))
    }

    private fun extractErrorStateMessage(e: java.lang.Exception) = StateMessage(message = e.message)
}
