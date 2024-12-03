package com.example.reservation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var showMealSelection by remember { mutableStateOf(false) }
            var showMealTable by remember { mutableStateOf(false) }
            var selectedMealType by remember { mutableStateOf("") }

            MaterialTheme {
                Surface {
                    if (showMealSelection) {
                        MealSelectionScreen(
                            onBackClicked = { showMealSelection = false },
                            onMealButtonClicked = { mealType ->
                                selectedMealType = mealType
                                showMealTable = true
                                showMealSelection = false
                            }
                        )
                    } else if (showMealTable) {
                        MealTable(
                            mealType = selectedMealType,
                            onBackClicked = {
                                showMealTable = false
                                showMealSelection = true
                            }
                        )
                    } else {
                        DepartementScreen(
                            onInscriptionClicked = { depName ->
                                println("Inscription au département: $depName")
                            },
                            onConsulterClicked = {
                                showMealSelection = true
                            }
                        )
                    }
                }
            }
        }
    }
}
