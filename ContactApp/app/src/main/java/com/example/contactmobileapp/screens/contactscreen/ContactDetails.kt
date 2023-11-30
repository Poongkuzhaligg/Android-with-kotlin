package com.example.contactdetails.screens.contactscreen

import com.example.contactdetails.R

data class ContactDetail constructor(
    val id: Int,
    val name: String,
    val contactNo: String,
    val country: String,
    val imgSrc: Int
)

val contactDetailList = arrayListOf<ContactDetail>(
    ContactDetail(id=0, name="John Doe", contactNo="+1 (913) 497-2020", country="Indonesia", R.drawable.john_doe),
    ContactDetail(id=1, name="Kayla", contactNo="+1 (951) 472-2967", country="USA", R.drawable.jane_lee),
    ContactDetail(id=2, name="Mike", contactNo="+1 (887) 478-2693", country="Canada", R.drawable.john_doe),
    ContactDetail(id=3, name="Chris", contactNo="+1 (996) 520-2967", country="New Zealand", R.drawable.john_doe),
    ContactDetail(id=4, name="Monica", contactNo="+1 (972) 566-2684", country="Sweden", R.drawable.jane_lee),
    ContactDetail(id=5, name="Jenna", contactNo="+1 (824) 467-3579", country="UK", R.drawable.jane_lee),
    ContactDetail(id=6, name="Ellen", contactNo="+1 (899) 528-3402", country="Mexico", R.drawable.jane_lee),
    ContactDetail(id=7, name="Cedric", contactNo="+1 (862) 507-3140", country="Spain", R.drawable.john_doe),
    ContactDetail(id=8, name="Serena", contactNo="+1 (901) 444-3081", country="Egypt", R.drawable.jane_lee),
    ContactDetail(id=9, name="Derrick", contactNo="+1 (831) 539-3366", country="Australia", R.drawable.john_doe),
)