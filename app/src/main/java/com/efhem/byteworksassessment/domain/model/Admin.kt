package com.efhem.byteworksassessment.domain.model


data class Admin(
    val id: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val dob: String,
    val passportPhoto: String?,
    val address: String,
    val country: String,
    val state: String,
)