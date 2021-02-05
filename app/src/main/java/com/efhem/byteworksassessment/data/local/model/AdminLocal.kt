package com.efhem.byteworksassessment.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.efhem.byteworksassessment.domain.model.Admin
import java.util.*


@Entity
data class AdminLocal(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val firstName: String,
    val lastName: String,
    val gender: String?,
    val dob: String,
    val passportPhoto: String?,
    val address: String,
    val country: String,
    val state: String?,
    val email: String,
    val password: String
){

    fun toAdmin(): Admin {
        return Admin(firstName, lastName, gender, dob, passportPhoto, address, country, state, email, password)
    }

    companion object {
        fun fromAdmin(admin: Admin): AdminLocal {
            return AdminLocal(
                firstName = admin.firstName,
                lastName = admin.lastName,
                gender = admin.gender,
                dob = admin.dob,
                passportPhoto = admin.passportPhoto,
                address = admin.address,
                country = admin.country,
                state = admin.state,
                email = admin.email,
                password = admin.password)
        }
    }


}