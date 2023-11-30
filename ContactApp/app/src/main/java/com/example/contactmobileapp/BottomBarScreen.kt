package com.example.contactdetails

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.contactmobileapp.R

sealed class BottomBarScreen (
    val route: String,
    val title: String,
    val icon: ImageVector
    ){

    object Favorites: BottomBarScreen(
        route = "favorites",
        title = "Favorites",
        icon = Icons.Default.Favorite
    )
    object Recent: BottomBarScreen(
        route = "recent",
        title = "Recent",
        icon = Icons.Default.Info
    )
    object Contact: BottomBarScreen(
        route = "contact",
        title = "Contact",
        icon = Icons.Default.AccountCircle
    )
    object Call: BottomBarScreen(
        route = "call",
        title = "Call",
        icon = Icons.Default.Call
    )

}