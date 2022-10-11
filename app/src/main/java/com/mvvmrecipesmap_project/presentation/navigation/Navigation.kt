package com.mvvmrecipesmap_project.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mvvmrecipesmap_project.presentation.screens.home.HomeScreen
import com.mvvmrecipesmap_project.presentation.screens.detail.DetailScreen
import com.mvvmrecipesmap_project.presentation.screens.home.ViewMoreScreen
import com.mvvmrecipesmap_project.presentation.screens.splash.SplashScreen
import com.mvvmrecipesmap_project.util.Constants.ARGS_CATEGORY
import com.mvvmrecipesmap_project.util.Constants.ARGS_MEAL_ID


@Composable
fun Navigation() {
    val navController = rememberNavController()
    val systemUiController = rememberSystemUiController()

    NavHost(navController = navController, startDestination = Screens.SplashScreen.route) {
        composable(Screens.SplashScreen.route) {
            SplashScreen(navController, systemUiController)
        }
        composable(Screens.HomeScreen.route) {
            HomeScreen(navController, systemUiController)
        }
        composable(
            route = Screens.ViewMoreScreen.route + "/{$ARGS_CATEGORY}",
            arguments = listOf(
                navArgument(ARGS_CATEGORY) {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            val category = entry.arguments?.getString(ARGS_CATEGORY).toString()
            ViewMoreScreen(navController, systemUiController, category)
        }
        composable(
            route = Screens.DetailScreen.route + "/{$ARGS_MEAL_ID}",
            arguments = listOf(
                navArgument(ARGS_MEAL_ID) {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            val mealId = entry.arguments?.getString(ARGS_MEAL_ID).toString()
            DetailScreen(navController, systemUiController)
        }
    }
}