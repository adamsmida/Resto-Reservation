package com.example.reservation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun DepartementScreen(
    onInscriptionClicked: (String) -> Unit,
    onConsulterClicked: () -> Unit
) {
    var selectedDepartement by remember { mutableStateOf<String?>(null) }
    var inscriptionMessage by remember { mutableStateOf<String?>(null) }

    val departements = listOf(
        Departement("consulter", "Mme Houda Toukabri", 10, 400)
    )

    val currentDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header()
        GreetingText(currentDate)

        Spacer(modifier = Modifier.height(70.dp))

        departements.forEach { departement ->
            DepartementRow(departement) {
                selectedDepartement = it.nom
                if (it.nom == "consulter") {
                    onConsulterClicked()
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        selectedDepartement?.let { depNom ->
            val selectedDep = departements.find { it.nom == depNom }
            selectedDep?.let { dep ->
                DepartementDetails(dep) {
                    inscriptionMessage = "Vous êtes inscrit au département $depNom."
                    onInscriptionClicked(depNom)
                }
            }
        }

        inscriptionMessage?.let {
            Text(
                text = it,
                fontWeight = FontWeight.Bold,
                color = Color.Green,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

@Composable
fun Header() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        Image(
            painter = painterResource(id = R.drawable.isetkl), // Assurez-vous d'avoir cette image dans votre dossier res/drawable
            contentDescription = "Logo ISET Kélibia",
            modifier = Modifier.size(80.dp).padding(8.dp),
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
fun GreetingText(currentDate: String) {
    Text(
        text = "Bienvenue à restaurant d'iset kelibia",
        fontWeight = FontWeight.Bold,
        color = Color.Blue,
        modifier = Modifier.padding(top = 20.dp)
    )
    Text(
        text = "Cette application permet aux étudiants de l'université de réserver facilement leurs repas dans le restaurant universitaire.",
        fontWeight = FontWeight.Bold,
        color = Color(0xFFFFA500),
        modifier = Modifier.padding(top = 35.dp)
    )
    Text(
        text = "Sélectionnez un repas pour afficher ses informations et vous y réserver.",
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(top = 35.dp)
    )
    Text(
        text = "Date : $currentDate",
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        modifier = Modifier.padding(top = 16.dp)
    )
}

@Composable
fun DepartementRow(departement: Departement, onSelect: (Departement) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        Button(
            onClick = { onSelect(departement) },
            colors = ButtonDefaults.buttonColors(Color.Blue)
        ) {
            Text("Consulter")
        }
    }
}

@Composable
fun DepartementDetails(dep: Departement, onInscription: () -> Unit) {
    Button(
        onClick = onInscription,
        modifier = Modifier.padding(top = 20.dp),
        colors = ButtonDefaults.buttonColors(Color.Green)
    ) {
        Text("S'inscrire au département")
    }
}

data class Departement(val nom: String, val responsable: String, val nombrePlaces: Int, val montantInscription: Int)
