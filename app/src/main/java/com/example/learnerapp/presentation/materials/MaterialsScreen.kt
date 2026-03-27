package com.example.learnerapp.presentation.materials
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MaterialsScreen(
    navController: NavController,
    unit: String,
    viewModel: MaterialsViewModel
) {

    val materials = viewModel.getMaterials(unit).collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Materials for $unit",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(materials) { material ->

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {

                    Box(
                        modifier = Modifier.padding(20.dp)
                    ) {

                        Text(
                            text = material,
                            fontSize = 18.sp
                        )

                    }

                }

            }

        }

    }
}