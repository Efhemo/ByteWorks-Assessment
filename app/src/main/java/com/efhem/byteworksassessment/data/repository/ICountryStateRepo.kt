package com.efhem.byteworksassessment.data.repository

import androidx.lifecycle.LiveData
import com.efhem.byteworksassessment.data.CountryState
import com.efhem.byteworksassessment.domain.model.ResultWrapper

interface ICountryStateRepo {

    suspend fun getCountry(id: String): CountryState
    suspend fun saveCountries(countries: List<CountryState>)
    suspend fun deleteAllFarm()
    fun observableCountryState(): LiveData<List<CountryState>>
    suspend fun isCountryStateAvailable(): Boolean
    suspend fun fetchCountryState(): ResultWrapper<List<CountryState>>
}
