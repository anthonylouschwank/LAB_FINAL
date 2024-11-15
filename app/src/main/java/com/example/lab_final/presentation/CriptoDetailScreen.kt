package com.example.lab_final.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.lab_final.monedas.Cripto
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.material3.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CriptoDetailScreen(item: Cripto, navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("${item.name} - Detalles") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Nombre y Símbolo
            Text(
                text = item.name,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = item.symbol,
                fontSize = 20.sp,
                color = Color.Gray
            )

            // Tarjeta para el Precio
            DetailCard {
                Text(
                    text = "Precio en USD",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "$${item.priceUsd}",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // Tarjeta para Cambio Porcentual
            DetailCard {
                val changeColor = if (item.changePercent24Hr.toDouble() >= 0) Color.Green else Color.Red
                Text(
                    text = "Cambio en 24h",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${item.changePercent24Hr}%",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = changeColor
                )
            }

            // Tarjeta para Supply
            DetailCard {
                Text(
                    text = "Supply",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.supply,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            // Tarjeta para Max Supply
            DetailCard {
                Text(
                    text = "Max Supply",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.maxSupply ?: "N/A",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            // Tarjeta para Market Cap
            DetailCard {
                Text(
                    text = "Market Cap (USD)",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "$${item.marketCapUsd}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            // Última Actualización
            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            val lastUpdated = dateFormat.format(item.lastUpdated?.let { Date(it) })
            Text(
                text = "Última Actualización: $lastUpdated",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Botón para Volver al Catálogo
            Button(
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Volver al Catálogo", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

// Composable reutilizable para las tarjetas de detalles
@Composable
fun DetailCard(content: @Composable ColumnScope.() -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            content = content
        )
    }
}




