package com.mvvmrecipesmap_project.category

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.mvvmrecipesmap_project.category.model.Category
import com.mvvmrecipesmap_project.category.viewmodel.CategoryViewModel

@Composable
fun CategoryScreen(
    viewModel: CategoryViewModel = hiltViewModel(),
    onItemClick: (String) -> Unit
) {

    val listOfCategories by remember {viewModel.listOfCategories }

    LazyColumn {
        items(listOfCategories) { item ->
            SingleItem( item.strCategory, item.strCategoryThumb){
                onItemClick(it)
            }

        }
    }
}
@OptIn(ExperimentalCoilApi::class)
@Composable
fun SingleItem(
    Title: String,
    thumbnail: String,
    onClick: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick(Title) },           // handling onCategoryClick
        elevation = 8.dp,
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = Modifier.size(80.dp), painter = rememberImagePainter(
                    thumbnail
                ), contentDescription = null
            )
            Text(text = Title, fontSize = 24.sp)
        }
    }

}


