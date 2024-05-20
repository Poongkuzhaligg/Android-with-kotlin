package com.example.contactlistapp.di

sealed interface ContactEvent {
    object SaveContact: ContactEvent
    data class SetFirstName(val firstName: String): ContactEvent
    data class setLastName(val lastName: String): ContactEvent
    data class setPhoneNo(val phoneNo: String): ContactEvent
    object ShowDialog: ContactEvent
    object HideDialog: ContactEvent
    data class SortContact(val sortType: SortType): ContactEvent
    data class DeleteContact(val contact: Contact): ContactEvent
}