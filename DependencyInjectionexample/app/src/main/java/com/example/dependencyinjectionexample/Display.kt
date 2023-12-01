package com.example.dependencyinjectionexample

import android.util.Log
import javax.inject.Inject

class Display  @Inject constructor(){
    init {
        Log.i("Mobile", "Screen Created")
    }

    fun turnOnScreen() {
        Log.i("Mobile", "Turning on the screen")
    }
}