package com.example.recipes.presentation.model

class RecipeDetail(
    val title: String,
    val id: Int,
    val recipeImg: String,
    val prepTime: Long,
    val cookTime: Long,
    var ingredients: Array<Ingredients>
)

class Ingredients(
    name: String,
    isChecked: Boolean
)