package com.example.learnerapp.presentation.navigation
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.learnerapp.presentation.courses.CoursesScreen
import com.example.learnerapp.presentation.courses.CoursesViewModel
import com.example.learnerapp.presentation.institutions.InstitutionsScreen
import com.example.learnerapp.presentation.institutions.InstitutionsViewModel
import com.example.learnerapp.presentation.levels.CourseLevelsScreen
import com.example.learnerapp.presentation.levels.CourseLevelsViewModel
import com.example.learnerapp.presentation.materials.MaterialsScreen
import com.example.learnerapp.presentation.materials.MaterialsViewModel
import com.example.learnerapp.presentation.schools.SchoolsScreen
import com.example.learnerapp.presentation.schools.SchoolsViewModel
@Composable
fun NavGraph(navController: NavHostController) {

    // Initialize ViewModels
    val institutionsVM = InstitutionsViewModel()
    val schoolsVM = SchoolsViewModel()
    val coursesVM = CoursesViewModel()
    val levelsVM = CourseLevelsViewModel()
    val materialsVM = MaterialsViewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.Institutions.route
    ) {

        // Institutions
        composable(Screen.Institutions.route) {
            InstitutionsScreen(navController = navController, viewModel = institutionsVM)
        }

        // Schools
        composable(
            route = Screen.Schools.route,
            arguments = listOf(navArgument("institution") { type = NavType.StringType })
        ) { backStackEntry ->
            val institution = backStackEntry.arguments?.getString("institution") ?: ""
            SchoolsScreen(navController = navController, institution = institution, viewModel = schoolsVM)
        }

        // Courses
        composable(
            route = Screen.Courses.route,
            arguments = listOf(navArgument("school") { type = NavType.StringType })
        ) { backStackEntry ->
            val school = backStackEntry.arguments?.getString("school") ?: ""
            CoursesScreen(navController = navController, school = school, viewModel = coursesVM)
        }

        // Levels (Years/Semesters)
        composable(
            route = Screen.Levels.route,
            arguments = listOf(navArgument("course") { type = NavType.StringType })
        ) { backStackEntry ->
            val course = backStackEntry.arguments?.getString("course") ?: ""
            CourseLevelsScreen(navController = navController, course = course, viewModel = levelsVM)
        }

        // Materials
        composable(
            route = Screen.Materials.route,
            arguments = listOf(
                navArgument("course") { type = NavType.StringType },
                navArgument("level") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val course = backStackEntry.arguments?.getString("course") ?: ""
            val level = backStackEntry.arguments?.getString("level") ?: ""
            MaterialsScreen(navController = navController, course = course, level = level, viewModel = materialsVM)
        }
    }
}