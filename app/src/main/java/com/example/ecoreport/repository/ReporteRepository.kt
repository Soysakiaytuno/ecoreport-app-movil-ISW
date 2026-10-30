package com.example.ecoreport.repository

import com.example.ecoreport.data.ReporteDao
import com.example.ecoreport.data.ReporteEntity
import com.example.ecoreport.data.remote.ReporteApiService
import com.example.ecoreport.data.remote.ReporteDto
import com.example.ecoreport.data.remote.toEntity
import kotlinx.coroutines.flow.Flow

class ReporteRepository(
    private val reporteDao: ReporteDao,
    private val api: ReporteApiService
) {
    fun obtenerTodos(): Flow<List<ReporteEntity>> {
        return reporteDao.obtenerTodos()
    }

    fun obtenerPorCategoria(categoria: String?): Flow<List<ReporteEntity>> {
        return reporteDao.obtenerPorCategoria(categoria)
    }

    suspend fun insertar(reporte: ReporteEntity) {
        reporteDao.insertar(reporte)
    }

    suspend fun actualizar(reporte: ReporteEntity) {
        reporteDao.actualizar(reporte)
    }

    suspend fun eliminar(reporte: ReporteEntity) {
        reporteDao.eliminar(reporte)
    }

    // Llamada a las funciones remotas
    suspend fun obtenerReportesRemotos(): List<ReporteDto> {
        return api.obtenerReportes()
    }

    // Sincronización: descarga de la API y guarda en Room
    suspend fun sincronizar() {
        val remotos = api.obtenerReportes()
        reporteDao.insertarTodos(remotos.map { it.toEntity() })
    }
}
