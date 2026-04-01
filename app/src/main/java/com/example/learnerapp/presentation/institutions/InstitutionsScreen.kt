package com.example.learnerapp.presentation.institutions
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.learnerapp.ui.components.AppCard
import com.example.learnerapp.ui.theme.DarkGradient

@Composable
fun InstitutionsScreen(
    navController: NavController,
    viewModel: InstitutionsViewModel
) {

    val institutions = viewModel.institutions.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGradient)
            .padding(16.dp)
    ) {

        Text("🎓 LearnerApp", fontSize = 30.sp, color = Color.White)
        Text("Choose your institution", color = Color.LightGray)

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {

            items(institutions) { institution ->

                AppCard(
                    title = institution,
                    subtitle = "Explore schools",
                    icon = "🏫"
                ) {
                    navController.navigate("schools/$institution")
                }
            }
        }
    }
}