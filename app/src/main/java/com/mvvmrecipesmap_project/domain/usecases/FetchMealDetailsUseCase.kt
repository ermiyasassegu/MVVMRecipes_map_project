package com.mvvmrecipesmap_project.domain.usecases

import com.mvvmrecipesmap_project.data.dto.details.toMeal
import com.mvvmrecipesmap_project.domain.models.MealDetail
import com.mvvmrecipesmap_project.domain.repository.MealsRepository
import com.mvvmrecipesmap_project.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class FetchMealDetailsUseCase @Inject constructor(
    private val repository: MealsRepository
) {
    operator fun invoke(id: String): Flow<Resource<MealDetail>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.fetchMealDetails(id).meals[0].toMeal()
            emit(Resource.Success(response))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}