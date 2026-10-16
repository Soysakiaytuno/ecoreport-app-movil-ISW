package com.example.ecoreport.navigation

import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.example.ecoreport.data.ReporteEntity
import com.example.ecoreport.repository.ReporteRepository
import com.example.ecoreport.ui.screens.*
import com.example.ecoreport.ui_comp.screens.PantallaInicio
import com.example.ecoreport.viewmodel.ReporteViewModel

@Composable
fun AppNavigation(
    repository: ReporteRepository
) {

    val navController =
        rememberNavController()

    val viewModel: ReporteViewModel = viewModel(
        factory = ReporteViewModel.Factory(repository)
    )

    var reporteSeleccionado by remember {
        mutableStateOf<
                ReporteEntity?
                >(null)
    }

    NavHost(
        navController =
            navController,

        startDestination =
            "inicio"
    ) {

        composable(
            "inicio"
        ) {

            PantallaInicio (
                reportes =
                    viewModel.reportes,

                irRegistro = {
                    navController
                        .navigate(
                            "registro"
                        )
                },

                irReportes = {
                    navController
                        .navigate(
                            "reportes"
                        )
                },

                irEstadisticas = {
                    navController
                        .navigate(
                            "estadisticas"
                        )
                }
            )
        }
        composable(
            "registro"
        ) {

            PantallaRegistro(
                registrarReporte = { reporte ->

                    viewModel.insertar(reporte)

                    navController
                        .popBackStack()
                },

                volver = {

                    navController
                        .popBackStack()
                }
            )
        }
        composable(
            "reportes"
        ) {

            PantallaReportes(
                reportes =
                    viewModel.reportesConsulta,

                onBuscar = { query ->
                    viewModel.consultar(query)
                },

                seleccionarReporte = { reporte ->

                    reporteSeleccionado =
                        reporte

                    navController.navigate(
                        "detalle"
                    )
                },

                volver = {
                    viewModel.cargarReportes()

                    navController
                        .popBackStack()
                }
            )
        }
        composable(
            "detalle"
        ) {

            PantallaDetalle(
                reporte =
                    reporteSeleccionado,

                actualizarReporte = {
                    reporteActualizado ->
                    viewModel.actualizar(reporteActualizado)
                    reporteSeleccionado = reporteActualizado
                },

                eliminarReporte = { reporte ->

                    viewModel.eliminar(reporte)

                    navController
                        .popBackStack()
                },

                volver = {
                    navController
                        .popBackStack()
                }
            )
        }
        composable(
            "estadisticas"
        ) {

            PantallaEstadisticas(
                reportes =
                    viewModel.reportes,

                volver = {

                    navController
                        .popBackStack()
                }
            )
        }
    }
}