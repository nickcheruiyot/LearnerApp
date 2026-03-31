package com.example.learnerapp.presentation.levels
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CourseLevelsViewModel : ViewModel() {

    private val _levels = MutableStateFlow<List<String>>(emptyList())
    val levels: StateFlow<List<String>> = _levels.asStateFlow()

    fun loadLevels(course: String) {
        _levels.value = getLevelsForCourse(course)
    }

    private fun getLevelsForCourse(course: String): List<String> {
        return listOf(
            "1.1",
            "1.2",
            "2.1",
            "2.2",
            "3.1",
            "3.2",
            "4.1",
            "4.2"
        )
    }
}