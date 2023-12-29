package com.example.recipes.presentation.reusableComponents

import android.app.TimePickerDialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
        label = { Text(text = label, fontSize = 8.sp, ) },
        onClick = { /*TODO*/ })
}

@Composable
fun TimePickerLayout(
    label: String,
    timeState: MutableState<LocalTime>,
    onTimeChange: (LocalTime) -> Unit
) {
    val timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss")
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 16.dp) // Adjust for Wear OS screen
    ) {
        Text(text = label, style = MaterialTheme.typography.caption)

        Spacer(modifier = Modifier.height(4.dp))

        TimePicker(
            onTimeConfirm = onTimeChange,
            showSeconds = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = timeState.value.format(timeFormatter), // Display format
            style = MaterialTheme.typography.body1
        )
    }

}