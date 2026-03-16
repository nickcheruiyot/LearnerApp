package com.example.learnerapp.presentation.courses

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CoursesViewModel : ViewModel() {

    private val schoolCourses = mapOf(

        "School of Engineering" to listOf(
            "Civil Engineering",
            "Electrical Engineering",
            "Mechanical Engineering"
        ),

        "School of Computing" to listOf(
            "Computer Science",
            "Information Technology",
            "Software Engineering"
        ),

        "School of Education" to listOf(
            "Mathematics Education",
            "Physics Education",
            "English Education"
        )
    )

    fun getCourses(school: String): StateFlow<List<String>> {
        val courses = schoolCourses[school] ?: emptyList()
        val state = MutableStateFlow(courses)
        return state.asStateFlow()
    }
}