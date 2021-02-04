package com.efhem.byteworksassessment.data

import androidx.lifecycle.LiveData
import com.efhem.byteworksassessment.data.local.ByteWorksDatabase
import com.efhem.byteworksassessment.data.remote.RemoteApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CountryStateRepo(database: ByteWorksDatabase, remoteApi: RemoteApi, private val ioDispatcher: CoroutineDispatcher =
                            Dispatchers.IO) : ICountryStateRepo {

    private val countryStateDaoDao = database.daoCountries()

    override suspend fun getCountry(id: String): CountryState = withContext(ioDispatcher) {
        countryStateDaoDao.getCountry(id) }

    override suspend fun saveCountries(countries: List<CountryState>) {
        countryStateDaoDao.insertFarms(countries)
    }

    override suspend fun deleteAllFarm() = countryStateDaoDao.deleteAllCountries()


    override fun observableFarms(): LiveData<List<CountryState>>  = countryStateDaoDao.observeCountries()

}