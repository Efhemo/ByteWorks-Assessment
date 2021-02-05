package com.efhem.byteworksassessment.data

import androidx.lifecycle.LiveData
import com.efhem.byteworksassessment.data.local.ByteWorksDatabase
import com.efhem.byteworksassessment.data.remote.RemoteApi
import com.efhem.byteworksassessment.data.remote.safeApiResult
import com.efhem.byteworksassessment.domain.model.ResultWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CountryStateRepo(
    val database: ByteWorksDatabase,
    private val remoteApi: RemoteApi,
    private val ioDispatcher: CoroutineDispatcher =
                            Dispatchers.IO) : ICountryStateRepo {

    private val countryStateDao = database.daoCountries()

    override suspend fun getCountry(id: String): CountryState = withContext(ioDispatcher) {
        countryStateDao.getCountry(id) }

    override suspend fun saveCountries(countries: List<CountryState>) {
        countryStateDao.insertEmployee(countries)
    }

    override suspend fun isCountryStateAvailable(): Boolean  = countryStateDao.countryCount() > 0

    override suspend fun fetchCountryState(): ResultWrapper<List<CountryState>> {
        return when(val result = safeApiResult(ioDispatcher){remoteApi.countryState()})  {
            is ResultWrapper.Success -> {
                saveCountries(result.data.countryState)
                ResultWrapper.Success(result.data.countryState)
            }
            is ResultWrapper.Error -> ResultWrapper.Error(result.exception)
            is  ResultWrapper.NetworkError -> ResultWrapper.NetworkError(result.message)
        }
    }

    override suspend fun deleteAllFarm() = countryStateDao.deleteAllCountries()


    override fun observableFarms(): LiveData<List<CountryState>>  = countryStateDao.observeCountries()

}