package com.mvvmrecipesmap_project.recipes.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.mvvmrecipesmap_project.recipes.presentation.screens.Screens
import com.mvvmrecipesmap_project.ui.theme.*
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch


@Composable
fun ViewMoreScreen(
    navController: NavHostController,
    systemUiController: SystemUiController,
    category: String
) {
    SideEffect {
        systemUiController.setNavigationBarColor(color = OffWhite)
        systemUiController.setStatusBarColor(color = OffWhite)
    }

    val viewModel = hiltViewModel<HomeViewModel>()
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(OffWhite)
            .padding(vertical = 50.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = category,
                style = MaterialTheme.typography.body1,
                color = Black,
                fontSize = 25.sp,
                modifier = Modifier.padding(horizontal = 15.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            MealsGridSection(
                showSubList = false,
                onMealItemClick = { mealId ->
                    navController.navigate(Screens.DetailScreen.withArgs(mealId))
                },
                onFilterMealByCategory = {
                    coroutineScope.launch {
                        viewModel.onAction(HomeScreenEvents.OnCategorySelected(category))
                        coroutineScope.cancel()
                    }
                    viewModel.mealsState.value
                }
            )
        }
    }
}
