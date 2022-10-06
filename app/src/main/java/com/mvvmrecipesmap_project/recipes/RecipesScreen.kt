package com.mvvmrecipesmap_project.recipes

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.mvvmrecipesmap_project.category.SingleItem
import com.mvvmrecipesmap_project.recipes.model.Meal
import com.mvvmrecipesmap_project.recipes.viewmodel.RecipesViewModel
import com.mvvmrecipesmap_project.recipes.viewmodel.ViewState

@Composable
fun RecipesScreen(
    viewmodel : RecipesViewModel= hiltViewModel(),
    category: String?
) {

    DisposableEffect(key1 = Unit) {
        if (!category.isNullOrBlank()) {

            viewmodel.getRecipesForCategory(category)

        }
        onDispose {}
    }

    val viewState by remember{ viewmodel.viewState }

    when(val state = viewState){
        is ViewState.Success -> {
            RecipesList (state.data)
        }
        is ViewState.Error -> {
            Text (text=" Error ${state.errorMessage}")
        }
        else -> {
            Text(text = "Loading")
        }
    }
}

@Composable
fun RecipesList(meals: List<Meal>) {

    LazyColumn {
        items(meals) { item ->
            SingleItem( Title= item.strMeal, thumbnail = item.strMealThumb, onClick ={} )
        }
    }
}

