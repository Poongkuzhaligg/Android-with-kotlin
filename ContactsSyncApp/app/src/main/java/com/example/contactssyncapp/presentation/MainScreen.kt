package com.example.contactssyncapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import com.example.contactssyncapp.presentation.contacts.ContactScreen

@Composable
fun MainScreen(navController: NavHostController) {
    var scanning = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                if (isBLESupported()) {
                    checkAndRequestBluetoothPermission()
                    if (isBluetoothEnabled()) {
                        scanning = !scanning
                        if (scanning) {
                            // Start BLE scan
                            scanLeDevice()
                        } else {
                            // Stop BLE scan
                            stopLeScan()
                        }
                    }
                } else {
                    // Handle the case where BLE is not supported
                }
            }
        ) {
            Text(if (scanning) "Stop Scan" else "Start Scan")
        }

        if (scanning) {
            // Display the list of available devices
            DeviceList(leDeviceListAdapter)
        }
    }
}





