package com.example.contactlistapp.di

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    @Upsert
    suspend fun upsertContact(contact: Contact)
    @Delete
    suspend fun deleteContact(contact: Contact)
    @Query("SELECT * FROM contact ORDER BY firstName ASC")
    fun getContactsOrderedByFirstName(): Flow<List<Contact >>

    @Query("SELECT * FROM contact ORDER BY lastName ASC")
    fun getContactsOrderedByLastName(): Flow<List<Contact >>

    @Query("SELECT * FROM contact ORDER BY phoneNo ASC")
    fun getContactsOrderedByPhoneNo(): Flow<List<Contact >>
}