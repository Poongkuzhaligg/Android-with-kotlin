package com.example.recipes.presentation.repository

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import com.example.recipes.presentation.model.RecipeDetail
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class RecipeItemsRepository @Inject constructor( @ApplicationContext val context: Context) {
    private val recipes: MutableStateFlow<List<RecipeDetail>> = MutableStateFlow(emptyList())

    suspend fun getRecipesFromAssets(): List<RecipeDetail> {
        val jsonString = context.assets.open("json/recipe-items.json").bufferedReader().use { it.readText() }
        val gson = Gson()
        val jsonArray = gson.fromJson(jsonString, JsonArray::class.java)
        val foodCodeList = jsonArray.map { gson.fromJson(it, RecipeDetail::class.java) }
        return foodCodeList
    }
}