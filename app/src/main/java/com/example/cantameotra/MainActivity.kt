package com.example.cantameotra

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.cantameotra.navigation.DrawerItems
import com.example.cantameotra.navigation.ItemsProvider
import com.example.cantameotra.navigation.NavigationHost
import com.example.cantameotra.ui.theme.CantameOtraTheme
import com.example.cantameotra.ui.theme.Morado
import com.example.cantameotra.ui.theme.RosaFondoMenu
import com.example.cantameotra.ui.theme.White
import kotlinx.coroutines.launch
import androidx.compose.ui.Alignment
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.cantameotra.navigation.AnimatedHeaderContent


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CantameOtraTheme {
                NavDrawer()
            }
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavDrawer() {
    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    // Variable para almacenar la ruta actual
    val currentBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry.value?.destination?.route ?: "Inicio"

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            ModalDrawerSheet(drawerContainerColor = RosaFondoMenu) {

                // ----------- HEADER DINÁMICO -----------
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .background(Morado),
                    contentAlignment = Alignment.Center
                ) {
                    AnimatedHeaderContent(
                        screen = currentRoute,
                        isDrawerOpen = drawerState.isOpen // Pasa el estado del drawer
                    )
                }

                // ----------- MENÚ -----------
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(RosaFondoMenu)
                        .padding(top = 8.dp)
                ) {
                    ItemsProvider.itemsArray.forEach { item ->
                        DrawerItems(
                            label = item.label,
                            iconResId = item.icon,
                            selected = currentRoute == item.route,
                            onClick = {
                                coroutineScope.launch {
                                    drawerState.close()
                                    if (currentRoute != item.route) {
                                        navController.navigate(item.route)
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    ) {
        val isDrawerOpen = drawerState.isOpen
        Scaffold(
            topBar = {
                val currentBackStackEntry by navController.currentBackStackEntryAsState()
                val currentScreen = currentBackStackEntry?.destination?.route ?: ""

                TopAppBar(
                    title = {
                        val isDrawerOpen = drawerState.isOpen
                        AnimatedHeaderContent(
                            screen = currentScreen,
                            isDrawerOpen = isDrawerOpen
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = { coroutineScope.launch { drawerState.open() } }
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.icono_menu),
                                contentDescription = "Menú"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Morado)
                )
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                NavigationHost(navController = navController)
            }
        }
    }
}