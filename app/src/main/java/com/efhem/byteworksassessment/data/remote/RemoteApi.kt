package com.efhem.byteworksassessment.data.remote

import com.efhem.byteworksassessment.data.remote.model.CountryResponseRemote
import retrofit2.Response
import retrofit2.http.*

interface RemoteApi {

    @GET("countries")
    suspend fun countryState(): Response<CountryResponseRemote>
}