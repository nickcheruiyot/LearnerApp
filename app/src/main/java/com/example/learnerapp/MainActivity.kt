package com.example.learnerapp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.rememberNavController
import com.example.learnerapp.presentation.navigation.BottomBar
import com.example.learnerapp.presentation.navigation.NavGraph
import com.example.learnerapp.ui.theme.LearnerAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            LearnerAppTheme {

                val navController = rememberNavController()

                Scaffold(

                    bottomBar = {
                        BottomBar(navController)
                    }

                ) { paddingValues ->

                    NavGraph(
                        navController = navController
                    )

                }

            }

        }

    }

}