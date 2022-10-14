package com.mvvmrecipesmap_project.recipes.presentation.screens.home

sealed class HomeScreenEvents {
    data class OnCategorySelected(val category: String): HomeScreenEvents()
    data class OnSearchInputKeyed(val searchQuery: String): HomeScreenEvents()
}
