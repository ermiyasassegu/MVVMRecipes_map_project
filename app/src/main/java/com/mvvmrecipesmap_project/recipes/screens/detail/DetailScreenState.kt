package com.mvvmrecipesmap_project.recipes.screens.detail

import com.mvvmrecipesmap_project.recipes.domain.models.MealDetail


data class DetailScreenState(
    val isLoading: Boolean = false,
    val error: String = "",
    val mealDetail: MealDetail? = null,
)
