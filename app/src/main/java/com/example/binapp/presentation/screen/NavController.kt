package com.example.binapp.presentation.screen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.binapp.presentation.viewmodel.BinViewModel
import com.example.binapp.tools.historyNav
import com.example.binapp.tools.searchingNav

@Composable
fun NavController(viewModel: BinViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = searchingNav
    ) {
        composable(searchingNav) {
            BinSearchingScreen(viewModel = viewModel, navController = navController)
        }
        composable(historyNav) {
            BinHistoryScreen(viewModel = viewModel, navController = navController)
        }
    }
}