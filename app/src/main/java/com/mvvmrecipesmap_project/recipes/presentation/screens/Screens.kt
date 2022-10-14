package com.mvvmrecipesmap_project.recipes.presentation.screens

import com.mvvmrecipesmap_project.util.Constants

sealed class Screens(val route: String) {
    object SplashScreen: Screens(Constants.SPLASH_ROUTE)
    object HomeScreen: Screens(Constants.HOME_ROUTE)
    object DetailScreen: Screens(Constants.DETAIL_ROUTE)
    object ViewMoreScreen: Screens(Constants.VIEW_MORE_ROUTE)

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}