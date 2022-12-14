package com.mvvmrecipesmap_project.recipes.data.repository

import com.mvvmrecipesmap_project.recipes.data.dto.category.MealCategoriesDto
import com.mvvmrecipesmap_project.recipes.data.dto.details.MealDetailsDto
import com.mvvmrecipesmap_project.recipes.data.dto.mealByCategory.MealByCategoryDto
import com.mvvmrecipesmap_project.recipes.data.remote.MealApi
import com.mvvmrecipesmap_project.recipes.domain.repository.MealsRepository
import javax.inject.Inject


class MealRepositoryImpl @Inject constructor(
    private val mealApi: MealApi
): MealsRepository {
    override suspend fun fetchMealCategories(): MealCategoriesDto {
        return mealApi.fetchMealCategories()
    }

    override suspend fun fetchMealDetails(id: String): MealDetailsDto {
        return mealApi.fetchMealDetail(id)
    }

    override suspend fun filterMealByCategory(category: String): MealByCategoryDto {
        return mealApi.filterMealsByCategory(category)
    }
}