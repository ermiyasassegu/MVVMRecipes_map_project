package com.mvvmrecipesmap_project.data.repository

import com.mvvmrecipesmap_project.data.dto.category.MealCategoriesDto
import com.mvvmrecipesmap_project.data.dto.details.MealDetailsDto
import com.mvvmrecipesmap_project.data.dto.mealByCategory.MealByCategoryDto
import com.mvvmrecipesmap_project.data.remote.MealApi
import com.mvvmrecipesmap_project.domain.repository.MealsRepository
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