package com.example.recipes.presentation.recipeDetails

import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.wear.compose.material.Chip
import com.example.recipes.presentation.model.RecipeDetail
import com.example.recipes.presentation.navigation.rememberNavController
import com.example.recipes.presentation.recipes.RecipeViewModel
import com.example.recipes.presentation.reusableComponents.ChipLayout
import kotlinx.coroutines.flow.StateFlow
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun RecipeDetailScreen(recipeId: Int) {
    val recipeViewModel: RecipeViewModel = hiltViewModel()
    val recipesFlow: StateFlow<List<RecipeDetail>> = recipeViewModel.fetchRecipes()
    val recipes: List<RecipeDetail> by recipesFlow.collectAsState(initial = emptyList())
    val recipeDetail = recipes.find { recipeDetail -> recipeDetail.id === recipeId }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
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

            ChipLayout(
                modifier = Modifier
                    .padding(bottom = 4.dp)
                    .size(width = 100.dp, height = 30.dp),
                label = "Prep Time: $prepTime"
            )

            ChipLayout(
                modifier = Modifier
                    .padding(bottom = 4.dp)
                    .size(width = 100.dp, height = 30.dp)

                ,
                label = "Cook Time: $cookTime"
            )
        }
    }
}
