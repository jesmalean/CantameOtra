package com.example.cantameotra.navigation

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cantameotra.R
import com.example.cantameotra.ui.theme.White

@Composable
fun AnimatedHeaderContent(screen: String, isDrawerOpen: Boolean) {
    val showLogo = remember(isDrawerOpen) { isDrawerOpen }

    if (showLogo) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier.height(120.dp)
        )
    } else {
        Crossfade(targetState = screen, label = "HeaderTransition") { currentRoute ->
            when (currentRoute) {
                "Salas" -> {
                    Image(
                        painter = painterResource(id = R.drawable.salas),
                        contentDescription = "Salas",
                        modifier = Modifier.height(140.dp)
                    )
                }
                "Reservas" -> {
                    Image(
                        painter = painterResource(id = R.drawable.icono_reservas),
                        contentDescription = "Reservas",
                        modifier = Modifier.height(140.dp)
                    )
                }
                else -> {
                    // Imagen por defecto o nada
                }
            }
        }
    }
}
