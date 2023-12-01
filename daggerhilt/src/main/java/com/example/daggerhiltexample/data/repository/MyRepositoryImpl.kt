package com.example.daggerhiltexample.data.repository

import com.example.daggerhiltexample.data.remote.MyApi
import com.example.daggerhiltexample.domain.repository.MyRepository

class MyRepositoryImpl(
    private val api: MyApi
): MyRepository{
    override suspend fun doNetworkCall() {
    }
}