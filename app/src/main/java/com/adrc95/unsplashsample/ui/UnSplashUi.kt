package com.adrc95.unsplashsample.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.adrc95.unsplashsample.R
import com.adrc95.unsplashsample.ui.common.component.Toolbar
import com.adrc95.unsplashsample.ui.navigation.AppNavigation
import com.adrc95.unsplashsample.ui.theme.UnSplashSampleComposeTheme

@Composable
fun App(
    appState: AppState = rememberAppState()
) {
    Screen {
        Scaffold(
            scaffoldState = appState.scaffoldState,
            topBar = { Toolbar(text =  stringResource(id = R.string.app_name))}
        ) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(padding)
            ) {
                AppNavigation(appState.navController)
            }
        }
    }
}

@Composable
fun Screen(content: @Composable () -> Unit) {
    UnSplashSampleComposeTheme() {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            content()
        }
    }
}
