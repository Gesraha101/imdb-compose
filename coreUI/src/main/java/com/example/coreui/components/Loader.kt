package com.example.coreui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color


@Composable
fun Loader(loadingShown: Boolean) {
    val interactionSource = remember { MutableInteractionSource() }
    AnimatedVisibility(visible = loadingShown, enter = fadeIn(), exit = fadeOut()) {
        Box(
            modifier = Modifier
                .clickable(interactionSource, null) {}
                .fillMaxWidth()
                .fillMaxHeight()
                .alpha(0.5f)
                .background(Color.Black)
        ) {
            CircularProgressIndicator(
                Modifier.align(Alignment.Center),
                color = MaterialTheme.colors.primary
            )
        }
    }
}