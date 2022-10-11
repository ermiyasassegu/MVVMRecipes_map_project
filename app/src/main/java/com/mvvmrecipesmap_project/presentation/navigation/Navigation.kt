package com.mvvmrecipesmap_project.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mvvmrecipesmap_project.R
import com.mvvmrecipesmap_project.map.ui.LocationTab
import com.mvvmrecipesmap_project.navigation.screen.BottomNavigationScreens
import com.mvvmrecipesmap_project.presentation.screens.home.HomeScreen
import com.mvvmrecipesmap_project.presentation.screens.detail.DetailScreen
import com.mvvmrecipesmap_project.presentation.screens.home.ViewMoreScreen
import com.mvvmrecipesmap_project.presentation.screens.splash.SplashScreen
import com.mvvmrecipesmap_project.scanner.ScanTab
import com.mvvmrecipesmap_project.util.Constants.ARGS_CATEGORY
import com.mvvmrecipesmap_project.util.Constants.ARGS_MEAL_ID

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val bottomNavigationItems = listOf(
        BottomNavigationScreens.Home,
        BottomNavigationScreens.Location,
        BottomNavigationScreens.Scan
    )
    Scaffold(

        bottomBar = {
            RecipesBottomNavigation(navController, bottomNavigationItems)
        }
    ) {
        MainScreenNavigationConfigurations(navController)

    }
}
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
@Composable
fun RecipesBottomNavigation(
    navController: NavHostController,
    items: List<BottomNavigationScreens>) {

    BottomNavigation (
        modifier = Modifier.fillMaxWidth(1f).padding(30.dp),

        elevation = 20.dp
    ){
        val currentRoute = currentRoute(navController)
        items.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(screen.icon, null) },
                label = { Text(stringResource(id = screen.resourceId)) },
                selected = currentRoute == screen.route,
                alwaysShowLabel = false,
                onClick = {
                    if(currentRoute != screen.route) {
                        navController.navigate(screen.route)
                    }
                },
                modifier = Modifier.background(color = colorResource(id = R.color.bottom_bar_color)),
                selectedContentColor = colorResource(id = R.color.icon_highlight),
                unselectedContentColor = colorResource(id = R.color.icon_color)
            )
        }
    }
}



@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}
@Composable
private fun MainScreenNavigationConfigurations(
    navController: NavHostController
) {
    NavHost(navController, startDestination = BottomNavigationScreens.Home.route) {
        composable(BottomNavigationScreens.Home.route) {
            Navigation()
        }

        composable(BottomNavigationScreens.Location.route) {
            LocationTab()
        }
        composable(BottomNavigationScreens.Scan.route) {
            ScanTab()
        }
    }
}

