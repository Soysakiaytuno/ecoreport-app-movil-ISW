package com.example.ecoreport.data.remote

import com.example.ecoreport.data.ReporteEntity

data class ReporteDto(
    val id: Int,
    val titulo: String,
    val descripcion: String,
    val categoria: String,
    val ubicacion: String,
    val estado: String,
    val fecha: String? = ""
)

fun ReporteDto.toEntity(): ReporteEntity {
    return ReporteEntity(
        id = id,
        titulo = titulo,
        descripcion = descripcion,
        categoria = categoria,
        ubicacion = ubicacion,
        estado = estado,
        fecha = fecha ?: ""
    )
}
