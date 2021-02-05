package com.efhem.byteworksassessment.data

import androidx.lifecycle.LiveData
import com.efhem.byteworksassessment.domain.model.ResultWrapper

interface ICountryStateRepo {

    suspend fun getCountry(id: String): CountryState
    suspend fun saveCountries(countries: List<CountryState>)
    suspend fun deleteAllFarm()
    fun observableFarms(): LiveData<List<CountryState>>
    suspend fun isCountryStateAvailable(): Boolean
    suspend fun fetchCountryState(): ResultWrapper<List<CountryState>>
}
