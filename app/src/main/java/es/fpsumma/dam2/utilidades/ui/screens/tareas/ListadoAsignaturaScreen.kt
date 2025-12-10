package es.fpsumma.dam2.utilidades.ui.screens.tareas

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import es.fpsumma.dam2.utilidades.model.Asignatura
import es.fpsumma.dam2.utilidades.model.Tarea
import es.fpsumma.dam2.utilidades.ui.viewmodel.AsignaturasViewModel
import es.fpsumma.dam2.utilidades.ui.viewmodel.TareasViewModel


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListadoAsignaturasScreen(navController: NavController, vm: AsignaturasViewModel, modifier: Modifier= Modifier) {

    val asignatura by vm.uiState.collectAsState()

    var nombre by rememberSaveable { mutableStateOf("") }
    var tipoAsignatura by rememberSaveable { mutableStateOf("") }
    var trimestre by rememberSaveable { mutableStateOf("") }
    var nota by rememberSaveable { mutableStateOf(0.0) }

    fun handleAddAsignatura(){
        vm.addAsignatura(nombre,tipoAsignatura,trimestre,nota)
        nombre=""
        tipoAsignatura=""
        trimestre=""
        nota=0.0
    }

    fun handleDeleteAsignatura(asignatura: Asignatura){
        vm.deleteAsignatura(asignatura)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Listado de tareas") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)) {

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = tipoAsignatura,
                onValueChange = { tipoAsignatura = it },
                label = { Text("Asignatura") },
                singleLine = false,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = trimestre,
                onValueChange = { trimestre = it },
                label = { Text("Trimestre") },
                singleLine = false,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = nota.toString(),
                onValueChange = { newValue ->
                    val newDouble = newValue.toDoubleOrNull()
                    if (newDouble != null) {
                        nota = newDouble
                    } else if (newValue.isEmpty()) {
                        nota = 0.0
                    }
                },
                label = { Text("Nota") },
                singleLine = true,
                modifier = androidx.compose.ui.Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
            Button(
                onClick = ::handleAddAsignatura,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Añadir nota") }
            HorizontalDivider(modifier.padding(vertical = 16.dp))
/*
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

                items(
                    items = asignatura,
                    key = { it.id }
                ) { tarea ->
                    Card (
                        modifier = modifier,
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        ListItem(
                            headlineContent = { Text(tarea.titulo) },
                            supportingContent = { Text(tarea.descripcion) },
                            trailingContent = {
                                IconButton(
                                    onClick = {handleDeleteAsignatura(asignatura)},
                                    modifier = modifier.size(48.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.Delete,
                                        contentDescription = "Borrar nota"
                                    )
                                }
                            }
                        )
                    }
                }
            }*/
        }
    }
}