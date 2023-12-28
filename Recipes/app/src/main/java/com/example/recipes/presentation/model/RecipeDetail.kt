package com.example.recipes.presentation.model

class RecipeDetail(
    val title: String,
    val id: Int,
    val recipeImg: String,
    val prepTime: Int,
    val cookTime: Int,
    var ingredients: Array<String>
)
