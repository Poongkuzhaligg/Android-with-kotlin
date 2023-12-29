package com.example.recipes.presentation.addRecipe.addInput

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.example.recipes.presentation.addRecipe.AddRecipeviewModel
import com.example.recipes.presentation.reusableComponents.TimePickerLayout
import java.time.LocalTime

@Composable
fun AddInputScreen(navController: NavController, inputName: String) {
    val addRecipeViewModel: AddRecipeviewModel = hiltViewModel()
    val enteredTitle = remember { mutableStateOf("") }
    val enteredPrepTime = remember { mutableStateOf(LocalTime.now()) }
    val enteredCookTime = remember { mutableStateOf(LocalTime.now()) }
    val enteredIngredients = remember { mutableListOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            when (inputName) {
                "Title" -> {
                    TextField(
                        value = enteredTitle.value,
                        onValueChange = {
                            enteredTitle.value = it
                            addRecipeViewModel.setRecipeTitle(it)},
                        modifier = Modifier
                            .height(30.dp)
                            .width(140.dp),
                        label = { Text(text = "Enter Recipe ${inputName}", fontSize = 9.sp) },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    )
                }
                "Prep Time" -> {
                    var prepTime= remember { mutableStateOf(LocalTime.now()) }
                    TimePickerLayout(
                        label = "Prep Time",
                        timeState = prepTime,
                        onTimeChange = { newTime -> prepTime.value = newTime }
                    )

                }
                "Cook Time" -> {
                    var cookTime= remember { mutableStateOf(LocalTime.now()) }
                    TimePickerLayout(
                        label = "Prep Time",
                        timeState = cookTime,
                        onTimeChange = { newTime -> cookTime.value = newTime }
                    )
                    // Code for cook time input (e.g., NumberPicker)
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
                            "Title" -> addRecipeViewModel.setRecipeTitle("")
                            "Prep Time" -> {
                                val prepTimeInSeconds = enteredPrepTime.value.toSecondOfDay()
                                addRecipeViewModel.setRecipePrepTime(prepTimeInSeconds)
                            }
                            "Cook Time" -> {
                                val cookTimeInSeconds = enteredCookTime.value.toSecondOfDay()
                                addRecipeViewModel.setRecipeCookTime(cookTimeInSeconds)
                            }

                        }
                        navController.popBackStack()
                              },
                ) {
                    Icon(Icons.Filled.Close, contentDescription = "Cancel", tint = Color.Gray)
                }
                // Save button
                IconButton(
                    onClick = {
                        when (inputName) {
                            "Title" -> addRecipeViewModel.setRecipeTitle(enteredTitle.value)
                            "Prep Time" -> addRecipeViewModel.setRecipePrepTime(0)
                        }
                        navController.popBackStack()
                    },
                ) {
                    Icon(Icons.Filled.Check, contentDescription = "Save", tint = Color.DarkGray)
                }
            }
        }
    }
}