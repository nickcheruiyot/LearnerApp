package com.example.learnerapp.presentation.navigation
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
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

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {

        composable(Screen.Login.route) {
            val authVM: AuthViewModel = viewModel()
            LoginScreen(navController, authVM)
        }

        composable(Screen.Register.route) {
            val authVM: AuthViewModel = viewModel()
            RegisterScreen(navController, authVM)
        }

        composable(Screen.Institutions.route) {
            val vm: InstitutionsViewModel = viewModel()
            InstitutionsScreen(navController, vm)
        }

        composable(
            Screen.Schools.route,
            arguments = listOf(navArgument("institution") { type = NavType.StringType })
        ) {
            val vm: SchoolsViewModel = viewModel()
            val institution = it.arguments?.getString("institution") ?: ""
            SchoolsScreen(navController, institution, vm)
        }

        composable(
            Screen.Courses.route,
            arguments = listOf(navArgument("school") { type = NavType.StringType })
        ) {
            val vm: CoursesViewModel = viewModel()
            val school = it.arguments?.getString("school") ?: ""
            CoursesScreen(navController, school, vm)
        }

        composable(
            Screen.Levels.route,
            arguments = listOf(navArgument("course") { type = NavType.StringType })
        ) {
            val vm: CourseLevelsViewModel = viewModel()
            val course = it.arguments?.getString("course") ?: ""
            CourseLevelsScreen(navController, course, vm)
        }

        composable(
            Screen.Units.route,
            arguments = listOf(
                navArgument("course") { type = NavType.StringType },
                navArgument("level") { type = NavType.StringType }
            )
        ) {
            val vm: UnitsViewModel = viewModel()
            val course = it.arguments?.getString("course") ?: ""
            val level = it.arguments?.getString("level") ?: ""

            UnitsScreen(
                navController,
                course,
                level,
                vm
            )
        }

        composable(
            Screen.Materials.route,
            arguments = listOf(navArgument("unit") { type = NavType.StringType })
        ) {
            val vm: MaterialsViewModel = viewModel()
            val unit = it.arguments?.getString("unit") ?: ""

            MaterialsScreen(navController, unit, vm)
        }
    }
}