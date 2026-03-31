package com.example.learnerapp.presentation.units
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UnitsViewModel : ViewModel() {

    private val _units = MutableStateFlow<List<String>>(emptyList())
    val units: StateFlow<List<String>> = _units.asStateFlow()

    fun loadUnits(course: String, level: String) {

        _units.value = listOf(
            "$course Unit 1",
            "$course Unit 2",
            "$course Unit 3"
        )
    }
}