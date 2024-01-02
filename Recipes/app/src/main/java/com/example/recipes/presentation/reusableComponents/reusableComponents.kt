package com.example.recipes.presentation.reusableComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Chip
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
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxHeight()
    ) {
        Text(text = label, style = androidx.wear.compose.material.MaterialTheme.typography.caption1)

        Spacer(modifier = Modifier.height(4.dp))

        TimePicker(
            onTimeConfirm = onTimeChange,
            showSeconds = true,
            time = LocalTime.of(0, 0, 0)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = timeState.format(timeFormatter), // Display format
            style = MaterialTheme.typography.body1
        )
    }

}

@Composable
fun RecipeTitleTextField(
    enteredTitle: String,
    onTitleChange: (String) -> Unit
) {
    OutlinedTextField(
        value = enteredTitle,
        onValueChange = onTitleChange,
        modifier = Modifier
            .height(60.dp)
            .width(140.dp),
        textStyle = TextStyle(fontSize = 9.sp),
        label = { Text(text = "Enter Recipe Title", fontSize = 8.sp) },
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
}