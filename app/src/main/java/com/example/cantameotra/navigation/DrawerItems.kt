package com.example.cantameotra.navigation

import androidx.compose.material3.DrawerState
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cantameotra.R


@Composable
fun DrawerItems(
    label: String,
    selected: Boolean,
    iconResId: Int, // Acepta un ID de recurso en lugar de ImageVector
    contentDescription: String,
    route: String,
    navigationController: NavController,
    coroutineScope: CoroutineScope,
    drawerState: DrawerState
) {
    NavigationDrawerItem(
        //label = { Text(text = label) },
        label = {
            Text(
                text = label,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.audiowide)),  // Aplica la fuente Audiowide
                    fontSize = 20.sp,  // Tamaño de la fuente
                    color = Color.White  // Color blanco
                ),
                modifier = Modifier.padding(start = 16.dp, end = 16.dp)  // Padding alrededor del texto
            )
        },
        selected = selected,

        icon = {
            Image(
                painter = painterResource(id = iconResId),
                contentDescription = contentDescription
            )
        },
        modifier = Modifier
            .padding(vertical = 8.dp)  // Padding general para todo el elemento
            .background(
                color = if (selected) colorResource(id = R.color.rosapulsar) else colorResource(id = R.color.rosa), // Fondo para el item seleccionado
                //shape = RoundedCornerShape(8.dp)  // Bordes redondeados
            )
            //.border(1.dp, colorResource(id = R.color.black), RoundedCornerShape(8.dp)) // Borde blanco con bordes redondeados
            .border(1.dp, colorResource(id = R.color.black)) // Borde blanco con bordes redondeados

            .padding(horizontal = 12.dp, vertical = 8.dp)  // Padding dentro del borde
        ,

        onClick = {
            coroutineScope.launch {
                drawerState.close()
            }
            navigationController.navigate(route) {
                popUpTo(navigationController.graph.startDestinationId) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    )
}
