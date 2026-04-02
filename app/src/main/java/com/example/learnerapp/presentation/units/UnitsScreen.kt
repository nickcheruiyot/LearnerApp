package com.example.learnerapp.presentation.units
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.example.learnerapp.ui.components.ShimmerItem

@Composable
fun UnitsScreen(
    navController: NavController,
    course: String,
    level: String,
    viewModel: UnitsViewModel
) {

    LaunchedEffect(course, level) {
        viewModel.loadUnits(course, level)
    }

    val state = viewModel.state.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFF0F2027), Color(0xFF203A43))
                )
            )
            .padding(16.dp)
    ) {

        Text("📚 $course", color = Color.White, fontSize = 26.sp)
        Text("Semester $level", color = Color.LightGray)

        Spacer(modifier = Modifier.height(20.dp))

        when (state) {

            is UnitsState.Loading -> {
                LazyColumn {
                    items(5) { ShimmerItem() }
                }
            }

            is UnitsState.Success -> {

                val units = (state as UnitsState.Success).units

                LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {

                    items(units) { unit ->

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController.navigate("materials/$unit")
                                },
                            shape = RoundedCornerShape(18.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White.copy(alpha = 0.1f)
                            )
                        ) {

                            Row(
                                modifier = Modifier
                                    .padding(18.dp)
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {

                                Column {
                                    Text(unit, color = Color.White, fontSize = 18.sp)
                                    Text("Open materials", color = Color.LightGray)
                                }

                                Text("📂")
                            }
                        }
                    }
                }
            }

            is UnitsState.Empty -> {
                Text("No units available", color = Color.White)
            }
        }
    }
}