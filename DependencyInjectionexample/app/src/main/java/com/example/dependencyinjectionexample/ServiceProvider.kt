package com.example.dependencyinjectionexample

import android.util.Log
import javax.inject.Inject

class ServiceProvider @Inject constructor(){
    init {
        Log.i("Mobile", "Service Provider Created")
    }

    fun getServiceProvider() {
        Log.i("Mobile", "Service Provider Connected Successfully")
    }
}