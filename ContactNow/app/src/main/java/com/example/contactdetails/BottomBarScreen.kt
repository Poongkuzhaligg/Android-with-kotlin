package com.example.contactdetails

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen (
    val route: String,
    val title: String,
    val icon: Int
    ){

    object Favorites: BottomBarScreen(
        route = "favorites",
        title = "Favorites",
        icon = R.drawable.clock_icon
    )
    object Recent: BottomBarScreen(
        route = "recent",
        title = "Recent",
        icon = R.drawable.clock_icon
    )
    object Contact: BottomBarScreen(
        route = "contact",
        title = "Contact",
        icon = R.drawable.person_icon
    )
    object Call: BottomBarScreen(
        route = "call",
        title = "Call",
        icon = R.drawable.clock_icon
    )

}