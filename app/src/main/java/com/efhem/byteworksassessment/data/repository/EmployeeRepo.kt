package com.efhem.byteworksassessment.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.efhem.byteworksassessment.data.local.ByteWorksDatabase
import com.efhem.byteworksassessment.data.local.model.EmployeeLocal
import com.efhem.byteworksassessment.domain.model.Employee
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EmployeeRepo(
    val database: ByteWorksDatabase,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) : IEmployeeRepo {

    private val employeeDao = database.daoEmployee()

    override suspend fun getEmployee(email: String): Employee? = withContext(ioDispatcher){
        employeeDao.getEmployee(email)?.toEmployee()
    }

    override fun observeEmployees(): LiveData<List<Employee>> =
        Transformations.map(employeeDao.observeEmployees()){
            it.map { it.toEmployee() }
        }

    override suspend fun saveEmployee(employee: Employee) {
       withContext(ioDispatcher){
           employeeDao.insertEmployee(EmployeeLocal.fromEmployee(employee))
       }
    }


}