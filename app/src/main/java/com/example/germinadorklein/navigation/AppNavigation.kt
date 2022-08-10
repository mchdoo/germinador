package com.example.germinadorklein.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.germinadorklein.screens.BandejasScreen
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.germinadorklein.screens.DetalleBandejaScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "bandejas") {
        composable(Screen.Bandejas.route) { BandejasScreen(navController = navController) }
        composable(
            route = Screen.DetalleBandeja.route + "/{seed}",
            arguments = listOf(
                navArgument("seed") {
                    type = NavType.StringType
                }
            )
        ) { entry -> DetalleBandejaScreen(navController = navController, seed = entry.arguments?.getString("seed")) }
    }
}

sealed class Screen(val route: String) {
    object Bandejas: Screen("bandejas")
    object DetalleBandeja: Screen("detalle_bandeja")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}