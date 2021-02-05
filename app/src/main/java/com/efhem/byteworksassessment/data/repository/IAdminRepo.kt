package com.efhem.byteworksassessment.data.repository

import com.efhem.byteworksassessment.domain.model.Admin

interface IAdminRepo {

    suspend fun getAdmin(email: String): Admin?
    suspend fun saveAdmin(admin: Admin)
}
