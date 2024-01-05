package com.example.contactssyncapp.presentation.connection

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.contactssyncapp.presentation.model.PairingState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class ConnectDeviceViewModel @Inject constructor(): ViewModel() {
    private val _pairingState = MutableLiveData(PairingState.Idle)
    val pairingState: LiveData<PairingState> = _pairingState

    // LiveData to hold discovered devices
    private val _discoveredDevices = MutableLiveData<List<BluetoothDevice>>(emptyList())
    val discoveredDevices: LiveData<List<BluetoothDevice>> = _discoveredDevices


}