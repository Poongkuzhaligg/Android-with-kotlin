package com.example.contactdetails.screens.contactscreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SearchBarViewModel: ViewModel() {
    private val searchText = MutableStateFlow("")
    val searchedText = searchText.asStateFlow()

    private val isSearching = MutableStateFlow(false)
    val isSearch = isSearching.asStateFlow()
}