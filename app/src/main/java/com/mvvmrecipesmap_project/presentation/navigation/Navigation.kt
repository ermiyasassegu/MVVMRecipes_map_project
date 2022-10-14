package com.mvvmrecipesmap_project.presentation.navigation

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.os.Build

import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mvvmrecipesmap_project.R
import com.mvvmrecipesmap_project.map.screens.home.view.MapScreen
import com.mvvmrecipesmap_project.map.screens.home.view.PlacesScreen
import com.mvvmrecipesmap_project.map.screens.home.viewmodel.MapViewModel
import com.mvvmrecipesmap_project.map.screens.permission.screen.LocationPermissionScreen
import com.mvvmrecipesmap_project.map.screens.permission.viewmodel.PermissionViewModel
import com.mvvmrecipesmap_project.navigation.screen.BottomNavigationScreens
import com.mvvmrecipesmap_project.presentation.screens.home.HomeScreen
import com.mvvmrecipesmap_project.presentation.screens.detail.DetailScreen
import com.mvvmrecipesmap_project.presentation.screens.home.ViewMoreScreen
import com.mvvmrecipesmap_project.presentation.screens.splash.SplashScreen
import com.mvvmrecipesmap_project.scanner.ScanTab
import com.mvvmrecipesmap_project.util.Constants.ARGS_CATEGORY
import com.mvvmrecipesmap_project.util.Constants.ARGS_MEAL_ID
import timber.log.Timber


@Composable
fun MainScreen() {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val bottomNavigationItems = listOf(
        BottomNavigationScreens.Home,
        BottomNavigationScreens.Location,
        BottomNavigationScreens.Scan
    )
    // State of bottomBar, set state to false, if current page route is "car_details"
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }


    when(navBackStackEntry?.destination?.route){
        "Home" ->{
            bottomBarState.value = true
        }
        "location" ->{
            bottomBarState.value = true
        }

    }
    Scaffold(
        bottomBar = {
            RecipesBottomNavigation(navController, bottomBarState, bottomNavigationItems )
        }
    ) {
        MainScreenNavigationConfigurations(navController)

    }
    
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val systemUiController = rememberSystemUiController()
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

    NavHost(navController = navController, startDestination = Screens.SplashScreen.route) {
        composable(Screens.SplashScreen.route) {

                bottomBarState.value = false

            SplashScreen(navController, systemUiController)

        }
        composable(Screens.HomeScreen.route) {
            bottomBarState.value = true
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
    bottomBarState: MutableState<Boolean>,
    items: List<BottomNavigationScreens>) {

    AnimatedVisibility(
        visible = bottomBarState.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
        content = {
            BottomNavigation(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(40.dp),


            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                //val currentRoute = currentRoute(navController)
                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(screen.icon, null) },
                        label = { Text(stringResource(id = screen.resourceId)) },
                        selected = currentRoute == screen.route,
                        alwaysShowLabel = false,
                        onClick = {
                            navController.popBackStack(
                                navController.graph.startDestinationId, inclusive = false
                            )

                            if (currentRoute != screen.route) {
                                navController.navigate(screen.route)
                            }
                        },
                        modifier = Modifier.background(color = colorResource(id = R.color.bottom_bar_color)),
                        selectedContentColor = colorResource(id = R.color.icon_highlight),
                        unselectedContentColor = colorResource(id = R.color.icon_color)
                    )
                }
            }
        })
}


/*@Composable
fun barControl() {
    // State of bottomBar, set state to false, if current page route is "car_details"
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

    val navController = rememberNavController()
    // Subscribe to navBackStackEntry, required to get current route
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    when(navBackStackEntry?.destination?.route){
        "splashScreen" ->{
            bottomBarState.value = false
        }
    }
}*/
/*@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}*/
@Composable
private fun MainScreenNavigationConfigurations(
    navController: NavHostController
) {


    NavHost(navController, startDestination = BottomNavigationScreens.Home.route) {
        composable(BottomNavigationScreens.Home.route) {
            Navigation()
        }

        composable(BottomNavigationScreens.Location.route) {
            val viewModel: MapViewModel = hiltViewModel()
            val permissionViewModel: PermissionViewModel = hiltViewModel()
            val permissionsState = permissionViewModel.locationPermission.observeAsState()

            PlacesScreen( viewModel )


        }
        composable(BottomNavigationScreens.Scan.route) {
            ScanTab()
        }
    }
}


