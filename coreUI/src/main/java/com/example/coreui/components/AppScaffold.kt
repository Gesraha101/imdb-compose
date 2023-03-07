package com.example.coreui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.core.domain.StateMessage
import com.example.coreui.theme.MyApplicationTheme

@Composable
fun AppScaffold(
    error: StateMessage?,
    errorShown: Boolean,
    isLoading: Boolean,
    onErrorActionClick: () -> Unit,
    topBar: @Composable () -> Unit = {},
    content: @Composable () -> Unit
) {
    MyApplicationTheme {
        Scaffold(
            topBar = {
                topBar()
            },
            content = {
                Box(modifier = Modifier.padding(it)) { content() }
            })
        Loader(isLoading)
        ErrorDialog(onErrorActionClick, error, errorShown)
    }
}