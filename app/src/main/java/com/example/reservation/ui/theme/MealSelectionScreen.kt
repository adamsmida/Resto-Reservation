package com.example.reservation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun MealSelectionScreen(onBackClicked: () -> Unit, onMealButtonClicked: (String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Sélectionnez un repas", fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { onMealButtonClicked("Déjeuner") }) {
            Text("Déjeuner")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { onMealButtonClicked("Dîner") }) {
            Text("Dîner")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onBackClicked) {
            Text("Retour")
        }
    }
}
