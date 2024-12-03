package com.example.reservation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.ButtonDefaults

@Composable
fun MealTable(mealType: String, onBackClicked: () -> Unit) {
    val meals = listOf(
        Meal("spaghetti", R.drawable.pates_carbonara),
        Meal("yaourt", R.drawable.burger_classique),
        Meal("escalope", R.drawable.pizza_margherita),
        Meal("Salade", R.drawable.salade_cesar)
    )

    var isReserved by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "$mealType du jour",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        meals.forEach { meal ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(meal.name, modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(id = meal.imageRes),
                    contentDescription = meal.name,
                    modifier = Modifier.size(100.dp)
                )
            }
        }

        // Un espace flexible pour pousser le bouton vers le bas
        Spacer(modifier = Modifier.weight(1f))

        // Bouton unique de réservation
        Button(
            onClick = { isReserved = !isReserved },
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isReserved) Color.Green else Color(0xFFFFA500) // Vert si réservé, orange sinon
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp)
        ) {
            Text(if (isReserved) "Réservé" else "Réserver")
        }

        // Bouton retour
        Button(
            onClick = onBackClicked,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp)
        ) {
            Text("Retour")
        }
    }
}

data class Meal(val name: String, val imageRes: Int)
