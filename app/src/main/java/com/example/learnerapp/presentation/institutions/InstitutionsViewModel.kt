package com.example.learnerapp.presentation.institutions
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class InstitutionsViewModel : ViewModel() {

    private val institutionsList = listOf(
        "University of Nairobi",
        "Kisii University",
        "JKUAT",
        "Moi University",
        "Kapsabet TVET"
    )

    private val _institutions = MutableStateFlow(institutionsList)
    val institutions: StateFlow<List<String>> = _institutions.asStateFlow()
}