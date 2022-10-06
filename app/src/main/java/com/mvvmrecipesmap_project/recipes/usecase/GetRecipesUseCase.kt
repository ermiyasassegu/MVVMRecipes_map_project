package com.mvvmrecipesmap_project.recipes.usecase


import com.mvvmrecipesmap_project.recipes.model.RecipesResponse
import com.mvvmrecipesmap_project.recipes.repository.IRecipesRepository
import javax.inject.Inject

interface IGetRecipesUseCase {

    suspend operator fun invoke(categoryName: String) : RecipesResponse
}

class GetRecipesUseCase @Inject constructor(
    val repo: IRecipesRepository
): IGetRecipesUseCase{
    override suspend fun invoke(categoryName: String): RecipesResponse {
        return repo.getRecipesForCategory(categoryName)
    }
}