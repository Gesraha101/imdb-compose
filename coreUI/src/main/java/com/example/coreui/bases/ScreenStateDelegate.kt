package com.example.coreui.bases

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.core.domain.StateMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class ScreenStateDelegate @Inject constructor(
) {
    private var loadingShown = mutableStateOf(false)
    val isLoading get() = loadingShown
    private var errorShown = mutableStateOf(false)
    private var mError = MutableStateFlow<StateMessage?>(null)
    val error get() = mError

    fun showLoading() {
        loadingShown.value = true
        hideError()
    }

    fun hideLoading() {
        loadingShown.value = false
    }

    fun showError(
        errorTitle: String? = null,
        errorDesc: String? = null
    ) {
        errorShown.value = true
        mError.update { StateMessage(errorTitle, errorDesc) }
        hideLoading()
    }

    fun hideError() {
        errorShown.value = false
    }

    @Composable
    fun StatesContainer(
        Container: @Composable ScreenStatesScope.() -> Unit
    ) {
        val error by mError.collectAsStateWithLifecycle()
        val errorShown by remember { errorShown }

        val isLoading by remember { loadingShown }

        val navController = rememberNavController()
        val scaffoldState = rememberScaffoldState()

        Container(
            ScreenStatesScope(
                isLoading,
                errorShown,
                error,
                navController,
                scaffoldState
            )
        )
    }

    inner class ScreenStatesScope(
        val loadingShown: Boolean,
        val errorShown: Boolean,
        val error: StateMessage?,
        val navController: NavHostController,
        val scaffoldState: ScaffoldState
    )
}