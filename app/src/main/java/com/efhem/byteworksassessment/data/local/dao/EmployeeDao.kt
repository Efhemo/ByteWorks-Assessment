package com.efhem.byteworksassessment.data.local.dao

import androidx.room.*
import com.efhem.byteworksassessment.data.local.model.AdminLocal
import com.efhem.byteworksassessment.data.local.model.EmployeeLocal

@Dao
interface EmployeeDao {

    @Query("SELECT * FROM employeelocal")
    suspend fun getEmployees(): List<EmployeeLocal>

    @Query("SELECT * FROM employeelocal WHERE id = :id")
    suspend fun getEmployee(id: String): EmployeeLocal?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmployee(employeeLocal: EmployeeLocal)
}
