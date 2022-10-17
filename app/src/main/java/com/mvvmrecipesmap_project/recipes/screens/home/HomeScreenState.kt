package com.mvvmrecipesmap_project.recipes.screens.home

import com.mvvmrecipesmap_project.recipes.domain.models.Category
import com.mvvmrecipesmap_project.recipes.domain.models.MealCategory

data class HomeScreenState(
    val isLoading: Boolean = false,
    val mealsLoading: Boolean = false,
    val error: String = "",
    val mealsError: String = "",
    val mealCategories: List<Category> = emptyList(),
    val mealsByCategory: List<MealCategory> = emptyList()
)
