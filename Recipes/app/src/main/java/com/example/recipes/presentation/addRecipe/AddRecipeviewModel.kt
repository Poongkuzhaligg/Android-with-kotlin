package com.example.recipes.presentation.addRecipe

import android.util.Log
import androidx.compose.ui.text.intl.Locale
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipes.presentation.model.RecipeTitleState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.text.SimpleDateFormat
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class AddRecipeviewModel  @Inject constructor(): ViewModel() {
    private val _recipeTitle = MutableStateFlow(RecipeTitleState())
    val recipeTitle: StateFlow<RecipeTitleState> = _recipeTitle.asStateFlow()

    private val _prepTime = MutableStateFlow(LocalTime.of(0, 0, 0))
    val prepTime: StateFlow<LocalTime> = _prepTime.asStateFlow()

    private val _cookTime = MutableStateFlow(LocalTime.of(0, 0, 0))
    val cookTime: StateFlow<LocalTime> = _cookTime.asStateFlow()

    private val _ingredientsList = MutableStateFlow(mutableListOf<String>())
    val ingredientsList: StateFlow<List<String>> = _ingredientsList
    private val _recipeFields = MutableStateFlow(mutableListOf("Title", "Prep Time", "Cook Time", "Ingredients"));
    val recipeFields: StateFlow<List<String>> = _recipeFields


    fun setRecipeTitle(newTitle: RecipeTitleState) {
        val currentRecipeTitle = _recipeTitle.value
        _recipeTitle.value = currentRecipeTitle.copy(recipeTitle = newTitle.recipeTitle)
        Log.d("ViewModel", "Recipe Title updated: ${newTitle}")
    }

    fun setRecipePrepTime(newTime: LocalTime) {
        _prepTime.value = newTime
    }

    fun setRecipeCookTime(newTime: LocalTime) {
        viewModelScope.launch {
            _cookTime.value = newTime
        }
    }

    fun addIngredient(ingredient: String) {
        _ingredientsList.value += ingredient
    }

    fun removeIngredient(ingredient: String) {
        _ingredientsList.value -= ingredient
    }

    fun resetInputFor(inputName: String) {
        // ... (same as before)
    }
}