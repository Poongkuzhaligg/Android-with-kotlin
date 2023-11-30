package com.example.model

import com.example.model.Response.ContactDetailsResponse

class ContactsRepository {
    fun getContacts(): ContactDetailsResponse = ContactDetailsResponse(arrayListOf())
}