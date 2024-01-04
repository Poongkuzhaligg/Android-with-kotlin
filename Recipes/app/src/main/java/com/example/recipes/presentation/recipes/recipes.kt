package com.example.recipes.presentation.recipes

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.wear.compose.foundation.lazy.items
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.ListHeader
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import com.example.recipes.presentation.model.RecipeDetail
import kotlinx.coroutines.flow.StateFlow

@Composable
fun RecipeScreen(navController: NavController, recipeViewModel: RecipeViewModel) {
    val recipesFlow: StateFlow<List<RecipeDetail>> = recipeViewModel.fetchRecipes()
    val recipes: List<RecipeDetail> by recipesFlow.collectAsState(initial = emptyList())
    val listState = rememberScalingLazyListState()
    Scaffold(
        positionIndicator = {
            PositionIndicator(scalingLazyListState = listState)
        },
        vignette = {
            Vignette(vignettePosition = VignettePosition.TopAndBottom)
        }
    ) {
        androidx.wear.compose.foundation.lazy.ScalingLazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = listState,
            contentPadding = PaddingValues(
                top = 10.dp,
                start = 10.dp,
                end = 10.dp,
                bottom = 40.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                ListHeader {
                    Text(text = "Recipe")
                }
            }

            items(recipes) { recipe ->
                Chip(
                    modifier = Modifier
                        .width(140.dp)
                        .padding(top = 10.dp),
                    icon = {
                        Icon(
                            painter = painterResource(id = android.R.drawable.btn_star_big_on),
                            contentDescription = "Star",
                            modifier = Modifier
                                .size(16.dp)
                                .wrapContentSize(align = Alignment.Center),
                        )
                    },
                    label = {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            color = MaterialTheme.colors.onPrimary,
                            text = recipe.title,
                            fontSize = 12.sp
                        )
                    },
                    onClick = {
                        navController.navigate("recipeDetails/${recipe.id}")
                    }
                )
            }

            item {
                Chip(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .height(30.dp),
                    contentPadding = PaddingValues(6.dp),
                    shape = RoundedCornerShape(60),
                    label = {
                        Text(
                            text = "Add Recipe",
                            fontSize = 9.sp,
                            modifier = Modifier.fillMaxWidth(),
                        )
                    },
                    onClick = { navController.navigate("addRecipe") },
                    icon = {
                        Icon(
                            painter = painterResource(id = android.R.drawable.ic_input_add),
                            contentDescription = "Add",
                            modifier = Modifier.size(12.dp),
                            tint = Color.Black
                        )
                    },
                )
            }
        }
    }
}