package com.example.learnerapp.presentation.units
class UnitsViewModel {

    fun getUnits(course: String, level: String): List<String> {
        return listOf(
            "Unit 1",
            "Unit 2",
            "Unit 3",
            "Unit 4"
        )
    }
}