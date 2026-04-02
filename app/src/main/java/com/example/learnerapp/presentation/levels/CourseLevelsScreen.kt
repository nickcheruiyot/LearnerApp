package com.example.learnerapp.presentation.levels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.learnerapp.ui.components.ShimmerItem

@Composable
fun CourseLevelsScreen(
    navController: NavController,
    course: String,
    viewModel: CourseLevelsViewModel
) {

    //  Load data when screen opens
    LaunchedEffect(course) {
        viewModel.loadLevels(course)
    }

    val state = viewModel.state.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFF141E30), Color(0xFF243B55))
                )
            )
            .padding(16.dp)
    ) {

        Text(
            text = "📘 $course",
            fontSize = 28.sp,
            color = Color.White
        )

        Text(
            text = "Select Semester",
            fontSize = 18.sp,
            color = Color.LightGray
        )

        Spacer(modifier = Modifier.height(20.dp))

        when (state) {

            //  LOADING STATE (SHIMMER)
            is LevelsState.Loading -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(6) {
                        ShimmerItem()
                    }
                }
            }

            // ✅ SUCCESS STATE
            is LevelsState.Success -> {

                val successState = state as LevelsState.Success
                val levels = successState.levels

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    items(levels) { level ->

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController.navigate("units/$course/$level")
                                },
                            shape = RoundedCornerShape(20.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White.copy(alpha = 0.1f)
                            )
                        ) {

                            Row(
                                modifier = Modifier
                                    .padding(20.dp)
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {

                                Column {
                                    Text(
                                        text = "Semester $level",
                                        color = Color.White,
                                        fontSize = 18.sp
                                    )

                                    Text(
                                        text = "Tap to explore units",
                                        color = Color.LightGray,
                                        fontSize = 14.sp
                                    )
                                }

                                Text("➡️", fontSize = 20.sp)
                            }
                        }
                    }
                }
            }

            // 📭 EMPTY STATE (FIXES YOUR ERROR)
            is LevelsState.Empty -> {
                Text(
                    text = "No semesters available",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
    }
}