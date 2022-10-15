package com.mvvmrecipesmap_project.recipes.presentation.screens.detail

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.SystemUiController
import com.mvvmrecipesmap_project.R
import com.mvvmrecipesmap_project.recipes.domain.models.MealDetail
import com.mvvmrecipesmap_project.recipes.presentation.components.IngredientsItem
import com.mvvmrecipesmap_project.ui.theme.*
import com.mvvmrecipesmap_project.util.watchYoutubeVideo

@Composable
fun DetailScreen(
    navController: NavHostController,
    systemUiController: SystemUiController,
) {
    ProvideWindowInsets {
        SideEffect {
            systemUiController.setNavigationBarColor(color = OffWhite)
            systemUiController.setStatusBarColor(color = Color.Transparent)
        }

        val viewModel = hiltViewModel<DetailViewModel>()

        val detailScreenState = viewModel.detailScreenState.value

        val context = LocalContext.current

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(OffWhite)
                .padding(bottom = 50.dp)
        ) {
            detailScreenState.mealDetail?.let { mealDetail ->
                val ingredients = viewModel.extractIngredientsFromDetails(mealDetail)
                Spacer(modifier = Modifier.width(10.dp))
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    content = {
                        item {
                            Spacer(modifier = Modifier.width(40.dp))
                            Column(
                                Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.Center)
                                    .padding(8.dp),

                            ) {
                                IconButton(
                                    onClick = {
                                        navController.popBackStack()
                                    },
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_left),
                                        contentDescription = "back",
                                        modifier = Modifier.size(32.dp)
                                    )

                                }
                                AsyncImage(
                                    model = mealDetail.strMealThumb,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(250.dp),
                                    contentScale = ContentScale.Crop
                                )
                            }

                        }
                        item {
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = mealDetail.strMeal,
                                style = MaterialTheme.typography.h1,
                                fontSize = 20.sp,
                                color = Black,
                                modifier = Modifier.padding(horizontal = 15.dp)
                            )

                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = "Category: ${mealDetail.strCategory}",
                                style = MaterialTheme.typography.body1,
                                modifier = Modifier.padding(horizontal = 15.dp)
                            )
                        }

                        item {
                            Spacer(modifier = Modifier.height(20.dp))

                            IngredientSection(ingredients)
                        }

                           item {
                               Spacer(modifier = Modifier.height(20.dp))
                               InstructionSection(mealDetail)
                         }

                        item {
                            Spacer(modifier = Modifier.height(5.dp))

                            Button(
                                elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Red,
                                    contentColor = White
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        top = 20.dp,
                                        bottom = 10.dp,
                                        start = 15.dp,
                                        end = 15.dp
                                    ),
                                shape = RoundedCornerShape(5.dp),
                                onClick = {
                                    watchYoutubeVideo(context, mealDetail.strYoutube)
                                }) {
                                Text(
                                    text = "Watch Video",
                                    style = MaterialTheme.typography.body1,
                                    color = White
                                )
                            }
                            Spacer(modifier = Modifier.height(30.dp))
                        }
                    })
            }

            if (detailScreenState.error.isNotBlank()) {
                Text(
                    text = detailScreenState.error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }

            if (detailScreenState.isLoading) {
                CircularProgressIndicator(
                    color = Red,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }

}
private const val MINIMIZED_MAX_LINES = 4
@Composable
fun InstructionSection(mealDetail: MealDetail) {
    var isExpanded by remember { mutableStateOf(false) }
    val textLayoutResultState = remember { mutableStateOf<TextLayoutResult?>(null) }
    var isClickable by remember { mutableStateOf(false) }
    var finalText by remember { mutableStateOf(mealDetail.strInstructions) }

    val textLayoutResult = textLayoutResultState.value

    LaunchedEffect(textLayoutResult) {
        if (textLayoutResult == null) return@LaunchedEffect

        when {
            isExpanded -> {
                finalText = "${mealDetail.strInstructions} Show Less"
            }
            !isExpanded && textLayoutResult.hasVisualOverflow -> {
                val lastCharIndex = textLayoutResult.getLineEnd(MINIMIZED_MAX_LINES - 1)
                val showMoreString = "... Show More"
                val adjustedText = mealDetail.strInstructions
                    .substring(startIndex = 0, endIndex = lastCharIndex)
                    .dropLast(showMoreString.length)
                    .dropLastWhile { it == ' ' || it == '.' }

                finalText = "$adjustedText$showMoreString"

                isClickable = true
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
    ) {
        Text(
            text = "Instructions",
            style = MaterialTheme.typography.h2,
            fontSize = 20.sp,
            color = Black
        )

        Text(
            text = mealDetail.strInstructions,
            style = MaterialTheme.typography.body1,
            maxLines = if (isExpanded) Int.MAX_VALUE else MINIMIZED_MAX_LINES,
            onTextLayout = { textLayoutResultState.value = it },
            modifier = Modifier
                .clickable(enabled = isClickable) { isExpanded = !isExpanded }
                .animateContentSize(),
        )
    }
}



@Composable
fun IngredientSection(ingredients: MutableList<String>) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
            .height(300.dp)
    ) {
        Text(
            text = "Ingredients",
            style = MaterialTheme.typography.h2,
            fontSize = 20.sp,
            color = Black
        )
        LazyVerticalGrid(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            columns = GridCells.Fixed(2),
            content = {
                items(ingredients) { ingredient ->
                    IngredientsItem(ingredient = ingredient)
                }
            })
    }
}

