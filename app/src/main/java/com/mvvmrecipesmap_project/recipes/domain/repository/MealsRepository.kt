package com.mvvmrecipesmap_project.recipes.domain.repository

import com.mvvmrecipesmap_project.recipes.data.dto.category.MealCategoriesDto
import com.mvvmrecipesmap_project.recipes.data.dto.details.MealDetailsDto
import com.mvvmrecipesmap_project.recipes.data.dto.mealByCategory.MealByCategoryDto

interface MealsRepository {
    suspend fun fetchMealCategories(): MealCategoriesDto
    suspend fun fetchMealDetails(id: String): MealDetailsDto
    suspend fun filterMealByCategory(category: String): MealByCategoryDto
}