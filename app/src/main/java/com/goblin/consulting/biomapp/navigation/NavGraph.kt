package com.goblin.consulting.biomapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.goblin.consulting.biomapp.ui.main.MainActivityViewModel
import com.goblin.consulting.biomapp.ui.main.SplashScreen
import com.goblin.consulting.biomapp.ui.map.MapScreen

@Composable
fun setupNavGraph(
    navController: NavHostController,
    viewModel: ViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(
            route = Screen.Splash.route
        ) {
            SplashScreen(
                navController = navController,
                viewModel = viewModel as MainActivityViewModel
            )
        }
        composable(
            route = Screen.Map.route
        ) {
            MapScreen(
            )
        }
    }
}