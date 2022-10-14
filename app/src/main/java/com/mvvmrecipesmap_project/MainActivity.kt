package com.mvvmrecipesmap_project

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource
import com.mvvmrecipesmap_project.map.screens.home.viewmodel.MapViewModel
import com.mvvmrecipesmap_project.map.screens.permission.viewmodel.PermissionViewModel
import com.mvvmrecipesmap_project.presentation.navigation.MainScreen
import com.mvvmrecipesmap_project.presentation.navigation.Navigation
import com.mvvmrecipesmap_project.ui.theme.MVVMRecipesMap_projectTheme
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class MainActivity : ComponentActivity() {
   /* private val permissionViewModel: PermissionViewModel by viewModels()
    private val mapViewModel: MapViewModel by viewModels()

    private val fusedLocationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(applicationContext)
    }

    private var cancellationTokenSource = CancellationTokenSource()*/

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVMRecipesMap_projectTheme {
                //RecipesApp()
                WindowCompat.setDecorFitsSystemWindows(window, false)
                MainScreen()
                LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

            }
        }
    }
}

@Composable
fun LockScreenOrientation(orientation: Int) {
    val context = LocalContext.current
    DisposableEffect(Unit) {
        val activity = context.findActivity() ?: return@DisposableEffect onDispose {}
        val originalOrientation = activity.requestedOrientation
        activity.requestedOrientation = orientation
        onDispose {
            // restore original orientation when view disappears
            activity.requestedOrientation = originalOrientation
        }
    }
}

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

/*@Preview(showBackground = true)
@Composable
fun DefaultPreview(navController: NavController) {
    MVVMRecipesMap_projectTheme {
        Navigation(navController )
    }
}*/

/*@Composable
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
            MapScreen( )
        }
        composable(BottomNavigationScreens.Scan.route) {
            ScanTab()
        }
    }
}*/
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
