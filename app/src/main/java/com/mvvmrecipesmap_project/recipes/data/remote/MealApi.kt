package com.mvvmrecipesmap_project.recipes.data.remote


import com.mvvmrecipesmap_project.recipes.data.dto.category.MealCategoriesDto
import com.mvvmrecipesmap_project.recipes.data.dto.details.MealDetailsDto
import com.mvvmrecipesmap_project.recipes.data.dto.mealByCategory.MealByCategoryDto
import retrofit2.http.GET
import retrofit2.http.Query


interface MealApi {

    @GET("lookup.php")
    suspend fun fetchMealDetail(@Query("i") id: String): MealDetailsDto

    @GET("categories.php")
    suspend fun fetchMealCategories(): MealCategoriesDto

    @GET("filter.php")
    suspend fun filterMealsByCategory(@Query("c") category: String): MealByCategoryDto
}