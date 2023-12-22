package com.example.mywearos.presentation.model

class RecipeData(
    val title: String,
    val recipeImg: String,
    val prepTime: Long,
    val cookTime: Long,
    var ingredients: Array<String>
    )