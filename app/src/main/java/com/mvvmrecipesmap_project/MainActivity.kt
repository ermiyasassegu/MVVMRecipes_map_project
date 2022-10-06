package com.mvvmrecipesmap_project
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
