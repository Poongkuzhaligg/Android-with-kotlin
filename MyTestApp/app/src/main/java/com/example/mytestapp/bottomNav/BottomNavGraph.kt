package com.example.mytestapp.bottomNav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mytestapp.screens.CallScreen
import com.example.mytestapp.screens.ContactScreen
import com.example.mytestapp.screens.FavoritesScreen
import com.example.mytestapp.screens.RecentScreen

@Composable
fun BottomNavGraph(navHostController: NavHostController, paddingModifier: Modifier) {
    NavHost(navController = navHostController, startDestination = BottomBar.Contact.route ) {
        composable(route = BottomBar.Contact.route) {
            ContactScreen(paddingModifier)
        }
        composable(route = BottomBar.Favorites.route) {
            FavoritesScreen(paddingModifier)
        }
        composable(route = BottomBar.Recent.route) {
            RecentScreen(paddingModifier)
        }
        composable(route = BottomBar.Call.route) {
            CallScreen(paddingModifier)
        }
    }
}