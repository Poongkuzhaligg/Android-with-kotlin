package com.example.recipes.presentation.recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipes.presentation.model.RecipeDetail
import com.example.recipes.presentation.repository.RecipeItemsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(private val repository: RecipeItemsRepository) :
    ViewModel() {
    private val _recipes: MutableStateFlow<List<RecipeDetail>> = MutableStateFlow(emptyList())
    val recipes: StateFlow<List<RecipeDetail>> get() = _recipes.asStateFlow()

    init {
        viewModelScope.launch {
            val parsedRecipes = repository.getRecipesFromAllSources()
            _recipes.value = parsedRecipes
        }
    }

    fun fetchRecipes(): StateFlow<List<RecipeDetail>> = recipes

    fun formatTime(timeInMinutes: Int, forDisplay: Boolean): String {
        val hours = timeInMinutes / 60
        val minutes = timeInMinutes % 60

        return when {
            forDisplay -> {
                when {
                    hours > 0 -> "${hours} hr ${minutes} mins"
                    else -> "${minutes} mins"
                }
            }

            else -> String.format("%02d:%02d", hours, minutes)
        }
    }

    fun addNewRecipe(newRecipe: RecipeDetail) {
        viewModelScope.launch {
            _recipes.value += newRecipe
            repository.saveRecipeToInternalStorage(_recipes.value)
        }
    }


}
