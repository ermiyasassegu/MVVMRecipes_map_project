package com.mvvmrecipesmap_project.recipes.repository

import com.mvvmrecipesmap_project.recipes.model.RecipesResponse
import com.mvvmrecipesmap_project.recipes.services.IRecipesService
import javax.inject.Inject

interface  IRecipesRepository{

        suspend fun getRecipesForCategory(categoryName: String): RecipesResponse
}

class RecipesRepository @Inject constructor(
val service : IRecipesService
) : IRecipesRepository{

    override suspend fun getRecipesForCategory(categoryName: String): RecipesResponse {
         return service.getRecipesForCategory(categoryName)
    }
}