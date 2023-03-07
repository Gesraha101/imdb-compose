package com.example.core.domain

sealed class DataState<T>(
    var data: T? = null,
    var stateMessage: StateMessage? = null,
) {
    class Loading<T>(
        data: T? = null,
    ) : DataState<T>()

    open class Success<T>(
        data: T? = null,
        stateMessage: StateMessage? = null,
        val cached: Boolean = false
    ) : DataState<T>(
        data = data,
        stateMessage = stateMessage
    )

    class PageSuccess<T>(
        data: T? = null,
        stateMessage: StateMessage? = null,
        cached: Boolean = false,
        val extraPagesAvailable: Boolean = true
    ) : Success<T>(
        data = data,
        stateMessage = stateMessage,
        cached = cached
    )

    class Error<T>(
        stateMessage: StateMessage? = null,
        data: T? = null
    ) : DataState<T>(
        data = data,
        stateMessage = stateMessage
    )
}
