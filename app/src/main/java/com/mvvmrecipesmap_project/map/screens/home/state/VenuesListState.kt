package com.mvvmrecipesmap_project.map.screens.home.state

import com.mvvmrecipesmap_project.map.model.response.Result

data class VenuesListState(
    val venueList: List<Result>? = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)