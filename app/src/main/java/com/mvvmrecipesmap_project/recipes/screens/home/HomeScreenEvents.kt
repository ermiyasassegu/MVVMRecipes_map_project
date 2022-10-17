package com.mvvmrecipesmap_project.recipes.screens.home

sealed class HomeScreenEvents {
    data class OnCategorySelected(val category: String): HomeScreenEvents()
    data class OnSearchInputKeyed(val searchQuery: String): HomeScreenEvents()
}
