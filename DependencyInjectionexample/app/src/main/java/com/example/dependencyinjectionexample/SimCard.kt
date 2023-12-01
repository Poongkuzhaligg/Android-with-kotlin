package com.example.dependencyinjectionexample

import android.util.Log
import javax.inject.Inject

class SimCard @Inject constructor(private val serviceProvider: ServiceProvider) {
    init {
        Log.i("Mobile", "Sim Card Created")
    }

    fun connectToNetwork() {
        serviceProvider.getServiceProvider()
    }
}