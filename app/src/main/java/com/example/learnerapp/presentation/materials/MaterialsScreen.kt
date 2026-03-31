package com.example.learnerapp.presentation.materials
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Download
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun MaterialsScreen(
    navController: NavController,
    unit: String,
    viewModel: MaterialsViewModel
) {

    // 🔥 Load materials once
    LaunchedEffect(unit) {
        viewModel.loadMaterials(unit)
    }

    // ✅ Collect state properly
    val materials = viewModel.materials.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFF0F2027), Color(0xFF203A43), Color(0xFF2C5364))
                )
            )
            .padding(16.dp)
    ) {

        Text(
            text = "📚 $unit Materials",
            fontSize = 26.sp,
            color = Color.White,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(materials) { material ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            // future: open file
                        },
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White.copy(alpha = 0.1f)
                    )
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            imageVector = Icons.Default.Description,
                            contentDescription = null,
                            tint = Color.White
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Text(
                            text = material,
                            fontSize = 16.sp,
                            color = Color.White,
                            modifier = Modifier.weight(1f)
                        )

                        IconButton(onClick = {
                            // future: download
                        }) {
                            Icon(
                                imageVector = Icons.Default.Download,
                                contentDescription = "Download",
                                tint = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}