package com.mvvmrecipesmap_project.recipes.data.dto.mealByCategory

import com.mvvmrecipesmap_project.recipes.domain.models.MealCategory


data class MealDto(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String
)

fun MealDto.toMealCategory(): MealCategory {
    return MealCategory(idMeal, strMeal, strMealThumb)
}
