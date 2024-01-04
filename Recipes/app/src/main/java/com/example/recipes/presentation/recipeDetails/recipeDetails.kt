package com.example.recipes.presentation.recipeDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.example.recipes.presentation.model.RecipeDetail
import com.example.recipes.presentation.recipes.RecipeViewModel
import com.example.recipes.presentation.reusableComponents.ChipLayout
import kotlinx.coroutines.flow.StateFlow

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
                fontSize = MaterialTheme.typography.title3.fontSize,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                overflow = TextOverflow.Clip
            )

            Spacer(modifier = Modifier.height(10.dp))

            ChipLayout(
                modifier = Modifier
                    .padding(bottom = 4.dp)
                    .size(width = 110.dp, height = 30.dp),
                label = "Prep Time: $prepTime"
            )

            ChipLayout(
                modifier = Modifier
                    .padding(bottom = 4.dp)
                    .size(width = 110.dp, height = 30.dp),
                label = "Cook Time: $cookTime"
            )
        }
    }
}
