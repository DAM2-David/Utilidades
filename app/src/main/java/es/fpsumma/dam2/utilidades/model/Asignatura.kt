package es.fpsumma.dam2.utilidades.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "asignaturas")
data class Asignatura (

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "Asignatura")
    val Asignatura: String,

    @ColumnInfo(name = "Trimestre")
    val Trimestre: String,

    @ColumnInfo(name = "Nota")
    val Nota: String,
)