package com.example.mywearos.presentation.repository

import android.content.Context
import com.dmdbrands.perfectportions.config.AppConfig
import com.example.mywearos.presentation.model.RecipeData
import com.google.gson.Gson
import org.json.JSONArray
import javax.inject.Inject

class RecipeItemsRepository constructor(
    private val context: Context,
    ) {

    fun getRecipes(): List<RecipeData> {
        val jsonString = context.assets.open("java/com/example/mywearos/presentation/assets/recipe-items.json").bufferedReader().use { it.readText() }
        val gson = Gson()
        val recipeArray = gson.fromJson(jsonString, Array<RecipeData>::class.java)
        return recipeArray.toList()
    }
}