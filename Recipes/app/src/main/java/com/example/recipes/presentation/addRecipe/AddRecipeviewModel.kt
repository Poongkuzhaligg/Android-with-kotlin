package com.example.recipes.presentation.addRecipe

import androidx.lifecycle.ViewModel
import com.example.recipes.presentation.model.RecipeDetail
import com.example.recipes.presentation.repository.RecipeItemsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AddRecipeviewModel @Inject constructor(private val recipeRepository: RecipeItemsRepository) :
    ViewModel() {
    private val _recipeTitle = MutableStateFlow("")
    val recipeTitle: StateFlow<String> = _recipeTitle.asStateFlow()

    private val _prepTime = MutableStateFlow(0)
    val prepTime: StateFlow<Int> = _prepTime.asStateFlow()

    private val _cookTime = MutableStateFlow(0)
    val cookTime: StateFlow<Int> = _cookTime.asStateFlow()

    private val _isPrepTimeSaved = MutableStateFlow(false)
    val isPrepTimeSaved: StateFlow<Boolean> = _isPrepTimeSaved.asStateFlow()

    private val _isCookTimeSaved = MutableStateFlow(false)
    val isCookTimeSaved: StateFlow<Boolean> = _isCookTimeSaved.asStateFlow()

    private val _ingredientsList = MutableStateFlow(mutableListOf<String>())
    val ingredientsList: StateFlow<List<String>> = _ingredientsList


    fun setRecipeTitle(newTitle: String) {
        _recipeTitle.value = newTitle
    }

    fun setRecipePrepTime(newTime: Int) {
        _prepTime.value = newTime
    }

    fun setRecipeCookTime(newTime: Int) {
        _cookTime.value = newTime
    }

    fun isTimeFieldSaved(fieldName: String, isSaved: Boolean) {
        when (fieldName) {
            "Prep Time" -> _isPrepTimeSaved.value = isSaved
            "Cook Time" -> _isCookTimeSaved.value = isSaved
        }
    }

    fun addIngredient(ingredient: String) {
        _ingredientsList.value += ingredient
    }

    fun removeIngredient(ingredient: String) {
        _ingredientsList.value -= ingredient
    }

    fun isRecipeSaved(): Boolean {
        var isSaved = _recipeTitle.value.isNotEmpty() && _ingredientsList.value.isNotEmpty()
                && _isCookTimeSaved.value && _isPrepTimeSaved.value
        return isSaved
    }

    fun resetInputFor(inputName: String) {
        when (inputName) {
            "Title" -> setRecipeTitle("")
            "Prep Time" -> {
                setRecipePrepTime(0)
                isTimeFieldSaved(inputName, isSaved = false)
            }

            "Cook Time" -> {
                setRecipeCookTime(0)
                isTimeFieldSaved(inputName, isSaved = false)
            }

            "Ingredients" -> _ingredientsList.value = mutableListOf()

            "Reset All" -> {
                _recipeTitle.value = ""
                _prepTime.value = 0
                _cookTime.value = 0
                _isPrepTimeSaved.value = false
                _isCookTimeSaved.value = false
                _ingredientsList.value = mutableListOf()
            }
        }
    }

    fun createNewRecipe(recipes: List<RecipeDetail>): RecipeDetail {
        val highestId = recipes.maxByOrNull { it.id }?.id ?: -1
        val nextRecipeId = highestId + 1
        val newRecipe = RecipeDetail(
            title = _recipeTitle.value,
            id = nextRecipeId,
            prepTime = _prepTime.value,
            cookTime = _cookTime.value,
            ingredients = _ingredientsList.value
        )
        return newRecipe
    }
}