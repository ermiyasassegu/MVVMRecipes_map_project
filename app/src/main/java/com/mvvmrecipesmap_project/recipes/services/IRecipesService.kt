package com.mvvmrecipesmap_project.recipes.services

import com.mvvmrecipesmap_project.recipes.model.RecipesResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface IRecipesService {

    @GET("filter.php")
    suspend fun getRecipesForCategory(
        @Query("c") categoryName: String
    ): RecipesResponse
}