package com.mvvmrecipesmap_project.category.usecase

import com.mvvmrecipesmap_project.category.model.CategoryResponse
import com.mvvmrecipesmap_project.category.repository.ICategoryRepository
import javax.inject.Inject

interface IgetCategoriesUseCase {

    suspend operator fun invoke() : CategoryResponse

}

class GetCategoriesUseCase @Inject constructor(
    val repo: ICategoryRepository
): IgetCategoriesUseCase {
    override suspend fun invoke(): CategoryResponse {
        return repo.getAllCategories()
    }

}