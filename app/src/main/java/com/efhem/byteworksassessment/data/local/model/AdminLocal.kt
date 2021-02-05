package com.efhem.byteworksassessment.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity
data class AdminLocal(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val firstName: String,
    val lastName: String,
    val gender: String,
    val dob: String,
    val passportPhoto: String?,
    val address: String,
    val country: String,
    val state: String,
)