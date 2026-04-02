package com.example.learnerapp.presentation.materials

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

sealed class MaterialsState {
    object Loading : MaterialsState()
    data class Success(val materials: List<String>) : MaterialsState()
    object Empty : MaterialsState()
}

class MaterialsViewModel : ViewModel() {

    private val _state = MutableStateFlow<MaterialsState>(MaterialsState.Loading)
    val state: StateFlow<MaterialsState> = _state.asStateFlow()

    fun loadMaterials(unit: String) {

        _state.value = MaterialsState.Loading

        val data = listOf(
            "Lecture Notes",
            "CAT 1",
            "Assignment",
            "Past Paper"
        )

        _state.value = if (data.isEmpty()) {
            MaterialsState.Empty
        } else {
            MaterialsState.Success(data)
        }
    }
}