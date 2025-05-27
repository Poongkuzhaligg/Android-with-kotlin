package com.example.mymeapplication.data.storage.persistence

import android.content.Context

import kotlinx.coroutines.flow.first
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit // For the edit suspend function
import com.google.gson.Gson // Already used in your setValue method

abstract class BaseDataStore(private val context: Context) {
    abstract var dataStore: DataStore<Preferences>

    suspend fun setValue(key: Preferences.Key<String>, value: Any) {
        var finalValue: String
        dataStore.edit { preferences ->
            finalValue = if (value !is String) {
                Gson().toJson(value)
            } else {
                value
            }
            preferences[key] = finalValue
        }
    }

    suspend fun setBooleanValue(key: Preferences.Key<Boolean>, value: Boolean) {
        dataStore.edit {preferences ->
            preferences[key] = value
        }
    }

    suspend fun getBooleanValue(key: Preferences.Key<Boolean>): Boolean? {
        val preferences = dataStore.data.first()
        return preferences[key]
    }

    suspend fun getValue(key: Preferences.Key<String>): String? {
        val preferences = dataStore.data.first()
        return preferences[key]
    }

    suspend fun getLiveValue(key: Preferences.Key<String>, callback: (String?) -> Unit) {
        return dataStore.data.collect {
            callback(it[key])
        }
    }

    suspend fun clearValue(key: Preferences.Key<String>) {
        dataStore.edit { prefs ->
            prefs.remove(key)
        }
    }


}
