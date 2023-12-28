package com.example.recipes.presentation.addRecipe.addInput

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import com.example.recipes.presentation.addRecipe.AddRecipeviewModel

@Composable
fun AddInputScreen(navController: NavController, inputName: String) {
    val addRecipeViewModel: AddRecipeviewModel = hiltViewModel()
    var enteredTitle by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            TextField(
                value = enteredTitle,
                onValueChange = {enteredTitle = it},
                modifier = Modifier
                    .height(30.dp)
                    .width(140.dp),
                label = { Text(text = "Enter Recipe ${inputName}", fontSize = 9.sp) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                // Cancel button
                IconButton(
                    onClick = {navController.popBackStack() },
                ) {
                    Icon(Icons.Filled.Close, contentDescription = "Cancel", tint = Color.Gray)
                }
                // Save button
                IconButton(
                    onClick = {addRecipeViewModel.setRecipeTitle(enteredTitle)},
                ) {
                    Icon(Icons.Filled.Check, contentDescription = "Save", tint = Color.DarkGray)
                }
            }
        }
    }
}