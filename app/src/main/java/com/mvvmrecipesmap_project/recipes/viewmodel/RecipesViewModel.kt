package com.mvvmrecipesmap_project.recipes.viewmodel

import android.os.Message
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvvmrecipesmap_project.recipes.model.Meal
import com.mvvmrecipesmap_project.recipes.usecase.IGetRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class ViewState{
    object Loading: ViewState()
    data class Success(val data: List<Meal>): ViewState()
    data class Error(val errorMessage: String): ViewState()
}


@HiltViewModel
class RecipesViewModel @Inject constructor(
    val usecase: IGetRecipesUseCase
): ViewModel() {


    private val _viewState: MutableState<ViewState> = mutableStateOf(ViewState.Loading)
    val viewState: State<ViewState> = _viewState

    fun getRecipesForCategory(catName: String){
        viewModelScope.launch {
            try {
                val listRecipes = usecase(catName)

                _viewState.value = ViewState.Success(listRecipes.meals)
            } catch (e: Exception) {
                //error
                Log.d("BK", "Exception ${e.message}")
                _viewState.value = ViewState.Error(e.message?: "Unknown error")
            }
        }
    }
}