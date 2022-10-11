package com.mvvmrecipesmap_project.data.dto.category

import com.mvvmrecipesmap_project.domain.models.Category

data class CategoryDto(
    val idCategory: String,
    val strCategory: String,
    val strCategoryDescription: String,
    val strCategoryThumb: String
)

fun CategoryDto.toCategory(): Category {
    return Category(idCategory, strCategory, strCategoryThumb)
}
