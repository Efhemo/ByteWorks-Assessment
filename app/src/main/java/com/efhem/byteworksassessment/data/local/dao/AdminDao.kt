package com.efhem.byteworksassessment.data.local.dao

import androidx.room.*
import com.efhem.byteworksassessment.data.local.model.AdminLocal

@Dao
interface AdminDao {

    @Query("SELECT * FROM adminlocal WHERE email = :email")
    suspend fun getAdmin(email: String): AdminLocal

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAdmin(form: AdminLocal)
}
