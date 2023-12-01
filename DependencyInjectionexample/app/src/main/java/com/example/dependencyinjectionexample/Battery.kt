package com.example.dependencyinjectionexample

import android.util.Log
import javax.inject.Inject

class Battery  @Inject constructor(){
    init {
        Log.i("Mobile", "Battery Created")
    }

    fun displayBatteryPower() {
        Log.i("Mobile", "Battery is 100%")
    }
}