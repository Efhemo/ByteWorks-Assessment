package com.efhem.byteworksassessment.domain.model

data class Admin(
    val firstName: String,
    val lastName: String,
    val gender: String,
    val dob: String,
    val passportPhoto: String?,
    val address: String,
    val country: String,
    val state: String,
)

/*
* Firstname
Lastname
Gender
Date of birth Passport photo Address Country
State
* */