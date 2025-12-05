package es.fpsumma.dam2.utilidades.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import es.fpsumma.dam2.utilidades.data.local.AppDatabase
import es.fpsumma.dam2.utilidades.model.Asignatura
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AsignaturasViewModel (application: Application) : AndroidViewModel(application){

    private val database = AppDatabase.getDatabase(application)

    private val dao = database.asignaturaDao()

    val uiState: StateFlow<List<Asignatura>> = dao.getAllAsignaturas()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    fun addAsignatura(nombre: String, trimestre: String, nota: Double) {
        viewModelScope.launch {
            val nueva = Asignatura(nombre = nombre, trimestre = trimestre, nota = nota)
            dao.insert(nueva)
        }
    }

    fun deleteAsignatura(asignatura: Asignatura) {
        viewModelScope.launch {
            dao.delete(asignatura)
        }
    }

}