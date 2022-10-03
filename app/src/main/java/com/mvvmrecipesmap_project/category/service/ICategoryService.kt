package com.mvvmrecipesmap_project.category.service

import com.mvvmrecipesmap_project.category.model.CategoryResponse
import retrofit2.http.GET

interface ICategoryService {
    @GET("categories.php")
    suspend fun getAllCategories():CategoryResponse
}


//API: www.themealdb.com/api/json/v1/1/categories.php