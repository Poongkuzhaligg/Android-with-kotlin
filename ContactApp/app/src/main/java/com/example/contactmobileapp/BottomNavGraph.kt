package com.example.contactdetails

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.contactdetails.screens.callscreen.CallScreen
import com.example.contactdetails.screens.ContactsScreen
import com.example.contactdetails.screens.favoritesscreen.FavoritesScreen
import com.example.contactdetails.screens.RecentScreen

@Composable
fun BottomNavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Contact.route,
    ){
        composable(route = BottomBarScreen.Favorites.route){
            FavoritesScreen()
        }
        composable(route = BottomBarScreen.Recent.route){
            RecentScreen()
        }
        composable(route = BottomBarScreen.Contact.route){
            ContactsScreen()
        }
        composable(route = BottomBarScreen.Call.route){
            CallScreen()
        }
    }
}