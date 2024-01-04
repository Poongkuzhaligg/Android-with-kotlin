package com.example.recipes.presentation.model

class RecipeDetail(
    val title: String,
    val id: Int,
    val prepTime: Int,
    val cookTime: Int,
    var ingredients: List<String>
)

