package com.example.learnerapp.presentation.navigation

sealed class Screen(val route: String) {

    object Login : Screen("login")
    object Register : Screen("register")

    object Institutions : Screen("institutions")

    object Schools : Screen("schools/{institution}")

    object Courses : Screen("courses/{school}")

    object Levels : Screen("levels/{course}")

    object Materials : Screen("materials/{course}/{level}")

}