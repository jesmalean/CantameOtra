package com.example.cantameotra.navigation

sealed class Routes (val route: String) {
    data object Salas: Routes("Salas")
    data object Reservas: Routes("Reservas")
    data object Mapas: Routes("Mapas")
    data object Perfiles: Routes("Perfiles")
}