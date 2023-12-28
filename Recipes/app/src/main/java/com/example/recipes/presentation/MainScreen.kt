package com.example.recipes.presentation

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import com.example.recipes.presentation.recipeDetails.RecipeDetailScreen
import com.example.recipes.presentation.recipes.RecipeScreen

@Composable
fun MainScreen(navController: NavHostController) {
    SwipeDismissableNavHost(
        navController = navController,
        startDestination = "recipes",
        modifier = Modifier.background(MaterialTheme.colors.background)
    ) {
        composable("recipes") {
            RecipeScreen(navController = navController )
        }
        composable("recipeDetails/{recipeId}", arguments = listOf(navArgument("recipeId") { type = NavType.IntType })) {
            val recipeId = it.arguments?.getInt("recipeId") ?: 0
            RecipeDetailScreen(navController = navController,recipeId)
        }
    }
}