package com.efhem.byteworksassessment.domain.model

data class Employee (
    val id: String,
    val firstName: String,
    val lastName: String,
    val gender: String?,
    val designer: String,
    val dob: String,
    val passportPhoto: String?,
    val address: String,
    val country: String,
    val state: String?,
    val email: String
)