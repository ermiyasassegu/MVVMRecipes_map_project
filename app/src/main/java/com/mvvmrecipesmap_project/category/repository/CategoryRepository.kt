package com.mvvmrecipesmap_project.category.repository

import com.mvvmrecipesmap_project.category.model.CategoryResponse
import com.mvvmrecipesmap_project.category.service.ICategoryService
import javax.inject.Inject

interface ICategoryRepository {
    suspend fun getAllCategories(): CategoryResponse
}

class CategoryRepository @Inject constructor(
    val service : ICategoryService
): ICategoryRepository {
    override suspend fun getAllCategories(): CategoryResponse {
        return service.getAllCategories()
    }
}