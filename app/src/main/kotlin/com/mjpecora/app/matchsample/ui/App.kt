package com.mjpecora.app.matchsample.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mjpecora.app.matchsample.ui.home.Home

@Composable
fun App(
    appState: AppState = rememberBooksAppState()
) {
    NavHost(
        navController = appState.navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            Home(hiltViewModel())
        }
    }
}