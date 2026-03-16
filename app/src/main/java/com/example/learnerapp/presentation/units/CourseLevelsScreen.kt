package com.example.learnerapp.presentation.levels
import androidx.compose.foundation.clickable
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
fun CourseLevelsScreen(
    navController: NavController,
    course: String,
    viewModel: CourseLevelsViewModel
) {

    val levels = viewModel.levels.collectAsState().value

    Column(Modifier.padding(16.dp)) {

        Text(
            text = "Choose Semester",
            fontSize = 24.sp
        )

        LazyColumn {

            items(levels) { level ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .clickable {
                            navController.navigate("materials/$course/$level")
                        },
                    shape = RoundedCornerShape(14.dp)
                ) {

                    Text(
                        text = level,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(20.dp)
                    )

                }

            }

        }

    }
}