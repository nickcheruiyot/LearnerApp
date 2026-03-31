package com.example.learnerapp.presentation.materials
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MaterialsViewModel : ViewModel() {

    private val materialsData = mapOf(

        "Computer Science Unit 1" to listOf(
            "Programming Notes",
            "CAT 1",
            "Assignment"
        ),

        "Computer Science Unit 2" to listOf(
            "Data Structures Notes",
            "Past Papers"
        )
    )

    private val _materials = MutableStateFlow<List<String>>(emptyList())
    val materials: StateFlow<List<String>> = _materials.asStateFlow()

    fun loadMaterials(unit: String) {
        _materials.value = materialsData[unit] ?: listOf(
            "Lecture Notes",
            "Past Papers",
            "Assignments"
        )
    }
}