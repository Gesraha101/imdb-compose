package com.example.coreui.bases

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import javax.inject.Inject

enum class ListState {
    IDLE, LOADING, END_OF_PAGINATION, ERROR
}

class PaginationDelegate @Inject constructor() {

    val dataSet = ArrayList<Any>()
    var page by mutableStateOf(1)
    private var canPaginate by mutableStateOf(true)
    private var listState by mutableStateOf(ListState.IDLE)

    val paginationAllowed: Boolean
        get() = canPaginate && listState == ListState.IDLE
    val extraPagesAvailable get() = listState != ListState.END_OF_PAGINATION

    fun blockPaging() {
        listState = ListState.LOADING
    }

    fun updatePaginationVariables(success: Boolean, list: List<Any>? = null) {
        if (success) {
            canPaginate = list?.size == 20

            list?.let { dataSet.addAll(it) }
            listState = ListState.IDLE

            page++
        } else
            listState = if (page == 1) ListState.ERROR else ListState.END_OF_PAGINATION
    }

    fun reset() {
        page = 1
        dataSet.clear()
        listState = ListState.IDLE
        canPaginate = false
    }
}