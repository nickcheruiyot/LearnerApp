package com.example.learnerapp.presentation.materials
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.learnerapp.ui.components.ShimmerItem

@Composable
fun MaterialsScreen(
    navController: NavController,
    unit: String,
    viewModel: MaterialsViewModel
) {

    val state = viewModel.state.collectAsState().value

    LaunchedEffect(unit) {
        viewModel.loadMaterials(unit)
    }

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
            text = "📘 $unit",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Text(
            text = "Study Materials",
            fontSize = 14.sp,
            color = Color.LightGray
        )

        Spacer(modifier = Modifier.height(20.dp))

        when (state) {

            // 🔄 LOADING
            is MaterialsState.Loading -> {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(5) {
                        ShimmerItem()
                    }
                }
            }

            // ✅ SUCCESS
            is MaterialsState.Success -> {
                val materials = state.materials

                LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(materials) { item ->

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController.navigate("material_detail/$item")
                                },
                            shape = RoundedCornerShape(18.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White.copy(alpha = 0.08f)
                            )
                        ) {

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(18.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Column {
                                    Text(
                                        text = item,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = Color.White
                                    )

                                    Text(
                                        text = "Tap to open",
                                        fontSize = 13.sp,
                                        color = Color.LightGray
                                    )
                                }

                                Text("➡️", color = Color.White)
                            }
                        }
                    }
                }
            }

            // 🟡 EMPTY STATE (IMPORTANT FIX)
            is MaterialsState.Empty -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {

                        Text(
                            text = "No Materials Available",
                            fontSize = 18.sp,
                            color = Color.White
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Check back later",
                            fontSize = 13.sp,
                            color = Color.LightGray
                        )
                    }
                }
            }
        }
    }
}