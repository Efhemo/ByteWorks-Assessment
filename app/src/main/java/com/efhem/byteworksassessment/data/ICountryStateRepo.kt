package com.efhem.byteworksassessment.data

import androidx.lifecycle.LiveData

interface ICountryStateRepo {

    suspend fun getCountry(id: String): CountryState
    suspend fun saveCountries(countries: List<CountryState>)
    suspend fun deleteAllFarm()
    fun observableFarms(): LiveData<List<CountryState>>
}
