package com.mvvmrecipesmap_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mvvmrecipesmap_project.category.CategoryScreen
import com.mvvmrecipesmap_project.recipes.RecipesScreen
import com.mvvmrecipesmap_project.ui.theme.MVVMRecipesMap_projectTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVMRecipesMap_projectTheme {
                RecipesApp()
            }
        }
    }
}

@Composable
fun RecipesApp() {
    //TODO
    /**
     * split this NavHost
     * composable -cs
     * composables- Recipes screen
     */

 /*   val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "category"){

        composable("category"){
            navController.navigate("recipes")
            CategoryScreen()
        }
        composable("recipes"){
            RecipesScreen()
        }
    }*/

    CategoryScreen()

    //RecipesScreen
}
