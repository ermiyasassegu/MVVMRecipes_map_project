package com.mvvmrecipesmap_project.presentation.screens.detail

import com.mvvmrecipesmap_project.domain.models.MealDetail


data class DetailScreenState(
    val isLoading: Boolean = false,
    val error: String = "",
    val mealDetail: MealDetail? = null,
)
