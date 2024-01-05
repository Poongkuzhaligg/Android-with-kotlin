package com.example.contactssyncapp.presentation.model

enum class PairingState {
    Idle,    // Initial state, waiting for pairing to start
    Scanning,  // Scanning for nearby devices
    DevicesFound,  // Devices found, ready for user selection
    Pairing,  // Pairing in progress with a specific device
    Paired,   // Pairing completed successfully
    Failed   // Pairing failed
}