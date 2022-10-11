package com.adrc95.unsplashsample.ui.detail

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.adrc95.domain.model.Author
import com.adrc95.domain.model.Camera
import com.adrc95.domain.model.Photo
import com.adrc95.unsplashsample.R
import com.adrc95.unsplashsample.ui.common.component.ImageUrl

@Composable
fun DetailScreen(viewModel: DetailViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    DetailScreenView(state)
}

@Preview(name = "Light", showBackground = true)
@Preview(name ="Dark Mode", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DetailScreenPreview() {
    DetailScreenView(
        DetailViewState(
            loading = false,
            photo = Photo(
                id = "",
                url = "demo",
                author = Author(
                    username = "Username",
                    name = "Name",
                    firstName = "FirstName",
                    lasName = "LastName",
                    photo = "",
                    location = ""
                ),
                camera = null,
                description = "description",
                createdAt = ""
            )
        )
    )
}


@Composable
fun DetailScreenView(state: DetailViewState) {
    Box {
        ImageUrl(
            url = state.photo?.url, modifier = Modifier
                .fillMaxSize()
                .drawWithCache {
                    val gradient = Brush.linearGradient(
                        colors = listOf(Color.Transparent, Color(0x80000000)),
                        end = Offset.Zero,
                        start = Offset(0f, Float.POSITIVE_INFINITY)
                    )
                    onDrawWithContent {
                        drawContent()
                        drawRect(gradient, blendMode = BlendMode.Multiply)
                    }
                }
        )

        if (state.loading) {
            CircularProgressIndicator(modifier = Modifier.align(alignment = Alignment.Center))
        }
        state.photo?.let {
            BottomPhotoInfo(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = if(isSystemInDarkTheme()) Color.Black else Color.White)
                    .align(alignment = Alignment.BottomEnd),
                photo = it,
                onCameraTextClicked = {

                }
            )
        }

    }
}

@Composable
fun BottomPhotoInfo(
    modifier: Modifier = Modifier, photo: Photo,
    onCameraTextClicked: (Camera) -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen.spacing_medium)
            ),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(dimensionResource(id = R.dimen.spacing_medium))
        ) {
            ImageUrl(
                url = photo.author.photo,
                placeHolder = R.drawable.userplaceholder,
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = dimensionResource(id = R.dimen.spacing_medium))
                    .weight(1f)
            ) {
                Text(
                    text = "${photo.author.firstName} ${photo.author.lasName}",
                    color = if(isSystemInDarkTheme()) Color.White else Color.Black,
                    fontSize = dimensionResource(id = R.dimen.subtitle).value.sp,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = photo.author.username,
                    color = if(isSystemInDarkTheme()) Color.White else Color.Black,
                    fontSize = dimensionResource(id = R.dimen.caption).value.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Image(
                painter = painterResource(id = R.drawable.outline_photo_camera_24),
                contentDescription = "camera",
                colorFilter = ColorFilter.tint(Color.Gray)
            )
            Text(
                text = photo.camera?.model ?: "",
                color = if(isSystemInDarkTheme()) Color.White else Color.Black,
                fontSize = dimensionResource(id = R.dimen.caption).value.sp,
                modifier = Modifier
                    .clickable(
                        enabled = photo.camera != null
                    ) {
                        photo.camera?.let {
                            onCameraTextClicked(it)
                        }
                    }
            )
    }
    }
}