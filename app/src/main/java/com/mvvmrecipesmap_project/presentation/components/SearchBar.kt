package com.mvvmrecipesmap_project.presentation.components


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mvvmrecipesmap_project.ui.theme.InactiveCategory
import com.mvvmrecipesmap_project.ui.theme.OnBoardingBG
import com.mvvmrecipesmap_project.ui.theme.*
import com.mvvmrecipesmap_project.R

@Composable
fun SearchBar(
    text: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = text,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = InactiveCategory,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Red
        ),
        textStyle = MaterialTheme.typography.body1,
        shape = RoundedCornerShape(32.dp),
        singleLine = true,
        placeholder = {
            Text(
                text = "Search",
                color = OnBoardingBG
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search",
                tint = Red
            )
        },
    )
}

//@Preview(showBackground = true)
//@Composable
//fun SearchBarPreview() {
//    ComposeRecipeAppTheme {
//        SearchBar("hello") {}
//    }
//}