package com.example.recipes.presentation.addRecipe

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Chip
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.wear.compose.foundation.lazy.items
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.ListHeader
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.example.recipes.presentation.recipes.RecipeViewModel

@Composable
fun AddRecipeScreen(navController: NavController) {
    val addRecipeviewModel: AddRecipeviewModel = hiltViewModel()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val recipeFields = listOf("Title", "Prep Time", "Cook Time", "Ingredients")
            androidx.wear.compose.foundation.lazy.ScalingLazyColumn {
                item { ListHeader {
                        Text(text = "Add Recipe", color = Color.Black, fontSize = 16.sp)
                    }
                }
                items(recipeFields) { field ->
                    androidx.wear.compose.material.Chip(
                        modifier = Modifier
                            .width(140.dp)
                            .height(40.dp)
                            .padding(top = 10.dp),
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_input_add),
                                contentDescription = "Add",
                                modifier = Modifier
                                    .size(16.dp)
                                    .wrapContentSize(align = Alignment.Center),
                            )
                        },
                        label = {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                color = MaterialTheme.colors.onPrimary,
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