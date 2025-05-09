package com.example.cantameotra.screens

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cantameotra.R

import com.example.viewmodelform.models.ViewModel_class
import com.example.cantameotra.ui.theme.Fondo // Asegúrate de importar el color "Fondo"
import com.example.cantameotra.ui.theme.Rosa

@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun MapasScreen() {
    Formulario(ViewModel_class()) // Aquí es donde se muestra el nuevo diseño
}

@Composable
fun Formulario(viewModel: ViewModel_class) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    // Estados locales para los dropdowns y horarios
    var salaSeleccionada by remember { mutableStateOf("Selecciona tu sala") }
    var calendarioSeleccionado by remember { mutableStateOf("Selecciona fecha") }
    val horarios = listOf(
        "18:00-19:00", "19:15-20:15", "20:30-21:30",
        "21:45-22:45", "23:00-00:00", "00:15-01:15",
        "01:30-02:30", "02:45-03:45"
    )
    val horariosSeleccionados = remember { mutableStateMapOf<String, Boolean>() }

    val scrollState = rememberScrollState()

    val audiowideFont = FontFamily(Font(R.font.audiowide))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Fondo)
            .verticalScroll(scrollState)
            .padding(16.dp)
            .navigationBarsPadding(), // Evita que los elementos choquen con la barra de navegacion de abajo del movil
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        //Spacer(modifier = Modifier.height(64.dp))

        // Título
        Text(
            text = "AÑADE UNA RESERVA",
            color = Rosa,
            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
            fontFamily = audiowideFont,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .align(Alignment.CenterHorizontally),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
            maxLines = 2
        )

        Spacer(modifier = Modifier.height(32.dp))
        Text("SELECCIONA TU SALA",
            color = Color.White,
            fontFamily = audiowideFont,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = MaterialTheme.typography.titleLarge.fontSize)

        ExposedDropdownMenuBoxSample(
            opciones = listOf("Sala 1", "Sala 2", "Sala 3"),
            seleccion = salaSeleccionada,
            onSeleccionChange = { salaSeleccionada = it },
            fontFamily = audiowideFont
        )

        Spacer(modifier = Modifier.height(32.dp))
        Text("DATOS PERSONALES",
            color = Color.White,
            fontFamily = audiowideFont,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = MaterialTheme.typography.titleLarge.fontSize)

        CustomInput("Escribe tu nombre", uiState.nombre, viewModel::onNombreChange, audiowideFont)
        CustomInput("Escribe tu teléfono", uiState.apellido1, viewModel::onApellido1Change, audiowideFont)
        CustomInput("Escribe tu email", uiState.email, viewModel::onEmailChange, audiowideFont)
        CustomInput("Introduce nº de personas", uiState.apellido2, viewModel::onApellido2Change, audiowideFont)
        CustomInput("Escribe tu carnet de socio", uiState.dni, viewModel::onDniChange, audiowideFont)

        Spacer(modifier = Modifier.height(32.dp))
        Text("CALENDARIO",
            color = Color.White,
            fontFamily = audiowideFont,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = MaterialTheme.typography.titleLarge.fontSize)

        ExposedDropdownMenuBoxSample(
            opciones = listOf("10/05/2025", "11/05/2025", "12/05/2025"),
            seleccion = calendarioSeleccionado,
            onSeleccionChange = { calendarioSeleccionado = it },
            fontFamily = audiowideFont
        )

        Spacer(modifier = Modifier.height(32.dp))
        Text("HORA",
            color = Color.White,
            fontFamily = audiowideFont,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = MaterialTheme.typography.titleLarge.fontSize)

        horarios.forEach { hora ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = horariosSeleccionados[hora] ?: false,
                    onCheckedChange = { horariosSeleccionados[hora] = it }
                )
                Text(text = hora, color = Color.White, fontFamily = audiowideFont)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
        Text("COMENTARIOS",
            color = Color.White,
            fontFamily = audiowideFont,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = MaterialTheme.typography.titleLarge.fontSize)

        OutlinedTextField(
            value = uiState.comentarios,
            onValueChange = viewModel::onComentariosChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            placeholder = { Text("Añade alguna sugerencia", fontFamily = audiowideFont, color = Color.Black) },
            textStyle = TextStyle(fontFamily = audiowideFont, color = Color.Black),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Rosa,
                unfocusedTextColor = Color.Black,
                cursorColor = Rosa,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedPlaceholderColor = Rosa,
                unfocusedPlaceholderColor = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = { viewModel.validarYEnviar() },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF3366FF)
            )
        ) {
            Text("ACEPTAR", color = Color.White, fontFamily = audiowideFont)
        }
    }

   // Spacer(modifier = Modifier.height(64.dp)) // ← AÑADE ESTO

        // Envío de email
        if (uiState.envioExitoso) {
            LaunchedEffect(Unit) {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "message/rfc822"
                    putExtra(Intent.EXTRA_EMAIL, arrayOf("diazcanoignacio@gmail.com"))
                    putExtra(Intent.EXTRA_SUBJECT, "Formulario de reserva")
                    putExtra(
                        Intent.EXTRA_TEXT,
                        """
                        Nombre: ${uiState.nombre}
                        Email: ${uiState.email}
                        Sala: $salaSeleccionada
                        Fecha: $calendarioSeleccionado
                        Horarios: ${horariosSeleccionados.filterValues { it }.keys.joinToString()}
                        Comentarios: ${uiState.errorMensaje}
                        """.trimIndent()
                    )
                }
                context.startActivity(Intent.createChooser(intent, "Enviar email con..."))
                // Reseteamos estado para evitar múltiples lanzamientos
                viewModel.onEmailChange("") // ejemplo para reset
            }
        }
    }


@Composable
fun CustomInput(label: String, value: String, onChange: (String) -> Unit, fontFamily: FontFamily) {
    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        placeholder = { Text(label, fontFamily = fontFamily, color = Color.Black) },
        modifier = Modifier.fillMaxWidth(),
        textStyle = TextStyle(color = Color.Black, fontFamily = fontFamily),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Rosa,
            unfocusedTextColor = Color.Black,
            cursorColor = Rosa,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedPlaceholderColor = Rosa,
            unfocusedPlaceholderColor = Color.Black
        )
    )
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExposedDropdownMenuBoxSample(
    opciones: List<String>,
    seleccion: String,
    onSeleccionChange: (String) -> Unit,
    fontFamily: FontFamily
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = seleccion,
            onValueChange = {},
            readOnly = true,
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            textStyle = TextStyle(fontFamily = fontFamily, color = Color.Black),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Rosa,
                unfocusedTextColor = Color.Black,
                cursorColor = Rosa,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedPlaceholderColor = Rosa,
                unfocusedPlaceholderColor = Color.Black
            )
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            opciones.forEach { opcion ->
                DropdownMenuItem(
                    text = { Text(opcion, fontFamily = fontFamily, color = Rosa) },
                    onClick = {
                        onSeleccionChange(opcion)
                        expanded = false
                    }
                )
            }
        }
    }
}


