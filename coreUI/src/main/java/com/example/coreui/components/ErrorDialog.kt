package com.example.coreui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.core.domain.StateMessage


@Composable
fun ErrorDialog(doOnTryAgain: () -> Unit, error: StateMessage?, errorShown: Boolean) {
    val interactionSource = remember { MutableInteractionSource() }
    val context = LocalContext.current
    AnimatedVisibility(visible = errorShown, enter = fadeIn(), exit = fadeOut()) {
        Box(
            modifier = Modifier
                .clickable(interactionSource, null) {}
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.85F))
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth(0.85F)
                    .align(Alignment.Center)
                    .padding(top = 12.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.Gray)
            ) {

                val (errorImage, title, description, horizontalDivider, mainButton) = createRefs()

                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(android.R.drawable.stat_notify_error)
                        .build(),
                    contentDescription = "Placeholder",
                    modifier = Modifier
                        .padding(16.dp)
                        .size(24.dp)
                        .constrainAs(errorImage) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                        },
                )
                Text(
                    error?.title
                        ?: stringResource(com.example.core.R.string.general_error_title),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .padding(horizontal = 24.dp)
                        .constrainAs(title) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(errorImage.bottom)
                        }, color = Color.White
                )
                Text(
                    error?.message ?: stringResource(com.example.core.R.string.general_error_description),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .padding(horizontal = 24.dp)
                        .constrainAs(description) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(title.bottom)
                        }, color = Color.White
                )
                Divider(
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .constrainAs(horizontalDivider) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(description.bottom)
                        }, color = Color.White
                )
                Text(
                    stringResource(com.example.core.R.string.ok),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { doOnTryAgain() }
                        .constrainAs(mainButton) {
                            width = Dimension.fillToConstraints
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(horizontalDivider.bottom)
                        }
                        .padding(vertical = 12.dp), color = Color.White
                )
            }
        }
    }
}