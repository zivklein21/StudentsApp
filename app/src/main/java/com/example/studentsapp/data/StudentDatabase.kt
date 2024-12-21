package com.example.studentsapp.data

import com.example.studentsapp.models.Student

object StudentDatabase {
    val students = mutableListOf(
        Student("1", "John Doe", "1234567890", "123 Main St"),
        Student("2", "Jane Smith", "9876543210", "456 Oak Ave")
    )
}
