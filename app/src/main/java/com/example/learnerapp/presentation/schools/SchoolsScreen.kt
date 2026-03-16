package com.example.learnerapp.presentation.schools
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
fun SchoolsScreen(
    navController: NavController,
    institution: String,
    viewModel: SchoolsViewModel
) {

    val schools = viewModel.getSchools(institution).collectAsState().value

    Column(Modifier.padding(16.dp)) {

        Text(
            text = "Schools in $institution",
            fontSize = 24.sp
        )

        LazyColumn {

            items(schools) { school ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .clickable {
                            navController.navigate("courses/$school")
                        },
                    shape = RoundedCornerShape(14.dp)
                ) {

                    Text(
                        text = school,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(20.dp)
                    )

                }

            }

        }

    }

}