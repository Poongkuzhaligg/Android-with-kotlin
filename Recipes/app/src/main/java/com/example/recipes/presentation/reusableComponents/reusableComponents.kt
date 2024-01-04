package com.example.recipes.presentation.reusableComponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.items
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import com.google.android.horologist.composables.TimePicker
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun ButtonExample(
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        // Button
        Button(
            modifier = Modifier.size(ButtonDefaults.LargeButtonSize),
            onClick = { /* ... */ },
        ) {
        }
    }
}


@Composable
fun ChipLayout(modifier: Modifier, label: String) {
    Chip(
        modifier = modifier,
        label = { Text(text = label, fontSize = 8.sp) },
        onClick = { /*TODO*/ })
}

@Composable
fun TimePickerLayout(
    label: String,
    timeState: LocalTime,
    onTimeChange: (LocalTime) -> Unit
) {
    val timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss")
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.height(110.dp)
    ) {
        Text(text = label, style = androidx.wear.compose.material.MaterialTheme.typography.caption2)

        TimePicker(
            onTimeConfirm = onTimeChange,
            showSeconds = true,
            time = timeState
        )

        Text(
            text = timeState.format(timeFormatter), // Display format
        )
    }

}

@Composable
fun RecipeField(
    field: String,
    labelText: String,
    keyboardType: KeyboardType,
    enteredInput: String,
    onInputChange: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = enteredInput,
        onValueChange = onInputChange,
        modifier = Modifier
            .height(60.dp)
            .width(160.dp),
        textStyle = TextStyle(fontSize = 9.sp),
        label = { Text(text = labelText, fontSize = 8.sp) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.LightGray,
            placeholderColor = Color.Gray,
            unfocusedIndicatorColor = Color.Gray,
            focusedIndicatorColor = Color.LightGray
        ),
        shape = RoundedCornerShape(80)
    )
}

@Composable
fun IngredientsInput(
    addIngredient: (String) -> Unit,
    ingredients: List<String>,
    removeIngredient: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier.height(60.dp)) {
        OutlinedTextField(
            value = "",
            onValueChange = { newValue ->
                if (newValue.endsWith("\n")) {
                    addIngredient(newValue.trim())
                }
            },
            modifier = Modifier
                .height(60.dp)
                .width(140.dp),
            textStyle = TextStyle(fontSize = 9.sp),
            label = { Text(text = "Enter Ingredients", fontSize = 8.sp) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.LightGray,
                cursorColor = Color.Blue,
                placeholderColor = Color.Gray,
                unfocusedIndicatorColor = Color.Gray,
                focusedIndicatorColor = Color.LightGray
            ),
            shape = RoundedCornerShape(80)
        )
        Spacer(modifier = Modifier.height(8.dp))
        ScalingLazyColumn {
            items(ingredients) { ingredient ->
                IngredientChip(ingredient, removeIngredient)
            }
        }
    }
}

@Composable
fun IngredientChip(ingredient: String, removeIngredient: (String) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(8.dp)
            .clickable { removeIngredient(ingredient) }
    ) {
        Text(text = ingredient, color = Color.White)
        Icon(
            Icons.Filled.Close,
            contentDescription = "Remove ingredient",
        )
    }
}
