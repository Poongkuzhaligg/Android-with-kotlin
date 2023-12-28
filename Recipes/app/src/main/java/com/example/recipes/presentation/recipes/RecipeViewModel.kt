package com.example.recipes.presentation.recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipes.presentation.model.RecipeDetail
import com.example.recipes.presentation.repository.RecipeItemsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(private val repository: RecipeItemsRepository) : ViewModel() {
    private val _recipes: MutableStateFlow<List<RecipeDetail>> = MutableStateFlow(emptyList())
    val recipes: StateFlow<List<RecipeDetail>> get() = _recipes

    init {
        viewModelScope.launch {
            val parsedRecipes = repository.getRecipesFromAssets()
            _recipes.value = parsedRecipes
        }
    }
    fun fetchRecipes(): StateFlow<List<RecipeDetail>> = recipes

    fun formatTime(timeInSeconds: Int, forDisplay: Boolean): String {
        val timeInMinutes = timeInSeconds / 60
        val hours = timeInMinutes / 60
        val minutes = timeInMinutes % 60
        val seconds = timeInSeconds % 60

        return when {
            forDisplay -> {
                when {
                    hours > 0 -> "${hours}hr"
                    else -> "${minutes} mins"
                }
            }
            else -> String.format("%02d:%02d:%02d", hours, minutes, seconds)
        }
    }
}
