package com.mvvmrecipesmap_project.domain.repository

import com.mvvmrecipesmap_project.data.dto.category.MealCategoriesDto
import com.mvvmrecipesmap_project.data.dto.details.MealDetailsDto
import com.mvvmrecipesmap_project.data.dto.mealByCategory.MealByCategoryDto

interface MealsRepository {
    suspend fun fetchMealCategories(): MealCategoriesDto
    suspend fun fetchMealDetails(id: String): MealDetailsDto
    suspend fun filterMealByCategory(category: String): MealByCategoryDto
}