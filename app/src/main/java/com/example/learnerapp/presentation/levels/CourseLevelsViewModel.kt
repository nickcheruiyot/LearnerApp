package com.example.learnerapp.presentation.levels
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

//  UI State
sealed class LevelsState {
    object Loading : LevelsState()
    data class Success(val levels: List<String>) : LevelsState()
    object Empty : LevelsState()
}

class CourseLevelsViewModel : ViewModel() {

    private val _state = MutableStateFlow<LevelsState>(LevelsState.Loading)
    val state: StateFlow<LevelsState> = _state.asStateFlow()

    fun loadLevels(course: String) {

        // 🔄 Show loading first
        _state.value = LevelsState.Loading

        val data = getLevelsForCourse(course)

        //  Handle result
        _state.value = if (data.isEmpty()) {
            LevelsState.Empty
        } else {
            LevelsState.Success(data)
        }
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