package com.example.learnerapp.presentation.units

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.learnerapp.presentation.units.UnitsViewModel

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

    val units = viewModel.units.collectAsState().value

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