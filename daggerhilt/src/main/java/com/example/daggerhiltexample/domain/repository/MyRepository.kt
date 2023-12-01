package com.example.daggerhiltexample.domain.repository

interface MyRepository {
    suspend fun doNetworkCall()
}