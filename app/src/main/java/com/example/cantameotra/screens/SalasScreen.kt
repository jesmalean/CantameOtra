package com.example.cantameotra.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Box  // Importa Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cantameotra.R


@Composable
fun SalasScreen() {
    FlipCardExample() // Aquí es donde se muestra el nuevo diseño
}


// Contenedor general de las tarjetas de salas
@Composable
fun FlipCardExample() {
    Column(
        modifier = Modifier
            .fillMaxSize() // Ocupa toda la pantalla
            .background(Color(0xFF0C0B1A)) // Fondo oscuro
            .verticalScroll(rememberScrollState()) // Permite hacer scroll si hay mucho contenido
            .padding(top = 60.dp, start = 16.dp, end = 16.dp, bottom = 16.dp) // Margen interior
    ) {
        // Título de la sección
        Text(
            "SALAS",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 28.sp,
            color = Color(0xFFE57BC2)
        )

        Spacer(modifier = Modifier.height(16.dp)) // Espacio entre título y primera tarjeta

        // Primera tarjeta: Sala Verano
        FlipCard(
            title = "Sala Verano",
            imageRes = R.drawable.sala_verano,
            description = "Disfruta del verano todo el año en un ambiente tropical con luces LED y hamacas. El lugar ideal para cantar y relajarte.",
            extra = "Sistema de sonido de última generación, pantallas, iluminación LED y barra de bebidas tropicales.",
            tableData = listOf(
                "Capacidad máxima" to "4 personas",
                "Canciones" to "59",
                "Micrófonos" to "2",
                "Consumiciones" to "2",
                "Precio/hora" to "10€/persona"
            )
        )

        Spacer(modifier = Modifier.height(24.dp)) // Espacio entre tarjetas

        // Segunda tarjeta: Sala Rock
        FlipCard(
            title = "Sala Rock",
            imageRes = R.drawable.sala_rock,
            description = "Una sala con estética rockera, luces de neón y un repertorio musical potente.",
            extra = "Ideal para cantar con energía y sentirte como en un concierto en vivo.",
            tableData = listOf(
                "Capacidad máxima" to "6 personas",
                "Canciones" to "51",
                "Micrófonos" to "4",
                "Consumiciones" to "2",
                "Precio/hora" to "12€/persona"
            )
        )
    }
}


// Componente reutilizable de tarjeta con animación de giro (como una carta)
@Composable
fun FlipCard(
    title: String,
    imageRes: Int,
    description: String,
    extra: String,
    tableData: List<Pair<String, String>>
) {
    // Estado para controlar si la tarjeta está volteada
    var flipped by remember { mutableStateOf(false) }

    // Animación de rotación
    val rotation = animateFloatAsState(
        targetValue = if (flipped) 180f else 0f, // 180º si está volteada
        animationSpec = tween(durationMillis = 600), // Duración de la animación
        label = "rotation"
    )

    // Densidad del dispositivo para ajustar la perspectiva
    val density = LocalDensity.current.density
    var cameraDistance = 30f

    // Contenedor de la tarjeta
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(385.dp) // Altura de la tarjeta
            .graphicsLayer {
                rotationY = rotation.value // Aplica la rotación Y
                cameraDistance = cameraDistance * density // Ajusta profundidad de cámara
            }
            .clickable { flipped = !flipped } // Alterna entre cara y dorso al pulsar
    ) {
        if (rotation.value <= 90f) {
            // Cara delantera de la tarjeta
            Card(
                modifier = Modifier.fillMaxSize(),
                colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.rosaFondoMenu)),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    // Título centrado con fuente personalizada
                    Text(
                        title,
                        fontSize = 22.sp,
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.audiowide)),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    // Imagen de la sala
                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(160.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    // Descripción de la sala
                    Text(description, color = Color.White, fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(6.dp))
                    // Texto adicional
                    Text(extra, color = Color.LightGray, fontSize = 13.sp)
                }
            }
        } else {
            // Cara trasera de la tarjeta con tabla de información
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        rotationY = 180f // Volteada completamente
                    },
                colors = CardDefaults.cardColors(containerColor = Color(0xFF2B2942)),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    // Título del dorso
                    Text(
                        "Información",
                        fontSize = 20.sp,
                        color = Color(0xFFE57BC2),
                        fontFamily = FontFamily(Font(R.font.audiowide)),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    // Recorre cada par clave-valor de la tabla
                    tableData.forEach { (label, value) ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(label, color = Color.White)
                            Text(value, color = Color.White)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}

