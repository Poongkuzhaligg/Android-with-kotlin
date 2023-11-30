package com.example.profilecardlayout

data class UserProfile constructor(val id: Int, val name: String, val status: Boolean, val drawableId: Int){
}
val userProfileList = arrayListOf<UserProfile>(
    UserProfile(id = 0, name = "John Doe", status= true, R.drawable.john_doe),
    UserProfile(id = 1, name = "Jane Foster", status= false, R.drawable.john_doe),
    UserProfile(id = 2, name = "John Doe", status= true, R.drawable.john_doe),
    UserProfile(id = 3, name = "Jane Foster", status= false, R.drawable.john_doe),
    UserProfile(id = 4, name = "John Doe", status= true, R.drawable.john_doe),
    UserProfile(id = 5, name = "Jane Foster", status= false, R.drawable.john_doe),
    UserProfile(id = 6, name = "John Doe", status= true, R.drawable.john_doe),
    UserProfile(id = 7, name = "Jane Foster", status= false, R.drawable.john_doe),
    UserProfile(id = 8, name = "John Doe", status= true, R.drawable.john_doe),
    UserProfile(id = 9, name = "Jane Foster", status= false, R.drawable.john_doe),
    UserProfile(id = 10, name = "John Doe", status= true, R.drawable.john_doe),
    UserProfile(id = 11, name = "Jane Foster", status= false, R.drawable.john_doe),
)