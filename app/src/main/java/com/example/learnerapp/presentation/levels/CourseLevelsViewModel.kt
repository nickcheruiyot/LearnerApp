package com.example.learnerapp.presentation.levels
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CourseLevelsViewModel : ViewModel() {

    private val levelsList = listOf(
        "1.1",
        "1.2",
        "2.1",
        "2.2",
        "3.1",
        "3.2",
        "4.1",
        "4.2"
    )

    private val _levels = MutableStateFlow(levelsList)
    val levels: StateFlow<List<String>> = _levels.asStateFlow()
}