package com.adrc95.unsplashsample.ui

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController(),
): AppState = AppState(scaffoldState, navController)

class AppState(
    val scaffoldState: ScaffoldState,
    val navController: NavHostController) {

    val currentRoute: String
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination?.route
            ?: ""

    /*val showUpNavigation: Boolean
        @Composable get() = !NavItem.values().map { it.navCommand.route }.contains(currentRoute)*/

    fun onUpClick() {
        navController.popBackStack()
    }



}
