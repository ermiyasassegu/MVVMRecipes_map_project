package com.mvvmrecipesmap_project.navigation.screen

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.mvvmrecipesmap_project.R


sealed class BottomNavigationScreens(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
    object Category : BottomNavigationScreens("Category", R.string.category, Icons.Filled.Home)
    object Favourites : BottomNavigationScreens("Bookmark", R.string.favourites, Icons.Filled.Favorite)
    object Location : BottomNavigationScreens("location", R.string.location, Icons.Filled.LocationOn)
    object Scan : BottomNavigationScreens("scanner", R.string.scan, Icons.Filled.Face)
}