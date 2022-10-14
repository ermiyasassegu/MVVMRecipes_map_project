package com.mvvmrecipesmap_project.recipes.presentation.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.mvvmrecipesmap_project.R
import com.mvvmrecipesmap_project.recipes.presentation.screens.Screens
import com.mvvmrecipesmap_project.ui.theme.*

import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavHostController,
    systemUiController: SystemUiController
) {
    SideEffect {
        systemUiController.setNavigationBarColor(color = OffWhite)
        systemUiController.setStatusBarColor(color = OffWhite)
    }

    LaunchedEffect(key1 = true) {
        delay(2000)
        navController.popBackStack()
        navController.navigate(Screens.HomeScreen.route)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 50.dp)
    ) {
        Image(
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.Center),
            painter = painterResource(id = R.drawable.pizza),
            contentDescription = "Logo",
        )
        CircularProgressIndicator(
            color = Red,
            modifier = Modifier
                .size(15.dp)
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp),
            strokeWidth = 2.dp
        )
    }
}