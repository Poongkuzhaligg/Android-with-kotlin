package com.example.stateflowexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.tooling.preview.Preview
import com.example.stateflowexample.ui.theme.StateFlowExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StateFlowExampleTheme {
                val viewModel = viewModel<MainViewModel>()
                val flowColor  = viewModel.currentColor.collectAsState()

                Box(
                   modifier = Modifier
                       .fillMaxSize()
                       .background(Color(flowColor.value))
                       .clickable { viewModel.generateNewColor() }
                ) {
                    Text("Click me!")
                }
            }
        }
    }
}
