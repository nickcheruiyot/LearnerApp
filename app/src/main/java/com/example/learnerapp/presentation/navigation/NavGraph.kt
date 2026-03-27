package com.example.learnerapp.presentation.navigation
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.learnerapp.presentation.courses.CoursesScreen
import com.example.learnerapp.presentation.courses.CoursesViewModel
import com.example.learnerapp.presentation.institutions.InstitutionsScreen
import com.example.learnerapp.presentation.institutions.InstitutionsViewModel
import com.example.learnerapp.presentation.levels.CourseLevelsScreen
import com.example.learnerapp.presentation.levels.CourseLevelsViewModel
import com.example.learnerapp.presentation.login.AuthViewModel
import com.example.learnerapp.presentation.login.LoginScreen
import com.example.learnerapp.presentation.login.RegisterScreen
import com.example.learnerapp.presentation.materials.MaterialsScreen
import com.example.learnerapp.presentation.materials.MaterialsViewModel
import com.example.learnerapp.presentation.schools.SchoolsScreen
import com.example.learnerapp.presentation.schools.SchoolsViewModel
import com.example.learnerapp.presentation.units.UnitsScreen
import com.example.learnerapp.presentation.units.UnitsViewModel

@Composable
fun NavGraph(navController: NavHostController) {

    val authVM = AuthViewModel()
    val institutionsVM = InstitutionsViewModel()
    val schoolsVM = SchoolsViewModel()
    val coursesVM = CoursesViewModel()
    val levelsVM = CourseLevelsViewModel()
    val materialsVM = MaterialsViewModel()
    val unitsVM = UnitsViewModel() // ✅ NEW

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {

        // Login
        composable(Screen.Login.route) {
            LoginScreen(navController, authVM)
        }

        // Register
        composable(Screen.Register.route) {
            RegisterScreen(navController, authVM)
        }

        // Institutions
        composable(Screen.Institutions.route) {
            InstitutionsScreen(navController, institutionsVM)
        }

        // Schools
        composable(
            Screen.Schools.route,
            arguments = listOf(navArgument("institution") { type = NavType.StringType })
        ) { backStackEntry ->
            val institution = backStackEntry.arguments?.getString("institution") ?: ""
            SchoolsScreen(navController, institution, schoolsVM)
        }

        // Courses
        composable(
            Screen.Courses.route,
            arguments = listOf(navArgument("school") { type = NavType.StringType })
        ) { backStackEntry ->
            val school = backStackEntry.arguments?.getString("school") ?: ""
            CoursesScreen(navController, school, coursesVM)
        }

        // Levels (Semesters)
        composable(
            Screen.Levels.route,
            arguments = listOf(navArgument("course") { type = NavType.StringType })
        ) { backStackEntry ->
            val course = backStackEntry.arguments?.getString("course") ?: ""
            CourseLevelsScreen(navController, course, levelsVM)
        }

        // ✅ NEW: Units Screen
        composable(
            Screen.Units.route,
            arguments = listOf(
                navArgument("course") { type = NavType.StringType },
                navArgument("level") { type = NavType.StringType }
            )
        ) { backStackEntry ->

            val course = backStackEntry.arguments?.getString("course") ?: ""
            val level = backStackEntry.arguments?.getString("level") ?: ""

            UnitsScreen(
                navController = navController,
                course = course,
                level = level,
                viewModel = unitsVM
            )
        }

        // ✅ UPDATED: Materials now uses UNIT
        composable(
            Screen.Materials.route,
            arguments = listOf(
                navArgument("unit") { type = NavType.StringType }
            )
        ) { backStackEntry ->

            val unit = backStackEntry.arguments?.getString("unit") ?: ""

            MaterialsScreen(
                navController = navController,
                unit = unit,
                viewModel = materialsVM
            )
        }
    }
}