package com.mvvmrecipesmap_project.category.usecase

import com.mvvmrecipesmap_project.category.model.CategoryResponse
import com.mvvmrecipesmap_project.category.repository.ICategoryRepository
import javax.inject.Inject

interface IGetCategoriesUseCase {

    suspend operator fun invoke() : CategoryResponse

}

class GetCategoriesUseCase @Inject constructor(
    val repo: ICategoryRepository
): IGetCategoriesUseCase {
    override suspend fun invoke(): CategoryResponse {
        return repo.getAllCategories()
    }

}