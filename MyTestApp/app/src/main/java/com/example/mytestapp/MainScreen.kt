@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mytestapp

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mytestapp.bottomNav.BottomBar
import com.example.mytestapp.bottomNav.BottomNavGraph

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold (
        bottomBar = { BottomBarScreen(navHostController = navController) }
    ){ paddingValues ->
        BottomNavGraph(navHostController = navController, paddingModifier = Modifier.padding((paddingValues)))
    }
}

@Composable
fun BottomBarScreen(navHostController: NavHostController) {
    val screens = listOf(
        BottomBar.Contact,
        BottomBar.Recent,
        BottomBar.Favorites,
        BottomBar.Call
    )
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    BottomNavigation {
        screens.forEach { screen ->
            if (currentDestination != null) {
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navHostController = navHostController
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBar,
    currentDestination: NavDestination,
    navHostController: NavHostController
) {
    BottomNavigationItem(
        label = {
            Text(text = screen.title)
        },
        icon =  {
            Icon(imageVector = screen.icon, contentDescription = "Navigation Icon")
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navHostController.navigate(screen.route)
        }
    )
}