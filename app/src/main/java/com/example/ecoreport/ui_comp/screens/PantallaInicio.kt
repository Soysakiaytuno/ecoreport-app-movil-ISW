package com.example.ecoreport.ui_comp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ecoreport.data.ReporteEntity

@Composable
fun PantallaInicio(
    reportes: List<ReporteEntity>,
    cargando: Boolean = false,
    error: String? = null,
    onSincronizar: () -> Unit = {},
    irRegistro: () -> Unit,
    irReportes: () -> Unit,
    irEstadisticas: () -> Unit
) {
    val pendientes = reportes.count { it.estado == "PENDIENTE" }
    val resueltos = reportes.count { it.estado == "RESUELTO" }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Text(
            text = "EcoReport",
            style = MaterialTheme.typography.headlineLarge
        )

        Text(
            text = "Reportes ambientales"
        )

        Spacer(Modifier.height(20.dp))

        Text("Total: ${reportes.size}")
        Text("Pendientes: $pendientes")
        Text("Resueltos: $resueltos")

        Spacer(Modifier.height(16.dp))

        // Indicador de sincronización en progreso
        if (cargando) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    strokeWidth = 2.dp
                )
                Spacer(Modifier.size(8.dp))
                Text(
                    text = "Sincronizando con el servidor...",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        // Mensaje de error si falla la conexión
        if (error != null) {
            Text(
                text = "Error al sincronizar: $error",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }

        Spacer(Modifier.height(12.dp))

        // Botón manual para forzar la sincronización
        OutlinedButton(
            onClick = onSincronizar,
            enabled = !cargando,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (cargando) "SINCRONIZANDO..." else "SINCRONIZAR CON SERVIDOR")
        }

        Spacer(Modifier.height(8.dp))

        Button(
            onClick = irRegistro,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("NUEVO REPORTE")
        }

        Spacer(Modifier.height(8.dp))

        Button(
            onClick = irReportes,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("VER REPORTES")
        }

        Spacer(Modifier.height(8.dp))

        Button(
            onClick = irEstadisticas,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("ESTADÍSTICAS")
        }
    }
}
