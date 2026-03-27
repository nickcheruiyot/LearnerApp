package com.example.learnerapp.presentation.units
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun UnitsScreen(
    navController: NavController,
    course: String,
    level: String,
    viewModel: UnitsViewModel
) {

    val units = viewModel.getUnits(course, level)

    Column(Modifier.padding(16.dp)) {

        Text("Units - $level", fontSize = 24.sp)

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {

            items(units) { unit ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .clickable {
                            navController.navigate("materials/$unit")
                        },
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        unit,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(20.dp)
                    )
                }
            }
        }
    }
}