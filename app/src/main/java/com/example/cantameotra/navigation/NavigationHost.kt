package com.example.cantameotra.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost  // Importar NavHost desde androidx.navigation.compose
import androidx.navigation.compose.composable  // Importar composable desde androidx.navigation.compose
import com.example.cantameotra.screens.SalasScreen
import com.example.cantameotra.screens.MapasScreen
import com.example.cantameotra.screens.PerfilesScreen
import com.example.cantameotra.screens.ReservasScreen

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.Salas.route
    ) {
        composable(Routes.Salas.route) { SalasScreen() }
        composable(Routes.Reservas.route) { ReservasScreen() }
        composable(Routes.Mapas.route) { MapasScreen() }
        composable(Routes.Perfiles.route) { PerfilesScreen() }
    }
}
