package com.example.learnerapp.presentation.materials
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MaterialsViewModel : ViewModel() {

    private val materialsData = mapOf(

        "Computer Science_1.1" to listOf(
            "Introduction to Programming Notes",
            "Discrete Mathematics CAT 1",
            "Computer Organization Notes"
        ),

        "Computer Science_1.2" to listOf(
            "Data Structures Notes",
            "Operating Systems Notes",
            "Algorithms Assignment"
        ),

        "Civil Engineering_1.1" to listOf(
            "Engineering Mathematics",
            "Mechanics Notes"
        )
    )

    fun getMaterials(course: String, level: String): StateFlow<List<String>> {

        val key = "${course}_${level}"

        val materials = materialsData[key] ?: listOf(
            "Lecture Notes",
            "Past Papers",
            "Assignments"
        )

        val state = MutableStateFlow(materials)

        return state.asStateFlow()
    }
}