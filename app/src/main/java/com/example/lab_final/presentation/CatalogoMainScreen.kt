package com.example.lab_final.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab_final.monedas.Cripto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogoMainScreen(items: List<Cripto>, onItemClick: (Cripto) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Catálogo de Criptomonedas") })
        }
    ) { paddingValues -> // Recibimos el padding de Scaffold aquí
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // Aplicamos el padding a LazyColumn
                .padding(horizontal = 16.dp), // Padding adicional horizontal
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items) { item ->
                CriptoCard(item = item, onClick = { onItemClick(item) })
            }
        }
    }
}


@Composable
fun CriptoCard(item: Cripto, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = item.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = item.symbol,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Text(
                    text = "Precio: $${item.priceUsd}",
                    fontSize = 14.sp
                )
            }

            val changeColor = if (item.changePercent24Hr.toDouble() >= 0) Color.Green else Color.Red
            Text(
                text = "${item.changePercent24Hr}%",
                color = changeColor,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}