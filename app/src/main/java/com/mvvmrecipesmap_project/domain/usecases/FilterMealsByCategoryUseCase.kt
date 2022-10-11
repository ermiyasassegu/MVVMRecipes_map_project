package com.mvvmrecipesmap_project.domain.usecases


import com.mvvmrecipesmap_project.data.dto.mealByCategory.toMealCategory
import com.mvvmrecipesmap_project.domain.models.MealCategory
import com.mvvmrecipesmap_project.domain.repository.MealsRepository
import com.mvvmrecipesmap_project.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class FilterMealsByCategoryUseCase @Inject constructor(
    private val repository: MealsRepository
) {
    operator fun invoke(category: String): Flow<Resource<List<MealCategory>>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.filterMealByCategory(category).meals.map { it.toMealCategory() }
            emit(Resource.Success(response))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}