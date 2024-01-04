package com.example.recipes.presentation.addRecipe.addInput

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import com.example.recipes.presentation.addRecipe.AddRecipeviewModel
import com.example.recipes.presentation.reusableComponents.RecipeField
import java.time.LocalTime

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AddInputScreen(
    navController: NavController,
    inputName: String,
    addRecipeViewModel: AddRecipeviewModel
) {
    val defaultPrepTime = LocalTime.of(0, 0, 0)
    val enteredTitle = addRecipeViewModel.recipeTitle.collectAsState().value
    val enteredPrepTime = addRecipeViewModel.prepTime.collectAsState().value
    val enteredCookTime = addRecipeViewModel.cookTime.collectAsState().value
    var enteredIngredients = addRecipeViewModel.ingredientsList.collectAsState().value
    val focusManager = LocalFocusManager.current

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
                    RecipeField(
                        field = inputName,
                        labelText = "Enter Recipe Title",
                        keyboardType = KeyboardType.Text,
                        enteredInput = enteredTitle,
                        onInputChange = { newValue ->
                            addRecipeViewModel.setRecipeTitle(newValue)
                        })
                }

                "Prep Time" -> {
                    RecipeField(
                        field = inputName,
                        labelText = "Enter preparation time in minutes",
                        keyboardType = KeyboardType.Number,
                        enteredInput = enteredPrepTime.toString(),
                        onInputChange = { newValue ->
                            addRecipeViewModel.setRecipePrepTime(newValue.toInt())
                        })
                }

                "Cook Time" -> {
                    RecipeField(
                        field = inputName,
                        labelText = "Enter cook time in minutes",
                        keyboardType = KeyboardType.Number,
                        enteredInput = enteredCookTime.toString(),
                        onInputChange = { newValue ->
                            addRecipeViewModel.setRecipeCookTime(newValue.toInt())
                        })
                }

                "Ingredients" -> {
                    val enteredIngredient = remember { mutableStateOf("") }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        OutlinedTextField(
                            value = enteredIngredient.value,
                            onValueChange = { enteredIngredient.value = it },
                            modifier = Modifier
                                .height(40.dp)
                                .width(140.dp),
                            textStyle = TextStyle(fontSize = 9.sp),
                            label = {
                                androidx.wear.compose.material.Text(
                                    text = "Enter Ingredients",
                                    fontSize = 8.sp
                                )
                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    if (enteredIngredient.value.isNotEmpty()) {
                                        addRecipeViewModel.addIngredient(enteredIngredient.value)
                                        enteredIngredient.value = ""
                                    }
                                    focusManager.clearFocus()
                                }
                            ),
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = Color.LightGray,
                                cursorColor = Color.Blue,
                                placeholderColor = Color.Gray,
                                unfocusedIndicatorColor = Color.Gray,
                                focusedIndicatorColor = Color.LightGray
                            ),
                            shape = RoundedCornerShape(80)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        FlowRow(Modifier.padding(horizontal = 4.dp)) {
                            enteredIngredients.forEach { ingredient ->
                                Chip(
                                    modifier = Modifier
                                        .height(22.dp)
                                        .padding(bottom = 3.dp)
                                        .background(MaterialTheme.colors.background),
                                    label = {
                                        Text(
                                            ingredient,
                                            fontSize = 6.sp,
                                            textAlign = TextAlign.Center
                                        )
                                    },
                                    onClick = { /* Handle chip click if needed */ }
                                )
                                Spacer(modifier = Modifier.width(3.dp))
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.padding(top = 16.dp))
            Row {
                // Cancel button
                IconButton(
                    modifier = Modifier.size(25.dp),
                    onClick = {
                        addRecipeViewModel.resetInputFor(inputName)
                        navController.popBackStack()
                    },
                ) {
                    Icon(
                        Icons.Filled.Close,
                        contentDescription = "Cancel",
                        tint = MaterialTheme.colors.error
                    )
                }

                Spacer(modifier = Modifier.width(40.dp))

                // Save button
                IconButton(
                    modifier = Modifier.size(25.dp),
                    onClick = {
                        when (inputName) {
                            "Title" -> {
                                addRecipeViewModel.setRecipeTitle(enteredTitle)
                            }

                            "Prep Time" -> {
                                addRecipeViewModel.isTimeFieldSaved(inputName, isSaved = true)
                                addRecipeViewModel.setRecipePrepTime(enteredPrepTime)
                            }

                            "Cook Time" -> {
                                addRecipeViewModel.isTimeFieldSaved(inputName, isSaved = true)
                                addRecipeViewModel.setRecipeCookTime(enteredCookTime)
                            }
                        }

                        navController.popBackStack()
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
