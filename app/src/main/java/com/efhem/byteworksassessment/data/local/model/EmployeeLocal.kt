package com.efhem.byteworksassessment.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.efhem.byteworksassessment.domain.model.Admin
import com.efhem.byteworksassessment.domain.model.Employee
import java.util.*


@Entity
data class EmployeeLocal(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
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
){

    fun toEmployee(): Employee {
        return Employee(id, firstName, lastName, gender, designer, dob, passportPhoto, address, country, state, email)
    }

    companion object {
        fun fromEmployee(employee: Employee): EmployeeLocal {
            return EmployeeLocal(
                id = employee.id,
                firstName = employee.firstName,
                lastName = employee.lastName,
                designer = employee.designer,
                gender = employee.gender,
                dob = employee.dob,
                passportPhoto = employee.passportPhoto,
                address = employee.address,
                country = employee.country,
                state = employee.state,
                email = employee.email)
        }
    }


}