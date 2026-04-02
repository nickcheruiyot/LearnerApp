package com.example.learnerapp.presentation.units
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

sealed class UnitsState {
    object Loading : UnitsState()
    data class Success(val units: List<String>) : UnitsState()
    object Empty : UnitsState()
}

class UnitsViewModel : ViewModel() {

    private val _state = MutableStateFlow<UnitsState>(UnitsState.Loading)
    val state: StateFlow<UnitsState> = _state.asStateFlow()

    fun loadUnits(course: String, level: String) {

        _state.value = UnitsState.Loading

        val data = listOf(
            "$course Unit 1",
            "$course Unit 2",
            "$course Unit 3"
        )

        _state.value = if (data.isEmpty()) {
            UnitsState.Empty
        } else {
            UnitsState.Success(data)
        }
    }
}