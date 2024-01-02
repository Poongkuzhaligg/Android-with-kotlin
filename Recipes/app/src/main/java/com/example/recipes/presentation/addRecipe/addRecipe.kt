package com.example.recipes.presentation.addRecipe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.wear.compose.foundation.lazy.items
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.ListHeader
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text

@Composable
fun AddRecipeScreen(navController: NavController, addRecipeViewModel: AddRecipeviewModel) {
    val enteredTitle = addRecipeViewModel.recipeTitle.collectAsState()
    val isTitleSaved = enteredTitle.value.recipeTitle.isNotEmpty()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.onBackground),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val recipeFields = listOf("Title", "Prep Time", "Cook Time", "Ingredients")
            androidx.wear.compose.foundation.lazy.ScalingLazyColumn {
                item {
                    ListHeader {
                        Text(
                            text = "Add Recipe",
                            color = Color.DarkGray,
                            fontSize = 16.sp
                        )
                    }
                }
                items(recipeFields) { field ->
                    androidx.wear.compose.material.Chip(
                        modifier = Modifier
                            .width(140.dp)
                            .height(40.dp)
                            .padding(top = 10.dp),
                        icon = {
                            val iconPainter =
                                rememberVectorPainter(if (isTitleSaved) Icons.Filled.Check else Icons.Default.Add)
                            Icon(
                                painter = iconPainter,
                                contentDescription = if (isTitleSaved) "Save" else "Add",
                                modifier = Modifier
                                    .size(16.dp)
                                    .wrapContentSize(align = Alignment.Center),
                                tint = Color.DarkGray
                            )
                        },
                        label = {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                color = Color.DarkGray,
                                text = field,
                                fontSize = 10.sp
                            )
                        },
                        onClick = { navController.navigate("addInput/${field}") }
                    )
                }

                item {
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.padding(top = 8.dp),
                    ) {
                        Text(text = "SAVE", fontSize = 8.sp)
                    }
                }
            }
        }
    }
}