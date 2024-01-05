package com.example.contactssyncapp.presentation.connection

import android.bluetooth.BluetoothDevice
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.CircularProgressIndicator
import androidx.wear.compose.material.Text
import com.example.contactssyncapp.presentation.model.PairingState
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState

@Composable
fun ConnectionScreen(navController: NavController, connectDeviceViewModel: ConnectDeviceViewModel) {
    val pairingState: PairingState by connectDeviceViewModel.pairingState.observeAsState(initial = PairingState.Idle)
    val discoveredDevices: List<BluetoothDevice> by connectDeviceViewModel.discoveredDevices.observeAsState(emptyList())

Text(text = "Pair")
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (pairingState) {
            PairingState.Idle -> {
                Button(onClick = { }) {
                    Text("Start Pairing")
                }
            }

            PairingState.Scanning -> {
                // Display progress indicator
                CircularProgressIndicator()
                Text("Scanning for devices...")
            }

            else -> {

            }
        }
//            PairingState.DevicesFound -> {
//                LazyColumn() {
//                    items(discoveredDevices) { device ->
//                        Text(device.name)
//                        // Allow user interaction to initiate pairing with the device
//                    }
//                }
//            }
//            PairingState.Pairing -> {
//                // Display progress indicator
//                CircularProgressIndicator()
//                Text("Pairing with ${(pairingState as PairingState.Pairing).device.name}")
//            }
//            PairingState.Paired -> {
//                Text("Paired successfully!")
//                Button(onClick = {  }) {
//                    Text("Continue")
//                }
//            }
//            PairingState.Failed -> {
//                Text("Pairing failed. Please try again.")
//                Button(onClick = { connectDeviceViewModel.startPairing() }) {
//                    Text("Retry")
//                }
//            }
    }
}
