package com.example.recipes.presentation.repository

import android.content.Context
import com.example.recipes.presentation.model.RecipeDetail
import com.google.gson.Gson
import com.google.gson.JsonArray
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import java.io.File
import javax.inject.Inject

class RecipeItemsRepository @Inject constructor(@ApplicationContext val context: Context) {
    private val recipes: MutableStateFlow<List<RecipeDetail>> = MutableStateFlow(emptyList())

    suspend fun getRecipesFromAssets(): List<RecipeDetail> {
        val jsonString =
            context.assets.open("json/recipe-items.json").bufferedReader().use { it.readText() }
        val gson = Gson()
        val jsonArray = gson.fromJson(jsonString, JsonArray::class.java)
        val foodCodeList = jsonArray.map { gson.fromJson(it, RecipeDetail::class.java) }
        return foodCodeList
    }

    fun saveRecipeToInternalStorage(recipes: List<RecipeDetail>) {
        val file = File(context.filesDir, "saved_recipes.json")
        val json = Gson().toJson(recipes)
        file.writeText(json)
    }

    suspend fun getRecipesFromAllSources(): List<RecipeDetail> {
        val assetsRecipes = getRecipesFromAssets()
        val savedRecipes = getRecipeFromInternalStorage() ?: assetsRecipes
        return savedRecipes
    }

    private fun getRecipeFromInternalStorage(): List<RecipeDetail>? {
        val file = File(context.filesDir, "saved_recipes.json")
        if (file.exists()) {
            val json = file.readText()
            return Gson().fromJson(json, Array<RecipeDetail>::class.java)?.toList()
        } else {
            return null
        }
    }


}