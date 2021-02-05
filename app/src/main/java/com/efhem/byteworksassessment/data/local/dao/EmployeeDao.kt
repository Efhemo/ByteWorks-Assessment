package com.efhem.byteworksassessment.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.efhem.byteworksassessment.data.local.model.EmployeeLocal

@Dao
interface EmployeeDao {

    @Query("SELECT * FROM employeelocal")
    fun observeEmployees(): LiveData<List<EmployeeLocal>>

    @Query("SELECT * FROM employeelocal WHERE email = :email")
    suspend fun getEmployee(email: String): EmployeeLocal?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmployee(employeeLocal: EmployeeLocal)
}
