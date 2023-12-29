package com.example.recipes.presentation.addRecipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AddRecipeviewModel  @Inject constructor(): ViewModel() {

    private val _recipeTitle = MutableStateFlow("")
    val recipeTitle: StateFlow<String> = _recipeTitle

    private val _prepTime = MutableLiveData(0)
    val prepTime: MutableLiveData<Int> = _prepTime

    private val _cookTime = MutableLiveData(0)
    val cookTime: MutableLiveData<Int> = _cookTime

    private val _ingredientsList = MutableStateFlow(emptyList<String>())
    val ingredientsList: StateFlow<List<String>> = _ingredientsList

    fun setRecipeTitle(title: String) {
        _recipeTitle.value = title
    }

    fun setRecipePrepTime(prepTime: Int) {
        _prepTime.value = prepTime
    }

    fun setRecipeCookTime(cookTime: Int) {
        _cookTime.value = cookTime
    }

    fun setRecipeIngredients(ingredients: List<String>) {
        _ingredientsList.value = ingredients
    }
}