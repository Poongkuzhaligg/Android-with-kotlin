package com.example.mytestapp.bottomNav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBar(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Favorites: BottomBar(
        route = "favorites",
        title = "Favorites",
        icon = Icons.Default.Favorite
    )
    object Recent: BottomBar(
        route = "recent",
        title = "Recent",
        icon = Icons.Default.Info
    )
    object Contact: BottomBar(
        route = "contact",
        title = "Contact",
        icon = Icons.Default.AccountCircle
    )
    object Call: BottomBar(
        route = "call",
        title = "Call",
        icon = Icons.Default.Call
    )
}
