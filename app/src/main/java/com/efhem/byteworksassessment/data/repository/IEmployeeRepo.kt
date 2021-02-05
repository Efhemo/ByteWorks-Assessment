package com.efhem.byteworksassessment.data.repository

import androidx.lifecycle.LiveData
import com.efhem.byteworksassessment.domain.model.Employee

interface IEmployeeRepo {

    suspend fun getEmployee(email: String): Employee?
    fun observeEmployees(): LiveData<List<Employee>>
    suspend fun saveEmployee(employee: Employee)
}
