package com.adrc95.unsplashsample.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class NavCommand(
    internal val feature: Feature,
    internal val subRoute: String = "home",
    private val navArgs: List<NavArg> = emptyList()
) {
    class ContentType(feature: Feature) : NavCommand(feature)

    class ContentDetail(feature: Feature, navArgs: List<NavArg>) : NavCommand(
        feature,
        "detail",
        navArgs
    ) {
        fun createRoute(photoID: String) = "${feature.route}/$subRoute/$photoID"
    }

    val route = run {
        val argValues = navArgs.map { "{${it.key}}" }
        listOf(feature.route)
            .plus(subRoute)
            .plus(argValues)
            .joinToString("/")
    }

    val args = navArgs.map {
        navArgument(it.key) { type = it.navType }
    }

}

enum class NavArg(val key: String, val navType: NavType<*>) {
    PhotoID("photoID", NavType.StringType)
}