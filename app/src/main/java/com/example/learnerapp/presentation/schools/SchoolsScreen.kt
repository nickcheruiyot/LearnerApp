package com.example.learnerapp.presentation.schools
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.learnerapp.ui.components.AppCard
import com.example.learnerapp.ui.theme.PinkGradient

@Composable
fun SchoolsScreen(
    navController: NavController,
    institution: String,
    viewModel: SchoolsViewModel
) {

    LaunchedEffect(institution) {
        viewModel.loadSchools(institution)
    }

    val schools = viewModel.schools.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PinkGradient)
            .padding(16.dp)
    ) {

        Text("🏫 $institution", fontSize = 26.sp, color = Color.White)
        Text("Select School", color = Color.White.copy(0.7f))

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {

            items(schools) { school ->

                AppCard(
                    title = school,
                    subtitle = "View courses",
                    icon = "📘"
                ) {
                    navController.navigate("courses/$school")
                }
            }
        }
    }
}