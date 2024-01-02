package com.example.recipes.presentation.model

import android.icu.text.CaseMap.Title

class RecipeDetail(
    val title: String,
    val id: Int,
    val recipeImg: String,
    val prepTime: Int,
    val cookTime: Int,
    var ingredients: Array<String>
)


data class RecipeTitleState(
    val recipeTitle: String = ""
)
