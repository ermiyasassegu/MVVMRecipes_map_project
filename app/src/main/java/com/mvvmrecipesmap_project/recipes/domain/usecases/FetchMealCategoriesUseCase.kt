package com.mvvmrecipesmap_project.recipes.domain.usecases

import com.mvvmrecipesmap_project.recipes.data.dto.category.toCategory
import com.mvvmrecipesmap_project.recipes.domain.models.Category
import com.mvvmrecipesmap_project.recipes.domain.repository.MealsRepository
import com.mvvmrecipesmap_project.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class FetchMealCategoriesUseCase @Inject constructor(
    private val repository: MealsRepository
) {
    operator fun invoke(): Flow<Resource<List<Category>>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.fetchMealCategories().categories.map {
                it.toCategory() }
            emit(Resource.Success(response))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}