package com.adrc95.unsplashsample.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.adrc95.unsplashsample.ui.detail.DetailScreen
import com.adrc95.unsplashsample.ui.main.MainScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Feature.MAIN.route
    ) {
        mainNav(navController)
    }
}

private fun NavGraphBuilder.mainNav(navController: NavController) {
    navigation(
        startDestination = NavCommand.ContentType(Feature.MAIN).route,
        route = Feature.MAIN.route
    ) {
        composable(navCommand = NavCommand.ContentType(Feature.MAIN)) {
            MainScreen(
                onPhotoClicked = { photo ->
                    navController.navigateToDetailFromMain(photo.id)
                }
            )
        }

        composable(navCommand = NavCommand.ContentDetail(Feature.MAIN, listOf(NavArg.PhotoID))) {
            DetailScreen()
        }
    }
}

private fun NavController.navigateToDetailFromMain(photoID: String) {
    navigate(
        route = NavCommand.ContentDetail(Feature.MAIN, listOf(NavArg.PhotoID))
            .createRoute(photoID)
    )
}

private fun NavGraphBuilder.composable(
    navCommand: NavCommand,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = navCommand.route,
        arguments = navCommand.args
    ) {
        content(it)
    }
}