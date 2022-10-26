package com.adrc95.unsplashsample.ui.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.adrc95.domain.exception.ApiError
import com.adrc95.domain.model.Photo
import com.adrc95.unsplashsample.R
import com.adrc95.unsplashsample.ui.common.component.ImageUrl
import com.adrc95.unsplashsample.ui.common.extension.message
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    onPhotoClicked: (Photo) -> Unit,
    viewModel: MainViewModel = hiltViewModel(),
    snackbarHostState: SnackbarHostState
) {
    val state by viewModel.state.collectAsState()
    UnSplashItemsList(
        snackbarHostState = snackbarHostState,
        loading = state.loading,
        photos = state.photos,
        error = state.error,
        onPhotoClick = onPhotoClicked,
        modifier = Modifier.fillMaxSize()
    )

}

/*
 private fun initializeList() {
        binding.rvPhotos.addOnScrollListener(object : EndlessRecyclerOnScrollListener(layoutManager) {
            override fun onLoadMore(page: Int) {
                viewModel.onLoadMore(page)
            }
        })
    }
 */
//    private fun renderPhotos(photos: List<Photo>) {
//        adapter.photos = adapter.photos + photos
//    }

@Composable
fun UnSplashItemsList(
    modifier: Modifier = Modifier,
    photos: List<Photo> = arrayListOf(),
    error: ApiError?,
    loading: Boolean,
    snackbarHostState: SnackbarHostState,
    onPhotoClick: (Photo) -> Unit,
) {
    val context = LocalContext.current
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        val state = rememberLazyGridState()
        if (loading) {
            CircularProgressIndicator()
        }
        if (photos.isNotEmpty()) {
            LazyVerticalGrid(
                state = state,
                contentPadding = PaddingValues(dimensionResource(id = R.dimen.spacing_small)),
                verticalArrangement =
                Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing_small)),
                horizontalArrangement =
                Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing_small)),
                columns = GridCells.Fixed(2)
            ) {
                items(photos) {
                    UnSplashListItem(
                        photo = it,
                        modifier = Modifier.clickable { onPhotoClick(it) }
                    )
                }
            }
        }
    }
    error?.let {
        coroutineScope.launch {
            snackbarHostState.showSnackbar(
                message = it.message(context),
                duration = SnackbarDuration.Short
            )
        }
    }
}

@Composable
fun UnSplashListItem(photo: Photo, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
    ) {
        Row {
            ImageUrl(
                url = photo.url,
                contentDescription = "picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1 / 1.5f)
            )
        }
    }
}
