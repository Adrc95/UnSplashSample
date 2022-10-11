package com.adrc95.unsplashsample.ui.common.component

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.adrc95.unsplashsample.R

@Composable
fun ImageUrl(
    modifier : Modifier = Modifier,
    url: String?,
    @DrawableRes placeHolder: Int = R.drawable.placeholder,
    contentDescription : String? = null,
    contentScale: ContentScale = ContentScale.Crop
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .placeholder(placeHolder)
            .build(),
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier
    )
}