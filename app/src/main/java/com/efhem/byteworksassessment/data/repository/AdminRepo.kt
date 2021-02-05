package com.efhem.byteworksassessment.data.repository

import com.efhem.byteworksassessment.data.local.ByteWorksDatabase
import com.efhem.byteworksassessment.data.local.model.AdminLocal
import com.efhem.byteworksassessment.domain.model.Admin
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AdminRepo(
    val database: ByteWorksDatabase,
    private val ioDispatcher: CoroutineDispatcher =
                            Dispatchers.IO) : IAdminRepo {

    private val adminDao = database.daoAdmin()
    override suspend fun getAdmin(email: String): Admin? = withContext(ioDispatcher){
        adminDao.getAdmin(email)?.toAdmin()
    }

    override suspend fun saveAdmin(admin: Admin) {
        adminDao.insertAdmin(AdminLocal.fromAdmin(admin))
    }


}