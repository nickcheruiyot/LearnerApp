package com.example.learnerapp.presentation.materials
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MaterialsViewModel : ViewModel() {

    private val materialsData = mapOf(

        "Unit 1" to listOf(
            "Introduction to Programming Notes",
            "Discrete Mathematics CAT 1",
            "Computer Organization Notes"
        ),

        "Unit 2" to listOf(
            "Data Structures Notes",
            "Operating Systems Notes",
            "Algorithms Assignment"
        ),

        "Unit 3" to listOf(
            "Lecture Notes",
            "Past Papers",
            "Assignments"
        )
    )

    fun getMaterials(unit: String): StateFlow<List<String>> {

        val materials = materialsData[unit] ?: listOf(
            "Lecture Notes",
            "Past Papers",
            "Assignments"
        )

        return MutableStateFlow(materials).asStateFlow()
    }
}