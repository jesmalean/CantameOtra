package com.example.cantameotra.navigation

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.cantameotra.R

data class Items(
    val label: String,
    val selected: Boolean,
    val icon: Int,
    val contentDescription: String,
    val route: String
)

object ItemsProvider {
    val itemsArray = arrayOf(
        Items("Salas", false, R.drawable.icono_salas, "Salas", Routes.Salas.route),
        Items("Reservas", false, R.drawable.icono_reservas, "Reservas", Routes.Reservas.route),
        Items("Mapas", false, R.drawable.icono_mapa, "Mapas", Routes.Mapas.route),
        Items("Perfiles", false, R.drawable.icono_perfil, "Perfiles", Routes.Perfiles.route)
    )
}



// Convertimos los PNG en iconos
@Composable
fun CustomIcon(iconResId: Int, contentDescription: String?) {
    Icon(
        painter = painterResource(id = iconResId),
        contentDescription = contentDescription
    )
}