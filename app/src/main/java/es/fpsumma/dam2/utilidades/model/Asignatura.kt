package es.fpsumma.dam2.utilidades.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "asignaturas")
data class Asignatura (

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "Asignatura")
    val nombre: String,

    @ColumnInfo(name = "Trimestre")
    val trimestre: String,

    @ColumnInfo(name = "Nota")
    val nota: Double,
)