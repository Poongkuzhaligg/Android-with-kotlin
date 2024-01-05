package com.example.contactssyncapp.presentation

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import com.example.contactssyncapp.presentation.connection.ConnectDeviceViewModel
import com.example.contactssyncapp.presentation.connection.ConnectionScreen

@Composable
fun MainScreen(navController: NavHostController) {
    val connectDeviceViewModel: ConnectDeviceViewModel = hiltViewModel()

    SwipeDismissableNavHost(
        navController = navController,
        startDestination = "ConnectionScreen",
        modifier = Modifier.background(MaterialTheme.colors.background)
    ) {
        composable("ConnectionScreen") {
            ConnectionScreen(navController = navController, connectDeviceViewModel)
        }
    }
}



