package com.goblin.consulting.biomapp.navigation

sealed class Screen(val route : String) {
    object Splash : Screen(route = "splash_screen")
    object Map : Screen(route = "map_screen")
}
