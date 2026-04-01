package com.example.learnerapp.presentation.courses
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
fun CoursesScreen(
    navController: NavController,
    school: String,
    viewModel: CoursesViewModel
) {

    val courses = viewModel.getCourses(school).collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGradient)
            .padding(16.dp)
    ) {

        Text("📘 $school", fontSize = 26.sp, color = Color.White)
        Text("Choose Course", color = Color.LightGray)

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {

            items(courses) { course ->

                AppCard(
                    title = course,
                    subtitle = "View semesters",
                    icon = "📚"
                ) {
                    navController.navigate("levels/$course")
                }
            }
        }
    }
}