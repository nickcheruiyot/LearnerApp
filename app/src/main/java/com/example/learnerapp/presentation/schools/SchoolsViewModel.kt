package com.example.learnerapp.presentation.schools
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SchoolsViewModel : ViewModel() {

    private val schoolsData = mapOf(

        "University of Nairobi" to listOf(
            "School of Engineering",
            "School of Education",
            "School of Business"
        ),

        "Kisii University" to listOf(
            "School of Computing",
            "School of Education",
            "School of Agriculture"
        ),

        "JKUAT" to listOf(
            "School of Computing",
            "School of Engineering"
        )
    )

    fun getSchools(institution: String): StateFlow<List<String>> {
        val schools = schoolsData[institution] ?: emptyList()
        val state = MutableStateFlow(schools)
        return state.asStateFlow()
    }
}