package com.example.coreui.bases

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.DataState
import com.example.core.domain.StateMessage
import com.example.core.ui.ViewAction
import com.example.core.ui.ViewState
import kotlinx.coroutines.flow.*

abstract class BaseViewModel<ACTION : ViewAction, STATE : ViewState>(
    val screenStateDelegate: ScreenStateDelegate,
    protected val paginationDelegate: PaginationDelegate
) : ViewModel() {
    private val ongoingRequests = HashSet<ACTION>()

    protected val uiState = MutableStateFlow<DataState<STATE>?>(null)

    val isLoading get() = screenStateDelegate.isLoading.value
    val shouldShowError get() = screenStateDelegate.error.value

    fun Flow<DataState<STATE>?>.collectAsStateFlow() = stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        null
    )

    private fun Flow<DataState<out Any>>.map(action: ACTION, isPaging: Boolean): Flow<DataState<STATE>> {
        return map { dataState ->
            when (dataState) {
                is DataState.Loading -> {
                    DataState.Loading()
                }
                is DataState.Error -> {
                    if (isPaging)
                        paginationDelegate.updatePaginationVariables(false)
                    handleErrorState(action, dataState.stateMessage)
                }
                is DataState.Success -> {
                    if (isPaging) {
                        paginationDelegate.updatePaginationVariables(true, dataState.data as? List<Any>)
                        handleSuccessStatus(action, paginationDelegate.dataSet)
                    }
                    else if (dataState.cached) handleCachedStatus(
                        action,
                        dataState.data,
                        dataState.stateMessage
                    )
                    else handleSuccessStatus(action, dataState.data)
                }
            }
        }
    }

    private fun Flow<DataState<STATE>>.listen(action: ACTION): Flow<DataState<STATE>> {
        return onEach {
            when (it) {
                is DataState.Loading -> {
                    ongoingRequests.add(action)
                }
                is DataState.Error -> {
                    ongoingRequests.remove(action)
                    onFailedDataState(it)
                }
                is DataState.Success -> {
                    ongoingRequests.remove(action)
                    uiState.update { _ -> it }
                }
            }

            if (ongoingRequests.isEmpty())
                screenStateDelegate.hideLoading()
            else
                screenStateDelegate.showLoading()
        }
    }

    private fun onFailedDataState(it: DataState<*>) {
        screenStateDelegate.showError(it.stateMessage?.title, it.stateMessage?.message)
    }

    suspend fun dispatchSuspendAction(action: ACTION): DataState<STATE>? {
        return initAction(action).lastOrNull()
    }

    fun dispatchPagingAction(action: ACTION) {
        paginationDelegate.blockPaging()
        initAction(action, true).launchIn(viewModelScope)
    }

    fun dispatchAction(action: ACTION) {
        initAction(action).launchIn(viewModelScope)
    }

    private fun initAction(action: ACTION, isPaging: Boolean = false): Flow<DataState<STATE>> {
        return handleAction(action)
            .map(action, isPaging)
            .listen(action)
    }

    open fun handleErrorState(action: ACTION, message: StateMessage?) =
        DataState.Error<STATE>(stateMessage = message)

    open fun handleCachedStatus(
        action: ACTION,
        data: Any?,
        message: StateMessage?
    ): DataState<STATE> = DataState.Success()

    abstract fun handleSuccessStatus(action: ACTION, data: Any?): DataState<STATE>

    abstract fun handleAction(action: ACTION): Flow<DataState<out Any>>

    override fun onCleared() {
        paginationDelegate.reset()
        super.onCleared()
    }
}