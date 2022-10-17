package com.mvvmrecipesmap_project.map.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.rememberAsyncImagePainter


@Composable
fun CustomImageFromURL(modifier: Modifier, image: String) {
    Image(
        modifier = modifier,
        painter = rememberAsyncImagePainter(model = image),
        contentScale = ContentScale.Crop,
        contentDescription = null
    )
}

@Composable
fun CustomImageFromResource(modifier: Modifier, image: Int) {
    Image(
        modifier = modifier,
        painter = painterResource(id = image),
        contentDescription = null
    )
}