package com.example.recipes.presentation.addRecipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddRecipeviewModel  @Inject constructor(): ViewModel() {

    private val _recipeTitle = MutableLiveData("")
    val recipeTitle: LiveData<String> = _recipeTitle

    private val _prepTime = MutableLiveData(0)
    val prepTime: MutableLiveData<Int> = _prepTime

    private val _cookTime = MutableLiveData(0)
    val cookTime: LiveData<Int> = _cookTime

    private val _ingredientsList = MutableLiveData(listOf<String>())
    val ingredientsList: LiveData<List<String>> = _ingredientsList

    fun setRecipeTitle(title: String) {
        _recipeTitle.value = title
    }
    fun setRecipePrepTime(prepTime: Int) {
        _prepTime.value = prepTime
    }
    fun setRecipeCookTime(cookTime: Int) {
        _cookTime.value = cookTime
    }
    fun setRecipeIngredients(ingredients: Array<String>) {
        val ingredientList = ingredients.map { it }
        _ingredientsList.value = ingredientList
    }
}