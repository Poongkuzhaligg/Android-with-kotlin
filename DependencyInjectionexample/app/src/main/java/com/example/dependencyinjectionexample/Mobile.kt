package com.example.dependencyinjectionexample

import android.util.Log
import javax.inject.Inject

class Mobile @Inject constructor(val battery: Battery, val simCard: SimCard, val display: Display){
    init {
        battery.displayBatteryPower()
        simCard.connectToNetwork()
        display.turnOnScreen()

        Log.i("Mobile", "Mobile Created Successfully!")
    }

    fun turnOnMobile() {
        Log.i("Mobile", "Mobile is turned ON!")
    }
}