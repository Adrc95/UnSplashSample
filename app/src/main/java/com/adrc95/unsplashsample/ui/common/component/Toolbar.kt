package com.adrc95.unsplashsample.ui.common.component

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Toolbar(text: String, modifier: Modifier = Modifier) {
    TopAppBar(
        modifier = modifier
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.h6
        )
    }
}