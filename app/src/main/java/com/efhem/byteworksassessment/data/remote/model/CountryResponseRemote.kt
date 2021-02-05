package com.efhem.byteworksassessment.data.remote.model


import com.efhem.byteworksassessment.data.CountryState
import com.google.gson.annotations.SerializedName

data class CountryResponseRemote(
    @SerializedName("code")
    val code: Int,
    @SerializedName("result")
    val countryState: List<CountryState>
)