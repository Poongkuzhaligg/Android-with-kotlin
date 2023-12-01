package com.example.dependencyinjectionexample

import dagger.Component

@Component
interface MobileComponent {
    fun getMobileInstance(): Mobile
}