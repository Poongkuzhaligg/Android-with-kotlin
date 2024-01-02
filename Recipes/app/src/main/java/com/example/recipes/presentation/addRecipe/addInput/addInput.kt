package com.example.recipes.presentation.addRecipe.addInput

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.example.recipes.presentation.addRecipe.AddRecipeviewModel
import com.example.recipes.presentation.model.RecipeTitleState
import com.example.recipes.presentation.reusableComponents.RecipeTitleTextField
import com.example.recipes.presentation.reusableComponents.TimePickerLayout
import java.time.LocalTime

@Composable
fun AddInputScreen(
    navController: NavController,
    inputName: String,
    addRecipeViewModel: AddRecipeviewModel
) {
    val defaultPrepTime = LocalTime.of(0, 0, 0)
    val enteredTitleState = addRecipeViewModel.recipeTitle.collectAsState().value
    val enteredPrepTime = addRecipeViewModel.prepTime.collectAsState()
    val enteredCookTime = addRecipeViewModel.cookTime.collectAsState()
    var enteredIngredients = remember { addRecipeViewModel.ingredientsList.value }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            when (inputName) {
                "Title" -> {
                    Log.d("hello 1", enteredTitleState.recipeTitle)
                    RecipeTitleTextField(
                        enteredTitle = enteredTitleState.recipeTitle,
                        onTitleChange = { newValue ->
                            addRecipeViewModel.setRecipeTitle(RecipeTitleState(newValue))
                        })
                    Log.d("hello", enteredTitleState.recipeTitle)
                    Text(text = enteredTitleState.recipeTitle)

                }

                "Prep Time" -> {
                    TimePickerLayout(
                        label = "Prep Time",
                        timeState = enteredPrepTime.value,
                        onTimeChange = { newTime -> addRecipeViewModel.setRecipePrepTime(newTime) }
                    )

                }

                "Cook Time" -> {
                    TimePickerLayout(
                        label = "Prep Time",
                        timeState = enteredCookTime.value,
                        onTimeChange = { newTime -> addRecipeViewModel.setRecipeCookTime(newTime) }
                    )
                }

                "Ingredients" -> {
                    // Code for ingredients input (e.g., TextField list)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Row {
                // Cancel button
                IconButton(
                    onClick = {
                        when (inputName) {
                            "Title" -> addRecipeViewModel.setRecipeTitle(RecipeTitleState(""))
                            "Prep Time" -> {
                                addRecipeViewModel.setRecipePrepTime(LocalTime.of(0, 0, 0))
                            }

                            "Cook Time" -> {
                                addRecipeViewModel.setRecipeCookTime(LocalTime.of(0, 0, 0))
                            }
                        }
                        navController.navigate("addRecipe")
                    },
                ) {
                    Icon(
                        Icons.Filled.Close,
                        contentDescription = "Cancel",
                        tint = MaterialTheme.colors.error
                    )
                }
                // Save button
                IconButton(
                    onClick = {
                        when (inputName) {
                            "Title" -> addRecipeViewModel.setRecipeTitle(
                                RecipeTitleState(
                                    enteredTitleState.recipeTitle
                                )
                            )
                        }

                        navController.navigate("addRecipe")
                    },
                ) {
                    Icon(
                        Icons.Filled.Check,
                        contentDescription = "Save",
                        tint = MaterialTheme.colors.primary
                    )
                }
            }
        }
    }
}