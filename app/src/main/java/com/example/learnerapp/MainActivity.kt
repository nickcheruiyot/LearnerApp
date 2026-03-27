package com.example.learnerapp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.learnerapp.presentation.navigation.BottomBar
import com.example.learnerapp.presentation.navigation.NavGraph
import com.example.learnerapp.ui.theme.LearnerAppTheme
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Firebase
        FirebaseApp.initializeApp(this)
        enableEdgeToEdge()

        setContent {
            LearnerAppTheme {

                val navController = rememberNavController()

                // Detect current route
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                // Screens without bottom bar
                val hideBottomBarScreens = listOf(
                    "login",
                    "register"
                )

                Scaffold(
                    bottomBar = {
                        if (currentRoute !in hideBottomBarScreens) {
                            BottomBar(navController)
                        }
                    }
                ) { innerPadding ->
                    NavGraph(navController = navController)
                }
            }
        }
    }
}