package com.example.recipes.presentation.recipeDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.example.recipes.presentation.model.RecipeDetail
import com.example.recipes.presentation.recipes.RecipeViewModel
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RecipeDetailScreen(recipeId: Int, recipeViewModel: RecipeViewModel) {
    val recipesFlow: StateFlow<List<RecipeDetail>> = recipeViewModel.fetchRecipes()
    val recipes: List<RecipeDetail> by recipesFlow.collectAsState(initial = emptyList())
    val recipeDetail = recipes.find { recipeDetail -> recipeDetail.id === recipeId }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            val prepTime = recipeDetail?.let { recipeViewModel.formatTime(it.prepTime, true) }
            val cookTime = recipeDetail?.let { recipeViewModel.formatTime(it.cookTime, true) }

            Text(
                modifier = Modifier.padding(vertical = 6.dp),
                text = "${recipeDetail?.title}",
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.LightGray,
                overflow = TextOverflow.Clip
            )

            Spacer(modifier = Modifier.height(6.dp))

            Row {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .size(width = 50.dp, height = 50.dp)
                        .background(
                            MaterialTheme.colors.onSecondary,
                            shape = RoundedCornerShape(80)
                        )
                ) {
                    Text(
                        text = "Prep Time",
                        fontSize = 8.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                    )
                    Text(
                        text = "$prepTime",
                        textAlign = TextAlign.Center,
                        fontStyle = FontStyle.Italic,
                        fontSize = 7.sp,
                        color = Color.LightGray,
                        modifier = Modifier.padding(top = 5.dp)
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .size(width = 50.dp, height = 50.dp)
                        .background(
                            MaterialTheme.colors.onSecondary,
                            shape = RoundedCornerShape(80)
                        )
                ) {
                    Text(
                        text = "Cook Time",
                        fontSize = 8.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                    )
                    Text(
                        text = "$cookTime",
                        textAlign = TextAlign.Center,
                        fontStyle = FontStyle.Italic,
                        fontSize = 7.sp,
                        color = Color.LightGray,
                        modifier = Modifier.padding(top = 5.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            val listState = rememberScrollState()
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .width(140.dp)
                    .height(45.dp)
                    .verticalScroll(listState)
            ) {
                FlowRow(Modifier.padding(horizontal = 4.dp)) {
                    recipeDetail?.ingredients?.forEach { ingredient ->
                        Chip(
                            modifier = Modifier
                                .height(22.dp)
                                .padding(bottom = 3.dp)
                                .background(MaterialTheme.colors.background),
                            contentPadding = PaddingValues(6.dp),
                            label = {
                                Text(
                                    ingredient,
                                    fontSize = 6.sp,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.W400
                                )
                            },
                            onClick = { /* Handle chip click if needed */ }
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                    }
                }
            }
        }
    }
}
