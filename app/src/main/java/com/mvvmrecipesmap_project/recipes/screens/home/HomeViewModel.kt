package com.mvvmrecipesmap_project.recipes.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvvmrecipesmap_project.recipes.domain.usecases.FetchMealCategoriesUseCase
import com.mvvmrecipesmap_project.recipes.domain.usecases.FilterMealsByCategoryUseCase
import com.mvvmrecipesmap_project.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject



@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchMealCategoriesUseCase: FetchMealCategoriesUseCase,
    private val filterMealsByCategoryUseCase: FilterMealsByCategoryUseCase
) : ViewModel() {

    private val _mealCategoriesState = mutableStateOf(HomeScreenState())
    val mealCategoriesState: State<HomeScreenState> = _mealCategoriesState

    private val _mealsState = mutableStateOf(HomeScreenState())
    val mealsState: State<HomeScreenState> = _mealsState

    var savedPosition = 0

    init {
        fetchMealCategories()
    }

    fun onAction(events: HomeScreenEvents) {
        when(events) {
            is HomeScreenEvents.OnSearchInputKeyed -> {
                //Todo -> implement Search
                fetchMealCategories()
            }
            is HomeScreenEvents.OnCategorySelected -> {
                filterMealsByCategory(events.category)
            }
        }
    }

    private fun fetchMealCategories() {
        fetchMealCategoriesUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _mealCategoriesState.value = HomeScreenState(isLoading = true)
                }
                is Resource.Error -> {
                    _mealCategoriesState.value = HomeScreenState(error = result.message!!)
                }
                is Resource.Success -> {
                    _mealCategoriesState.value = HomeScreenState(mealCategories = result.data!!)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun filterMealsByCategory(category: String) {
        filterMealsByCategoryUseCase(category).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _mealsState.value = HomeScreenState(mealsLoading = true)
                }
                is Resource.Error -> {
                    _mealsState.value = HomeScreenState(mealsError = result.message!!)
                }
                is Resource.Success -> {
                    _mealsState.value = HomeScreenState(mealsByCategory = result.data!!)
                }
            }
        }.launchIn(viewModelScope)
    }
}