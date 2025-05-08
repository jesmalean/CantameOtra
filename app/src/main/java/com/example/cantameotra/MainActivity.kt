package com.example.cantameotra

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.cantameotra.navigation.DrawerItems
import com.example.cantameotra.navigation.NavigationHost
import com.example.cantameotra.ui.theme.NavigationDrawerTheme
import kotlinx.coroutines.launch
import com.example.cantameotra.navigation.ItemsProvider
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.colorResource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationDrawerTheme {
                NavDrawer()
            }
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavDrawer() {
    val navigationController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            ModalDrawerSheet {

                Box( modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(color = colorResource(id = R.color.morado))) {
                    //Text(text = "Header content goes here")
                    Image(
                        painter = painterResource(id = R.drawable.logo), // Usamos el PNG
                        contentDescription = "Logo"
                    )

                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth().fillMaxHeight()
                        .background(color = colorResource(id = R.color.rosaFondoMenu)) // Color rosa para los elementos
                        .padding(vertical = 10.dp)
                ) {
                    // Itera sobre los elementos del Drawer
                    for (item in ItemsProvider.itemsArray) {
                        DrawerItems(
                            label = item.label,
                            selected = item.selected,
                            iconResId = item.icon, // Pasamos el ID del recurso PNG
                            contentDescription = item.contentDescription,
                            route = item.route,
                            navigationController = navigationController,
                            coroutineScope = coroutineScope,
                            drawerState = drawerState
                        )
                    }
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "TopBar")
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                coroutineScope.launch {
                                    drawerState.open()
                                }
                            }
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.icono_menu), // Usamos el PNG
                                contentDescription = "Menu"
                            )
                        }
                    }
                )
            }
        ) {
            NavigationHost(navController = navigationController)
        }
    }
}

