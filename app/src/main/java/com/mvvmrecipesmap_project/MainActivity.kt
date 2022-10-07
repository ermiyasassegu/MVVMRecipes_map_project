package com.mvvmrecipesmap_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.*
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.android.gms.location.FusedLocationProviderClient
import com.mvvmrecipesmap_project.category.CategoryScreen
import com.mvvmrecipesmap_project.recipes.RecipesScreen
import com.mvvmrecipesmap_project.navigation.screen.BottomNavigationScreens
import com.mvvmrecipesmap_project.navigation.screen.FavouritesTab
import com.mvvmrecipesmap_project.map.ui.LocationTab
import com.mvvmrecipesmap_project.scanner.ScanTab
import com.mvvmrecipesmap_project.ui.theme.MVVMRecipesMap_projectTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVMRecipesMap_projectTheme {
                //RecipesApp()
                MainScreen()
            }
        }
    }
}
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val bottomNavigationItems = listOf(
        BottomNavigationScreens.Category,
        BottomNavigationScreens.Favourites,
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
fun RecipesBottomNavigation(
    navController: NavHostController,
    items: List<BottomNavigationScreens>) {

    BottomNavigation {
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
fun MainScreenNavigationConfigurations(
    navController: NavHostController
) {
    //val navController = rememberNavController()

    NavHost(navController, startDestination = BottomNavigationScreens.Category.route) {
        composable(BottomNavigationScreens.Category.route) {
            CategoryScreen { category ->
                navController.navigate("recipes/${category}")
            }
        }

        composable("recipes/{category}", arguments = listOf(navArgument("category") {
            type = NavType.StringType
        })) {

            //TODO
            val categoryStr = remember {
                it.arguments?.getString("category")
            }
            RecipesScreen(category = categoryStr)
        }




        composable(BottomNavigationScreens.Favourites.route) {
            FavouritesTab()
        }
        composable(BottomNavigationScreens.Location.route) {
            LocationTab( )
        }
        composable(BottomNavigationScreens.Scan.route) {
            ScanTab()
        }
    }
}
/*
@Composable
fun RecipesApp() {
    //TODO

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "category"){

        composable("category"){

            CategoryScreen { category ->
                navController.navigate("recipes/${category}")
            }
        }

        composable("recipes/{category}", arguments = listOf(navArgument("category"){
            type = NavType.StringType
        })){

            //TODO
            val categoryStr = remember {
                it.arguments?.getString("category")
            }
            RecipesScreen(category = categoryStr)
        }

    }

}
*/
