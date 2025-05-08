package com.example.cantameotra.screens

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.example.cantameotra.R

val audiowide = FontFamily(Font(R.font.audiowide_regular))

@Preview
@Composable
fun GifImage(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(context)
                    .data(R.drawable.cantameotra)
                    .size(Size.ORIGINAL)
                    .build(),
                imageLoader = imageLoader
            ),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // Ocupa el espacio restante
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally // Esta es la forma correcta
        ) {
            Text(
                "Cántame Otra: ¡Tu escenario, tu show, tu momento! Reserva y deja que empiece la diversión.",
                fontFamily = audiowide,
                color = Color(0xFFFF96D5),
                modifier = Modifier
                    .offset(y = (-200).dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Juan José Acosta Rodríguez", fontFamily = audiowide, color = Color(0xFFFF96D5))
            Text("María del Carmen Letrán Andrades", fontFamily = audiowide, color = Color(0xFFFF96D5))
        }
    }
}